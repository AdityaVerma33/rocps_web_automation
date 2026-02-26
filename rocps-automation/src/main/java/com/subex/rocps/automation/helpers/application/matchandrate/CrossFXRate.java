package com.subex.rocps.automation.helpers.application.matchandrate;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class CrossFXRate extends PSAcceptanceTest
{

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> crossFXRatesExcel = null;
	protected Map<String, String> crossFXRatesMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String crossFXRateGroup;
	protected String sourceCurrency;
	protected String targetCurrency;
	protected String validFrom;
	protected String dateSel;
	protected String filter;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public CrossFXRate( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		crossFXRatesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName );
		excelHolderObj = new ExcelHolder( crossFXRatesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public CrossFXRate( String path, String workBookName, String sheetName, String testCaseName, int occurance ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		crossFXRatesExcel = excelData.readDataByColumn( this.path, this.workBookName, this.sheetName, this.testCaseName, occurance );
		excelHolderObj = new ExcelHolder( crossFXRatesExcel );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Cross FX Rates
	 * 
	 * @method : isCrossFXRatesPresent returns false then Cross FX Rates is
	 * configured isCrossFXRatesPresent returns true then Cross FX Rates is not
	 * configured
	 */
	public void crossFXRatesCreation() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Cross FX Rates" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				crossFXRatesMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( crossFXRatesMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( !isCrossFXRatesPresent() )
				{

					newCrossFXRates();

					Log4jHelper.logInfo( "Cross FX Rate is created successfully with name " + crossFXRateGroup );
				}
				else
				{
					Log4jHelper.logInfo( "Cross FX Rate is available with name " + crossFXRateGroup );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	//Method: is for edit thethe cross fx rate
	public void crossFXRatesEdit() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Cross FX Rates" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{

				crossFXRatesMap = excelHolderObj.dataMap( paramVal );

				initializeVariables( crossFXRatesMap );
				ButtonHelper.click( "ClearButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( isCrossFXRatesPresent() )
				{

					int row = GridHelper.getRowNumber( "SearchGrid", crossFXRateGroup, "Cross Fx Rate Group" );
					NavigationHelper.navigateToEdit( "SearchGrid", row );
					editCrossFXRates();

					Log4jHelper.logInfo( "Cross FX Rate is updated successfully with name " + crossFXRateGroup );
				}
				else
				{
					Log4jHelper.logInfo( "Cross FX Rate is not available with name " + crossFXRateGroup );
				}
			}
		}
		catch ( Exception e )
		{

			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	// Method: Validate the search results of 'Cross Fx Rates' screen
	public void validateCrossFxRateSearchResult() throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Cross FX Rates" );
			for ( paramVal = 0; paramVal < colSize; paramVal++ )
			{
				crossFXRatesMap = excelHolderObj.dataMap( paramVal );
				initializeVariables( crossFXRatesMap );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( isCrossFXRatesPresent(), "Cross fx rate Group is not found :" + crossFXRateGroup );
				ButtonHelper.click( "SearchButton" );
				genericHelperObj.waitforHeaderElement( "Rate" );
				String mapkeys = ExcelHolder.getKey( crossFXRatesMap, "MapRowKeys" );
				String colmHdrs = ExcelHolder.getKey( crossFXRatesMap, "ColmnHeaders" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				genericHelperObj.sortColumnHeaderGrid( "PS_Detail_CrossFxRate_sourceCurr_IconID", "PS_Detail_CrossFxRate_sourceCurrSort_menuID" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				genericHelperObj.validateSearchResult( colmHdrs, mapkeys, crossFXRatesMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
				Log4jHelper.logInfo( "'Cross Fx Rates' results are validated successfully for " + crossFXRateGroup );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	protected boolean isCrossFXRatesPresent() throws Exception
	{

		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_crossFXRate_rateGroup_comboID", filter, "Cross Fx Rate Group" );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_crossFXRate_sourcecurrency_comboID", sourceCurrency, "Source Currency" );
		SearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_crossFXRate_targetCurrency_comboID", targetCurrency, "Target Currency" );

		return GridHelper.isValuePresent( "SearchGrid", crossFXRateGroup, "Cross Fx Rate Group" );
	}

	protected void newCrossFXRates() throws Exception
	{
		CrossFXRatesImpl crossFxObj = new CrossFXRatesImpl( crossFXRatesMap );
		crossFxObj.createNewCrossFXRates();
		crossFxObj.newCrossFXRate();
		crossFxObj.saveCrossFXRates();
	}

	protected void editCrossFXRates() throws Exception
	{
		CrossFXRatesImpl crossFxObj = new CrossFXRatesImpl( crossFXRatesMap );
		crossFxObj.editCrossFXRate();
		crossFxObj.saveCrossFXRates();
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{
		NavigationHelper.navigateToScreen( "Cross FX Rates" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			crossFXRatesMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( crossFXRatesMap, "SearchScreenColumns" );
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
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables( Map<String, String> map ) throws Exception
	{

		clientPartition = ExcelHolder.getKey( map, "Partition" );
		//dateSel = ExcelHolder.getKey(map, "DateSel");
		crossFXRateGroup = ExcelHolder.getKey( map, "CrossFXRateGroup" );
		sourceCurrency = ExcelHolder.getKey( map, "SourceCurrency" );
		targetCurrency = ExcelHolder.getKey( map, "TargetCurrency" );
		validFrom = ExcelHolder.getKey( map, "ValidFrom" );
		filter = ExcelHolder.getKey( map, "Filter" );

	}

}
