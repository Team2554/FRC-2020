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

public class Elevator extends SubsystemBase {
  public Victor motor1 = new Victor(0);
  public DigitalInput bottomSwitch = new DigitalInput(0);
  public DigitalInput topSwitch = new DigitalInput(1);

  boolean goingUp = true;

  public Elevator() {
  }

  public void stopElevator() {
    motor1.set(0);
  }

  public void startElevator() {
    if (goingUp && !atTop()) {
      motor1.set(1);
    } else if (!goingUp && !atBottom()) {
      motor1.set(-1);
    } else {
      stopElevator();
    }
  }

  public boolean atTop() {
    return topSwitch.get();
  }

  public boolean atBottom() {
    return bottomSwitch.get();
  }

  public void goDown() {
    goingUp = false;
  }

  public void goUp() {
    goingUp = true;
  }
}
