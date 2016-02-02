package org.mort11.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import org.mort11.constants.SensorConstants;

/**
 * SensorDealer - Declare all sensors on robot to consolidate them to one place
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 */
public class SensorDealer {
    private static SensorDealer instance = new SensorDealer();

    private final Encoder leftDriveTrain;
    private final Encoder rightDriveTrain;
    private final Encoder arm;
    private final Encoder roller;
    private final AnalogPotentiometer armPot;
    private final DigitalInput armLimitSwitch;

    private SensorDealer() {
        leftDriveTrain = new Encoder(SensorConstants.DT_ENCODER_LEFT_A, SensorConstants.DT_ENCODER_LEFT_B, false, CounterBase.EncodingType.k4X);
        rightDriveTrain = new Encoder(SensorConstants.DT_ENCODER_RIGHT_A, SensorConstants.DT_ENCODER_RIGHT_B, false, CounterBase.EncodingType.k4X);
        arm = new Encoder(SensorConstants.ARM_ENCODER_A, SensorConstants.ARM_ENCODER_B, false, CounterBase.EncodingType.k4X);
        roller = new Encoder(SensorConstants.ROLLER_ENCODER_A, SensorConstants.ROLLER_ENCODER_B, false, CounterBase.EncodingType.k4X);
        armPot = new AnalogPotentiometer(SensorConstants.ARM_POT);
        armLimitSwitch = new DigitalInput(SensorConstants.ARM_LIM_SWITCH_PORT);
    }

    public static SensorDealer getInstance() {
        return instance;
    }

    public Encoder getLeftDriveTrain() {
        return this.leftDriveTrain;
    }

    public Encoder getRightDriveTrain() {
        return this.rightDriveTrain;
    }

    public Encoder getArm() {
        return this.arm;
    }

    public Encoder getRoller() {
        return this.roller;
    }

    public AnalogPotentiometer getArmPot() {
        return this.armPot;
    }

    public DigitalInput getArmLimitSwitch() {
        return this.armLimitSwitch;
    }
}
