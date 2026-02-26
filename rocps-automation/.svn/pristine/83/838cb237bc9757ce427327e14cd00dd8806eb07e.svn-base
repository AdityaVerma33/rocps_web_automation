package com.subex.rocps.automation.helpers.application.eventandaggregation;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventAndAggregation extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG322_EventAndAggregation";
	String sheetName1="EventAndAggPrerequisite";

    @org.testng.annotations.Test( priority = 1, description = "EventandAggregation" )
	public void configureEventAndAggregation() throws Exception{
		try {
			EventAndAggregation evtAgg=new EventAndAggregation( path, workBookName, sheetName, "EventAndAggregationConfig" );
			evtAgg.createEventAndAggConfiguration();
			
		}
		catch(Exception e) {
			FailureHelper.reportFailure( e );
		}
		
	}
    
    @org.testng.annotations.Test( priority = 2, description = "EventandAggregation" )
   	public void configureMultipleEventAndAggregation() throws Exception{
   		try {
   			EventAndAggregation evtAgg=new EventAndAggregation( path, workBookName, sheetName, "EventAndAggregationConfig2" );
   			evtAgg.createEventAndAggConfiguration();
   			
   		}
   		catch(Exception e) {
   			FailureHelper.reportFailure( e );
   		}
   		
   	}
	
	
}
