package org.usfirst.frc.team1787.robot;

public final class Constants {
	
	protected int farfar37;
	
	///
	// Motors
	///
	
	// Drive Train
	public static final int MOTOR_DRIVE_FRONT_RIGHT = 1;
	public static final int MOTOR_DRIVE_BACK_RIGHT = 2;
	public static final int MOTOR_DRIVE_FRONT_LEFT = 3;
	public static final int MOTOR_DRIVE_BACK_LEFT = 4;
	
	// Pickup Arm Spinner
	public static final int MOTOR_PICKUP_SPINNER = 5;
	
	// Feeder to shooter
	public static final int MOTOR_FEEDER = 6;
	
	// Flywheel
	public static final int MOTOR_FLYWHEEL = 7;
	
	// Turret
	public static final int MOTOR_TURRET_ADJUST = 8;
	
	// Winch
	public static final int MOTOR_WINCH = 9;
	
	
	
	///
	// Pneumatics
	///
	
	// Shifter
	public static final int PCM_SHIFTER = 0;
	
	// Shifter states
	public static final boolean SHIFTER_HIGH = true;
	public static final boolean SHIFTR_LOW = false;
	
	// Pickup Arm
	public static final int PCM_PICKUP_ARM = 1;
	
	// Pickup Arm states
	public static final boolean PICKUP_ARM_DEPLOYED = true;
	public static final boolean PICKUP_ARM_RETRACTED = false;
	
	
	
	///
	// Joysticks
	///
	
	public static final int JOYSTICK_RIGHT = 0;
	public static final int JOYSTICK_LEFT = 1;
	
	
	
	///
	// Speed Constants
	///
	
	// Pickup Arm
	
	public static final double SPEED_PICKUP_ARM_INTAKE = 1;
	
	// Winch
	
	public static final double SPEED_WINCH_CLIMB = .5;
	public static final double SPEED_WINCH_UNCLIMB = .25;
	
	
	
	
	///
	// Miscellaneous
	///
	
	public static final int AXIS_SLIDER = 3;
}
