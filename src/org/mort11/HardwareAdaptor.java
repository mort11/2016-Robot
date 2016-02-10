package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Pneumatics;
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
    public IntakeArm intake;
    public Shooter shooter;
    public Pneumatics piston;

    // Navigational instruments
    public Accelerometer accelerometer;

    // Subsystems
    public DTSide leftSide;
    public DTSide rightSide;

    public HardwareAdaptor() {
        pdp = new PowerDistributionPanel();
        cam = new Camera();
        compressor = new Compressor(HardwareConstants.PCM_ID);

        rollers = new Rollers();
        intake = new IntakeArm();
        shooter = new Shooter();

        accelerometer = new BuiltInAccelerometer();

        leftSide = new DTLeft();
        rightSide = new DTRight();
    }
}
