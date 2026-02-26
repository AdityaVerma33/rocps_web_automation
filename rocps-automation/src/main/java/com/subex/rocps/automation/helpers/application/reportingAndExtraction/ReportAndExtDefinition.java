package com.subex.rocps.automation.helpers.application.reportingAndExtraction;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnAction;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportandExtDefn.ReportAndExtDefnDetails;
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

public class ReportAndExtDefinition extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> reportAndExtDefnExcelMap = null;
	protected Map<String, String> reportAndExtDefnMap = null;
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
	protected String repAndExtName;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**
	 * Default Constructor
	 */
	public ReportAndExtDefinition()
	{

	}

	/**Constructor : Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public ReportAndExtDefinition( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		reportAndExtDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reportAndExtDefnExcelMap );
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
	public ReportAndExtDefinition( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		reportAndExtDefnExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( reportAndExtDefnExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for 'Report and Extract Definition' screen common method
	 */
	private void reportAndExtDefnScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Report and Extract Definition" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		reportAndExtDefnMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		repAndExtName = ExcelHolder.getKey( map, "ReportExtName" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );
	}

	/*
	 * This method is for 'Report and Extract Definition' screen column validation
	 */
	public void reportAndExtDefnColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportAndExtDefnScreen();
				colmHdrs = ExcelHolder.getKey( reportAndExtDefnMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String reportAndExtDefnGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : reportAndExtDefnGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Report and Extract Definition' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report and Extract Definition' report creation
	 */
	public void reportAndExtReportCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportAndExtDefnScreen();
				initializeVariable( reportAndExtDefnMap );
				initializeWorkbookNmSheetNm();
				boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
				if ( !isReportPresent )
				{
					ReportAndExtDefnAction reExtDefnAction = new ReportAndExtDefnAction();
					reExtDefnAction.clickNewAction( clientPartition );
					ReportAndExtDefnDetails reportAndExtDefnDetails = new ReportAndExtDefnDetails( reportAndExtDefnMap );
					reportAndExtDefnDetails.configureReportAndExtDefn();
					Log4jHelper.logInfo( "'Report and Extract Definition' is successfully created with name '" + repAndExtName );

				}
				else

					Log4jHelper.logInfo( "'Report and Extract Definition' is already avilable with name" + repAndExtName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report and Extract Definition' report edit
	 */
	public void reportAndExtReportEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportAndExtDefnScreen();
				initializeVariable( reportAndExtDefnMap );
				initializeWorkbookNmSheetNm();
				boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
				if ( isReportPresent )
				{
					ReportAndExtDefnAction reExtDefnAction = new ReportAndExtDefnAction();
					int row = GridHelper.getRowNumber( "SearchGrid", repAndExtName, "Name" );
					GridHelper.clickRow( "SearchGrid", row, "Name" );
					reExtDefnAction.clickOnAction( "Common Tasks", "Edit" );
					ReportAndExtDefnDetails reportAndExtDefnDetails = new ReportAndExtDefnDetails( reportAndExtDefnMap );
					reportAndExtDefnDetails.modifyReportAndExtDefn();
					Log4jHelper.logInfo( "'Report and Extract Definition' is successfully modify with name '" + repAndExtName );

				}
				else

					Log4jHelper.logInfo( "'Report and Extract Definition' is not avilable with name" + repAndExtName );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for 'Report and Extract Definition' deletion action
	public void reportAndExtDefnDelete() throws Exception
	{
		reportAndExtDefnScreen();
		initializeVariable( reportAndExtDefnMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
		assertTrue( isReportPresent, "Report and Extract Definition' is not available in the screen with  name: -'" + repAndExtName );
		psGenericHelper.clickDeleteOrUnDeleteAction( repAndExtName, "Name", "Delete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
		assertTrue( isReportPresent, repAndExtName + " is not present" );
		Log4jHelper.logInfo( "'Report and Extract Definition' is deleted successfully with the value-:'" + repAndExtName );

	}

	// Method: for  Report and Extract Definition Undeletion action
	public void reportAndExtDefnUnDelete() throws Exception
	{

		reportAndExtDefnScreen();
		initializeVariable( reportAndExtDefnMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		boolean isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
		assertTrue( isReportPresent, "'Report and Extract Definition' is not available in the screen with  name: -'" + repAndExtName );
		psGenericHelper.clickDeleteOrUnDeleteAction( repAndExtName, "Name", "Undelete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
		isReportPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_reportAndExtDefn_RepExtDefname_textID", repAndExtName, "Name" );
		assertTrue( isReportPresent, repAndExtName + " is not present" );
		Log4jHelper.logInfo( "'Report and Extract Definition' is undeleted successfully with the value:  '" + repAndExtName );

	}

	/*
	 * This method is for  initialize workbook, sheet, path name to use in different class
	 */
	private void initializeWorkbookNmSheetNm()
	{
		bookName = workBookName;
		sheetNm = sheetName;
		pathWB = path;
	}

	/*
	 * This method is for  get the path
	 */
	public String getPath()
	{
		return pathWB;
	}

	/*
	 * This method is for  get workbook name
	 */
	public String getWorkbookName()
	{
		return bookName;
	}

	/*
	 * This method is for get sheetname
	 */
	public String getSheetName()
	{
		return sheetNm;
	}

}
