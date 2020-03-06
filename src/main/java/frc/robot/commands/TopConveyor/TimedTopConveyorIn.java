/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TopConveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ConveyorConstants;
import frc.robot.subsystems.TopConveyor;

public class TimedTopConveyorIn extends CommandBase {

  /**
   * Creates a new TimedTopConveyorIN.
   */
  Timer topTimer = new Timer();

  private final double stopTime = ConveyorConstants.stopTime; // Make this a constant
  private final TopConveyor m_topConveyor;

  public TimedTopConveyorIn(TopConveyor topConveyor) {
    m_topConveyor = topConveyor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_topConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    topTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_topConveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_topConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (topTimer.get() >= stopTime);
  }
}
