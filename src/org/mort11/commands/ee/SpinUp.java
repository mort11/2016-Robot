package org.mort11.commands.ee;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import org.mort11.Robot;
import org.mort11.sensors.SensorDealer;
import org.mort11.subsystems.ee.Shooter;
import org.mort11.util.PIDLoop;
/**
 *
 */
public class SpinUp extends Command {
	private Shooter armMotor;
    private PIDLoop pd_arm;
    private Encoder shooter = SensorDealer.getInstance().getShooterEncoder();
    
    public SpinUp(double velocity) {
    	requires(armMotor);
    pd_arm	= new PIDLoop(velocity,.01,0); // Placeholder values

    }
    protected void initialize() {
    	
    }

    protected void execute() {
    	 double currentVelocity = shooter.getRate();

         double speed = pd_arm.getP(currentVelocity);

         armMotor.set(speed);

         SmartDashboard.putNumber("Velocity", currentVelocity);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    }

    protected void interrupted() {
    }
}
