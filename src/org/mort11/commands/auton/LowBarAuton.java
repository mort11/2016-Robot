package org.mort11.commands.auton;

import org.mort11.HardwareAdaptor;
import org.mort11.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class LowBarAuton extends CommandGroup {
    
    public  LowBarAuton() {
        System.out.println("Starting LowBar auton");
    	addSequential(new DriveStraight(96));
    	addSequential(new TurnDegrees(false,90));
    	addSequential(new AdjustToGoal());
    	
    }
}
