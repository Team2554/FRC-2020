/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Intake.IntakeIn;
import frc.robot.commands.Intake.IntakeOut;
import frc.robot.commands.BottomConveyor.BottomConveyorIn;
import frc.robot.commands.BottomConveyor.BottomConveyorOut;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IntakeAndBottom extends ParallelCommandGroup {
  /**
   * Creates a new IntakeAndBottom.
   */
  public IntakeAndBottom(Intake intake, BottomConveyor bottomConveyor, boolean isReverse) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(isReverse ? new IntakeOut(intake) : new IntakeIn(intake),
        isReverse ? new BottomConveyorOut(bottomConveyor) : new BottomConveyorIn(bottomConveyor));
  }
}
