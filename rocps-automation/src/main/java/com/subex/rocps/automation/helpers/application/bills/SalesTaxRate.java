package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
//import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.salestax.SalesTaxRateImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesTaxRate extends PSAcceptanceTest
{
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> salesTaxExcel = null;
	protected Map<String, String> salesRateMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String salesTaxName;
	protected String validOnFilter;
	protected String colmnHeadersScreen;
    protected String mapRowKeys;
	protected String country;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
    DataVerificationHelper dataverification=new DataVerificationHelper();
	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public SalesTaxRate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		salesTaxExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( salesTaxExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public SalesTaxRate( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		salesTaxExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( salesTaxExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Sales Tax Rate
	 * 
	 * @method : isSalesTaxRatePresent returns false then Sales Tax Rate is
	 * configured isSalesTaxRatePresent returns true then Sales Tax Rate is not
	 * configured
	 */

	public void salesTaxRateCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Sales Tax Rate" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesRateMap = excelHolderObj.dataMap( paramVal );
				assertEquals( NavigationHelper.getScreenTitle(), "Sales Tax Rate Search" );
				//ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				initializeVariables( salesRateMap );
				boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Sales Tax" );
				if ( !isSalesTaxPresent )
				{

					newSalesTaxRate();
					assertTrue( GridHelper.isValuePresent( "Detail_salesTaxRate_gridID", salesTaxName, "Sales Tax" ), "Sales Tax Rate is not configured" );
					Log4jHelper.logInfo( "Sales Tax Rate is created successfully with name " + salesTaxName );
				}
				else
					Log4jHelper.logInfo( "Sales Tax Rate is available with name " + salesTaxName );

			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*Method for edit sales tax rate*/

	public void salesTaxRateEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Sales Tax Rate" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesRateMap = excelHolderObj.dataMap( paramVal );
				assertEquals( NavigationHelper.getScreenTitle(), "Sales Tax Rate Search" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				initializeVariables( salesRateMap );
				boolean isSalesTaxPresent = GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Sales Tax" );
				if ( isSalesTaxPresent )
				{
					int row = GridHelper.getRowNumber( "SearchGrid", salesTaxName, "Sales Tax" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editSalesTaxRate();
					Log4jHelper.logInfo( "Sales Tax Rate is updated successfully with name " + salesTaxName );
				}
				else
					Log4jHelper.logInfo( "Sales Tax Rate is not  available with name " + salesTaxName );

			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for new Sales Tax Rate creation.
	 */

	protected void newSalesTaxRate() throws Exception
	{
		SalesTaxRateImpl taxRateObj = new SalesTaxRateImpl( salesRateMap );
		taxRateObj.createNew();
		taxRateObj.basicDetails();
		genericHelperObj.detailSave( "PS_details_taxRate_save", salesTaxName, "Sales Tax" );

	}

	/*
	 * This method is for edit Sales Tax Rate creation.
	 */

	protected void editSalesTaxRate() throws Exception
	{
		SalesTaxRateImpl taxRateObj = new SalesTaxRateImpl( salesRateMap );
		taxRateObj.editbasicDetails();
		genericHelperObj.detailSave( "PS_details_taxRate_save", salesTaxName, "Sales Tax" );

	}

	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVariables( Map<String, String> Map ) throws Exception
	{
		salesTaxName = ExcelHolder.getKey( Map, "SalesTaxName" );

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Sales Tax Rate" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			salesRateMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( salesRateMap, "SearchScreenColumns" );
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
	public void salesTaxRateValidOnFilterVerify() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Sales Tax Rate" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				salesRateMap = excelHolderObj.dataMap( paramVal );
				assertEquals( NavigationHelper.getScreenTitle(), "Sales Tax Rate Search" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				initializeVarValidOn( salesRateMap );
				boolean isSalesTaxPresent = salesTaxRateFilter(salesTaxName, validOnFilter);
				assertTrue(isSalesTaxPresent, "Sales Tax Rate is not present for this Valid On Filter");
				dataverification.validateDataWithoutSorting("grid_column_header_searchGrid_", salesRateMap, colmnHeadersScreen, mapRowKeys, false);
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}
	public boolean salesTaxRateFilter(String salesTaxName, String validOnFilter) throws Exception
	{
		CalendarHelper.setOnDate("dummySalesTaxValidOn", validOnFilter);
		ButtonHelper.click("SearchButton");
		genericHelperObj.waitforHeaderElement("Rate");
		return GridHelper.isValuePresent( "SearchGrid", salesTaxName, "Sales Tax" );
	}
	/*
	 * This method is to initialize instance variable
	 */

	protected void initializeVarValidOn( Map<String, String> Map ) throws Exception
	{
		salesTaxName = ExcelHolder.getKey( Map, "SalesTaxName" );
		validOnFilter = ExcelHolder.getKey( Map, "FromDate" );
		colmnHeadersScreen = ExcelHolder.getKey( Map, "ColmnHeadersScreen" );
		mapRowKeys = ExcelHolder.getKey( Map, "MapRowKeys" );

	}

}