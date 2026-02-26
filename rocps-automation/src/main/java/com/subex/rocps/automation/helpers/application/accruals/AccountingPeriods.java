package com.subex.rocps.automation.helpers.application.accruals;

import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accountingperiod.AccountingPeriodSearchImpl;
import com.subex.rocps.automation.helpers.application.accruals.accountingperioddefinition.AccountingPeriodDefnDetailImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterHelper;
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

public class AccountingPeriods extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> accPeriodExcel = null;
	protected Map<String, String> accPeriodMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String accountingPeriod;
	protected String criteria;
	protected String date;
	protected String colHeader;
	protected String results;
	protected Map<String, ArrayList<String>> excelReaderMapObj = null;
	protected Map<String, String> mapObj = null;
	String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public AccountingPeriods( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accPeriodExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( accPeriodExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public AccountingPeriods( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		accPeriodExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( accPeriodExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for search screen column validation
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Accounting Period" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accPeriodMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( accPeriodMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	public boolean isAccountingPeriodPresent() throws Exception
	{
		genericHelperObj.waitforHeaderElement( "Accounting Period Definition" );
		GridFilterSearchHelper.gridFilterAdvancedSearch( "accPrdInstEntitys$accPrdDfnEntity$accountingPeriodDfn", accountingPeriod, "Accounting Period Definition" );
		SearchGridHelper.gridFilterSearchWithTextBox( "pacpRefTblValues", criteria, "Accounting Period Criteria" );
		if ( !date.isEmpty() )
			CalendarHelper.setOnDate( "dummyValidOnDataProperty", date );
		//genericHelperObj.setDate( "dummyValidOnDataProperty", date );
		return GridHelper.isValuePresent( "SearchGrid", accountingPeriod, "Accounting Period Definition" );
	}

	/*
	 * This method is for view reference table  action
	 */
	public void viewReferenceTables() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accPeriodMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( accPeriodMap );
			String refTable = ExcelHolder.getKey( accPeriodMap, "Reference Table" );
			String value = ExcelHolder.getKey( accPeriodMap, "Value" );

			if ( isAccountingPeriodPresent() )
			{
				GridHelper.clickRow( "SearchGrid", accountingPeriod, "Accounting Period Definition" );

				PSGenericHelper.waitForParentActionElementTOBeclickable( "View Reference Tables" );
				NavigationHelper.navigateToAction( "View Reference Tables", "View Reference Tables" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( ElementHelper.isElementPresent( "//div[contains(text(),'Value')]" ), "View Reference Table Page is not opened" );
				String[] refTableArr = refTable.split( regex, -1 );
				String[] valueArr = value.split( regex, -1 );
				ArrayList<String> actualName = GridHelper.getColumnValues( "viewRefTableGrid", "Name" );
				List<String> excelRefVal = Arrays.asList( refTableArr );
				List<String> excelVal = Arrays.asList( valueArr );
				assertEquals( actualName, excelRefVal, "Reference Table Values are not matching" );
				ArrayList<String> actualValue = GridHelper.getColumnValues( "viewRefTableGrid", "Value" );
				assertEquals( actualValue, excelVal, "Values are not matching" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ButtonHelper.click( "accPrdViewRefTableDetail.cancel" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				Log4jHelper.logInfo( "Accounting Period View Reference table is validated successfully :" + accountingPeriod );

			}
			else
				Log4jHelper.logInfo( "Accounting period definition  is not available with :" + accountingPeriod );
		}
	}

	/*
	 * This method is for view Usage Results
	 */
	public void viewUsageResults() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accPeriodMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( accPeriodMap );
			colHeader = ExcelHolder.getKey( accPeriodMap, "ColHeader" );
			results = ExcelHolder.getKey( accPeriodMap, "Results" );
			String accMod = ExcelHolder.getKey( accPeriodMap, "AccrualsModelling" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( isAccountingPeriodPresent() )
			{
				for ( int i = 0; i < 5; i++ )
				{
					try
					{
						ButtonHelper.click( "SearchButton" );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						GridHelper.clickRow( "SearchGrid", accountingPeriod, "Accounting Period Definition" );
						Thread.sleep( 2000 );
						PSGenericHelper.waitForParentActionElementTOBeclickable( "View Usage Results" );
						genericHelperObj.validateActionText( "View Usage Results", accMod );
						NavigationHelper.navigateToAction( "View Usage Results", accMod );
						Thread.sleep( 2000 );
						GenericHelper.waitForLoadmask( searchScreenWaitSec );
						break;
					}
					catch ( Exception e )
					{
						Log4jHelper.logInfo( " This exception " + e.getMessage() + " viewUsageResults() for accounting period" );

					}
					finally
					{
						Log4jHelper.logInfo( "Retring with " + ( i + 1 ) + " in viewUsageResults() for accounting period" );
					}
				}

				//assertTrue( ElementHelper.isElementPresent( "//div[contains(text(),'Event Date')]" ), "View usage results Page is not opened" );
				AccountingPeriodSearchImpl searchObj = new AccountingPeriodSearchImpl( accPeriodMap );
				searchObj.filterOperations();
				int rowCount = GridHelper.getRowCount( "SearchGrid" );

				if ( rowCount != 0 && !results.isEmpty() )
				{
					DataVerificationHelper verifyObj = new DataVerificationHelper();
					verifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", accPeriodMap, colHeader, results, true );
				}
				Log4jHelper.logInfo( "Accruals Usage Results validated successfully" );
			}
			else
				Log4jHelper.logInfo( "Accounting period is not available with :" + accountingPeriod );
		}
	}

	/*
	 * This method is for view Usage Results
	 */
	public void underReviewAction() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accPeriodMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( accPeriodMap );
			colHeader = ExcelHolder.getKey( accPeriodMap, "ColHeader" );
			results = ExcelHolder.getKey( accPeriodMap, "Results" );
			String accMod = ExcelHolder.getKey( accPeriodMap, "AccrualsModelling" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( isAccountingPeriodPresent() )
			{
				GridHelper.clickRow( "SearchGrid", accountingPeriod, "Change Status" );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Open" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", accMod );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( PopupHelper.isPresent() )
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Under Review" );
				Log4jHelper.logInfo( "Accruals Usage Results validated successfully" );
			}
			else
				Log4jHelper.logInfo( "Accounting period is not available with :" + accountingPeriod );
		}
	}

	public void closeAccountingPeriod() throws Exception
	{

		NavigationHelper.navigateToScreen( "Accounting Period" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			accPeriodMap = excelHolderObj.dataMap( paramVal );
			initializeVariables( accPeriodMap );

			String accMod = ExcelHolder.getKey( accPeriodMap, "AccrualsModelling" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			if ( isAccountingPeriodPresent() )
			{
				GridHelper.clickRow( "SearchGrid", accountingPeriod, "Change Status" );
				//assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Open" );
				PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
				NavigationHelper.navigateToAction( "Change Status", accMod );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( PopupHelper.isPresent() )
					ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertEquals( GridHelper.getCellValue( "SearchGrid", 1, "Status" ), "Closed" );
				Log4jHelper.logInfo( "Accruals Usage Results validated successfully" );
			}
			else
				Log4jHelper.logInfo( "Accounting period is not available with :" + accountingPeriod );
		}
	}

	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		accountingPeriod = ExcelHolder.getKey( map, "Accounting Period" );
		criteria = ExcelHolder.getKey( map, "Criteria" );
		date = ExcelHolder.getKey( map, "Date" );
	}
}
