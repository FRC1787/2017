package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	VisionProcessing visionProcessing;
	
	DriveTrain driveTrain;
	PickupArm pickupArm;
	Winch winch;
	Shooter shooter;
	
	Preferences prefs;
	
	Timer autoTimer;
	
	double flywheelAdjust;
	boolean flywheelAdjustButtonPressed = false;
	
	boolean cameraSwitchButtonPressed = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Create the joysticks
		joystick_right = new Joystick(Constants.JOYSTICKS.JOYSTICK_RIGHT_ID);
		joystick_left = new Joystick(Constants.JOYSTICKS.JOYSTICK_LEFT_ID);
		
		visionProcessing = new VisionProcessing();
		
		// Create the different mechanisms
		driveTrain = new DriveTrain(Constants.MOTORS.MOTOR_DRIVE_FRONT_RIGHT, Constants.MOTORS.MOTOR_DRIVE_REAR_RIGHT,
									Constants.MOTORS.MOTOR_DRIVE_FRONT_LEFT, Constants.MOTORS.MOTOR_DRIVE_REAR_LEFT,
									Constants.PNEUMATICS.PCM_SHIFTER_ID, Constants.DIO.ENCODER_DRIVE_RIGHT_ID_A,
									Constants.DIO.ENCODER_DRIVE_RIGHT_ID_B, Constants.DIO.ENCODER_DRIVE_LEFT_ID_A,
									Constants.DIO.ENCODER_DRIVE_LEFT_ID_B);
		
		pickupArm = new PickupArm(Constants.PNEUMATICS.PCM_PICKUP_ARM_DEPLOYED_ID, Constants.PNEUMATICS.PCM_PICKUP_ARM_RETRACTED_ID, Constants.MOTORS.MOTOR_PICKUP_ARM_SPINNER);
		
		winch = new Winch(Constants.MOTORS.MOTOR_WINCH);
		
		shooter = new Shooter(Constants.MOTORS.MOTOR_SHOOTER_FEEDER, Constants.MOTORS.MOTOR_SHOOTER_TURRET, Constants.MOTORS.MOTOR_SHOOTER_FLYWHEEL, Constants.ANALOG.GYRO_ID, visionProcessing);
		
		prefs = Preferences.getInstance();
		
		//shooter.setPID(prefs.getDouble("TURRET_HORIZONTAL_P", 0), prefs.getDouble("TURRET_HORIZONTAL_I", 0), prefs.getDouble("TURRET_HORIZONTAL_D", 0));
		
		//CameraServer.getInstance().startAutomaticCapture();
		
		autoTimer = new Timer();
		
		
	}

	/**
	 * This function is run when preparing for autonomous
	 */
	@Override
	public void autonomousInit() {
		autoTimer.start();
		driveTrain.setGear(0);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		
		//autoTimer.start();
		if (autoTimer.get() < 9)
			driveTrain.drive(-0.5);
		else
		{
			driveTrain.drive(0);
			autoTimer.stop();
		}
		
		
	}

	
	public void teleopInit() {
		
		visionProcessing.updateHSV();
		visionProcessing.updateGoalPixelOffset();
		shooter.updateTurretPIDConstants();
		shooter.updateTurretLengthWidthRatioTolerableError();
		
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
		
		driveTrain.publishEncoders();
		
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
		
		
		// Shooter manual control
		/*if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FEEDER))
			shooter.feed();
		else
			shooter.dontFeed();
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FLYWHEEL))
			shooter.shoot();
		else
			shooter.dontShoot();*/
		
		//shooter.turn(joystick_left.getRawAxis(2));
		
		
		// Adjust flywheel speed
		if (!flywheelAdjustButtonPressed)
		{
			if (joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_FLYWHEEL_RPS_INCREASE))
			{
				flywheelAdjustButtonPressed = true;
				flywheelAdjust += Constants.MISC.FLYWHEEL_RPS_CHANGE_RATE;
			}
			else if (joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_FLYWHEEL_RPS_DECREASE))
			{
				flywheelAdjustButtonPressed = true;
				flywheelAdjust -= Constants.MISC.FLYWHEEL_RPS_CHANGE_RATE;
			}
		}
		else
		{
			if (!(joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_FLYWHEEL_RPS_INCREASE) ||
				joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_FLYWHEEL_RPS_DECREASE)))
				flywheelAdjustButtonPressed = false;
		}
		shooter.setFlywheelAdjust(flywheelAdjust);
		
		
		
		SmartDashboard.putNumber("Flywheel Setpoint RPS", shooter.getFLywheelSetpointRps());
		SmartDashboard.putNumber("Flywheel RPS", shooter.getFlywheelRPS());
		SmartDashboard.putNumber("Flywheel Error", shooter.getFLywheelSetpointRps() - shooter.getFlywheelRPS());
		
		
		
		// Shooter automatic control
		/*if (joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_AIM_AND_SHOOT))
			shooter.aimAndShoot();
		else if (joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_AIM_2))
			shooter.aim();
		else
			shooter.dontAimAndShoot();*/

		
		// Shooter auto manual hybrid control
		if (joystick_left.getRawButton(Constants.JOYSTICKS.JOYSTICK_LEFT_AIM_2))
			shooter.aim();
		else
			shooter.dontAim();
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FEEDER))
			shooter.feed();
		else
			shooter.dontFeed();
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_TURRET_FLYWHEEL))
			shooter.shoot();
		else
			shooter.dontShoot();
		
		
		///
		// Vision
		///
		
		
		if (joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_CAMERA_SWITCH) && !cameraSwitchButtonPressed)
		{
			cameraSwitchButtonPressed = true;
			visionProcessing.switchCamera();
		}
		if (!joystick_right.getRawButton(Constants.JOYSTICKS.JOYSTICK_RIGHT_CAMERA_SWITCH))
			cameraSwitchButtonPressed = false;

		visionProcessing.sendCurrentCamera();
		
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		
		shooter.turn(joystick_left.getRawAxis(2));
		
	}
}

