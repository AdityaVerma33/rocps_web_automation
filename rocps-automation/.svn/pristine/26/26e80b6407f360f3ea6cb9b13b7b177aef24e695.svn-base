package com.subex.rocps.automation.helpers.application.matchandrate;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.eventextraratingfield.EventExtraFieldActionImpl;
import com.subex.rocps.automation.helpers.application.networkConfiguraiton.rulestringset.RuleStringSetActionImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class EventExtraRatingField extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> eventRatingExcel = null;
	protected Map<String, String> eventRatingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String eventType;
	protected String component;
	protected String inRoute;
	protected String outRoute;
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	EventExtraFieldActionImpl eventRatingActionObj = new EventExtraFieldActionImpl();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public EventExtraRatingField( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventRatingExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( eventRatingExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventExtraRatingField( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventRatingExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( eventRatingExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the event extra rating field
	 * 
	 * @method : isEventExtraRatingPresent returns false then Event Extra Rating Field is
	 * configured isEventExtraRatingPresent returns true then Event Extra Rating Field
	 * is not configured
	 */
	public void eventRatingCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Extra Rating Field" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eventRatingMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eventRatingMap );
				boolean isEventExtraRatingPresent = genHelperObj.isGridTextValuePresent( "peefName", name, "Name" );
				if ( !isEventExtraRatingPresent )
				{
					eventRatingActionObj.clickNewAction( clientPartition );
					newEventExtraRatingField();
					saveEventRating();
					Log4jHelper.logInfo( "Event Extra Rating Field is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Event Extra Rating Field is available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	/*
	 * This method is to create new event extra rating field
	 */
	protected void newEventExtraRatingField() throws Exception
	{

		TextBoxHelper.type( "peefName", name );
		ComboBoxHelper.select( "eventType_gwt_uid_", eventType );
		ComboBoxHelper.select( "component_gwt_uid_", component );
		/*GridHelper.clickRow("extraRatingPropertyGrid", 1, "Value");
		GridHelper.clickRow("extraRatingPropertyGrid", 1, "Value");*/
		genHelperObj.clickPropertyValueColumn( "In  Route" );
		ComboBoxHelper.select( "tclEditor_gwt_uid_", inRoute );

		genHelperObj.clickPropertyValueColumn( "Out Route" );
		ComboBoxHelper.select( "tclEditor_gwt_uid_", outRoute );

	}

	public void saveEventRating() throws Exception
	{
		ButtonHelper.click( "eventExtraRatingFieldDetail.OK" );
		GenericHelper.waitForLoadmask();
		assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Event Extra Rating field is not configured" );
	}

	protected void editEventExtraRatingField() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( name ) )
			TextBoxHelper.type( "peefName", name );		
		assertEquals( ComboBoxHelper.getValue( "eventType_gwt_uid_"), eventType );		
		assertEquals( ComboBoxHelper.getValue( "component_gwt_uid_"), component );
		if ( ValidationHelper.isNotEmpty( inRoute ) )
		{
			genHelperObj.clickPropertyValueColumn( "In  Route" );
			ComboBoxHelper.select( "tclEditor_gwt_uid_", inRoute );
		}
		if ( ValidationHelper.isNotEmpty( outRoute ) )
		{
			genHelperObj.clickPropertyValueColumn( "Out Route" );
			ComboBoxHelper.select( "tclEditor_gwt_uid_", outRoute );
		}

	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "Event Type" );
		component = ExcelHolder.getKey( map, "Component" );
		inRoute = ExcelHolder.getKey( map, "In Route" );
		outRoute = ExcelHolder.getKey( map, "Out Route" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Extra Rating Field" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventRatingMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eventRatingMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for Event Extra Rating Field deletion
	 */
	public void eventRatingDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Extra Rating Field" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eventRatingMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eventRatingMap, "Partition" );
			name = ExcelHolder.getKey( eventRatingMap, "Name" );

			genHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();
			boolean isEventExtraRatingPresent = genHelperObj.isGridTextValuePresent( "peefName", name, "Name" );
			if ( isEventExtraRatingPresent )
			{
				eventRatingActionObj.clickDeleteAction( name );
				genHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Extra Rating Field is deleted successfully with name " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Extra Rating Field is not available with name " + name );
			}

		}
	}

	/*
	 * This method is for Event Extra Rating Field un delete
	 */
	public void eventRatingUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Extra Rating Field" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eventRatingMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eventRatingMap, "Partition" );
			name = ExcelHolder.getKey( eventRatingMap, "Name" );

			genHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isEventExtraRatingPresent = genHelperObj.isGridTextValuePresent( "peefName", name, "Name" );
			if ( isEventExtraRatingPresent )
			{
				eventRatingActionObj.clickUnDeleteACtion( name );
				genHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Extra Rating Field is un deleted successfully with name " + name );
			}
			else
			{
				Log4jHelper.logInfo( "Event Extra Rating Field is not available with name " + name );
			}

		}
	}

	public void editEventRatingCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Extra Rating Field" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eventRatingMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eventRatingMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isEventExtraRatingPresent = genHelperObj.isGridTextValuePresent( "peefName", name, "Name" );
				if ( isEventExtraRatingPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Extra Rating Field" );
					editEventExtraRatingField();
					saveEventRating();
					Log4jHelper.logInfo( "Event Extra Rating Field is edited successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Event Extra Rating Field is not available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{
			throw e;
		}
	}

}
