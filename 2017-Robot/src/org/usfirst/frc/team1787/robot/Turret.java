 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turret {

	private CANTalon talon_turret;
	private AnalogGyro gyro;
	private PIDController pidController;
	
	private Preferences prefs;
	
	private double length_width_ratio_tolerable_error = Constants.VISION.LENGTH_WIDTH_RATIO_TOLERABLE_ERROR;
	
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
		
		prefs = Preferences.getInstance();
		
	}
	
	public void enableVisionTargeting()
	{
		if (!pidController.isEnabled())
			pidController.enable();
	}
	
	
	public void disableVisionTargeting()
	{	
		if (pidController.isEnabled())
			pidController.reset();
	}
	
	
	public void setSetpointPi()
	{
		
		setSetpoint(SmartDashboard.getNumber("ERROR_DEGREES_HORIZONTAL", 0));
		
	}
	
	
	public void setSetpoint(double degrees_error)
	{
		pidController.setSetpoint(gyro.getAngle() + degrees_error);
	}
	

	public void turn(double speed)
	{
		
		talon_turret.set(speed);
		
	}
	
	public void stop()
	{
		
		talon_turret.set(0);
		
	}
	
//	public void setPID(double p, double i, double d)
//	{
//		pidController.setPID(p, i, d);
//	}
	
	public double getDistance()
	{
	
		double angle = SmartDashboard.getNumber(Constants.VISION.ANGLE_TO_TARGET_STRING, 1);
		if (Math.tan(angle) != 0)
			return Constants.VISION.TARGET_HEIGHT / Math.tan(angle);
		else
			return -1;
		
	}
	
	public double getError()
	{
		return pidController.getError();
	}
	
	public boolean onTarget()
	{
		return pidController.onTarget();
	}

	public void updatePIDConstants()
	{
		pidController.setPID(prefs.getDouble("TURRET_ALIGNMENT_P", 0),
							 prefs.getDouble("TURRET_ALIGNMENT_I", 0),
							 prefs.getDouble("TURRET_ALIGNMENT_D", 0));
	}
	
	public void updateLengthWidthRatioTolerableError()
	{
		length_width_ratio_tolerable_error = prefs.getDouble(Constants.VISION.LENGTH_WIDTH_RATIO_TOLERABLE_ERROR_LABEL, Constants.VISION.LENGTH_WIDTH_RATIO_TOLERABLE_ERROR);
	}
	
}
