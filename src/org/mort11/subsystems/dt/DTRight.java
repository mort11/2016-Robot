package org.mort11.subsystems.dt;


import org.mort11.RobotMap;

public class DTRight extends DTSide {

    public DTRight() {
        super(RobotMap.DT_RIGHT_PORT, RobotMap.DT_ENC_RIGHT_A, RobotMap.DT_ENC_RIGHT_B, false, false); //placeholder values
    }

    public void initDefaultCommand() {

    }
}