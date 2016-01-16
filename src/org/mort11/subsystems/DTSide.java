package org.mort11.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import util.DTConstants;

public abstract class DTSide extends Subsystem {
    private static TalonSRX motors;
    private static Encoder enc;
    // private double curVal = 0;
    private boolean motorReverse;

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new TalonSRX(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE); // Placeholder value
        this.motorReverse = motorReverse;
    }

    public void initDefaultCommand() {

    }

    public double getSpeed() {
        return motors.get();
    }

    public static void setSpeed(double speed) {
        motors.set(speed);
    }
    
    public static double getCurent(){ //returns raw value, unsure of what it means
    	return motors.getRaw();
    }

    public static double getDist() {
        return enc.getDistance();
    }

    public static double getEncRate() {
        return enc.getRate();
    }

    public static void resetEnc() {
        enc.reset();
    }
}

