package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.subsystems.ee.Pneumatics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
    public static OI oi;
    public static DT dt;
    public static ShootingMechanism intakeArm;
    public static ShootingMechanism ShootMech;
    public static ShootingMechanism intakeRollers;
    public static ShootingMechanism motorToAngle;
    public static Pneumatics piston;
    Command autonomousCommand;
    Command DrivePIDCommand;

    public void robotInit() {
        oi = new OI();
        dt = new DT();
        intakeArm = new ShootingMechanism();
        ShootMech = new ShootingMechanism();
        intakeRollers = new ShootingMechanism();
        motorToAngle = new ShootingMechanism();
        piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
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
    	System.out.println(System.getProperty("user.home"));
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
