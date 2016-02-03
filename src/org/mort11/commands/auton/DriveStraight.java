package org.mort11.commands.auton;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;

/**
 * DriveStraight - Drive in a (mostly) straight line
 *
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class DriveStraight extends Command {
    private double driveDistance; //distance that you want robot to drive
    private DTSide left = Robot.left;
    private DTSide right = Robot.right;
        private PIDLoop pd_left;
        private PIDLoop pd_right;
        private double curDist_left;
        private double curDist_right;
        private double speed_left;
        private double speed_right;
        private Encoder leftDTEncoder = SensorDealer.getInstance().getLeftDTEncoder();
        private Encoder rightDTEncoder = SensorDealer.getInstance().getRightDTEncoder();

        public DriveStraight(double distance) {
            this.driveDistance = distance;
            requires(left);
            requires(right);
            pd_left = new PIDLoop(driveDistance, .01, 0); //placeholder values, must test
        pd_right = new PIDLoop(driveDistance, .01, 0); //placeholder values, must test
    }

    protected void initialize() {
        DTSide.resetEncoders();
    }

    protected void execute() {
        curDist_left = leftDTEncoder.getDistance(); //get distance traveled since last encoder reset
        curDist_right = rightDTEncoder.getDistance(); //get distance traveled since last encoder reset
        speed_left = pd_left.getOutput(curDist_left); //gets speed using pid loop
        speed_right = pd_right.getOutput(curDist_right); //gets speed using pid loop
        left.setSpeed(speed_left); //sets speed of robot, is negative to make it drive correctly
        right.setSpeed(-speed_right); //sets speed of robot
        System.out.println(curDist_left);
        System.out.println(curDist_right);
        SmartDashboard.putNumber("Distance Traveled Left", curDist_left); //gets and displays distance traveled
        SmartDashboard.putNumber("Distance Traveled Right", curDist_right); //gets and displays distance traveled
        SmartDashboard.putNumber("Left PWM Value", left.getSpeed()); //gets most recently set PWM value, between -1.0 and 1.0
        SmartDashboard.putNumber("Right PWM Value", right.getSpeed()); //gets most recently set PWM value, between -1.0 and 1.0
        SmartDashboard.putNumber("Left Speed", speed_left); //gets most recently set PWM value, between -1.0 and 1.0
        SmartDashboard.putNumber("Right Speed", speed_right); //gets most recently set PWM value, between -1.0 and 1.0
        SmartDashboard.putNumber("Left Talon Current", left.getTalonCurrent()); //gets the current of the left talon in amps
        SmartDashboard.putNumber("Right Talon Current", right.getTalonCurrent()); //gets the current of the right talon in amps
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        left.setSpeed(0);
        right.setSpeed(0);
        DTSide.resetEncoders();
    }

    protected void interrupted() {
    }
}
