package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.ee.Shooter;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.util.PIDLoop;
/**
 *
 */
public class SpinUp extends Command {
    private DTSide left = Robot.adaptor.leftSide;
	private Shooter armMotor;
    private PIDLoop pd_arm;
    private boolean PID;
    private Encoder shooter = SensorDealer.getInstance().getLeftDTEncoder();//SensorDealer.getInstance().getShooterEncoder();
    private double speed_ghetto = 0;
    private double velocity;
    
    public SpinUp(double velocity,boolean PID) {
    	this.velocity = velocity;
    	requires(left);
    	pd_arm	= new PIDLoop(velocity,0.03,0); // Placeholder values
    	this.PID = PID;

    }
    protected void initialize() {
    	
    }

    protected void execute() {
    	if(PID) {
	    	 double currentVelocity = shooter.getRate();
	    	 System.out.println("speed: " + currentVelocity);
	         double speed = pd_arm.getP(currentVelocity);
	         left.set(speed);
	         SmartDashboard.putNumber("Velocity", currentVelocity);
    	} else {
	    	 double currentVelocity = shooter.getRate();
    			if (currentVelocity < velocity) {
		    		speed_ghetto += .03;
		    		System.out.println("speed if: " + speed_ghetto);
		    		System.out.println("velocity if: " + currentVelocity);
		    		left.set(speed_ghetto);
    			} else {
    				speed_ghetto -= .03;
    				left.set(speed_ghetto);
    				System.out.println("speed else: " + speed_ghetto);
		    		System.out.println("velocity else: " + currentVelocity);
    			}
    	}
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }

    protected void interrupted() {
    }
}
