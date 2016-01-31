package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ee.Shooter;
import org.mort11.subsystems.ee.Pneumatics;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	public static Compressor comp;
    public static OI oi;
    public static DT dt;
    public static Shooter intakeArm;
    public static Shooter ShootMech;
    public static Shooter intakeRollers;
    public static Pneumatics piston;
    Command autonomousCommand;
    Command DrivePIDCommand;
    Accelerometer accel;
    
    public void robotInit() {
    	
    	piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        comp = new Compressor(30); 
        accel = new BuiltInAccelerometer();
        //intakeArm = new Shooter();
        ShootMech = new Shooter();
        piston = new Pneumatics(RobotMap.PNE_ENG1, RobotMap.PNE_ENG2);
        DrivePIDCommand = new DrivePID(120);
        oi = new OI();
        
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        System.out.print("accel output x " + accel.getX()  + "   ");
        System.out.print("accel output y " + accel.getY()  + "   ");
        System.out.println("accel output z " + accel.getZ());
        Timer.delay(0.5);
    }

    public void teleopInit() {
    	System.out.println(System.getProperty("user.home"));
        //if (autonomousCommand != null) DrivePIDCommand.cancel();
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
