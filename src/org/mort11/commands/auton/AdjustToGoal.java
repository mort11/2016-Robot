package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;

/**
 * AdjustToGoal - Adjusts the robots orientation to line up with the goal
 *
 * @author Carl Hausman <carl@hausman.org>
 */
public class AdjustToGoal extends Command {
    
    boolean finished = false;

    public AdjustToGoal() {
        requires(Robot.adaptor.leftSide);
        requires(Robot.adaptor.rightSide);
        requires(Robot.adaptor.cam);
    }

    protected void initialize() {
        //SensorDealer.getInstance().getAHRS().zeroYaw();
    }

    protected void execute() {
        Robot.adaptor.cam.setPicture();
        System.out.println("looping");
        double x_val = Robot.adaptor.cam.getX();
        if (x_val == -1) {
            System.out.println("not found");
            finished = true;
        }
        System.out.println("Centering");
        if (x_val < 135) {
            Robot.adaptor.leftSide.set(-0.15);
            Robot.adaptor.rightSide.set(0.15);
            System.out.println("too far right");
        } else if (x_val > 185) {
            Robot.adaptor.leftSide.set(0.15);
            Robot.adaptor.rightSide.set(-0.15);
            System.out.println("too far left");
        } else {
            System.out.println("centered");
            finished = true;
        }
        Robot.adaptor.cam.setPicture();
    }

    protected boolean isFinished() {
        return finished;
    }

    protected void end() {
    }

  
    protected void interrupted() {
    }
}
