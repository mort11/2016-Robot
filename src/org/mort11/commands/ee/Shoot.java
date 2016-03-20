package org.mort11.commands.ee;

import org.mort11.commands.SubsystemStates.RollerRequest;
import org.mort11.commands.auton.AdjustToGoal;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Shoot extends CommandGroup {
    
    public  Shoot() {
        addSequential(new HoodToggle());
      //  addSequential(new AdjustToGoal());
      //  addSequential(new AdjustToGoal());
        addSequential(new IntakeRollers(1.5, RollerRequest.INTAKE));
        addSequential(new HoodToggle());
    }
}
