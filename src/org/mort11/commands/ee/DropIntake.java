package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * DropIntake - Todo file description
 *
 * @author Matt Turi
 */
public class DropIntake extends Command {
    Timer timer;

    public DropIntake() {
        requires(Robot.adaptor.intakeArm);
    }

    @Override
    protected void initialize() {
        timer = new Timer();
        timer.start();
        setInterruptible(true);
    }

    @Override
    protected void execute() {
        if (timer.get() > 1.5 && timer.get() < 2) {
            Robot.adaptor.intakeArm.set(0.3);
        } else {
            Robot.adaptor.intakeArm.set(0);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {

    }
}
