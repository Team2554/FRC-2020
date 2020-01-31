/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Custom;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/*
Jank docs because I don't know how to write javadocs:

Constructor: TalonSRX or WPI_TalonSRX object with optional reverseDirection parameter

reset: resets the encoder

setGearRatio: if the encoder is connected to a geared down shaft, then enter the gear ratio to change pulses per rotation
For example: if encoder is mounted on motor shaft, but the ouptut shaft is geared 100:1, then input 100 for the gear ratio
This way, there will be 409600 pulses per rotation, so the final shaft velocity and position is measured instaed of the motor shaft

setRawDistancePerRotation: the amount of distance the output shaft covers per rotation

setWheelDiameter/setWheelRadius: used instead of setRawDistancePerRotation. Just a function to make things easier

getRawPosition: raw encoder output multiplied by 1 or -1 depending on if the direction needs to be reversed(see constructor).
Not adjusted for gear ratio.
Units are encoder ticks(4096 per encoder shaft rotation)

getRawVelocity: Basically like getRawPosition. Units are Encoder Ticks per 100 milliseconds. Not adjusted for gear ratio

getPosition: getRawPosition but adjusted for gear ratio and distance per rotation. Output units is [unit of distance per rotation].

getVelocity: getRawVelocity adjusted for gear ratio and distance per rotation. Output units are [unit of distance per rotation] per second.

getDistancePerRotation: self explanatory

getTicksPerRotation: I don't know what this would be useful for, but its there

getIsReverse: self explanatory
*/
public class SRXMagEncoder_Relative {
    private TalonSRX talon;
    private boolean reverseDirection = false;
    private double distancePerRotation = 1;
    private double ticksPerRotation = 4096;

    public SRXMagEncoder_Relative(TalonSRX talonSRX, boolean reverseDirection) {
        talon = talonSRX;
        this.reverseDirection = reverseDirection;
        commonConstruct();
    }

    public SRXMagEncoder_Relative(WPI_TalonSRX talonSRX, boolean reverseDirection) {
        talon = (TalonSRX) talonSRX;
        this.reverseDirection = reverseDirection;
        commonConstruct();
    }

    public SRXMagEncoder_Relative(TalonSRX talonSRX) {
        talon = talonSRX;
        commonConstruct();
    }

    public SRXMagEncoder_Relative(WPI_TalonSRX talonSRX) {
        talon = (TalonSRX) talonSRX;
        commonConstruct();
    }

    private void commonConstruct() {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        reset();
    }

    public void reset() {
        talon.setSelectedSensorPosition(0);
    }

    public void setRawDistancePerRotation(double distancePerRotation) {
        this.distancePerRotation = distancePerRotation;
    }

    public void setWheelDiameter(double diameter) {
        distancePerRotation = diameter * Math.PI;
    }

    public void setWheelRadius(double radius) {
        distancePerRotation = 2 * radius * Math.PI;
    }

    public void setGearRatio(double ratio) {// eg, for 100:2, ratio would be 50
        ticksPerRotation = 4096 * ratio;
    }

    public int getRawPosition() {
        return talon.getSelectedSensorPosition() * (reverseDirection ? -1 : 1);
    }

    public int getRawVelocity() {
        return talon.getSelectedSensorVelocity() * (reverseDirection ? -1 : 1);
    }

    public double getPosition() {
        return ((double) getRawPosition() / ticksPerRotation) * distancePerRotation;
    }

    public double getVelocity() {
        return (((double) getRawVelocity() / ticksPerRotation) * distancePerRotation) / 0.1;
    }

    public double getDistancePerRotation() {
        return distancePerRotation;
    }

    public double getTicksPerRotation() {
        return ticksPerRotation;
    }

    public boolean getIsReverse() {
        return reverseDirection;
    }
}
