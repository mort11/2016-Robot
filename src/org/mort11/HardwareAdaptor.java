package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.DTConstants;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.LED;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.IntakeBrake;
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

    // Brake mechanism
    public IntakeBrake intakeBrake;

    // Navigational instruments
    public Accelerometer accelerometer;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;
    public LED led;

    public HardwareAdaptor() {
        this.pdp = new PowerDistributionPanel();
        this.cam = new Camera();
        this.compressor = new Compressor(HardwareConstants.PCM_ID);

        this.rollers = new Rollers();
        this.intakeArm = new IntakeArm();
        this.shooter = new Shooter();

        this.shifter = new DoubleSolenoid(HardwareConstants.PCM_ID, DTConstants.DT_LOW_SHIFTER_PORT, DTConstants.DT_HIGH_SHIFTER_PORT);
        this.intakeBrakeSolenoid = new DoubleSolenoid(HardwareConstants.PCM_ID, 0, 0); // TODO: 2/16/16 Get ports for intake arm brake
        this.hood = new DoubleSolenoid(HardwareConstants.PCM_ID, 1, 1); // TODO: 2/16/16  Get ports for hood popper

        this.accelerometer = new BuiltInAccelerometer();

        this.leftSide = new DTLeft();
        this.rightSide = new DTRight();
        this.led = new LED();
    }
}
