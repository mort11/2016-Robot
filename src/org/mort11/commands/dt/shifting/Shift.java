package org.mort11.commands.dt.shifting;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.dt.DTSide;

/**
 * Shift - Shift transmission
 *
 * @author Matt Turi
 */
public class Shift extends Command {
    private SubsystemStates.Gear gear;

    public Shift(SubsystemStates.Gear gear) {
        this.gear = gear;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
//    	System.out.println("shifting gears");
        DTSide.shift(gear);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {

    }
}
