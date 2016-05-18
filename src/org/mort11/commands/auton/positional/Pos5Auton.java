package org.mort11.commands.auton.positional;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.ee.IntakeArmToAngle;

/**
 * @author Sahit Chintalapudi
 */
public class Pos5Auton extends CommandGroup {
    public Pos5Auton() {
        addParallel(new IntakeArmToAngle(90));
        addSequential(new WaitCommand(3.5));
        addSequential(new DriveStraight(60));
    }
}
