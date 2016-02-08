package org.mort11.commands;

import org.mort11.OI;
import org.mort11.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * FullSpeed - Allows the robot to drive at fullSpeed for a specified amount of time in teleop
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class FullSpeed extends Command {

    Timer timer;
    private double waitTime; // fullSpeed duration 
    public static boolean isEnabled_fullSpeed = false;
    
    public FullSpeed(double time) {
        this.waitTime = time;
        timer = new Timer();
        OI.count_fullSpeed++; // makes sure fullSpeed can only be used once per match
    }

    protected void initialize() {
        timer.start();
    }

    protected void execute() {
        if (OI.count_fullSpeed > 1) { // makes sure fullSpeed can only be used once per match
            end();
        } else {
            FullSpeed.isEnabled_fullSpeed = true;
        }
       //wait
    }

    protected boolean isFinished() {
        return (timer.get() > waitTime); 
        //return timer.get() < 10 && timer.get() > 0;
    }

    protected void end() {
        FullSpeed.isEnabled_fullSpeed = false;
        timer.stop();
        timer.reset();
    }


    protected void interrupted() {
    }
}
