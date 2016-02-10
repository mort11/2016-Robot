package org.mort11.subsystems.dt;

import org.mort11.Robot;
import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.constants.DrivetrainConstants;
import org.mort11.constants.PDPMap;
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
        super(DrivetrainConstants.DT_LEFT_TALON_ID, false, SensorDealer.getInstance().getLeftDTEncoder());
        SensorDealer.getInstance().getLeftDTEncoder().setDistancePerPulse(DrivetrainConstants.INCHES_PER_PULSE_LEFT);
    }

    /**
     * Get total current in use by all left three DT CIMs
     *
     * @return Total current for all left three DT CIMs
     */
    @Override
    public double getCurrent() {
        return Robot.adaptor.pdp.getCurrent(PDPMap.LEFT_DT_1) + Robot.adaptor.pdp.getCurrent(PDPMap.LEFT_DT_2) +
                Robot.adaptor.pdp.getCurrent(PDPMap.LEFT_DT_3);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearLeft());
    }
}

