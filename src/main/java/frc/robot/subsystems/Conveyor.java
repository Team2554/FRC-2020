/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConveyorConstants;
import edu.wpi.first.wpilibj.VictorSP;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  VictorSP bottomConveyor = new VictorSP(ConveyorConstants.bottomConveyorPort);
  VictorSP topConveyor = new VictorSP(ConveyorConstants.topConveyorPort);

  public Conveyor() {
  }

  public void conveyorIn() {
    bottomConveyor.set(ConveyorConstants.conveyorSpeed);
    topConveyor.set(ConveyorConstants.conveyorSpeed);
  }

  public void conveyorOut() {
    bottomConveyor.set(-ConveyorConstants.conveyorSpeed);
    topConveyor.set(-ConveyorConstants.conveyorSpeed);
  }

  public void stopConveyor() {
    bottomConveyor.set(0);
    topConveyor.set(0);
  }

  @Override
  public void periodic() {
  }
}
