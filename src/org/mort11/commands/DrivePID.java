package org.mort11.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.util.Logger;
import org.mort11.util.PIDLoop;

/**
 * DrivePID - Drive linear within PID confines
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DrivePID extends Command {
    PIDLoop loopFunction_left;
    PIDLoop loopFunction_right;
    double target = 60;
    double velLeft = 0;
    double velRight = 0;

    public DrivePID() {
        requires(Robot.adaptor.dt);
    }

    public DrivePID(double target) {
        this.target = target;
        requires(Robot.adaptor.dt);
    }

    protected void initialize() {
        loopFunction_left = new PIDLoop(target, 0.05, 0);
        loopFunction_right = new PIDLoop(target, 0.05, 0);

        Logger.init("/home/lvuser/output");
        Logger.writeString("Left Dist,SP Left,Left PWM, Right Dist,SP Right, Right PWM");
    }

    protected void execute() {
        velLeft = loopFunction_left.getOutput(Robot.adaptor.dt.getDistLeft());
        velRight = loopFunction_right.getOutput(Robot.adaptor.dt.getDistRight());
        Logger.writeString(Robot.adaptor.dt.getDistLeft() + "," + loopFunction_left.getSP()
                + "," + velLeft + "," + Robot.adaptor.dt.getDistRight() + "," + loopFunction_right.getSP()
                + "," + velRight);
        System.out.println("Left- Distance:  " + Robot.adaptor.dt.getDistLeft() + " PI: " + velLeft);
        System.out.println("Right- Distance:  " + Robot.adaptor.dt.getDistRight() + " PI: " + velRight);
        Robot.adaptor.dt.driveLeft(velLeft);
        Robot.adaptor.dt.driveRight(velRight);
    }

    protected boolean isFinished() {
        //2 inch threshold and slow
        return Math.abs(Robot.adaptor.dt.getDistLeft() / target) > 0.98
                && Math.abs(velLeft) < 0.35;
    }

    protected void end() {
        Logger.close();
        Robot.adaptor.dt.resetEnc();
        Robot.adaptor.dt.stop();
        Robot.adaptor.dt.resetEnc();
        Robot.adaptor.dt.stop();
    }

    protected void interrupted() {
    }
}
