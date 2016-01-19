package org.mort11.subsystems.dt;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import util.DTConstants;

public abstract class DTSide extends Subsystem {

    private static CANTalon motors;
    private static Encoder enc;
    private static boolean motorReverse;
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);
    
    static PowerDistributionPanel pdp = new PowerDistributionPanel();

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new CANTalon(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE); // Placeholder value
        DTSide.motorReverse = motorReverse;
    }

    public static double getSpeed() {
        return motors.get();
    }

    public static void setSpeed(double speed) {
        motors.set(speed * (motorReverse ? -1 : 1));
    }

    public static double getCurrentLeft() { 
    	SmartDashboard.putNumber("Left Motor Current", pdp.getCurrent(0));
        return pdp.getCurrent(0); //placeholder channel value, returns current of channel in Amps
    }
    
    public static double getCurrentRight() {
    	SmartDashboard.putNumber("Right Motor Current", pdp.getCurrent(1));
    	return pdp.getCurrent(1); //placeholder channel value, returns current of channel in Amps
    }

    public static double getTalonCurrent() {
    	return motors.getOutputCurrent(); //gets current of talon in Amps
    }
    
    public static double getTalonVoltage() {
    	return motors.getOutputVoltage(); //gets voltage of talon in Volts
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

    public void initDefaultCommand() {

    }

    public double getAngle() {
        return navx.getAngle();
    }

}

