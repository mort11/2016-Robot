
package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.PortMap;
import org.mort11.util.EEConstants;


public class ShootingMechanism extends Subsystem {

    private Talon ArmMotor;
    private DigitalInput LimSwitch;
    private Encoder ArmEnc;
    private AnalogPotentiometer ArmPot;

    public ShootingMechanism() {
        ArmMotor = new Talon(PortMap.ARM_TAL_1);
        LimSwitch = new DigitalInput(PortMap.ARM_LIM);
        ArmEnc = new Encoder(PortMap.ARM_ENC_1, PortMap.ARM_ENC_2, true, EncodingType.k4X);
        ArmPot = new AnalogPotentiometer(PortMap.ARM_POT);
        ArmEnc.reset();
        ArmEnc.setDistancePerPulse(EEConstants.INCHES_PER_PULSE);
    }

    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        ArmMotor.set(speed);
    }

    public boolean isLimSwitch() {
        return LimSwitch.get();
    }

    public double getDistance() {
        return ArmEnc.getDistance();
    }

    public double getAngle() {
        return ArmPot.get();
    }


}
