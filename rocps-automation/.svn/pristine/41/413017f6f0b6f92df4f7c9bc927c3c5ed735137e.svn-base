package com.subex.rocps.automation.helpers.application.accruals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accountingperioddefinition.AccountingPeriodDefnDetailImpl;
import com.subex.rocps.automation.helpers.application.accruals.accrualsoverviewmodelling.AccOverviewModDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class AccountingPeriodDefinition extends PSAcceptanceTest{
	
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> accDefnExcel = null;
	protected Map<String, String> accDefnMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String partition;
	protected String name;
	protected String prefix;
	protected String mergedTablePrefix;
	protected String aggrConfiguration;
	protected String schema;
	protected String billedUsage;
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
	public AccountingPeriodDefinition( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accDefnExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( accDefnExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AccountingPeriodDefinition( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accDefnExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( accDefnExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the accounting period definition
	 * 
	 */
	public void configureAccountingPeriodDefinition() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Accounting Period Definition" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				accDefnMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( accDefnMap );
				boolean isAccPeriodDefnPresent = genericHelperObj.isGridTextValuePresent( "papdName", name, "Name" );
				if(!isAccPeriodDefnPresent)
					newAccountingPeriodDefnConfig();
				else
					Log4jHelper.logInfo( "Accounting period definition is already availablewith name :" +name);			
				
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}
	
	public void newAccountingPeriodDefnConfig() throws Exception
	{
		AccountingPeriodDefnDetailImpl detailObj = new AccountingPeriodDefnDetailImpl( accDefnMap );
		detailObj.newAccountingPeriodDefn();
		detailObj.accountingPeriodFrequency();
		detailObj.apdCategories();
		detailObj.saveAcountingPeriodDefn();
	}
	
	
	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Accounting Period Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accDefnMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( accDefnMap, "SearchScreenColumns" );
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
	 * This method is for sync accounting periods  action
	 */
	public void syncAccountingPeriods() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accDefnMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( accDefnMap, "Name" );	
			boolean isAccPeriodDefnPresent = genericHelperObj.isGridTextValuePresent( "papdName", name, "Name" );
			if(isAccPeriodDefnPresent)
			{
				GridHelper.clickRow( "SearchGrid", name, "Name" );				
				
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Accounting Period" );
				NavigationHelper.navigateToAction( "Accounting Period", "Sync Accounting Periods" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );	
				Thread.sleep(2000);
				if(PopupHelper.isPresent())
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );			
				Log4jHelper.logInfo( "Accounting period definition is synced successfully :" + name );
			
			}
			else
				Log4jHelper.logInfo( "Accounting period definition  is not available with :" + name );
		}
	}
	
	/*
	 * This method is for edit Accruals Overview Modelling
	 */
	public void accountingPeriodDefnEdit() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accDefnMap = excelHolderObj.dataMap( paramVal );
			name = ExcelHolder.getKey( accDefnMap, "Name" );			
					
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isAccPeriodDefnPresent = genericHelperObj.isGridTextValuePresent( "papdName", name, "Name" );
			if(isAccPeriodDefnPresent)
			{
				int row = GridHelper.getRowNumber( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToEdit( "SearchGrid", row );
				assertTrue( ElementHelper.isElementPresent( "//*[text()='Accounting  Period  Frequency']" ), "Edit Accrual Overview Modelling Page is not opened" );
				AccountingPeriodDefnDetailImpl detailObj = new AccountingPeriodDefnDetailImpl( accDefnMap );				
				detailObj.accountingPeriodFrequency();
				detailObj.apdCategories();
				detailObj.saveAcountingPeriodDefn();
				Log4jHelper.logInfo( "Accounting period definition is Edited successfully :" + name );
			}
			else
				Log4jHelper.logInfo( "Accounting period definition is not available with :" + name );
		}
	}

	
	/*
	 * This method is to delete Accruals Overview Modelling
	 */
	public void accountingPeriodDefnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accDefnMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( accDefnMap, "Partition" );
			name = ExcelHolder.getKey( accDefnMap, "Name" );			
			genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
			boolean isAccPeriodDefnPresent = genericHelperObj.isGridTextValuePresent( "papdName", name, "Name" );
			if(isAccPeriodDefnPresent)
			{
				
				GridHelper.clickRow( "SearchGrid", name, "Name" );
				NavigationHelper.navigateToAction( "Common Tasks", "Delete" );
				GenericHelper.waitForLoadmask();
				if(PopupHelper.isPresent())
					ButtonHelper.click( "YesButton" );
				GenericHelper.waitForLoadmask();				
				if(PopupHelper.isPresent())
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask();			
				genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ));
				Log4jHelper.logInfo( "Accounting period definition is deleted successfully with name " + name );
			}
			else
				Log4jHelper.logInfo( "Accounting period definition is not available with name " + name );
		}
	}

	/*
	 * This method is to un delete Accruals Overview Modelling
	 */
	public void accountingPeriodDefnUnDelete() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period Definition" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accDefnMap = excelHolderObj.dataMap( paramVal );

			partition = ExcelHolder.getKey( accDefnMap, "Partition" );
			name = ExcelHolder.getKey( accDefnMap, "Name" );
			genericHelperObj.selectPartitionFilter( partition, "Deleted Items" );
			GenericHelper.waitForLoadmask();
			
			boolean isAccPeriodDefnPresent = genericHelperObj.isGridTextValuePresent( "papdName", name, "Name" );
			if(isAccPeriodDefnPresent)
			{
				genericHelperObj.clickDeleteOrUnDeleteAction( name, "Name", "Undelete" );
				GenericHelper.waitForLoadmask();
				if(PopupHelper.isPresent())
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask();	
				genericHelperObj.selectPartitionFilter( partition, "Non Deleted Items" );
				assertTrue( GridHelper.isValuePresent( "SearchGrid", name, "Name" ) );
				Log4jHelper.logInfo( "Accounting period definition is un deleted successfully : " + name );
			}
			else
				Log4jHelper.logInfo( "Accounting period definition is not available : " + name );
		}
	}
	
	public void initializeVariables(Map<String, String> map) throws Exception
	{
		name = ExcelHolder.getKey( map, "Name" );
		partition = ExcelHolder.getKey( map, "Partition" );
		
	}
}
