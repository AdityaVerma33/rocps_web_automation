package com.subex.rocps.automation.helpers.application.matchandrate;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventIdentiferDefinition extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, ArrayList<String>> eveExcelMap = null;
	protected Map<String, String> eidMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int paramVal;
	protected String clientPartition;
	protected String name;
	protected String eventType;
	protected String field;
	protected String definitionType;
	protected String component;
	protected String criteriaType;
	protected String matchType;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public EventIdentiferDefinition( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eveExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( eveExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventIdentiferDefinition( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		eveExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( eveExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * this method is used for initializing the instance variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );
		field = ExcelHolder.getKey( map, "Field" );
		definitionType = ExcelHolder.getKey( map, "DefinitionType" );
		component = ExcelHolder.getKey( map, "Component" );
		criteriaType = ExcelHolder.getKey( map, "CriteriaType" );
		matchType = ExcelHolder.getKey( map, "MatchType" );

	}

	/*
	 * Configuring the eventIdentifierDefinition
	 * 
	 * @method : isIdentifierDefinitionPresent returns false then
	 * eventIdentifierDefinition is configured isIdentifierDefinitionPresent returns
	 * true then eventIdentifierDefinition is not configured
	 */

	public void eventCreation() throws Exception
	{

		try
		{
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				NavigationHelper.navigateToScreen( "Event Identifier Definition" );
				eidMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eidMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierDefinitionPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventDefn_eventName_txtId", name, "Name" );

				if ( !isIdentifierDefinitionPresent )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();

					newEventIdentifierDefinition();

					genericHelperObj.detailSave( "Detail_eventDefn_Save_btnId", name, "Name" );
					Log4jHelper.logInfo( "Event Identifier Definition is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Event identifier Definition is available with name " + name );

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
	 * this method is to configure new EventIdentifierDefinition
	 */
	protected void newEventIdentifierDefinition() throws Exception
	{

		try
		{
			TextBoxHelper.type( "Detail_eventDefn_eventName_txtId", name );
			ComboBoxHelper.select( "Detail_eventDefn_eventType_comboId", eventType );
			ComboBoxHelper.select( "Detail_eventDefn_field_comboId", field );
			ComboBoxHelper.select( "Detail_eventDefn_definitionType_comboId", definitionType );
			ComboBoxHelper.select( "Detail_eventDefn_component_comboId", component );
			ComboBoxHelper.select( "Detail_eventDefn_Criteria_comboId", criteriaType );
			propertiesConfig();

		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	/*
	 * This method is for configuring the property value if component is
	 * "String Match"
	 */
	protected void propertiesConfig() throws Exception
	{

		try
		{
			if ( component.contentEquals( "String Match" ) )
			{
				ComboBoxHelper.select( "Detail_eventDefn_WrapperID", "Detail_eventDefn_matchType_comboId", matchType );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void editEventIdentifierDefinition() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Event Identifier Definition" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eidMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eidMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierDefinitionPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventDefn_eventName_txtId", name, "Name" );

				if ( isIdentifierDefinitionPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Identifier Definition" );
					editEventIdentifierDefinitionDetail();
					genericHelperObj.detailSave( "Detail_eventDefn_Save_btnId", name, "Name" );
					Log4jHelper.logInfo( "Event Identifier Definition is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Event identifier Definition is available with name " + name );

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
	 * this method is to configure new EventIdentifierDefinition
	 */
	protected void editEventIdentifierDefinitionDetail() throws Exception
	{

		try
		{
			TextBoxHelper.type( "Detail_eventDefn_eventName_txtId", name );
			assertEquals( ComboBoxHelper.getValue( "Detail_eventDefn_eventType_comboId" ), eventType );
			assertEquals( ComboBoxHelper.getValue( "Detail_eventDefn_field_comboId" ), field );
			assertEquals( ComboBoxHelper.getValue( "Detail_eventDefn_definitionType_comboId" ), definitionType );
			assertEquals( ComboBoxHelper.getValue( "Detail_eventDefn_component_comboId" ), component );
			if ( !criteriaType.isEmpty() )
				assertEquals( ComboBoxHelper.getValue( "Detail_eventDefn_Criteria_comboId" ), criteriaType );
			if ( !component.isEmpty() )
				propertiesConfig();

		}
		catch ( Exception e )
		{
			throw e;
		}
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Identifier Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eidMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eidMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}
	/*
	 * This method is to perform Re-Order action
	 */

	public void reOrderAction() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Identifier Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eidMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( eidMap, "Name" );
			String moveUp = ExcelHolder.getKey( eidMap, "MoveUp" );
			String moveDown = ExcelHolder.getKey( eidMap, "MoveDown" );
			int row;
			boolean isIdentifierDefinitionPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventDefn_eventName_txtId", name, "Name" );

			if ( isIdentifierDefinitionPresent )
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToAction( "Reorder", "Change Definition Order" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );

				if ( ValidationHelper.isNotEmpty( moveUp ) )
				{

					row = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
					int moveRow = GridHelper.getRowNumber( "orderGrid", moveUp, "Event Identifier Definition" );
					int val = row - moveRow;
					int actualRow = moveUpMoveDownConfig( moveUp, "toolbar-button-label-dfnGridToolbar.moveUp", val );
					assertEquals( actualRow, moveRow, "MoveUp is not performed" );
				}
				if ( ValidationHelper.isNotEmpty( moveDown ) )
				{
					row = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
					int moveRow = GridHelper.getRowNumber( "orderGrid", moveDown, "Event Identifier Definition" );
					int val = moveRow - row;
					int actualRow = moveUpMoveDownConfig( moveDown, "toolbar-button-label-dfnGridToolbar.moveDown", val );
					assertEquals( actualRow, moveRow, "MoveUp is not performed" );
				}

				ButtonHelper.click( "dfnOrderDetail.save" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			else
				Log4jHelper.logInfo( "Event identifier definition is not available" );
		}

	}

	public int moveUpMoveDownConfig( String move, String gridID, int val ) throws Exception
	{
		for ( int i = 0; i < val; i++ )
		{
			GridHelper.clickRow( "orderGrid", name, "Event Identifier Definition" );
			ButtonHelper.click( gridID );
			GenericHelper.waitForLoadmask();

		}
		int actualRow = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
		return actualRow;
	}

	/*public void reOrderActionTry() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Event Identifier Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eidMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey(eidMap, "Name");
			String moveUp = ExcelHolder.getKey(eidMap, "MoveUp");
			String moveDown = ExcelHolder.getKey(eidMap, "MoveDown");
			int row;
			int totalCount;
			GridHelper.clickRow( "SearchGrid", name, "Name" );
			NavigationHelper.navigateToAction( "Reorder", "Change Definition Order" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			
			if(ValidationHelper.isNotEmpty( moveUp ))
			{
				row = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
				int moveRow = GridHelper.getRowNumber( "orderGrid", moveUp, "Event Identifier Definition" );
				int val = row - moveRow;
				for (int i=0;i<val; i++)
				{
					GridHelper.clickRow( "orderGrid", name , "Event Identifier Definition");
					ButtonHelper.click( "dfnGridToolbar.moveUp" );
					GenericHelper.waitForLoadmask();
					
				}
				int actulaRow = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
				assertEquals( actulaRow, moveRow , "MoveUp is not performed");
				
			}
			if(ValidationHelper.isNotEmpty( moveDown ))
			{
				row = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
				int moveRow = GridHelper.getRowNumber( "orderGrid", moveDown, "Event Identifier Definition" );
				int val = moveRow - row;
				for (int i=0;i<val; i++)
				{
					GridHelper.clickRow( "orderGrid", name , "Event Identifier Definition");
					ButtonHelper.click( "dfnGridToolbar.moveDown" );
					GenericHelper.waitForLoadmask();
					
				}
				int actulaRow = GridHelper.getRowNumber( "orderGrid", name, "Event Identifier Definition" );
				assertEquals( actulaRow, moveRow , "MoveUp is not performed");
			}
					
			ButtonHelper.click( "dfnOrderDetail.save" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	
	}
	*/
	/*
	 * This method is for event Identifier Definition deletion
	 */
	public void eventIdentifierDefnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Identifier Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eidMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eidMap, "Partition" );
			name = ExcelHolder.getKey( eidMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isIdentifierDefinitionPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventDefn_eventName_txtId", name, "Name" );

			if ( isIdentifierDefinitionPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier Definition is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Identifier Definition is not available : " + name );
			}

		}
	}

	/*
	 * This method is for event Identifier Definition un delete
	 */
	public void eventIdentifierDefnUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Identifier Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eidMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eidMap, "Partition" );
			name = ExcelHolder.getKey( eidMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isIdentifierDefinitionPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventDefn_eventName_txtId", name, "Name" );

			if ( isIdentifierDefinitionPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier Definition is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Identifier Definition is not available : " + name );
			}

		}
	}

}
