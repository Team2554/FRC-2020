/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.EntryNotification;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
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

  private final NetworkTableEntry angleEntry = ntVision.getEntry("angleToTarget");

  private final Solenoid visionLight = new Solenoid(0);

  private double lastEntryChange;
  private double lastEntry;

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

    angleEntry.addListener(this::onAngleEntryChange, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Vision Light", visionLight.get());
  }

  public void onAngleEntryChange(EntryNotification event) {
    lastEntryChange = Timer.getFPGATimestamp();
    lastEntry = angleEntry.getDouble(0.0);
  }

  public double getHorizAngle() {
    return lastEntry;
  }

  public double[] getHorizAngleAndTimestamp() {
    double[] toReturn = { lastEntryChange, lastEntry };
    return toReturn;
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
