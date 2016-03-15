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

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1735.Stronghold2016.subsystems.*;

/**
 *
 */
public class AutonomousDefenseCross extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousDefenseCross() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
    	
    	// Initial state:  facing backwards directly in front of (any) defense
    	
    	// 1) Deploy the collector so we can go under the lowbar (backwards)
    	// But can't do that first thing because we will cross the midline and get a tech foul.
    	// So, drive forward for a moment to clear the line
    	addSequential(new DriveWithLimits(1000, 0.5, -0.75, false, false)); // distance, time, mag, left, right
    	addSequential(new DeployCollector(.5), 0.25); // second arg to addSequential is an optional timeout in seconds

    	// 2) Drive backwards long enough to definitely get past the defense and into the courtyard (a "Cross" or "Breach")
    	// Note that this might drive too long and plow into the far wall...
    	// we could activate the rangefinder part of the DriveWithLimits and stop the motors if distance gets less than 4 feet
    	addSequential(new DriveWithLimits(1000, 4.5, -0.75, false, false)); // distance, time, mag, left, right
/* For experimentation at some point...
        Command cmd = new DriveWithLimits(4, 5, -0.75, false, false); // drive to 4' from obstacle (using override below!)
        DriveWithLimits driveCmd = (DriveWithLimits)cmd; // cast command to implemented type so we an access variables
        driveCmd.m_useDistanceFromObstacle = true; // override the default for this one instance of the command
        addSequential(driveCmd);
*/
        
        
    } 
}
