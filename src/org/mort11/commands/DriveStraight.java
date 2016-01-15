package org.mort11.commands;

import org.mort11.Robot;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {

    private double driveDistance;
    private DTSide left = Robot.leftSide;
    private DTSide right = Robot.rightSide;
    private Timer timer;

    public DriveStraight() {
        requires(left);
        requires(right);
        timer = new Timer();
    }

    protected void initialize() {
        timer.start();
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
