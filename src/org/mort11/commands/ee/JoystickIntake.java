package org.mort11.commands.ee;

import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickIntake extends Command {

    public JoystickIntake() {
    	requires(Robot.adaptor.intake);
    }
    protected void initialize() {
    }
    
    protected void execute() {
    	Robot.adaptor.intake.set(Robot.oi.getEEJoy());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
