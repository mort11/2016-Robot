package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.Robot;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.Flywheel;
import org.mort11.util.PIDLoop;

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
    }
    
    public SpinUp() {
    	this(1000,true);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (PID) { //uses pid loop to SpinUp
        	curr_vel = spinUp.getSpeed() ;
        	//System.out.println("RPM = " + curr_vel);
        	double delta_rpm = (velocity - curr_vel) * 0.000001;
        	voltage_command = voltage_command+delta_rpm;
        	if(voltage_command > 1) {
        		voltage_command = 1;
        	} else if (voltage_command < 0) {
        		voltage_command = 0;
        	}
        	//System.out.println("voltage_command = " + voltage_command);
        	spinUp.set(voltage_command);
        	per_error = Math.abs((velocity - curr_vel)/velocity);
        	if(per_error < 0.1) {
        		SmartDashboard.putString("RPM", "you're there!");
        	} else {
        		System.out.println("percent error shooter: " + per_error);
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
    	spinUp.set(0);
    }

    @Override
    protected void interrupted() {
    }
}
