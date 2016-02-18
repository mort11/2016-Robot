//package org.mort11.subsystems.ee;
//
//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import org.mort11.Robot;
//
///**
// * IntakeBrake - Breaking mechanism for the intake arm
// *
// * @author Ryan Thant <ryanthant1@gmail.com>
// * @author Seven Kurt <seven.kurt@motsd.org>
// * @author Matt Turi <mturi@mort11.org>
// */
//public class IntakeBrake extends Subsystem {
//    public boolean intakeBrakeEngaged = false;
//
//    @Override
//    public void initDefaultCommand() {
//    }
//
//    /**
//     * Engage brake
//     */
//    public void engage() {
//        Robot.adaptor.intakeBrakeSolenoid.set(DoubleSolenoid.Value.kForward);
//        this.intakeBrakeEngaged = true;
//    }
//
//    /**
//     * Disengage brake
//     */
//    public void disengage() {
//        Robot.adaptor.intakeBrakeSolenoid.set(DoubleSolenoid.Value.kReverse);
//        this.intakeBrakeEngaged = false;
//    }
//}
