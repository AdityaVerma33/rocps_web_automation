package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingExprGroup;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingExpGroup extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingExpGroup";

	@Test( priority = 1, enabled = true, description = "'Roaming Expression Group'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingExprGroup roamingExprGroup=new RoamingExprGroup( path, workBookName, sheetName, "RoamingExpGroupScreencolVal" );
			roamingExprGroup.roamingExpGrpColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "'Roaming Expression Group'  creation" )
	public void roamingExpGroupCreation() throws Exception
	{
		try
		{
			RoamingExprGroup roamingExprGroup=new RoamingExprGroup( path, workBookName, sheetName, "TestRoamingExpGroupCreation" );
			roamingExprGroup.roamingExpGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
