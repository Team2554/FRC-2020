/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class ColorWheelConstants {
        public static final Color kBlueTarget = ColorMatch.makeColor(0.135, 0.433, 0.4257); // (R, G, B)
        public static final Color kGreenTarget = ColorMatch.makeColor(0.176, 0.565, 0.258);
        public static final Color kRedTarget = ColorMatch.makeColor(0.49, 0.311, 0.145);
        public static final Color kYellowTarget = ColorMatch.makeColor(0.33, 0.55, 0.13);
        public static final Color kWhiteTarget = ColorMatch.makeColor(0.267, 0.475, 0.25);
        public static final Color kBlackTarget = ColorMatch.makeColor(0.0, 0.0, 0.0);
        public static final double circumOfColorWheel = 100 / 12; // circumfrence of color wheel (feet)
        public static final double circumOfMotorWheel = Math.PI * 4 / 12; // circumference of motor (feet)
        public static final double pulsesPerRev = 300;
        public static final double distancePerpulse = circumOfMotorWheel / pulsesPerRev;
        public static final double encoderStopValue = circumOfColorWheel * 4;
        public static final double encoderOneEighth = circumOfColorWheel / 8;

    }
}
