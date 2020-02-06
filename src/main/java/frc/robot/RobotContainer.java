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
import frc.robot.Constants.ButtonJoystickMappings;
import frc.robot.subsystems.Flywheel;
import frc.robot.commands.Flywheel.RunFlywheel;
import frc.robot.commands.Flywheel.ToggleClosedLoop;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Flywheel m_flywheel = new Flywheel();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  private Joystick driveJoystick = new Joystick(0);
  private Joystick buttonJoystick = new Joystick(1);

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
    // When a button is held down, set the flywheel motor to some double supplier
    // (TBD)
    new JoystickButton(buttonJoystick, ButtonJoystickMappings.runFlywheel)
        .whenHeld(new RunFlywheel(m_flywheel, () -> buttonJoystick.getY()));

    // Velocity closed loop for flywheel is toggled when a button is pressed
    new JoystickButton(buttonJoystick, ButtonJoystickMappings.setFlywheelClosedLoop)
        .toggleWhenPressed(new ToggleClosedLoop(m_flywheel));
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
    // An ExampleCommand will run in autonomous
    return null; // Change this when autonomous command configured
  }
}
