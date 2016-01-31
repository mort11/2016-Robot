package org.mort11.subsystems.dt;


import org.mort11.PortMap;
import org.mort11.util.DTConstants;

public class DTLeft extends DTSide {

    public DTLeft() {
        super(PortMap.DT_LEFT_PORT, PortMap.DT_ENC_LEFT_A, PortMap.DT_ENC_LEFT_B, false, false); //placeholder values
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE_LEFT); // Placeholder value        
    }

    public void initDefaultCommand() {

    }
}

