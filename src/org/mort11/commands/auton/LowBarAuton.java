package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {
        System.out.println("Starting LowBar auton");
        addSequential(new DriveStraight(96));
        addSequential(new TurnDegrees(false, 90));
        addSequential(new AdjustToGoal());
    }
}
