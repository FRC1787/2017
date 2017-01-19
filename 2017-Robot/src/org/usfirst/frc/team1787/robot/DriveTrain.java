package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

public class DriveTrain {

	// Talon id's
	private CANTalon talon_front_right;
	private CANTalon talon_rear_right;
	private CANTalon talon_front_left;
	private CANTalon talon_rear_left;
	
	// Solenoid id
	private Solenoid sol_shifter;
	
	// RobotDrive class
	private RobotDrive driver;
	
	public DriveTrain(int motor_front_right_id, int motor_rear_right_id,
					  int motor_front_left_id, int motor_rear_left_id,
					  int sol_shifter_id)
	{
		this.talon_front_right = new CANTalon(motor_front_right_id);
		this.talon_rear_right = new CANTalon(motor_rear_right_id);
		this.talon_front_left = new CANTalon(motor_front_left_id);
		this.talon_rear_left = new CANTalon(motor_rear_left_id);
		
		this.sol_shifter = new Solenoid(sol_shifter_id);
		
		this.driver = new RobotDrive(talon_front_left, talon_rear_left,
									 talon_front_right, talon_rear_right);
	}
	/**
	 * Drive forwards
	 * @param joystick The joystick to use
	 */
	public void driveForwards(Joystick joystick) {
		
		driver.arcadeDrive(joystick);
		
	}
	
	/**
	 * Drive backwards
	 * @param joystick The joystick to use
	 */
	public void driveBackwards(Joystick joystick) {
		
		driver.arcadeDrive(-joystick.getY(), joystick.getX());
		
	}
	
	/**
	 * Set the gear to high or low
	 * @param value If value is less than .5, low gear, if value is greater than .5, high gear
	 */
	public void setGear(double value) {
		
		if (value < 0.5)
			sol_shifter.set(Constants.SHIFTR_LOW);
		else
			sol_shifter.set(Constants.SHIFTER_HIGH);
		
	}
	
}
