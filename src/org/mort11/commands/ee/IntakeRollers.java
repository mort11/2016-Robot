package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.behavior.Commands;
import org.mort11.constants.EEConstants;
import org.mort11.subsystems.ee.Rollers;

/**
 * IntakeRollers - Spins the intake rollers at a
 *
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Jakob Shortell <jshortell@mort11.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author chsahit
 */
public class IntakeRollers extends Command {
    Rollers roller = Robot.adaptor.rollers;
    Commands.RollerRequest rollerRequest;
    double time = -1;
    Timer timer = new Timer();

    /**
     * Run intake rollers indefinitely in specified direction
     *
     * @param rollerRequest Roller mode [Intake, Exhaust, None]
     */
    public IntakeRollers(Commands.RollerRequest rollerRequest) {
        this.rollerRequest = rollerRequest;
        requires(roller);
        setInterruptible(true);
    }

    /**
     * Run intake rollers for specified time in specified direction
     *
     * @param time             Time to run
     * @param requestDirection Roller mode [Intake, Exhaust, None]
     */
    public IntakeRollers(double time, Commands.RollerRequest requestDirection) {
        this(requestDirection);
        this.time = time;
        timer = new Timer();
    }

    @Override
    protected void initialize() {
        timer.start();
    }

    @Override
    protected void execute() {
        switch (rollerRequest) {
            case INTAKE:
                roller.set(EEConstants.ROLLER_SPEED);
                break;
            case EXHAUST:
                roller.set(-EEConstants.ROLLER_SPEED);
                break;
            case STOP:
                roller.set(0);
        }
    }

    @Override
    protected boolean isFinished() {
        return timer.get() > time;
    }

    @Override
    protected void end() {
        if (time != -1) {
            roller.set(0);
        }
    }

    protected void interrupted() {
    }

}
