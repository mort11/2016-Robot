package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;

/**
 * IntakeArm - Intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
<<<<<<< HEAD
<<<<<<< HEAD:src/org/mort11/subsystems/ee/IntakeArm.java
public class IntakeArm extends Subsystem {
=======
public class Rollers extends Subsystem {
<<<<<<< HEAD
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7:src/org/mort11/subsystems/ee/Rollers.java
=======
public class Rollers extends Subsystem {
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
    public void initDefaultCommand() {
       
    }
=======

    private CANTalon rollerMotor = new CANTalon(EndEffectorConstants.ARM_TALON_PORT);
    private Encoder rollerEnc = SensorDealer.getInstance().getShooterEncoder();

    public Rollers() {
        rollerEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
        rollerEnc.reset();
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
}

    protected void initDefaultCommand() {

    }

    public void set(double speed) {
        rollerMotor.set(speed);
    }

    public double getDistance() {
        return rollerEnc.getDistance();
    }
}