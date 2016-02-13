package org.mort11.commands.dt;

import org.mort11.Robot;

import static org.mort11.Robot.oi;

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

    protected double getSpeed() {
        return oi.getRightJoy();
    }
}
