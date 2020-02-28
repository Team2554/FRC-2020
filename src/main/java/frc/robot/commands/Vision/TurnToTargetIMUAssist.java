/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class TurnToTargetIMUAssist extends CommandBase {
  /**
   * Creates a new TurnToTargetIMUAssist.
   */

  Vision m_vision;
  DriveTrain m_driveTrain;
  PIDController pid = new PIDController(0, 0, 0);

  public TurnToTargetIMUAssist(Vision vision, DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_vision = vision;
    m_driveTrain = driveTrain;
    addRequirements(m_vision);
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.visionLightOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rotOutput = pid.calculate(m_driveTrain.getHeading().getDegrees(),
        m_driveTrain.getHeading().getDegrees() + m_vision.getHorizAngle());

    m_driveTrain.curvatureDrive(0, rotOutput, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vision.visionLightOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
