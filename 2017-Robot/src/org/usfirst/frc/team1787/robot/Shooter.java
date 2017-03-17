package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

public class Shooter {

	private Feeder feeder;
	private Turret turret;
	private Flywheel flywheel;
	private VisionProcessing visionProcessing;
	
	private Preferences prefs;
	
	private double turret_shooting_error_allowance;
	private Timer flywheelStartupTimer;
	
	public Shooter(int talon_feeder_id, int talon_turret_id, int talon_flywheel_id, int gyro_id, VisionProcessing visionProcessing)
	{
		
		feeder = new Feeder(talon_feeder_id);
		turret = new Turret(talon_turret_id, gyro_id);
		flywheel = new Flywheel(talon_flywheel_id);
		this.visionProcessing = visionProcessing;
		
		prefs = Preferences.getInstance();
		
		updateTurretShootingErrorAllowance();
		flywheelStartupTimer = new Timer();
		
	}
	
	public void feed()
	{
		feeder.feed();
	}
	
	public void dontFeed()
	{
		feeder.stop();
	}
	
	public void visionTrack()
	{
		turret.enableVisionTargeting();
	}
	
	public void shoot()
	{
		flywheel.run();
	}
	
	public void dontShoot()
	{
		flywheel.stop();
	}
	
	public void turn(double speed)
	{
		turret.turn(speed);
	}
	
	public void dontTurn()
	{
		turret.stop();
	}
	
	public void setFlywheelSetpoint(double rps)
	{
		flywheel.setSetpointRPS(rps);
	}
	
	public double getFlywheelRPS()
	{
		return flywheel.getRPS();
	}
	
	public void aimAndShoot()
	{
		// Set the andle setpoint for the turret
		turret.setSetpoint(visionProcessing.getHorizontalDegreesError());
		
		// Set turret to track the target
		turret.enableVisionTargeting();
		
		// If error is small enough
		if (Math.abs(turret.getError()) < turret_shooting_error_allowance)
		{
			// Start flywheel
			flywheel.run();
			flywheelStartupTimer.start();
			
			// If enough time has passed
			if (flywheelStartupTimer.get() >= Constants.MISC.FLYWHEEL_STARTUP_TIME)
				feeder.feed();
		}
		else
		{
			feeder.stop();
		}
	}
	
	public void dontAimAndShoot()
	{
		turret.disableVisionTargeting();
		turret.stop();
		flywheel.stop();
		feeder.stop();
		flywheelStartupTimer.reset();
	}

	public void updateTurretShootingErrorAllowance()
	{
		turret_shooting_error_allowance = prefs.getDouble(Constants.VISION.TURRET_SHOOTING_ERROR_ALLOWANCE_LABEL,
														  Constants.VISION.TURRET_SHOOTING_ERROR_ALLOWANCE_BACKUP);
	}
}
