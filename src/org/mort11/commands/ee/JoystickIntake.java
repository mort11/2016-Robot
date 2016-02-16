package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * 
 * @author chsahit
 *
 */
public class JoystickIntake extends Command {

    public JoystickIntake() {
        requires(Robot.adaptor.intakeArm);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.adaptor.intakeArm.set(Robot.oi.getEEJoy());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
