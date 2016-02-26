package org.mort11.commands.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.subsystems.LED;

/**
 * LEDControl - Controls the LED's
 *
 * @author Jakob Shortell
 * @author Seven Kurt
 * @author Matt Turi
 */
public class LEDControl extends Command {
    private SubsystemStates.Light light;
    private LED led = Robot.adaptor.led;
    private double duration = 0;
    private Timer durationTimer = new Timer();

    /**
     * Set LED color for given duration
     *
     * @param light    LED color
     * @param duration Duration to stay lit. Pass in 0 to run infinitely
     */
    public LEDControl(SubsystemStates.Light light, double duration) {
        assert duration >= 0; // Ensure that duration is greater than or equal to 0 seconds

        requires(led);

        this.light = light;
        this.duration = duration;

        if (duration > 0) {
            this.durationTimer.start();
        }
    }

    @Override
    protected void initialize() {
    }

    @Override
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

    @Override
    protected boolean isFinished() {
        if (this.duration == 0) {
            return false;
        } else if (this.durationTimer.get() < this.duration) {
            return false;
        }
        this.durationTimer.stop();
        this.durationTimer.reset();
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
