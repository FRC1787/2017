package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class PickupArm {

	private DoubleSolenoid sol_deploy;
	
	private CANTalon talon_spinner;
	
	Compressor compressor;
	
	public PickupArm (int sol_deploy_id, int sol_retract_id, int motor_spinner_id)
	{
		this.sol_deploy = new DoubleSolenoid(sol_deploy_id, sol_retract_id);
		this.talon_spinner = new CANTalon(motor_spinner_id);
	}
	
	/**
	 * Deploy the pickup arm
	 */
	public void deploy() {
		
		sol_deploy.set(Constants.PNEUMATICS.PICKUP_ARM_DEPLOYED);
		
	}
	
	/**
	 * Retract the pickup arm
	 */
	public void retract() {
		
		sol_deploy.set(Constants.PNEUMATICS.PICKUP_ARM_RETRACTED);
		
	}
	
	/**
	 * Spin intake motors, to suck in balls
	 */
	public void intake() {
		
		talon_spinner.set(Constants.SPEEDS.SPEED_PICKUP_ARM_INTAKE);
		
	}
	
	/**
	 * Stop the intake motors
	 */
	public void stopIntake() {
		
		talon_spinner.set(0);
		
	}
	
}
