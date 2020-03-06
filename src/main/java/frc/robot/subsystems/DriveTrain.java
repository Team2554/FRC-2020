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
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainPortConstants;
import frc.robot.Custom.SRXMagEncoder_Relative;

public class DriveTrain extends SubsystemBase {

  private final WPI_TalonSRX tRF = new WPI_TalonSRX(DriveTrainPortConstants.tRFPort);
  private final VictorSPX vRB = new VictorSPX(DriveTrainPortConstants.vRBPort);

  private final WPI_TalonSRX tLF = new WPI_TalonSRX(DriveTrainPortConstants.tLFPort);
  private final VictorSPX vLB = new VictorSPX(DriveTrainPortConstants.vLBPort);

  private final SRXMagEncoder_Relative rightEncoder = new SRXMagEncoder_Relative(tRF);
  private final SRXMagEncoder_Relative leftEncoder = new SRXMagEncoder_Relative(tLF);

  private final DifferentialDrive driveTrain = new DifferentialDrive(tLF, tRF);

  private final PigeonIMU pigeon = new PigeonIMU(1);

  private final double maxVoltage = 10;

  private final double wheelDiameterInches = 6;
  private final double differentialWidthMeters = 0.557176939999995;

  // TODO
  // move this and the differential width to Constants.java
  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(differentialWidthMeters);
  private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  // TODO
  // move this to Constants.java as well
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.3, 1.96, 0.06);

  private final PIDController leftPIDController = new PIDController(2.95, 0, 0);
  private final PIDController rightPIDController = new PIDController(2.95, 0, 0);

  private boolean isInverted = false;

  List<Double> gyroTimestamps = new ArrayList<Double>(100);
  List<Double> gyroAngles = new ArrayList<Double>(100);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    driveTrain.setRightSideInverted(false);

    tRF.configFactoryDefault();
    tLF.configFactoryDefault();
    vRB.configFactoryDefault();
    vLB.configFactoryDefault();

    tRF.setNeutralMode(NeutralMode.Brake);
    tLF.setNeutralMode(NeutralMode.Brake);
    vRB.setNeutralMode(NeutralMode.Brake);
    vLB.setNeutralMode(NeutralMode.Brake);

    tRF.enableVoltageCompensation(true);
    tRF.configVoltageCompSaturation(maxVoltage);
    tLF.enableVoltageCompensation(true);
    tLF.configVoltageCompSaturation(maxVoltage);

    vRB.follow(tRF);
    tRF.setInverted(true);
    vRB.setInverted(true);

    vLB.follow(tLF);
    tLF.setInverted(false);
    vLB.setInverted(false);

    leftEncoder.setWheelDiameter(Units.inchesToMeters(wheelDiameterInches));
    rightEncoder.setWheelDiameter(Units.inchesToMeters(wheelDiameterInches));

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

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public void tankDriveVolts(final double leftVolts, final double rightVolts) {
    tLF.set(ControlMode.PercentOutput, leftVolts / maxVoltage);
    tRF.set(ControlMode.PercentOutput, rightVolts / maxVoltage);
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
    pigeon.setYaw(0.0);
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getHeading());
  }

  public Rotation2d getHeading() {
    final double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
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