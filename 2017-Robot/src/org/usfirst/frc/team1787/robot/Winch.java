package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

public class Winch {

	private CANTalon talon_winch;
	
	public Winch (int motor_winch_id) {
		
		this.talon_winch = new CANTalon(motor_winch_id);
		
	}
	
	public void climb() {
		
		talon_winch.set(Constants.SPEED_WINCH_CLIMB);
		
	}
	
	public void unclimb() {
		
		talon_winch.set(Constants.SPEED_WINCH_UNCLIMB);
		
	}
	
	public void stop() {
		
		talon_winch.set(0);
		
	}
	
}
