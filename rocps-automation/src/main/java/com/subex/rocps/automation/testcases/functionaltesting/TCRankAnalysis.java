package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bcrManagement.RankAnalysis;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCRankAnalysis extends PSAcceptanceTest
{

	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCasesRO.xlsx";
	String sheetName = "RankAnalysis";

	@Test( priority = 1, enabled = true, description = "' Rank Analysis'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			RankAnalysis rankAnalysis = new RankAnalysis( path, workBookName, sheetName, "RankAnalysisScreencolVal" );
			rankAnalysis.rankAnalysisColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
