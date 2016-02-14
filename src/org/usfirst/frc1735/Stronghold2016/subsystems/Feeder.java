// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016.subsystems;

import org.usfirst.frc1735.Stronghold2016.RobotMap;
import org.usfirst.frc1735.Stronghold2016.commands.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Feeder extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DigitalInput ballReady = RobotMap.feederballReady;
    private final SpeedController roller = RobotMap.feederRoller;
    private final SpeedController limboBar = RobotMap.feederLimboBar;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void collectBall(double magnitudeDirection) {
    	// when collecting the ball we run both the roller and limbo bar
    	RobotMap.feederRoller.set(magnitudeDirection);
    	RobotMap.feederLimboBar.set(magnitudeDirection);
    }
    public void loadShooter(double magnitudeDirection) {
    	// when loading the shooter we run only the limbo bar
    	RobotMap.feederLimboBar.set(magnitudeDirection);
    }
    public void stop() {
    	RobotMap.feederRoller.set(0);
    	RobotMap.feederLimboBar.set(0);
    }
}

