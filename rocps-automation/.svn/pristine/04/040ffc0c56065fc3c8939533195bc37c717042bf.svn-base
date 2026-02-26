package com.subex.rocps.sprintTestCase.bklg254;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestScheduler;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventUsgReqScheduler extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "PSSprintFunctional_ExcelData.xlsx";
	String sheetName = "BKLG254_EventUsgReqScheduler";

	@Test( priority = 1, enabled = true, description = "Event Usage Request Scheduler verify the column Headers Of Search Screen" )
	public void verifyTheColHdrs() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "EventUsgReqSchedulerSearchScreencolVal" );
			eventUsgRequestScheduler.eventUsgReqSchedulerColumsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	@Test( priority = 2, enabled = true, description = "Event Usage Request Scheduler creation With Entities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventUsgReqSchedulerCreationWithEntities() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "CreateEventUsgReqScheWithoutEntities" );
			eventUsgRequestScheduler.eventUsgReqSchedulerCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	
}
