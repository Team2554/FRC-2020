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
    private String m_inputColor;
    private String m_prevColor;
    private double m_runningTime = 0.0;
    private double m_distanceNeeded;

    /**
     * Creates a new RotateToColor.
     */
    public RotateToColor(ColorWheel colorWheel, String inputColor) {
        m_colorWheel = colorWheel;
        m_inputColor = inputColor;
        addRequirements(m_colorWheel);
        m_prevColor = m_colorWheel.getColor();
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_colorWheel.resetEncoder();
        m_prevColor = m_colorWheel.getColor();
        m_distanceNeeded = m_colorWheel.getRequiredDistance("Red", m_prevColor);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_distanceNeeded < 0) {
            m_colorWheel.setMotor(-0.07);
        } else {
            m_colorWheel.setMotor(0.07);
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
        return (Math.abs(m_colorWheel.getDistance()) >= Math.abs(m_distanceNeeded));
    }
}
