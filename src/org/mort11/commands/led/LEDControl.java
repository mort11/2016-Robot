//package org.mort11.commands.led;
//
//import edu.wpi.first.wpilibj.command.Command;
//import org.mort11.Robot;
//import org.mort11.subsystems.LED;
//
///**
// * LEDControl - Controls the LED's
// *
// * @author Jakob Shortell <jshortell@mort11.org>
// * @author Seven Kurt <seven.kurt@motsd.org>
// */
//public class LEDControl extends Command {
//
//    Light light;
//    LED led = Robot.adaptor.led;
//
//    public LEDControl(Light light) {
//        this.light = light;
//    }
//
//    protected void initialize() {
//    }
//
//    protected void execute() {
//        switch (light) {
//            case RED:
//                led.set(255, 0, 0);
//                break;
//            case ORANGE:
//                led.set(255, 165, 0);
//                break;
//            case YELLOW:
//                led.set(255, 255, 0);
//                break;
//            case GREEN:
//                led.set(0, 255, 0);
//                break;
//            case BLUE:
//                led.set(0, 0, 255);
//                break;
//            case INDIGO:
//                led.set(75, 0, 130);
//                break;
//            case VIOLET:
//                led.set(238, 130, 238);
//                break;
//
//        }
//    }
//
//    protected boolean isFinished() {
//        return false;
//    }
//
//    protected void end() {
//    }
//
//    protected void interrupted() {
//    }
//
//    public enum Light {
//        RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
//    }
//}
