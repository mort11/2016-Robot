package org.mort11.commands.dt;

import org.mort11.Robot;

import static org.mort11.Robot.oi;

/**
 * DriveLinearLeft - Controls left drivetrain movement
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DriveLinearLeft extends DriveLinear {

    public DriveLinearLeft() {
        super(Robot.adaptor.leftSide);
    }

    protected double getSpeed() {
        return oi.getLeftJoy();
    }
}
