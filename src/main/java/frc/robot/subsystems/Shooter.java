/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final TalonSRX m_shootMotor = new TalonSRX(ShooterConstants.shooterPort);

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    m_shootMotor.configFactoryDefault();

    m_shootMotor.setSensorPhase(true);
    m_shootMotor.enableVoltageCompensation(true);
    m_shootMotor.configVoltageCompSaturation(11);

    m_shootMotor.configNominalOutputForward(0, ShooterConstants.kTimeoutMs);
    m_shootMotor.configNominalOutputReverse(0, ShooterConstants.kTimeoutMs);
    m_shootMotor.configPeakOutputForward(1, ShooterConstants.kTimeoutMs);
    m_shootMotor.configPeakOutputReverse(-1, ShooterConstants.kTimeoutMs);

    m_shootMotor.config_kF(ShooterConstants.kPIDLoopIdx, ShooterConstants.kF, ShooterConstants.kTimeoutMs);
    m_shootMotor.config_kP(ShooterConstants.kPIDLoopIdx, ShooterConstants.kP, ShooterConstants.kTimeoutMs);
    m_shootMotor.config_kI(ShooterConstants.kPIDLoopIdx, ShooterConstants.kI, ShooterConstants.kTimeoutMs);
    m_shootMotor.config_kD(ShooterConstants.kPIDLoopIdx, ShooterConstants.kD, ShooterConstants.kTimeoutMs);
  }

  /**
   * Depending on the mode of the talon, set the motor accordingly. If in
   * ControlMode.Velocity, appropriately handle Velocity PID, otherwise just set
   * talon directly
   * 
   * @param target The raw value provided by whatever DoubleSupplier is used
   */
  public void setTalon(double target) {
    m_shootMotor.set(ControlMode.Velocity, target * 500.0 * 4096 / 600);
  }

  public void stopMotor() {
    m_shootMotor.set(ControlMode.Velocity, 0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Raw Peak Velocity", m_shootMotor.getSelectedSensorVelocity());
  }
}
