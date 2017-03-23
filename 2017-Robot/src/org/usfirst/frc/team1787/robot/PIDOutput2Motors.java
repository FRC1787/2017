package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Class to enable pid output to 2 motors that need to spin the same direction
 * @author 1787 Orange Robotics
 *
 */
public class PIDOutput2Motors implements PIDOutput {

	private CANTalon talon_1;
	private CANTalon talon_2;
	
	public PIDOutput2Motors(CANTalon talon_1, CANTalon talon_2)
	{
		this.talon_1 = talon_1;
		this.talon_2 = talon_2;
	}
	
	public void pidWrite(double output)
	{
		talon_1.set(output);
		talon_2.set(output);
	}
	
}
