/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TopConveyor;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TopConveyor;

public class TopConveyorIn extends CommandBase {
  private final TopConveyor m_topConveyor;
  private final BooleanSupplier m_checkIfShootable;

  /**
   * Creates a new TopConveyorIn.
   */
  public TopConveyorIn(final TopConveyor conveyor, BooleanSupplier checkIfShootable) {
    m_topConveyor = conveyor;
    m_checkIfShootable = checkIfShootable;
    addRequirements(m_topConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_checkIfShootable == null || m_checkIfShootable.getAsBoolean()) {
      m_topConveyor.conveyorIn();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_topConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
