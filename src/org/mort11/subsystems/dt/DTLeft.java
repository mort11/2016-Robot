package org.mort11.subsystems.dt;

import org.mort11.constants.DrivetrainConstants;
import org.mort11.sensors.SensorDealer;

/**
 * DTLeft - Subsystem controlling left drivetrain side
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DTLeft extends DTSide {
    public DTLeft() {
        super(DrivetrainConstants.DT_LEFT_TALON_ID, DrivetrainConstants.DT_LEFT_LOW_SHIFTER_PORT, DrivetrainConstants.DT_LEFT_HIGH_SHIFTER_PORT, false);
//        SensorDealer.getInstance().getLeftDriveTrain().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_LEFT);
    }

    public void initDefaultCommand() {
    }
}

