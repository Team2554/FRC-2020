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

public class TopConveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  private final VictorSP leftConveyor1 = new VictorSP(ConveyorConstants.leftConveyor1Port);
  private final VictorSP leftConveyor2 = new VictorSP(ConveyorConstants.leftConveyor2Port);

  private final SpeedControllerGroup leftConveyor = new SpeedControllerGroup(leftConveyor1, leftConveyor2);

  private final VictorSP rightConveyor1 = new VictorSP(ConveyorConstants.rightConveyor1Port);
  private final VictorSP rightConveyor2 = new VictorSP(ConveyorConstants.rightConveyor2Port);

  private final SpeedControllerGroup rightConveyor = new SpeedControllerGroup(rightConveyor1, rightConveyor2);

  public TopConveyor() {
  }

  public void conveyorIn() {
    leftConveyor.setVoltage(ConveyorConstants.topConveyorVoltage);
    rightConveyor.setVoltage(-ConveyorConstants.topConveyorVoltage);
  }

  public void conveyorOut() {
    leftConveyor.setVoltage(-ConveyorConstants.topConveyorVoltage);
    rightConveyor.setVoltage(ConveyorConstants.topConveyorVoltage);
  }

  public void stopConveyor() {
    leftConveyor.stopMotor();
    rightConveyor.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
