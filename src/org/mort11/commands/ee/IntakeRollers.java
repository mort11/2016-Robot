package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.constants.Constants;
import org.mort11.subsystems.ee.Rollers;

/**
 * Spins the intake rollers in a specified direction
 *
 * @author Matt Turi
 * @author Seven Kurt
 * @author Jakob Shortell
 * @author Ryan O'Toole
 * @author Ryan Thant
 * @author Sahit Chintalapudi
 */
public class IntakeRollers extends Command {
    private Rollers roller = Robot.adaptor.rollers;
    private SubsystemStates.RollerState rollerState;
    private double time = -1;
    private Timer timer = new Timer();

    /**
     * Set the roller up to infinitely run in given direction
     *
     * @param rollerDirection Roller direction
     */
    public IntakeRollers(SubsystemStates.RollerState rollerDirection) {
        requires(roller);
        setInterruptible(true);
        this.rollerState = rollerDirection;
    }

    /**
     * Sets the roller to spin for specified duration in given direction
     *
     * @param duration  Duration to spin for
     * @param direction Direction to spin
     */
    public IntakeRollers(double duration, SubsystemStates.RollerState direction) {
        this(direction);
        this.time = duration;
        this.timer = new Timer();
    }

    @Override
    protected void initialize() {
        timer.start();
    }

    @Override
    protected void execute() {
        switch (rollerState) {
            case INTAKE:
                roller.set(Constants.ROLLER_SPEED);
                break;
            case EXHAUST:
                roller.set(-Constants.ROLLER_SPEED);
                break;
            case STOP:
                roller.set(0);
        }
    }

    @Override
    protected boolean isFinished() {
        return timer.get() > time;
    }

    @Override
    protected void end() {
        if (time != -1) {
            roller.set(0);
        }
    }

    @Override
    protected void interrupted() {
    }
}
