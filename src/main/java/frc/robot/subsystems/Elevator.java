/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  private final VictorSP elevatorMotor = new VictorSP(ElevatorConstants.motorPort);
  /*
   * private final VictorSP levelAdjusterMotorOne = new
   * VictorSP(ElevatorConstants.levelAdjusterMotorOnePort); private final VictorSP
   * levelAdjusterMotorTwo = new
   * VictorSP(ElevatorConstants.levelAdjusterMotorTwoPort); private final
   * SpeedControllerGroup levelAdjuster = new
   * SpeedControllerGroup(levelAdjusterMotorOne, levelAdjusterMotorTwo);
   */
  private final DigitalInput bottomSwitch = new DigitalInput(ElevatorConstants.bottomSwitch);
  private final DigitalInput topSwitch = new DigitalInput(ElevatorConstants.topSwitch);

  public Elevator() {
  }

  /*
   * public void startLevelAdjuster(final int direction) {
   * levelAdjuster.set(direction * 0.3); }
   * 
   * public void stopLevelAdjuster() { levelAdjuster.stopMotor(); }
   */
  public void stopElevator() {
    elevatorMotor.stopMotor();
  }

  public void goUp() {
    elevatorMotor.set(ElevatorConstants.upSpeed);
  }

  public void goDown() {
    elevatorMotor.set(ElevatorConstants.downSpeed);
  }

  public boolean atTop() {
    return topSwitch.get();
  }

  public boolean atBottom() {
    return bottomSwitch.get();
  }
}
