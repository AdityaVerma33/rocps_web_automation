package com.subex.rocps.automation.helpers.application.carrierinvoice;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.carrierinvoice.fieldmapping.FieldMappingDetailImpl;
import com.subex.rocps.automation.helpers.application.carrierinvoice.systemfieldlist.SystemFieldListDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class FieldMapping extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> fieldMapExcel = null;
	protected Map<String, String> fieldMapMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String rateEffectiveDate;
	protected String completeExpireDate;
	protected String originRateEffectiveDate;
	protected String originCompleteExpireDate;
	protected String complete;
	protected String autoAuthorize;
	protected String locationInformation;
	protected String sheet;
	protected String columnIndex;
	protected String rowNumber;
	protected String destinationDetails;
	protected String originDetails;
	protected String colHeaders;
	protected String results;
	protected String fileName;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public FieldMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		fieldMapExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( fieldMapExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public FieldMapping( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		fieldMapExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( fieldMapExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the FieldMapping
	 * 
	 */
	public void configureFieldMapping() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Field Mapping" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				fieldMapMap = excelHolderObj.dataMap( paramVal );
				String targetField = ExcelHolder.getKey( fieldMapMap, "TargetField" );
				clientPartition = ExcelHolder.getKey( fieldMapMap, "Partition" );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent( "pifmFieldName",targetField , "Target Field" );
				if ( !isSystemFieldPresent )
				{
					FieldMappingDetailImpl fieldDetailObj = new FieldMappingDetailImpl( fieldMapMap );
					fieldDetailObj.newFiledMaping();
					fieldDetailObj.configureFieldMapping();
					fieldDetailObj.saveFieldMapping();
				}
				else
					Log4jHelper.logInfo( "FieldMapping is available with name :" + name );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Field Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			fieldMapMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( fieldMapMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	
	/*
	 * This method is for FieldMapping un delete
	 */
	public void fieldMappingDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Field Mapping" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			fieldMapMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( fieldMapMap, "Partition" );
			name = ExcelHolder.getKey( fieldMapMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent( "PS_systemFieldList_Name_txtID", name, "Name" );

			if ( isSystemFieldPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Field Mapping is deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Field Mapping is not available with :" + name );
		}
	}

	/*
	 * This method is for Field Mapping un delete
	 */
	public void fieldMappingUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Field Mapping" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			fieldMapMap = excelHolderObj.dataMap( paramVal );
			clientPartition = ExcelHolder.getKey( fieldMapMap, "Partition" );
			name = ExcelHolder.getKey( fieldMapMap, "Name" );
			genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

			boolean isSystemFieldPresent = genericHelperObj.isGridTextValuePresent( "PS_systemFieldList_Name_txtID", name, "Name" );

			if ( isSystemFieldPresent )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ), name );
				Log4jHelper.logInfo( "Field Mapping is un deleted successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Field Mapping is not available with :" + name );
		}

	}
}
