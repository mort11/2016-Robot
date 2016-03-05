package org.mort11.commands.led;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.LED;

/**
 * LEDControl - Controls the LED's
 *
 * @author Jakob Shortell
 * @author Seven Kurt
 */
public class LEDControl extends Command {

    SubsystemStates.Light light;
    LED led = Robot.adaptor.led;
    

    public LEDControl(SubsystemStates.Light light) {
        this.light = light;
        requires(led);
    }
    
    public void setLEDColor(SubsystemStates.Light light) {
    	this.light = light;
    }

    protected void initialize() {
    }

    protected void execute() {
        switch (light) {
            case RED:
            	System.out.println("RED");
                led.set(-1, 0, 0);
                break;
            case ORANGE:
            	System.out.println("ORANGE");
                led.set(-1, 1, 0);
                break;
            case YELLOW:
            	System.out.println("YELLOW");
                led.set(-1, -1, 0);
                break;
            case GREEN:
            	System.out.println("GREEN");
                led.set(0, -1, 0);
                break;
            case BLUE:
            	System.out.println("BLUE");
                led.set(0, 0, -1);
                break;
            case PURPLE:
            	System.out.println("PURPLE");
                led.set(1, 0, -1);
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
