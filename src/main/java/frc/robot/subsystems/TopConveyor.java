/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConveyorConstants;

public class TopConveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  private final WPI_VictorSPX leftConveyor1 = new WPI_VictorSPX(ConveyorConstants.leftConveyor1Port);
  private final WPI_VictorSPX leftConveyor2 = new WPI_VictorSPX(ConveyorConstants.leftConveyor2Port);

  private final SpeedControllerGroup leftConveyor = new SpeedControllerGroup(leftConveyor1, leftConveyor2);

  private final WPI_VictorSPX rightConveyor1 = new WPI_VictorSPX(ConveyorConstants.rightConveyor1Port);
  private final WPI_VictorSPX rightConveyor2 = new WPI_VictorSPX(ConveyorConstants.rightConveyor2Port);

  private final SpeedControllerGroup rightConveyor = new SpeedControllerGroup(rightConveyor1, rightConveyor2);
  private final SpeedControllerGroup leftConveyor = new SpeedControllerGroup(leftConveyor1, leftConveyor2);
  public TopConveyor() {
    
  }

  public void conveyorIn() {
    leftConveyor.set(ConveyorConstants.topConveyorVoltage);
    rightConveyor.set(-ConveyorConstants.topConveyorVoltage);
  }

  public void conveyorOut() {
    leftConveyor.set(-ConveyorConstants.topConveyorVoltage);
    rightConveyor.set(ConveyorConstants.topConveyorVoltage);
  }

  public void stopConveyor() {
    leftConveyor.stopMotor();
    rightConveyor.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
