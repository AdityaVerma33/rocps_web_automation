package com.subex.rocps.automation.testcases.functionaltesting;

import org.testng.annotations.Test;

import com.subex.rocps.automation.helpers.application.aggregation.AggregationResult;
import com.subex.rocps.automation.helpers.application.exchangeRates.ImfExchangeRateImport;
import com.subex.rocps.automation.helpers.application.matchandrate.CrossFXRate;
import com.subex.rocps.automation.helpers.application.monitoring.PSCollectedFileSearchHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.application.system.TaskSchedule;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TCIMFServerCases extends PSAcceptanceTest
{
	String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
	String workBookName = "FunctionalTestCases.xlsx";
	String sheetName = "IMFServerCases";

	@org.testng.annotations.Test( priority = 1, enabled = true, description = "task Controller capabilities" )
	public void taskControllerCapabilities() throws Exception
	{
		try
		{

			TaskControllerHelper taskObj = new TaskControllerHelper();
			taskObj.setTaskControllerCapability( path, workBookName, "IMFPrerequisite", "TCCapability", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 2, enabled = true, description = "Schedule and verify Recurring task for  IMFExchangeRateFileDownload " )
	public void scheduleVerifyRecurringImfExchRateFlDownload() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			taskObj.scheduleRecurringTask( path, workBookName, sheetName, "RecurringImfExchDownloadTask", 1 );
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "ImfExchDownloadTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@org.testng.annotations.Test( priority = 3, enabled = true, description = "IMF Exchange Rate import ,File collection and task verification for  IMFExchangeRateImport" )
	public void imfExchRateImportScheduleFlCollection() throws Exception

	{
		try
		{
			TaskSchedule taskObj = new TaskSchedule();
			taskObj.scheduleFileCollection( path, workBookName, sheetName, "FileSchedule_ImfExchRateImport", 1 );
			PSCollectedFileSearchHelper collectedFlObj = new PSCollectedFileSearchHelper( path, workBookName, sheetName, "CollectedFileSearch_ImfExchRateImport" );
			collectedFlObj.validationOfCollectedFileWithoutFileNm();
			PSTaskSearchHelper psTaskobj = new PSTaskSearchHelper();
			psTaskobj.psVerifyTaskStatus( path, workBookName, sheetName, "IMFExchangeRateImportTaskStatus", 1 );

		}
		catch ( Exception e )
		{
			FailureHelper.reportFailure( e );
			throw e;
		}
	}

	@Test( priority = 4, enabled = true, description = "'IMF Exchange Rate import'  verify the column Headers Of Search Screen" )
	public void verifyTheColHdrsImfExchangeRateImport() throws Exception
	{
		try
		{
			ImfExchangeRateImport imfExchangeRateImport = new ImfExchangeRateImport( path, workBookName, sheetName, "ImfExchRateImportScreencolVal" );
			imfExchangeRateImport.imfExchRateImportColumsValidation();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 5, enabled = true, description = "'IMF Exchange Rate import'  validate the exchange rates action search result" )
	public void validateExchangeRatesResultImf() throws Exception
	{
		try
		{
			ImfExchangeRateImport imfExchangeRateImport = new ImfExchangeRateImport( path, workBookName, sheetName, "ImfExchRateImport_ExchRateValidateResult" );
			imfExchangeRateImport.validateImfExchRateImpSearchResult();
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	@Test( priority = 6, enabled = true, description = "'Cross Fx Rates'  validate the rates of Imf Currency name " )
	public void validateCrosssFxRatesIMF() throws Exception
	{
		try
		{
			CrossFXRate crossFXRate = new CrossFXRate( path, workBookName, sheetName, "CrossFXRate_Validation" );
			crossFXRate.validateCrossFxRateSearchResult();

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
}
