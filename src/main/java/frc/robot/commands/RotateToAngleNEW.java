/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class RotateToAngleNEW extends PIDCommand {
  /**
   * Creates a new DriveStraightNEW.
   */
  public RotateToAngleNEW(double targetAngle, double speed) {
    super(
        // The controller that the command will use
        new PIDController(0.1, 0.01, 0),
        // This should return the measurement
        () -> Robot.m_driveTrain.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> targetAngle,
        // This uses the output
        output -> {
          Robot.m_driveTrain.arcadeDrive(0, speed);
        });

    addRequirements(Robot.m_driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(2.5);
    getController().enableContinuousInput(-180, 180);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

  public void initialize() {
    // Robot.m_driveTrain.gyro.setYaw(0);
  }

  public void stop() {
    getController().close();
    Robot.m_driveTrain.stop();
  }
}