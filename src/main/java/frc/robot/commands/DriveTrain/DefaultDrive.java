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
  private final DoubleSupplier m_speed;
  private final DoubleSupplier m_rotation;
  private final BooleanSupplier m_quickTurn;

  public DefaultDrive(final DriveTrain driveTrain, final DoubleSupplier speed, final DoubleSupplier rotation,
      final BooleanSupplier quickTurn) {
    m_driveTrain = driveTrain;
    m_speed = speed;
    m_rotation = rotation;
    m_quickTurn = quickTurn;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.curvatureDrive(m_speed.getAsDouble(), m_rotation.getAsDouble(), m_quickTurn.getAsBoolean());
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
