package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.dt.DTSide;
import org.mort11.subsystems.ee.Shooter;
import org.mort11.util.PIDLoop;

/**
 * SpinUp - Spins up flywheel to a specified RPM
 *
 * @author Seven Kurt <seven.kurt@motsd.org>
 * @author Matthew Krzyzanowski <matthew.krzyzanowski@gmail.com>
 * @author Sahit Chintalapudi <schintalapudi@mort11.org>
 */
public class SpinUp extends Command {
    private Shooter spinUp = Robot.adaptor.shooter;
    private DTSide left = Robot.adaptor.leftSide; //replace/remove with SpinUp stuff
    private Shooter armMotor;
    private PIDLoop pd_arm;
    private boolean PID;
    private Encoder shooter = SensorDealer.getInstance().getLeftDTEncoder();//SensorDealer.getInstance().getShooterEncoder(); replace with SpinUp encoder
    private double speed_ghetto = 0;
    private double velocity;

    public SpinUp(double velocity, boolean PID) {
        this.velocity = velocity;
        requires(left);
        pd_arm = new PIDLoop(velocity, 0.03, 0); // Placeholder values
        this.PID = PID;
    }

    protected void initialize() {
//        DTSide.resetEncoders(); // replace with SpinUp encoder
    }

    protected void execute() {
        if (PID) { //uses pid loop to SpinUp
            double currentVelocity = spinUp.getSpeed();
            System.out.println("speed: " + currentVelocity);
            double speed = pd_arm.getP(currentVelocity);
            spinUp.set(speed);
            SmartDashboard.putNumber("Velocity", currentVelocity);
        } else { // ghetto way of spinning up
            double currentVelocity = spinUp.getSpeed();
            if (currentVelocity < velocity) {
                speed_ghetto += .03;
                System.out.println("speed if: " + speed_ghetto);
                System.out.println("velocity if: " + currentVelocity);
                spinUp.set(speed_ghetto);
            } else {
                speed_ghetto -= .03;
                spinUp.set(speed_ghetto);
                System.out.println("speed else: " + speed_ghetto);
                System.out.println("velocity else: " + currentVelocity);
            }
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
