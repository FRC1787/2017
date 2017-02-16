package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Constants {
	
	protected int farfar37;
	
	///
	// Motors
	///
	
	// Drive Train
	public static final int MOTOR_DRIVE_FRONT_RIGHT = 8;
	public static final int MOTOR_DRIVE_REAR_RIGHT = 9;
	public static final int MOTOR_DRIVE_FRONT_LEFT = 6;
	public static final int MOTOR_DRIVE_REAR_LEFT = 7;
	
	// Pickup Arm Spinner
	public static final int MOTOR_PICKUP_ARM_SPINNER = 3;
	
	// Feeder to shooter
	public static final int MOTOR_TURRET_FEEDER = 2;
	
	// Flywheel
	public static final int MOTOR_FLYWHEEL = 5;
	
	// Turret
	public static final int MOTOR_TURRET_SPINNER = 1;
	
	// Winch
	public static final int MOTOR_WINCH = 4;
	
	
	
	///
	// Pneumatics
	///
	
	// Shifter
	public static final int PCM_SHIFTER_ID = 0;
	
	// Shifter states
	public static final boolean SHIFTER_HIGH = true;
	public static final boolean SHIFTR_LOW = false;
	
	// Pickup Arm
	public static final int PCM_PICKUP_ARM_DEPLOYED_ID = 1;
	public static final int PCM_PICKUP_ARM_RETRACTED_ID = 2;
	
	// Pickup Arm states
	public static final Value PICKUP_ARM_DEPLOYED = DoubleSolenoid.Value.kForward;
	public static final Value PICKUP_ARM_RETRACTED = DoubleSolenoid.Value.kReverse;
	
	
	
	///
	// Joysticks
	///
	
	// Joystick id's
	public static final int JOYSTICK_RIGHT_ID = 0;
	public static final int JOYSTICK_LEFT_ID = 1;
	
	// Joystick values
	
	public static final int JOYSTICK_RIGHT_PICKUP_ARM_DEPLOY = 2;
	public static final int JOYSTICK_RIGHT_PICKUP_ARM_RETRACT = 3;
	public static final int JOYSTICK_RIGHT_PICKUP_ARM_INAKE = 1;
	
	public static final int JOYSTICK_RIGHT_WINCH_CLIMB = 0;
	public static final int JOYSTICK_RIGHT_WINCH_UNCLIMB = 0;
	
	
	///
	// Speed Constants
	///
	
	// Pickup Arm

	public static final double SPEED_PICKUP_ARM_INTAKE = -.8;
	
	// Winch
	
	public static final double SPEED_WINCH_CLIMB = .5;
	public static final double SPEED_WINCH_UNCLIMB = -.25;
	
	
	
	///
	// DIO
	///
	
	public static final int ENCODER_DRIVE_RIGHT_ID_A = 0;
	public static final int ENCODER_DRIVE_RIGHT_ID_B = 1;
	
	public static final int ENCODER_DRIVE_LEFT_ID_A = 2;
	public static final int ENCODER_DRIVE_LEFT_ID_B = 3;
	
	public static final int ENCODER_TURRET_SPIN_ID_A = 4;
	public static final int ENCODER_TURRET_SPIN_ID_B = 5;
	
	
	
	///
	// Vision Targeting
	///
	
	public static final double VISION_P = .02;
	public static final double VISION_I = 0;
	public static final double VISION_D = 0;
	public static final double VISION_F = 0;
	
	public static final double VISION_TURRET_ROTATION_SPEED_MAX = .5;
	public static final double VISION_TURRET_ROTATION_SPEED_MIN = -.5;
	
	public static final double VISION_TOLERANCE_ANGLE = 5;
	
	public static final boolean VISION_CONTINUOUS = false;
	
	public static final String VISION_ERROR_STRING = "ERROR_DEGREES_HORIZONTAL";
	
	
	
	///
	// Miscellaneous
	///
	
	public static final int AXIS_SLIDER = 3;
	
	public static final int GYRO_ID = 0;
}
