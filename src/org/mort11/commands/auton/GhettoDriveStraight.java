package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * GhettoDriveStraight - Drives ghetto-ly straight
 *
 * @author Sahit Chintalapudi
 */
public class GhettoDriveStraight extends Command {
    double time;
    Timer timer;

    public GhettoDriveStraight(double time) {
        requires(Robot.adaptor.leftSide);
        requires(Robot.adaptor.rightSide);
        this.time = time;
        timer = new Timer();
    }

    protected void initialize() {
        timer.start();

    }

    protected void execute() {
        Robot.adaptor.leftSide.set(0.5);
        Robot.adaptor.rightSide.set(0.5);
    }

    protected boolean isFinished() {
        return timer.get() > time;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
