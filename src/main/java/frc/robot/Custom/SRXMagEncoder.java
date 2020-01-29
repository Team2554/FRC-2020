/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Custom;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class SRXMagEncoder {
    private TalonSRX talon;
    private boolean reverseDirection = false;

    public SRXMagEncoder(TalonSRX talonSRX, boolean reverseDirection) {
        talon = talonSRX;
        this.reverseDirection = reverseDirection;
        commonConstruct();
    }

    public SRXMagEncoder(WPI_TalonSRX talonSRX, boolean reverseDirection) {
        talon = (TalonSRX) talonSRX;
        this.reverseDirection = reverseDirection;
        commonConstruct();
    }

    public SRXMagEncoder(TalonSRX talonSRX) {
        talon = talonSRX;
        commonConstruct();
    }

    public SRXMagEncoder(WPI_TalonSRX talonSRX) {
        talon = (TalonSRX) talonSRX;
        commonConstruct();
    }

    public void commonConstruct() {

    }
}
