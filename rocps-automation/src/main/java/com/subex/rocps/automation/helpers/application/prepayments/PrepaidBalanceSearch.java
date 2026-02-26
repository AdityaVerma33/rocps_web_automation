package com.subex.rocps.automation.helpers.application.prepayments;

import org.testng.annotations.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.prepayments.prepaidbalance.PrepaidBalanceActionImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PrepaidBalanceSearch extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> prepaidExcel = null;
	protected Map<String, String> prepaidMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	String billProfile;
	String balanceHistoryColHeaders;
	String balanceHistoryResults;
	String prepaymentsResults;
	String prepaymentsColHeaders;
	String colHeaders;
	String results;
	String date;
	String account;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	PrepaidBalanceActionImpl prepaidActionObj = new PrepaidBalanceActionImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public PrepaidBalanceSearch( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		prepaidExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( prepaidExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public PrepaidBalanceSearch( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		prepaidExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( prepaidExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Prepaid Balance
	 * 
	 */
	public void prepaidBalanceResults() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Prepaid Balance" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				prepaidMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( prepaidMap );

				if ( isprepaidBalancePresnet() )
					prepaidBalanceValidation();
				else
					Log4jHelper.logInfo( "Prepaid Balance is not availabe for" + billProfile );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for prepaidBalance Validation
	 */
	public void prepaidBalanceValidation() throws Exception
	{
		GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
		if ( ValidationHelper.isNotEmpty( results ) )
		{
			dataVerifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", prepaidMap, colHeaders, results, false );
			Log4jHelper.logInfo( "Prepaid Balance is validated successfully for: " + billProfile );
		}

		if ( ValidationHelper.isNotEmpty( balanceHistoryResults ) )
			balanceHistoryValidation();
			GenericHelper.waitForLoadmask();
		if ( ValidationHelper.isNotEmpty( prepaymentsResults ) )
			prepaymentResultsValidation();
			GenericHelper.waitForLoadmask();

	}

	/*
	 * This method is for balance history validation
	 */
	public void balanceHistoryValidation() throws Exception
	{

		prepaidActionObj.clickBalanceHistoryAction();
		//calender( "PS_Search_prepaidBalance_calender_filterID" );
		GridHelper.clickRow( "SearchGrid", 1, "Bill Profile" );
		dataVerifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", prepaidMap, balanceHistoryColHeaders, balanceHistoryResults, false );
		ButtonHelper.clickIfEnabled( "CancelButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
		Log4jHelper.logInfo( "Prepaid Balance History is validated successfully for: " + billProfile );

	}

	/*
	 * This method is for prepayment Results validation
	 */
	public void prepaymentResultsValidation() throws Exception
	{

		prepaidActionObj.clickPaymentsAction();
		TextBoxHelper.type( "PS_Search_prepaidBalance_account_txtID", account );
		ButtonHelper.click( "SearchButton" );
		GridHelper.sortGrid( "SearchGrid", "Created On" );
		GridHelper.sortGrid( "SearchGrid", "Created On" );
		GridHelper.clickRow( "SearchGrid", 1, "Transaction Reference" );
		dataVerifyObj.validateData( "popupWindow", "grid_column_header_searchGrid_", prepaidMap, "SearchGrid", prepaymentsColHeaders, prepaymentsResults );
		ButtonHelper.click( "CancelButton" );
		Log4jHelper.logInfo( "prepayments is validated successfully for: " + billProfile );

	}

	/*
	 * This method is to check if prepaidbalance is presnet or not
	 */
	public boolean isprepaidBalancePresnet() throws Exception
	{
		ButtonHelper.click( "ClearButton" );
		boolean flag = false;
		String actualBillProfile = retriveBillProfileValue( billProfile, account );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Search_prepaidBalance_billProfile_comboID", actualBillProfile, "Bill Profile" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowNumberContains( "SearchGrid", billProfile, "Bill Profile" );
		if ( row > 0 )
		{
			flag = true;
		}
		return flag;
	}

	/*
	 * this method is to validate the search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{

		NavigationHelper.navigateToScreen( "Prepaid Balance" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			prepaidMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( prepaidMap, "SearchScreenColumns" );
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
	 * This method is for calender
	 */
	public void calender( String filterIconID ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon( filterIconID );
		CalendarHelper.setOnDate( "PS_Search_prepaidBalance_calenderID", date );
		ButtonHelper.click( "SearchButton" );
		GridHelper.sortGrid( "popupWindow", "searchGrid", "Date" );
		GridHelper.sortGrid( "popupWindow", "searchGrid", "Date" );
	}
	
	public String retriveBillProfileValue( String billProfile, String account ) throws Exception
	{
		DBHelper dbHelper = null;
		ResultSet value;
		String currencyActual = null;

		try
		{
			dbHelper = DBHelper.connectToReferenceDB( false );

			if ( dbHelper != null )
			{
				String query = "select PBIP_DISPLAY from BILL_PROFILE where PBIP_NAME= ? AND PACC_ID IN (select PACC_ID from ACCOUNT where PACC_NAME = ?)";
				
				PreparedStatement stmt = dbHelper.dbConnection.prepareStatement( query );
				stmt.setString( 1, billProfile );
				stmt.setString( 2, account );
				ResultSet rs = stmt.executeQuery();

				while ( rs.next() )
				{
					currencyActual = rs.getString( 1 );
				}
			}

			return currencyActual;
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
		finally
		{
			if ( dbHelper != null )
				dbHelper.closeConnection();
		}
	}

	/*
	 *This method is for initialize instance variables
	 */
	public void initializeVariables( Map<String, String> map ) throws Exception
	{
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		colHeaders = ExcelHolder.getKey( map, "ColHeaders" );
		results = ExcelHolder.getKey( map, "Results" );
		balanceHistoryColHeaders = ExcelHolder.getKey( map, "BalanceHistory ColHeaders" );
		balanceHistoryResults = ExcelHolder.getKey( map, "BalanceHistory Results" );
		prepaymentsColHeaders = ExcelHolder.getKey( map, "Prepayment ColHeaders" );
		prepaymentsResults = ExcelHolder.getKey( map, "Prepayment Results" );
		date = ExcelHolder.getKey( map, "Date" );
		account = ExcelHolder.getKey( map, "Account" );
	}

}
