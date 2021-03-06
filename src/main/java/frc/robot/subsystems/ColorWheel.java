package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorWheelConstants;

public class ColorWheel extends SubsystemBase {
  // Color chooser
  private final SendableChooser<String> m_colorChooser = new SendableChooser<>();

  // Color motor and encoder
  private final WPI_VictorSPX m_colorMotor = new WPI_VictorSPX(ColorWheelConstants.colorMotorPort);
  private final Encoder m_colorEncoder = new Encoder(ColorWheelConstants.encoderPorts[0],
      ColorWheelConstants.encoderPorts[1]);

  // Color sensor and matcher
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
  private final ColorSensorV3 m_whiteLineSensor = new ColorSensorV3(I2C.Port.kMXP);
  private final ColorMatch m_colorMatcher = new ColorMatch();
  private final ColorMatch m_whiteLineMatcher = new ColorMatch();

  public ColorWheel() {
    // Setup color chooser
    m_colorChooser.addOption("Red", "Red");
    m_colorChooser.addOption("Green", "Green");
    m_colorChooser.addOption("Yellow", "Yellow");
    m_colorChooser.addOption("Blue", "Blue");

    SmartDashboard.putData("Color Chooser", m_colorChooser);

    m_colorMatcher.addColorMatch(ColorWheelConstants.kBlueTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kGreenTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kRedTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kYellowTarget);
    m_colorMatcher.addColorMatch(ColorWheelConstants.kWhiteTarget);

    m_whiteLineMatcher.addColorMatch(ColorWheelConstants.kBlackTargetForWhiteLine);
    m_whiteLineMatcher.addColorMatch(ColorWheelConstants.kWhiteTargetForWhiteLine);

    m_colorEncoder.setDistancePerPulse(ColorWheelConstants.distancePerPulse);
    m_colorEncoder.setReverseDirection(false);
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

  public boolean isWhiteLine() {
    final Color detectedColor = m_whiteLineSensor.getColor();
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    return match.color == ColorWheelConstants.kWhiteTargetForWhiteLine;
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Detected Color", getColor());
    SmartDashboard.putNumber("Encoder Distance", getDistance());
    SmartDashboard.putNumber("Encoder Raw Value", m_colorEncoder.getRaw());
  }

  public void resetEncoder() {
    m_colorEncoder.reset();
  }

  public void stopMotor() {
    m_colorMotor.set(0);
  }

  public void setMotor(final double speed) {
    m_colorMotor.set(speed);
  }

  public double getDistance() {
    return m_colorEncoder.getDistance();
  }

  public double getRequiredDistance(final String inputColor, final String currentColor) {
    double distanceNeeded;
    switch (currentColor) {
      case "Red":
        if (inputColor.equals("Green")) {
          distanceNeeded = ColorWheelConstants.encoderOneEighth;
        } else if (inputColor.equals("Blue")) {
          distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        } else { // If inputColor is Yellow
          distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        }
        break;

      case "Yellow":
        if (inputColor.equals("Red")) {
          distanceNeeded = ColorWheelConstants.encoderOneEighth;
        } else if (inputColor.equals("Green")) {
          distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        } else { // If inputColor is Blue
          distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        }
        break;

      case "Blue":
        if (inputColor.equals("Yellow")) {
          distanceNeeded = ColorWheelConstants.encoderOneEighth;
        } else if (inputColor.equals("Red")) {
          distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        } else { // If inputColor is Green
          distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        }
        break;

      case "Green":
        if (inputColor.equals("Blue")) {
          distanceNeeded = ColorWheelConstants.encoderOneEighth;
        } else if (inputColor.equals("Yellow")) {
          distanceNeeded = 2 * ColorWheelConstants.encoderOneEighth;
        } else { // If inputColor is Red
          distanceNeeded = -ColorWheelConstants.encoderOneEighth;
        }
        break;

      default:
        System.out.println("Incorrect color detected");
        distanceNeeded = 0;
        break;
    }

    SmartDashboard.putNumber("distance needed", distanceNeeded);
    return distanceNeeded;
  }

  public String getSelectedColor() {
    return m_colorChooser.getSelected();
  }
}
