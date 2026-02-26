package com.subex.rocps.automation.helpers.application.accruals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accountingperiod.AccountingPeriodSearchImpl;
import com.subex.rocps.automation.helpers.application.accruals.accountingperioddefinition.AccountingPeriodDefnDetailImpl;
import com.subex.rocps.automation.helpers.application.accruals.estimationprocessor.EstimationProcessorDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class EstimationProcessor extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> estProExcel = null;
	protected Map<String, String> estProMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String name;
	protected String partition;
	protected String component;
	protected String inputTable;
	protected String outputTable;
	protected String lookBackDays;
	protected String outputGrain;
	protected String dealSimulationFlag;
	protected String column;
	protected String estimate;
	protected String pivotDays;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public EstimationProcessor( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		estProExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( estProExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public EstimationProcessor( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		estProExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( estProExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the estimation processor
	 * 
	 */
	public void configureEstimationProcessor() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Estimation Processor" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				estProMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( estProMap );
				boolean isEstimationProcessorPresent = genericHelperObj.isGridTextValuePresent( "pespName", name, "Name" );
				if(!isEstimationProcessorPresent)
					newEstiationProcessor();
				else
					Log4jHelper.logInfo( "Estimation Processor is already availablewith name :" +name);		
				
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
	public void newEstiationProcessor() throws Exception
	{
		EstimationProcessorDetailImpl detailObj = new EstimationProcessorDetailImpl( estProMap );
		detailObj.newEstimationProcessor();
		detailObj.basicDetails();
		detailObj.columnPropertiesGrid();
		detailObj.saveEstimationProcesor();
	}
	
	
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Estimation Processor" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			estProMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( estProMap, "SearchScreenColumns" );
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
	 * This method is for edit Estimation Processor
	 */
	public void estimationProcessorEdit() throws Exception
	{

		NavigationHelper.navigateToScreen( "Estimation Processor" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			estProMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( estProMap, "Name" );			
					
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isEstimationProcessorPresent = genericHelperObj.isGridTextValuePresent( "pespName", name, "Name" );
			if(isEstimationProcessorPresent)
			{
				int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToEdit( "SearchGrid", row );
				//assertTrue( ElementHelper.isElementPresent( "//*[text()='Accounting  Period  Frequency']" ), "Edit Accrual Overview Modelling Page is not opened" );
				EstimationProcessorDetailImpl detailObj = new EstimationProcessorDetailImpl( estProMap );				
				detailObj.editBasicDetails();
				detailObj.columnPropertiesGrid();
				detailObj.saveEstimationProcesor();
				Log4jHelper.logInfo( "Estimation Processor is Edited successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Estimation Processor is not available with :" + name );
		}
	}

	
	/*
	 * This method is to delete Estimation Processor
	 */
	public void estimationProcessorDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Estimation Processor" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			estProMap = excelHolderObj.dataMap( paramVal );

			initializeVariables( estProMap );		
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isEstimationProcessorPresent = genericHelperObj.isGridTextValuePresent( "pespName", name, "Name" );
			if(isEstimationProcessorPresent)
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Delete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ));
				Log4jHelper.logInfo( "Estimation Processor is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "Estimation Processor is not available with name " + name );
		}
	}

	/*
	 * This method is to un delete Estimation Processor
	 */
	public void estimationProcessorUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Estimation Processor" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			estProMap = excelHolderObj.dataMap( paramVal );

			initializeVariables( estProMap );	
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			
			boolean isEstimationProcessorPresent = genericHelperObj.isGridTextValuePresent( "pespName", name, "Name" );
			if(isEstimationProcessorPresent)
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				Log4jHelper.logInfo( "Estimation Processor is un deleted successfully : " + name );
			}
			else
				Log4jHelper.logInfo( "Estimation Processor is not available : " + name );
		}
	}

	
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );		
	}
}
