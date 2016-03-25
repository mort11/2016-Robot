package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnToGoal extends CommandGroup {
    
    public  TurnToGoal() {
       addSequential(new AdjustToGoal(true));
       //addSequential(new TurnDegrees());
    }
}
