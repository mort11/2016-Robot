package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;

/**
 * Adjust robot to goal using the camera
 *
 * @author Sahit Chintalapudi
 * @author Jakob Shortell
 * @author Matt Krzyzanowski
 */
@Deprecated
public class TurnToGoal extends Command {
    Timer timer;
    double thistime = 0, lasttime = 0;
    double netError;
    double kP = 0.0005, kI = 0.000;
    boolean kangaroo = true;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double area = 0;
    private boolean isFinished = false;
    private int curr_index = 0, target_index = 0;
    private double curr_pix = -1, error;

    public TurnToGoal() {
        requires(left);
        requires(right);
        timer = new Timer();
        setInterruptible(true);
    }

    public TurnToGoal(boolean kangaroo) {
        requires(left);
        requires(right);
        timer = new Timer();
        setInterruptible(true);
        this.kangaroo = kangaroo;
    }

    protected void initialize() {
        this.left.set(0);
        this.right.set(0);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        if (Robot.table.getNumberArray("centerX", new double[]{}).length == 0 && kangaroo) {
            System.out.println("Not found in ATG");
            isFinished = true;
            return;
        }
        timer.start();
    }

    protected void execute() {
        if (kangaroo) {
            try {
                //TODO: take advantage of PID class
                area = 0;
                target_index = 0;
                curr_index = 0;
                for (double i : Robot.table.getNumberArray("area", new double[]{})) {
                    if (i > area) {
                        area = i;
                        target_index = curr_index;
                    }
                    curr_index++;
                }
                curr_pix = Robot.table.getNumberArray("centerX", new double[]{})[target_index];
                System.out.println("curr pix: " + curr_pix);
                System.out.println("curr area: " +
                        Robot.table.getNumberArray("area", new double[]{})[target_index]);
                thistime = timer.get();
                lasttime = thistime;
                error = curr_pix - 158;
                netError += (error * (thistime - lasttime));
                right.set(-(error * kP + netError * kI));
                left.set((error * kP + netError * kI));
            } catch (Exception e) {
                this.isFinished = true;
            }
        } else {
            Robot.adaptor.cam.threshold(Robot.adaptor.cam.getPicture());
            curr_pix = Robot.adaptor.cam.getBlob();
            System.out.println("curr pix: " + curr_pix);
            thistime = timer.get();
            lasttime = thistime;
            error = curr_pix - 310;
            System.out.println("error: " + error + " output " + (error * kP));
            netError += (error * (thistime - lasttime));
            //right.set(-(error * kP + netError * kI));
            //left.set((error * kP + netError * kI));
        }
    }

    protected boolean isFinished() {
//    	return (Math.abs(distance - currentDistanceLeft) < 8 &&
        System.out.println("time: " + timer.get());
        return timer.get() > 2.5 || isFinished;
        //return ((Math.abs(error) < 0.1 && loopStarted)) || (debug && this.isFinished);
        //return false;
    }

    protected void end() {
        this.isFinished = false;
        this.left.set(0);
        this.right.set(0);

    }

    protected void interrupted() {
    }
}