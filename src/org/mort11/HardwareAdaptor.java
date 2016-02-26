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
import org.mort11.subsystems.ee.Flywheel;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Rollers;

/**
 * HardwareAdaptor - Instantiation of most subsystems, system hardware, and misc.
 *
 * @author Matt Turi
 */
public class HardwareAdaptor {
    // System components
    public PowerDistributionPanel pdp;
    public Camera cam;
    public Compressor compressor;

    // End Effector mechanisms
    public Rollers rollers;
    public IntakeArm intakeArm;
    public Flywheel flywheel;

    // Pneumatic-based systems
    public DoubleSolenoid shifter;
    public DoubleSolenoid hood;

    // Navigational instruments
    public Accelerometer accelerometer;
    public AHRS ahrs;

    // Sensors
    public Encoder leftDTEncoder;
    public Encoder rightDTEncoder;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;
    public LED led;

    // NetworkTable
    public double[] areas, centerX, centerY, width, height, solidity;
    double[] defaultValue = new double[0];

    public HardwareAdaptor() {
        this.pdp = new PowerDistributionPanel();
        this.cam = new Camera();
        this.compressor = new Compressor(Constants.PCM_ID);

        this.rollers = new Rollers();
        this.intakeArm = new IntakeArm();
        this.flywheel = new Flywheel();

        this.shifter = new DoubleSolenoid(Constants.PCM_ID, Constants.DT_LOW_SHIFTER_PORT, Constants.DT_HIGH_SHIFTER_PORT);
        this.hood = new DoubleSolenoid(Constants.PCM_ID, Constants.HOOD_SOLENOID_A, Constants.HOOD_SOLENOID_B);

        this.accelerometer = new BuiltInAccelerometer();
        this.ahrs = new AHRS(SPI.Port.kMXP);

        this.leftDTEncoder = new Encoder(Constants.DT_ENCODER_LEFT_A, Constants.DT_ENCODER_LEFT_B, false, CounterBase.EncodingType.k4X);
        this.leftDTEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE_LEFT);
        this.rightDTEncoder = new Encoder(Constants.DT_ENCODER_RIGHT_A, Constants.DT_ENCODER_RIGHT_B, false, CounterBase.EncodingType.k4X);
        this.rightDTEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE_RIGHT);

        this.leftSide = new DTLeft(leftDTEncoder);
        this.rightSide = new DTRight(rightDTEncoder);
        this.led = new LED();

        this.areas = Robot.tableLocation.getNumberArray("area", defaultValue);
        this.centerX = Robot.tableLocation.getNumberArray("centerX", defaultValue);
        this.centerY = Robot.tableLocation.getNumberArray("centerY", defaultValue);
        this.width = Robot.tableLocation.getNumberArray("width", defaultValue);
        this.height = Robot.tableLocation.getNumberArray("height", defaultValue);
        this.solidity = Robot.tableLocation.getNumberArray("solidity", defaultValue);
    }
}
