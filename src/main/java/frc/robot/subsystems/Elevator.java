/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {
  private final WPI_VictorSPX elevatorMotor1 = new WPI_VictorSPX(ElevatorConstants.motorPort1);
  private final WPI_VictorSPX elevatorMotor2 = new WPI_VictorSPX(ElevatorConstants.motorPort2);

  private final SpeedControllerGroup elevatorMotors = new SpeedControllerGroup(elevatorMotor1, elevatorMotor2);

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
    elevatorMotor1.enableVoltageCompensation(true);
    elevatorMotor1.configVoltageCompSaturation(10);

    elevatorMotor2.enableVoltageCompensation(true);
    elevatorMotor2.configVoltageCompSaturation(10);
  }

  /*
   * public void startLevelAdjuster(final int direction) {
   * levelAdjuster.set(direction * 0.3); }
   * 
   * public void stopLevelAdjuster() { levelAdjuster.stopMotor(); }
   */
  public void stopElevator() {
    elevatorMotors.stopMotor();
  }

  public void goUp() {
    elevatorMotors.setVoltage(ElevatorConstants.upVoltage);
  }

  public void goDown() {
    elevatorMotors.setVoltage(ElevatorConstants.downVoltage);
  }

  public boolean atTop() {
    return topSwitch.get();
  }

  public boolean atBottom() {
    return bottomSwitch.get();
  }
}
