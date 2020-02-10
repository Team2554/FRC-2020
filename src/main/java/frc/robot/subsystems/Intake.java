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

public class Intake extends SubsystemBase {

  private VictorSP intake = new VictorSP(0);

  /**
   * Creates a new Intake.
   */
  public Intake() {
    SmartDashboard.putNumber("Intake Multiplier", 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
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
