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
  private String m_inputColor;
  private String m_prevColor;
  private double m_runningTime = 0.0;

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
    m_prevColor = m_colorWheel.getColor();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double startTime = System.currentTimeMillis();
    m_colorWheel.setMotor(0.3);
    m_runningTime += System.currentTimeMillis() - startTime;
    if (m_colorWheel.getColor() != m_prevColor)
      m_runningTime = 0;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheel.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_colorWheel.getColor() == m_inputColor && m_runningTime >= 100;
  }
}
