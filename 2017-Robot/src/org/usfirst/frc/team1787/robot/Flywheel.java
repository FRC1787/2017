package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Flywheel {

	private CANTalon talon_flywheel;
	private Encoder encoder;
	private double setpointRps;
	
	public Flywheel(int talon_flywheel_id)
	{
		
		this.talon_flywheel = new CANTalon(talon_flywheel_id);
		this.encoder = new Encoder(Constants.DIO.ENCODER_FLYWHEEL_A, Constants.DIO.ENCODER_FLYWHEEL_B);
		this.encoder.setPIDSourceType(PIDSourceType.kRate);
		this.encoder.setDistancePerPulse(Constants.MISC.ENCODER_FLYWHEEL_DISTANCE_PER_PULSE);
		this.setpointRps = Constants.SPEEDS.SPEED_FLYWHEEL_RPS_DEFAULT;
		
	}
	
	public void run() {
		System.out.println("Flywheel Run");
		if (encoder.getRate() < setpointRps)
		{
			talon_flywheel.set(Constants.SPEEDS.SPEED_FLYWHEEL_VOLTS);
			System.out.println("Speeding");
		}
		else
		{
			talon_flywheel.set(0);
			System.out.println("Not Speeding");
		}
	}
	
	public void stop() {
		
		talon_flywheel.set(0);
		
	}
	
	public void setSetpointRPS(double rps)
	{
		setpointRps = rps;
	}
	
	public void setAdjust(double rps)
	{
		setpointRps = Constants.SPEEDS.SPEED_FLYWHEEL_RPS_DEFAULT + rps;
	}
	
	public double getSetpoint()
	{
		return setpointRps;
	}
	
	public double getRPS()
	{
		return encoder.getRate();
	}
	
	public boolean onTarget()
	{
		return Math.abs(setpointRps - encoder.getRate()) < Constants.MISC.FLYWHEEL_RPS_ACCEPTABLE_ERROR;
	}
}
