package org.mort11.subsystems;


import org.mort11.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DTLeft extends DTSide {
	 
    
	   public DTLeft(){
		   super (RobotMap.DT_LEFT_PORT, RobotMap.DT_ENC_LEFT_A, RobotMap.DT_ENC_LEFT_B, false, false); //placeholder values 
	   }
	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	        //setDefaultCommand(new MySpecialCommand());
	    }
	}

