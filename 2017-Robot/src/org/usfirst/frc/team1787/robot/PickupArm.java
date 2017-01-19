package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class PickupArm {

	private Solenoid sol_deploy;
	
	private CANTalon talon_spinner;
	
	public PickupArm (int sol_deploy_id, int motor_spinner_id)
	{
		this.sol_deploy = new Solenoid(sol_deploy_id);
		
		this.talon_spinner = new CANTalon(motor_spinner_id);
	}
	
	/**
	 * Deploy the pickup arm
	 */
	public void deploy() {
		
		sol_deploy.set(Constants.PICKUP_ARM_DEPLOYED);
		
	}
	
	/**
	 * Retract the pickup arm
	 */
	public void retract() {
		
		sol_deploy.set(Constants.PICKUP_ARM_RETRACTED);
		
	}
	
	/**
	 * Spin intake motors, to suck in balls
	 */
	public void intake() {
		
		talon_spinner.set(Constants.SPEED_PICKUP_ARM_INTAKE);
		
	}
	
	/**
	 * Stop the intake motors
	 */
	public void stopIntake() {
		
		talon_spinner.set(0);
		
	}
	
}
