package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsg.EventUsgSeacrhImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class EventUsageHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> eventUsgExcelMap = null;
	protected Map<String, String> eventUsgMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String streamStage;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	// Constructor : Initializing the excel without occurrence
	public EventUsageHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eventUsgExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( eventUsgExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public EventUsageHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;

		eventUsgExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( eventUsgExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Method: Initialize the variables
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );

	}

	// Method: Common method to navigate to the Event Usage screen
	private void eventUsgScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Usage" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Event Usage Search" );
		eventUsgMap = excelHolderObj.dataMap( index );
		initializeVariable( eventUsgMap );

	}

	// Method: Verify the column headers of Event Usage screen
	public void verifyColmnHeaderOfeventUsg() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventUsgSeacrhImpl eventUsgSeacrhImpl = new EventUsgSeacrhImpl( eventUsgMap );
			eventUsgSeacrhImpl.eventUsgFilter();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			eventUsgSeacrhImpl.verifyColmnHeaderOfEveErr();

			Log4jHelper.logInfo( "'Event Usage  screen' Columns are validated successfully" );
		}
	}

	// Method: : Validate the search results of 'Event Usage' screen
	public void validateEventUsgSearchResult() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventUsgSeacrhImpl eventUsgSeacrhImpl = new EventUsgSeacrhImpl( eventUsgMap );
			eventUsgSeacrhImpl.eventUsgFilter();
			eventUsgSeacrhImpl.validateSearchResultEventUsg();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	// Method: : Validate the search results of 'Event Usage' viewTotalAction
	public void validateViewTotalActionResult() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgScreen();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventUsgSeacrhImpl eventUsgSeacrhImpl = new EventUsgSeacrhImpl( eventUsgMap );
			eventUsgSeacrhImpl.eventUsgFilter();
			eventUsgSeacrhImpl.validateSearchResultEventUsg();
			eventUsgSeacrhImpl.viewTotalAction();
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

}
