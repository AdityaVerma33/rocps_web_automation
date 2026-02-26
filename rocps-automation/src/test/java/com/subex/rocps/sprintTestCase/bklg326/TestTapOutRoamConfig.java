package com.subex.rocps.sprintTestCase.bklg326;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestTapOutRoamConfig extends PSAcceptanceTest {
	
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG326_TAPOutRoamConfig";
	
	@Test( priority = 5, enabled = true, description = "'Roaming  Configuration'  creation Tap Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationTapOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestTapOutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
