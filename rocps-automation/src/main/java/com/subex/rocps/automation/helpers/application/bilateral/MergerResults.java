package com.subex.rocps.automation.helpers.application.bilateral;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
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
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class MergerResults extends PSAcceptanceTest
{
	protected ExcelReader excelData;
	protected ExcelHolder excelHolderObj;
	protected Map<String, ArrayList<String>> mergerExcelMap;
	protected Map<String, String> mergerMap;

	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected int occurence;
	protected String account;
	protected String bilateralModelling;
	protected String eventDate;
	protected String billProfile;
	protected String band;
	protected String tariffRateName;
	protected String tariff;
	protected String billPeriod;
	protected String isRatable;
	protected String service;
	protected String itemCode;
	protected String deal;
	protected String contractNo;
	protected String dealTier;
	protected String dealBandGroup;
	protected String category;
	protected String direction;
	protected String show;
	protected String threshold;
	protected String colHeader;
	protected String results;
	protected String eventFromDate;
	protected String eventToDate;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();
	protected AdvanceSearchFiltersHelper advanceFilterObj = new AdvanceSearchFiltersHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public MergerResults( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		mergerExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( mergerExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public MergerResults( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		excelData = new ExcelReader();
		mergerExcelMap = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( mergerExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Method to validate merger results 
	 */
	public void viewMergerResults() throws Exception
	{
		try
		{
			
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mergerMap = excelHolderObj.dataMap( paramVal );
				NavigationHelper.navigateToScreen( "Merger Results" );
				GenericHelper.waitForLoadmask();
			//mergerMap = excelHolderObj.dataMap( 0 );
			initialiseVariables( mergerMap );
			viewResults();

			Log4jHelper.logInfo( "Merger results is validated successfully" );
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}
	/*
	 * Method to validate merger results 
	 */
	public void isMergerResultsPresent() throws Exception
	{
		try
		{
			
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				mergerMap = excelHolderObj.dataMap( paramVal );
				NavigationHelper.navigateToScreen( "Merger Results" );
				GenericHelper.waitForLoadmask();
			//mergerMap = excelHolderObj.dataMap( 0 );
			initialiseVariables( mergerMap );
			isViewResultsPresent();

			Log4jHelper.logInfo( "Merger results is validated successfully" );
			}

		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}

	public void viewResults() throws Exception
	{

		assertTrue( ElementHelper.isElementPresent( "//*[text()='BRD Modelling']" ), "Merger Result Search Screen did not open" );
		filterOperations();
		int rowCount = GridHelper.getRowCount( "SearchGrid" );
	
		if ( rowCount !=0 && !results.isEmpty() )
		{
			DataVerificationHelper verifyObj = new DataVerificationHelper();
			//verifyObj.validateDataInResultScreen( "grid_column_header_searchGrid_", mergerMap, colHeader, results, true );
			verifyObj.validateDataWithoutSorting("grid_column_header_searchGrid_", mergerMap, colHeader, results, true);
		}
		Log4jHelper.logInfo( "Merger Results validated successfully" );

	}
	public void isViewResultsPresent() throws Exception
	{

		assertTrue( ElementHelper.isElementPresent( "//*[text()='BRD Modelling']" ), "Merger Result Search Screen did not open" );
		filterOperations();
		int rowCount = GridHelper.getRowCount( "SearchGrid" );
	
		if ( rowCount !=0 && !results.isEmpty() )
			Log4jHelper.logInfo( "Merger Results validated successfully" );
		else
		FailureHelper.failTest( "Merger Results are not  present" );

	}

	public void filterOperations() throws Exception
	{
		advanceFilterObj.brdModelingAdvanceSearch( "BRD Modelling", bilateralModelling );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Thread.sleep( 1000 );
		ElementHelper.waitForElement( "//*[text()='Band']", searchScreenWaitSec );
		//genericObj.collapsableXpath();

		//CalendarHelper.setOnDate( "PS_searchPanelId", "bimr$evt_dttm", eventDate );
		setCalendar();
		advanceFilterObj.billProfileAdvanceSearch( "Bill Profile", billProfile );
		if ( !band.isEmpty() )
			advanceFilterObj.bandAdvanceSearch( "Band", band );
		if ( !tariffRateName.isEmpty() )
			ComboBoxHelper.select( "trn_trn_name_gwt_uid_", tariffRateName );
		if ( !tariff.isEmpty() )
			advanceFilterObj.tariffAdvanceSearch( "Tariff", tariff );

		if ( !billPeriod.isEmpty() )
			advanceFilterObj.billPeriodAdvanceSearch( "Bill Period", billPeriod );
		if ( !isRatable.isEmpty() )
			ComboBoxHelper.select( "vocb_rev_ratable_fl_gwt_uid_", isRatable );
		if ( !service.isEmpty() )
			advanceFilterObj.serviceAdvanceSearch( "Service", service );

		if ( !itemCode.isEmpty() )
			TextBoxHelper.type( "//input[contains(@id,'$item_code')]", itemCode );
		if ( !deal.isEmpty() )
			advanceFilterObj.dealAdvanceSearch( "Deal", account, contractNo );
		if ( !dealTier.isEmpty() )
			ComboBoxHelper.select( "pdlt_pdlt_from_val__pdlt_pdlt_to_val_", dealTier );
		if ( !dealBandGroup.isEmpty() )
			ComboBoxHelper.select( "pdbg_pdbg_name_", dealBandGroup );
		if ( !category.isEmpty() )
			ComboBoxHelper.select( "bimr_brd_cat_", category );
		if ( !direction.isEmpty() )
			ComboBoxHelper.select( "bimr_brd_direction_", direction );
		if ( !show.isEmpty() )
		{
			//ElementHelper.scrollDown( "//*[contains(@id,'pdel_Id_gwt_uid_')]", false );
			ComboBoxHelper.select( "pdel_Id_gwt_uid_", show );
		}
		if ( !threshold.isEmpty() )
		{
			//ElementHelper.scrollDown( "//*[contains(@id,'pdlt_Id')]", false );
			TextBoxHelper.type( "pdlt_Id", threshold );
		}
		
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	/*
	 * Method for selecting calendar in merger result screen
	 */
	public void setCalendar() throws Exception
	{

		if ( !eventFromDate.isEmpty() && !eventToDate.isEmpty() )
			CalendarHelper.setDate( "PS_searchPanelId", "PS_calendarButtonId", "Between", eventFromDate, eventToDate );
		else if ( !eventFromDate.isEmpty() )
			CalendarHelper.setOnDate( "PS_searchPanelId", "PS_calendarButtonId", eventFromDate );
		else
			throw new RuntimeException( "From date field cannot be left empty" );
	}
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Merger Results" );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			mergerMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( mergerMap, "SearchScreenColumns" );
			bilateralModelling = ExcelHolder.getKey( mergerMap, "BilateralModelling" );
			advanceFilterObj.brdModelingAdvanceSearch( "BRD Modelling", bilateralModelling );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			Thread.sleep( 1000 );
			ElementHelper.waitForElement( "//*[text()='Band']", searchScreenWaitSec );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			//filterOperations();
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericObj.totalColumns( excelColumnNames );
		}
	}
	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		account = ExcelHolder.getKey( map, "Account" );
		//eventDate = ExcelHolder.getKey( map, "EventDate" );
		colHeader = ExcelHolder.getKey( map, "ColHeader" );
		results = ExcelHolder.getKey( map, "Results" );
		bilateralModelling = ExcelHolder.getKey( map, "BilateralModelling" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		band = ExcelHolder.getKey( map, "Band" );
		tariffRateName = ExcelHolder.getKey( map, "TariffRateName" );
		tariff = ExcelHolder.getKey( map, "Tariff" );
		billPeriod = ExcelHolder.getKey( map, "BillPeriod" );
		isRatable = ExcelHolder.getKey( map, "IsRatable" );
		service = ExcelHolder.getKey( map, "Service" );
		itemCode = ExcelHolder.getKey( map, "ItemCode" );
		deal = ExcelHolder.getKey( map, "Deal" );
		contractNo = ExcelHolder.getKey( map, "ContractNo" );
		dealTier = ExcelHolder.getKey( map, "DealTier" );
		dealBandGroup = ExcelHolder.getKey( map, "DealBandGroup" );
		category = ExcelHolder.getKey( map, "Category" );
		direction = ExcelHolder.getKey( map, "Direction" );
		show = ExcelHolder.getKey( map, "Show" );
		threshold = ExcelHolder.getKey( map, "Threshold" );
		eventFromDate = ExcelHolder.getKey( map, "EventFromDate" );
		eventToDate = ExcelHolder.getKey( map, "EventToDate" );

	}

}
