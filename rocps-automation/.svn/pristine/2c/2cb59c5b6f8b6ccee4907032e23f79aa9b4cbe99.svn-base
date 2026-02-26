package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.RoamingTaxType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCRoamingTaxType extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RoamingTaxType";

	@Test( priority = 1, enabled = true, description = "'Roaming Tax Type'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			RoamingTaxType roamingTaxType = new RoamingTaxType( path, workBookName, sheetName, "RoamingTaxTypeScreencolVal" );
			roamingTaxType.roamingTaxTypeColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Roaming Tax Type'  creation" )
	public void roamingTaxTypeCreation() throws Exception
	{
		try
		{

			RoamingTaxType roamingTaxType = new RoamingTaxType( path, workBookName, sheetName, "TestRoamingTaxTypeCreation" );
			roamingTaxType.roamingTaxTypeCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	@Test( priority = 3, enabled = true, description = "'Roaming Tax Type'  edit" )
	public void roamingTaxTypeEdit() throws Exception
	{
		try
		{

			RoamingTaxType roamingTaxType = new RoamingTaxType( path, workBookName, sheetName, "TestRoamingTaxTypeEdit" );
			roamingTaxType.roamingTaxTypeEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
