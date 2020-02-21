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

public class SRXMagEncoder_Relative {
    private TalonSRX talon;
    private boolean reverseDirection = false;
    private double distancePerRotation = 1;
    private double ticksPerRotation = 4096;

    /**
     * 
     * @param talonSRX         TalonSRX or WPI_TalonSRX object
     * @param reverseDirection optional reverseDirection parameter
     */
    public SRXMagEncoder_Relative(TalonSRX talonSRX, boolean reverseDirection) {
        talon = talonSRX;
        this.reverseDirection = reverseDirection;
    }

    /**
     * 
     * @param talonSRX         TalonSRX or WPI_TalonSRX object
     * @param reverseDirection optional reverseDirection parameter
     */
    public SRXMagEncoder_Relative(WPI_TalonSRX talonSRX, boolean reverseDirection) {
        talon = (TalonSRX) talonSRX;
        this.reverseDirection = reverseDirection;
    }

    /**
     * 
     * @param talonSRX         TalonSRX or WPI_TalonSRX object
     * @param reverseDirection optional reverseDirection parameter
     */
    public SRXMagEncoder_Relative(TalonSRX talonSRX) {
        talon = talonSRX;
    }

    /**
     * 
     * @param talonSRX         TalonSRX or WPI_TalonSRX object
     * @param reverseDirection optional reverseDirection parameter
     */
    public SRXMagEncoder_Relative(WPI_TalonSRX talonSRX) {
        talon = (TalonSRX) talonSRX;
    }

    /**
     * used to configure the Talon SRX to use an encoder. Must be called in
     * subsystem constructor.
     */
    public void configure() {
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    /**
     * resets the encoder
     */
    public void reset() {
        talon.setSelectedSensorPosition(0);
    }

    /**
     * set the amount of distance the output shaft covers per rotation
     * 
     * @param distancePerRotation the amount of distance the output shaft covers per
     *                            rotation
     */
    public void setRawDistancePerRotation(double distancePerRotation) {
        this.distancePerRotation = distancePerRotation;
    }

    /**
     * Use to make working with setRawDistancePerRotation easier
     * 
     * @param diameter the diameter of the wheel
     */
    public void setWheelDiameter(double diameter) {
        distancePerRotation = diameter * Math.PI;
    }

    /**
     * Use to make working with setRawDistancePerRotation easier
     * 
     * @param radius the radius of the wheel
     */
    public void setWheelRadius(double radius) {
        distancePerRotation = 2 * radius * Math.PI;
    }

    /**
     * if the encoder is connected to a geared down shaft, then enter the gear ratio
     * to change pulses per rotation For example: if encoder is mounted on motor
     * shaft, but the ouptut shaft is geared 100:1, then input 100 for the gear
     * ratio This way, there will be 409600 pulses per rotation, so the final shaft
     * velocity and position is measured instaed of the motor shaft
     * 
     * @param ratio eg, for 100:2, ratio would be 50
     */
    public void setGearRatio(double ratio) {
        ticksPerRotation = 4096 * ratio;
    }

    /**
     * @return raw encoder output multiplied by 1 or -1 depending on if the
     *         direction needs to be reversed(see constructor). Not adjusted for
     *         gear ratio. Units are encoder ticks(4096 per encoder shaft rotation)
     */
    public int getRawPosition() {
        return talon.getSelectedSensorPosition() * (reverseDirection ? -1 : 1);
    }

    /**
     * @return getRawPosition with units as Encoder Ticks per 100 milliseconds and
     *         not adjusted for gear ratio.
     */
    public int getRawVelocity() {
        return talon.getSelectedSensorVelocity() * (reverseDirection ? -1 : 1);
    }

    /**
     * @return getRawPosition but adjusted for gear ratio and distance per rotation.
     *         Output units is [unit of distance per rotation].
     */
    public double getPosition() {
        return ((double) getRawPosition() / ticksPerRotation) * distancePerRotation;
    }

    /**
     * @return getRawVelocity adjusted for gear ratio and distance per rotation.
     *         Output units are [unit of distance per rotation] per second.
     */
    public double getVelocity() {
        return (((double) getRawVelocity() / ticksPerRotation) * distancePerRotation) / 0.1;
    }

    /**
     * @return the distance per rotation
     */
    public double getDistancePerRotation() {
        return distancePerRotation;
    }

    /**
     * @return the ticks per per rotation
     */
    public double getTicksPerRotation() {
        return ticksPerRotation;
    }

    /**
     * @return get whether moving in reverse or not
     */
    public boolean getIsReverse() {
        return reverseDirection;
    }
}
