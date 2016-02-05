package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.util.MORTSubsystem;

/**
 *
 */
public class Hood extends Subsystem implements MORTSubsystem{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid sol;
	boolean engaged;
	boolean isDisabled = false;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }	
    public void popHood() {
    	if(isDisabled == false){
    		sol.set(DoubleSolenoid.Value.kForward);
    		engaged = true;
    	}
    }
    public void stowHood() {
    	if(isDisabled == false){
    		sol.set(DoubleSolenoid.Value.kReverse);
    		engaged = false;
    	}
    }
    public void toggleHood() {
    	if(isDisabled == false){
    		setSolenoid(!engaged);
    	}
    }
    
    public void setSolenoid(boolean engage) {
        if(isDisabled == false){
        	if (engage)
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
    
    public void disable(){
    	isDisabled = true;
    }
}

