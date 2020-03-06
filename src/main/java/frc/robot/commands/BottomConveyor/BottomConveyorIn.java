/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.BottomConveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BottomConveyor;

public class BottomConveyorIn extends CommandBase {
  private final BottomConveyor m_bottomConveyor;

  /**
   * Creates a new BottomConveyorIn.
   */
  public BottomConveyorIn(final BottomConveyor bottomConveyor) {
    m_bottomConveyor = bottomConveyor;
    addRequirements(m_bottomConveyor);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_bottomConveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_bottomConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
