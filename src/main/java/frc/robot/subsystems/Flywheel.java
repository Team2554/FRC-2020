/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {
  TalonSRX talon = new TalonSRX(Constants.FlywheelConstants.TALON_PORT);
  DoubleSupplier leftStickY;
  boolean inClosedLoop = false;

  /**
   * Creates a new Flywheel.
   */
  public Flywheel(DoubleSupplier leftStickY) {
    inClosedLoop = false;
    this.leftStickY = leftStickY;
    talon.configFactoryDefault();

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.FlywheelConstants.kPIDLoopIdx,
        Constants.FlywheelConstants.kTimeoutMs);

    talon.setSensorPhase(true);

    // talon.configNominalOutputForward(0, )
    talon.configNominalOutputForward(0, Constants.FlywheelConstants.kTimeoutMs);
    talon.configNominalOutputReverse(0, Constants.FlywheelConstants.kTimeoutMs);
    talon.configPeakOutputForward(1, Constants.FlywheelConstants.kTimeoutMs);
    talon.configPeakOutputReverse(-1, Constants.FlywheelConstants.kTimeoutMs);

    talon.config_kF(Constants.FlywheelConstants.kPIDLoopIdx, Constants.FlywheelConstants.gains.kF,
        Constants.FlywheelConstants.kTimeoutMs);
    talon.config_kP(Constants.FlywheelConstants.kPIDLoopIdx, Constants.FlywheelConstants.gains.kP,
        Constants.FlywheelConstants.kTimeoutMs);
    talon.config_kI(Constants.FlywheelConstants.kPIDLoopIdx, Constants.FlywheelConstants.gains.kI,
        Constants.FlywheelConstants.kTimeoutMs);
    talon.config_kD(Constants.FlywheelConstants.kPIDLoopIdx, Constants.FlywheelConstants.gains.kD,
        Constants.FlywheelConstants.kTimeoutMs);
  }

  public void enterClosedLoop() {
    inClosedLoop = true;
  }

  public void exitClosedLoop() {
    inClosedLoop = false;
  }

  public DoubleSupplier getDoubleSupplier() {
    return leftStickY;
  }

  public TalonSRX getTalon() {
    return talon;
  }

  public void closedLoopMode() {
    double targetVelocity_UnitsPer100ms = leftStickY.getAsDouble() * 500.0 * 4096 / 600;
    talon.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
