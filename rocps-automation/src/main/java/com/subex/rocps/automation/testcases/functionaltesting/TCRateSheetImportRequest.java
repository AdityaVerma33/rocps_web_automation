package com.subex.rocps.automation.testcases.functionaltesting;

import com.subex.rocps.automation.helpers.application.matchandrate.PreRatingMatchRule;
import com.subex.rocps.automation.helpers.application.tariffs.RateSheetImportRequest;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCRateSheetImportRequest extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "RateSheetImportRequest";

	@org.testng.annotations.Test( priority = 1, description = "rateSheetImportRequest" )
	public void rateSheetImportRequest() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequest" );
			ratesheetObj.rateSheetImportRequest();
			ratesheetObj.rateSheetRequestResultsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 2, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestOrigin() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequest Origin" );
			ratesheetObj.rateSheetImportRequest();
			ratesheetObj.rateSheetRequestResultsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 3, description = "rateSheetImportRequest- Template" )
	public void rateSheetImportRequestTemplate() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequest Template" );
			ratesheetObj.rateSheetImportRequest();
			ratesheetObj.rateSheetRequestResultsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 4, description = "rateSheetImportRequest column validation" )
	public void rateSheetImportRequestColVal() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetRequestColVal" );
			ratesheetObj.searchScreenColumnsValidation();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 5, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestViewTemplate() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest ViewTemplate" );
			ratesheetObj.ViewTemplate();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 6, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestViewStatistics() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest ViewStatistics" );
			ratesheetObj.ViewStatistics();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 7, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestViewErrors() throws Exception
	{

		try
		{
			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "RateSheet", "TCCapability", 1 );

			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest ViewErrors" );
			ratesheetObj.ViewErrors();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}


	@org.testng.annotations.Test( priority = 8, description = "rateSheetImportRequest- Generate Report" )
	public void rateSheetGenerateReport() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest generateReport" );
			ratesheetObj.generateReport();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}

	@org.testng.annotations.Test( priority = 9, description = "rateSheetImportRequest - Download report" )
	public void rateSheetDownloadReport() throws Exception
	{
		try
		{

			RateSheetImportRequest ratesheet1Obj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest downloadReport" );
			ratesheet1Obj.downloadReport();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}
	@org.testng.annotations.Test( priority = 10, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestCreation2() throws Exception
	{

		try
		{
			RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RateSheetImportRequest-02" );
			ratesheetObj.rateSheetImportRequest();
			ratesheetObj.rateSheetRequestResultsValidation();
			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}
	@org.testng.annotations.Test( priority = 11, description = "rateSheetImportRequest" )
	public void rateSheetImportRequestRejectImport() throws Exception
	{

		try
		{

			RateSheetImportRequest ratesheetObj2 = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequestReject" );
			ratesheetObj2.RejectImport();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			;
			throw e;
		}

	}


	/*@org.testng.annotations.Test( priority = 11, description = "rateSheetImportRequest - Download report" )
	public void rateSheetviewError() throws Exception
	{
		
		
		try {
				RateSheetImportRequest ratesheetObj = new RateSheetImportRequest( path, workBookName, sheetName, "RSImportRequest viewErrors" );
				ratesheetObj.viewErrors();		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);;
			throw e;
		}
		
	}*/
}
