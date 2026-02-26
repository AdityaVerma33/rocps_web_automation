package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.eventandaggregation.EventAndAggregation;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventAndAggregation extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventAndAggregation";

	@org.testng.annotations.Test( priority = 1, description = "EventandAggregation" )
	public void configureEventAndAggregation() throws Exception
	{
		try
		{
			EventAndAggregation evtAgg = new EventAndAggregation( path, workBookName, sheetName, "EventAndAggregationConfig" );
			evtAgg.createEventAndAggConfiguration();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
		}

	}

}
