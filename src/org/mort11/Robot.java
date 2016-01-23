package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.mort11.commands.DisplayCurrents;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.dt.DTLeft;
import org.mort11.subsystems.dt.DTRight;

public class Robot extends IterativeRobot {
    public static final DT dt = new DT();
    public static OI oi;
    public static DTLeft leftSide;
    public static DTRight rightSide;
    Command autonomousCommand;
    Command DriveStraight;
    Command DispCurrent;
    public void robotInit() {
        oi = new OI();
        leftSide = new DTLeft();
        rightSide = new DTRight();
        DispCurrent = new DisplayCurrents();
        DriveStraight = new DriveStraight(10);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        if (autonomousCommand != null) {
        	DriveStraight.start();
        	DispCurrent.start();
        }
        
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	System.out.println(System.getProperty("user.home"));
        if (autonomousCommand != null) {
        	DriveStraight.cancel();
        	DispCurrent.cancel();
        }
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
