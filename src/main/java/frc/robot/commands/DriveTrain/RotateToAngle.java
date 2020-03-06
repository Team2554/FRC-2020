/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class RotateToAngle extends CommandBase {
  /**
   * Creates a new TurnToAngle.
   */

  private final DriveTrain m_driveTrain;
  private final double m_targetAngle;
  private final PIDController m_pidController;

  public RotateToAngle(double targetAngle, DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_targetAngle = targetAngle;

    addRequirements(m_driveTrain);

    m_pidController = new PIDController(0.0075, 0.0030, 0.00065);
    m_pidController.setTolerance(1.0);
    m_pidController.enableContinuousInput(-180, 180);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_pidController.setSetpoint(m_targetAngle);
    m_pidController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double pidOutput = m_pidController.calculate(m_driveTrain.getHeading().getDegrees());
    m_driveTrain.tankDriveVolts(10 * pidOutput, -10 * pidOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_pidController.atSetpoint();
  }
}
