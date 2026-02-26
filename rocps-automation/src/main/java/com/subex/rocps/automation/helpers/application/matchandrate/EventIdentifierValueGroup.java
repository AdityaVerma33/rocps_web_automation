package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EventIdentifierValueGroup extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected ExcelHolder excelHolderObj = null;
	protected Map<String, ArrayList<String>> evevalueExcelMap = null;
	protected Map<String, String> eivgMap = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int paramVal;
	protected String clientPartition;
	protected String name;
	protected String eventDefn;
	protected String nameDetail;
	protected String nameCheck;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String splitRegex = new PSStringUtils().regexFirstLevelDelimeter();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public EventIdentifierValueGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		evevalueExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( evevalueExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EventIdentifierValueGroup( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		evevalueExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( evevalueExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * this method is used for initializing the instance variables
	 */
	protected void initializeVariables()
	{

		clientPartition = eivgMap.get( "Partition" );
		name = eivgMap.get( "Name" );
		eventDefn = eivgMap.get( "EventDefn" );
		nameDetail = eivgMap.get( "NameDetail" );
		nameCheck = eivgMap.get( "NameCheck" );

	}

	/*
	 * Configuring the eventIdentifierValueGroup
	 * 
	 * @method : isIdentifierPresent returns false then eventIdentifierValueGroup is
	 * configured isIdentifierPresent returns true then eventIdentifierValueGroup is
	 * not configured
	 */

	public void eventValueGrpCreation() throws Exception
	{

		try
		{
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				NavigationHelper.navigateToScreen( "Event Identifier Value Group" );
				eivgMap = excelHolderObj.dataMap( paramVal );

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventIdentifierValueGrp_txtID", name, "Name" );
				if ( !isIdentifierPresent )
				{

					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();

					newEventIdentifierValueGroup();

					genericHelperObj.detailSave( "Detail_save_btnID", name, "Name" );
					Log4jHelper.logInfo( "Event Identifier value group is created successfully with name " + name );

				}
				else
				{

					Log4jHelper.logInfo( "Event identifier value group is available with name " + name );

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
	 * This method is to create new Identifier value group
	 */
	protected void newEventIdentifierValueGroup() throws Exception
	{
		try
		{
			TextBoxHelper.type( or.getProperty( "Detail_eventIdentifierValueGrp_txtID" ), name );

			PSEntityComboHelper.selectUsingGridFilterTextBox( "Detail_eventValue_definition_popId", "Event Identifier Definition", "Detail_eventDefn_eventName_txtId", eventDefn, "Name" );

			String[] nameDetailarr = nameDetail.split( splitRegex, -1 );

			for ( int i = 0; i < nameDetailarr.length; i++ )
			{
				ButtonHelper.click( "Detail_routeGrpAdd_btn" );
				genericHelperObj.criteriaValueSelection( nameDetailarr[i] );

			}

		}
		catch ( Exception e )
		{

			throw e;
		}
	}

	public void editeventValueGrp() throws Exception
	{

		try
		{
			NavigationHelper.navigateToScreen( "Event Identifier Value Group" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				eivgMap = excelHolderObj.dataMap( paramVal );

				initializeVariables();
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isIdentifierPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventIdentifierValueGrp_txtID", name, "Name" );
				if ( isIdentifierPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					assertEquals( NavigationHelper.getScreenTitle(), "Edit Event Identifier Value Group" );
					editEventIdentifierValueGroup();

					genericHelperObj.detailSave( "Detail_save_btnID", name, "Name" );
					Log4jHelper.logInfo( "Event Identifier value group is created successfully with name " + name );

				}
				else
				{

					Log4jHelper.logInfo( "Event identifier value group is available with name " + name );

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
	 * This method is to edit new Identifier value group
	 */
	protected void editEventIdentifierValueGroup() throws Exception
	{
		try
		{
			TextBoxHelper.type( or.getProperty( "Detail_eventIdentifierValueGrp_txtID" ), name );
			assertEquals( EntityComboHelper.getValue( "Detail_eventValue_definition_popId" ), eventDefn );
			String[] nameDetailarr = nameDetail.split( splitRegex, -1 );
			for ( int i = 0; i < nameDetailarr.length; i++ )
			{
				int row = GridHelper.getRowNumber( "groupGrid", nameDetailarr[i], "Name" );
				if ( row == 0 )
				{
					ButtonHelper.click( "Detail_routeGrpAdd_btn" );
					genericHelperObj.criteriaValueSelection( nameDetailarr[i] );
				}
				else
					assertEquals( GridHelper.getCellValue( "groupGrid", i + 1, 1 ), nameDetailarr[i] );

			}

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
		NavigationHelper.navigateToScreen( "Event Identifier Value Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			eivgMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( eivgMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( splitRegex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	/*
	 * This method is for Identifier value group deletion
	 */
	public void eventIdentifierValueGrpDelete() throws Exception
	{
		NavigationHelper.navigateToScreen( "Event Identifier Value Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eivgMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( eivgMap, "Name" );
			clientPartition = ExcelHolder.getKey( eivgMap, "Partition" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

			boolean isIdentifierPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventIdentifierValueGrp_txtID", name, "Name" );
			if ( isIdentifierPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier ValueGrp is deleted successfully :" + name );

			}
			else
				Log4jHelper.logInfo( "Event Identifier ValueGrp is not available with :" + name );
		}
	}

	/*
	 * This method is for Identifier value group un delete
	 */
	public void eventIdentifierValueGrpUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Event Identifier Value Group" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			eivgMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( eivgMap, "Name" );
			clientPartition = ExcelHolder.getKey( eivgMap, "Partition" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean isIdentifierPresent = genericHelperObj.isGridTextValuePresent( "Detail_eventIdentifierValueGrp_txtID", name, "Name" );
			if ( isIdentifierPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Event Identifier ValueGrp is un deleted successfully :" + name );

			}
			else
				Log4jHelper.logInfo( "Event Identifier ValueGrp is not available with :" + name );
		}
	}

}
