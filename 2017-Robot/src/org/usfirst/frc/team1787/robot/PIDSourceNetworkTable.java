package org.usfirst.frc.team1787.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDSourceNetworkTable implements PIDSource {

	String fieldName;
	
	public PIDSourceNetworkTable (String fieldName)
	{
		
		this.fieldName = fieldName;
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return SmartDashboard.getNumber(fieldName, 0);
	}
	
	
	
}
