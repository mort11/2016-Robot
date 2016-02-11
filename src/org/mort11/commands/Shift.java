package org.mort11.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

/**
 * Shift - ToDo class description
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class Shift extends Command {
    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        System.out.println(String.format("Shifted to [%s]", DTSide.currentGear));
        Robot.adaptor.shifter.set(DoubleSolenoid.Value.kForward);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Robot.adaptor.shifter.set(DoubleSolenoid.Value.kReverse);
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
