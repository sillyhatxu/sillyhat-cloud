package com.sillyhat.groovy.chapter2.fire1

//import org.junit.Test


//@Test
//public void fireAlarmScenario(){
    FireEarlyWarning fireEarlyWarning = new FireEarlyWarning();
    int triggeredSensors = 1;

    fireEarlyWarning.feedData(triggeredSensors);
    WarningStatus warningStatus = fireEarlyWarning.getCurrentStatus();

    assertTrue("Alarm sounds",warningStatus.isAlarmActive());
    assertFalse("No notifications",warningStatus.isFireDepartmentNotified())
//}