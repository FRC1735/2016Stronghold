// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1735.Stronghold2016.subsystems.*;

/**
 *
 */
public class AutonomousDefenseReach extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousDefenseReach() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    	// Initial state:  facing backwards directly in front of (any) defense
    	// 1) Drive backwards long enough to get TO the defense (A "Reach")
        addSequential(new DriveWithLimits(1000, 1.5, -0.75, false, false)); // distance, time, mag, left, right

    } 
}
