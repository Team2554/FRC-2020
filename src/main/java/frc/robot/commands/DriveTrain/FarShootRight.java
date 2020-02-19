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
public class FarShootRight extends SequentialCommandGroup {
  /**
   * Creates a new FarShootRight.
   */
  public FarShootRight(DriveTrain dT) {
    super(new DriveStraightNEW(0.9144, 1, 0, dT), new RotateToAngleNEW(90, 1, dT),
        new DriveStraightNEW(5.1816, 1, 0, dT), new RotateToAngleNEW(180, 1, dT));
  }
}