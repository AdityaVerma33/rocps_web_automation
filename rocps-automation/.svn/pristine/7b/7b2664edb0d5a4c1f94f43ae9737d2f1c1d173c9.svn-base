package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.bills.RerateRequest;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.BillProfile;
import com.subex.rocps.automation.helpers.application.system.Streams;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TriggerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRerateRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RerateRequest";

	@org.testng.annotations.Test( priority = 1, description = "bill profile- Invoice creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void billProfileCreation() throws Exception
	{
		try
		{
			BillProfile billObj = new BillProfile( path, workBookName, "BillProfile", "BillProfileInvoice", 1 );
			billObj.billProfileCreation();

			BillProfile bill1Obj = new BillProfile( path, workBookName, "BillProfile", "BillProfileAllFields", 1 );
			bill1Obj.billProfileCreation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, description = "Rerate request - 3rd level creation",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rerateRequest3rdLevelCreation() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate", 1 );
			rerateObj.rerateRequestCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, description = "Rerate request creation - 2nd level" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void rerateRequest2ndlevelCreation() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate 2ndLevel", 1 );
			rerateObj.rerateRequestCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 4, description = "Rerate request creation - 1stnd level",retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void rerateRequest1stlevelCreation() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate 1ndLevel", 1 );
			rerateObj.rerateRequestCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	//@org.testng.annotations.Test( priority = 5, description = "Rerate request creation - Schedule" )
	public void rerateRequestschedule() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate Schedule", 1 );
			rerateObj.scheduleRerateRequest();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 6, description = "Rerate request creation - saveAs" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void rerateRequestSaveAs() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate SaveAS", 1 );
			rerateObj.saveASRerateRequest();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 7, description = "Rerate request creation - saveAs" )
	public void rerateRequestColVal() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRateColVal", 1 );
			rerateObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 8, description = "Rerate request creation - Multi event Type" ,retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void rerateRequest1stlevelMultiEventCreation() throws Exception
	{
		try
		{
			RerateRequest rerateObj = new RerateRequest( path, workBookName, sheetName, "ReRate MultiEventType", 1 );
			rerateObj.rerateRequestCreation();

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 9, description = "add rerate stream stages", retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class )
	public void editVoiceStreamRerate() throws Exception
	{

		try
		{
			Streams streamObj = new Streams();
			if ( streamObj.editStreamConfig( path, workBookName, "RerateServer", "VoiceStreamsRerate", 1 ) )
			{
				streamObj.rerateStreamConfig( path, workBookName, "RerateServer", testCaseName, 1 );
				streamObj.saveStreamDetail();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 10, description = "Trigger creation" , retryAnalyzer = com.subex.rocps.automation.helpers.listener.Retry.class)
	public void createTrigger() throws Exception
	{
		try
		{

			TriggerHelper triggerObj = new TriggerHelper();
			triggerObj.createTrigger( path, workBookName, "RerateServer", "Rerate Trigger", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
