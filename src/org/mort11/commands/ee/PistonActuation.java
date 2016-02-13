package org.mort11.commands.ee;


import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.Brake;

/**
 * PistonActuation - Actuates a piston
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Michael Kozak <michael.kozak@motsd.org>
 */
public class PistonActuation extends Command {

    boolean isPressed;
    private Pneumatics piston = Robot.adaptor.piston;

    public PistonActuation() {
        requires(piston);
        setInterruptible(true);
    }

    protected void initialize() {
    }

    /**
     * Toggle the piston based on joystick button status
     */
    protected void execute() {
        System.out.println("Execute");
        if (Robot.oi.piston.get()) {
            System.out.println("piston is pressed");
            piston.setSolenoid(true);
        } else {
            piston.setSolenoid(false);
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
