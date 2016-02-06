package org.mort11.commands.ee;


import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * PistonActuation - Actuates a piston
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Michael Kozak <michael.kozak@motsd.org>
 */
public class PistonActuation extends Command {

    boolean isPressed;

    public PistonActuation() {
        requires(Robot.piston);
        setInterruptible(true);
    }

    protected void initialize() {
    }

    protected void execute() {
        System.out.println("Execute");
        if (Robot.oi.piston.get()) {
            System.out.println("piston is pressed");
            Robot.piston.setSolenoid(true);
        } else {
//        	Robot.piston.setSolenoid(true);
//        	Timer.delay(1);
            Robot.piston.setSolenoid(false);
        }
    }

    protected boolean isFinished() {
        System.out.println("isfinished");
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
