package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hood extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid sol;
	boolean engaged;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	
    public void popHood() {
    	sol.set(DoubleSolenoid.Value.kForward);
    	engaged = true;
    }
    public void stowHood() {
    	sol.set(DoubleSolenoid.Value.kReverse);
    	engaged = false;
    }
    public void toggleHood() {
    	setSolenoid(!engaged);
    }
    
    public void setSolenoid(boolean engage) {
        if (engage) {
            sol.set(DoubleSolenoid.Value.kForward); 
        } else {
            sol.set(DoubleSolenoid.Value.kReverse); 
        }
        engaged = engage;
        System.out.println("state 2: " + engage);
    }

    public boolean isEngaged() {
        return engaged;
    }
}

