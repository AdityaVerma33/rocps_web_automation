package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.quality.QualityMetric;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCQualityMetric extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "QualityMetric";

	@Test( priority = 1, enabled = true, description = "'Quality Metric '  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{

			QualityMetric qualityMetric = new QualityMetric( path, workBookName, sheetName, "QualityMetricScreencolVal" );
			qualityMetric.qualityMetricColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
