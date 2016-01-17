package org.mort11.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import util.DTConstants;

public abstract class DTSide extends Subsystem {
    
	private static TalonSRX motors;
    private static Encoder enc;
    private static boolean motorReverse;
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new TalonSRX(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE); // Placeholder value
        this.motorReverse = motorReverse;
    }

    public void initDefaultCommand() {

    }

    public static double getSpeed() {
        return motors.get();
    }

    public static void setSpeed(double speed) {
        motors.set(speed * (motorReverse ? -1 : 1));
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
    
    public double getAngle() {
    	return navx.getAngle();
    }
    
}

