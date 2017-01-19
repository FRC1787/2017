package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

public class Winch {

	private CANTalon talon_winch;
	
	public Winch (int motor_winch_id) {
		
		this.talon_winch = new CANTalon(motor_winch_id);
		
	}
	
	/**
	 * Spin motors to climb up the rope
	 */
	public void climb() {
		
		talon_winch.set(Constants.SPEED_WINCH_CLIMB);
		
	}
	
	/**
	 * Spin motors to climb down the rope
	 */
	public void unclimb() {
		
		talon_winch.set(Constants.SPEED_WINCH_UNCLIMB);
		
	}
	
	/**
	 * Stop the winch motors
	 */
	public void stop() {
		
		talon_winch.set(0);
		
	}
	
}
