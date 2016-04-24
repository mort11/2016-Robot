package org.mort11.subsystems.ee;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.mort11.Robot;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.ee.IndexerToggle;

/**
 * IndexerToggle - Intake indexers
 *
 * @author Sahit Chintalapudi
 * @author Matt Turi
 * @author Jakob Shortell
 * 
 */
public class Indexers extends Subsystem {
    private static DoubleSolenoid solenoid = Robot.adaptor.indexers;
    private static boolean indexerUp = false;

    public static void setIndexer(SubsystemStates.IndexerRequest IndexerRequest) {
        switch (IndexerRequest) {
            case UP:
                solenoid.set(DoubleSolenoid.Value.kReverse);
                break;
            case DOWN:
                solenoid.set(DoubleSolenoid.Value.kForward);
                break;
        }
    }

    public static void IndexerUp() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public static void IndexerDown() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public static void toggleIndexer() {
    	System.out.println("new state is: " + indexerUp);
        if (indexerUp) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        } else {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        indexerUp = !indexerUp;
    }

    @Override
    protected void initDefaultCommand() {
    	setDefaultCommand(new IndexerToggle());
    }
}

