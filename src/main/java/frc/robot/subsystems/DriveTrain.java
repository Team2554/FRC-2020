/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;<<<<<<<HEAD
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;=======>>>>>>>8 a1fd73b3b675b1695216dd7e336402c9824d022
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  final WPI_TalonSRX tRF = new WPI_TalonSRX(1);<<<<<<<HEAD=======
  Encoder encoder = new Encoder(1, 2);>>>>>>>8 a1fd73b3b675b1695216dd7e336402c9824d022
  final VictorSPX vRB = new VictorSPX(3);

  final WPI_TalonSRX tLF = new WPI_TalonSRX(2);
  final VictorSPX vLB = new VictorSPX(4);

  final DifferentialDrive driveTrain = new DifferentialDrive(tLF, tRF);

  final PigeonIMU pigeon = new PigeonIMU(0);

  final double maxVoltage = 12;

  final int encoderUnitsPerRotation = 4096;
  final double wheelDiameterMeters = 0.1;
  final double differentialWidthMeters = 0.5;

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(differentialWidthMeters);
  DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());

  SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.3, 1.96, 0.06);

  PIDController leftPIDController = new PIDController(2.95, 0, 0);
  PIDController rightPIDController = new PIDController(2.95, 0, 0);

  Pose2d pose = new Pose2d();

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

    tRF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    tLF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

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

    resetEncoders();
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

  <<<<<<<HEAD

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(tLF.getSelectedSensorVelocity(), tRF.getSelectedSensorVelocity());
  }

  =======>>>>>>>8 a1fd73b3b675b1695216dd7e336402c9824d022

  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }

  public double getLeftMeters() {
    return encoderTicksToMeters(tLF.getSelectedSensorPosition());
  }

  public double getRightMeters() {
    return encoderTicksToMeters(tRF.getSelectedSensorPosition());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    tLF.set(ControlMode.PercentOutput, leftVolts / maxVoltage);
    tRF.set(ControlMode.PercentOutput, rightVolts / maxVoltage);
  }

  public void resetEncoders() {
    tLF.setSelectedSensorPosition(0);
    tRF.setSelectedSensorPosition(0);
  }

  public double getAverageEncoderDistance() {
    return (encoderTicksToMeters(tLF.getSelectedSensorPosition())
        + encoderTicksToMeters(tRF.getSelectedSensorPosition())) / 2;
  }

  public void resetGyro() {
    pigeon.setYaw(0.0);
  }

  public Rotation2d getHeading() {
    pigeon.getYawPitchRoll(ypr);
    return Rotation2d.fromDegrees(ypr[0]);
  }

  public double encoderTicksToMeters(int encoderVal) {
    return ((double) encoderVal / encoderUnitsPerRotation) * Math.PI * wheelDiameterMeters;
  }

  public double encoderTicksPer100msToMetersPerSecond(int encoderVal) {
    return encoderTicksToMeters(encoderVal) / 0.1;
  }

  @Override
  public void periodic() {
    pose = odometry.update(getHeading(), getLeftMeters(), getRightMeters());
  }

  public Pose2d getPose() {
<<<<<<< HEAD
    return odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading().getDegrees()));
=======
    return pose;
>>>>>>> 8a1fd73b3b675b1695216dd7e336402c9824d022
  }

  public void inverseInput() {
    isInverted = !isInverted;
  }

}