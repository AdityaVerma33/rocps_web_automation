package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SignallingType extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> sigTypeExcel = null;
	protected Map<String, String> sigTypeMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String code;
	protected String enableTariffType;
	protected String tableName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public SignallingType( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		sigTypeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( sigTypeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */

	public SignallingType( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		sigTypeExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( sigTypeExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the SignallingType
	 * 
	 */
	public void signallingTypeCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Signalling Type" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				sigTypeMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( sigTypeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !isSignallingTypePresent() )
				{

					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();

					newsignallingtype();
					saveSignallingType();

					Log4jHelper.logInfo( "Signalling type is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Signalling type is available with name " + name );
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
	 * edit the SignallingType
	 * 
	 */
	public void signallingTypeEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Signalling Type" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				sigTypeMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( sigTypeMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( isSignallingTypePresent() )
				{

					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					editsignallingtype( sigTypeMap );

				}
				else
				{
					Log4jHelper.logInfo( "Signalling type is not available with name " + name );
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
	 * This is method is to create new signalling Type
	 */
	public void newsignallingtype() throws Exception
	{
		assertEquals(NavigationHelper.getScreenTitle(), "New Signalling Type");
		TextBoxHelper.type( "PS_Detail_wrapperID", "PS_Detail_RefTable_Signallingtype_name_TextID", name );
	}

	/*
	 * This is method is to edit  signalling Type
	 */
	public void editsignallingtype( Map<String, String> map ) throws Exception
	{
		String newName = ExcelHolder.getKey( map, "NewName" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_RefTable_Signallingtype_name_TextID" ), name, "Signalling Type name is not matched" );
		if ( ValidationHelper.isNotEmpty( newName ) )
		{
			TextBoxHelper.type( "PS_Detail_wrapperID", "PS_Detail_RefTable_Signallingtype_name_TextID", newName );
			saveSignallingType( newName );
			Log4jHelper.logInfo( "Signalling type is updated successfully with name " + newName );
		}
		else
		{
			saveSignallingType( name );
			Log4jHelper.logInfo( "Signalling type is updated successfully with name " + name );
		}
	}

	/*
	 * This method is to save signalling type
	 */
	public void saveSignallingType( String name ) throws Exception
	{
		ButtonHelper.click( "SaveButton" );
		GenericHelper.waitForSave();
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Signalling Type is not configured" );
	}

	/*
	 * This method is to save signalling type
	 */
	public void saveSignallingType() throws Exception
	{
		ButtonHelper.click( "SaveButton" );
		GenericHelper.waitForSave();
		assertEquals( NavigationHelper.getScreenTitle(), "Reference Table Search" );
		assertTrue( GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" ), "Signalling Type is not configured" );
	}

	/*
	 * This method is to check if signalling tyoe is alraedy present
	 */
	private boolean isSignallingTypePresent() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_RefTable_Signallingtype_name_TextID", name );
		ButtonHelper.click( "SearchButton" );
		return GridHelper.isValuePresent( "Detail_eventDefn_gridID", name, "Name" );
	}

	/*
	 * This method is to initaalize Instance Variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		name = ExcelHolder.getKey( map, "Name" );
		tableName = ExcelHolder.getKey( map, "TableName" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Signalling Type" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			sigTypeMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( sigTypeMap, "SearchScreenColumns" );
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
	 * This method is for signallingType deletion
	 */
	public void signallingTypeDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Signalling Type" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			sigTypeMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( sigTypeMap, "Partition" );
			name = ExcelHolder.getKey( sigTypeMap, "Name" );

			if ( ValidationHelper.isTrue( configProp.getProperty( "clientPartitionFlag" ) ) && ValidationHelper.isNotEmpty( clientPartition ) )
				genericHelperObj.collapsableXpath();
			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			GenericHelper.waitForLoadmask();

			if ( isSignallingTypePresent() )
			{

				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();

				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "signallingType is deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "signallingType is not available : " + name );
			}

		}
	}

	/*
	 * This method is for signallingType un delete
	 */
	public void signallingTypeUnDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Signalling Type" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			sigTypeMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( sigTypeMap, "Partition" );
			name = ExcelHolder.getKey( sigTypeMap, "Name" );

			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			if ( isSignallingTypePresent() )
			{

				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();

				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "signallingType is un deleted successfully : " + name );

			}
			else
			{
				Log4jHelper.logInfo( "signallingType is not available : " + name );
			}

		}
	}
}
