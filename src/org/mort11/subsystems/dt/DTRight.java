package org.mort11.subsystems.dt;

import org.mort11.commands.dt.DriveLinearRight;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.sensors.SensorDealer;

/**
 * DTRight - Subsystem controlling right drivetrain side
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 */
public class DTRight extends DTSide {
    public DTRight() {
        super(DrivetrainConstants.DT_RIGHT_TALON_ID_1, DrivetrainConstants.DT_RIGHT_TALON_ID_2,
                DrivetrainConstants.DT_RIGHT_TALON_ID_3, PDPConstants.RIGHT_DT_1, PDPConstants.RIGHT_DT_2, PDPConstants.RIGHT_DT_3,
                "DT_RIGHT_1", "DT_RIGHT_2", "DT_RIGHT_3", true, true, true, SensorDealer.getInstance().getRightDTEncoder());
        SensorDealer.getInstance().getRightDTEncoder().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_RIGHT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearRight());
    }
}