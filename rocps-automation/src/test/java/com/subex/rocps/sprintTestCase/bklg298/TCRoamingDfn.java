package com.subex.rocps.sprintTestCase.bklg298;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.roaming.RoamingConfiguration;
import com.subex.rocps.automation.helpers.application.roaming.RoamingDefinition;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoamingDfn extends PSAcceptanceTest{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG298_hubChangesOfRoamingDfn";

	@Test( priority = 3, description = "'Roaming Definition' creation for 'HUB' Type Of Agreement",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class  )
	public void roamingDefnCreationHUBAgreement() throws Exception
	{
		try
		{
			RoamingDefinition roamingDefn = new RoamingDefinition( path, workBookName, sheetName, "TestRoamingDfnCreationHUB" );
			roamingDefn.roamingDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}