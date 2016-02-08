package org.mort11.commands;

import org.mort11.OI;
import org.mort11.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FullSpeed extends Command {

    Timer timer;
    public static boolean isEnabled_fullSpeed = false;
    
    public FullSpeed() {
        timer = new Timer();
        OI.count_fullSpeed++;
    }

    protected void initialize() {
        timer.start();
    }

    protected void execute() {
        if (OI.count_fullSpeed > 1) {
            end();
        } else {
            FullSpeed.isEnabled_fullSpeed = true;
        }
       //wait
    }

    protected boolean isFinished() {
        return timer.get() < 10 && timer.get() > 0;
    }

    protected void end() {
        FullSpeed.isEnabled_fullSpeed = false;
        timer.stop();
        timer.reset();
    }


    protected void interrupted() {
    }
}
