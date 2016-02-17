package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
import org.mort11.RobotMap;
=======

import org.mort11.commands.ee.JoystickIntake;
>>>>>>> 60dd0b09bbbcdeb91e63404ddf2dfc76cc2a939f
import org.mort11.constants.EndEffectorConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718

/**
 * Intake - Intake
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
<<<<<<< HEAD
<<<<<<< HEAD
public class Intake extends Subsystem {
    public void initDefaultCommand() {
=======
import org.mort11.RobotMap;
import org.mort11.util.EEConstants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
public class Intake extends Subsystem {
		private Talon intakeArm;
    
		private Encoder intakeEnc;
    

    public Intake() {
    	intakeArm = new Talon(RobotMap.INTAKE_TALON);
    	intakeEnc = new Encoder(RobotMap.INTAKE_ENCODER1,RobotMap.INTAKE_ENCODER2 );
    	intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
<<<<<<< HEAD
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
=======
public class Intake extends Subsystem implements MORTSubsystem {
    boolean isDisabled = false;
    private CANTalon intakeArm;

    public Intake() {
        intakeArm = new CANTalon(EndEffectorConstants.ARM_TALON_PORT);
        SensorDealer.getInstance().getArmEncoder().reset();
        SensorDealer.getInstance().getArmEncoder().setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }
// returns the distance gotten from the arm encoder
    public static double getDistance() {
        System.out.println(SensorDealer.getInstance().getArmEncoder().get());
        return SensorDealer.getInstance().getArmEncoder().get();
>>>>>>> 71aec0c941fc3f0a86872790a1014caf2b6d8718
    }
// returns the angle of the intake
    public static double getAngle() {
        return getDistance() * EndEffectorConstants.ROLLER_DEGREE_PER_TICK;
    }

    protected void initDefaultCommand() {
    	setDefaultCommand(new JoystickIntake());
    }
    // sets the speed of the intake arm 
    public void set(double speed) {
        if (!isDisabled) {
            intakeArm.set(speed);
        }
    }

    public void disable() {
        isDisabled = true;
    }

}
