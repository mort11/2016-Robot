package org.mort11.commands.dt;

import org.mort11.HardwareAdaptor;

import static org.mort11.Robot.oi;

public class DriveLinearRight extends DriveLinear {

    public DriveLinearRight() {
        super(HardwareAdaptor.rightSide);
    }

    protected double getSpeed() {
        return oi.getRightJoy();
    }
}
