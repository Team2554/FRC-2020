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
import frc.robot.commands.CommandGroups.TimedBothConveyors;
import frc.robot.commands.CommandGroups.ShooterAndConveyors;
import frc.robot.commands.DriveTrain.DefaultDrive;
import frc.robot.commands.DriveTrain.DriveStraightNEW;
import frc.robot.commands.DriveTrain.RotateToAngleNEW;
import frc.robot.commands.Elevator.ElevatorToBottom;
import frc.robot.commands.Elevator.ElevatorToTop;
import frc.robot.commands.Elevator.LevelAdjusterLeft;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.TopConveyor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

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
        private final Joystick m_driveJoystick = new Joystick(0);
        private final Joystick m_buttonJoystick = new Joystick(1);

        // Subsystems
        private final Elevator m_elevator = new Elevator();
        private final Shooter m_shooter = new Shooter();
        private final TopConveyor m_topConveyor = new TopConveyor();
        private final ColorWheel m_colorWheel = new ColorWheel();
        private final DriveTrain m_driveTrain = new DriveTrain();
        private final BottomConveyor m_bottomConveyor = new BottomConveyor();

        public RobotContainer() {
                // Configure the button bindings
                m_driveTrain.setDefaultCommand(new DefaultDrive(m_driveTrain, () -> -m_driveJoystick.getY(),
                                () -> m_driveJoystick.getX(),
                                () -> m_driveJoystick.getRawButton(DriveJoystickMappings.quickTurn)));

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
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.elevatorUp)
                                .whileHeld(new ElevatorToTop(m_elevator));
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.elevatorDown)
                                .whileHeld(new ElevatorToBottom(m_elevator));

                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.setElevatorTop)
                                .whenPressed(new ElevatorToTop(m_elevator));
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.setElevatorButtom)
                                .whenPressed(new ElevatorToBottom(m_elevator));

                // Elevator Level Adjuster
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.levelAdjusterLeft)
                                .whenPressed(new LevelAdjusterLeft(m_elevator));
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.levelAdjusterRight)
                                .whenPressed(new LevelAdjusterLeft(m_elevator));

                // Color wheel buttons
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.colorWheelSpinNumberOfTimes)
                                .whenPressed(new RotateWheel(m_colorWheel));
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.colorWheelTurnToColor)
                                .whenPressed(new RotateToColor(m_colorWheel, m_colorWheel::getSelectedColor));

                // Conveyor and Shooter
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.shootersAndConveyors).whenPressed(
                                new ShooterAndConveyors(m_shooter, m_bottomConveyor, m_topConveyor, () -> 11.5));

                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.timedConveyors)
                                .whenPressed(new TimedBothConveyors(m_topConveyor, m_bottomConveyor));

                // Just Bottom Conveyor
                new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.bottomConveyor)
                                .whenPressed(new BottomConveyorIn(m_bottomConveyor, m_shooter));

                // DriveStraight button
                new JoystickButton(m_buttonJoystick, Constants.DriveJoystickMappings.driveStraight).whenPressed(
                                new DriveStraightNEW(1, 0.2, m_driveTrain.getHeading().getDegrees(), m_driveTrain));

                // RotateToAngle
                new JoystickButton(m_buttonJoystick, Constants.DriveJoystickMappings.rotateToAngle)
                                .whenPressed(new RotateToAngleNEW(90, 0.1, m_driveTrain));
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                return new InstantCommand();
        }
}
