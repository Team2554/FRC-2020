/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveStraight extends PIDCommand {
  double speed;
  double distance;
  boolean endStop;
  double jank = 0.9166 / 2;
  Timer timer = new Timer();
  boolean timeOut = false;
  PIDController StraightPID = getPIDController();

  public DriveStraight(double distance, double speed, boolean stop, double heading) {
    super(0.1, 0, .225, 0.02); // p = 0.08
    this.speed = speed;
    if (stop)
      this.distance = distance - jank;
    else
      this.distance = distance;
    getPIDController().setInputRange(-180, 180);
    getPIDController().setOutputRange(-1, 1);
    getPIDController().setSetpoint(heading);
    getPIDController().setContinuous(true);
    requires(Robot.m_driveTrain);
    endStop = stop;
  }

  protected void initialize() {
    Robot.m_driveTrain.resetDriveTrain();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.m_driveTrain.getGyroAngle();
  }

  @Override
  protected void usePIDOutput(double rotation) {
    Robot.m_driveTrain.arcadeDrive(speed, rotation);
  }

  @Override
  protected boolean isFinished() {

    if (Math.abs(Robot.m_driveTrain.getDistance()) > Math.abs(0.75 * distance)) {
      timer.start();
      timeOut = true;
    }

    else
      timeOut = false;

    return (Math.abs((Robot.m_driveTrain.getDistance())) >= Math.abs(distance) || (timeOut && timer.get() > 5));
  }

  protected void end() {

    if (endStop)
      Robot.m_driveTrain.stop();

  }

  protected void interrupted() {
    Robot.m_driveTrain.stop();
  }
}