package org.mort11.commands.dt;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.subsystems.dt.DTSide;

/**
 * DriveLinear - Base command for controlling left and right drivetrain movement
 *
 * @author gridbug
 * @author Matthew Krzyzanowski
 */
public abstract class DriveLinear extends Command {
    protected DTSide side;

    public DriveLinear(DTSide drivetrain) {
        requires(drivetrain);
        this.side = drivetrain;
        setInterruptible(true);
    }

    protected void initialize() {
    }

    protected abstract double getSpeed();

    protected void execute() {
        side.set(getSpeed());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
