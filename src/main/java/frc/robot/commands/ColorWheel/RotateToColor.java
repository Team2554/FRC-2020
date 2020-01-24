/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ColorWheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class RotateToColor extends CommandBase {
  private final ColorWheel m_colorWheel;

  private boolean m_isFinished = false;

  private String m_inputColor;

  /**
   * Creates a new RotateToColor.
   */
  public RotateToColor(ColorWheel colorWheel, String inputColor) {
    m_colorWheel = colorWheel;
    m_inputColor = inputColor;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_colorWheel.rotateToColor(m_inputColor);
    m_isFinished = true;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_isFinished;
  }
}
