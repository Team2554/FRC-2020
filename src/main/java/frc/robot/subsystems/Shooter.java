/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final TalonSRX shootMotor;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shootMotor = new TalonSRX(Constants.ShooterConstants.talonPort);
    shootMotor.configFactoryDefault();
    shootMotor.setSensorPhase(true);
    shootMotor.enableVoltageCompensation(true);
    shootMotor.configVoltageCompSaturation(Constants.ShooterConstants.shooterVoltage);
    shootMotor.configNominalOutputForward(0, ShooterConstants.kTimeoutMs);
    shootMotor.configNominalOutputReverse(0, ShooterConstants.kTimeoutMs);
    shootMotor.configPeakOutputForward(1, ShooterConstants.kTimeoutMs);
    shootMotor.configPeakOutputReverse(-1, ShooterConstants.kTimeoutMs);
    shootMotor.config_kF(ShooterConstants.kPIDLoopIdx, ShooterConstants.kF, ShooterConstants.kTimeoutMs);
    shootMotor.config_kP(ShooterConstants.kPIDLoopIdx, ShooterConstants.kP, ShooterConstants.kTimeoutMs);
    shootMotor.config_kI(ShooterConstants.kPIDLoopIdx, ShooterConstants.kI, ShooterConstants.kTimeoutMs);
    shootMotor.config_kD(ShooterConstants.kPIDLoopIdx, ShooterConstants.kD, ShooterConstants.kTimeoutMs);

  }

  public void startMotor(final double demand) {
    shootMotor.set(ControlMode.PercentOutput, demand);
    double target = 1.0 * 500.0 * 4096 / 600;
    shootMotor.set(ControlMode.Velocity, target);
  }

  public int getSpeed() {
    return shootMotor.getSelectedSensorVelocity();
  }

  public void periodic() {
    SmartDashboard.putNumber("Flywheel Velocity", getSpeed());
  }

  public boolean isShootable() {
    return getSpeed() > 24000;
  }

  public void stop() {
    shootMotor.set(ControlMode.Velocity, 0);
  }
}
