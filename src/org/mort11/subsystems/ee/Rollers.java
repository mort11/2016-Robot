package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * Rollers - Controls the intakeArm roller
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
<<<<<<< HEAD
<<<<<<< HEAD:src/org/mort11/subsystems/ee/IntakeArm.java
public class IntakeArm extends Subsystem {
=======
public class Rollers extends Subsystem {
<<<<<<< HEAD
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
=======
    private MORTCANTalon rollers;

    public Rollers() {
        this.rollers = new MORTCANTalon(EndEffectorConstants.ROLLER_TALON_ID, PDPConstants.ROLLERS, "Rollers");
        rollers.reset();
    }
>>>>>>> 60dd0b09bbbcdeb91e63404ddf2dfc76cc2a939f
}

    @Override
    protected void initDefaultCommand() {
    }

    /**
     * Set speed of intakeArm rollers
     *
     * @param speed Speed of rollers
     */
    public void set(double speed) {
        rollers.set(speed);
    }

    /**
     * Get distance rollers have gone
     *
     * @return Distance
     */
    public double getDistance() {
        return rollers.getEncPosition();
    }
}