package org.mort11.util;

public class DTConstants {
		//copypasta from 2015
	    public static final double DT_P = 0.3;
	    public static final double WHEEL_RADIUS_INCHES = 3; //from kop robot 
	    public static final int ENC_CPR_LEFT = 2372;//2438; //tick count from kop robot on left side, ports 2,3
	    public static final int ENC_CPR_RIGHT = -2438;//2372; //tick count from kop robot on right side, ports 0,1
	    public static final double INCHES_PER_PULSE_LEFT = 6 * Math.PI / ENC_CPR_LEFT;
	    public static final double INCHES_PER_PULSE_RIGHT = 6 * Math.PI / ENC_CPR_RIGHT;

}
