package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
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
    private Talon armMotor;
    boolean isDisabled = false;

    public Shooter() {
        armMotor = new Talon(EndEffectorConstants.ARM_TALON_PORT);
    }

    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        if(isDisabled == false){
        	armMotor.set(speed);
        }
    }

    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
    }

    public double getDistance() {
        return SensorDealer.getInstance().getArmEncoder().getDistance();
    }

    public double getAngle() {
        return SensorDealer.getInstance().getArmPot().get();
    }
    
    public void disable(){
    	isDisabled = true;
    }
}
