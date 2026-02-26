package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.OfferRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCOfferRule extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "OfferRule";

	@Test( priority = 1, enabled = true, description = "'Offer Rule'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			OfferRule offerRule = new OfferRule( path, workBookName, sheetName, "OfferRuleScreencolVal" );
			offerRule.offerRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
