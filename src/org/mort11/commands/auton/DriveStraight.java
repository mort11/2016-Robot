package org.mort11.commands.auton;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.Logger;
import org.mort11.util.PIDLoop;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * DriveStraight - Drive in a (mostly) straight line
 *
 * @author Matthew Krzyzanowski
 * @author Sahit Chintalapudi
 * @author Jeffrey Pastilha
 */
public class DriveStraight extends Command {
    double currentDistanceLeft, currentDistanceRight;
    double distance;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private PIDLoop pd_left, pd_right;
    private Encoder leftDTEncoder = Robot.adaptor.leftDTEncoder;
    private Encoder rightDTEncoder = Robot.adaptor.rightDTEncoder;
    private AHRS ahrs = Robot.adaptor.ahrs;
    private Timer timer;
    public DriveStraight(double distance) {
        requires(left);
        requires(right);
        pd_left = new PIDLoop(distance, .01, 0.01,1.2); 
        pd_right = new PIDLoop(distance, .01, 0.01,1.2);        
        this.distance = distance;
        leftDTEncoder.reset();
        rightDTEncoder.reset();
        this.ahrs.zeroYaw();
        timer = new Timer();
    }

    protected void initialize() {
//    	System.out.println("init yaw: " + ahrs.getYaw());
//    	System.out.println("init d_left: " + leftDTEncoder.getDistance());
//    	System.out.println("init d_right: " + rightDTEncoder.getDistance());
    	Logger.writeString("time,left dist,speed left,right dist,right speed");
    	timer.start();
    }

    protected void execute() {
        currentDistanceLeft = leftDTEncoder.getDistance();
        currentDistanceRight = rightDTEncoder.getDistance();
//        System.out.println("left: " + currentDistanceLeft);
//        System.out.println("Right " + currentDistanceRight);
        double speedLeft = pd_left.getOutput(currentDistanceLeft);
        double speedRight = pd_right.getOutput(currentDistanceRight);     
        Logger.writeString(timer.get()+","+currentDistanceLeft+","+speedLeft+","+currentDistanceRight+","+speedRight);
//        System.out.println("speed left: " + speedLeft);
//        System.out.println("speed left: " + speedRight);
        
        double angleError = this.ahrs.getYaw() % 360;
        if (angleError > 180) {
            angleError = Math.abs(360 - angleError);
        }
        System.out.println("angle error: " + angleError);

        left.set(speedLeft - 0.02 * angleError);
        right.set(speedRight + 0.02 * angleError);


        SmartDashboard.putNumber("Left Distancse", currentDistanceLeft);
        SmartDashboard.putNumber("Right Distance", currentDistanceRight);
        SmartDashboard.putNumber("Left Speed", speedLeft);
        SmartDashboard.putNumber("Right Speed", speedRight);
        SmartDashboard.putNumber("Angle Disp", angleError);
    }

    protected boolean isFinished() {
        return (Math.abs(distance - currentDistanceLeft) < 2 &&
                Math.abs(distance - currentDistanceRight) < 2) ||
                (pd_left.timeElapsed(2));
    }

    protected void end() {
    	Logger.close();
        left.halt();
        right.halt();
        left.resetEncoder();
        right.resetEncoder();
    }

    protected void interrupted() {
    }
}
