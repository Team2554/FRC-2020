/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Map;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveTrain;
import static frc.robot.Constants.kMaxAccelerationMetersPerSecondSquared;
import static frc.robot.Constants.kMaxSpeedMetersPerSecond;
import static frc.robot.Constants.kPDriveVel;
import static frc.robot.Constants.kRamseteB;
import static frc.robot.Constants.kRamseteZeta;
import static frc.robot.Constants.kaVoltSecondsSquaredPerMeter;
import static frc.robot.Constants.ksVolts;
import static frc.robot.Constants.kvVoltSecondsPerMeter;

public class AutonomousCommand extends CommandBase {

  Trajectory trajectory;
  DriveTrain m_driveTrain;
  private Command ramseteCommand;
  TrajectoryConfig config;

  /**
   * Creates a new GenerateTrajectory.
   */
  public AutonomousCommand(DriveTrain driveTrain, Trajectory traj) {
    addRequirements(driveTrain);
    config = new TrajectoryConfig(kMaxSpeedMetersPerSecond, kMaxAccelerationMetersPerSecondSquared)
        .setKinematics(driveTrain.getKinematics());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  public Command getAutonomousCommand() {
    return ramseteCommand.andThen(() -> m_driveTrain.tankDriveVolts(0, 0));
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RamseteCommand ramseteCommand = new RamseteCommand(trajectory, m_driveTrain::getHeading,
        new RamseteController(kRamseteB, kRamseteZeta),
        new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter, kaVoltSecondsSquaredPerMeter),
        m_driveTrain.getKinematics(), m_driveTrain::getWheelSpeeds, new PIDController(kPDriveVel, 0, 0),
        new PIDController(kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_driveTrain::tankDriveVolts, m_driveTrain);
  }

  // Called once the command ends or is interrupte
  Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
