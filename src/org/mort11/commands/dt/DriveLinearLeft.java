package org.mort11.commands.dt;

import static org.mort11.Robot.oi;
import static org.mort11.Robot.leftSide;

public class DriveLinearLeft extends DriveLinear {

    public DriveLinearLeft() {
        super(leftSide);
    }

    protected double getSpeed() {
        return oi.getLeftJoy();
    }

}
