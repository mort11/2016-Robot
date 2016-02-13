package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.subsystems.ee.Rollers;

/**
 * IntakeRollers - Spins the intake rollers at a
 *
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 */
public class IntakeRollers extends Command {

    boolean in, out;
    Rollers rollers = Robot.adaptor.rollers;

    public IntakeRollers(boolean in, boolean out) {
        this.in = in;
        this.out = out;
        requires(rollers);
    }

    protected void initialize() {
    }

    protected void execute() {
        if (in == out) {
            rollers.set(0);
        } else if (in) {
            rollers.set(EndEffectorConstants.ROLLER_SPEED);
        } else {
            rollers.set(-1 * EndEffectorConstants.ROLLER_SPEED);
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
