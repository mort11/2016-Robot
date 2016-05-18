package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * Drives semi-straight without assistance from NavX for correction
 *
 * @author Sahit Chintalapudi
 */
public class GhettoDriveStraight extends Command {
    private double time;
    private Timer timer;

    public GhettoDriveStraight(double time) {
        requires(Robot.adaptor.leftSide);
        requires(Robot.adaptor.rightSide);
        this.time = time;
        timer = new Timer();
    }

    @Override
    protected void initialize() {
        timer.start();
    }

    @Override
    protected void execute() {
        Robot.adaptor.leftSide.set(0.5);
        Robot.adaptor.rightSide.set(0.5);
    }

    @Override
    protected boolean isFinished() {
        return timer.get() > time;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
