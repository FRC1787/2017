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
	
	// The joysticks
	private Joystick joystick_right;
	private Joystick joystick_left;
	
	// Solenoid id
	private Solenoid sol_shifter;
	
	// RobotDrive class
	private RobotDrive driver;
	
	public DriveTrain(int motor_front_right_id, int motor_rear_right_id,
					  int motor_front_left_id, int motor_rear_left_id,
					  int joystick_right_id, int joystick_left_id,
					  int sol_shifter_id)
	{
		this.talon_front_right = new CANTalon(motor_front_right_id);
		this.talon_rear_right = new CANTalon(motor_rear_right_id);
		this.talon_front_left = new CANTalon(motor_front_left_id);
		this.talon_rear_left = new CANTalon(motor_rear_left_id);
		
		this.joystick_right = new Joystick(joystick_right_id);
		this.joystick_left = new Joystick(joystick_left_id);
		
		this.sol_shifter = new Solenoid(sol_shifter_id);
		
		this.driver = new RobotDrive(talon_front_left, talon_rear_left,
									 talon_front_right, talon_rear_right);
	}
	
	public void driveForward() {
		
		driver.arcadeDrive(joystick_right);
		
	}
	
	public void driveBackwards() {
		
		driver.arcadeDrive(-joystick_left.getY(), joystick_left.getX());
		
	}
	
	public void setGear() {
		
		if (joystick_right.getRawAxis(Constants.AXIS_SLIDER) < 0.5)
			sol_shifter.set(Constants.SHIFTR_LOW);
		else
			sol_shifter.set(Constants.SHIFTER_HIGH);
		
	}
	
}
