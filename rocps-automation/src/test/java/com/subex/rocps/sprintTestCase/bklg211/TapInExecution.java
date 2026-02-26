package com.subex.rocps.sprintTestCase.bklg211;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TapInExecution extends PSAcceptanceTest{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG211_TapInRoamingConf";

	@Test( priority = 3, enabled = true, description = "'Roaming  Configuration'  creation Tap In configuration type" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingConfigCreationTapIn() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapInRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}