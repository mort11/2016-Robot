package org.mort11;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.commands.auton.ObstacleAuton;
import org.mort11.commands.auton.TwoBallDump;
import org.mort11.commands.auton.positional.Pos4Auton;
import org.mort11.commands.ee.IndexerToggle;
import org.mort11.commands.ee.SpoolFlywheel;

/**
 * Main Robot class
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 * @author Ryan Thant
 * @author Matthew Krzyzanowski
 * @author Jakob Shortell
 * @author Seven Kurt
 * @author Michael Kozak
 * @author Jeffrey Pastilha
 * @author Ryan O'Toole
 * @author Carl Hausman
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static HardwareAdaptor adaptor = new HardwareAdaptor();
    public static NetworkTable table;
    private Command autonomousCommand = new WaitCommand(0);
    private SendableChooser autoModes;

    @Override
    public void robotInit() {
        oi = new OI();

        // Have operator choose autonomous mode
        autoModes = new SendableChooser();
        autoModes.addDefault("Do Nothing", new WaitCommand(1));
        autoModes.addObject("Drive over obstacle", new ObstacleAuton());
        autoModes.addObject("Two Ball Dump", new TwoBallDump());
        autoModes.addObject("Pos 4 Shot", new Pos4Auton());

        SmartDashboard.putData("Auto Mode", autoModes);
        SmartDashboard.putString("RPM", "STANDBY");
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        adaptor.leftDTEncoder.reset();
        adaptor.rightDTEncoder.reset();
        adaptor.ahrs.zeroYaw();
        autonomousCommand = (Command) autoModes.getSelected();
        autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        adaptor.ahrs.zeroYaw();
        autonomousCommand.cancel();
        new IndexerToggle().start();
    }

    @Override
    public void disabledInit() {
        new SpoolFlywheel(0, true).start();
    }


    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
