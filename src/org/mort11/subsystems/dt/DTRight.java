package org.mort11.subsystems.dt;

import org.mort11.commands.dt.DriveLinearRight;
import org.mort11.constants.Constants;

/**
 * DTRight - Subsystem controlling right drivetrain side
 *
 * @author gridbug <wmarshall@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 */
public class DTRight extends DTSide {
    public DTRight() {
        super(Constants.DT_RIGHT_TALON_ID_1, Constants.DT_RIGHT_TALON_ID_2,
                Constants.DT_RIGHT_TALON_ID_3, Constants.RIGHT_DT_1, Constants.RIGHT_DT_2, Constants.RIGHT_DT_3,
                false, false, false);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveLinearRight());
    }
}