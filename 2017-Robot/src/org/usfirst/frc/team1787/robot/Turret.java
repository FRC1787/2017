 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret {

	private CANTalon talon_feeder;
	private CANTalon talon_turret_spinner;
	private CANTalon talon_flywheel;
	private AnalogGyro gyro;
	
	private double feeder_speed = .7;
	private double flywheel_speed = 1;
	
	private PIDController pidController;
	
	public Turret (int motor_feeder_id, int motor_turret_spinner_id, int motor_flywheel_id, int gyro_id) {
		
		this.talon_feeder = new CANTalon(motor_feeder_id);
		this.talon_turret_spinner = new CANTalon(motor_turret_spinner_id);
		this.talon_flywheel = new CANTalon(motor_flywheel_id);
		
		this.gyro = new AnalogGyro(0);
		
		gyro.calibrate();
		
		this.pidController = new PIDController(Constants.VISION_P, Constants.VISION_I, Constants.VISION_D,
											   Constants.VISION_F, gyro, talon_feeder);
		//pidController.setOutputRange(Constants.VISION_TURRET_ROTATION_SPEED_MIN, Constants.VISION_TURRET_ROTATION_SPEED_MAX);
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
	
	
	public void setSetpoint() {
		
		pidController.setSetpoint(gyro.getAngle());
		
	}
	
	public void spinFeeder() {
		
		talon_feeder.set(-feeder_speed);
		
	}
	
	public void stopFeeder() {
		
		talon_feeder.set(0);
		
	}
	
	public void spinFlywheel() {
		
		// Positive to shoot
		talon_flywheel.set(flywheel_speed);
		
	}
	
	public void stopFlywheel() {
		
		talon_flywheel.set(0);
		
	}
	
	public void turn(double speed) {
		
		talon_turret_spinner.set(speed);
		
	}
	
	public void setFlywheelSpeed(double speed)
	{
		this.flywheel_speed = speed;
	}
	
	public void setFeederSpeed(double speed)
	{
		this.feeder_speed = speed;
	}
}
