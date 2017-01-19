 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

public class Turret {

	private CANTalon talon_feeder;
	private CANTalon talon_flywheel;
	
	public Turret (int motor_feeder_id, int motor_flywheel_id) {
		
		this.talon_feeder = new CANTalon(motor_feeder_id);
		this.talon_flywheel = new CANTalon(motor_flywheel_id);
		
	}
	
}
