package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.Sales.MarkupRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCMarkupRule extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "MarkupRule";

	@Test( priority = 1, enabled = true, description = "'Markup Rule'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			MarkupRule markupRule=new MarkupRule( path, workBookName, sheetName, "MarkupRuleScreencolVal" );
			markupRule.markupRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
