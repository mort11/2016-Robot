package org.mort11;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import org.mort11.constants.Constants;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.Flywheel;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Rollers;
import org.mort11.util.camera.Camera;

/**
 * Instantiation of subsystems and system hardware
 *
 * @author Matt Turi
 */
public class HardwareAdaptor {
    public Camera cam;

    // End Effector mechanisms
    public Rollers rollers;
    public IntakeArm intakeArm;
    public Flywheel flywheel;

    // Pneumatics
    public DoubleSolenoid shifter;
    public DoubleSolenoid hood;
    public DoubleSolenoid pistonIntake;
    public DoubleSolenoid indexers;

    // Navigational instruments
    public AHRS ahrs;

    // Sensors
    public Encoder leftDTEncoder;
    public Encoder rightDTEncoder;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;

    HardwareAdaptor() {
        this.rollers = new Rollers();
        this.intakeArm = new IntakeArm();
        this.flywheel = new Flywheel();

        this.shifter = new DoubleSolenoid(Constants.PCM_ID, Constants.DT_LOW_SHIFTER_PORT, Constants.DT_HIGH_SHIFTER_PORT);

        this.pistonIntake = new DoubleSolenoid(Constants.PCM_ID, Constants.INTAKE_SOLENOID_A, Constants.INTAKE_SOLENOID_B);

        this.hood = new DoubleSolenoid(Constants.PCM_ID, Constants.HOOD_SOLENOID_A, Constants.HOOD_SOLENOID_B);
        this.indexers = new DoubleSolenoid(Constants.PCM_ID, Constants.INDEXERS_SOLENOID_A, Constants.INDEXERS_SOLENOID_B);

        this.ahrs = new AHRS(SPI.Port.kMXP);

        this.leftDTEncoder = new Encoder(Constants.DT_ENCODER_LEFT_A, Constants.DT_ENCODER_LEFT_B, true, CounterBase.EncodingType.k4X);
        this.leftDTEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE_LEFT);
        this.rightDTEncoder = new Encoder(Constants.DT_ENCODER_RIGHT_A, Constants.DT_ENCODER_RIGHT_B, true, CounterBase.EncodingType.k4X);
        this.rightDTEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE_RIGHT);

        this.leftSide = new DTLeft(leftDTEncoder);
        this.rightSide = new DTRight(rightDTEncoder);
    }
}
