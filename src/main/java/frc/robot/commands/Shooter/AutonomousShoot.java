/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TopConveyor;

public class AutonomousShoot extends CommandBase {
  private final Shooter m_shooter;
  private final TopConveyor m_topConveyor;
  private final BottomConveyor m_bottomConveyor;

  private final double m_optimalVelocity;

  private Timer m_conveyorTimer;
  private boolean m_conveyorsRunning = false;

  /**
   * Creates a new AutonomousShoot.
   */
  public AutonomousShoot(final Shooter shooter, final TopConveyor topConveyor, final BottomConveyor bottomConveyor,
      final double optimalVelocity) {
    m_shooter = shooter;
    m_topConveyor = topConveyor;
    m_bottomConveyor = bottomConveyor;
    m_optimalVelocity = optimalVelocity;
    addRequirements(m_shooter, m_topConveyor, m_bottomConveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.startMotor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_shooter.startMotor();

    double currentVelocity = m_shooter.getVelocity();
    if (Math.abs(
        currentVelocity - m_optimalVelocity) <= (ShooterConstants.kVelocityTolerance / 100.0 * m_optimalVelocity)) {
      // If within desired velocity for shooter, start conveyors
      if (!m_conveyorsRunning) { // Start the timer if it hasn't already been started
        m_conveyorsRunning = true;

        if (m_conveyorTimer == null) { // Reset timer before starting timing
          m_conveyorTimer = new Timer();
        }

        m_conveyorTimer.start();
      }

      m_topConveyor.conveyorIn();
      m_bottomConveyor.conveyorIn();
    } else if (m_conveyorsRunning) {
      // Stop the conveyors if they've already started and pause timer
      m_topConveyor.stopConveyor();
      m_bottomConveyor.stopConveyor();
      m_conveyorTimer.stop();
      m_conveyorsRunning = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_shooter.stop();
    m_topConveyor.stopConveyor();
    m_bottomConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_conveyorTimer == null)
      return false;
    return m_conveyorTimer.get() > 5;
  }
}
