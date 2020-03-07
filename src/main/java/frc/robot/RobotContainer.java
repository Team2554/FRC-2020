/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveJoystickMappings;
import frc.robot.commands.BottomConveyor.BottomConveyorIn;
import frc.robot.commands.ColorWheel.RotateToColor;
import frc.robot.commands.ColorWheel.RotateWheel;
import frc.robot.commands.ColorWheel.WhiteLineStop;
import frc.robot.commands.CommandGroups.BothConveyorsAndShoot;
import frc.robot.commands.CommandGroups.IntakeAndBottom;
import frc.robot.commands.CommandGroups.TimedBothConveyors;
import frc.robot.commands.DriveTrain.DefaultDrive;
import frc.robot.commands.DriveTrain.DriveStraight;
import frc.robot.commands.DriveTrain.RotateToAngle;
import frc.robot.commands.Elevator.ElevatorToBottom;
import frc.robot.commands.Elevator.ElevatorToTop;
import frc.robot.commands.Shooter.ShootCommand;
import frc.robot.commands.TopConveyor.TopConveyorIn;
import frc.robot.commands.TopConveyor.TopConveyorOut;
import frc.robot.commands.Vision.ToggleVisionLight;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TopConveyor;
import frc.robot.subsystems.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class RobotContainer {
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public Joystick m_driveJoystick = new Joystick(0);
    private Joystick m_buttonJoystick = new Joystick(1);

    // Subsystems
    // private Elevator m_elevator = new Elevator();
    private Shooter m_shooter = new Shooter();

    // private TopConveyor m_topConveyor = new TopConveyor();
    // private Intake m_intake = new Intake();
    // private ColorWheel m_colorWheel = new ColorWheel();
    private Vision m_vision = new Vision();
    // TODO: make below private for final code. currently its public so gyro can be
    // reset on teleop init(see Robot.java teleop init)
    // public DriveTrain m_driveTrain = new DriveTrain();
    // private BottomConveyor m_bottomConveyor = new BottomConveyor();

    public RobotContainer() {
        // Configure the button bindings
        // m_driveTrain.setDefaultCommand(new DefaultDrive(m_driveTrain, () ->
        // -m_driveJoystick.getY(),
        // m_driveJoystick::getX, () ->
        // m_driveJoystick.getRawButton(DriveJoystickMappings.quickTurn)));

        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Elevator buttons
        new JoystickButton(m_driveJoystick, 1).whenHeld(new ShootCommand(m_shooter));

        // new JoystickButton(m_driveJoystick, 4).whileHeld(new
        // TopConveyorIn(m_topConveyor));
        // new JoystickButton(m_driveJoystick, 6).whileHeld(new
        // TopConveyorOut(m_topConveyor));

        // new JoystickButton(m_driveJoystick, 3).whileHeld(new
        // IntakeAndBottom(m_intake, m_bottomConveyor, false));
        // new JoystickButton(m_driveJoystick, 5).whileHeld(new
        // IntakeAndBottom(m_intake, m_bottomConveyor, true));

        // new JoystickButton(m_driveJoystick, 2).whenHeld(new
        // TimedBothConveyors(m_topConveyor, m_bottomConveyor));

        // new JoystickButton(m_driveJoystick, 12).whenPressed(new
        // ToggleVisionLight(m_vision));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
