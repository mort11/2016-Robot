package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.JoystickIntake;
import org.mort11.constants.EndEffectorConstants;
import org.mort11.constants.PDPConstants;
import org.mort11.sensors.SensorDealer;
import org.mort11.util.MORTSubsystem;
import org.mort11.util.powermanager.MORTCANTalon;

/**
 * IntakeArm - Controls the intake arm
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class IntakeArm extends Subsystem implements MORTSubsystem {
    private static Encoder intakeEnc;
    private boolean disabled = false;
    private MORTCANTalon intakeArm;

    public IntakeArm() {
        intakeArm = new MORTCANTalon(EndEffectorConstants.INTAKE_ARM_TALON_ID, PDPConstants.INTAKE_ARM, "Intake Arm");
        intakeEnc = SensorDealer.getInstance().getIntakeArmEncoder();
        intakeEnc.reset();
        intakeEnc.setDistancePerPulse(EndEffectorConstants.INCHES_PER_PULSE);
    }

    /**
     * Get position of intake arm from encoder reading
     *
     * @return Encoder distance/ticks
     */
    public static double getDistance() {
        return intakeEnc.get();
    }
    
    public double getTalDist() {
    	return intakeArm.get();
    }

    /**
     * Get position of intake arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public static double getAngle() {
//        return getDistance() * EndEffectorConstants.INTAKE_DEGREE_PER_TICK;
        return SensorDealer.getInstance().getArmPot().get();
    }

    /**
     * Check if arm is at limit switch
     *
     * @return True if arm at limit switch
     */
    public boolean islimSwitch() {
        return SensorDealer.getInstance().getArmLimitSwitch().get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new JoystickIntake());
    }

    /**
     * Adjust position of intake arm
     *
     * @param speed Amount to move arm by
     */
    public void set(double speed) {
        if (!disabled) {
            intakeArm.set(speed);
        }
    }

    /**
     * Disable the subsystem
     */
    @Override
    public void disable() {
        this.disabled = true;
    }

    /**
     * Check if subsystem is disabled
     *
     * @return Subsystem state
     */
    @Override
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * Re-enable subsystem that is in a disabled state
     */
    @Override
    public void enable() {
        this.disabled = false;
    }
}