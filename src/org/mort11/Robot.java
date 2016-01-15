package org.mort11;

import org.mort11.subsystems.DTLeft;
import org.mort11.subsystems.DTRight;
import org.mort11.subsystems.DTSide;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import org.mort11.commands.ExampleCommand;
//import org.mort11.subsystems.ExampleSubsystem;

public class Robot extends IterativeRobot {
//    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static OI oi;
    public static DTLeft leftSide;
    public static DTRight rightSide;

    Command autonomousCommand;

    public void robotInit() {
        oi = new OI();
        leftSide = new DTLeft();
        rightSide = new DTRight();
//        autonomousCommand = new ExampleCommand();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit() {

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
