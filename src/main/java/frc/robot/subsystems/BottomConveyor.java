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

public class BottomConveyor extends SubsystemBase {
  /**
   * Creates a new BottomConveyor.
   */
  private final VictorSP bottomConveyor = new VictorSP(ConveyorConstants.bottomConveyorPort);

  public BottomConveyor() {

  }

  public void ballIn() {
    bottomConveyor.setVoltage(ConveyorConstants.bottomConveyorVoltage);
  }

  public void ballOut() {
    bottomConveyor.setVoltage(-ConveyorConstants.bottomConveyorVoltage);
  }

  public void stopBottomConveyor() {
    bottomConveyor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
