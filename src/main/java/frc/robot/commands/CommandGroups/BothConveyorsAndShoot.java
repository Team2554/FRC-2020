/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.BottomConveyor.BottomConveyorIn;
import frc.robot.commands.Shooter.ShootCommand;
import frc.robot.commands.TopConveyor.TopConveyorIn;
import frc.robot.subsystems.BottomConveyor;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TopConveyor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class BothConveyorsAndShoot extends ParallelCommandGroup {
  /**
   * Creates a new ShooterAndConveyors.
   *
   * @param shooter
   * @param bottomConveyor
   * @param topConveyor
   * @param intake
   */
  public BothConveyorsAndShoot(final Shooter shooter, final BottomConveyor bottomConveyor,
      final TopConveyor topConveyor) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();

    super(new ShootCommand(shooter), new BottomConveyorIn(bottomConveyor, shooter::isShootable),
        new TopConveyorIn(topConveyor, shooter::isShootable));
  }
}
