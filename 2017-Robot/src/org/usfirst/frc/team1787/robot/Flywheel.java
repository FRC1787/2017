package org.usfirst.frc.team1787.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;

public class Flywheel {

	private CANTalon talon_flywheel;
	Encoder encoder;
	
	public Flywheel(int talon_flywheel_id)
	{
		
		this.talon_flywheel = new CANTalon(talon_flywheel_id);
		this.encoder = new Encoder(Constants.DIO.ENCODER_FLYWHEEL_A, Constants.DIO.ENCODER_FLYWHEEL_B);
		this.encoder.setPIDSourceType(PIDSourceType.kRate);
		this.encoder.setDistancePerPulse(Constants.MISC.ENCODER_FLYWHEEL_DISTANCEPERPULSE);
		
	}
	
	public void run() {
		
		if (encoder.getRate() < Constants.SPEEDS.SPEED_FLYWHEEL_RPS)
			talon_flywheel.set(Constants.SPEEDS.SPEED_FLYWHEEL_VOLTS);
		else
			talon_flywheel.set(0);
		
	}
	
	public void stop() {
		
		talon_flywheel.set(0);
		
	}
	
}
