package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingTaxation;
import com.subex.rocps.automation.helpers.application.roaming.IMSIManagement;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTaxation extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTaxation";

	@Test( priority = 1, enabled = true, description = "'Roaming Taxation'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingTaxation roamingTaxation =new RoamingTaxation( path, workBookName, sheetName, "RoamingTaxationScreencolVal" );
			roamingTaxation.roamingTaxationColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Roaming Taxation'  creation" )
	public void roamingTaxationCreation() throws Exception
	{
		try
		{
			RoamingTaxation roamingTaxation =new RoamingTaxation( path, workBookName, sheetName, "TestRoamingTaxationCreation" );
			roamingTaxation.roamingTaxationCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 3, enabled = true, description = "'Roaming Taxation'  edit" )
	public void roamingTaxationEdit() throws Exception
	{
		try
		{
			RoamingTaxation roamingTaxation =new RoamingTaxation( path, workBookName, sheetName, "TestRoamingTaxationEdit" );
			roamingTaxation.roamingTaxationEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
