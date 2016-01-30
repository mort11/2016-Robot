package org.mort11.subsystems.dt;


import org.mort11.PortMap;
import org.mort11.util.DTConstants;

public class DTRight extends DTSide {

    public DTRight() {
        super(PortMap.DT_RIGHT_PORT, PortMap.DT_ENC_RIGHT_A, PortMap.DT_ENC_RIGHT_B, false, false); //placeholder values
        enc.setDistancePerPulse(DTConstants.INCHES_PER_PULSE_RIGHT); // Placeholder value
    }

    public void initDefaultCommand() {

    }
}