package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {

	// Talon ids
	private CANTalon talon_front_right;
	private CANTalon talon_rear_right;
	private CANTalon talon_front_left;
	private CANTalon talon_rear_left;
	
	// Encoder ids
	private Encoder encoder_right;
	private Encoder encoder_left;
	
	// Solenoid id
	private Solenoid sol_shifter;
	
	// RobotDrive class
	private RobotDrive driver;
	
	private PIDOutput2Motors pidOutput_right;
	private PIDOutput2Motors pidOutput_left;
	
	// PID Controller for driving a distance
	private PIDController pidControllerDriveRight;
	private PIDController pidControllerDriveLeft;
	
	private Preferences prefs;
	
	public DriveTrain(int motor_front_right_id, int motor_rear_right_id,
					  int motor_front_left_id, int motor_rear_left_id,
					  int sol_shifter_id, int encoder_right_A_id, int encoder_right_B_id,
					  int encoder_left_A_id, int encoder_left_B_id)
	{
		this.talon_front_right = new CANTalon(motor_front_right_id);
		this.talon_rear_right = new CANTalon(motor_rear_right_id);
		this.talon_front_left = new CANTalon(motor_front_left_id);
		this.talon_rear_left = new CANTalon(motor_rear_left_id);
		
		this.encoder_right = new Encoder(encoder_right_A_id, encoder_right_B_id);
		this.encoder_left = new Encoder(encoder_left_A_id, encoder_left_B_id);
		this.encoder_right.setDistancePerPulse(Constants.MISC.DRIVE_DISTANCE_PER_PULSE);
		this.encoder_left.setDistancePerPulse(Constants.MISC.DRIVE_DISTANCE_PER_PULSE);
		
		this.sol_shifter = new Solenoid(sol_shifter_id);
		
		this.driver = new RobotDrive(talon_front_left, talon_rear_left,
									 talon_front_right, talon_rear_right);
		
		this.pidOutput_right = new PIDOutput2Motors(talon_front_right, talon_rear_right);
		this.pidOutput_left = new PIDOutput2Motors(talon_front_left, talon_rear_left);
		
		this.pidControllerDriveRight = new PIDController(Constants.MISC.DRIVE_DISTANCE_P, Constants.MISC.DRIVE_DISTANCE_I,
														 Constants.MISC.DRIVE_DISTANCE_D, Constants.MISC.DRIVE_DISTANCE_F,
														 encoder_right, pidOutput_right);
		this.pidControllerDriveLeft = new PIDController(Constants.MISC.DRIVE_DISTANCE_P, Constants.MISC.DRIVE_DISTANCE_I,
				 Constants.MISC.DRIVE_DISTANCE_D, Constants.MISC.DRIVE_DISTANCE_F,
				 encoder_left, pidOutput_left);
		
		this.prefs = Preferences.getInstance();
	}
	/**
	 * Drive forwards
	 * @param joystick The joystick to use
	 */
	public void driveForwards(Joystick joystick) {
		
		driver.arcadeDrive(joystick);
		SmartDashboard.putNumber("Joystick Value", joystick.getMagnitude());
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
			sol_shifter.set(Constants.PNEUMATICS.SHIFTR_LOW);
		else
			sol_shifter.set(Constants.PNEUMATICS.SHIFTER_HIGH);
		
	}
	
	public void drive(double speed)
	{
		driver.arcadeDrive(speed, 0);
	}
	
	public void driveConstant(double speed)
	{
		driver.arcadeDrive(speed, 0);
	}
	
	public void publishEncoders()
	{
		SmartDashboard.putNumber("Encoder Drive Right Ticks", encoder_right.get());
		SmartDashboard.putNumber("Encoder Drive Left Ticks", encoder_left.get());
	}
	
	
	public double getLeftDriveDistance()
	{
		return encoder_left.getDistance();
	}
	
	
	public double getRightDriveDistance()
	{
		return encoder_right.getDistance();
	}
	
	public void resetEncoders()
	{
		encoder_left.reset();
		encoder_right.reset();
	}
	
	public void updateEncodersDPP()
	{
		encoder_right.setDistancePerPulse(prefs.getDouble("Drive DPP", 1));
		encoder_left.setDistancePerPulse(prefs.getDouble("Drive DPP", 1));
	}
	
	/**
	 * Drive a distance
	 * @param distance The distance in inches
	 * @return true if distance is driven, false if otherwise
	 */
	/*public boolean driveDistance(double distance)
	{
		if
	}*/
	
}
