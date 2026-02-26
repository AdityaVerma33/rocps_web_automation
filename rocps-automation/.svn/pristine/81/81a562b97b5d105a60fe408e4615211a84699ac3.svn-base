package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.accruals.GLCodeDefn;
import com.subex.rocps.automation.helpers.application.referenceTable.TadigCodes;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.util.FailureHelper;

public class TCTadigCodes extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "TadigCodes";

	@Test( priority = 1, enabled = true, description = "'Tadig Codes'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			TadigCodes tadigCodes=new TadigCodes( path, workBookName, sheetName, "TadigCodeScreencolVal" );
			tadigCodes.tadigCodeColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "'Tadig Codes'  creation" )
	public void tadigCodeCreation() throws Exception
	{
		try
		{

			TadigCodes tadigCodes=new TadigCodes( path, workBookName, sheetName, "TestTadigCodeCreation" );
			tadigCodes.tadigCodesCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
