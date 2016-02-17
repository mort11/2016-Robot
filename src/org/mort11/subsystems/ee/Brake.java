package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.mort11.commands.ee.PistonActuation;
import org.mort11.util.MORTSubsystem;

/**
 * Pneumatics - Does pneumatic things
 *
 * @author Ryan Thant <ryanthant1@gmail.com>
 * @author Seven Kurt <seven.kurt@motsd.org>
 */
public class Brake extends Subsystem implements MORTSubsystem {
    DoubleSolenoid solenoid;
    boolean engaged;
    boolean isDisabled;

<<<<<<< HEAD:src/org/mort11/subsystems/ee/Pneumatics.java
    public Pneumatics(int engagedPort, int notEngagedPort) {
<<<<<<< HEAD
<<<<<<< HEAD
        solenoid = new DoubleSolenoid(engagedPort, notEngagedPort);
=======
        sol = new DoubleSolenoid(30,engagedPort, notEngagedPort);
>>>>>>> 830d2dde43e2d35cf89f028ef516f6ec618b4fa7
=======
=======
    public Brake(int engagedPort, int notEngagedPort) {
>>>>>>> 60dd0b09bbbcdeb91e63404ddf2dfc76cc2a939f:src/org/mort11/subsystems/ee/Brake.java
        solenoid = new DoubleSolenoid(30, engagedPort, notEngagedPort);
>>>>>>> 419908c65a86f490a546220fe1565cd093d66b4e
        engaged = false;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new PistonActuation());
    }
//if engaged set solenoid to kForward
    public void setSolenoid(boolean engage) {
        if (isDisabled == false) {
            if (engage) {
                solenoid.set(DoubleSolenoid.Value.kForward);
            } else {
                solenoid.set(DoubleSolenoid.Value.kReverse);
            }
            engaged = engage;
            System.out.println("state 2: " + engage);
        }
    }

    public boolean isEngaged() {
        return engaged;
    }

    public void disable() {
        isDisabled = true;
    }
}
