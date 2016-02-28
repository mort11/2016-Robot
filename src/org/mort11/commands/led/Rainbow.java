package org.mort11.commands.led;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.mort11.commands.led.LEDControl;
import org.mort11.commands.SubsystemStates;
import org.mort11.commands.led.WaitForColorChange;
/**
 *
 */
public class Rainbow extends CommandGroup {
    public  Rainbow() {
    	addSequential(new LEDControl(SubsystemStates.Light.RED));
//    	System.out.println("Red");
    	addSequential(new WaitForColorChange(1));
    	addSequential(new LEDControl(SubsystemStates.Light.ORANGE));
//    	System.out.println("Orange");
    	addSequential(new WaitForColorChange(1));
    	addSequential(new LEDControl(SubsystemStates.Light.YELLOW));
//    	System.out.println("Yellow");
    	addSequential(new WaitForColorChange(1));
    	addSequential(new LEDControl(SubsystemStates.Light.GREEN));
//    	System.out.println("Green");
    	addSequential(new WaitForColorChange(1));
    	addSequential(new LEDControl(SubsystemStates.Light.BLUE));
//    	System.out.println("Blue");
    	addSequential(new WaitForColorChange(1));
    	addSequential(new LEDControl(SubsystemStates.Light.PURPLE));
//    	System.out.println("Purple");
    	addSequential(new WaitForColorChange(1));
    }
}
