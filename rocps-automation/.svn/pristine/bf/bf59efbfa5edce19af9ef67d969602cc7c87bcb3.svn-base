package com.subex.rocps.automation.helpers.application.referenceTable;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AggrComponentMapping extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> aggrCompMapingExcel = null;
	protected Map<String, String> aggrCompMapingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String tableName;
	protected String masterTask;
	protected String aggregationTask;

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public AggrComponentMapping( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		aggrCompMapingExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( aggrCompMapingExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AggrComponentMapping( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		aggrCompMapingExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( aggrCompMapingExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the ServiceType
	 * 
	 */
	public void aggrComponentMappingCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Aggr Component Mapping" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				aggrCompMapingMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( aggrCompMapingMap );
				//	ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !isaggrCompMappingPresnet() )
				{
					genericHelperObj.clickNewAction( clientPartition );
					GenericHelper.waitForLoadmask();
					newaggrComponentMapping();

					ButtonHelper.click( "OK_Button_ByID" );
					GenericHelper.waitForSave( searchScreenWaitSec );
					Log4jHelper.logInfo( "Aggre component Mapping is created successfully with name " + masterTask );
				}
				else
				{
					Log4jHelper.logInfo( "Aggre component Mapping is available with name " + masterTask );
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
	 * Editing the aggregation component mapping
	 * 
	 */
	public void aggrComponentMappingEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToReferenceTable( "Aggr Component Mapping" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				aggrCompMapingMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( aggrCompMapingMap );
				//	ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				int row = getRowNumOfMasterTask();
				if ( row > 0 )
				{

					NavigationHelper.navigateToEdit( "SearchGrid", row );
					GenericHelper.waitForLoadmask();
					editaggrComponentMapping();
					ButtonHelper.click( "OK_Button_ByID" );
					GenericHelper.waitForSave( searchScreenWaitSec );
					Log4jHelper.logInfo( "Aggre component Mapping is updated successfully with name " + masterTask );
				}
				else
				{
					Log4jHelper.logInfo( "Aggre component Mapping is not available with name " + masterTask );
				}

			}

		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public boolean isaggrCompMappingPresnet() throws Exception
	{

		int row = GridHelper.getRowCount( "SearchGrid" );
		boolean isMasterTaskPresent = false;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, "Master Task Stream Stage" );
				GenericHelper.waitForLoadmask();
				if ( masterTask.contains( cellValue ) )
					return true;
			}
			return isMasterTaskPresent;
		}
		return false;
	}

	/* method to get row num of master task*/
	private int getRowNumOfMasterTask() throws Exception
	{

		int row = GridHelper.getRowCount( "SearchGrid" );
		int rownum = 0;
		if ( row > 0 )
		{
			for ( int i = 0; i < row; i++ )
			{
				String cellValue = GridHelper.getCellValue( "SearchGrid", i + 1, "Master Task Stream Stage" );
				GenericHelper.waitForLoadmask();
				if ( masterTask.contains( cellValue ) )
					rownum = i + 1;
			}

		}
		return rownum;

	}

	/*
	 * This method is to create new aggrComponentMapping
	 */
	protected void newaggrComponentMapping() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_masterTask_comboID", masterTask );
		ComboBoxHelper.select( "PS_Detail_aggregationTask_comboId", aggregationTask );
	}

	/*
	 * This method is to edit  aggrComponentMapping
	 */
	private void editaggrComponentMapping() throws Exception
	{

		assertEquals( ComboBoxHelper.getValue( "PS_Detail_masterTask_comboID" ), masterTask, "Master task stream stage is not matched" );
		if ( ValidationHelper.isNotEmpty( aggregationTask ) )
			ComboBoxHelper.select( "PS_Detail_aggregationTask_comboId", aggregationTask );
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		tableName = ExcelHolder.getKey( map, "TableName" );
		masterTask = ExcelHolder.getKey( map, "MasterTask" );
		aggregationTask = ExcelHolder.getKey( map, "AggregationTask" );
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToReferenceTable( "Aggr Component Mapping" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			aggrCompMapingMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( aggrCompMapingMap, "SearchScreenColumns" );
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
	 * This method is for aggrComponentMapping deletion
	 */
	public void aggrComponentMappingDelete() throws Exception
	{

		try
		{
			NavigationHelper.navigateToReferenceTable( "Aggr Component Mapping" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				aggrCompMapingMap = excelHolderObj.dataMap( paramVal );
				clientPartition = ExcelHolder.getKey( aggrCompMapingMap, "Partition" );
				masterTask = ExcelHolder.getKey( aggrCompMapingMap, "MasterTask" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );

				boolean isaggrComponentMapping = GridHelper.isValuePresent( "SearchGrid", masterTask, "Master Task Stream Stage" );
				if ( isaggrComponentMapping )
				{
					genericHelperObj.clickDeleteOrUnDeleteAction( masterTask, "Master Task Stream Stage", "Delete" );
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );
					assertTrue( GridHelper.isValuePresent( "SearchGrid", masterTask, "Master Task Stream Stage" ), masterTask );
					Log4jHelper.logInfo( "Aggr Component Mapping is deleted successfully :" + masterTask );

				}
				else
				{
					Log4jHelper.logInfo( "Aggr Component Mapping is not available with :" + masterTask );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}

	/*
	 * This method is for aggrComponentMapping un delete
	 */
	public void aggrComponentMappingUnDelete() throws Exception
	{

		try
		{
			NavigationHelper.navigateToReferenceTable( "Aggr Component Mapping" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				aggrCompMapingMap = excelHolderObj.dataMap( paramVal );
				clientPartition = ExcelHolder.getKey( aggrCompMapingMap, "Partition" );
				masterTask = ExcelHolder.getKey( aggrCompMapingMap, "MasterTask" );
				genericHelperObj.selectPartitionFilter( clientPartition, "Deleted Items" );

				boolean isaggrComponentMapping = GridHelper.isValuePresent( "SearchGrid", masterTask, "Master Task Stream Stage" );
				if ( isaggrComponentMapping )
				{
					genericHelperObj.clickDeleteOrUnDeleteAction( masterTask, "Master Task Stream Stage", "Undelete" );
					GenericHelper.waitForLoadmask();
					genericHelperObj.selectPartitionFilter( clientPartition, "Non Deleted Items" );
					assertTrue( GridHelper.isValuePresent( "SearchGrid", masterTask, "Master Task Stream Stage" ), masterTask );
					Log4jHelper.logInfo( "Aggr Component Mapping is un deleted successfully :" + masterTask );

				}
				else
				{
					Log4jHelper.logInfo( "Aggr Component Mapping is not available with :" + masterTask );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}
}
