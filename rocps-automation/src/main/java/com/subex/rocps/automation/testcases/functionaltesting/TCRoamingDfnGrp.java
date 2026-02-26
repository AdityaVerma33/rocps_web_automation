package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDefnGroup;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoamingDfnGrp extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingDfnGrp";

	@Test( priority = 1, enabled = true, description = "'Roaming Definition Group'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingDefnGroup roamingDefnGroup = new RoamingDefnGroup( path, workBookName, sheetName, "RoamingDfnGrpScreencolVal" );
			roamingDefnGroup.roamingDfnGrpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Roaming Definition Group' creation" )
	public void roamingDfnGrpCreation() throws Exception
	{
		try
		{
			RoamingDefnGroup roamingDefnGroup = new RoamingDefnGroup( path, workBookName, sheetName, "TestRoamingDfnGrpCreation" );
			roamingDefnGroup.roamingDfnGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
