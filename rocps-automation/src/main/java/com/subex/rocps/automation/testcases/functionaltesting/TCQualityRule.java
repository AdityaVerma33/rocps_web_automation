package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QualityRule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQualityRule extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QualityRule";

	@Test( priority = 1, enabled = true, description = "'Quality Rule '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			QualityRule qualityRule = new QualityRule( path, workBookName, sheetName, "QualityRuleScreencolVal" );
			qualityRule.qualityRuleColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
