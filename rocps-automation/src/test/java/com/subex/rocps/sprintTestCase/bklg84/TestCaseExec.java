package com.subex.rocps.sprintTestCase.bklg84;

import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TestCaseExec extends PSAcceptanceTest {
	
	@org.testng.annotations.Test
	public void accCreation() throws Exception
	{

		
		try {
			BilledUsage accObj = new BilledUsage();
			//accObj.selectMinuteConfiguration();
			accObj.checkBillUsageInMinutes();
			//accObj.checkTestBillUsageInMinutes();
			//Alerts accObj = new Alerts();
			//accObj.checkAlert();
			//accObj.checkSavedAlert();
			//BillConfiguration accObj = new BillConfiguration(path);
			//accObj.columnCheck();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
		
		
	}
	
	//@org.testng.annotations.Test(priority=2)
	public void Test() throws Exception
	{
		//URL path = this.getClass().getResource("//src//main//resources");
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workBookName = "TestData.xlsx";
		
		try {
				BillProfile bipObj = new BillProfile(path, workBookName, "Sheet1", "BillProfile");
				bipObj.billProfileCreation();
				
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		
		
	}

}
