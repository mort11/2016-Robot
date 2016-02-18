package org.mort11.sensors;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;

/**
 * SensorDealer - Declare all sensors on robot to consolidate them to one place
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 */
public class SensorDealer {

    private SensorDealer() {
        leftDriveTrain = new Encoder(SensorConstants.DT_ENCODER_LEFT_A, SensorConstants.DT_ENCODER_LEFT_B, true, CounterBase.EncodingType.k4X);
        leftDriveTrain.setDistancePerPulse(140 / 25918);
        rightDriveTrain = new Encoder(SensorConstants.DT_ENCODER_RIGHT_A, SensorConstants.DT_ENCODER_RIGHT_B, false, CounterBase.EncodingType.k4X);
        rightDriveTrain.setDistancePerPulse(140 / 25918);
        intakeArm = new Encoder(SensorConstants.INTAKE_ARM_ENCODER_A, SensorConstants.INTAKE_ARM_ENCODER_B, false, CounterBase.EncodingType.k4X);
        shooter = new Encoder(SensorConstants.SHOOTER_ENCODER_A, SensorConstants.SHOOTER_ENCODER_B, false, CounterBase.EncodingType.k4X);
        armPot = new AnalogPotentiometer(SensorConstants.ARM_POT);
        armLimitSwitch = new DigitalInput(SensorConstants.ARM_LIM_SWITCH_PORT);
        ahrs = new AHRS(SPI.Port.kMXP);
    }
}