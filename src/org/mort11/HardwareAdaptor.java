package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.DTConstants;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Rollers;
import org.mort11.subsystems.ee.Shooter;

//import org.mort11.subsystems.ee.IntakeBrake;

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
    //    public DoubleSolenoid intakeBrakeSolenoid;
    public DoubleSolenoid hood;

    // Brake mechanism
//    public IntakeBrake intakeBrake;

    // Navigational instruments
    public Accelerometer accelerometer;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;
//    public LED led;

    public HardwareAdaptor() {
        this.pdp = new PowerDistributionPanel();
        this.cam = new Camera();
        this.compressor = new Compressor(HardwareConstants.PCM_ID);
        System.out.println("elec");

//        this.intakeBrakeSolenoid = new DoubleSolenoid(HardwareConstants.PCM_ID, 4, 5); // TODO: 2/16/16 Get ports for intake arm brake
//        System.out.println("break sol");
//        this.intakeBrake = new IntakeBrake();
//        System.out.println("brake done");

        this.rollers = new Rollers();
        System.out.println("rollers");
        this.intakeArm = new IntakeArm();
        System.out.println("arm");
        this.shooter = new Shooter();
        System.out.println("shoot");

        this.shifter = new DoubleSolenoid(HardwareConstants.PCM_ID, DTConstants.DT_LOW_SHIFTER_PORT, DTConstants.DT_HIGH_SHIFTER_PORT);
        this.hood = new DoubleSolenoid(HardwareConstants.PCM_ID, 6, 7); // TODO: 2/16/16  Get ports for hood popper
        System.out.println("stuff");

        this.accelerometer = new BuiltInAccelerometer();
        System.out.println("Accel" +
                "");
        this.leftSide = new DTLeft();
        this.rightSide = new DTRight();
        System.out.println("dt");
//        this.led = new LED();
    }
}
