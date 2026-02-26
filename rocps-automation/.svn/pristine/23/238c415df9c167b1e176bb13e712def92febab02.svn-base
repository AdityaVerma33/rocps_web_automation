package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.BCRProduct;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCBCRProduct extends PSAcceptanceTest
{

String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "BCRProduct";

	@Test( priority = 1, enabled = true, description = "'BCR Product'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			BCRProduct bcrProduct=new BCRProduct( path, workBookName, sheetName, "BCRProductScreencolVal" );
			bcrProduct.bcrProductColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
