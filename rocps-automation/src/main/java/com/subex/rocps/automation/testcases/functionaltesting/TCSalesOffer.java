package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.SalesOffer;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCSalesOffer extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "SalesOffer";

	@Test( priority = 1, enabled = true, description = "'Sales  Offer'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			SalesOffer salesOffer=new SalesOffer( path, workBookName, sheetName, "SalesOfferScreencolVal" );
			salesOffer.salesOfferColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
