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
        DriveStraight = new DriveStraight(100);
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("auton initting");
        if (DriveStraight != null) {
        	DriveStraight.start();
        	DispCurrent.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (DriveStraight != null) {
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
