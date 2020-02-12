//
/*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved. */
// /* Open Source Software - may be modified and shared by FRC teams. The code
// */
// /* must be accompanied by the FIRST BSD license file in the root directory of
// */
// /* the project. */
//
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ColorWheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ColorWheelConstants;
import frc.robot.subsystems.ColorWheel;

public class RotateToColor extends CommandBase {
    private final ColorWheel m_colorWheel;
    private boolean m_isFinished = false;
    private String m_inputColor;
    private String m_prevColor;
    private double m_runningTime = 0.0;
    private double distanceNeeded;

    /**
     * Creates a new RotateToColor.
     */
    public RotateToColor(ColorWheel colorWheel, String inputColor) {
        m_colorWheel = colorWheel;
        m_inputColor = inputColor;
        m_prevColor = m_colorWheel.getColor();
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_colorWheel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_colorWheel.resetEncoder();
        m_prevColor = m_colorWheel.getColor();
        distanceNeeded = m_colorWheel.getRequiredDistance(m_inputColor, m_prevColor);

        // if (m_prevColor.equals("Red")) {
        // if (m_inputColor.equals("Green")) {
        // distanceNeeded = ColorWheelConstants.encoderOneEighth;
        // } else if (m_inputColor.equals("Blue")) {
        // distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        // } else { // If inputColor is Yellow
        // distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        // }
        // }

        // else if (m_prevColor.equals("Yellow")) {
        // if (m_inputColor.equals("Red")) {
        // distanceNeeded = ColorWheelConstants.encoderOneEighth;
        // } else if (m_inputColor.equals("Green")) {
        // distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        // } else { // If inputColor is Blue
        // distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        // }
        // }

        // else if (m_prevColor.equals("Blue")) {
        // if (m_inputColor.equals("Yellow")) {
        // distanceNeeded = ColorWheelConstants.encoderOneEighth;
        // } else if (m_inputColor.equals("Red")) {
        // distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        // } else { // If inputColor is Green
        // distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        // }
        // }

        // else if (m_prevColor.equals("Green")) {
        // if (m_inputColor.equals("Blue")) {
        // distanceNeeded = ColorWheelConstants.encoderOneEighth;
        // } else if (m_inputColor.equals("Yellow")) {
        // distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        // } else { // If inputColor is Red
        // distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        // }
        // }

        // else {
        // System.out.println("Incorrect color detected");
        // return;
        // }

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (distanceNeeded < 0) {
            m_colorWheel.setMotor(-0.3);
        } else {
            m_colorWheel.setMotor(0.3);
        }
        double startTime = System.currentTimeMillis();
        m_runningTime += System.currentTimeMillis() - startTime;

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_colorWheel.stopMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (m_colorWheel.getColor() == m_inputColor && m_colorWheel.getDistance() >= distanceNeeded);
    }
}
