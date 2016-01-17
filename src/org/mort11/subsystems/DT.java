package org.mort11.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */


public class DT extends Subsystem {
   private final static double kCircumference = 4 * Math.PI;
   Talon leftTal = new Talon(0);
   Talon rightTal = new Talon(2);
   Encoder leftEnc = new Encoder(2,3,false,EncodingType.k4X);
   Encoder rightEnc = new Encoder(2,3,false,EncodingType.k4X);
   
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
    
    public void driveLeft(double speed) {
    	leftTal.set(speed);
    }
    
    public void driveRight(double speed) {
    	rightTal.set(-speed);
    }
    
    public void stop() {
    	driveLeft(0);
    	driveRight(0);
    }
 
    public void resetEnc() {
    	leftEnc.reset();
    }
    
    public double getDistLeft() {
    	return leftEnc.getDistance();
    }
    
    public double getDistRight() {
    	return rightEnc.getDistance();
    }
}

