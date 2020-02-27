/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Conveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj.Timer;

public class WhenConveyorIn extends CommandBase {
  private Timer baseTime = new Timer();
  private boolean timeFail = false;
  private float timeTaken = 1;
  private final Conveyor m_conveyor;
  public static double checkTime;

  /**
   * Creates a new WhenConveyorIn.
   */
  public WhenConveyorIn(Conveyor conveyor) {
    m_conveyor = conveyor;
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    checkTime = Timer.getFPGATimestamp();
    m_conveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_conveyor.stopConveyor();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (checkTime > timeTaken);
  }
}
