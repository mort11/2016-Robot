package org.mort11.commands.dt;

import org.mort11.Robot;

/**
 * DriveLinearRight - Controls right drivetrain movement
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DriveLinearRight extends DriveLinear {

    public DriveLinearRight() {
        super(Robot.adaptor.rightSide);
    }

    @Override
    protected double getSpeed() {
        return Robot.oi.getRightJoy();
    }
}
