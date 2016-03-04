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
import org.usfirst.frc1735.Stronghold2016.Robot;

/**
 *
 */
public class EngageAutoShooter extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public EngageAutoShooter() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// This calculates range, and spins up the shooter using the PID controllers to the appropriate speed
    	Robot.shooter.engageAutoShooter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// We wait until both Left and Right PIDs have achieved their target
    	
        boolean leftIsFinished = Robot.shooterLeftPID.onTarget();
        boolean rightIsFinished = Robot.shooterRightPID.onTarget();
        
        return (leftIsFinished && rightIsFinished);
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Do not spin down or disable the PID loop.  Let it keep running so we can feed a ball into it.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// However, if interrupted by another shooter command... disable the PIDs?
    	Robot.shooterLeftPID.disable();
    	Robot.shooterRightPID.disable();
    }
}
