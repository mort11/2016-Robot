package org.mort11.commands.led;

import org.mort11.HardwareAdaptor;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RainbowCommand extends Command {

	HardwareAdaptor adaptor = new HardwareAdaptor();
    public RainbowCommand() {
    	requires(adaptor.led);
    }

    protected void initialize() {
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
