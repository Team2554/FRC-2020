/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveStraightNEW extends PIDCommand {
  double speed, distance;

  private final DriveTrain m_driveTrain;

  public DriveStraightNEW(double distance, double speed, double heading, DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(0.1, 0, .225, 0.02),
        // This should return the measurement
        () -> driveTrain.getHeading().getDegrees(),
        // This should return the setpoint (can also be a constant)
        () -> heading,
        // This uses the output
        output -> {
          driveTrain.curvatureDrive(speed, 0, true);
        }, driveTrain);

    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(m_driveTrain);
    getController().enableContinuousInput(-180, 180);
    this.speed = speed;
    this.distance = distance;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs((m_driveTrain.getAverageEncoderDistance())) >= Math.abs(distance));
  }

  public void initialize() {
  }

  public double returnPIDInput() {
    return m_driveTrain.getAverageEncoderDistance() - distance;
  }

  protected void end() {
    m_driveTrain.curvatureDrive(0, 0, true);
  }
}