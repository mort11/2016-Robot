package org.mort11.commands;

import static org.mort11.Robot.oi;

import edu.wpi.first.wpilibj.command.Command;

import static org.mort11.Robot.leftSide;

public class DriveLinearLeft extends DriveLinear {

    public DriveLinearLeft() {
        super(leftSide);
    }

    protected double getSpeed() {
        return oi.getLeftJoy();
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
