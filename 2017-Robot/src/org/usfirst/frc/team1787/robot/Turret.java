 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret {

	private CANTalon talon_turret;
	private AnalogGyro gyro;
	private PIDController pidController;
	
	private boolean vision_targeting_enabled = false;
	
	public Turret (int motor_turret_id, int gyro_id) {
		
		this.talon_turret = new CANTalon(motor_turret_id);
		this.gyro = new AnalogGyro(0);
		this.pidController = new PIDController(Constants.VISION.TURRET_ALIGNMENT_P, Constants.VISION.TURRET_ALIGNMENT_I,
				Constants.VISION.TURRET_ALIGNMENT_D, Constants.VISION.TURRET_ALIGNMENT_F, gyro, talon_turret);
		
		gyro.calibrate();
		
		//pidController.setOutputRange(Constants.VISION_TURRET_ROTATION_SPEED_MIN, Constants.VISION_TURRET_ROTATION_SPEED_MAX);
		pidController.setAbsoluteTolerance(Constants.VISION.TURRET_ALIGNMENT_TOLERANCE_ANGLE);
		pidController.setContinuous(Constants.VISION.TURRET_ALIGNMENT_CONTINUOUS);
		pidController.reset();
		
		
	}
	
	public void enableVisionTargeting() {
		
		if (!vision_targeting_enabled)
		{
			vision_targeting_enabled = true;
			pidController.enable();
		}
	}
	
	
	public void disableVisionTargeting() {
		
		if (vision_targeting_enabled)
		{
			vision_targeting_enabled = false;
			pidController.reset();
		}
	}
	
	
	public void setSetpoint() {
		
		pidController.setSetpoint(gyro.getAngle() + SmartDashboard.getNumber("ERROR_DEGREES_HORIZONTAL", 0));
		DriverStation.reportWarning(gyro.getAngle() + "", false);
		
	}
	

	public void turn(double speed) {
		
		talon_turret.set(speed);
		
	}
	
	public void setPID(double p, double i, double d) {
		pidController.setPID(p, i, d);
	}
}
