/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurnToAnglePIDConstants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;

public class TurnToTargetContinous extends CommandBase {
  /**
   * Creates a new TurnToTargetIMUAssist.
   */

  private final Vision m_vision;
  private final DriveTrain m_driveTrain;
  private final PIDController pid;

  public TurnToTargetContinous(final Vision vision, final DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_vision = vision;
    m_driveTrain = driveTrain;
    addRequirements(m_vision);
    addRequirements(m_driveTrain);

    pid = new PIDController(TurnToAnglePIDConstants.kP, TurnToAnglePIDConstants.kI, TurnToAnglePIDConstants.kD);
    pid.setTolerance(TurnToAnglePIDConstants.kTolerance);
    pid.enableContinuousInput(-180, 180);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.visionLightOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final double rotOutput = pid.calculate(m_driveTrain.getHeading().getDegrees(),
        m_driveTrain.getClosestAngle(m_vision.getTimestamp()) + m_vision.getHorizAngle());

    m_driveTrain.curvatureDrive(0, rotOutput, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    m_vision.visionLightOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
