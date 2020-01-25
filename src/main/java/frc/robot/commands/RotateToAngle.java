package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Robot;

public class RotateToAngle extends PIDCommand {
  int counter = 0;
  int angle;

  public RotateToAngle(int angle1) {

    super(0.1, 0.01, 0);
    angle = angle1;
    getPIDController().setInputRange(-180, 180);
    getPIDController().setOutputRange(-1, 1);
    getPIDController().setAbsoluteTolerance(2.5);
    getPIDController().setSetpoint(angle);
    getPIDController().setContinuous(true);
    requires(Robot.m_driveTrain);
  }

  protected void initialize() {
    // Robot.m_driveTrain.gyro.setYaw(0);
  }

  @Override
  protected void execute() {

    if (Math.abs(Robot.m_oi.stick.getX()) > 0.5 || Math.abs(Robot.m_oi.stick.getY()) > 0.5)
      getPIDController().setSetpoint(-Robot.m_oi.stick.getDirectionDegrees());

    else
      getPIDController().setSetpoint(0);
  }

  @Override
  protected double returnPIDInput() {
    // System.out.println("Gyro Angle: " + Robot.driveTrain.getGyroAngle());
    return Robot.m_driveTrain.getGyroAngle();

  }

  @Override
  protected void usePIDOutput(double speed) {
    System.out.println("PID Error:" + getPIDController().getError());
    // System.out.println("Speed:" + speed);
    Robot.m_driveTrain.arcadeDrive(0, -1 * speed);
  }

  @Override
  protected boolean isFinished() {
    return false;

  }

  protected void end() {
    getPIDController().disable();
    Robot.m_driveTrain.arcadeDrive(0, 0);
  }
}