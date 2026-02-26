package com.subex.rocps.sprintTestCase.bklg211;


import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingDiscounting;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCSprintRoamingDiscounting extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG_211RoamingDiscounting";

	@Test( priority = 1, enabled = true, description = "'Roaming Discounting'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoamingDiscounting roamingDiscounting = new RoamingDiscounting( path, workBookName, sheetName, "RoamingDiscountingScreencolVal" );
			roamingDiscounting.roamingDiscountingColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Roaming Discounting'  creation" )
	public void roamingDiscountingCreation() throws Exception
	{
		try
		{
			RoamingDiscounting roamingDiscounting = new RoamingDiscounting( path, workBookName, sheetName, "TestRoamingDiscountingCreation" );
			roamingDiscounting.roamingDiscountingCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Roaming Discounting'  edit" )
	public void roamingDiscountingEdit() throws Exception
	{
		try
		{
			RoamingDiscounting roamingDiscounting = new RoamingDiscounting( path, workBookName, sheetName, "TestRoamingDiscountingEdit" );
			roamingDiscounting.roamingDiscountingEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
