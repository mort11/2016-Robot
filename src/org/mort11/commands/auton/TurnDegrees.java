package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.Robot;
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

    public TurnDegrees(double angle) { //takes desired angle for turning (hopefully between -180 and 180)
        this.desiredAngle = angle;
        requires(left);
        requires(right);
        pd = new PIDLoop(this.desiredAngle, .01, 0); //placeholder values, must test
    }

    protected void initialize() {
        DTSide.resetYaw(); //resets the yaw so current angle is accurate
    }

    protected void execute() {
        if (!DTSide.getDisabled()){ // Will run when the Drivetrain is not disabled
            //currentAngle = DTSide.getAngle(); //gets current angle of robot
            currentAngle = DTSide.getYaw(); //might work better than getAngle(), must test
            System.out.println("current angle" + currentAngle);
            speed = pd.getOutput(currentAngle); //passes current angle through pid loop
            System.out.println("speed" + speed);
            SmartDashboard.putNumber("Current Angle", currentAngle);
            SmartDashboard.putNumber("Speed", speed);
            left.set(speed); //sets speed
            right.set(-speed); //sets negative speed so robot can turn
        } else {
            end();
        }
    }

    protected boolean isFinished() {
        return false;
        //return this.inThresh();
    }

    protected void end() {
        left.set(0);
        right.set(0);
        left.resetEncoder();
        right.resetEncoder();
        DTSide.resetYaw();
    }

    protected void interrupted() {
    }

    //used to determine if robot is close enough to target to stop
//    protected boolean inThresh() {
//        //placeholder values, must test
//        return speed < .1 && speed > -.1;
//    }
}
