package org.mort11.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;


public class Pneumatics {
	DoubleSolenoid sol;
	 boolean engaged; 

	    public Pneumatics(int engagedPort, int notEngagedPort) {
	        sol = new DoubleSolenoid(engagedPort, notEngagedPort);
	        engaged = false;
	    }


	    public void initDefaultCommand() {
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
	