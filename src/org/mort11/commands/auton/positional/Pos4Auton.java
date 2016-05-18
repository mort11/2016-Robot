package org.mort11.commands.auton.positional;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.TurnDegrees;
import org.mort11.commands.ee.IntakeArmToAngle;

/**
 * @author Sahit Chintalapudi
 */
public class Pos4Auton extends CommandGroup {
    public Pos4Auton() {
        addSequential(new DriveStraight(30, 45));
        addParallel(new IntakeArmToAngle(50));
        addSequential(new WaitCommand(0.5));
        addSequential(new DriveStraight(100, 30));
        addSequential(new TurnDegrees(30), 3);
        addSequential(new DriveStraight(40, 34));
        addSequential(new TurnDegrees(-30), 3);
        addSequential(new DriveStraight(100, 50));
    }
}
