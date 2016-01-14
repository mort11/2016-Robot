package org.mort11.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */


public class DT extends Subsystem {
   private final static double kCircumference = 4 * Math.PI;
   Talon leftTal = new Talon(0);
   Talon rightTal = new Talon(1);
   Encoder leftEnc = new Encoder(0,1);
   
   public DT() {
	   leftEnc.setDistancePerPulse(kCircumference/256);
	   leftEnc.reset();
   }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveSpeed(double speed) {
    	leftTal.set(speed);
    	rightTal.set(speed);
    }
    
    public void stop() {
    	driveSpeed(0);
    }
 
    public void resetEnc() {
    	leftEnc.reset();
    }
    
    public double getDist() {
    	return leftEnc.getDistance();
    }
}

