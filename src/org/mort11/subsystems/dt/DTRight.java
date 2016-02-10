package org.mort11.subsystems.dt;

import org.mort11.Robot;
import org.mort11.commands.dt.DriveLinearRight;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.constants.PDPMap;
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
                DrivetrainConstants.DT_RIGHT_TALON_ID_3, true, true, true, SensorDealer.getInstance().getRightDTEncoder());
        SensorDealer.getInstance().getRightDTEncoder().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_RIGHT);
    }

    /**
     * Get total current in use by all right three DT CIMs
     *
     * @return Total current for all right three DT CIMs
     */
    @Override
    public double getCurrent() {
        return Robot.adaptor.pdp.getCurrent(PDPMap.RIGHT_DT_1) + Robot.adaptor.pdp.getCurrent(PDPMap.RIGHT_DT_2) +
                Robot.adaptor.pdp.getCurrent(PDPMap.RIGHT_DT_3);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearRight());
    }
}