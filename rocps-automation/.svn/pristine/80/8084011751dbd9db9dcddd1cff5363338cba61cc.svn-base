package com.subex.rocps.automation.helpers.application.amountthreshold;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.amountthreshold.amountthreshold.AmountThresholdDetailImpl;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AmountThreshold extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> amtExcel = null;
	protected Map<String, String> amtMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String name;
	protected String addCurrency;	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();

	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();	

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public AmountThreshold( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		amtExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( amtExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AmountThreshold( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		amtExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( amtExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Amount Threshold 
	 * 
	 */
	public void amountThresholdSingleCurrencyCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Amount Threshold" );
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				amtMap = excelHolderObj.dataMap( paramVal );

				clientPartition = ExcelHolder.getKey( amtMap, "Partition" );
				name = ExcelHolder.getKey( amtMap, "Name" );			
				AmountThresholdDetailImpl amtThresholdDetailObj = new AmountThresholdDetailImpl( amtMap );
				boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" );
				if ( !isAmtThresholdPresnet )
				{
					genericHelperObj.clickNewAction( clientPartition );	
					
					amtThresholdDetailObj.configureThresholdLegends();
					
					LinkedHashSet<String> excelGridVal = amtThresholdDetailObj.getGridData();
					amtThresholdDetailObj.validateGridRowData( excelGridVal, "PS_Detail_Amtthreshold_currency_gridID", 1 );	
					Log4jHelper.logInfo( "Threshold Legends validated successfully");
					amtThresholdDetailObj.saveAmtThreshold();
					assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
					Log4jHelper.logInfo( "Amount Threshold is created successfully:" +name);
					
				}
				else
					Log4jHelper.logInfo( "Amount Threshold  is already presnet for " + name );
				}			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void amountThresholdMultiCurrencyCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Amount Threshold" );
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				amtMap = excelHolderObj.dataMap( paramVal );

				clientPartition = ExcelHolder.getKey( amtMap, "Partition" );
				name = ExcelHolder.getKey( amtMap, "Name" );
				AmountThresholdDetailImpl amtThresholdDetailObj = new AmountThresholdDetailImpl( amtMap );			
				boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" );
				if ( !isAmtThresholdPresnet )
				{
					genericHelperObj.clickNewAction( clientPartition );
					amtThresholdDetailObj.configureThresholdLegends();													
					amtThresholdDetailObj.saveAmtThreshold();
					assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
					Log4jHelper.logInfo( "Amount Threshold is created successfully:" +name);					
				}
				else
					Log4jHelper.logInfo( "Amount Threshold  is already presnet for " + name );
			}			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public void amountThresholdCopyCurrencyCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Amount Threshold" );
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				amtMap = excelHolderObj.dataMap( paramVal );

				clientPartition = ExcelHolder.getKey( amtMap, "Partition" );
				name = ExcelHolder.getKey( amtMap, "Name" );
				AmountThresholdDetailImpl amtThresholdDetailObj = new AmountThresholdDetailImpl( amtMap );			
				boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" );
				if ( !isAmtThresholdPresnet )
				{
					genericHelperObj.clickNewAction( clientPartition );
					amtThresholdDetailObj.configureThresholdLegends();
					//LinkedHashSet<String> getsrcGridData= amtThresholdDetailObj.getGridData();
					//amtThresholdDetailObj.validateGridRowData( getsrcGridData, "thresholdCurrencyGrid", 1 );
					amtThresholdDetailObj.saveAmtThreshold();
					assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
					Log4jHelper.logInfo( "Amount Threshold is created successfully:" +name);					
				}
				else
					Log4jHelper.logInfo( "Amount Threshold  is already presnet for " + name );
			}			
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	
	public void searchScreenColumnsValidation() throws Exception
	{
	
	NavigationHelper.navigateToScreen( "Re-Aggregation Request" );
	for ( paramVal = 0; paramVal < colSize; paramVal++ )
	{
		amtMap = excelHolderObj.dataMap( paramVal );
		String searchScreenColumns = ExcelHolder.getKey( amtMap, "SearchScreenColumns" );
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
	 * This method is to change status amount threshold
	 */
	public void amountThresholdChangeStatus() throws Exception {

		NavigationHelper.navigateToScreen( "Amount Threshold" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			amtMap = excelHolderObj.dataMap( paramVal );
			
			name = ExcelHolder.getKey( amtMap, "Name" );	
			
			boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" );
			if ( isAmtThresholdPresnet )
			{
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "No", "Online" ) );
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToAction( "Change Status", "Set Status To Online" );
				GenericHelper.waitForLoadmask();
				assertTrue( genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" ) );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", "No", "Online" ) );
				Log4jHelper.logInfo("Amount Threshold is status is changed successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Amount Threshold is not available with name " + name);
			}

		}
	}
	/*
	 * This method is to delete amount threshold
	 */
	public void amountThresholdDelete() throws Exception {

		NavigationHelper.navigateToScreen( "Amount Threshold" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			amtMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( amtMap, "Partition" );
			name = ExcelHolder.getKey( amtMap, "Name" );		

			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "PS_Detail_Amtthreshold_name_txtId", name, "Name" );
			if ( isAmtThresholdPresnet )
			{
				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Amount Threshold is deleted successfully with name " + name);

			} else {
				Log4jHelper.logInfo("Amount Threshold is not available with name " + name);
			}

		}
	}

	/*
	 * This method is to un delete amount threshold
	 */
	public void amountThresholdUnDelete() throws Exception {

		NavigationHelper.navigateToScreen( "Amount Threshold" );
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			amtMap = excelHolderObj.dataMap( paramVal );

			clientPartition = ExcelHolder.getKey( amtMap, "Partition" );
			name = ExcelHolder.getKey( amtMap, "Name" );	

			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			GenericHelper.waitForLoadmask();
			boolean isAmtThresholdPresnet = genericHelperObj.isGridTextValuePresent( "pamtName", name, "Name" );
			if ( isAmtThresholdPresnet ){
				genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), name);
				Log4jHelper.logInfo("Amount Threshold is un deleted successfully : " + name);

			} else {
				Log4jHelper.logInfo("Amount Threshold is not available : " + name);
			}

		}
	}
	
}
