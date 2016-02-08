package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * TurnDegrees - Turn x degrees
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class TurnDegrees extends Command {

    //	Timer timer;
    private PIDLoop pd;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    private double speed;
    private double desiredAngle; //angle that the robot will turn by
    private double currentAngle; //current orientation of robot
    private boolean isReverse; //allows the robot to turn counterclockwise if set to true

    public TurnDegrees(boolean isReverse, double angle) { //takes desired angle for turning, must be positive, and boolean for turn direction
        this.isReverse = isReverse;
        this.desiredAngle = angle;
        requires(left);
        requires(right);
        pd = new PIDLoop(this.desiredAngle, 0.01, 0.01, 1.5); //original values were (.02, .005, 2)
    }

    protected void initialize() {
        SensorDealer.getInstance().getAHRS().reset(); //resets the yaw so current angle is accurate
    }

    protected void execute() {
    	System.out.println("turning");
        if (!DTSide.getDisabled()){ // Will run when the Drivetrain is not disabled
            //currentAngle = DTSide.getAngle(); //gets current angle of robot
            currentAngle = Math.abs(SensorDealer.getInstance().getAHRS().getYaw()); //might work better than getAngle(), must test
            System.out.println("current angle" + currentAngle);
            speed = pd.getOutput(currentAngle); //passes current angle through pid loop
            System.out.println("speed" + speed);
            SmartDashboard.putNumber("Current Angle", currentAngle);
            SmartDashboard.putNumber("Speed", speed);
            if (!isReverse) { //clockwise turning
                left.set(speed); //sets speed
                right.set(-speed); //sets negative speed so robot can turn
            } else { //counterclockwise turning
                left.set(-speed); //sets negative speed so robot can turn
                right.set(speed); //sets speed
            }
        } else {
            end();
        }
    }

    protected boolean isFinished() {
        return SensorDealer.getInstance().getAHRS().getYaw() > desiredAngle * 0.9;
        //return this.inThresh();
        //return (currentAngle > (desiredAngle - 1) && currentAngle < (desiredAngle + 1));
    }

    protected void end() {
        left.set(0);
        right.set(0);
        left.resetEncoder();
        right.resetEncoder();
        SensorDealer.getInstance().getAHRS().reset();
    }

    protected void interrupted() {
    }

    //used to determine if robot is close enough to target to stop
//    protected boolean inThresh() {
//        //placeholder values, must test
//        return speed < .1 && speed > -.1;
//    }
}
