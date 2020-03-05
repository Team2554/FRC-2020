/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private final VictorSPX intake = new VictorSPX(IntakeConstants.intakePort);

  public Intake() {
    SmartDashboard.putNumber("Intake Multiplier", 1);
    intake.enableVoltageCompensation(true);
    intake.configVoltageCompSaturation(10);
  }

  @Override
  public void periodic() {
  }

  public void start(final boolean inverse) {
    final double multiplier = SmartDashboard.getNumber("Intake Multiplier", 1);
    final double speed = inverse ? -multiplier : multiplier;
    intake.set(ControlMode.PercentOutput, 1);
  }

  public void stop() {
    intake.set(ControlMode.PercentOutput, 0);
  }
}
