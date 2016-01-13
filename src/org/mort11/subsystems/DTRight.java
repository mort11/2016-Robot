package org.mort11.subsystems;


import org.mort11.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DTRight extends DTSide {
    
	   public DTRight(){
		   super(RobotMap.DT_RIGHT_PORT, RobotMap.DT_ENC_RIGHT_A, RobotMap.DT_ENC_RIGHT_B, false, false); //placeholder values
	   }

	    public void initDefaultCommand() {
	        // Set the default command for a subsystem here.
	        //setDefaultCommand(new MySpecialCommand());
	    }
	}