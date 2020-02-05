/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private VictorSP rightIntake = new VictorSP(0);
  private VictorSP leftIntake = new VictorSP(1);
  private double multiplier = 1;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    // nothing
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMultiplier(double newMultiplier) {
    multiplier = newMultiplier;
  }

  public void intake(boolean inverse) {
    double speed = inverse ? multiplier * -1 : multiplier;
    rightIntake.setVoltage(speed * 10);
    leftIntake.setVoltage(speed * 10);
  }

  public void stop() {
    rightIntake.set(0);
    leftIntake.set(0);
  }
}
