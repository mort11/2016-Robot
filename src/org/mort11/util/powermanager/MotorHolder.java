//package org.mort11.util.powermanager;
//
//import org.mort11.util.Loopable;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
///**
// * MotorHolder - Holds the MORTCANTalon objects
// *
// * @author Matt Turi <mturi@mort11.org>
// */
//public class MotorHolder implements Loopable {
//    public static ArrayList<MORTCANTalon> motors = new ArrayList<>();
//
//    @Override
//    public void update() {
//        Collections.sort(motors, (talon1, talon2) -> (int) (talon1.getVoltage() - talon2.getVoltage()));
//    }
//}
