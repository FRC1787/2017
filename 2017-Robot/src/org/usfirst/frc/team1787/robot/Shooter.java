package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;

public class Shooter {

	private Feeder feeder;
	private Turret turret;
	private Flywheel flywheel;
	private VisionProcessing visionProcessing;
	
	private Preferences prefs;
	
	
	public Shooter(int talon_feeder_id, int talon_turret_id, int talon_flywheel_id, int gyro_id, VisionProcessing visionProcessing)
	{
		feeder = new Feeder(talon_feeder_id);
		turret = new Turret(talon_turret_id, gyro_id);
		flywheel = new Flywheel(talon_flywheel_id);
		this.visionProcessing = visionProcessing;
		
		prefs = Preferences.getInstance();		
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
	
//	public void setFlywheelSetpoint(double rps)
//	{
//		flywheel.setSetpointRPS(rps);
//	}
	
	public double getFlywheelRPS()
	{
		return flywheel.getRPS();
	}
	
	public void aimAndShoot()
	{
		System.out.println("Aimandshooting");
		// Set the andle setpoint for the turret
		turret.setSetpoint(visionProcessing.getHorizontalDegreesError());
		System.out.println(turret.onTarget() + "");
		// Set turret to track the target
		turret.enableVisionTargeting();
		
		// If error is small enough
		if (turret.onTarget())
		{
			// Start flywheel
			//double approprateFlywheelSpeed = 0;
			//flywheel.setSetpointRPS(approprateFlywheelSpeed);
			flywheel.run();
			if (flywheel.onTarget())
				feeder.feed();
			else
				feeder.stop();
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
	}
	
	public void setFlywheelAdjust(double rps)
	{
		flywheel.setAdjust(rps);
	}
	
	public double getFLywheelSetpointRps()
	{
		return flywheel.getSetpoint();
	}
	
	public void aim()
	{
		turret.enableVisionTargeting();
		turret.setSetpoint(visionProcessing.getHorizontalDegreesError());
	}
	
	public void updateTurretPIDConstants()
	{
		turret.updatePIDConstants();
	}
	
	public void updateTurretLengthWidthRatioTolerableError()
	{
		turret.updateLengthWidthRatioTolerableError();
	}
	
	public void dontAim()
	{
		turret.disableVisionTargeting();
		turret.stop();
	}
}
