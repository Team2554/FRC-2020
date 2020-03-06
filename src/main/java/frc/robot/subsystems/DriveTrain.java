/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.custom.SRXMagEncoder_Relative;

public class DriveTrain extends SubsystemBase {

  private final WPI_TalonSRX talonRight = new WPI_TalonSRX(DriveTrainConstants.talonRightPort);
  private final VictorSPX victorRight = new VictorSPX(DriveTrainConstants.victorRightPort);

  private final WPI_TalonSRX talonLeft = new WPI_TalonSRX(DriveTrainConstants.talonLeftPort);
  private final VictorSPX victorLeft = new VictorSPX(DriveTrainConstants.victorLeftPort);

  private final SRXMagEncoder_Relative rightEncoder = new SRXMagEncoder_Relative(talonRight);
  private final SRXMagEncoder_Relative leftEncoder = new SRXMagEncoder_Relative(talonLeft);

  private final DifferentialDrive driveTrain = new DifferentialDrive(talonLeft, talonRight);

  private final PigeonIMU pigeonIMU = new PigeonIMU(1);

  private final double maxVoltage = 10;

  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  private final PIDController leftPIDController = new PIDController(DriveTrainConstants.kP, DriveTrainConstants.kI,
      DriveTrainConstants.kD);
  private final PIDController rightPIDController = new PIDController(DriveTrainConstants.kP, DriveTrainConstants.kI,
      DriveTrainConstants.kD);

  private boolean isInverted = false;

  List<Double> gyroTimestamps = new ArrayList<>(100);
  List<Double> gyroAngles = new ArrayList<>(100);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    driveTrain.setRightSideInverted(false);

    talonRight.configFactoryDefault();
    talonLeft.configFactoryDefault();
    victorRight.configFactoryDefault();
    victorLeft.configFactoryDefault();

    talonRight.setNeutralMode(NeutralMode.Brake);
    talonLeft.setNeutralMode(NeutralMode.Brake);
    victorRight.setNeutralMode(NeutralMode.Brake);
    victorLeft.setNeutralMode(NeutralMode.Brake);

    talonRight.configVoltageCompSaturation(maxVoltage);
    talonRight.enableVoltageCompensation(true);
    talonLeft.configVoltageCompSaturation(maxVoltage);
    talonLeft.enableVoltageCompensation(true);

    victorRight.follow(talonRight);
    talonRight.setInverted(true);
    victorRight.setInverted(true);

    victorLeft.follow(talonLeft);
    talonLeft.setInverted(false);
    victorLeft.setInverted(false);

    leftEncoder.setWheelDiameter(Units.inchesToMeters(DriveTrainConstants.wheelDiameterInches));
    rightEncoder.setWheelDiameter(Units.inchesToMeters(DriveTrainConstants.wheelDiameterInches));

    leftEncoder.configure();
    rightEncoder.configure();

    resetEncoders();
    resetGyro();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
  }

  public PIDController getLeftPIDController() {
    return leftPIDController;
  }

  public PIDController getRightPIDController() {
    return rightPIDController;
  }

  public void curvatureDrive(final double xSpeed, final double zRotation, final boolean isQuickTurn) {
    driveTrain.curvatureDrive(xSpeed * (isInverted ? -1 : 1), zRotation, isQuickTurn);
  }

  public void tankDriveVolts(final double leftVolts, final double rightVolts) {
    talonLeft.set(ControlMode.PercentOutput, leftVolts / maxVoltage);
    talonRight.set(ControlMode.PercentOutput, rightVolts / maxVoltage);
    driveTrain.feed();
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getAverageEncoderDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2;
  }

  public double getAverageEncoderVelocity() {
    return (leftEncoder.getVelocity() + rightEncoder.getVelocity()) / 2;
  }

  public void resetGyro() {
    pigeonIMU.setYaw(0.0);
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getHeading());
  }

  public Rotation2d getHeading() {
    final double[] ypr = new double[3];
    pigeonIMU.getYawPitchRoll(ypr);

    return Rotation2d.fromDegrees(Math.IEEEremainder(ypr[0], 360));
  }

  public double getClosestAngle(double timestamp) {
    int closestIdx = 0;
    for (int i = 1; i < gyroTimestamps.size(); i++) {
      if (Math.abs(gyroTimestamps.get(i) - timestamp) > Math.abs(gyroTimestamps.get(closestIdx) - timestamp))
        closestIdx = i;
    }

    return gyroAngles.get(closestIdx);
  }

  @Override
  public void periodic() {
    odometry.update(getHeading(), leftEncoder.getPosition(), rightEncoder.getPosition());
    SmartDashboard.putNumber("Gyro", getHeading().getDegrees());
    SmartDashboard.putNumber("Velocity", getAverageEncoderVelocity());

    gyroTimestamps.add(Timer.getFPGATimestamp());
    gyroAngles.add(getHeading().getDegrees());

    if (gyroTimestamps.size() > 100) {
      gyroTimestamps.remove(0);
      gyroAngles.remove(0);
    }
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void inverseInput() {
    isInverted = !isInverted;
  }
}
