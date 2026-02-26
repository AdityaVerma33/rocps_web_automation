package com.subex.rocps.automation.helpers.application.reportingAndExtraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule.RepAndExtScheduleAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repAndExtSchedule.RepAndExtSchedulerDetails;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnDetails;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt.ReportAndExtFileHelper;
import com.subex.rocps.automation.helpers.application.system.PSTaskSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReportAndExtScheduler extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> reportAndExtSchedExcelMap = null;
	protected Map<String, String> reportAndExtSchedMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected static String bookName;
	protected static String pathWB;
	protected String sheetName;
	protected static String sheetNm;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String repAndSchedulerNm;
	protected String repAndExtDefnName;
	protected String repAndExtFlPath;
	protected String fileExtensions;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	ReportsAndExtracts reportsAndExtObj = new ReportsAndExtracts();

	/**
	 * Default Constructor
	 */
	public ReportAndExtScheduler()
	{

	}

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ReportAndExtScheduler( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		reportAndExtSchedExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reportAndExtSchedExcelMap );
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
	public ReportAndExtScheduler( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		reportAndExtSchedExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( reportAndExtSchedExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'Report and Extract Scheduler' screen common method
	 */
	private void repAndExtSchedulerScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Report and Extract Scheduler" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		reportAndExtSchedMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndSchedulerNm = ExcelHolder.getKey( map, "RepAndSchName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for initialize report extraction file variable
	 */
	private void initializeRepExtFlVariable( Map<String, String> map ) throws Exception
	{
		repAndExtDefnName = ExcelHolder.getKey( map, "RepAndExtDefinitionName" );
		repAndExtFlPath = ExcelHolder.getKey( map, "ReportAndExtFilePath" );
		fileExtensions = ExcelHolder.getKey( map, "FileExtensions" );

	}

	/*
	 * This method is for 'Report and Extract Scheduler' screen column validation
	 */
	public void repAndExtScheduleColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				repAndExtSchedulerScreen();
				colmHdrs = ExcelHolder.getKey( reportAndExtSchedMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String reportAndExtSchGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : reportAndExtSchGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Report and Extract Scheduler' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report and Extract Scheduler'  creation
	 */
	public void reportAndExtScheduleCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				repAndExtSchedulerScreen();
				initializeVariable( reportAndExtSchedMap );
				initializeWorkbookNmSheetNm();
				boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtSch_name_txtId", repAndSchedulerNm, "Name" );
				if ( !isReportPresent )
				{
					RepAndExtScheduleAction repAndExtScheduleAction = new RepAndExtScheduleAction();
					repAndExtScheduleAction.clickNewAction( clientPartition );
					RepAndExtSchedulerDetails repAndExtSchedulerDetails = new RepAndExtSchedulerDetails( reportAndExtSchedMap );
					repAndExtSchedulerDetails.createRepAndExtSchedule();
					Log4jHelper.logInfo( "'Report and Extract Scheduler' is successfully created with name '" + repAndSchedulerNm );
				}
				else
					Log4jHelper.logInfo( "'Report and Extract Scheduler' is already avilable with name" + repAndSchedulerNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*
	 * This method is for 'Report and Extract Scheduler'  edit
	 */
	public void reportAndExtScheduleEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				repAndExtSchedulerScreen();
				initializeWorkbookNmSheetNm();
				initializeVariable( reportAndExtSchedMap );
				boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtSch_name_txtId", repAndSchedulerNm, "Name" );
				if ( isReportPresent )
				{

					int row = GridHelper.getRowNumber( "SearchGrid", repAndSchedulerNm, "Name" );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					RepAndExtScheduleAction repAndExtScheduleAction = new RepAndExtScheduleAction();
					repAndExtScheduleAction.clickOnAction( "Common Tasks", "Edit" );
					RepAndExtSchedulerDetails repAndExtSchedulerDetails = new RepAndExtSchedulerDetails( reportAndExtSchedMap );
					repAndExtSchedulerDetails.modifyRepAndExtSchedule();
					Log4jHelper.logInfo( "'Report and Extract Scheduler' is successfully updated with name '" + repAndSchedulerNm );

				}
				else

					Log4jHelper.logInfo( "'Report and Extract Scheduler' is not avilable with name" + repAndSchedulerNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report and Extract Scheduler'  schedule now
	 */
	public void scheduleRepAndExtSchudler() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				repAndExtSchedulerScreen();
				initializeVariable( reportAndExtSchedMap );
				initializeWorkbookNmSheetNm();
				boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtSch_name_txtId", repAndSchedulerNm, "Name" );
				if ( !isReportPresent )
				{
					String testcase = ExcelHolder.getKey( reportAndExtSchedMap, "TaskEvaluation" );
					RepAndExtScheduleAction repAndExtScheduleAction = new RepAndExtScheduleAction();
					repAndExtScheduleAction.clickNewAction( clientPartition );
					RepAndExtSchedulerDetails repAndExtSchedulerDetails = new RepAndExtSchedulerDetails( reportAndExtSchedMap );
					repAndExtSchedulerDetails.createRepAndExtSchedule();
					GenericHelper.waitTime( 15, "waiting to task update" );
					verifyTaskStatus( testcase );
					verifyRepAndExtractsTaskStatus();
					Log4jHelper.logInfo( "'Report and Extract Scheduler' is successfully scheduled with name '" + repAndSchedulerNm );

				}
				else

					Log4jHelper.logInfo( "'Report and Extract Scheduler' is already avilable with name" + repAndSchedulerNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for check Report Extract Location file
	public void reportExtLocFileValidation() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			reportAndExtSchedMap = excelHolderObj.dataMap( index );
			initializeRepExtFlVariable( reportAndExtSchedMap );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ReportAndExtFileHelper reportAndExtFileHelper = new ReportAndExtFileHelper();
			reportAndExtFileHelper.readFile( repAndExtFlPath, fileExtensions, repAndExtDefnName, "yyyyMMdd" );
		}
	}

	// Method: for check Report Extraction Location file with file name contains
	public void reportExtFileValidationWithFileNm() throws Exception
	{

		for ( index = 0; index < colSize; index++ )
		{
			reportAndExtSchedMap = excelHolderObj.dataMap( index );
			String fileNmContains = ExcelHolder.getKey( reportAndExtSchedMap, "FileNameContains" );
			repAndExtFlPath = ExcelHolder.getKey( reportAndExtSchedMap, "ReportAndExtFilePath" );
			fileExtensions = ExcelHolder.getKey( reportAndExtSchedMap, "FileExtensions" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ReportAndExtFileHelper reportAndExtFileHelper = new ReportAndExtFileHelper();
			reportAndExtFileHelper.readFile( repAndExtFlPath, fileExtensions, fileNmContains, "ddMMyyyy" );
		}
	}

	// Method: verify the  task status in Task search
	private void verifyTaskStatus( String testCase ) throws Exception
	{
		PSTaskSearchHelper psTaskSearchHelper = new PSTaskSearchHelper();
		psTaskSearchHelper.psVerifyTaskStatus( path, workBookName, sheetName, testCase, 1 );

	}

	// Method: verify the  task status in Task search
	private void verifyRepAndExtractsTaskStatus() throws Exception
	{
		String configRepExtInstTestCase = ExcelHolder.getKey( reportAndExtSchedMap, "ConfigReportAndExtInstTestCase" );
		List<Map<String, String>> listOfmap = psDataComponentHelper.getListOfTestCaseMap( path, workBookName, sheetName, configRepExtInstTestCase );
		for ( int i = 0; i < listOfmap.size(); i++ )
		{
			Map<String, String> map = listOfmap.get( i );
			String repextDefnNm = ExcelHolder.getKey( map, "Instance_RepAndExtDefn" );
			NavigationHelper.navigateToScreen( "Reports and Extracts" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			ButtonHelper.click( "ClearButton" );
			psGenericHelper.waitforHeaderElement( "File" );
			String reportExtTaskStatus = reportsAndExtObj.getRepAndExtractsStatus( "Report and Extract Definition", repextDefnNm );
			assertTrue( reportExtTaskStatus.contains( "Completed" ), "The task status of 'Reprot and Extracts' is found -" + reportExtTaskStatus );
			Log4jHelper.logInfo( "The task status of this Report and Extract Definition -'" + repextDefnNm + "' is in Completed status" );
			map = null;
		}
	}

	/*
	 * This method is for initialize workbook, sheet, path name to use in different class
	 */
	private void initializeWorkbookNmSheetNm()
	{
		bookName = workBookName;
		sheetNm = sheetName;
		pathWB = path;
	}

	/*
	 * This method is for get path
	 */
	public String getPath()
	{
		return pathWB;
	}

	/*
	 * This method is for get workbook name
	 */
	public String getWorkbookName()
	{
		return bookName;
	}

	/*
	 * This method is for sheet name
	 */
	public String getSheetName()
	{
		return sheetNm;
	}

}
