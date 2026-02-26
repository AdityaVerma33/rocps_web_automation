package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.TadigCodes;
import com.subex.rocps.automation.helpers.application.roaming.RoamingDefinition;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingDefinition extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingDfn";

	@Test( priority = 1, enabled = true, description = "'Tadig Codes'  creation Prerequisite for Roaming Definition", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void tadigCodeCreation() throws Exception
	{
		try
		{

			TadigCodes tadigCodes = new TadigCodes( path, workBookName, sheetName, "TestTadigCodeCreation" );
			tadigCodes.tadigCodesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 2, enabled = true, description = "'Roaming Definition'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			RoamingDefinition roamingDefn = new RoamingDefinition( path, workBookName, sheetName, "RoamingDefnScreencolVal" );
			roamingDefn.roamingDefnColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Roaming Definition' creation for 'Home' Type Of Agreement", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingDefnCreationHomeAgreement() throws Exception
	{
		try
		{

			RoamingDefinition roamingDefn = new RoamingDefinition( path, workBookName, sheetName, "TestRoamingDfnCreationHome" );
			roamingDefn.roamingDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'Roaming Definition' creation for 'Direct' Type Of Agreement", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingDefnCreationDirectAgreement() throws Exception
	{
		try
		{

			RoamingDefinition roamingDefn = new RoamingDefinition( path, workBookName, sheetName, "TestRoamingDfnCreationDirect" );
			roamingDefn.roamingDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'Roaming Definition' creation for 'HUB' Type Of Agreement", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
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

	@Test( priority = 6, enabled = true, description = "'Roaming Definition' creation for 'HUB' Type Of Agreement 'Consider Secondary Tadig for Tap In/ Rap Out'", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void roamingDefnCreationHUBAggWithConsiderSecondTadig() throws Exception
	{
		try
		{

			RoamingDefinition roamingDefn = new RoamingDefinition( path, workBookName, sheetName, "TestRoamingDfnCreationHUB_WithConsiderSecondaryTadig" );
			roamingDefn.roamingDefnCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
