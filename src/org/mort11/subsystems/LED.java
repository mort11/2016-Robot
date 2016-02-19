package org.mort11.subsystems;

import org.mort11.constants.Constants;

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
        red = new PWM(Constants.RED_LIGHT);
        green = new PWM(Constants.GREEN_LIGHT);
        blue = new PWM(Constants.BLUE_LIGHT);
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

