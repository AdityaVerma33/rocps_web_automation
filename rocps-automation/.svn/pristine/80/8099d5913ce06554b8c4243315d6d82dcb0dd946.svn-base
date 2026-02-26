package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QualityManager;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQualityManager extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QualityManager";

	@Test( priority = 1, enabled = true, description = "' Quality Manager'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			QualityManager qualityManager = new QualityManager( path, workBookName, sheetName, "QualityManagerScreencolVal" );
			qualityManager.qualityManagerColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
