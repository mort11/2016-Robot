package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
import org.mort11.behavior.Commands;
import org.mort11.behavior.Commands.RollerRequest;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.subsystems.ee.Rollers;

/**
 * IntakeRollers - Spins the intake rollers at a
 *
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Jakob Shortell <jshortell@mort11.org>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 */
public class IntakeRollers extends Command {
    Rollers roller = Robot.adaptor.rollers;
    Commands.RollerRequest rollerRequest;
    double time = -1;
    Timer timer;
    public IntakeRollers(Commands.RollerRequest rollerRequest) {
        this.rollerRequest = rollerRequest;
        requires(roller);
        setInterruptible(true);
    }
    
    public IntakeRollers(double time) {
    	this(RollerRequest.EXHAUST);
    	this.time = time;
    	timer = new Timer();
    }

    protected void initialize() {
    	timer.start();
    }

    protected void execute() {
        switch (rollerRequest) {
            case INTAKE:
                roller.set(EndEffectorConstants.ROLLER_SPEED);
                break;
            case EXHAUST:
                roller.set(-EndEffectorConstants.ROLLER_SPEED);
                break;
            case STOP:
                roller.set(0);
        }
    }

    protected boolean isFinished() {
        return timer.get() > time;
    }

    protected void end() {
    	if(time != -1) {
    		roller.set(0);
    	}
    }

    protected void interrupted() {
    }

}
