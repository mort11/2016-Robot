package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.Pneumatics;
import org.mort11.subsystems.ee.ShootingMechanism;
import org.mort11.commands.ee.IntakeRollers;
import org.mort11.OI;
import org.mort11.RobotMap;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Compressor;

//install mDNS client (check if already installed): http://wpilib.screenstepslive.com/s/4485/m/13503/l/242608-roborio-networking

public class Robot extends IterativeRobot {
	
	public static Compressor comp;
	public static ShootingMechanism ShootMech;
	public static OI oi;
    public static DT dt;
    public static Pneumatics piston;
    public static ShootingMechanism intakeArm;
    Command autonomousCommand;
    Command DrivePIDCommand;

    public void robotInit() {
    	comp = new Compressor(30);
    	piston = new Pneumatics(RobotMap.Pne_Eng1, RobotMap.Pne_Eng2);
        oi = new OI();
        intakeArm = new ShootingMechanism();
        //ShootMech = new ShootingMechanism();
    	//dt = new DT();
        //DrivePIDCommand = new DrivePID(120); 
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
    	comp.start();
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
