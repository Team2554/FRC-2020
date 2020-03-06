/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ConveyorConstants;

public class TopConveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  private final WPI_VictorSPX leftConveyor = new WPI_VictorSPX(ConveyorConstants.leftConveyorPort);

  private final WPI_VictorSPX rightConveyor = new WPI_VictorSPX(ConveyorConstants.rightConveyorPort);

  public TopConveyor() {
    leftConveyor.enableVoltageCompensation(true);
    leftConveyor.configVoltageCompSaturation(ConveyorConstants.topConveyorVoltage);

    rightConveyor.enableVoltageCompensation(true);
    rightConveyor.configVoltageCompSaturation(ConveyorConstants.topConveyorVoltage);
  }

  public void conveyorIn() {
    leftConveyor.set(1);
    rightConveyor.set(-1);
  }

  public void conveyorOut() {
    leftConveyor.set(-1);
    rightConveyor.set(1);
  }

  public void stopConveyor() {
    leftConveyor.stopMotor();
    rightConveyor.stopMotor();
  }

  @Override
  public void periodic() {
  }
}
