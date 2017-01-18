package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class PickupArm {

	Solenoid sol_deploy;
	
	CANTalon talon_spinner;
	
	public PickupArm (int sol_deploy_id, int motor_spinner_id)
	{
		this.sol_deploy = new Solenoid(sol_deploy_id);
		
		this.talon_spinner = new CANTalon(motor_spinner_id);
	}
	
	public void deploy() {
		
		sol_deploy.set(Constants.PICKUP_ARM_DEPLOYED);
		
	}
	
	public void retract() {
		
		sol_deploy.set(Constants.PICKUP_ARM_RETRACTED);
		
	}
	
	public void intake() {
		
		talon_spinner.set(Constants.SPEED_PICKUP_ARM_INTAKE);
		
	}
	
}
