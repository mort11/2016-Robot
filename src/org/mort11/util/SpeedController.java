package org.mort11.util;

import edu.wpi.first.wpilibj.Timer;
import org.mort11.Robot;
import org.mort11.constants.DrivetrainConstants;

/**
 * SpeedController - Functions to control speed manage
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class SpeedController {
    public static boolean fullSpeed = false;
    public static int fsUsageCount = 0;

    /**
     * Applies a deadband to the input to prevent jitter
     *
     * @param input Joystick or other control system input
     * @return Threshed value
     */
    public static double threshold(double input) {
        if (Math.abs(input) <= 0.05) {
            return 0;
        }
        if (fullSpeed) {
            return threshold(input);
        }
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }

    /**
     * Limits top speed of robot to avoid brownouts
     *
     * @param input Current speed received from Joystick or other control module
     * @return Speed limited value
     */
    public static double speedLimit(double input) {
        if (input >= DrivetrainConstants.SPEED_LIMIT) {
            input = DrivetrainConstants.SPEED_LIMIT;
        } else if (input <= -DrivetrainConstants.SPEED_LIMIT) {
            input = -DrivetrainConstants.SPEED_LIMIT;
        }
        return threshold(input);
    }
}
