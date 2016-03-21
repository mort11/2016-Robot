package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.subsystems.ee.IntakeArm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * JoystickIntake - Does joystick intaking
 *
 * @author chsahit
 */
public class JoystickIntake extends Command {
    private IntakeArm intakeArm = Robot.adaptor.intakeArm;
    private boolean posControl;
    private double desiredLocation;
    private double maxDown = 180;
    double output,error,neterror;
    Timer timer;
    double thistime = 0, lasttime = 0;
    public JoystickIntake() {
        this(false);
    }
    
    public JoystickIntake(boolean posControl) {
    	requires(intakeArm);
        this.posControl = posControl;
        timer = new Timer();
    }

    @Override
    protected void initialize() {
    	this.setInterruptible(true);
    	timer.start();
    }

    @Override
    protected void execute() {
    	if(posControl) {    		
    		desiredLocation = (-maxDown/2) * Robot.oi.getEEJoy() + maxDown/2;
    		desiredLocation -=5;
    		if(desiredLocation > 110) {
    			desiredLocation = 110;
    		} else if (desiredLocation < 0) {
    			desiredLocation = 0;
    		}
    		//System.out.println("desired location: " + desiredLocation);
    		//System.out.println("curr location: " + intakeArm.getAngle());
    		intakeArm.getAngle();
//    		System.out.println("getting curr");
    		error = desiredLocation - intakeArm.getAngle();
    		output = error * 0.010;    		    		
    		thistime = timer.get();
    		neterror += (thistime - lasttime) * error;
    		if(neterror > 30) {
    			neterror = 30;
    		} else if (neterror < -30) {
    			neterror = -30;
    		}
    		System.out.println("I gain: " + (neterror * 0.01));
    		output += (neterror * 0.005);
    		if(output > 0.2) {
    			output = 0.2;
    		} else if (output < -1) {
    			output = -1;
    		}
    		System.out.println("output: " + output);
    		intakeArm.set(output);
    		lasttime = thistime;
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
