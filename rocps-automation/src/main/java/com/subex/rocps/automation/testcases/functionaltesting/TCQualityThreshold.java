package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QualityThreshold;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQualityThreshold extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QualityThreshold";

	@Test( priority = 1, enabled = true, description = "' Quality Threshold'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			QualityThreshold qualityThreshold = new QualityThreshold( path, workBookName, sheetName, "QualityThresholdScreencolVal" );
			qualityThreshold.qualityThresholdColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
