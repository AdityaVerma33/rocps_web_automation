package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventType extends PSAcceptanceTest{

	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventType";

	@org.testng.annotations.Test(priority = 1, description = "EventType creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void eventTypeCreation() throws Exception {
		try {
			EventType eventObj = new EventType(path, workBookName, sheetName, "EventType", 1);
			eventObj.eventTypeCreation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
	
	@org.testng.annotations.Test(priority = 2, description = "EventType deletion", dependsOnMethods = "eventTypeCreation")
	public void eventTypeDelete() throws Exception {
		try {
			EventType eventdelObj = new EventType(path, workBookName, sheetName, "EventTypeDelete", 1);
			eventdelObj.eventTypeDelete();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
	
	@org.testng.annotations.Test(priority = 3, description = "EventType un delete", dependsOnMethods = "eventTypeDelete")
	public void eventTypeUnDelete() throws Exception {
		try {
			EventType eventundelObj = new EventType(path, workBookName, sheetName, "EventTypeUnDelete", 1);
			eventundelObj.eventTypeUnDelete();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
	
	@org.testng.annotations.Test(priority = 4, description = "EventType search screen column validation")
	public void eventTypeColVal() throws Exception {
		try {
			EventType eventColValObj = new EventType(path, workBookName, sheetName, "EventTypeSearchScreencolVal", 1);
			eventColValObj.searchScreenColumnsValidation();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}
