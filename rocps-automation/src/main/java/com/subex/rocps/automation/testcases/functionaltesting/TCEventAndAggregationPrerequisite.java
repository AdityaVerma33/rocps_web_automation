package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventAndAggregationPrerequisite extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventAndAggregationPrerequisite";

	@org.testng.annotations.Test( priority = 2, enabled = true, description = " Voice Surcharge stream stages creation, Parse, MNR", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void createVoiceSurchargeStreamStages() throws Exception
	{
		try
		{

			Streams streamObj = new Streams();
			streamObj.editStreamConfig( path, workBookName, sheetName, "Streams", 1 );
			streamObj.eventAndAggStreamStageConfig( path, workBookName, sheetName, "EventandAggregationConfigStreamSTage", 1 );
			streamObj.saveStreamDetail();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

}
