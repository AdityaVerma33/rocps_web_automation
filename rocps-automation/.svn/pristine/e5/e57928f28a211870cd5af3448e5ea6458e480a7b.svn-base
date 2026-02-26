package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventIdentifierValue extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, ArrayList<String>> eveMapExcel = null;
	protected Map<String, String> eivMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String definition;
	protected String fromDate;
	protected String toDate;
	protected String value;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public EventIdentifierValue( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eveMapExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( eveMapExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventIdentifierValue( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		eveMapExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( eveMapExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		try
		{
			clientPartition = ExcelHolder.getKey( map, "Partition" );
			name = ExcelHolder.getKey( map, "Name" );
			definition = ExcelHolder.getKey( map, "Definition" );
			fromDate = ExcelHolder.getKey( map, "From" );
			toDate = ExcelHolder.getKey( map, "To" );
			value = ExcelHolder.getKey( map, "Value" );
		}
		catch ( Exception e )
		{

			throw e;
		}

	}

	/*
	 * Method : Checking event identifier value exists isIdentifierValuePresent():
	 * returns false new identifier definition is created
	 */
	public void eventIdentifierValue() throws Exception
	{

		try
		{
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				NavigationHelper.navigateToScreen( "Event Identifier Value" );
				eivMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eivMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierValuePresent = genericHelperObj.isGridTextValuePresent( "Detail_eventValue_name_txtId", name, "Name" );

				if ( !isIdentifierValuePresent )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					newEventIdentifierValue();

					genericHelperObj.detailSave( "Detail_eventValueSave_btnID", name, "Name" );
					GenericHelper.waitForSave( searchScreenWaitSec );
					assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Event Identifier Value is not configured" );
					Log4jHelper.logInfo( "Event Identifier Value is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Event identifier value is available with name " + name );
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
	 * This method is to create new Event Identifier Value
	 */

	protected void newEventIdentifierValue() throws Exception
	{

		try
		{
			TextBoxHelper.type( "Detail_eventValue_name_txtId", name );

			if ( !definition.isEmpty() )
			{

				PSEntityComboHelper.selectUsingGridFilterTextBox( "Detail_eventValue_definition_popId", "Event Identifier Definition", "Detail_eventDefn_eventName_txtId", definition, "Name" );
			}
			TextBoxHelper.type( "Detail_eventValue_fromDate_txtId", fromDate );

			TextBoxHelper.type( "Detail_eventValue_toDate_txtId", toDate );

			TextBoxHelper.type( "Detail_eventValue_value_txtId", value );
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	public void editEventIdentifierValue() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Event Identifier Value" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eivMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( eivMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierValuePresent = genericHelperObj.isGridTextValuePresent( "Detail_eventValue_name_txtId", name, "Name" );

				if ( isIdentifierValuePresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editEventIdentifierValueDetail();
					genericHelperObj.detailSave( "Detail_eventValueSave_btnID", name, "Name" );
					Log4jHelper.logInfo( "Event Identifier Value is created successfully with name " + name );

				}
				else
				{
					Log4jHelper.logInfo( "Event identifier value is not available with name " + name );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	protected void editEventIdentifierValueDetail() throws Exception
	{

		TextBoxHelper.type( "Detail_eventValue_name_txtId", name );
		TextBoxHelper.type( "Detail_eventValue_fromDate_txtId", fromDate );
		TextBoxHelper.type( "Detail_eventValue_toDate_txtId", toDate );
		TextBoxHelper.type( "Detail_eventValue_value_txtId", value );

		if ( ValidationHelper.isNotEmpty( definition ) )
			assertEquals( EntityComboHelper.getValue( "eventIdentifierDfn" ), definition );

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Identifier Value" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eivMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eivMap, "SearchScreenColumns" );
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
	 * This method is for event Identifier Value deletion
	 */
	public void eventIdentifierValueDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Identifier Value" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eivMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eivMap, "Partition" );
			name = ExcelHolder.getKey( eivMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isIdentifierValuePresent = genericHelperObj.isGridTextValuePresent( "Detail_eventValue_name_txtId", name, "Name" );

			if ( isIdentifierValuePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier Value is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Identifier Value is not available : " + name );
			}

		}
	}

	/*
	 * This method is for event Identifier value un delete
	 */
	public void eventIdentifierValueUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Identifier Value" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eivMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( eivMap, "Partition" );
			name = ExcelHolder.getKey( eivMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isIdentifierValuePresent = genericHelperObj.isGridTextValuePresent( "Detail_eventValue_name_txtId", name, "Name" );

			if ( isIdentifierValuePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier Value is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "Event Identifier Value is not available : " + name );
			}

		}
	}

}
