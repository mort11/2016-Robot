package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarLowGoal - Oh baby a triple
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class LowBarLowGoal extends CommandGroup {

    public LowBarLowGoal() {
        addSequential(new DriveStraight(120));
    }
}
