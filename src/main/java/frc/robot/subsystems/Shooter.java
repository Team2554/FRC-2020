/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  VictorSP m_shootMotor;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    m_shootMotor = new VictorSP(Constants.ShooterConstants.victorPort);
  }

  public void setVoltage(double voltage) {
    m_shootMotor.setVoltage(voltage);
  }

  public void stopMotor() {
    setVoltage(0);
  }
}
