package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.arms.NetworkExclusion;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCNetworkAnalysis extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "NetworkExclusion";

	@Test( priority = 1, enabled = true, description = "'  Network Exclusion'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			NetworkExclusion networkExclusion = new NetworkExclusion( path, workBookName, sheetName, "NetworkExclusionScreencolVal" );
			networkExclusion.networkExclusionColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
