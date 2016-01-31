package org.mort11.commands.dt;

import org.mort11.subsystems.dt.DTSide;
import edu.wpi.first.wpilibj.command.Command;

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

    protected void execute() {
        side.setSpeed(getSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
