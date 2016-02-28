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
                led.set(-1, 0, 0);
                break;
            case ORANGE:
                led.set(-1, 1, 0);
                break;
            case YELLOW:
                led.set(-1, -1, 0);
                break;
            case GREEN:
                led.set(0, -1, 0);
                break;
            case BLUE:
                led.set(0, 0, -1);
                break;
            case PURPLE:
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
