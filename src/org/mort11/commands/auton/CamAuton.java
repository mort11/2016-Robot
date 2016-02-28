package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CamAuton extends CommandGroup {
    
    public  CamAuton() {
        addSequential(new AdjustToGoal());
        addSequential(new DriveForwardToGoal());
        addSequential(new AdjustToGoal());
    }
}
