package org.mort11.subsystems.dt;

import org.mort11.util.DTConstants;

//import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class DTSide extends Subsystem {

    private CANTalon motors;
    protected Encoder enc;
    private static boolean motorReverse;
//  private static AHRS navx = new AHRS(SerialPort.Port.kMXP);
    
    static PowerDistributionPanel pdp = new PowerDistributionPanel();

    public DTSide(int motorPort, int encAPort, int encBPort, boolean motorReverse, boolean encReverse) {
        motors = new CANTalon(motorPort);
        enc = new Encoder(encAPort, encBPort, encReverse, EncodingType.k4X);
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE_RIGHT);
        DTSide.motorReverse = motorReverse;
    }

    public double getSpeed() {
        return motors.get();
    }

    public void setSpeed(double speed) {
        motors.set(speed * (motorReverse ? -1 : 1));
    }

    public double getCurrentLeft() { 
        SmartDashboard.putNumber("Left Motor Current", pdp.getCurrent(0));
        return pdp.getCurrent(0); //placeholder channel value, returns current of channel in Amps
    }
    
    public double getCurrentRight() {
        SmartDashboard.putNumber("Right Motor Current", pdp.getCurrent(1));
        return pdp.getCurrent(1); //placeholder channel value, returns current of channel in Amps
    }

    public double getTalonCurrent() {
        return motors.getOutputCurrent(); //gets current of talon in Amps
    }
    
    public double getTalonVoltage() {
        return motors.getOutputVoltage(); //gets voltage of talon in Volts
    }
    public double getDist() {
        return enc.getDistance();
    }

    public double getEncRate() {
        return enc.getRate();
    }

    public void resetEnc() {
        enc.reset();
    }
    
    //will disable the motor by setting the speed to 0
    public void disable(){
        motors.set(0);
    }

    public void initDefaultCommand() {

    }
    //copy pasted 2014 DT shift methods 
   /* public void shift()
    {
        if (curGear == Gear.LowGear) {
            shift(Gear.HighGear);
        } else {
            shift(Gear.LowGear);
        }
    }
    
    public void shift(Gear g)
    {
        curGear = g;
        if (g == Gear.LowGear) {
            lowShifter.set(true);
            highShifter.set(false);
        } else {
            lowShifter.set(false);
            highShifter.set(true);
        }
    }*/

    //needs to be moved into a nav class
//    public static double getAngle() {
//        return navx.getAngle();
//    }

}

