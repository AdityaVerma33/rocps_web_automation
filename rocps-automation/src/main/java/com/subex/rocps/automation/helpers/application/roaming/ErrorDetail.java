package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ErrorDetail extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> errorDetailExcelMap = null;
	protected Map<String, String> errorDetailMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected String colmHdrsDataVerification;
	public String mapKeysDataVerification;
	protected int colSize;
	protected int index;
	protected String linkedTapFileName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	AdvanceSearchFiltersHelper advanceSearchFiltersHelper = new AdvanceSearchFiltersHelper();
	DataVerificationHelper dataVerificationHelper = new DataVerificationHelper();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public ErrorDetail( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		errorDetailExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( errorDetailExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 * Constructor : Initializing the excel with occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception
	 */
	public ErrorDetail( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		errorDetailExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( errorDetailExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		linkedTapFileName = ExcelHolder.getKey( map, "LinkedTapFileName" );
		colmHdrsDataVerification = ExcelHolder.getKey( map, "ColumnHdrsDataVerification" );
		mapKeysDataVerification = ExcelHolder.getKey( map, "MapKeysDataVerification" );

	}

	/*
	 * This method is for 'Error Detail' screen common method
	 */
	private void errorDetailScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Error Detail" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		errorDetailMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Error Code" );
	}

	/*
	 * This method is for 'Error Detail' screen column validation
	 */
	public void errorDetailColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				errorDetailScreen();
				colmHdrs = ExcelHolder.getKey( errorDetailMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Error Code", colmHdrs, "Error Detail" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Error Detail'  validate Search Result
	 */
	public void errorDetailSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				errorDetailScreen();
				initializeVariable( errorDetailMap );
				String rapOutFileName = rapOutFileAdvanceSearchFilter( linkedTapFileName );
				ButtonHelper.click( "SearchButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				psGenericHelper.waitforHeaderElement( "Error Code" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				dataVerificationHelper.validateDataWithoutSorting( GenericHelper.getORProperty( "PS_Detail_SearchScreen_ColumnHeaderID" ), errorDetailMap, colmHdrsDataVerification, mapKeysDataVerification, false );
				Log4jHelper.logInfo( "Error Detail  search Result validated successfully with Linked Tap File name: '" + linkedTapFileName + "' for Rap Out File Name:- " + rapOutFileName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for rap out file advance search result
	 */
	private String rapOutFileAdvanceSearchFilter( String linkedTapFileName ) throws Exception
	{
		return advanceSearchFiltersHelper.rapOutFileAdvanceSearch( "File Name", linkedTapFileName );
	}

}
