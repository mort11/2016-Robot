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
import org.mort11.util.auto.AutoCommand;

/**
 * Robot - Main Robot class
 *
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 * @author Matt Turi <mturi@mort11.org>
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Michael Kozak <michael.kozak@motsd.org>
 * @author Jeffrey Pastilha <jpmail967@yahoo.com>
 * @author Ryan O'Toole <ryan.otoole@motsd.org>
 * @author Carl Hausman <carl@hausman.org>
 * @author Jakob Shortell <jshortell@mort11.org>
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();

    Command autonomousCommand;
    SendableChooser autoModes;
    SendableChooser portcullis;

    // TODO: 2/11/16 Check MAX and MIN-REENABLE voltage values
//    Looper pdpMonitor = new Looper("PDPMonitor", new PDPUpdater(), 1 / 200.0); // Update PDP monitor every 20ms

    @Override
    public void robotInit() {
        oi = new OI();

        // Start loops
//        pdpMonitor.start();

        // Have operator choose autonomous mode
        autoModes = new SendableChooser();
        autoModes.addDefault("Do Nothing for 10s", new WaitTime(10));
        autoModes.addObject("Drive Straight [20in.]", new DriveStraight(20));
        autoModes.addObject("Drive Arc [Unknown units]", new DriveArc(1.33 * Math.PI, 0.5 * Math.PI));

        portcullis = new SendableChooser();
        portcullis.addDefault("Portcullis", new WaitTime(0));
        portcullis.addObject("No Portcullis", new WaitTime(0));
        
        SmartDashboard.putData("Auto Mode", autoModes);
        SmartDashboard.putData("Portcullis", portcullis);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        System.out.println("STARTING AUTONOMOUS");
        //Command[] autoCommands = new Command[]{(Command) autoModes.getSelected(), (Command) portcullis.getSelected()};
        autonomousCommand = new LowBarLowGoal();

        System.out.println("Running auto commands:");
//        for (Command autoCommand : autoCommands) {
//            System.out.println(autoCommand);
//        }
        autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        //new MotorToAngle(90).start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void disabledInit() {
        // Stop loopable threads
//        pdpMonitor.stop();
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
