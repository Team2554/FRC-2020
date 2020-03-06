/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.BottomConveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ConveyorConstants;
import frc.robot.subsystems.BottomConveyor;

public class TimedBottomConveyorIn extends CommandBase {
  /**
   * Creates a new BottomConveyorIn.
   */
  Timer m_bottomTimer = new Timer();
  private final BottomConveyor m_bottomConveyor;

  public TimedBottomConveyorIn(BottomConveyor bottomConveyor) {
    m_bottomConveyor = bottomConveyor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_bottomConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_bottomTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_bottomConveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_bottomConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_bottomTimer.get() >= ConveyorConstants.stopTime;
  }
}
