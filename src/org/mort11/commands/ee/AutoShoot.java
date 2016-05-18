package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.SubsystemStates.RollerState;

/**
 * Automatically operates the correct subsystems to make the robot shoot
 *
 * @author Matt Turi
 * @author Sahit Chintalapudi
 */
public class AutoShoot extends CommandGroup {
    public AutoShoot() {
        addSequential(new HoodToggle(SubsystemStates.HoodState.POP));
        addSequential(new WaitCommand(1));
        addSequential(new IntakeRollers(1.5, RollerState.INTAKE));
        addSequential(new WaitCommand(1));
        addSequential(new HoodToggle(SubsystemStates.HoodState.STOW));
    }
}
