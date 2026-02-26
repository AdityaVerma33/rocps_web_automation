package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgRequest.EventUsgReqActionImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgRequest.EventUsgReqDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSFileValidationHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventUsgRequestHelper extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> eventUsgReqExcelMap = null;
	protected Map<String, String> eventUsgReqMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected static String bookName;
	protected static String pathWB;
	protected String sheetName;
	protected static String sheetNm;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String description;
	protected String newDescription;
	protected String autoScheduleFlg;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	EventUsgReqActionImpl eventUsgReqActionOb = new EventUsgReqActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSFileValidationHelper psFileValidationHelper = new PSFileValidationHelper();

	// Constructor :default constructor
	public EventUsgRequestHelper()
	{

	}

	// Constructor : Initializing the excel without occurrence
	public EventUsgRequestHelper( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eventUsgReqExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( eventUsgReqExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	// Constructor : Initializing the excel with occurrence
	public EventUsgRequestHelper( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		eventUsgReqExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( eventUsgReqExcelMap );
		colSize = excelHolderObj.totalColumns();

	}

	// Method: Initialize the variables
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		description = ExcelHolder.getKey( map, "Description" );
	}

	// Method: Common method to navigate to the Event Usage Request screen
	private void eventUsgReqScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Usage Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Event Usage Request..." );
		eventUsgReqMap = excelHolderObj.dataMap( index );
		initializeVariable( eventUsgReqMap );
	}

	// Method: Verify the column headers of Event Usage Request screen
	public void verifyColmnHeaderOfeventUsgReq() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			NavigationHelper.navigateToScreen( "Event Usage Request" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertEquals( NavigationHelper.getScreenTitle(), "Event Usage Request..." );
			eventUsgReqMap = excelHolderObj.dataMap( index );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			EventUsgReqDetailImpl eventUsgReqDetailOb = new EventUsgReqDetailImpl( eventUsgReqMap );
			eventUsgReqDetailOb.verifyColmnHeaderOfEventUsgReq();
			Log4jHelper.logInfo( "'Event Usage Request screen' Columns are validated successfully" );
		}
	}

	// Method: Event Usage Request creation
	public void creationOfeventUsgReq() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgReqScreen();
			initializeWorkbookNmSheetNm();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int currentRowCount = GridHelper.getRowCount( "searchGrid" );
			if ( !iseventUsgReqPresent( description ) )
			{
				EventUsgReqDetailImpl eventUsgReqDetailOb = new EventUsgReqDetailImpl( eventUsgReqMap );
				EventUsgReqActionImpl eventUsgReqActionOb = new EventUsgReqActionImpl();
				eventUsgReqActionOb.clickNewAction( clientPartition, "Event Usage Request" );
				eventUsgReqDetailOb.configEventUsgRequest( description );
				eventUsgReqDetailOb.saveEventUsgReq( description, currentRowCount );
				assertTrue( iseventUsgReqPresent( description ), " Description is not found" );
				checkForAutoSchedule();
				Log4jHelper.logInfo( "'Event Usage Request' is successfully saved  with the description name:- " + description );
			}
			else
			{
				Log4jHelper.logInfo( "'Event Usage Request' is availabe with the description name:- " + description );
			}

		}
	}

	// Method: Event Usage Request Scheduler
	public void scheduleEventUsgReq() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgReqScreen();
			initializeWorkbookNmSheetNm();
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int currentRowCount = GridHelper.getRowCount( "searchGrid" );
			if ( !iseventUsgReqPresent( description ) )
			{
				EventUsgReqDetailImpl eventUsgReqDetailOb = new EventUsgReqDetailImpl( eventUsgReqMap );
				EventUsgReqActionImpl eventUsgReqActionOb = new EventUsgReqActionImpl();
				eventUsgReqActionOb.clickNewAction( clientPartition, "Event Usage Request" );
				eventUsgReqDetailOb.configEventUsgRequest( description );
				eventUsgReqDetailOb.saveEventUsgReq( description, currentRowCount );
				assertTrue( iseventUsgReqPresent( description ), " Description is not found" );
				scheduleRequestAction( description );
				int row = geteventUsgReqDescriptionRow( description );
				psDataComponentHelper.waitForTaskCompletion( row );
				validateExportFile();
				Log4jHelper.logInfo( "'Event Usage Request' is successfully scheduled  with the description name:- " + description );
			}
			else
			{
				Log4jHelper.logInfo( "'Event Usage Request' is availabe with the description name:- " + description );
				int row = geteventUsgReqDescriptionRow( description );
				String currentTaskStatus = GridHelper.getCellValue( "SearchGrid", row, "Task Status" ).trim();
				if ( currentTaskStatus.equals( "Unscheduled" ) )
				{
					scheduleRequestAction( description );
					row = geteventUsgReqDescriptionRow( description );
					psDataComponentHelper.waitForTaskCompletion( row );
					validateExportFile();
				}
				else
					Log4jHelper.logInfo( "'Event Usage Request' is already in '" + currentTaskStatus + "' status with the description name:- " + description );

			}

		}
	}

	// Method: Event Usage Request creation with save as
	public void creationOfeventUsgReqWithSaveAs() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			eventUsgReqScreen();
			initializeWorkbookNmSheetNm();
			newDescription = ExcelHolder.getKey( eventUsgReqMap, "NewDescription" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "SearchButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			int row = GridHelper.getRowCount( "SearchGrid" );
			if ( iseventUsgReqPresent( description ) )
			{
				EventUsgReqDetailImpl eventUsgReqDetailOb = new EventUsgReqDetailImpl( eventUsgReqMap );
				GridHelper.clickRow( "SearchGrid", geteventUsgReqDescriptionRow( description ), "Description" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				eventUsgReqActionOb.clickSaveAsAction( clientPartition, "Event Usage Request", description );
				eventUsgReqDetailOb.configSaveAsEventUsgRequest( description, newDescription );
				eventUsgReqDetailOb.saveEventUsgReq( newDescription, row );
				assertTrue( iseventUsgReqPresent( newDescription ), " Description is not found" );
				checkForAutoSchedule();
				Log4jHelper.logInfo( "'Event Usage Request' is successfully saved  with save as the description name:- " + newDescription );
			}
			else
			{
				Log4jHelper.logInfo( "'Event Usage Request' is not availabe with the description name:- " + description );
			}

		}
	}

	// Method to check event usage request is present
	protected boolean iseventUsgReqPresent( String description ) throws Exception
	{
		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, "Description" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				rowVal = cellValue.equals( description );
				if ( rowVal )
					return true;
			}
			return rowVal;
		}
		return false;
	}

	// Method to get row value of event usage request
	protected int geteventUsgReqDescriptionRow( String description ) throws Exception
	{

		int row = GridHelper.getRowCount( "searchGrid" );
		boolean rowVal = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "searchGrid", i + 1, "Description" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				rowVal = cellValue.equals( description );
				if ( rowVal )
					return i + 1;
			}
			return 0;
		}
		return 0;
	}

	// Method to schedule Request action
	protected void scheduleRequestAction( String description ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "searchGrid", geteventUsgReqDescriptionRow( description ), "Description" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		eventUsgReqActionOb.clickOnAction( "EventUsage Request Actions", "Schedule Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PopupHelper.waitForPopup( searchScreenWaitSec );
		assertTrue( PopupHelper.isTextPresent( "This action would export all the underlying events. Do you wish to continue?" ), "Popup message is not matched" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "YesButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PopupHelper.waitForPopup( searchScreenWaitSec );
		String popupText = ElementHelper.getText( GenericHelper.getORProperty( "PS_Detail_EventUsageReq_popupXpath" ) );
		assertTrue( popupText.contains( "Event Usage Request Task is Scheduled with Task id" ), "Information popup text is not matched but found -" + popupText );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
	}

	// Method to get Schedule Type name
	private String getScheduleTypeName( int row ) throws Exception
	{
		String currentScheduleTypeName = GridHelper.getCellValue( "SearchGrid", row, "Schedule Type" ).trim();
		return currentScheduleTypeName;
	}

	// Method to validate export file
	protected void validateExportFile() throws Exception
	{
		String path = "rocpsExpAllRowsPath";
		psFileValidationHelper.validatePartialFileNameInDir( path, "Voice", "zip" );
	}

	// Method to check for auto schedule
	protected void checkForAutoSchedule() throws Exception
	{
		autoScheduleFlg = ExcelHolder.getKey( eventUsgReqMap, "AutoSchedule" );
		if ( ValidationHelper.isTrue( autoScheduleFlg ) )
		{
			int row = geteventUsgReqDescriptionRow( description );
			psDataComponentHelper.waitForTaskCompletion( row );
			validateExportFile();
		}

	}

	/*
	 * This method is for initialize workbook, sheet, path name to use in different
	 * class
	 */
	private void initializeWorkbookNmSheetNm()
	{
		bookName = workBookName;
		sheetNm = sheetName;
		pathWB = path;
	}

	/*
	 * This method is for get the path
	 */
	public String getPath()
	{
		return pathWB;
	}

	/*
	 * This method is for get workbook name
	 */
	public String getWorkbookName()
	{
		return bookName;
	}

	/*
	 * This method is for get sheetname
	 */
	public String getSheetName()
	{
		return sheetNm;
	}

}
