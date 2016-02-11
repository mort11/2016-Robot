package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.mort11.commands.ee.JoystickShooter;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;

/**
 * Shooter - Controls the flywheel
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 */
public class Shooter extends Subsystem implements MORTSubsystem{
    private CANTalon armMotor;
    boolean isDisabled = false;
    public Shooter() {
        armMotor = new CANTalon(EndEffectorConstants.SHOOTER_PORT);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new JoystickShooter());
    }

    public void set(double speed) {
        if(isDisabled == false){
        	armMotor.set(speed); //sets motor speed on arm if disabled is false
        }
    }
  //returns whether or not the limit switch is on or not
    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get(); //returns whether or not the limit switch is on or not
    }

    // returns the distance taken from the arm encoder
    public double getDistance() {
        return SensorDealer.getInstance().getArmEncoder().getDistance(); 
    }
// returns the angle the arm is at
    public double getAngle() {
        return SensorDealer.getInstance().getArmPot().get();
    }
    // returns the rate the shooter is spinning at 
    public double getRate(){
    	return SensorDealer.getInstance().getShooterEncoder().getRate();
    }
    // disables the arm
    public void disable(){
    	isDisabled = true;
    }
    
}
