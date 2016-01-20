package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.Pneumatics;
import org.mort11.OI;
import org.mort11.RobotMap;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

    public static OI oi;
    public static final DT dt = new DT();
    public static final Pneumatics piston = new Pneumatics(RobotMap.Pne_Eng1, RobotMap.Pne_Eng2);
    Command autonomousCommand;
    Command DrivePIDCommand;

    public void robotInit() {
        oi = new OI();
        DrivePIDCommand = new DrivePID(120); 
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        if (autonomousCommand != null) DrivePIDCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) DrivePIDCommand.cancel();
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
