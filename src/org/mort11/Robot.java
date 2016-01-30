package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.subsystems.ee.Pneumatics;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	public static Compressor comp;
    public static OI oi;
    public static DT dt;
    public static ShootingMechanism intakeArm;
    public static ShootingMechanism ShootMech;
    public static ShootingMechanism intakeRollers;
    public static Pneumatics piston;
    Command autonomousCommand;
    Command DrivePIDCommand;

    public void robotInit() {
    	
    	piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        //dt = new DT();
        comp = new Compressor(30);
       //intakeArm = new ShootingMechanism();
       //ShootMech = new ShootingMechanism();
        //intakeRollers = new ShootingMechanism();
        //DrivePIDCommand = new DrivePID(120); 
        oi = new OI();
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
