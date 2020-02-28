/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

public class WhenConveyorOut extends CommandBase {
  private final Conveyor m_conveyor;
  private final float timeTaken = 1;
  private final Shooter m_shooter;
  private static double checkTime;

  /**
   * Creates a new WhenConveyorOut.
   */
  public WhenConveyorOut(final Conveyor conveyor, final Shooter shooter) {
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
    checkTime = Timer.getFPGATimestamp();
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
    return (checkTime > timeTaken);
  }
}
