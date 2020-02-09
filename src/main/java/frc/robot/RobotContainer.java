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
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ColorWheel.RotateWheel;
import frc.robot.subsystems.ColorWheel;
import frc.robot.commands.ShootCommand;
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
  Joystick m_driveJoystick = new Joystick(0);
  Joystick m_buttonJoystick = new Joystick(1);

  // Subsystems
  Shooter m_shooter = new Shooter();
  private final ColorWheel m_colorWheel = new ColorWheel();

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Color wheel buttons
    new JoystickButton(m_buttonJoystick, 1).whenPressed(new RotateWheel(m_colorWheel));

    // Shooter button
    new JoystickButton(m_buttonJoystick, Constants.ButtonJoystickMappings.runShooter)
        .whenHeld(new ShootCommand(m_shooter, () -> 10.5));
    // example on how to use the drive mappings in constants class:
    // new JoystickButton(buttonJoystick,
    // Constants.ButtonJoystickMappings.intakeIn).whileHeld(new InstantCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null; // Change this when autonomous command configured
  }
}
