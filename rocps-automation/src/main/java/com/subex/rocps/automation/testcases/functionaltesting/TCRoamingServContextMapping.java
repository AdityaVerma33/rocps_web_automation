package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingExprGroup;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingSerContextMapping;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingService;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingServContextMapping extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingServContMapping";

	@Test( priority = 1, enabled = true, description = "'Roaming Service context mapping'  prerequisite create Roaming Expression Group,Roaming service" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void roamingExpGrpPrerequisite() throws Exception
	{
		try
		{
			RoamingService roamingService=new RoamingService( path, workBookName, "RoamingService", "TestRoamingServiceCreation" );
			roamingService.roamingServiceCreation();
			RoamingExprGroup roamingExprGroup=new RoamingExprGroup( path, workBookName, "RoamingExpGroup", "TestRoamingExpGroupCreation" );
			roamingExprGroup.roamingExpGrpCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 2, enabled = true, description = "'Roaming Service context mapping'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			RoamingSerContextMapping roamingSerContextMapping = new RoamingSerContextMapping( path, workBookName, sheetName, "RoamingServContMappingScreencolVal" );
			roamingSerContextMapping.roamServContMapColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Roaming Service context mapping'  creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingServContMappingCreation() throws Exception
	{
		try
		{

			RoamingSerContextMapping roamingSerContextMapping = new RoamingSerContextMapping( path, workBookName, sheetName, "TestRoamingServContMappingCreation" );
			roamingSerContextMapping.RoamingServContMappingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
