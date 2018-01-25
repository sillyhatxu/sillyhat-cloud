package com.sillyhat.groovy.chapter2.fire1;

public class FireEarlyWarning {
	
	private int sensorsDetectingFire = 0;

	public void feedData(int triggeredFireSensors)
	{
		sensorsDetectingFire = triggeredFireSensors;
	}
	
	public WarningStatus getCurrentStatus()
	{
		return new WarningStatus(sensorsDetectingFire>0,sensorsDetectingFire>1);
	}
}
