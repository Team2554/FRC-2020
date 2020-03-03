/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
  /**
   * Creates a new Vision.
   */

  private final ShuffleboardTab sbVision = Shuffleboard.getTab("Vision");
  private final NetworkTable ntVision = NetworkTableInstance.getDefault().getTable("Shuffleboard")
      .getSubTable("Vision");

  private final Solenoid visionLight = new Solenoid(0);

  public Vision() {
    // sbVision.add("Hue Start", 80.0).withWidget("Number
    // Slider").withProperties(Map.of("min", 0.0, "max", 180.0));
    // sbVision.add("Hue End", 90.0).withWidget("Number
    // Slider").withProperties(Map.of("min", 0.0, "max", 180.0));
    // sbVision.add("Saturation Start", 100.0).withWidget("Number Slider")
    // .withProperties(Map.of("min", 0.0, "max", 255.0));
    // sbVision.add("Saturation End", 255.0).withWidget("Number
    // Slider").withProperties(Map.of("min", 0.0, "max", 255.0));
    // sbVision.add("Value Start", 0.0).withWidget("Number
    // Slider").withProperties(Map.of("min", 0.0, "max", 255.0));
    // sbVision.add("Value End", 255.0).withWidget("Number
    // Slider").withProperties(Map.of("min", 0.0, "max", 255.0));
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Vision Light", visionLight.get());
  }

  public double getHorizAngle() {
    return ntVision.getEntry("angleToTarget").getDouble(0.0);
  }

  public double getHorizPixels() {
    return ntVision.getEntry("pixelsToTarget").getDouble(0.0);
  }

  public void toggleVisionLight() {
    visionLight.set(!visionLight.get());
  }

  public void visionLightOn() {
    visionLight.set(true);
  }

  public void visionLightOff() {
    visionLight.set(false);
  }
}
