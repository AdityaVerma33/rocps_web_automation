package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

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

public class CrossFXRateGroup extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> crossFXRatesExcel = null;
	protected Map<String, String> crossFXRatesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String tableName;
	protected String code;
	protected String name;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public CrossFXRateGroup( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		crossFXRatesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( crossFXRatesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CrossFXRateGroup( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		crossFXRatesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( crossFXRatesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Cross FX Rate Group
	 * 
	 */
	public void crossFXRateGrpCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Cross FX Rate Group" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				crossFXRatesMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( crossFXRatesMap );
				//ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean iscrossFXRatePresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );
				if ( !iscrossFXRatePresent )
				{
					newCrossFXRateGrp();
					Log4jHelper.logInfo( "Cross FX Rate Group is created successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Cross FX Rate Group available with name " + name );
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
	 * Configuring the Cross FX Rate Group Edit
	 * 
	 */
	public void crossFXRateGrpEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Cross FX Rate Group" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				crossFXRatesMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( crossFXRatesMap );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean iscrossFXRatePresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );
				if ( iscrossFXRatePresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editCrossFXRateGrp();
					Log4jHelper.logInfo( "Cross FX Rate Group is updated successfully with name " + name );
				}
				else
				{
					Log4jHelper.logInfo( "Cross FX Rate Group is not available with name " + name );
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
	 * This method is to create new Cross FX Rate Group
	 */
	protected void newCrossFXRateGrp() throws Exception
	{

		createNewCrossFXRateGrpConfig();
		assertEquals( NavigationHelper.getScreenTitle(), "New Cross FX Rate Group" );
		TextBoxHelper.type( "PS_Detail_crossFXRateGrp_code_txtID", code );
		TextBoxHelper.type( "PS_Detail_crossFXRateGrp_name_txtID", name );
		genericHelperObj.detailSave( "OK_Button_ByID", name, "Name" );

	}

	/*
	 * This method is to edit  Cross FX Rate Group
	 */
	protected void editCrossFXRateGrp() throws Exception
	{

		assertEquals( NavigationHelper.getScreenTitle(), "Edit Cross FX Rate Group", "Title is not matched " );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_crossFXRateGrp_name_txtID" ), name, "Cross fx rate group is not matched" );
		if ( ValidationHelper.isNotEmpty( code ) )
			TextBoxHelper.type( "PS_Detail_crossFXRateGrp_code_txtID", code );
		genericHelperObj.detailSave( "OK_Button_ByID", name, "Name" );

	}

	private void createNewCrossFXRateGrpConfig() throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		code = ExcelHolder.getKey( map, "Code" );
		name = ExcelHolder.getKey( map, "Name" );
		tableName = ExcelHolder.getKey( map, "TableName" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Cross FX Rate Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			crossFXRatesMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( crossFXRatesMap, "SearchScreenColumns" );
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
	 * This method is for cross FX Rate Group deletion
	 */
	public void crossFXRateGroupDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Cross FX Rate Group" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			crossFXRatesMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( crossFXRatesMap, "Partition" );
			name = ExcelHolder.getKey( crossFXRatesMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean iscrossFXRatePresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );
			if ( iscrossFXRatePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "cross FX Rate Group is deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "cross FX Rate Group is not available with :" + name );
			}

		}
	}

	/*
	 * This method is for cross FX Rate Group un delete
	 */
	public void crossFXRateGroupUnDelete() throws Exception
	{

		NavigationHelper.navigateToReferenceTable( "Cross FX Rate Group" );

		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{

			crossFXRatesMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( crossFXRatesMap, "Partition" );
			name = ExcelHolder.getKey( crossFXRatesMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean iscrossFXRatePresent = GridHelper.isValuePresent( "SearchGrid", name, "Name" );
			if ( iscrossFXRatePresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "cross FX Rate Group is un deleted successfully :" + name );

			}
			else
			{
				Log4jHelper.logInfo( "cross FX Rate Group is not available with :" + name );
			}

		}
	}
}
