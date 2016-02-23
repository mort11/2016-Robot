package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.mort11.commands.auton.DriveArc;
import org.mort11.commands.auton.DriveStraight;
import org.mort11.commands.auton.LowBarLowGoal;
import org.mort11.commands.auton.WaitTime;
import org.mort11.commands.ee.MotorToAngle;
import org.mort11.commands.ee.SpinUp;
import org.mort11.commands.led.LEDControl;
import org.mort11.util.auto.AutoCommand;

/**
 * Robot - Main Robot class
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 * @author Ryan Thant
 * @author Matthew Krzyzanowski
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Jeffrey Pastilha
 * @author Ryan O'Toole
 * @author Carl Hausman
 * @author Jakob Shortell
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();

    Command autonomousCommand;
    SendableChooser autoModes;
    SendableChooser portcullis;

    @Override
    public void robotInit() {
        oi = new OI();

        // Have operator choose autonomous mode
        autoModes = new SendableChooser();
        autoModes.addDefault("Do Nothing for 10s", new WaitTime(10));
        autoModes.addObject("Drive Straight [20in.]", new DriveStraight(20));
        autoModes.addObject("Drive Arc [Unknown units]", new DriveArc(1.33 * Math.PI, 0.5 * Math.PI));

        portcullis = new SendableChooser();
        // TODO: 2/21/2016 Write auto commands for these
        portcullis.addDefault("Portcullis", new WaitTime(0));
        portcullis.addObject("No Portcullis", new WaitTime(0));

        SmartDashboard.putData("Auto Mode", autoModes);
        SmartDashboard.putData("Portcullis", portcullis);
        SmartDashboard.putString("RPM", "too slow!");
        
        
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
//        System.out.println("STARTING AUTONOMOUS");
//        Command[] autoCommands = new Command[]{(Command) autoModes.getSelected(), (Command) portcullis.getSelected()};
//        autonomousCommand = new AutoCommand(autoCommands);
//
//        System.out.println("Running auto commands:");
//        for (Command autoCommand : autoCommands) {
//            System.out.println(autoCommand);
//        }
    	autonomousCommand = new LowBarLowGoal();
        autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
       //new MotorToAngle(90).start();
    	//new SpinUp(98000, true).start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void disabledInit() {
    	new SpinUp(0,true).start();
        System.out.println("Disabled. Code halted!");
    }

    @Override
    public void teleopPeriodic() {

        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
        System.out.println("Starting test mode...");
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
