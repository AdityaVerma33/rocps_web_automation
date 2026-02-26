package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.EventUsgRequestScheduler;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TCEventUsgReqScheduler extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";

	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "EventUsgReqScheduler";

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
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "CreateEventUsgReqScheWithEntities" );
			eventUsgRequestScheduler.eventUsgReqSchedulerCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 3, enabled = true, description = "Event Usage Request Scheduler creation Without Entities", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventUsgReqSchedulerCreationWithoutEntities() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "CreateEventUsgReqScheWithOutEntities" );
			eventUsgRequestScheduler.eventUsgReqSchedulerCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "Event Usage Request Scheduler edit", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventUsgReqSchedulerEdit() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "EditEventUsgReqScheduler" );
			eventUsgRequestScheduler.eventUsgReqSchedulerEdit();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "Event Usage Request Scheduler save as", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventUsgReqSchedulerSaveAs() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "SaveAsEventUsgReqScheduler" );
			eventUsgRequestScheduler.eventUsgReqSchedulerSaveAs();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "Event Usage Request Scheduler View Template", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void eventUsgReqScheduler_ViewTemplate() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "CreateEventUsgReqScheWithViewTemplate" );
			eventUsgRequestScheduler.eventUsgReqSchedulerCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 7, enabled = true, description = "Event Usage Request Scheduler Delete" )
	public void eventUsgReqSchedulerDelete() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "DeleteEventUsgReqScheduler" );
			eventUsgRequestScheduler.eventUsgReqSchedulerDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 8, enabled = true, description = "Event Usage Request Scheduler UnDelete" )
	public void eventUsgReqSchedulerUnDelete() throws Exception
	{
		try
		{
			EventUsgRequestScheduler eventUsgRequestScheduler = new EventUsgRequestScheduler( path, workBookName, sheetName, "UnDeleteEventUsgReqScheduler" );
			eventUsgRequestScheduler.eventUsgReqSchedulerUnDelete();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
