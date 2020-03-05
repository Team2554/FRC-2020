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

// Sagar lo ve me
public final class Constants {
    public static final class DriveJoystickMappings {
        // TODO: define actual button ids later
        // public static final int intakeIn = 0; // hold to intake ball
        // public static final int runFlywheel = 1; // hold to spin flywheel
        // public static final int angleToVision = 2; // hold to keep align to vision
        // target
        // public static final int reverseDrivetrain = 3; // toggle reverse the drive
        // direction
        public static final int quickTurn = 4;
        public static final int driveStraight = 1; // press to drive straight
        public static final int rotateToAngle = 2; // press to rotate
        // above is for curvature drive. see:
        // https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html#curvatureDrive(double,double,boolean)
    }

    public static final class ButtonJoystickMappings {
        // TODO: define actual button ids later
        public static final int intakeIn = 0; // hold to intake ball
        public static final int intakeOut = 1; // hold to release ball in intake
        public static final int runShooter = 3; // hold to run the shooter
        public static final int conveyorIn = 4; // hold to make conveyor go in
        public static final int conveyorOut = 5; // hold to make conveyor go out
        public static final int elevatorUp = 6; // hold to make elevator go up
        public static final int elevatorDown = 7; // hold to make elevator go down
        public static final int setElevatorTop = 8; // press to set elevator to top using limit switch
        public static final int setElevatorButtom = 9; // press to set elevator to bottom using limit switch
        public static final int colorWheelTurnToColor = 10; // press to spin the color wheel to a certain color
        public static final int colorWheelSpinNumberOfTimes = 11; // press to spin the color wheel 4 times
        public static final int whiteLineStop = 12; // Level adjuster left
        public static final int levelAdjusterRight = 13; // Level adjuster right
        public static final int intakeAndShoot = 14;
        public static final int timedConveyors = 15;
        public static final int bottomConveyorIn = 16;
        public static int topConveyorOut = 17;
    }

    public static final class ColorWheelConstants {
        // Motors/Encoder ports
        public static final int colorMotorPort = 0;
        public static final int encoderPorts[] = { 1, 2 };

        // Motor speeds for different commands
        public static final double rotateWheelSpeed = 0.05;
        public static final double rotateToColorSpeed = 0.05;

        // Colors that can be detected
        public static final Color kBlueTarget = ColorMatch.makeColor(0.135, 0.433, 0.4257); // (R, G, B)
        public static final Color kGreenTarget = ColorMatch.makeColor(0.176, 0.565, 0.258);
        public static final Color kRedTarget = ColorMatch.makeColor(0.49, 0.311, 0.145);
        public static final Color kYellowTarget = ColorMatch.makeColor(0.33, 0.55, 0.13);
        public static final Color kWhiteTarget = ColorMatch.makeColor(0.267, 0.475, 0.25);
        public static final Color kBlackTarget = ColorMatch.makeColor(0.0, 0.0, 0.0);

        public static final Color kWhiteTargetForWhiteLine = ColorMatch.makeColor(0.267, 0.475, 0.25);
        public static final Color kBlackTargetForWhiteLine = ColorMatch.makeColor(0.0, 0.0, 0.0);

        // Encoder related calculations
        public static final double circumOfColorWheel = 100.0 / 12; // circumference of color wheel (feet)
        public static final double circumOfMotorWheel = (Math.PI * 4) / 12.0; // circumference of motor (feet)
        public static final double pulsesPerRev = 31.0;
        public static final double distancePerPulse = circumOfMotorWheel / pulsesPerRev;
        public static final double encoderStopValue = circumOfColorWheel * 1.0;
        public static final double encoderOneEighth = circumOfColorWheel / 8.0;
    }

    public static final class ShooterConstants {
        public static final int talonPort = 0; // CanID for talon
    }

    public static final class ConveyorConstants {
        public static final int topConveyorVoltage = 3;
        public static final int leftConveyor1Port = 0;
        public static final int leftConveyor2Port = 1;
        public static final int rightConveyor1Port = 2;
        public static final int rightConveyor2Port = 3;

        // BottomConveyor Constants
        public static final double stopTime = 0.5;
        public static final int bottomConveyorPort = 4;
        public static final int bottomConveyorVoltage = 5;
    }

    public static final class IntakeConstants {
        public static final int intakePort = 0;
    }

    public static final class ElevatorConstants {
        public static final int bottomSwitch = 0;
        public static final int upSpeed = 1;
        public static final int downSpeed = -1;
        public static final int topSwitch = 1;
        public static final int motorPort = 2;
        // public static final int levelAdjusterMotorOnePort = 0;
        // public static final int levelAdjusterMotorTwoPort = 1;
    }

    public static final class TurnToAnglePIDConstants {
        public static final double kP = 0.01;
        public static final double kI = 0.01;
        public static final double kD = 0.00085;
        public static final double kTolerance = 0.5;
    }
}
