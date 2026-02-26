package com.subex.rocps.automation.helpers.application.matchandrate;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition.ConfigureSearchGridActionDetailImpl;
import com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition.EventModellingDefinitionActionImpl;
import com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition.EventModellingDefinitionDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class EventModellingDefinition extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> eventModDefnExcel = null;
	protected Map<String, String> eventModDefnMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String editAction;
	protected String configMappingAction;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public EventModellingDefinition( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventModDefnExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( eventModDefnExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventModellingDefinition( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eventModDefnExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( eventModDefnExcel );
		colSize = excelHolderObj.totalColumns();
	}

	public void eventModellingDefnCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Event Modelling Definition" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				eventModDefnMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( eventModDefnMap );
				boolean isEventModDefnPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_EveModDefn_name_txtID", name, "Name" );
				if ( !isEventModDefnPresent )
				{
					EventModellingDefinitionActionImpl eventModDefnActionImpl = new EventModellingDefinitionActionImpl();
					EventModellingDefinitionDetailImpl eventModeDefnDetailImpl = new EventModellingDefinitionDetailImpl( eventModDefnMap );
					eventModDefnActionImpl.clickNewAction( clientPartition, "Event Modelling Definition" );
					eventModeDefnDetailImpl.openEventModellingDefinationDetail();
					eventModeDefnDetailImpl.provideTableDefinationDetail();
					eventModeDefnDetailImpl.saveEventModellingDefinition( name );
				}
				else
				{
					Log4jHelper.logInfo( "Event modelling defination is availabe with " + name );
				}
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}

	/*
	 * Configuring the EventModellingDefinition
	 * 
	 */
	public void modifyEventModellingDefn() throws Exception
	{
		try
		{
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eventModDefnMap = excelHolderObj.dataMap( paramVal );
				NavigationHelper.navigateToScreen( "Event Modelling Definition" );

				initializeVariables( eventModDefnMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isEventModDefn = genericHelperObj.isGridTextValuePresent( "PS_Detail_modelingDefn_name_txtID", name, "Name" );
				if ( isEventModDefn )
				{
					GenericHelper.waitForLoadmask( 1000 );
					eventModellingDefnAction();

				}
				else
				{
					Log4jHelper.logInfo( "Event Modelling Definition is not available with name " + name );
				}

			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	protected void eventModellingDefnAction() throws Exception
	{
		boolean isEditAction = ValidationHelper.isTrue( editAction );
		boolean isConfigMappingAction = ValidationHelper.isTrue( configMappingAction );
		if ( isEditAction )
		{
			editEventModellingDefn();
		}
		if ( isConfigMappingAction )
		{
			mappingConfig();
		}
		else
		{
			Log4jHelper.logInfo( "Failed to Modify Event Modelling Definition with name " + name );
		}
	}

	/*
	 * This method is for editing Event Modeling Definition
	 */
	protected void editEventModellingDefn() throws Exception
	{

		GridHelper.clickRow( "searchGrid", name, "Name" );
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		//NavigationHelper.navigateToNewOrEdit(1, clientPartition, "Event Modelling Definition" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "PS_Detail_edit_save_btn" );
		GenericHelper.waitForSave();
		Log4jHelper.logInfo( "Event Modelling Definition is modified successfully with name " + name );

	}

	/*
	 * This method is for Map Event table Mapping details
	 */
	protected void mappingConfig() throws Exception
	{

		GridHelper.clickRow( "Detail_eventDefn_gridID", name, "Name" );

		NavigationHelper.navigateToAction( "Map Event Tables", "Configure Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforPopupHeaderElement( "eventMappingDetail.window", "mappingGrid", "Output  Table  Column" );
		ButtonHelper.clickIfEnabled( "PS_Detail_mapping_save_btn" );
		GenericHelper.waitForSave();
		Log4jHelper.logInfo( "Event Modelling Definition Mapping is modified successfully with name " + name );
	}

	//validate the column headers value
	public void verifyTheColHdrsOfConfgSearchGrid() throws Exception
	{
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToScreen( "Event Modelling Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventModDefnMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( eventModDefnMap );
			EventModellingDefinitionActionImpl eventModDefnActionImpl = new EventModellingDefinitionActionImpl();
			ConfigureSearchGridActionDetailImpl confgSearchGdActDetail = new ConfigureSearchGridActionDetailImpl( eventModDefnMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isEventModDefnPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_EveModDefn_name_txtID", name, "Name" );
			if ( isEventModDefnPresent )
			{
				GridHelper.clickRow( "PS_Detail_EveModDefn_tabDefnSearch_GridId", name, "Name" );
				eventModDefnActionImpl.clickConfgSearchGridAction( clientPartition, "New Configure Search Grid" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				confgSearchGdActDetail.verifyTheColmnHeaderOfConfgSeGrd();
				Log4jHelper.logInfo( "Event Modelling Defn Confg Search Grid Columns are validated successfully" );
			}

			else
			{
				Log4jHelper.logInfo( "Event modelling defination is not availabe with " + name );

			}

		}
	}

	//Modifying the EventModellingDefinition Configure Search Grid
	public void confgSearchGridAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Modelling Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventModDefnMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( eventModDefnMap );
			EventModellingDefinitionActionImpl eventModDefnActionImpl = new EventModellingDefinitionActionImpl();
			ConfigureSearchGridActionDetailImpl confgSearchGdActDetail = new ConfigureSearchGridActionDetailImpl( eventModDefnMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isEventModDefnPresent = genericHelperObj.isGridTextValuePresent( "PS_Detail_EveModDefn_name_txtID", name, "Name" );
			if ( isEventModDefnPresent )
			{
				GridHelper.clickRow( "PS_Detail_EveModDefn_tabDefnSearch_GridId", name, "Name" );
				eventModDefnActionImpl.clickConfgSearchGridAction( clientPartition, "New Configure Search Grid" );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				confgSearchGdActDetail.modifyConfgSearchGridTable( clientPartition, "New Configure Search Grid" );
				confgSearchGdActDetail.clickConfigureSearchGridSave();
			}
			else
			{
				Log4jHelper.logInfo( "Event modelling defination is not availabe with " + name );
			}

		}
	}

	/*
	 * This method is for initializing instance variables
	 */

	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		editAction = ExcelHolder.getKey( map, "EditAction" );
		configMappingAction = ExcelHolder.getKey( map, "ConfigMappingAction" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Modelling Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eventModDefnMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eventModDefnMap, "SearchScreenColumns" );
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
	 * This method is for Event Modeling Definition deletion
	 */
	public void eventModellingDefnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Modelling Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eventModDefnMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eventModDefnMap, "Partition" );
			name = ExcelHolder.getKey( eventModDefnMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isEventModDefn = genericHelperObj.isGridTextValuePresent( "PS_Detail_modelingDefn_name_txtID", name, "Name" );
			if ( isEventModDefn )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Modelling Definition is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Modelling Definition is not available : " + name );
			}

		}
	}

	/*
	 * This method is for Event Modeling Definition un delete
	 */
	public void eventModellingDefnUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Modelling Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eventModDefnMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eventModDefnMap, "Partition" );
			name = ExcelHolder.getKey( eventModDefnMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isEventModDefn = genericHelperObj.isGridTextValuePresent( "PS_Detail_modelingDefn_name_txtID", name, "Name" );
			if ( isEventModDefn )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Modelling Definition is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Modelling Definition is not available : " + name );
			}

		}
	}

}
