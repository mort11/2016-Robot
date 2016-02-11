package org.mort11.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * DisplayCurrents - Main Robot class
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DisplayCurrents extends Command {
    private PowerDistributionPanel pdp = new PowerDistributionPanel();
    protected void initialize() {
    }

    protected void execute() {
        double cur1 = pdp.getCurrent(1);
        double cur2 = pdp.getCurrent(2);

        System.out.println("Channel 1: " + cur1);
        System.out.println("Channel 2: " + cur2);
        SmartDashboard.putNumber("PDP Channel 1", cur1);
        SmartDashboard.putNumber("PDP Channel 2", cur2);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
