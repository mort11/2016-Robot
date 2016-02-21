package org.mort11.commands.dt;

import org.mort11.Robot;

/**
 * DriveLinearLeft - Controls left drivetrain movement
 *
 * @author gridbug
 * @author Matthew Krzyzanowski
 */
public class DriveLinearLeft extends DriveLinear {

    public DriveLinearLeft() {
        super(Robot.adaptor.leftSide);
    }

    @Override
    protected double getSpeed() {
        return Robot.oi.getLeftJoy();
    }
}
