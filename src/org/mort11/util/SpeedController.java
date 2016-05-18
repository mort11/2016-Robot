package org.mort11.util;

/**
 * SpeedController - Functions to control speed manage
 *
 * @author Matt Turi
 * @author Matthew Krzyzanowski
 */
public class SpeedController {
    /**
     * Applies a deadband to the input to prevent jitter
     *
     * @param input Joystick or other control system input
     * @return Threshed value
     */
    public static double threshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        } else {
            return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
        }
    }
}
