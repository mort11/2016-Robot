package org.mort11.subsystems.dt;

import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.constants.Constants;
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
        super(Constants.DT_LEFT_TALON_ID_1, Constants.DT_LEFT_TALON_ID_2,
                Constants.DT_LEFT_TALON_ID_3, Constants.LEFT_DT_1, Constants.LEFT_DT_2, Constants.LEFT_DT_3,
                "DT_LEFT_1", "DT_LEFT_2", "DT_LEFT_3", true, true, true, SensorDealer.getInstance().getLeftDTEncoder());
        SensorDealer.getInstance().getLeftDTEncoder().setDistancePerPulse(Constants.INCHES_PER_PULSE_LEFT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearLeft());
    }
}

