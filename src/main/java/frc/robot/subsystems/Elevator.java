/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  public Victor elevatorMotor = new Victor(ElevatorConstants.motorPort);

  public Victor levelAdjusterMotorOne = new Victor(ElevatorConstants.levelAdjusterMotorOnePort);
  public Victor levelAdjusterMotorTwo = new Victor(ElevatorConstants.levelAdjusterMotorTwoPort);
  public SpeedControllerGroup levelAdjuster = new SpeedControllerGroup(levelAdjusterMotorOne, levelAdjusterMotorTwo);

  public DigitalInput bottomSwitch = new DigitalInput(ElevatorConstants.bottomSwitch);
  public DigitalInput topSwitch = new DigitalInput(ElevatorConstants.topSwitch);

  public Elevator() {
  }

  public void startLevelAdjuster(int direction) {
    levelAdjuster.set(direction * 0.3);
  }

  public void stopLevelAdjuster() {
    levelAdjuster.stopMotor();
  }

  public void stopElevator() {
    elevatorMotor.stopMotor();
  }

  public void goUp() {
    elevatorMotor.set(1);
  }

  public void goDown() {
    elevatorMotor.set(-1);
  }

  public boolean atTop() {
    return topSwitch.get();
  }

  public boolean atBottom() {
    return bottomSwitch.get();
  }
}
