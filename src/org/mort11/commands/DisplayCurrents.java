package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.HardwareAdaptor;

/**
 * DisplayCurrents - Main Robot class
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DisplayCurrents extends Command {
    // current values for each monitored HardwareAdaptor.pdp channel (0 - 15) in Amps
    // can comment out any channels that are not in use
    private double cur1;
    private double cur2;

    public DisplayCurrents() {

    }

    protected void initialize() {
    }

    protected void execute() {
        cur1 = HardwareAdaptor.pdp.getCurrent(1);
        cur2 = HardwareAdaptor.pdp.getCurrent(2);

        System.out.println("current 1: " + cur1);
        System.out.println("current 2: " + cur2);
        SmartDashboard.putNumber("HardwareAdaptor.pdp Current Channel 1", cur1);
        SmartDashboard.putNumber("HardwareAdaptor.pdp Current Channel 2", cur2);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
