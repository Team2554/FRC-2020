/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    public static final class FlywheelConstants {
        public static final int maxVoltage = 10;

        public static final int talonPort = 0;
        public static final int kSlotIdx = 0;
        public static final int kPIDLoopIdx = 0;
        public static final int kTimeoutMs = 30;

        // Gains
        public static final double kP = 0.25;
        public static final double kI = 0.01;
        public static final double kD = 20;
        public static final double kF = 1023.0 / 7200.0;
        public static final int kIzone = 300;
        public static final double kPeakOutput = 1.00;
    }

    public static final class DriveJoystickMappings {
        // TODO: define actual button ids later
        public static final int intakeIn = 0; // hold to intake ball
        // public static final int runFlywheel = 1; // hold to spin flywheel <-- What's
        // the purpose of this?
        public static final int angleToVision = 2; // hold to keep align to vision target
        public static final int reverseDrivetrain = 3; // toggle reverse the drive direction
        public static final int quickTurn = 4;
        // above is for curvature drive. see:
        // https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.html#curvatureDrive(double,double,boolean)
    }

    public static final class ButtonJoystickMappings {
        // TODO: define actual button ids later
        public static final int intakeIn = 0; // hold to intake ball
        public static final int intakeOut = 1; // hold to release ball in intake
        public static final int setFlywheelClosedLoop = 2; // toggle if flywheel should be velocity closed loop or not
        public static final int runFlywheel = 3; // hold to spin the flywheel
        public static final int conveyorIn = 4; // hold to make conveyor go in
        public static final int conveyorOut = 5; // hold to make conveyor go out
        public static final int elevatorUp = 6; // hold to make elevator go up
        public static final int elevatorDown = 7; // hold to make elevator go down
        public static final int setElevatorTop = 8; // press to set elevator to top using limit switch
        public static final int setElevatorButtom = 9; // press to set elevator to bottom using limit switch
        public static final int colorWheelColorTurn = 10; // press to spin the color wheel to a certain color
        public static final int colorWheelSpinTurn = 11; // press to spin the color wheel 4 times
    }
}
