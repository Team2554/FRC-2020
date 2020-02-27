/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private VictorSP intake = new VictorSP(IntakeConstants.intakePort);

  public Intake() {
    SmartDashboard.putNumber("Intake Multiplier", 1);
  }

  @Override
  public void periodic() {
  }

  public void start(boolean inverse) {
    double multiplier = SmartDashboard.getNumber("Intake Multiplier", 1);
    double speed = inverse ? -multiplier : multiplier;
    intake.setVoltage(speed * 10);
  }

  public void stop() {
    intake.set(0);
  }
}
