/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  public Victor motor1 = new Victor(ElevatorConstants.motorPort);
  public DigitalInput bottomSwitch = new DigitalInput(ElevatorConstants.bottomSwitch);
  public DigitalInput topSwitch = new DigitalInput(ElevatorConstants.topSwitch);

  public Elevator() {
  }

  public void stopElevator() {
    motor1.set(0);
  }

  public void goUp() {
    motor1.set(1);
  }

  public void goDown() {
    motor1.set(-1);
  }

  public boolean atTop() {
    return topSwitch.get();
  }

  public boolean atBottom() {
    return bottomSwitch.get();
  }
}
