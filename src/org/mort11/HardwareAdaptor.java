package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.dt.DT;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.ee.Intake;
import org.mort11.subsystems.ee.IntakeArm;
import org.mort11.subsystems.ee.Shooter;

/**
 * HardwareAdaptor - Instantiation of most subsystems, system hardware, and misc.
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class HardwareAdaptor {
    private static HardwareAdaptor instance;
    // System components
    public PowerDistributionPanel pdp;
    public Compressor compressor;
    // End Effector mechanisms
    public IntakeArm intakeArm;
    public Intake intakeRollers;
    public Shooter shooter;
    // Navigational instruments
    public Accelerometer accelerometer;
    // Motors
    public DTLeft leftSide;
    public DTRight rightSide;
    public DT dt;

    private HardwareAdaptor() {
        pdp = new PowerDistributionPanel();
        compressor = new Compressor(HardwareConstants.PCM_ID);

        intakeArm = new IntakeArm();
        intakeRollers = new Intake();

        accelerometer = new BuiltInAccelerometer();

        leftSide = new DTLeft();
        rightSide = new DTRight();
        dt = new DT();
    }

    public static HardwareAdaptor getInstance() {
        if (instance == null) {
            instance = new HardwareAdaptor();
        }
        return instance;
    }
}
