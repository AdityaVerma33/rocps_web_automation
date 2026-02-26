package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.FloorAndCeilingPrice;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCFloorCeilingPrice extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "FloorCeilingPrice";

	@Test( priority = 1, enabled = true, description = "' Floor And Ceiling Price'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			FloorAndCeilingPrice flAndCeilingPrice = new FloorAndCeilingPrice( path, workBookName, sheetName, "FloorCeilingPriceScreencolVal" );
			flAndCeilingPrice.floorCeilingPriceColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
