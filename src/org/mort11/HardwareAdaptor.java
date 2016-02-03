package org.mort11;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import org.mort11.constants.HardwareConstants;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.Shooter;

/**
 * HardwareAdaptor - Instantiation of most subsystems, system hardware, and misc.
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class HardwareAdaptor {
    // System components
    public static PowerDistributionPanel pdp = new PowerDistributionPanel();
    public static Compressor compressor = new Compressor(HardwareConstants.PCM_ID);

    // End Effector mechanisms
    public static Shooter intakeArm = new Shooter();
    public static Shooter intakeRollers = new Shooter();
    public static Shooter shooter = new Shooter();

    // Navigational instruments
    public static Accelerometer accelerometer = new BuiltInAccelerometer();

    // Motors
    public static DTSide leftSide;
    public static DTSide rightSide;

    private static HardwareAdaptor instance;

    public static HardwareAdaptor getInstance() {
        if (instance == null) {
            return new HardwareAdaptor();
        }
        return instance;
    }

    public HardwareAdaptor() {
        leftSide = new DTLeft();
        rightSide = new DTRight();
    }
}
