package org.mort11;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.Constants;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.LED;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Rollers;
import org.mort11.subsystems.ee.Shooter;

/**
 * HardwareAdaptor - Instantiation of most subsystems, system hardware, and misc.
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class HardwareAdaptor {
    // System components
    public PowerDistributionPanel pdp;
    public Camera cam;
    public Compressor compressor;

    // End Effector mechanisms
    public Rollers rollers;
    public IntakeArm intakeArm;
    public Shooter shooter;

    // Pneumatic-based systems
    public DoubleSolenoid shifter;
    public DoubleSolenoid intakeBrakeSolenoid;
    public DoubleSolenoid hood;

    // Navigational instruments
    public Accelerometer accelerometer;
    public AHRS ahrs;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;
    public LED led;

    public HardwareAdaptor() {
        this.pdp = new PowerDistributionPanel();
        this.cam = new Camera();
        this.compressor = new Compressor(Constants.PCM_ID);

        this.rollers = new Rollers();
        this.intakeArm = new IntakeArm();
        this.shooter = new Shooter();

        this.shifter = new DoubleSolenoid(Constants.PCM_ID, Constants.DT_LOW_SHIFTER_PORT, Constants.DT_HIGH_SHIFTER_PORT);
        this.intakeBrakeSolenoid = new DoubleSolenoid(Constants.PCM_ID, Constants.INTAKE_ARM_BRAKE_A, Constants.INTAKE_ARM_BRAKE_B);
        this.hood = new DoubleSolenoid(Constants.PCM_ID, Constants.HOOD_SOLENOID_A, Constants.HOOD_SOLENOID_B);

        this.accelerometer = new BuiltInAccelerometer();
        this.ahrs = new AHRS(SPI.Port.kMXP);

        this.leftSide = new DTLeft();
        this.rightSide = new DTRight();
        this.led = new LED();
    }
}
