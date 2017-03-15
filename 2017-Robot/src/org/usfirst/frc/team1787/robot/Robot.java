package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Joystick joystick_right;
	Joystick joystick_left;
	
	DriveTrain driveTrain;
	PickupArm pickupArm;
	Winch winch;
	Shooter shooter;
	
	Preferences prefs;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Create the joysticks
		joystick_right = new Joystick(Constants.JOYSTICKS.JOYSTICK_RIGHT_ID);
		joystick_left = new Joystick(Constants.JOYSTICKS.JOYSTICK_LEFT_ID);
		
		// Create the different mechanisms
		driveTrain = new DriveTrain(Constants.MOTORS.MOTOR_DRIVE_FRONT_RIGHT, Constants.MOTORS.MOTOR_DRIVE_REAR_RIGHT,
									Constants.MOTORS.MOTOR_DRIVE_FRONT_LEFT, Constants.MOTORS.MOTOR_DRIVE_REAR_LEFT,
									Constants.PNEUMATICS.PCM_SHIFTER_ID);
		
		pickupArm = new PickupArm(Constants.PNEUMATICS.PCM_PICKUP_ARM_DEPLOYED_ID, Constants.PNEUMATICS.PCM_PICKUP_ARM_RETRACTED_ID, Constants.MOTORS.MOTOR_PICKUP_ARM_SPINNER);
		
		winch = new Winch(Constants.MOTORS.MOTOR_WINCH);
		
		shooter = new Shooter(Constants.MOTORS.MOTOR_SHOOTER_FEEDER, Constants.MOTORS.MOTOR_SHOOTER_TURRET, Constants.MOTORS.MOTOR_SHOOTER_FLYWHEEL, Constants.ANALOG.GYRO_ID);
		
		prefs = Preferences.getInstance();
		
		//shooter.setPID(prefs.getDouble("TURRET_HORIZONTAL_P", 0), prefs.getDouble("TURRET_HORIZONTAL_I", 0), prefs.getDouble("TURRET_HORIZONTAL_D", 0));
	}

	/**
	 * This function is run when preparing for autonomous
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
	}

	
	public void teleopInit() {
		

		
	}
	
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		///
		// Drive Control
		///
		
		// Drive with the joystick being pushed more
		if (joystick_right.getMagnitude() > joystick_left.getMagnitude())
			driveTrain.driveForwards(joystick_right);
		else
			driveTrain.driveBackwards(joystick_left);
		
		// Shifter
		driveTrain.setGear(joystick_right.getRawAxis(Constants.MISC.AXIS_SLIDER));
		
		///
		// Pickup Arm
		///
		
		// Deploying and retracting
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_PICKUP_ARM_DEPLOY))
			pickupArm.deploy();
		else if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_PICKUP_ARM_RETRACT))
			pickupArm.retract();
		
		// Intake
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_PICKUP_ARM_INAKE))
			pickupArm.intake();
		else
			pickupArm.stopIntake();
		
		
		///
		// Winch
		///
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_WINCH_CLIMB))
			winch.climb();
		else if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_WINCH_UNCLIMB))
			winch.unclimb();
		else
			winch.stop();
		
	
		///
		// Turret
		///
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FEEDER))
			turret.spinFeeder();
		else
			turret.stopFeeder();
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FLYWHEEL))
			turret.spinFlywheel();
		else
			turret.stopFlywheel();
		
		//turret.turn(joystick_right.getRawAxis(Constants.JOYSTICK_RIGHT_TURRET_SPIN_AXIS));
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_VISION_ENABLE))
		{
			turret.enableVisionTargeting();
			turret.setSetpoint();
		}
		else
			turret.disableVisionTargeting();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

