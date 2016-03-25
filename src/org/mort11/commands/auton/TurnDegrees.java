package org.mort11.commands.auton;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * TurnDegrees - Turn x degrees
 *
 * @author Matthew Krzyzanowski
 */
public class TurnDegrees extends Command {
    private PIDLoop pd;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double speed;
    private double desiredAngle; // Angle that the robot will turn by
    private double currentAngle; // Current orientation of robot
    private boolean isReverse; // Allows the robot to turn counterclockwise if set to true
    private AHRS ahrs = Robot.adaptor.ahrs;

    /**
     * Takes desired angle for turning, must be positive, and boolean for turn direction
     *
     * @param isReverse Should reverse motor?
     * @param angle     Angle to turn by
     */
    public TurnDegrees(boolean isReverse, double angle) {
        this.isReverse = isReverse;
        this.desiredAngle = angle;
        requires(left);
        requires(right);
        pd = new PIDLoop(this.desiredAngle, 0.02, 0.01, 17); // Original vals:  (.02, .005, 2)
    }
    public TurnDegrees() {
    	if(left.getDistToTurn() < 0) {
    		this.isReverse = true;
            this.desiredAngle = -left.getDistToTurn();       
    	}
    	else {
    		this.isReverse = false;
            this.desiredAngle = left.getDistToTurn();
    	}
    	 requires(left);
         requires(right);
         System.out.println("Turn Degrees will turn: " + this.desiredAngle);
    	 pd = new PIDLoop(this.desiredAngle, 0.02, 0.01, 17); // Original vals:  (.02, .005, 2)
    }
    /**
     * Resets the yaw so current angle is accurate
     */
    protected void initialize() {
        this.ahrs.reset();
    }

    protected void execute() {
        //currentAngle = DTSide.getAngle(); //gets current angle of robot
        currentAngle = Math.abs(this.ahrs.getYaw()); //might work better than getAngle(), must test
        System.out.println("current angle" + currentAngle);
        speed = pd.getOutput(currentAngle); //passes current angle through pid loop
        System.out.println("output " + speed);
//        System.out.println("speed" + speed);
        SmartDashboard.putNumber("Current Angle", currentAngle);
        SmartDashboard.putNumber("Speed", speed);
        // Clockwise turning
        if (!isReverse) {
            left.set(speed);
            right.set(-speed);
        }
        // Counterclockwise turning
        else {
            left.set(-speed);
            right.set(speed);
        }
        //System.out.println("left speed: " + left.getSpeed());
        //System.out.println("right speed: " + right.getSpeed());
    }

    protected boolean isFinished() {
    	System.out.println("is finished: " + (this.ahrs.getYaw() > desiredAngle * 0.98));
        return this.ahrs.getYaw() > desiredAngle * 0.98;
    }

    protected void end() {
    	System.out.println("ending");
        left.set(0);
        right.set(0);
        left.resetEncoder();
        right.resetEncoder();
        this.ahrs.zeroYaw();
    }

    protected void interrupted() {
    }

}
