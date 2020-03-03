/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DefaultDrive extends CommandBase {
  /**
   * Creates a new DefaultDrive.
   */

  private final DriveTrain m_driveTrain;
  private final DoubleSupplier speed;
  private final DoubleSupplier rotation;
  private final BooleanSupplier quickTurn;

  public DefaultDrive(final DriveTrain driveTrain, final DoubleSupplier speed, final DoubleSupplier rotation,
      final BooleanSupplier quickTurn) {
    this.m_driveTrain = driveTrain;
    this.speed = speed;
    this.rotation = rotation;
    this.quickTurn = quickTurn;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.curvatureDrive(speed.getAsDouble(), rotation.getAsDouble(), quickTurn.getAsBoolean());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    if (interrupted)
      m_driveTrain.curvatureDrive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
