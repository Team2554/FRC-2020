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
public class DriveStraightNEW extends PIDCommand {
  double speed, distance;

  public DriveStraightNEW(double distance, double speed, double heading) {
    super(
        // The controller that the command will use
        new PIDController(0.1, 0, .225, 0.02),
        // This should return the measurement
        () -> Robot.m_driveTrain.getGyroAngle(),
        // This should return the setpoint (can also be a constant)
        () -> heading, // <--- I think this should be Robot.m_driveTrain.getGyroAngle()
        // This uses the output
        output -> {
          Robot.m_driveTrain.arcadeDrive(speed, 0);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(Robot.m_driveTrain);
    getController().enableContinuousInput(-180, 180);
    this.speed = speed;
    this.distance = distance;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs((Robot.m_driveTrain.getDistance())) >= Math.abs(distance));
  }

  public void initialize() {
    Robot.m_driveTrain.resetDriveTrain();
  }

  public double returnPIDInput() {
    return Robot.m_driveTrain.getDistance() - distance;
  }

  protected void end() {
    Robot.m_driveTrain.stop();
  }
}