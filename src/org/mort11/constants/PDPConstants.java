package org.mort11.constants;

/**
 * PDPConstants - Motors mapped to their corresponding PDP channel
 *
 * @author Matt Turi <mturi@mort11.org>
 */
public class PDPConstants {
    // TODO: 2/3/16 Get all PDP slots. These are all incorrect

    public static final int LEFT_DT_1 = 0;
    public static final int LEFT_DT_2 = 1;
    public static final int LEFT_DT_3 = 2;

    public static final int RIGHT_DT_1 = 3;
    public static final int RIGHT_DT_2 = 4;
    public static final int RIGHT_DT_3 = 5;

    public static final int INTAKE_ARM = 6;

    public static final int FLYWHEEL = 7;

    public static final int ROLLERS = 8;

    // TODO: 2/10/16 Get appropriate current limits
    public static final double MOTOR_MAX_VOLTAGE = 6;
    public static final double MOTOR_MIN_REENABLE_VOLTAGE = 4;
}
