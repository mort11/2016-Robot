package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.sensors.SensorDealer;

/**
 * PrintAngle - Prints an angle
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
