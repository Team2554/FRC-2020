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

public class Shooter extends SubsystemBase {
  private final TalonSRX shootMotor;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    shootMotor = new TalonSRX(Constants.ShooterConstants.talonPort);
    shootMotor.setSensorPhase(true);
    shootMotor.enableVoltageCompensation(true);
    shootMotor.configVoltageCompSaturation(11);
  }

  public void startMotor(final double demand) {
    shootMotor.set(ControlMode.PercentOutput, demand);
  }

  public int getSpeed() {
    return shootMotor.getSelectedSensorVelocity();
  }

  public void periodic() {
    SmartDashboard.putNumber("Flywheel Velocity(rotations * 4096/100ms)", getSpeed());
  }

  public boolean isShootable() {
    return getSpeed() > 24000;
  }

  public void stop() {
    shootMotor.set(ControlMode.PercentOutput, 0);
  }
}
