package org.mort11.commands.dt;

import static org.mort11.Robot.rightSide;
import static org.mort11.Robot.oi;

public class DriveLinearRight extends DriveLinear {

    public DriveLinearRight() {
        super(rightSide);
    }

    protected double getSpeed() {
        return oi.getRightJoy();
    }

}
