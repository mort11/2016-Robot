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
//    private Pneumatics piston = new Pneumatics();

    public PistonActuation() {
//        requires(piston);
        setInterruptible(true);
    }

    protected void initialize() {
    }

    /**
     * Toggle the piston based on joystick button status
     */
    protected void execute() {
        //            piston.set(true);
//            piston.set(false);
        this.isPressed = Robot.oi.piston.get();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
