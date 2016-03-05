package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.commands.ee.DropIntake;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.Shoot;
import org.mort11.commands.ee.SpinUp;

/**
 * LowBarAuton - Does the Low bar auton
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class LowBarAuton extends CommandGroup {
    public LowBarAuton() {
        addParallel(new DropIntake());
        addSequential(new WaitTime(0.7));
        addSequential(new DriveStraight(103, 30));
        addParallel(new SpinUp());
        addSequential(new DriveStraight(63.1));
        addParallel(new MotorToAngle(90));
        addSequential(new TurnDegrees(false, 55));
        addSequential(new DriveStraight(39, 32));
        addSequential(new Shoot());

//        addParallel(new WaitTime(3));
//        addSequential(new HoodToggle());
//        addSequential(new HoodToggle());
//        addParallel(new SpinUp(98000,true));
//        addSequential(new WaitTime(5));
//        addSequential(new IntakeRollers(1, RollerRequest.INTAKE));
    }
}
