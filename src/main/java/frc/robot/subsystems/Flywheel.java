/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {
  TalonSRX talon = new TalonSRX(Constants.FlywheelConstants.TALON_PORT);

  /**
   * Creates a new Flywheel.
   */
  public Flywheel() {
    talon.configFactoryDefault();

    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); // TODO: Move these numbers to
                                                                                       // Constants.java

    talon.setSensorPhase(true);

    // talon.configNominalOutputForward(0, )
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
