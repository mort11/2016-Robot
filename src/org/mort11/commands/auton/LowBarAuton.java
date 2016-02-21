package org.mort11.commands.auton;

import org.mort11.commands.SubsystemStates.RollerRequest;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.SpinUp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {
    	addParallel(new MotorToAngle(80));
        addSequential(new WaitTime(3.5));
        addSequential(new DriveStraight(256));
        addSequential(new TurnDegrees(false, 55));
        addParallel(new SpinUp(10000, true));
        addSequential(new WaitTime(4));
        addSequential(new IntakeRollers(1, RollerRequest.INTAKE));
    }
}
