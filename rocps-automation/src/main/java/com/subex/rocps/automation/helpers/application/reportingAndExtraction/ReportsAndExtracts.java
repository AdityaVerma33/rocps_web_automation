package com.subex.rocps.automation.helpers.application.reportingAndExtraction;

import org.dbunit.operation.ExclusiveTransactionException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnDetails;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportAndExtFileHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportsAndExtAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportsAndExtDetails;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReportsAndExtracts extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> reportAndExtExcelMap = null;
	protected Map<String, String> reportAndExtMap = null;
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
	protected String repAndExtDefnName;
	protected String repAndExtFlPath;
	protected String fileExtensions;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ReportsAndExtracts( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		reportAndExtExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reportAndExtExcelMap );
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
	public ReportsAndExtracts( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		reportAndExtExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( reportAndExtExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**
	 *Default Constructor
	 */
	public ReportsAndExtracts()
	{
	
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtDefnName = ExcelHolder.getKey( map, "RepAndExtDefinitionName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for initialize Report extracts file variable
	 */
	private void initializeRepExtFlVariable( Map<String, String> map ) throws Exception
	{
		repAndExtDefnName = ExcelHolder.getKey( map, "RepAndExtDefinitionName" );
		repAndExtFlPath = ExcelHolder.getKey( map, "ReportAndExtFilePath" );
		fileExtensions = ExcelHolder.getKey( map, "FileExtensions" );

	}

	/*
	 * This method is for 'Reports and Extracts' screen common method
	 */
	private void reportsAndExtScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Reports and Extracts" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		reportAndExtMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "File" );
	}

	/*
	 * This method is for 'Reports and Extracts' screen column validation
	 */
	public void reportAndExtColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportsAndExtScreen();
				colmHdrs = ExcelHolder.getKey( reportAndExtMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String reportAndExtGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : reportAndExtGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Reports and Extracts' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Reports and Extracts' report creation
	 */
	public void reportAndExtCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportsAndExtScreen();
				initializeVariable( reportAndExtMap );
				String testcase = ExcelHolder.getKey( reportAndExtMap, "TaskEvaluation" );
				ReportsAndExtAction repAndExtName = new ReportsAndExtAction();
				repAndExtName.clickAdhocReqAction( clientPartition );
				ReportsAndExtDetails reportsAndExtDetails = new ReportsAndExtDetails( reportAndExtMap );
				reportsAndExtDetails.configureRepAndExtracts();
				verifyTaskStatus( testcase );
				reportsAndExtScreen();
				String reportExtTaskStatus = getRepAndExtractsStatus( "Report and Extract Definition", repAndExtDefnName );
				assertTrue( reportExtTaskStatus.contains( "Completed" ), "The task status of 'Reprot and Extracts' is found -" + reportExtTaskStatus );
				Log4jHelper.logInfo( "'Reports and Extracts' is successfully completed with name '" + repAndExtDefnName );

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for check Report Extraction Location file
	public void reportExtLocFileValidation() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			reportAndExtMap = excelHolderObj.dataMap( index );
			initializeRepExtFlVariable( reportAndExtMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ReportAndExtFileHelper reportAndExtFileHelper = new ReportAndExtFileHelper();
			reportAndExtFileHelper.readFile( repAndExtFlPath, fileExtensions, repAndExtDefnName,"yyyyMMdd" );
		}
	}
	// Method: for check Report Extraction Location file with file name contains
		public void reportExtFileValidationWithFileNm() throws Exception
		{

			for ( index = 0; index < colSize; index++ )
			{
				reportAndExtMap = excelHolderObj.dataMap( index );
				String fileNmContains=ExcelHolder.getKey( reportAndExtMap, "FileNameContains" );
				repAndExtFlPath = ExcelHolder.getKey( reportAndExtMap, "ReportAndExtFilePath" );
				fileExtensions = ExcelHolder.getKey( reportAndExtMap, "FileExtensions" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ReportAndExtFileHelper reportAndExtFileHelper = new ReportAndExtFileHelper();
				reportAndExtFileHelper.readFile( repAndExtFlPath, fileExtensions, fileNmContains,"ddMMyyyy" );
			}
		}

	// Method: verify the  task status in Task search
	private void verifyTaskStatus( String testCase ) throws Exception
	{
		PSTaskSearchHelper psTaskSearchHelper = new PSTaskSearchHelper();
		psTaskSearchHelper.psVerifyTaskStatus( path, workBookName, sheetName, testCase, 1 );

	}

	// Method: Get the status of 'Report And Extracts'
	public String getRepAndExtractsStatus( String filterHeaderName, String reportExtDfnNm ) throws Exception
	{
		repExtDefnAdvanceSearchFilter( "SearchGrid", filterHeaderName, reportExtDfnNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.sortColumnHeaderGrid( "PS_Detail_repAndExt_createdDt_IconID", "PS_Detail_repAndExt_createdDt_menuID" );
		GenericHelper.waitTime( 2, "" );
		GridHelper.clickRow( "SearchGrid", 1, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		return GridHelper.getCellValue( "SearchGrid", 1, "Task Status" );
	}

	/*
	 * This method is for 'Report And Extract Definition' search filter 
	 */
	private void repExtDefnAdvanceSearchFilter( String gridId, String filterHeaderName, String reportExtDfnNm ) throws Exception
	{
		SearchHelper searchHelper = new SearchHelper();
		String advanceSearchBtn = GenericHelper.getORProperty( "PS_suggestionFilterAdvanceTextXpath" ).replace( "filterTxtId", "rocpsInterfaceTbl" );
		String reportExtDfnSearchBtnLocator = GenericHelper.getORProperty( "PS_suggestionFilterSearchBtnXpath" ).replace( "filterTxtId", "rocpsInterfaceTbl" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		searchHelper.clickFilterIcon( gridId, filterHeaderName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.click( advanceSearchBtn );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		Thread.sleep( 1000 );
		psGenericHelper.waitforPopupHeaderElement( "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		SearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", reportExtDfnNm, "Name" );
		boolean isrepExtDefnNmPresent = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", reportExtDfnNm, "Name" );
		assertTrue( isrepExtDefnNmPresent, "Report And Extract Definition  with name :'" + reportExtDfnNm + "'  is not found in 'Report And Extracts Definition Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", reportExtDfnNm, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement( reportExtDfnSearchBtnLocator, searchScreenWaitSec );
		ButtonHelper.click( reportExtDfnSearchBtnLocator );
		GenericHelper.waitForLoadmask();

	}

}
