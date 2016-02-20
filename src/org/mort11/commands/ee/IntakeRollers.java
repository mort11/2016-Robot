package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.constants.Constants;
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
    SubsystemStates.RollerRequest rollerRequest;
    double time = -1;
    Timer timer = new Timer();

    public IntakeRollers(SubsystemStates.RollerRequest rollerRequest) {
        this.rollerRequest = rollerRequest;
        requires(roller);
        setInterruptible(true);
    }

    public IntakeRollers(double time, SubsystemStates.RollerRequest direction) {
        this(direction);
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
            	System.out.println("intaking");
                roller.set(Constants.ROLLER_SPEED);
                break;
            case EXHAUST:
            	System.out.println("exhausting");
                roller.set(-Constants.ROLLER_SPEED);
                break;
            case STOP:
                roller.set(0);
        }
    }

    @Override
    protected boolean isFinished() {
    	System.out.println();
        return timer.get() > time;
    }

    protected void end() {
        if (time != -1) {
            roller.set(0);
        }
    }

    protected void interrupted() {
    }

}
