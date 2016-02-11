package org.mort11.commands;

import org.mort11.sensors.SensorDealer;

import edu.wpi.first.wpilibj.command.Command;

/**
 * PrintAngle - Prints the current yaw of the NavX
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class PrintAngle extends Command {

    public PrintAngle() {
    }

    protected void initialize() {
    	SensorDealer.getInstance().getAHRS().zeroYaw();
    }

    protected void execute() {
    	System.out.println(SensorDealer.getInstance().getAHRS().getYaw());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
