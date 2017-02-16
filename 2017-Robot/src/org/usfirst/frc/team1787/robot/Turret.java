 package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDController;

public class Turret {

	private CANTalon talon_feeder;
	private CANTalon talon_turret_spinner;
	private CANTalon talon_flywheel;
	
	private PIDController pidController;
	
	public Turret (int motor_feeder_id, int motor_turret_spinner_id, int motor_flywheel_id) {
		
		this.talon_feeder = new CANTalon(motor_feeder_id);
		this.talon_turret_spinner = new CANTalon(motor_turret_spinner_id);
		this.talon_flywheel = new CANTalon(motor_flywheel_id);
		
		//this.pidController = new PIDController(0, 0, 0, )
	}
	
	
	
}
