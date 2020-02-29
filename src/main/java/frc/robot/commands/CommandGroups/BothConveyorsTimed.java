/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.BottomConveyor.TimedBottomConveyorIn;
import frc.robot.commands.TopConveyor.TimedTopConveyorIn;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.TopConveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class BothConveyorsTimed extends ParallelCommandGroup {
  /**
   * Creates a new BothConveyorsTimed.
 * @param topConveyor 
 * @param bottomConveyor 
   */
  public BothConveyorsTimed(TopConveyor topConveyor, BottomConveyor bottomConveyor) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(new TimedTopConveyorIn(topConveyor), new TimedBottomConveyorIn(bottomConveyor))
  }
}
