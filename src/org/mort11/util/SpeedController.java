package org.mort11.util;

import edu.wpi.first.wpilibj.Timer;
import org.mort11.Robot;

/**
 * SpeedController - Functions to control speed manage
 *
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class SpeedController {
    public static boolean fullSpeed;
    private static int fsUsageCount;
    private static Timer timer = new Timer();

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
        return input / Math.abs(input) * (Math.abs(input) - 0.05) / (1 - 0.05);
    }

    /**
     * Limits top speed of robot to avoid brownouts
     *
     * @param input Current speed received from Joystick or other control module
     * @return Speed limited value
     */
    public static double speedLimit(double input) {
        if (Robot.oi.fullSpeed.get()) {
            fullSpeed = true;
            fsUsageCount++;
        }
        if (fullSpeed) {
            timer.start();
            fullSpeed = false;
        }
        if (timer.get() < 10 && timer.get() > 0 && fsUsageCount <= 20) {
            return input;
        }
        if (timer.get() >= 10) {
            timer.stop();
            timer.reset();
            System.out.println("Full Speed Duration: " + timer.get());
        }
        if (!fullSpeed) {
            if (input >= .75) {
                input = .75;
            } else if (input <= -.75) {
                input = -.75;
            }
        }
        return input;
    }
}
