package com.subex.rocps.automation.helpers.application.partnerConfiguration;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgReqScheduler.EventUsgReqSchedulerActImpl;
import com.subex.rocps.automation.helpers.application.partnerConfiguration.eventUsgReqScheduler.EventUsgReqSchedulerDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventUsgRequestScheduler extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> eventUsgReqSchedulerExcelMap = null;
	protected Map<String, String> eventUsgReqSchedulerMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected static String bookName;
	protected static String pathWB;
	protected String sheetName;
	protected static String sheetNm;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String name;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	EventUsgReqSchedulerActImpl eventUsgReqSchedulerActImpl = new EventUsgReqSchedulerActImpl();

	/**
	 * Default constructor
	 */
	public EventUsgRequestScheduler()
	{

	}

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public EventUsgRequestScheduler( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eventUsgReqSchedulerExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( eventUsgReqSchedulerExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public EventUsgRequestScheduler( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		eventUsgReqSchedulerExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( eventUsgReqSchedulerExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );

	}

	/*
	 * This method is for 'Event Usage Request Scheduler' screen common method
	 */
	private void eventUsgReqSchedulerScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Usage Request Scheduler" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		eventUsgReqSchedulerMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for 'Event Usage Request Scheduler' screen column validation
	 */
	public void eventUsgReqSchedulerColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventUsgReqSchedulerScreen();
				colmHdrs = ExcelHolder.getKey( eventUsgReqSchedulerMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Name", colmHdrs, "Event Usage Request Scheduler" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Event Usage Request Scheduler' creation
	 */
	public void eventUsgReqSchedulerCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventUsgReqSchedulerScreen();
				initializeVariable( eventUsgReqSchedulerMap );
				initializeWorkbookNmSheetNm();
				boolean isEventUsgReqSchPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
				if ( !isEventUsgReqSchPresent )
				{
					eventUsgReqSchedulerActImpl.clickNewAction( clientPartition );
					EventUsgReqSchedulerDetailImpl eveUsgReqSchedulerDetailImpl = new EventUsgReqSchedulerDetailImpl( eventUsgReqSchedulerMap );
					eveUsgReqSchedulerDetailImpl.configEventUsgScheduler();
					Log4jHelper.logInfo( "Event Usage Request Scheduler is created successfully with : " + name );
				}
				else
					Log4jHelper.logInfo( "Event Usage Request Scheduler is already available with : " + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Event Usage Request Scheduler' edit
	 */
	public void eventUsgReqSchedulerEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventUsgReqSchedulerScreen();
				initializeVariable( eventUsgReqSchedulerMap );
				initializeWorkbookNmSheetNm();
				boolean isEventUsgReqSchPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
				if ( isEventUsgReqSchPresent )
				{
					GridHelper.clickRow( "SearchGrid", name, "Name" );
					eventUsgReqSchedulerActImpl.clickOnAction( "Common Tasks", "Edit" );
					EventUsgReqSchedulerDetailImpl eveUsgReqSchedulerDetailImpl = new EventUsgReqSchedulerDetailImpl( eventUsgReqSchedulerMap );
					eveUsgReqSchedulerDetailImpl.modifyEventUsgScheduler();
					Log4jHelper.logInfo( "Event Usage Request Scheduler is updated successfully with : " + name );
				}
				else
					Log4jHelper.logInfo( "Event Usage Request Scheduler is not available with : " + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Event Usage Request Scheduler' saveas
	 */
	public void eventUsgReqSchedulerSaveAs() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				eventUsgReqSchedulerScreen();
				initializeVariable( eventUsgReqSchedulerMap );
				initializeWorkbookNmSheetNm();
				String newName = ExcelHolder.getKey( eventUsgReqSchedulerMap, "NewName" );
				boolean isSveAsEventUsgReqSchPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", newName, "Name" );
				ButtonHelper.click( "ClearButton" );
				boolean isEventUsgReqSchPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
				
				if ( isEventUsgReqSchPresent )
				{
					if ( !isSveAsEventUsgReqSchPresent )
					{
						GridHelper.clickRow( "SearchGrid", name, "Name" );
						eventUsgReqSchedulerActImpl.clickOnAction( "Common Tasks", "Save As" );
						EventUsgReqSchedulerDetailImpl eveUsgReqSchedulerDetailImpl = new EventUsgReqSchedulerDetailImpl( eventUsgReqSchedulerMap );
						eveUsgReqSchedulerDetailImpl.saveAsEventUsgScheduler( name, newName );
						Log4jHelper.logInfo( "Event Usage Request Scheduler is created successfully with : " + newName );
					}
					else
						Log4jHelper.logInfo( "Event Usage Request Scheduler is already available with save as name : " + newName );

				}
				else
					Log4jHelper.logInfo( "Event Usage Request Scheduler is not available with : " + name );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Event Usage Request Scheduler deletion action
	public void eventUsgReqSchedulerDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			eventUsgReqSchedulerScreen();
			initializeVariable( eventUsgReqSchedulerMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isnamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
			assertTrue( isnamePresent, "Event Usage Request Scheduler is not available in the screen with  name: -'" + name );
			psGenericHelper.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isnamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
			assertTrue( isnamePresent, name + " is not present" );
			Log4jHelper.logInfo( "Event Usage Request Scheduler is deleted successfully with the value-:'" + name );
		}

	}

	// Method: for Event Usage Request Scheduler Undeletion action
	public void eventUsgReqSchedulerUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			eventUsgReqSchedulerScreen();
			initializeVariable( eventUsgReqSchedulerMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isnamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
			assertTrue( isnamePresent, "Event Usage Request Scheduler is not available in the screen with  name: -'" + name );
			psGenericHelper.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isnamePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
			assertTrue( isnamePresent, name + " is not present" );
			Log4jHelper.logInfo( "Event Usage Request Scheduler is undeleted successfully with the  value:  '" + name );
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
