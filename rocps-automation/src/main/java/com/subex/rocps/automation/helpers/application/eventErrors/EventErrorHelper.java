package com.subex.rocps.automation.helpers.application.eventErrors;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.eventErrors.eventError.EventErrorSearchImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class EventErrorHelper extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> eventErrorExcelMap = null;
	protected Map<String, String> eventErrorMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String columnName;
	protected String rowValues;
	protected String errorType;
	protected String groupBy;
	protected String taskEvaluation;
	protected String changeStatusTo;
	protected String assignTo;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	// Constructor : Initializing the excel without occurrence
	public EventErrorHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eventErrorExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( eventErrorExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public EventErrorHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		eventErrorExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( eventErrorExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Method: Initialize the variables for Reprocess suspense error
	protected void initializeVariableForReprocess( Map<String, String> map ) throws Exception
	{
		columnName = ExcelHolder.getKey( map, "ColumnName" );
		rowValues = ExcelHolder.getKey( map, "RowValues" );
		taskEvaluation = ExcelHolder.getKey( map, "TaskEvalution" );
		errorType = ExcelHolder.getKey( map, "ErrorType" );
	}

	// Method: Common method to navigate to the Event Error screen
	private void eventErrorScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Error" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Event Error Search" );
		eventErrorMap = excelHolderObj.dataMap( index );
	}

	// Method: Verify the column headers of Event Error screen
	public void verifyTheColHdrsOfEveErrSrch() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.verifyColmnHeaderOfEveErr();
			Log4jHelper.logInfo( "Event Error Columns are validated successfully" );
		}
	}

	// Method: Validate the search results of Event Error screen
	public void validateSearchResult() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.validateSearchResult();
			Log4jHelper.logInfo( "Event Error results are validated successfully" );
		}

	}

	// Method: Changing the status of Event error
	public void changeStatusAction() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			changeStatusTo = ExcelHolder.getKey( eventErrorMap, "ChangeStatusTo" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.validateSearchResult();
			if ( ValidationHelper.isNotEmpty( changeStatusTo ) )
				eveErrorSrchImpl.changeStatusAction( changeStatusTo );
			if ( index > 0 )
				Log4jHelper.logInfo( "The status are changed succesfully completed: " );
		}

	}

	// Method: Assign to the user
	public void assignToAction() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			assignTo = ExcelHolder.getKey( eventErrorMap, "AssignToAction" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.validateSearchResult();
			if ( ValidationHelper.isNotEmpty( assignTo ) )
				eveErrorSrchImpl.assignToAction( assignTo );
			if ( index > 0 )
				Log4jHelper.logInfo( "Assign To action is successfully completed: " );
		}

	}

	// Method: Reprocess the suspense error
	public void reprocessSuspensErr() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			initializeVariableForReprocess( eventErrorMap );
			assertEquals( errorType, "Suspense Error", "Error Type is not  a Suspense Error" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.validateSearchResult();
			if ( ValidationHelper.isNotEmpty( columnName ) && ValidationHelper.isNotEmpty( rowValues ) && ValidationHelper.isNotEmpty( taskEvaluation ) )
			{
				eveErrorSrchImpl.reprocessSuspenseError();
			}
			if ( index > 0 )
				Log4jHelper.logInfo( "----\nEvent Error  reprocess the Suspense Error  successfully completed\n" );

		}

	}

	// Method: Reprocess the suspense error in diagnoistic mode
	public void reprocessSuspensErrinDiagMd() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			eventErrorScreen();
			initializeVariableForReprocess( eventErrorMap );
			groupBy = ExcelHolder.getKey( eventErrorMap, "GroupBy" );

			assertEquals( errorType, "Suspense Error", "Error Type is not Suspense Error" );
			assertEquals( groupBy, "(All)", "Group By is not (All)" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
			eveErrorSrchImpl.eveErrFilterSearch();
			eveErrorSrchImpl.validateSearchResult();

			if ( ValidationHelper.isNotEmpty( columnName ) && ValidationHelper.isNotEmpty( rowValues ) && ValidationHelper.isNotEmpty( taskEvaluation ) )
				eveErrorSrchImpl.reprocessSuspenseErrorInDiagMd();

			if ( index > 0 )
				Log4jHelper.logInfo( "----\nEvent Error reprocess in Diagnostic mode successfully completed\n" );
		}

	}
	
	// Method: Reprocess the Rate error
		public void reprocessRateErr() throws Exception
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventErrorScreen();
				initializeVariableForReprocess( eventErrorMap );
				assertEquals( errorType, "Rate Error", "Error Type is not  a Rate Error" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				EventErrorSearchImpl eveErrorSrchImpl = new EventErrorSearchImpl( eventErrorMap );
				eveErrorSrchImpl.eveErrFilterSearch();
				eveErrorSrchImpl.validateSearchResult();
				if ( ValidationHelper.isNotEmpty( columnName ) && ValidationHelper.isNotEmpty( rowValues ) && ValidationHelper.isNotEmpty( taskEvaluation ) )
				{
					eveErrorSrchImpl.reprocessRateError();
				}
				if ( index > 0 )
					Log4jHelper.logInfo( "----\nEvent Error  reprocess the Rate Error  successfully completed\n" );

			}

		}
}
