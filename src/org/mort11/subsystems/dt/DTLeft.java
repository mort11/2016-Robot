package org.mort11.subsystems.dt;

import edu.wpi.first.wpilibj.Encoder;
import org.mort11.commands.dt.DriveLinearLeft;
import org.mort11.constants.Constants;

/**
 * DTLeft - Subsystem controlling left drivetrain side
 *
 * @author gridbug
 * @author Matt Turi
 * @author Matthew Krzyzanowski
 * @author Jeffrey Pastilha
 */
public class DTLeft extends DTSide {
    public DTLeft(Encoder encoder) {
        super(
                Constants.DT_LEFT_TALON_ID_1,
                Constants.DT_LEFT_TALON_ID_2,
                Constants.DT_LEFT_TALON_ID_3,
                Constants.PDP_LEFT_DT_1,
                Constants.PDP_LEFT_DT_2,
                Constants.PDP_LEFT_DT_3,
                true, true, true,
                encoder
        );
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearLeft());
    }
}

