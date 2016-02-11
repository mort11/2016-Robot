package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.mort11.Robot;
import org.mort11.subsystems.LED;

/**
 * LEDControl - Controls the LED's
 * 
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public class LEDControl extends Command {

	public enum Light{
		RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
	}
	
	Light light;
	LED led = Robot.adaptor.led;
	
    public LEDControl(Light light) {
    	this.light = light;
    }

    protected void initialize() {
    }

    protected void execute() {
    	switch (light){
    	case RED:
    		led.set(255, 0, 0);
    		break;
    	case ORANGE:
    		
    		break;
    		
    	
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
