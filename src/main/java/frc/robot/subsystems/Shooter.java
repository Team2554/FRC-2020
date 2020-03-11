/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final TalonSRX shootMotor = new TalonSRX(ShooterConstants.talonPort);

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shootMotor.configFactoryDefault();
    shootMotor.setSensorPhase(false);
    shootMotor.enableVoltageCompensation(true);
    shootMotor.configVoltageCompSaturation(ShooterConstants.shooterVoltage);
    shootMotor.configNominalOutputForward(0, ShooterConstants.kTimeoutMs);
    shootMotor.configNominalOutputReverse(0, ShooterConstants.kTimeoutMs);
    shootMotor.configPeakOutputForward(1, ShooterConstants.kTimeoutMs);
    shootMotor.configPeakOutputReverse(-1, ShooterConstants.kTimeoutMs);
    shootMotor.config_kF(ShooterConstants.kPIDLoopIdx, ShooterConstants.kF, ShooterConstants.kTimeoutMs);
    shootMotor.config_kP(ShooterConstants.kPIDLoopIdx, ShooterConstants.kP, ShooterConstants.kTimeoutMs);
    shootMotor.config_kI(ShooterConstants.kPIDLoopIdx, ShooterConstants.kI, ShooterConstants.kTimeoutMs);
    shootMotor.config_kD(ShooterConstants.kPIDLoopIdx, ShooterConstants.kD, ShooterConstants.kTimeoutMs);
    Shuffleboard.getTab("SmartDashboard").addNumber("Flywheel Velocity", this::getVelocity)
        .withWidget(BuiltInWidgets.kGraph);
  }

  public void startMotor() {
    shootMotor.set(ControlMode.Velocity, 25000);
  }

  public int getVelocity() {
    return shootMotor.getSelectedSensorVelocity();
  }

  public void periodic() {
    SmartDashboard.putNumber("shooter_velocity", getVelocity());
  }

  public boolean isShootable() {
    return getVelocity() > 25000;
  }

  public void stop() {
    shootMotor.set(ControlMode.Velocity, 0);
  }
}
