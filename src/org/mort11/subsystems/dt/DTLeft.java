package org.mort11.subsystems.dt;

import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.sensors.SensorDealer;

/**
 * DTLeft - Subsystem controlling left drivetrain side
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 */
public class DTLeft extends DTSide {
    public DTLeft() {
        super(DrivetrainConstants.DT_LEFT_TALON_ID_1, DrivetrainConstants.DT_LEFT_TALON_ID_2,
                DrivetrainConstants.DT_LEFT_TALON_ID_3,DrivetrainConstants.DT_LEFT_LOW_SHIFTER_PORT,
                DrivetrainConstants.DT_LEFT_HIGH_SHIFTER_PORT, true, true, true, SensorDealer.getInstance().getLeftDTEncoder());
        SensorDealer.getInstance().getLeftDTEncoder().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_LEFT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearLeft());
    }
}

