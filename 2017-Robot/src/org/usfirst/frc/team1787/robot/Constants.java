

package org.usfirst.frc.team1787.robot;

import edu.wpi.cscore.VideoCamera.WhiteBalance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Constants {
	
	protected int farfar37;
	
	///
	// Motors
	///
	public static class MOTORS {
	
		// Drive Train
		public static final int MOTOR_DRIVE_FRONT_RIGHT = 8;
		public static final int MOTOR_DRIVE_REAR_RIGHT = 9;
		public static final int MOTOR_DRIVE_FRONT_LEFT = 6;
		public static final int MOTOR_DRIVE_REAR_LEFT = 7;
		
		// Pickup Arm Spinner
		public static final int MOTOR_PICKUP_ARM_SPINNER = 3;
		
		// Feeder to shooter
		public static final int MOTOR_SHOOTER_FEEDER = 2;
		
		// Flywheel
		public static final int MOTOR_SHOOTER_FLYWHEEL = 5;
		
		// Turret
		public static final int MOTOR_SHOOTER_TURRET = 1;
		
		// Winch
		public static final int MOTOR_WINCH = 4;
		
	}
	
	///
	// Pneumatics
	///
	
	public static class PNEUMATICS {
	
		// Shifter
		public static final int PCM_SHIFTER_ID = 0;
		
		// Shifter states
		public static final boolean SHIFTER_HIGH = true;
		public static final boolean SHIFTR_LOW = false;
		
		// Pickup Arm
		public static final int PCM_PICKUP_ARM_DEPLOYED_ID = 1;
		public static final int PCM_PICKUP_ARM_RETRACTED_ID = 2;
		
		// Pickup Arm states
		public static final Value PICKUP_ARM_DEPLOYED = DoubleSolenoid.Value.kReverse;
		public static final Value PICKUP_ARM_RETRACTED = DoubleSolenoid.Value.kForward;
		
	}
	
	///
	// Joysticks
	///
	
	public static class JOYSTICKS {
	
		// Joystick id's
		public static final int JOYSTICK_RIGHT_ID = 0;
		public static final int JOYSTICK_LEFT_ID = 1;
		
		// Joystick values
		public static final int JOYSTICK_RIGHT_SHOOT = 1;
		public static final int JOYSTICK_RIGHT_INTAKE= 2;
		public static final int JOYSTICK_RIGHT_PICKUP_DOWN = 3;
		public static final int JOYSTICK_RIGHT_PICKUP_UP = 4;
		public static final int JOYSTICK_RIGHT_FLYWHEEL_FASTER = 7;
		public static final int JOYSTICK_RIGHT_FLYWHEEL_SLOWER = 8;
		public static final int JOYSTICK_RIGHT_VISIONTARGET = 10;
		public static final int JOYSTICK_RIGHT_WINCH_CLIMB = 14;
		
		public static final int JOYSTICK_LEFT_CAMERA_SWITCH = 14;
		
		
		
		
		
		
		
		
		
		
		
		
		
		// Old joystick values
//		public static final int JOYSTICK_DISABLED = 0;
//		
//		public static final int JOYSTICK_RIGHT_PICKUP_ARM_DEPLOY = 3;
//		public static final int JOYSTICK_RIGHT_PICKUP_ARM_RETRACT = 4;
//		public static final int JOYSTICK_RIGHT_PICKUP_ARM_INAKE = 1;
//		
//		public static final int JOYSTICK_RIGHT_WINCH_CLIMB = 2;
//		public static final int JOYSTICK_RIGHT_WINCH_UNCLIMB = JOYSTICK_DISABLED;
//		
//		public static final int JOYSTICK_RIGHT_TURRET_FLYWHEEL = 14;
//		public static final int JOYSTICK_RIGHT_TURRET_FEEDER = 8;
//		
//		//public static final int JOYSTICK_RIGHT_TURRET_SPIN_AXIS = 2;
//		
//		//public static final int JOYSTICK_RIGHT_VISION_ENABLE = 8;
//		
//		public static final int JOYSTICK_LEFT_AIM_AND_SHOOT = 9;
//		
//		public static final int JOYSTICK_LEFT_AIM_1 = 5;
//		public static final int JOYSTICK_LEFT_AIM_2 = 8;
//		
//		public static final int JOYSTICK_LEFT_FLYWHEEL_RPS_INCREASE = 13;
//		public static final int JOYSTICK_LEFT_FLYWHEEL_RPS_DECREASE = 14;
//		
//		public static final int JOYSTICK_RIGHT_CAMERA_SWITCH = 10;
		
	}
	
	
	///
	// Speed Constants
	///
	
	public static class SPEEDS {
	
		// Pickup Arm
		public static final double SPEED_PICKUP_ARM_INTAKE = -.8;
		
		// Winch
		public static final double SPEED_WINCH_CLIMB = -1.0;
		public static final double SPEED_WINCH_UNCLIMB = .5;
		
		// Feeder
		public static final double SPEED_FEEDER = -0.5;
		
		// Flywheel
		public static final double SPEED_FLYWHEEL_RPS_DEFAULT = 55; // approximately rotations/second
		public static final double SPEED_FLYWHEEL_VOLTS = 1; // The value to spin if the flywheel speed is less than the setpoint
		public static final double SPEED_FLYWHEEL_ADJUSTABLE_RANGE = 10; // The amount to be able to adjust the flywheel setpoint
		
	}
	
	
	///
	// DIO Input
	///
	public static class DIO {
	
		public static final int ENCODER_DRIVE_RIGHT_ID_A = 0;
		public static final int ENCODER_DRIVE_RIGHT_ID_B = 1;
		
		public static final int ENCODER_DRIVE_LEFT_ID_A = 2;
		public static final int ENCODER_DRIVE_LEFT_ID_B = 3;
		
		public static final int ENCODER_TURRET_SPIN_ID_A = 4;
		public static final int ENCODER_TURRET_SPIN_ID_B = 5;
		
		public static final int ENCODER_FLYWHEEL_A = 6;
		public static final int ENCODER_FLYWHEEL_B = 7;
		
	}
	
	///
	// Analog Input
	///
	public static class ANALOG {
		
		public static final int GYRO_ID = 0;
		
	}
	
	
	///
	// Vision Targeting
	///
	public static class VISION {
	
		// Turret Alignment
		public static final double TURRET_ALIGNMENT_P = .01;
		public static final double TURRET_ALIGNMENT_I = 0;
		public static final double TURRET_ALIGNMENT_D = 0;
		public static final double TURRET_ALIGNMENT_F = 0;
		
		public static final double TURRET_ALIGNMENT_SPEED_MIN = -.5;
		public static final double TURRET_ALIGNMENT_SPEED_MAX = .5;
		
		public static final double TURRET_ALIGNMENT_TOLERANCE_ANGLE = 10;
		
		public static final boolean TURRET_ALIGNMENT_CONTINUOUS = false;
		
		public static final String TURRET_ALIGNMENT_ERROR_LABEL = "ERROR_DEGREES_HORIZONTAL";
		
		public static final String GOAL_PIXEL_OFFSET_LABEL = "GOAL_PIXEL_OFFSET";
		
		public static final double TARGET_HEIGHT = 86;
		public static final String ANGLE_TO_TARGET_STRING = "angle_to_target";
		
		public static final String CAMERA_SHOOTER_NAME = "camera_shooter";
		public static final String CAMERA_GEAR_NAME = "camera_gear";
		
		public static final int CAMERA_WIDTH = 320;
		public static final int CAMERA_HEIGHT = 240;
		
		public static final int CAMERA_SHOOTER_WHITEBALANCE = WhiteBalance.kFixedIndoor;
		public static final int CAMERA_SHOOTER_BRIGHTNESS = 100;
		public static final int CAMERA_SHOOTER_EXPOSURE = 2;
		public static final int CAMERA_SHOOTER_FPS = 25;
		
		public static final int CAMERA_GEAR_BRIGHTNESS = 50;
		
		public static final String HSV_LOW_H = "HSV_LOW_H";
		public static final String HSV_LOW_S = "HSV_LOW_S";
		public static final String HSV_LOW_V = "HSV_LOW_V";
		public static final String HSV_HIGH_H = "HSV_HIGH_H";
		public static final String HSV_HIGH_S = "HSV_HIGH_S";
		public static final String HSV_HIGH_V = "HSV_HIGH_V";
		
		public static final int MORPHOLOGY_KERNEL_SIZE = 5;
		
		public static final int CONTOUR_MIN_AREA = 50;
		
		public static final double TAPE_TARGET_LENGTH_WIDTH_RATIO = 3.75;
		public static final double LENGTH_WIDTH_RATIO_TOLERABLE_ERROR = .25;
		public static final String LENGTH_WIDTH_RATIO_TOLERABLE_ERROR_LABEL = "LENGTH_WIDTH_RATIO_TOLERABLE_ERROR";
		
		public static final double DEGREES_PER_PIXEL = 0.15;
		
		public static final int GOAL_PIXEL_OFFSET_DEFAULT = 0;
		public static final int GOAL_PIXEL_OFFSET_ADJUST_AMOUNT = 5;
		
	}
	
	///
	// Miscellaneous
	///
	public static class MISC {
	
		public static final int AXIS_SLIDER = 3;
		
		public static final double ENCODER_FLYWHEEL_DISTANCE_PER_PULSE = 0.00048828125;
		
		public static final double FLYWHEEL_RPS_ACCEPTABLE_ERROR = 50;
		
		public static final double FLYWHEEL_RPS_CHANGE_RATE = .25;
		
		public static final double DRIVE_DISTANCE_PER_PULSE = 0.009817707144979;
		
		public static final double DRIVE_DISTANCE_P = 0.01;
		public static final double DRIVE_DISTANCE_I = 0;
		public static final double DRIVE_DISTANCE_D = 0;
		public static final double DRIVE_DISTANCE_F = 0;
		
		public static final int SHOOTING_FLYWHEEL_DELAY = 3;
		
		public static final double JOYSTICK_RIGHT_SHOOTER_ADJUST_ACTIVATION_DISTANCE = 0.5;
	}
	
}
