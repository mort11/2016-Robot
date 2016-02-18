package org.mort11.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * LED - Subsystem for LED's
 *
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public class LED extends Subsystem {
    private PWM red, green, blue;

    public LED() {
        red = new PWM(HardwareConstants.RED_LIGHT);
        green = new PWM(HardwareConstants.GREEN_LIGHT);
        blue = new PWM(HardwareConstants.BLUE_LIGHT);
    }

    @Override
    public void initDefaultCommand() {
    }

    public void set(int r, int g, int b) {
        red.setRaw(r);
        green.setRaw(g);
        blue.setRaw(b);
    }
}

