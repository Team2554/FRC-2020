/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ColorWheel;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.subsystems.ColorWheel;

public class WhiteLine extends CommandBase {
  private final ColorWheel m_colorWheel;
  private String m_currentColor;

  /**
   * Creates a new WhiteLine.
   */
  public WhiteLine(final ColorWheel colorWheel) {
    m_colorWheel = colorWheel;
    addRequirements(m_colorWheel);
    m_currentColor = m_colorWheel.getWhiteLineColor();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putString("WhiteLine Color", m_colorWheel.getWhiteLineColor());

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
