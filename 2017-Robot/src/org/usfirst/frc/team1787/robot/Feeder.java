package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

public class Feeder {
	
	CANTalon talon_feeder;
	
	public Feeder(int talon_feeder_id) {
		
		talon_feeder = new CANTalon(talon_feeder_id);
		
	}
	
	public void feed() {
		
		talon_feeder.set(Constants.SPEEDS.SPEED_FEEDER);
		
	}
	
	public void stop() {
		
		talon_feeder.set(0);
		
	}
	
}
