 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDController;

public class Turret {

	private CANTalon talon_feeder;
	private CANTalon talon_turret_spinner;
	private CANTalon talon_flywheel;
	private AnalogGyro gyro;
	
	private PIDController pidController;
	
	public Turret (int motor_feeder_id, int motor_turret_spinner_id, int motor_flywheel_id, int gyro_id) {
		
		this.talon_feeder = new CANTalon(motor_feeder_id);
		this.talon_turret_spinner = new CANTalon(motor_turret_spinner_id);
		this.talon_flywheel = new CANTalon(motor_flywheel_id);
		
		this.gyro = new AnalogGyro(Constants.GYRO_ID);
		
		this.pidController = new PIDController(0, 0, 0, gyro, talon_turret_spinner);
		pidController.setOutputRange(Constants.VISION_TURRET_ROTATION_SPEED_MAX, Constants.VISION_TURRET_ROTATION_SPEED_MIN);
		pidController.setAbsoluteTolerance(Constants.VISION_TOLERANCE_ANGLE);
		pidController.setContinuous(Constants.VISION_CONTINUOUS);
		pidController.disable();
		
	}
	
	public void enableVisionTargeting() {
		pidController.enable();
	}
	
	
	public void disableVisionTargeting() {
		pidController.disable();
	}
	
	
}
