package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CamAuton extends CommandGroup {
    
    public  CamAuton() {
    	System.out.println("cam auton called");
        addSequential(new AdjustToGoal());
//        addSequential(new DriveForwardToGoal());
        //addSequential(new AdjustToGoal());
    }
}
