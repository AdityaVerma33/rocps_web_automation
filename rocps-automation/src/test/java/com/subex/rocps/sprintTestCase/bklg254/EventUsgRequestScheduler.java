package com.subex.rocps.sprintTestCase.bklg254;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
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
	protected Map<String, String> eventUsgReqSchedulerDetailMap = null;
	/*protected String name;*/
	protected String description;
	protected String streamStage;
	protected String eventLookBack;
	protected String eventType;
	protected String xdrExtTemplate;
	protected String entities;
	protected String values;
	protected String viewXDRTemplateTestCase;
	protected String frequencyMultiplier;
	protected String frequency;
	protected String nextSchedule;
	protected String dayGroups;
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
	public void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
	    description = ExcelHolder.getKey( map, "Description" );
		streamStage = ExcelHolder.getKey( map, "StreamStage" );
		eventLookBack = ExcelHolder.getKey( map, "EventLookBack" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		xdrExtTemplate = ExcelHolder.getKey( map, "XdrExtTemplate" );
		entities = ExcelHolder.getKey( map, "Entities" );
		values = ExcelHolder.getKey( map, "Values" );
		viewXDRTemplateTestCase = ExcelHolder.getKey( map, "ViewXDRTemplateTestCase" );
		frequencyMultiplier = ExcelHolder.getKey( map, "FrequencyMultiplier" );
		frequency = ExcelHolder.getKey( map, "Frequency" );
		nextSchedule = ExcelHolder.getKey( map, "NextSchedule" );
		dayGroups = ExcelHolder.getKey( map, "DayGroups" );

	}

	/*
	 * This method is for 'Event Usage Request Scheduler' screen common method
	 */
	public void eventUsgReqSchdlSearchScreen() throws Exception
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
				eventUsgReqSchdlSearchScreen();
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
				eventUsgReqSchdlSearchScreen();
				initializeVariable( eventUsgReqSchedulerMap );
				initializeWorkbookNmSheetNm();
				boolean isEventUsgReqSchPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_eventUsgReqSch_name_txtId", name, "Name" );
				if ( !isEventUsgReqSchPresent )
				{
					//eventUsgReqSchedulerActImpl.clickNewAction( clientPartition );
					NavigationHelper.navigateToNew(clientPartition);
					//EventUsgReqSchedulerDetailImpl eveUsgReqSchedulerDetailImpl = new EventUsgReqSchedulerDetailImpl( eventUsgReqSchedulerMap );
					configEventUsgScheduler();
					eventUsgReqSchedulerScreen();
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
	
	private void initializeWorkbookNmSheetNm()
	{
		bookName = workBookName;
		sheetNm = sheetName;
		pathWB = path;
	}
	
	public void configEventUsgScheduler() throws Exception
	{
		initializeVariable( eventUsgReqSchedulerDetailMap );
		configSchdulerNamePanel();
		configStreamStageXdrTempl();
		
		configScheduleTime();
		clickOnSave();
	}
	protected void configSchdulerNamePanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_eventUsgReqSch_name_txtId", name );
		TextAreaHelper.type( "PS_Detail_eventUsgReqSch_description_txtareaId", description );
	}
	protected void configStreamStageXdrTempl() throws Exception
	{
		ComboBoxHelper.select( "PS_Detail_eventUsgReq_streamStg_ComboID", streamStage );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		TextBoxHelper.type( "PS_Detail_eventUsgReqSch_eventLookback_txtId", eventLookBack );
		if ( ValidationHelper.isNotEmpty( eventType ) )
			assertEquals( ComboBoxHelper.getValue( "PS_Detail_eventUsgReq_eventType_ComboID" ), eventType, "Event Type is not matched" );
		configXdrTemplateEntitySearch( xdrExtTemplate );
	}
	
	

	// Method: xdr template entity search
	public void xdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_eventUsgReq_xdrExtTemp_triggerID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Status" );
		int row = SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_xdrExtTemp_name_textID", xdrTemplate, "Name" );
		GridHelper.clickRow( "SearchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	private void configXdrTemplateEntitySearch( String xdrTemplate ) throws Exception
	{
		xdrTemplateEntitySearch( xdrTemplate );
		//initializePathWorkBkNmSheetNm();
		
	}
	
	private void configScheduleTime() throws Exception
	{

		TestDataHelper testDataObj = new TestDataHelper();
		String[][] dayGroupsArr = testDataObj.getStringValue( dayGroups, firstLevelDelimiter, secondLevelDelimiter );
		psDataComponentHelper.updateScheduleTimes( frequencyMultiplier, frequency, nextSchedule, dayGroupsArr );
	}
	private void clickOnSave() throws Exception
	{
		psGenericHelper.detailSave( "PS_Detail_eventUsgReqSch_save_btnId", name, "Name" );
	}
	private void eventUsgReqSchedulerScreen() throws Exception
    {
        NavigationHelper.navigateToScreen( "Event Usage Request Scheduler" );
        GenericHelper.waitForLoadmask( searchScreenWaitSec );
        eventUsgReqSchedulerMap = excelHolderObj.dataMap( index );
        ButtonHelper.click( "ClearButton" );
        psGenericHelper.waitforHeaderElement( "Name" );
    }
	/*
	 * This method is for 'Event Usage Request Scheduler' edit
	 */
	/*public void eventUsgReqSchedulerEdit() throws Exception
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

	
	 * This method is for 'Event Usage Request Scheduler' saveas
	 
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

	
	 * This method is for initialize workbook, sheet, path name to use in different
	 * class
	 
	private void initializeWorkbookNmSheetNm()
	{
		bookName = workBookName;
		sheetNm = sheetName;
		pathWB = path;
	}

	
	 * This method is for get the path
	 
	public String getPath()
	{
		return pathWB;
	}

	
	 * This method is for get workbook name
	 
	public String getWorkbookName()
	{
		return bookName;
	}

	
	 * This method is for get sheetname
	 
	public String getSheetName()
	{
		return sheetNm;
	}*/
}
