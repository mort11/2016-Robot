package org.mort11.commands.dt;

import org.mort11.Robot;

/**
 * Controls left drivetrain movement
 *
 * @author Will Marshall
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
