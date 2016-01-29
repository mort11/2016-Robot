package org.mort11;

import org.mort11.commands.DrivePID;
import org.mort11.commands.ExampleCommand;
import org.mort11.subsystems.DT;
import org.mort11.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static OI oi;
    public static final DT dt = new DT();
    Command autonomousCommand;
    Command DrivePIDCommand;
    Accelerometer accel;
    
    public void robotInit() {
        oi = new OI();
        accel = new BuiltInAccelerometer();
        autonomousCommand = new ExampleCommand();
        //DrivePIDCommand = new DrivePID(120); 
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        //if (autonomousCommand != null) DrivePIDCommand.start();
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
