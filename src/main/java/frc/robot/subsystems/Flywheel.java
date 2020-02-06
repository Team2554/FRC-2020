/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FlywheelConstants;

public class Flywheel extends SubsystemBase {
  private final TalonSRX talon = new TalonSRX(FlywheelConstants.TALON_PORT);
  private ControlMode controlMode = ControlMode.PercentOutput;

  /**
   * Creates a new Flywheel.
   */
  public Flywheel() {
    talon.configFactoryDefault();

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, FlywheelConstants.kPIDLoopIdx,
        FlywheelConstants.kTimeoutMs);

    talon.setSensorPhase(true);

    talon.configNominalOutputForward(0, FlywheelConstants.kTimeoutMs);
    talon.configNominalOutputReverse(0, FlywheelConstants.kTimeoutMs);
    talon.configPeakOutputForward(1, FlywheelConstants.kTimeoutMs);
    talon.configPeakOutputReverse(-1, FlywheelConstants.kTimeoutMs);

    talon.config_kF(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.gains.kF, FlywheelConstants.kTimeoutMs);
    talon.config_kP(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.gains.kP, FlywheelConstants.kTimeoutMs);
    talon.config_kI(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.gains.kI, FlywheelConstants.kTimeoutMs);
    talon.config_kD(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.gains.kD, FlywheelConstants.kTimeoutMs);
  }

  /**
   * Toggle Talon control mode
   */
  public void toggleClosedLoop() {
    if (controlMode == ControlMode.PercentOutput) {
      controlMode = ControlMode.Velocity;
    } else {
      controlMode = ControlMode.PercentOutput;
    }
  }

  /**
   * Depending on the mode of the talon, set the motor accordingly. If in
   * ControlMode.Velocity, appropriately handle Velocity PID, otherwise just set
   * talon directly
   * 
   * @param target
   */
  public void setTalon(double target) {
    if (controlMode == ControlMode.Velocity) {
      target *= 500.0 * 4096 / 600;
    }
    talon.set(controlMode, target);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
