/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ColorWheel;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;

public class WhiteLineStop extends CommandBase {
  private final DriveTrain m_driveTrain;
  private final ColorWheel m_colorWheel;
  private DoubleSupplier m_speed;
  private DoubleSupplier m_rotation;
  private BooleanSupplier m_quickTurn;

  public WhiteLineStop(final ColorWheel colorWheel, final DriveTrain driveTrain, final DoubleSupplier speed,
      final DoubleSupplier rotation, final BooleanSupplier quickTurn) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_driveTrain = driveTrain;
    this.m_colorWheel = colorWheel;
    this.m_speed = speed;
    this.m_rotation = rotation;
    this.m_quickTurn = quickTurn;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_colorWheel.isWhiteLine()) {
      m_driveTrain.tankDriveVolts(0, 0);
    } else {
      m_driveTrain.curvatureDrive(m_speed.getAsDouble() * 0.25, m_rotation.getAsDouble() * 0.25,
          m_quickTurn.getAsBoolean());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
