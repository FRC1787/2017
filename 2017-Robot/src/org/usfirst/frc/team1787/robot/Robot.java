package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	
	DriveTrain driveTrain;
	PickupArm pickupArm;
	Winch winch;
	Turret turret;
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		// Create the joysticks
		joystick_right = new Joystick(Constants.JOYSTICK_RIGHT_ID);
		joystick_left = new Joystick(Constants.JOYSTICK_LEFT_ID);
		
		// Create the different mechanisms
		driveTrain = new DriveTrain(Constants.MOTOR_DRIVE_FRONT_RIGHT, Constants.MOTOR_DRIVE_REAR_RIGHT,
									Constants.MOTOR_DRIVE_FRONT_LEFT, Constants.MOTOR_DRIVE_REAR_LEFT,
									Constants.PCM_SHIFTER_ID);
		
		pickupArm = new PickupArm(Constants.PCM_PICKUP_ARM_DEPLOYED_ID, Constants.PCM_PICKUP_ARM_RETRACTED_ID, Constants.MOTOR_PICKUP_ARM_SPINNER);
		
		winch = new Winch(Constants.MOTOR_WINCH);
		
		turret = new Turret(Constants.MOTOR_TURRET_FEEDER, Constants.MOTOR_TURRET_SPINNER, Constants.MOTOR_FLYWHEEL, Constants.GYRO_ID);
		
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

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		turret.enableVisionTargeting();
		turret.setSetpoint();
		///
		// Drive Control
		///
		/*
		// Drive with the joystick being pushed more
		if (joystick_right.getMagnitude() > joystick_left.getMagnitude())
			driveTrain.driveForwards(joystick_right);
		else
			driveTrain.driveBackwards(joystick_left);
		
		// Shifter
		driveTrain.setGear(joystick_right.getRawAxis(Constants.AXIS_SLIDER));
		
		///
		// Pickup Arm
		///
		
		// Deploying and retracting
		if (joystick_right.getRawButton(Constants.JOYSTICK_RIGHT_PICKUP_ARM_DEPLOY))
			pickupArm.deploy();
		else if (joystick_right.getRawButton(Constants.JOYSTICK_RIGHT_PICKUP_ARM_RETRACT))
			pickupArm.retract();
		
		// Intake
		if (joystick_right.getRawButton(Constants.JOYSTICK_RIGHT_PICKUP_ARM_INAKE))
			pickupArm.intake();
		else
			pickupArm.stopIntake();
		
		
		///
		// Winch
		///
		
		if (joystick_right.getRawButton(Constants.JOYSTICK_RIGHT_WINCH_CLIMB))
			winch.climb();
		else if (joystick_right.getRawButton(Constants.JOYSTICK_RIGHT_WINCH_UNCLIMB))
			winch.unclimb();
		else
			winch.stop();
		
		*/
		//turret.spinTurret(joystick_right.getRawAxis(3));
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

