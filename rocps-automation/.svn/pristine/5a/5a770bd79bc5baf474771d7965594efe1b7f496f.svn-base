package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.referenceTable.ChargeType;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCChargeType extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "ChargeType";

	@Test( priority = 1, enabled = true, description = "'Charge Type'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			ChargeType chargeType = new ChargeType( path, workBookName, sheetName, "ChargeTypeScreencolVal" );
			chargeType.chargeTypeColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Charge Type' creation" )
	public void chargeTypeCreation() throws Exception
	{
		try
		{
			ChargeType chargeType = new ChargeType( path, workBookName, sheetName, "TestChargeTypeCreation" );
			chargeType.chargeTypeCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "'Charge Type' edit" )
	public void chargeTypeEdit() throws Exception
	{
		try
		{
			ChargeType chargeType = new ChargeType( path, workBookName, sheetName, "TestChargeTypeEdit" );
			chargeType.chargeTypeEdit();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
