package org.mort11.subsystems.dt;

import org.mort11.constants.DrivetrainConstants;
import org.mort11.sensors.SensorDealer;

public class DTRight extends DTSide {

    public DTRight() {
        super(DrivetrainConstants.DT_RIGHT_TALON_PORT, DrivetrainConstants.DT_RIGHT_LOW_SHIFTER_PORT, DrivetrainConstants.DT_RIGHT_HIGH_SHIFTER_PORT, false);
        SensorDealer.getInstance().getRightDriveTrain().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_RIGHT);
    }

    public void initDefaultCommand() {
    }
}