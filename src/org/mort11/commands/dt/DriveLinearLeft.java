package org.mort11.commands.dt;

import org.mort11.Robot;
import org.mort11.commands.FullSpeed;

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
        if (!FullSpeed.isEnabled_fullSpeed) {
            return oi.getLeftJoy_limit();
        } else {
            return oi.getLeftJoy_full();
        }
    }
}
