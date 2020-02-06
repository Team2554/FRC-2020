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
import frc.robot.Constants.FlywheelConstants;

public class Flywheel extends SubsystemBase {
  TalonSRX talon = new TalonSRX(FlywheelConstants.TALON_PORT);
  DoubleSupplier leftStickY;
  boolean inClosedLoop = false;

  /**
   * Creates a new Flywheel.
   */
  public Flywheel(DoubleSupplier leftStickY) {
    inClosedLoop = false;
    this.leftStickY = leftStickY;
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
