/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TopConveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TopConveyor;

public class TopConveyorOut extends CommandBase {
  private final TopConveyor m_conveyor;
  private final Shooter m_shooter;

  /**
   * Creates a new TopConveyorOut.
   */
  public TopConveyorOut(final TopConveyor conveyor, final Shooter shooter) {
    m_conveyor = conveyor;
    m_shooter = shooter;
    addRequirements(m_conveyor, m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_conveyor.conveyorOut();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_conveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_shooter.isShootable();
  }
}