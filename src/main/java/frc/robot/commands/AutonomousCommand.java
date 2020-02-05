/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveTrain;
import static frc.robot.Constants.kPDriveVel;
import static frc.robot.Constants.kRamseteB;
import static frc.robot.Constants.kRamseteZeta;
import static frc.robot.Constants.kaVoltSecondsSquaredPerMeter;
import static frc.robot.Constants.ksVolts;
import static frc.robot.Constants.kvVoltSecondsPerMeter;

public class AutonomousCommand extends CommandBase {

  private Trajectory trajectory;
  private DriveTrain m_driveTrain;
  public Command ramseteCommand;

  /**
   * Creates a new GenerateTrajectory.
   */
  public AutonomousCommand(DriveTrain driveTrain, Trajectory traj) {
    addRequirements(driveTrain);
    trajectory = traj;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ramseteCommand = new RamseteCommand(trajectory, m_driveTrain::getPose,
        new RamseteController(kRamseteB, kRamseteZeta),
        new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter),
        m_driveTrain.getKinematics(), m_driveTrain::getWheelSpeeds, new PIDController(kPDriveVel, 0, 0),
        new PIDController(kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_driveTrain::tankDriveVolts, m_driveTrain);
  }

  // Called once the command ends or is interrupte
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public Command getAutonomousCommand() {
    return ramseteCommand.andThen(() -> m_driveTrain.tankDriveVolts(0, 0));
  }
}
