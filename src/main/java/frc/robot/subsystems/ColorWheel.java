package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorWheelConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/**
 * An example subsystem. You can replace with me with your own subsystem.
 */
public class ColorWheel extends SubsystemBase {
  // Color motor and encoder
  VictorSP colorMotor = new VictorSP(1);
  public Encoder colorEncoder = new Encoder(1, 2);

  // Color sensor and matcher
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  public ColorWheel() {
    m_colorMatcher.addColorMatch(ColorWheelConstants.kBlueTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kGreenTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kRedTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kYellowTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kWhiteTarget);

    colorEncoder.setDistancePerPulse(ColorWheelConstants.distancePerpulse);
    colorEncoder.setReverseDirection(false);
    resetEncoder();
  }

  public String getColor() {
    final Color detectedColor = m_colorSensor.getColor();
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    String colorString;
    if (match.color == ColorWheelConstants.kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == ColorWheelConstants.kRedTarget) {
      colorString = "Red";
    } else if (match.color == ColorWheelConstants.kGreenTarget) {
      colorString = "Green";
    } else if (match.color == ColorWheelConstants.kYellowTarget) {
      colorString = "Yellow";
    } else if (match.color == ColorWheelConstants.kWhiteTarget) {
      colorString = "White";
    } else if (match.color == ColorWheelConstants.kBlackTarget) {
      colorString = "Black";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Detected Color", getColor());
    SmartDashboard.putNumber("Encoder Distance", getDistance());
    SmartDashboard.putNumber("Encoder Raw Value", colorEncoder.getRaw());
  }

  public void resetEncoder() {
    colorEncoder.reset();
  }

  public void stopMotor() {
    colorMotor.set(0);
  }

  public void setMotor(double speed) {
    colorMotor.set(speed);
  }

  public double getDistance() {
    return colorEncoder.getDistance();
  }

  public double getRequiredDistance(String inputColor, String m_prevColor) {
    double distanceNeeded;
    if (m_prevColor.equals("Red")) {
      if (inputColor.equals("Green")) {
        distanceNeeded = ColorWheelConstants.encoderOneEighth;
      } else if (inputColor.equals("Blue")) {
        distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
      } else { // If inputColor is Yellow
        distanceNeeded = -ColorWheelConstants.encoderOneEighth;
      }
    }

    else if (m_prevColor.equals("Yellow")) {
      if (inputColor.equals("Red")) {
        distanceNeeded = ColorWheelConstants.encoderOneEighth;
      } else if (inputColor.equals("Green")) {
        distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
      } else { // If inputColor is Blue
        distanceNeeded = -ColorWheelConstants.encoderOneEighth;
      }
    }

    else if (m_prevColor.equals("Blue")) {
      if (inputColor.equals("Yellow")) {
        distanceNeeded = ColorWheelConstants.encoderOneEighth;
      } else if (inputColor.equals("Red")) {
        distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
      } else { // If inputColor is Green
        distanceNeeded = -ColorWheelConstants.encoderOneEighth;
      }
    }

    else if (m_prevColor.equals("Green")) {
      if (inputColor.equals("Blue")) {
        distanceNeeded = ColorWheelConstants.encoderOneEighth;
      } else if (inputColor.equals("Yellow")) {
        distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
      } else { // If inputColor is Red
        distanceNeeded = -ColorWheelConstants.encoderOneEighth;
      }
    }

    else {
      System.out.println("Incorrect color detected");
      distanceNeeded = 0;
    }
    SmartDashboard.putNumber("distance needed", distanceNeeded);
    return distanceNeeded;
  }
}
