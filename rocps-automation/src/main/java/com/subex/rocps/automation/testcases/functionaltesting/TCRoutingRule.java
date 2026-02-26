package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.RoutingRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoutingRule extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RoutingRule";

	@Test( priority = 1, enabled = true, description = "'Routing Rule '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RoutingRule routingRule = new RoutingRule( path, workBookName, sheetName, "RoutingRuleScreencolVal" );
			routingRule.routingRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
