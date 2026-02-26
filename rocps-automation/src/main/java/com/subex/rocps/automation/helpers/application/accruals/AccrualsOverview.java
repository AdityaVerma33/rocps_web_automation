package com.subex.rocps.automation.helpers.application.accruals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.accruals.accountingperiod.AccountingPeriodSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;

public class AccrualsOverview extends PSAcceptanceTest
{
	
		protected ExcelReader excelData = null;
		protected Map<String, ArrayList<String>> accOvwExcel = null;
		protected Map<String, String> accOvwMap = null;
		protected ExcelHolder excelHolderObj = null;
		protected String path;
		protected String workBookName;
		protected String sheetName;
		protected String testCaseName;
		protected int paramVal;
		protected int colSize;
		protected String accrualOverviewModelling;
		protected String accruedMonth;
		
		protected String trafficMonth;
		
		protected String account;
		protected String billProfile;
		protected String homeCurrency;
		protected String originalCurrency;
		protected String accrualOverviewStatus;
		protected String cashFlow;
		protected String colHeader;
		protected String results;
		protected Map<String, ArrayList<String>> excelReaderMapObj = null;
		protected Map<String, String> mapObj = null;
		String regex = new PSStringUtils().regexFirstLevelDelimeter();
		protected PSGenericHelper genericHelperObj = new PSGenericHelper();
		DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
		protected AdvanceSearchFiltersHelper advanceFilterObj = new AdvanceSearchFiltersHelper();
		/*
		 * Constructor for initializing excel Identifying the column size from the
		 * map passed
		 */
		public AccrualsOverview( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
		{
			this.path = path;
			this.workBookName = workBookName;
			this.sheetName = sheetName;
			this.testCaseName = testCaseName;
			excelData = new ExcelReader();
			accOvwExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
			excelHolderObj = new ExcelHolder( accOvwExcel );
			colSize = excelHolderObj.totalColumns();
		}

		/*
		 * Overloaded constructor for reading data from excel if test case name
		 * appears more than once
		 * 
		 * @Param occurance : Test case instance in excel sheet Constructor for
		 * initializing excel Identifying the column size from the map
		 */
		public AccrualsOverview( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
		{
			this.path = path;
			this.workBookName = workBookName;
			this.sheetName = sheetName;
			this.testCaseName = testCaseName;
			excelData = new ExcelReader();
			accOvwExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
			excelHolderObj = new ExcelHolder( accOvwExcel );
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
				accOvwMap = excelHolderObj.dataMap( paramVal );
				String searchScreenColumns = ExcelHolder.getKey( accOvwMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String[] searchGridColumnsArr = searchScreenColumns.split( regex, -1 );
				for ( int col = 0; col < searchGridColumnsArr.length; col++ )
				{
					excelColumnNames.add( searchGridColumnsArr[col] );
				}
				genericHelperObj.totalColumns( excelColumnNames );
			}

		}
		
		public void filterOperations() throws Exception
		{
			ComboBoxHelper.select( "accrOverviewModelling_gwt_uid_", accrualOverviewModelling );
			/*setCalendar( accruedMonthStartDate, accruedMonthEndDate );
			setCalendar( trafficMonthStartDate, trafficMonthEndDate );*/
			advanceFilterObj.billProfileAdvanceSearch( "Bill Profile", billProfile );
			CalendarHelper.setOnDate( "PS_searchPanelId", "aom_accrual_month_dttm", accruedMonth );
			CalendarHelper.setOnDate( "PS_searchPanelId", "aom_traffic_month_dttm", trafficMonth );
			advanceFilterObj.accountAdvanceSearch( "Account", account );
			
			ComboBoxHelper.select( "currency_curName_gwt_uid_", homeCurrency );
			ComboBoxHelper.select( "currency0_curName_gwt_uid_", originalCurrency );
			ComboBoxHelper.select( "accrualStatus_pausName_gwt_uid_", accrualOverviewStatus );
			ComboBoxHelper.select( "aom_cash_flow_gwt_uid_", cashFlow );
		}
	
		/*
		 * Method for selecting calendar in merger result screen
		 */
		public void setCalendar(String fromDate, String toDate) throws Exception
		{

			/*if ( !fromDate.isEmpty() && !toDate.isEmpty() )
				CalendarHelper.setDate( "PS_searchPanelId", "aom_accrual_month_dttm", "Between", fromDate, toDate );
			else*/ if ( !fromDate.isEmpty() )
				CalendarHelper.setOnDate( "PS_searchPanelId", "aom_accrual_month_dttm", fromDate );
			else
				throw new RuntimeException( "From date field cannot be left empty" );
		}
		
		/*
		 * This method is for view  Results
		 */
		public void viewAccrualOverviewResults() throws Exception
		{

			NavigationHelper.navigateToScreen( "Accruals Overview" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				accOvwMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( accOvwMap );
				
				GenericHelper.waitForLoadmask( searchScreenWaitSec );	
				genericHelperObj.collapsableXpath();
				assertTrue( ElementHelper.isElementPresent( "//div[contains(text(),'Accruals Overview Modelling')]" ), "Accruals Overview Page is not opened" );
				filterOperations();
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );				
					
					int rowCount = GridHelper.getRowCount( "SearchGrid" );
					
					if ( rowCount !=0 && !results.isEmpty() )
					{
						DataVerificationHelper verifyObj = new DataVerificationHelper();
						verifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", accOvwMap, colHeader, results, true );
					}
					Log4jHelper.logInfo( "Accruals Usage Results validated successfully" );				
				
			}
		}

		
		public void initializeVariables(Map<String, String> map) throws Exception
		{
			accrualOverviewModelling = ExcelHolder.getKey( map, "AccrualOverviewModelling" );
			accruedMonth = ExcelHolder.getKey( map, "AccruedMonth" );
			
			trafficMonth =  ExcelHolder.getKey( map, "TrafficMonth" );
			
			account = ExcelHolder.getKey( map, "Account" );
			billProfile = ExcelHolder.getKey( map, "BillProfile" );
			homeCurrency =  ExcelHolder.getKey( map, "HomeCurrency" );
			originalCurrency = ExcelHolder.getKey( map, "OriginalCurrency" );
			accrualOverviewStatus =  ExcelHolder.getKey( map, "AccrualOverviewStatus" );
			cashFlow = ExcelHolder.getKey( map, "CashFlow" );
			colHeader = ExcelHolder.getKey( map, "ColHeader" );
			results =  ExcelHolder.getKey( map, "Results" );
		
		}
}
