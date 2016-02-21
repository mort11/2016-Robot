package org.mort11.commands.dt;

import org.mort11.Robot;

/**
 * DriveLinearRight - Controls right drivetrain movement
 *
 * @author gridbug
 * @author Matthew Krzyzanowski
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
