package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingTaxIndicator;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTaxIndicator extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTaxIndicator";

	@Test( priority = 1, enabled = true, description = "'RoamingTaxIndicator'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			RoamingTaxIndicator roamingTaxIndicator = new RoamingTaxIndicator( path, workBookName, sheetName, "RoamingTaxIndicatorScreencolVal" );
			roamingTaxIndicator.roamingTaxIndicatorColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	

	@Test( priority = 2, enabled = true, description = "'RoamingTaxIndicator'  creation" )
	public void roamingTaxIndicatorCreation() throws Exception
	{
		try
		{

			RoamingTaxIndicator roamingTaxIndicator = new RoamingTaxIndicator( path, workBookName, sheetName, "TestRoamTaxIndicatorCreation" );
			roamingTaxIndicator.RoamingTaxIndicatorCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'RoamingTaxIndicator'  edit" )
	public void roamingTaxIndicatorEdit() throws Exception
	{
		try
		{

			RoamingTaxIndicator roamingTaxIndicator = new RoamingTaxIndicator( path, workBookName, sheetName, "TestRoamTaxIndicatorEdit" );
			roamingTaxIndicator.RoamingTaxIndicatorEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
