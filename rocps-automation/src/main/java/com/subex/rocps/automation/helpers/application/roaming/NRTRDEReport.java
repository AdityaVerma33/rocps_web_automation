package com.subex.rocps.automation.helpers.application.roaming;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.eventErrors.eventError.EventErrorSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeFlStatus.NRTRDERecordsScreen;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport.NRTRDEErrorReport;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport.NRTRDEFileDeliveryReport;
import com.subex.rocps.automation.helpers.application.roaming.nrtrdeReport.NRTRDEReportActionImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class NRTRDEReport extends PSAcceptanceTest
{

	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> nrtrdeReportExcelMap = null;
	protected Map<String, String> nrtrdeReportMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String direction;
	protected String fileNm;
	protected String mapkeys;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public NRTRDEReport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		nrtrdeReportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( nrtrdeReportExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor :  Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public NRTRDEReport( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		nrtrdeReportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( nrtrdeReportExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		direction = ExcelHolder.getKey( map, "Direction" );
		fileNm = ExcelHolder.getKey( map, "FileName" );
	}

	/*
	 * This method is for 'NRTRDE Report' screen common method
	 */
	private void nrtrdeReportScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "NRTRDE Report" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		nrtrdeReportMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Direction" );
	}

	/*
	 * This method is for 'NRTRDE Report' screen column validation
	 */
	public void nrtrdeReportColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeReportScreen();
				colmHdrs = ExcelHolder.getKey( nrtrdeReportMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Direction", colmHdrs, "NRTRDE Report" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: Validate the search results of 'NRTRDE Report' screen
	public void validateNrtrdeReportSearchResult() throws Exception
	{

		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeReportScreen();
				ButtonHelper.click( "SearchButton" );
				psGenericHelper.waitforHeaderElement( "Direction" );
				mapkeys = ExcelHolder.getKey( nrtrdeReportMap, "MapRowKeys" );
				colmHdrs = ExcelHolder.getKey( nrtrdeReportMap, "ColmnHeaders" );
				psGenericHelper.validateSearchResult( colmHdrs, mapkeys, nrtrdeReportMap, "PS_Detail_SearchScreen_ColumnHeaderID", "SearchGrid" );
				Log4jHelper.logInfo( "'NRTRDE Report' results are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'NRTRDE Report'  view report action
	 */
	public void nrtrdeReportViewReportAction() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				nrtrdeReportScreen();
				initializeVariable( nrtrdeReportMap );
				boolean isNRTRDEReportPresent = isNRTRDEReportPresent();
				if ( isNRTRDEReportPresent )
				{
					String testCase = ExcelHolder.getKey( nrtrdeReportMap, "ViewReportTestCase" );
					GridHelper.clickRow( "SearchGrid", fileNm, "File Name" );
					String reportType = GridHelper.getCellValue( "searchGrid", 1, "Type" );
					NRTRDEReportActionImpl nrtrdeReportActionImpl = new NRTRDEReportActionImpl();
					nrtrdeReportActionImpl.clickOnViewReportAction();
					validateViewReportActResult( reportType, testCase );
				}
				else

					Log4jHelper.logInfo( "'NRTRDE Report'' is not avilable with file name:" + fileNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'NRTRDE Report'  view report action search result
	 */
	protected void validateViewReportActResult( String reportType, String testcase ) throws Exception
	{
		if ( reportType.contentEquals( "Delivery Report" ) )
		{
			NRTRDEFileDeliveryReport nrtrdeFileDeliveryReport = new NRTRDEFileDeliveryReport( path, workBookName, sheetName, testcase );
			nrtrdeFileDeliveryReport.validateNrtrdeFlDelReportSearchResult( fileNm );
		}
		if ( reportType.contentEquals( "Error Report" ) )
		{
			NRTRDEErrorReport nrtrdeErrorReport = new NRTRDEErrorReport( path, workBookName, sheetName, testcase );
			nrtrdeErrorReport.validateNrtrdeErrReportSearchResult( fileNm );
		}

	}

	/*
	 * This method is for 'NRTRDE Report' present or not
	 */
	private boolean isNRTRDEReportPresent() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( direction ) )
			PSSearchGridHelper.gridFilterSearchWithComboBox( "PS_Detail_NrtrdeReport_direc_comboId", direction, "Direction" );
		return psGenericHelper.isGridTextValuePresent( "PS_Detail_NrtrdeReport_fileNm_txtId", fileNm, "File Name" );
	}

}
