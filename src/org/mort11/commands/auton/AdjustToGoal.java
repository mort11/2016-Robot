package org.mort11.commands.auton;
import edu.wpi.first.wpilibj.command.Command;
import org.mort11.HardwareAdaptor;
import org.mort11.Robot;
import org.mort11.subsystems.Camera;
import org.mort11.subsystems.dt.DTSide;
/**
 * AdjustToGoal - Adjust robot to goal
 *
 * @author Sahit Chintalapudi
 */
public class AdjustToGoal extends Command {
    private boolean isFinished = false;
    private DTSide left = Robot.adaptor.leftSide;
    private DTSide right = Robot.adaptor.rightSide;
    public AdjustToGoal() {
        requires(left);
        requires(right);
    }
    protected void initialize() {
//        Robot.adaptor.ahrs.zeroYaw();
    }
    protected void execute() {
        if (Robot.table.getNumberArray("centerX", new double[]{}).length == 0) {
            System.out.println("Not found");
            this.isFinished = true;
            return;
        }
//        for(double area : adaptor.areas){
//        	if(area > adaptor.areas[1]){
//        		
//        	}
//        }
        System.out.println("Centering"); 
        if (Robot.table.getNumberArray("centerX", new double[]{})[0] < 150) {
            this.left.set(-0.25);
            this.right.set(0.25);
            System.out.println("Too far right");
        } else if (Robot.table.getNumberArray("centerX", new double[]{})[0] > 170) {
            this.left.set(0.25);
            this.right.set(-0.25);
            System.out.println("Too far left");
        } else {
            System.out.println("Centered");
            this.isFinished = true;
        }
    }
    protected boolean isFinished() {
        return this.isFinished;
    }
    protected void end() {
    }
    protected void interrupted() {
    }
}