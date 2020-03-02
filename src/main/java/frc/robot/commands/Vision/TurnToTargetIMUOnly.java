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

public class TurnToTargetIMUOnly extends CommandBase {
  /**
   * Creates a new TurnToTargetIMUOnly.
   */

  private final Vision m_vision;
  private final DriveTrain m_driveTrain;
  private final PIDController pid = new PIDController(0, 0, 0);
  private double turnAngle;

  public TurnToTargetIMUOnly(final Vision vision, final DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_vision = vision;
    m_driveTrain = driveTrain;
    addRequirements(m_vision);
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    /*
     * Though the vision light is being turned on here, it is better to turn it on
     * beforehand and ensure that there is a vision target visible, because right
     * after turning on the vision light, the code gets the angle from the camera,
     * and it might take time for the camera to detect a target after the vision
     * light is turned on.
     */
    m_vision.visionLightOn();
    turnAngle = m_vision.getHorizAngle() + m_driveTrain.getHeading().getDegrees();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.curvatureDrive(0, pid.calculate(m_driveTrain.getHeading().getDegrees(), turnAngle), true);
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
