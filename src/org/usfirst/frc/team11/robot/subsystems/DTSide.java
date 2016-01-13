package org.usfirst.frc.team11.robot.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class DTSide extends Subsystem {
    private Talon motors;
    private Encoder enc;
    private double curVal = 0;
    private boolean motorReverse;
    
    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse,
    	boolean encReverse) {
    	motors = new Talon(motorPort);
    	enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
    	enc.setDistancePerPulse(1); //placeholder value
    	this.motorReverse = motorReverse;
    }
    
    
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

