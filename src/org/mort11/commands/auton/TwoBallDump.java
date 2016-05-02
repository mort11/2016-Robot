package org.mort11.commands.auton;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.SubsystemStates.RollerRequest;
import org.mort11.commands.ee.ActuatePistonIntake;
import org.mort11.commands.ee.IntakeRollers;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TwoBallDump extends CommandGroup {
    
    public  TwoBallDump() {
    	addSequential(new DriveStraight(160,40));
    	addSequential(new WaitTime(1));
    	addSequential(new TurnDegrees(false,50),3);
    	addSequential(new ActuatePistonIntake(SubsystemStates.Intake.DOWN));
    	addSequential(new IntakeRollers(2, RollerRequest.EXHAUST));
        addSequential(new TurnDegrees(true,50),3);
//        addParallel(new IntakeRollers(5,RollerRequest.INTAKE));        
        //addSequential(new DriveStraight(-180,45));
//        addSequential(new DriveStraight(70,45));
//        addSequential(new TurnDegrees(false,90));
//    	addSequential(new IntakeRollers(2, RollerRequest.EXHAUST));
    }
}
