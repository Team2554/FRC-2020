/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.VictorSP;

public class Conveyer extends SubsystemBase {
  /**
   * Creates a new Conveyer.
   */
  VictorSP BottomConveyer = new VictorSP(0);
  VictorSP TopConveyer = new VictorSP(1);

  public Conveyer() {

  }

  public void conveyerRunFirst() {
    BottomConveyer.set(0.45);
    TopConveyer.set(0.45);
  }

  void conveyerSetSpeedNegative() {
    BottomConveyer.set(-0.45);
    TopConveyer.set(-0.45);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
