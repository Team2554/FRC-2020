/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class MidShootRight extends SequentialCommandGroup {
  /**
   * Creates a new MidShootLeft.
   */
  public MidShootRight(final DriveTrain dT) {
    super(new DriveStraight(0.9144, 0, dT), new RotateToAngle(90, dT), new DriveStraight(1.2192, 0, dT),
        new RotateToAngle(180, dT));
  }
}
