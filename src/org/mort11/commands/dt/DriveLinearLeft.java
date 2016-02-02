package org.mort11.commands.dt;

import org.mort11.HardwareAdaptor;

import static org.mort11.Robot.oi;

public class DriveLinearLeft extends DriveLinear {

    public DriveLinearLeft() {
        super(HardwareAdaptor.leftSide);
    }

    protected double getSpeed() {
        return oi.getLeftJoy();
    }
}
