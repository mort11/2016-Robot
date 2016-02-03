package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.Intake;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Shooter;

/**
 * HardwareAdaptor - Instantiation of most subsystems, system hardware, and misc.
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class HardwareAdaptor {
    // System components
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;

    // End Effector mechanisms
    public static IntakeArm intakeArm;
    public static Intake intakeRollers;
    public static Shooter shooter;

    // Navigational instruments
    public static Accelerometer accelerometer;

    // Motors
    public static DTLeft leftSide;
    public static DTRight rightSide;

    public HardwareAdaptor() {
        pdp = new PowerDistributionPanel();
        compressor = new Compressor(HardwareConstants.PCM_ID);

        intakeArm = new IntakeArm();
        intakeRollers = new Intake();

        accelerometer = new BuiltInAccelerometer();

        leftSide = new DTLeft();
        rightSide = new DTRight();
    }
}
