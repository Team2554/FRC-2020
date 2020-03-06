/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandGroups;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TopConveyor;

public class ShootAndConveyorCombined extends CommandBase {
  private TopConveyor m_topConveyor;
  private BottomConveyor m_bottomConveyor;
  private Shooter m_shooter;
  private DoubleSupplier m_voltageSupplier;

  /**
   * Creates a new ShootAndConveyorCombined.
   */
  public ShootAndConveyorCombined(final TopConveyor topConveyor, final BottomConveyor bottomConveyor,
      final Shooter shooter, final DoubleSupplier voltageSupplier) {
    m_topConveyor = topConveyor;
    // Use addRequirements() here to declare subsystem dependencies.
    m_bottomConveyor = bottomConveyor;
    m_shooter = shooter;
    m_voltageSupplier = voltageSupplier;
    addRequirements(m_topConveyor, m_bottomConveyor, m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.startMotor(m_voltageSupplier.getAsDouble());
    m_topConveyor.conveyorIn();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
    m_topConveyor.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !m_shooter.isShootable();
  }
}
