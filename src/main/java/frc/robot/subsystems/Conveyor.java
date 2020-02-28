/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConveyorConstants;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  VictorSP leftConveyor1 = new VictorSP(ConveyorConstants.leftConveyor1Port);
  VictorSP leftConveyor2 = new VictorSP(ConveyorConstants.leftConveyor2Port);

  SpeedControllerGroup leftConveyor = new SpeedControllerGroup(leftConveyor1, leftConveyor2);

  VictorSP rightConveyor1 = new VictorSP(ConveyorConstants.rightConveyor1Port);
  VictorSP rightConveyor2 = new VictorSP(ConveyorConstants.rightConveyor2Port);

  SpeedControllerGroup rightConveyor = new SpeedControllerGroup(rightConveyor1, rightConveyor2);

  VictorSP bottomConveyor = new VictorSP(ConveyorConstants.bottomConveyorPort);

  public Conveyor() {
  }

  public void conveyorIn() {
    bottomConveyor.setVoltage(ConveyorConstants.bottomConveyorVoltage);
    leftConveyor.setVoltage(ConveyorConstants.topConveyorVoltage);
    rightConveyor.setVoltage(-ConveyorConstants.topConveyorVoltage);
  }

  public void conveyorOut() {
    bottomConveyor.setVoltage(-ConveyorConstants.bottomConveyorVoltage);
    leftConveyor.setVoltage(ConveyorConstants.topConveyorVoltage);
    rightConveyor.setVoltage(-ConveyorConstants.topConveyorVoltage);
  }

  public void stopConveyor() {
    bottomConveyor.stopMotor();
    leftConveyor.stopMotor();
    rightConveyor.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
