/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Custom.SRXMagEncoder_Relative;

public class DriveTrain extends SubsystemBase {

  final WPI_TalonSRX tRF = new WPI_TalonSRX(1);
  final VictorSPX vRB = new VictorSPX(3);

  final WPI_TalonSRX tLF = new WPI_TalonSRX(2);
  final VictorSPX vLB = new VictorSPX(4);

  final SRXMagEncoder_Relative rightEncoder = new SRXMagEncoder_Relative(tRF);
  final SRXMagEncoder_Relative leftEncoder = new SRXMagEncoder_Relative(tLF);

  final DifferentialDrive driveTrain = new DifferentialDrive(tLF, tRF);

  final PigeonIMU pigeon = new PigeonIMU(0);

  final double maxVoltage = 10;

  final double wheelDiameterInches = 6;
  final double differentialWidthMeters = 0.557176939999995;

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(differentialWidthMeters);
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.3, 1.96, 0.06);

  PIDController leftPIDController = new PIDController(2.95, 0, 0);
  PIDController rightPIDController = new PIDController(2.95, 0, 0);

  double[] ypr = new double[3];

  boolean isInverted = false;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    tRF.configFactoryDefault();
    tLF.configFactoryDefault();
    vRB.configFactoryDefault();
    vLB.configFactoryDefault();

    tRF.setNeutralMode(NeutralMode.Brake);
    tLF.setNeutralMode(NeutralMode.Brake);
    vRB.setNeutralMode(NeutralMode.Brake);
    vLB.setNeutralMode(NeutralMode.Brake);

    tRF.configVoltageCompSaturation(maxVoltage);
    tRF.enableVoltageCompensation(true);
    tLF.configVoltageCompSaturation(maxVoltage);
    tLF.enableVoltageCompensation(true);

    vRB.follow(tRF);
    tRF.setInverted(false);
    vRB.setInverted(InvertType.FollowMaster);

    vLB.follow(tLF);
    tLF.setInverted(false);
    vLB.setInverted(InvertType.FollowMaster);

    leftEncoder.setWheelDiameter(Units.inchesToMeters(wheelDiameterInches));
    rightEncoder.setWheelDiameter(Units.inchesToMeters(wheelDiameterInches));

    resetEncoders();
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

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    driveTrain.curvatureDrive(xSpeed * (isInverted ? -1 : 1), zRotation, isQuickTurn);
  }

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
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

  public void resetGyro() {
    pigeon.setYaw(0.0);
  }

  public Rotation2d getHeading() {
    pigeon.getYawPitchRoll(ypr);
    return Rotation2d.fromDegrees(Math.IEEEremainder(ypr[0], 360));
  }

  @Override
  public void periodic() {
    odometry.update(getHeading(), leftEncoder.getPosition(), rightEncoder.getPosition());
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  public void inverseInput() {
    isInverted = !isInverted;
  }

}