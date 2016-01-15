package org.mort11.commands;

import static org.mort11.Robot.rightSide;
import static org.mort11.Robot.oi;

public class DriveLinearRight extends DriveLinear {

    public DriveLinearRight() {
        super(rightSide);
    }

    protected double getSpeed() {
        return oi.getRightJoy();
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
