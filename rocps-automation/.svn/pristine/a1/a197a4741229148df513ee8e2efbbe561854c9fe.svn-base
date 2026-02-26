package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.referenceTable.RoamingSerMatchExpresion;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRoamingReferenceTablesDeleteAction extends PSAcceptanceTest

{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";

	@Test( priority = 1, enabled = true, description = "'Roaming Service Match Expression' Delete action" )
	public void roamingServMatchExprDelete() throws Exception
	{
		try
		{
			RoamingSerMatchExpresion roamingSerMatchExpresion = new RoamingSerMatchExpresion( path, workBookName, "RoamingServMatchExpr", "TestRoamingServMatchExpDelete" );
			roamingSerMatchExpresion.roamServMatchExpressionDelete();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
