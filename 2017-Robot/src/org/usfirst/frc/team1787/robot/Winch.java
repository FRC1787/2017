package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

public class Winch {

	private CANTalon talon_winch_1;
	private CANTalon talon_winch_2;
	
	public Winch (int motor_winch_id_1, int motor_winch_id_2) {
		
		this.talon_winch_1 = new CANTalon(motor_winch_id_1);
		this.talon_winch_2 = new CANTalon(motor_winch_id_2);
		
	}
	
	/**
	 * Spin motors to climb up the rope
	 */
	public void climb() {
		
		talon_winch_1.set(Constants.SPEEDS.SPEED_WINCH_CLIMB);
		talon_winch_2.set(-Constants.SPEEDS.SPEED_WINCH_CLIMB);
		
	}
	
	/**
	 * Spin motors to climb down the rope
	 */
	public void unclimb() {
		
		talon_winch_1.set(Constants.SPEEDS.SPEED_WINCH_UNCLIMB);
		talon_winch_2.set(Constants.SPEEDS.SPEED_WINCH_UNCLIMB);
		
	}
	
	/**
	 * Stop the winch motors
	 */
	public void stop() {
		
		talon_winch_1.set(0);
		talon_winch_2.set(0);
		
	}
	
}
