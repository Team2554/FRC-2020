/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TopConveyor;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ConveyorConstants;
import frc.robot.subsystems.TopConveyor;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;

public class WhenConveyorIn extends CommandBase {
  private final double timeTaken = ConveyorConstants.stopTime;
  private final TopConveyor m_conveyor;
  private final Shooter m_shooter;
  public static double checkTime;
  Timer ConveyorTimer = new Timer();

  /**
   * Creates a new WhenConveyorIn.
   */
  public WhenConveyorIn(final TopConveyor conveyor, final Shooter shooter) {
    m_conveyor = conveyor;
    m_shooter = shooter;
    addRequirements(m_conveyor, m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ConveyorTimer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    checkTime = Timer.getFPGATimestamp();
    m_conveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_conveyor.stopConveyor();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (ConveyorTimer.get() >= timeTaken || !m_shooter.isShootable());
  }
}
