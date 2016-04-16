package org.mort11.commands.ee;

import org.mort11.Robot;
import org.mort11.subsystems.ee.Flywheel;
import org.mort11.util.PIDLoop;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * SpinUp - Spins up flywheel to a specified RPM
 *
 * @author Seven Kurt
 * @author Matthew Krzyzanowski
 * @author Sahit Chintalapudi
 */
public class SpinUp extends Command {
    private Flywheel spinUp = Robot.adaptor.flywheel;
    private Flywheel armMotor;
    private PIDLoop pd_arm;
    private boolean PID;
    private double speed_ghetto = 0;
    private double velocity;
    double curr_vel,voltage_command,per_error;

    public SpinUp(double velocity, boolean PID) {
        this.velocity = velocity;
        requires(spinUp);
        pd_arm = new PIDLoop(velocity, 0.03, 0); // Placeholder values
        this.PID = PID;
        setInterruptible(true);
    }
    
    public SpinUp() {
    	this(96000,true);
    }

    @Override
    protected void initialize() {
    	setInterruptible(true);
    }

    @Override
    protected void execute() {
    	if(this.velocity == 0) {
    		spinUp.set(0);
//    		System.out.println("stopping");
    		return;
    	}
        if (PID) { //uses pid loop to SpinUp
        	curr_vel = spinUp.getSpeed() ;
        	System.out.println("RPM = " + curr_vel);
        	if((curr_vel - velocity) < 1000  && (curr_vel - velocity) > 0) { 
        		curr_vel = velocity;
        	}
        	double delta_rpm = (velocity - curr_vel) * 0.00000025;
        	voltage_command = voltage_command+delta_rpm;
        	if(voltage_command > 1) {
        		voltage_command = 1;
        	} else if (voltage_command < 0) {
        		voltage_command = 0;
        	}
        	System.out.println("voltage_command = " + voltage_command);
        	spinUp.set(voltage_command);
        	per_error = (velocity - curr_vel)/velocity;
        	if(per_error > -0.02 && per_error < 0.05) {
        		SmartDashboard.putString("RPM", "there!");
        		SmartDashboard.putBoolean("spun up", true);
        	} else {
        		SmartDashboard.putString("RPM", "not there!");
        		SmartDashboard.putBoolean("spun up", false);
//        		System.out.println("percent error shooter: " + per_error);
        	}
            //SmartDashboard.putNu1mber("Velocity", currentVelocity);
        } else { // ghetto way of spinning up
            double currentVelocity = spinUp.getSpeed();
            if (currentVelocity < velocity) {
                speed_ghetto += .03;
//                System.out.println("speed if: " + speed_ghetto);
//                System.out.println("velocity if: " + currentVelocity);
                spinUp.set(speed_ghetto);
            } else {
                speed_ghetto -= .03;
                spinUp.set(speed_ghetto);
//                System.out.println("speed else: " + speed_ghetto);
//                System.out.println("velocity else: " + currentVelocity);
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
//    	System.out.println("ending");
    	spinUp.set(0);
    }

    @Override
    protected void interrupted() {
//    	System.out.println("ending");
    	spinUp.set(0);
    }
}
