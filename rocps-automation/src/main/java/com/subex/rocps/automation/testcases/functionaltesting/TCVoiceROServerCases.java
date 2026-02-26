package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCVoiceROServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RO_Prerequisites";

	@org.testng.annotations.Test( priority = 1, description = "task Controller capabilities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability_Rerate", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability_Ratesheet", 1 );
			taskObj.setTaskControllerCapability( path, workBookName, sheetName, "TCCapability_RO", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
