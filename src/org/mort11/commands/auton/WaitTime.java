package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * WaitTime - Waits for a specified amount of time
 *
 * @author Matt Turi
 */
public class WaitTime extends Command {
    private Timer timer;
    private double timeToWait;

    public WaitTime(double timeToWait) {
        timer = new Timer();
        this.timeToWait = timeToWait;
        setInterruptible(true);
    }

    @Override
    protected void initialize() {
        timer.start();
    }


    @Override
    protected void execute() {
        // Wait
    }

    @Override
    protected boolean isFinished() {
        return (timer.get() > timeToWait);
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
