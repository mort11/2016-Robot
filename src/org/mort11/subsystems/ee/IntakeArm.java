package org.mort11.subsystems.ee;

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
    private boolean disabled = false;
    private MORTCANTalon intakeArm;

    public IntakeArm() {
        intakeArm = new MORTCANTalon(EndEffectorConstants.INTAKE_ARM_TALON_ID, PDPConstants.INTAKE_ARM, "Intake Arm");
        intakeArm.reset();
    }

    /**
     * Get position of intakeArm arm from encoder reading
     *
     * @return Encoder distance/ticks
     */
    public double getDistance() {
        return intakeArm.getEncPosition();
    }

    /**
     * Get position of intakeArm arm from encoder reading as angle
     *
     * @return Encoder angle
     */
    public double getAngle() {
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
     * Adjust position of intakeArm arm
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