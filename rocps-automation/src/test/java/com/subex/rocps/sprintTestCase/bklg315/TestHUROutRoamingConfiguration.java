package com.subex.rocps.sprintTestCase.bklg315;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestHUROutRoamingConfiguration extends PSAcceptanceTest {
	
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG315_HUROutRoamConfig";
	
	@Test( priority = 9, enabled = true, description = "'Roaming  Configuration'  creation HUR Out configuration type", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingConfigCreationHurOut() throws Exception
	{
		try
		{
			RoamingConfiguration roamingConfiguration = new RoamingConfiguration( path, workBookName, sheetName, "TestHUROutRoamingConfigCreation" );
			roamingConfiguration.roamingConfigCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
