package org.usfirst.frc.team1787.robot;

public class Shooter {

	Feeder feeder;
	Turret turret;
	Flywheel flywheel;
	
	public Shooter(int talon_feeder_id, int talon_turret_id, int talon_flywheel_id, int gyro_id)
	{
		
		feeder = new Feeder(talon_feeder_id);
		turret = new Turret(talon_turret_id, gyro_id);
		flywheel = new Flywheel(talon_flywheel_id);
		
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
	
}
