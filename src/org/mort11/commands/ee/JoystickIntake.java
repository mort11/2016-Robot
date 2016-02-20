package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * JoystickIntake - Does joystick intaking
 *
 * @author chsahit
 *
 */
public class JoystickIntake extends Command {
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;

    public JoystickIntake() {
        requires(intakeArm);
    }

    @Override
    protected void initialize() {
    	this.setInterruptible(true);
    }

    @Override
    protected void execute() {
    	//if its safe to move the switch then sure, if not then don't move it
    	if(intakeArm.isLimitSwitch() && Robot.oi.getEEJoy() > 0) {
    		System.out.println("unsafe to move the arm upwards!!");
    		intakeArm.set(0);
    	} else {
    		intakeArm.set(Robot.oi.getEEJoy());
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
