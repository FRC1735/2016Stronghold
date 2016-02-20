// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.Stronghold2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1735.Stronghold2016.commands.*;
import org.usfirst.frc1735.Stronghold2016.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	// needed for streaming webcam data to the dashhboard.
	CameraServer server;


    Command autonomousCommand;
    //Create a chooser for Autonomous mode...
    // Allows user to select which autonomous mode to run from the Smart Dashboard
    SendableChooser autoChooser;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Feeder feeder;
    public static Collector collector;
    public static Climber climber;
    public static PDP pDP;
    public static Range range;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // Additional Member variables
    // Simple variable to turn on/off extra debug messages.  Driven by the SmartDashboard.
    public static boolean m_debugOn;

    // High-pass filter.  Any joystick absolute value less than this should be clamped to zero.
    public static double m_joystickFilter = 0.15;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	// experimental.  TODO:  Remove this, as it doesn't happen early enough to impact CommandGroup initialization
    	SmartDashboard.putNumber("autoForwardTime", autoForwardTime);
    	
    	// Camera streaming setup
        server = CameraServer.getInstance();
        server.setQuality(50);
        // originally started streaming here, but this streams even when robot is disabled.  and in autonomous.
        // at a minimum, don't start streaming until we start autonomous.  may want to wait all the way until teleop.


    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        feeder = new Feeder();
        collector = new Collector();
        climber = new Climber();
        pDP = new PDP();
        range = new Range();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // SAT says do not... BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        //SAT:  Don't instantiate the auto-generated command since we replace it with a chooser!
        //autonomousCommand = new AutonomousCommand();

    // SAT says do not... END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        autoChooser = new SendableChooser();
        // Provide the available choices
        autoChooser.addDefault("Do Nothing",  new AutonomousDoNothing());
        autoChooser.addObject("Forward One Second", new AutonomousFwd());
        autoChooser.addObject("Backward One Second", new AutonomousBkw());
        // Add the chooser widget to the dashboard
        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
        
        //Add a preferences widget for storing robot constants
		prefs = Preferences.getInstance();
		// This is the INITIAL and DEFAULT value for the shooter
		shooterStrength = prefs.getDouble("shooterStrength", 1); // second arg is a default if var isn't found
		
		// take the prefs value and put it into a dynamic variable.
		// this allows us to set the DEFAULT value via prefs, but change the value during a match in the SmartDashboard...
		SmartDashboard.putNumber("ShooterStrength", shooterStrength);
 
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	// TODO:  We may need to set the camera server to some kind of mode here
    	// or... might need to stream from the server just like in Teleop.  need to research this.
        // schedule the autonomous command (example)
        // SAT removed: if (autonomousCommand != null) autonomousCommand.start();
    	// We use the Chooser:
    	autonomousCommand = (Command) autoChooser.getSelected();
    	// Some error checkcing in case the chooser is broken:
    	if (autonomousCommand != null) autonomousCommand.start();
    	else System.out.println("ERROR:  Chooser could not find a selected Autonomous Command!  getSelected() returned NULL");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        //dbgPrintln("Enabling Robot!");
        
        // Wait to enable streaming until after autonomous is over
        // to save processing bandwidth.
        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    // this function queries a joystick button to implement a master sensor override capability
    public static boolean isSensorOvrd() {
    	// Manually assigned a joystick button in OI.java to implement a "master override" indication
    	// If pressed, we can use it to override the sensor (e.g. this function returns true)
        return oi.sensorOverride.get();
    }
    
    public static boolean isDbgOn() {
    	return SmartDashboard.getBoolean("Master Debug Enable");
    }
    
    public static void dbgPrintln(String message) {
    	if(isDbgOn()) {
    		System.out.println(message);
    	}
    }
    
    //Instantiate preferences object used with SmartDashboard
    Preferences prefs;
	
    // Variable for holding the shooter strength stored in the prefs widget
	public static double shooterStrength;
	// default/initial time for testing the autonomousVariableFwd command
	public static double autoForwardTime = 1.0;
		
}
