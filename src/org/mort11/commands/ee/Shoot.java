package org.mort11.commands.ee;

import org.mort11.commands.SubsystemStates;
import org.mort11.commands.SubsystemStates.RollerRequest;
import org.mort11.commands.auton.AdjustToGoal;
import org.mort11.commands.auton.WaitTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {
    
    public  Shoot() {
        addSequential(new HoodToggle(SubsystemStates.HoodRequest.POP));
        addSequential(new IntakeRollers(0.3,RollerRequest.EXHAUST));
        addSequential(new WaitForSpinUp(), 2);
        addSequential(new WaitTime(1));
      //  addSequential(new AdjustToGoal());
      //  addSequential(new AdjustToGoal());
        addSequential(new IntakeRollers(1.5, RollerRequest.INTAKE));
        addSequential(new HoodToggle(SubsystemStates.HoodRequest.STOW));
    }
}
