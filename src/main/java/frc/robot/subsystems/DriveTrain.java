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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  private final double deadBandConstant = 0.02;
  private final double limitConstant = 1;

  final TalonSRX tRF = new TalonSRX(1);
  final VictorSPX vRB = new VictorSPX(3);

  final TalonSRX tLF = new TalonSRX(2);
  final VictorSPX vLB = new VictorSPX(4);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    tRF.set(ControlMode.PercentOutput, 0);
    tLF.set(ControlMode.PercentOutput, 0);

    vRB.set(ControlMode.Follower, 0);
    vLB.set(ControlMode.Follower, 0);

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

    vRB.follow(tRF);
    tRF.setInverted(false);
    vRB.setInverted(InvertType.FollowMaster);

    vLB.follow(tLF);
    tLF.setInverted(false);
    vLB.setInverted(InvertType.FollowMaster);
  }

  public void arcadeDrive(double xSpeed, double zRotation) {
    xSpeed = limit(xSpeed);
    xSpeed = deadBand(xSpeed);

    zRotation = limit(zRotation);
    zRotation = deadBand(zRotation);

    // Square the inputs (while preserving the sign) to increase fine control
    // while permitting full power.
    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
    zRotation = Math.copySign(zRotation * zRotation, zRotation);

    double leftMotorOutput;
    double rightMotorOutput;

    double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

    if (xSpeed >= 0.0) {
      if (zRotation >= 0.0) {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      } else {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      }
    } else {
      if (zRotation >= 0.0) {
        leftMotorOutput = xSpeed + zRotation;
        rightMotorOutput = maxInput;
      } else {
        leftMotorOutput = maxInput;
        rightMotorOutput = xSpeed - zRotation;
      }
    }

    tLF.set(ControlMode.PercentOutput, limit(leftMotorOutput));
    tRF.set(ControlMode.PercentOutput, limit(rightMotorOutput));
  }

  public double limit(double x) {
    if (x > limitConstant)
      x = limitConstant;
    if (x < -limitConstant)
      x = -limitConstant;

    return x;
  }

  public double deadBand(double x) {
    if (x < deadBandConstant)
      return 0.0;
    return x;
  }

  public double getGyroAngle() {
    return getGyroAngle();
  }

  public int getDistance() {
    return (tLF.getSelectedSensorPosition() + tRF.getSelectedSensorPosition()) / 2;
  }

  public void stop() {
    arcadeDrive(0, 0);
  }

  public void resetDriveTrain() {
    // reset encoders and stuff maybe
  }
}
