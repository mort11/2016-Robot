package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

/**
 * JoystickIntake - Does joystick intaking
 *
 * @author chsahit
 */
public class JoystickIntake extends Command {
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;
    private boolean posControl;
    private double desiredLocation;
    private double maxDown = 95;
    public JoystickIntake() {
        this(false);
    }
    
    public JoystickIntake(boolean posControl) {
    	requires(intakeArm);
        this.posControl = posControl;
    }

    @Override
    protected void initialize() {
    	this.setInterruptible(true);
    }

    @Override
    protected void execute() {
    	if(posControl) {    		
    		desiredLocation = (-maxDown/2) * Robot.oi.getEEJoy() + maxDown/2;
//    		System.out.println("desired location: " + desiredLocation);
//    		System.out.println("curr location: " + intakeArm.getAngle());
    		intakeArm.set((desiredLocation - intakeArm.getAngle()) * 0.01);
    		return;
    	}
    	//if its safe to move the switch then sure, if not then don't move it
    		intakeArm.set(Robot.oi.getEEJoy());
    	
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
