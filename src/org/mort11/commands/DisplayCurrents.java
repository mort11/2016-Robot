package org.mort11.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;

/**
 * DisplayCurrents - Displays currents from specified PDP channels
 *
 * @author Sahit Chintalapudi
 * @author Matthew Krzyzanowski
 */
public class DisplayCurrents extends Command {
    private PowerDistributionPanel pdp = Robot.adaptor.pdp;

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double cur1 = pdp.getCurrent(1);
        double cur2 = pdp.getCurrent(2);

        System.out.println("Channel 1: " + cur1);
        System.out.println("Channel 2: " + cur2);
        SmartDashboard.putNumber("PDP Channel 1", cur1);
        SmartDashboard.putNumber("PDP Channel 2", cur2);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
