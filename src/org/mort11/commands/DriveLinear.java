package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.DTSide;

public abstract class DriveLinear extends Command {
    protected DTSide side;

    public DriveLinear(DTSide drivetrain) {
        requires(drivetrain);
        setInterruptible(true);
        side = drivetrain;
    }

    protected void initialize() {
    }

    protected abstract double getSpeed();
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	side.getSpeed();
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
