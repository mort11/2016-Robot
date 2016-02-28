package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * WaitTime - Waits for a specified amount of time
 *
 * @author Matt Turi
 */
public class WaitTime extends Command {
    Timer timer;
    double timeToWait;

    public WaitTime(double timeToWait) {
        timer = new Timer();
        this.timeToWait = timeToWait;
        setInterruptible(true);
    }


    protected void initialize() {
        timer.start();
    }


    protected void execute() {
        // Wait
    }

    protected boolean isFinished() {
//        System.out.println("WaitTime finished!");
        return (timer.get() > timeToWait);
    }

    protected void end() {
    }

    protected void interrupted() {
//        System.out.println("Interrupted WaitTime command!");
    }
}
