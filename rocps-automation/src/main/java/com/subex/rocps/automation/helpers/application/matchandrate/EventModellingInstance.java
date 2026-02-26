package com.subex.rocps.automation.helpers.application.matchandrate;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.eventmodellinginstance.EventModInstActionImpl;
import com.subex.rocps.automation.helpers.application.matchandrate.eventmodellinginstance.EventModInstDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventModellingInstance extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, ArrayList<String>> eventInsExcel = null;
	protected Map<String, String> eventInsMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String mapAdditionalFields;
	String eventModellingInstance;
	String partition;
	String errorUsageGroupPartitioned;
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	EventModInstActionImpl eventModInstAction = new EventModInstActionImpl();
	PSStringUtils psStringUtils = new PSStringUtils();

	/*
	 * Constructor: for initializing excel Identifying the column size from the map
	 * passed
	 * 
	 */
	public EventModellingInstance( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventInsExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( eventInsExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventModellingInstance( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventInsExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( eventInsExcel );
		colSize = excelHolderObj.totalColumns();
	}

	// Method to check the Create Inst Button enable or not
	public void checkTheCrtNwInstEnableOrNot() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Modelling Instance" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventInsMap = excelHolderObj.dataMap( paramVal );
			partition = eventInsMap.get( "Partition" );
			eventModellingInstance = eventInsMap.get( "Name" );
			EventModInstActionImpl eventModInstAction = new EventModInstActionImpl();
			EventModInstDetailImpl eventModInstDetail = new EventModInstDetailImpl( eventInsMap );
			boolean isEventModInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_EveModInst_name_txtID", eventModellingInstance, "Name" );
			if ( !isEventModInstPresent )
			{
				eventModInstAction.clickNewAction( partition, "Event Modelling Instance" );
				eventModInstDetail.openEventModelInstDetail();
				eventModInstDetail.chckCrtInstBtnEnableOrNot();
				Log4jHelper.logInfo( "Create Instance Button successfully validated" );
			}
			else
			{
				Log4jHelper.logInfo( "Event modelling Instance is availabe with " + eventModellingInstance );

			}

		}

	}
	//Configuring the EventModellingInstance

	public void eventModInstCreation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Modelling Instance" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventInsMap = excelHolderObj.dataMap( paramVal );
			partition = eventInsMap.get( "Partition" );
			eventModellingInstance = eventInsMap.get( "Name" );

			EventModInstDetailImpl eventModInstDetail = new EventModInstDetailImpl( eventInsMap );
			boolean isEventModInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_EveModInst_name_txtID", eventModellingInstance, "Name" );
			if ( !isEventModInstPresent )
			{
				eventModInstAction.clickNewAction( partition, "Event Modelling Instance" );
				eventModInstDetail.openEventModelInstDetail();
				eventModInstDetail.configureTableInstanceDetail();
				eventModInstDetail.saveEventModelInstance( eventModellingInstance );
				Log4jHelper.logInfo( "Event modelling Instance is successfuly created with " + eventModellingInstance );
			}
			else
			{
				Log4jHelper.logInfo( "Event modelling Instance is availabe with " + eventModellingInstance );

			}

		}
	}

	/*
	 * Configuring the event Modelling Instance
	 * 
	 */
	public void eventModellingInstance() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Modelling Instance" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				eventInsMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( eventInsMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean ismodellingInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_eventModellingInstance_name_txtID", eventModellingInstance, "Name" );

				if ( ismodellingInstPresent )
				{
					eventModellingInstanceInstanceConfig();
				}
				else
				{
					Log4jHelper.logInfo( "Event Modelling Instance is not available with name " + eventModellingInstance );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * Configuring the event Modelling Instance
	 * 
	 */
	public void eventModInstMapAdditionalFieldAction() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Modelling Instance" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				eventInsMap = excelHolderObj.dataMap( paramVal );
				eventModellingInstance = ExcelHolder.getKey( eventInsMap, "EventModellingInstance" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean ismodellingInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_eventModellingInstance_name_txtID", eventModellingInstance, "Name" );

				if ( ismodellingInstPresent )
				{
					GridHelper.clickRow( "SearchGrid", eventModellingInstance, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					mapAddtionalFieldAction();
				}
				else
				{
					Log4jHelper.logInfo( "Event Modelling Instance is not available with name " + eventModellingInstance );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'Map Additional Fields' Event Modelling Instance
	 */
	private void mapAddtionalFieldAction() throws Exception
	{
		mapAdditionalFields = ExcelHolder.getKey( eventInsMap, "MapAdditionalFields" );
		String mapAdditionalFieldsArr[] = psStringUtils.stringSplitFirstLevel( mapAdditionalFields );
		String gridId = "PS_Detail_eventModInst_mapAddFields_gridId";
		int rowNum = 0;
		eventModInstAction.clickOnAction( "Map Additional Fields", "Map Additional Fields", "PS_Detail_eventModInst_mapAddFields_pageLoadxpath" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( int i = 0; i < mapAdditionalFieldsArr.length; i++ )
		{
			rowNum = i + 1;
			ButtonHelper.click( "PS_Detail_eventModInst_mapAddFields_add_btnId" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.updateGridComboBox( gridId, "PS_Detail_eventModInst_mapAddFields_maComp_combId", rowNum, "Mapping  Component", mapAdditionalFieldsArr[i] );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
		ButtonHelper.click( "PS_Detail_eventModInst_mapAddFields_saveBtnId" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_eventModInst_mapAddFields_pageLoadxpath" ), searchScreenWaitSec );
		Log4jHelper.logInfo( "Event Modelling Instance is mapped with Additional Fields name-: " + mapAdditionalFields );
		

	}

	/*
	 * This method is for editing Event Modelling Instance
	 */
	public void eventModellingInstanceInstanceConfig() throws Exception
	{
		eventModellingInstScript();
		GenericHelper.waitForLoadmask();
		GridHelper.clickRow( "Detail_eventDefn_gridID", eventModellingInstance, "Name" );
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Common Tasks", "View" );
		GenericHelper.waitForLoadmask();
		String text = ElementHelper.getText( "//div[contains(@id,'errUsageGroup_gwt_uid')]/a/span" );
		assertEquals( text, errorUsageGroupPartitioned, "Usage groups are not matching" );
		ButtonHelper.click( "CloseButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Event Modelling Instance is modified successfully" );

	}

	/*
	 * This method is to run instance Script in backend
	 */

	public void eventModellingInstScript() throws Exception
	{
		DBHelper dbHelper = null;
		int totalrows = 0;
		String usageGroupName = configProp.getProperty( "partitionerrorusagegroup" );
		try
		{
			dbHelper = DBHelper.connectToReferenceDB( false );

			if ( dbHelper != null )
			{
				String updateQuery = "update event_modelling_inst set pemi_err_usg_id = (select usg_id from usage_group where usg_name =?) where PEMI_NAME=?";
				PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( updateQuery );
				stmt.setString( 1, errorUsageGroupPartitioned );
				stmt.setString( 2, eventModellingInstance );
				totalrows = stmt.executeUpdate();
				Log4jHelper.logInfo( " total rows updated in the event_modelling_inst table : " + totalrows );
			}

			assertTrue( totalrows > 0, "pemi_err_usg_id is not set in Event modelling instance with usage group " + usageGroupName );

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		eventModellingInstance = ExcelHolder.getKey( map, "EventModellingInstance" );
		partition = ExcelHolder.getKey( map, "Partition" );
		errorUsageGroupPartitioned = ExcelHolder.getKey( map, "ErrorUsageGroup(Partitioned)" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Modelling Instance" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventInsMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eventInsMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for Event Modeling Instance deletion
	 */
	public void eventModellingInstDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Modelling Instance" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventInsMap = excelHolderObj.dataMap( paramVal );

			eventModellingInstance = ExcelHolder.getKey( eventInsMap, "EventModellingInstance" );
			partition = ExcelHolder.getKey( eventInsMap, "Partition" );

			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean ismodellingInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_eventModellingInstance_name_txtID", eventModellingInstance, "Name" );

			if ( ismodellingInstPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( eventModellingInstance, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", eventModellingInstance, "Name" ), eventModellingInstance );
				Log4jHelper.logInfo( "Event Modelling Instance is deleted successfully : " + eventModellingInstance );

			}
			else
			{
				Log4jHelper.logInfo( "Event Modelling Instance is not available : " + eventModellingInstance );
			}

		}
	}

	/*
	 * This method is for Event Modeling Instance un delete
	 */
	public void eventModellingInstUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Modelling Instance" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventInsMap = excelHolderObj.dataMap( paramVal );

			eventModellingInstance = ExcelHolder.getKey( eventInsMap, "EventModellingInstance" );
			partition = ExcelHolder.getKey( eventInsMap, "Partition" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			boolean ismodellingInstPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_eventModellingInstance_name_txtID", eventModellingInstance, "Name" );

			if ( ismodellingInstPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( eventModellingInstance, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", eventModellingInstance, "Name" ), eventModellingInstance );
				Log4jHelper.logInfo( "Event Modelling Instance is un deleted successfully : " + eventModellingInstance );

			}
			else
			{
				Log4jHelper.logInfo( "Event Modelling Instance is not available : " + eventModellingInstance );
			}

		}
	}
}
