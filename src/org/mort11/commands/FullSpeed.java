package org.mort11.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.OI;

/**
 * FullSpeed - Allows the robot to drive at fullSpeed for a specified amount of time in teleop
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class FullSpeed extends Command {
    public static boolean fullSpeedEnabled = false;
    private Timer timer;
    private double duration;

    /**
     * Ensure that full speed (robot at max power) is only used once per match
     *
     * @param time Length of time full speed will run for
     */
    public FullSpeed(double time) {
        this.duration = time;
        this.timer = new Timer();
        OI.count_fullSpeed++;
    }

    /**
     * Start timer
     */
    protected void initialize() {
        this.timer.start();
    }

    /**
     * Keep full speed enabled while fullSpeedCounter is less than 1
     */
    protected void execute() {
        if (OI.count_fullSpeed > 1) {
            end();
        } else {
            fullSpeedEnabled = true;
        }
    }

    /**
     * @return Finished if time is greater than allotted duration
     */
    protected boolean isFinished() {
        return (timer.get() > this.duration);
    }

    protected void end() {
        fullSpeedEnabled = false;
        timer.stop();
        timer.reset();
    }

    protected void interrupted() {
    }
}
