package org.usfirst.frc.team1787.robot;

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
		public static final Value PICKUP_ARM_DEPLOYED = DoubleSolenoid.Value.kForward;
		public static final Value PICKUP_ARM_RETRACTED = DoubleSolenoid.Value.kReverse;
		
	}
	
	///
	// Joysticks
	///
	
	public static class JOYSTICKS {
	
		// Joystick id's
		public static final int JOYSTICK_RIGHT_ID = 0;
		public static final int JOYSTICK_LEFT_ID = 1;
		
		// Joystick values
		
		public static final int JOYSTICK_RIGHT_PICKUP_ARM_DEPLOY = 0;
		public static final int JOYSTICK_RIGHT_PICKUP_ARM_RETRACT = 0;
		public static final int JOYSTICK_RIGHT_PICKUP_ARM_INAKE = 2;
		
		public static final int JOYSTICK_RIGHT_WINCH_CLIMB = 0;
		public static final int JOYSTICK_RIGHT_WINCH_UNCLIMB = 0;
		
		public static final int JOYSTICK_RIGHT_TURRET_FLYWHEEL = 1;
		public static final int JOYSTICK_RIGHT_TURRET_FEEDER = 3;
		
		public static final int JOYSTICK_RIGHT_TURRET_SPIN_AXIS = 2;
		
		public static final int JOYSTICK_RIGHT_VISION_ENABLE = 8;
		
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
		public static final double SPEED_FEEDER = 0.7;
		
		// Flywheel
		public static final double SPEED_FLYWHEEL_RPS = 50; // approximately rotations/second
		public static final double SPEED_FLYWHEEL_VOLTS = 0.9; // The value to spin if the flywheel speed is less than the setpoint
		
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
		
		public static final double TURRET_ALIGNMENT_TOLERANCE_ANGLE = 5;
		
		public static final boolean TURRET_ALIGNMENT_CONTINUOUS = false;
		
		public static final String TURRET_ALIGNMENT_ERROR_LABEL = "ERROR_DEGREES_HORIZONTAL";
		
		
	}
	
	///
	// Miscellaneous
	///
	public static class MISC {
	
		public static final int AXIS_SLIDER = 3;
		
		public static final double ENCODER_FLYWHEEL_DISTANCEPERPULSE = 0.00048828125;
	
	}
	
}
