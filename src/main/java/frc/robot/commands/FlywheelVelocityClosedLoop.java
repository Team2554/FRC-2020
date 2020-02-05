/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Flywheel;

public class FlywheelVelocityClosedLoop extends CommandBase {
  Flywheel m_flywheel;

  /**
   * Creates a new FlywheelVelocityClosedLoop.
   */
  public FlywheelVelocityClosedLoop(Flywheel flywheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_flywheel = flywheel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double targetVelocity = m_flywheel.getDoubleSupplier().getAsDouble() * 500.0 * 4096 / 600;
    m_flywheel.getTalon().set(ControlMode.Velocity, targetVelocity);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
