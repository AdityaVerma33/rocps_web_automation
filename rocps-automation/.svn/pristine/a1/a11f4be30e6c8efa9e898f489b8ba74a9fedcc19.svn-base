package com.subex.rocps.automation.helpers.application.reportingAndExtraction;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.AdvanceSearchFiltersHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping.RepColumnMappingActImpl;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repColMapping.RepColumnMappingDetailImpl;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repModelling.ReportModellingActionImpl;
import com.subex.rocps.automation.helpers.application.reportingAndExtraction.repModelling.ReportModellingDetailImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ReportModelling extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> reportModellingExcelMap = null;
	protected Map<String, String> reportModellingMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String repModellingNm;
	protected String prefix;
	protected String summaryType;
	protected String summaryName;
	protected String clientPartition;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	AdvanceSearchFiltersHelper advanceSearchFiltersHelper = new AdvanceSearchFiltersHelper();
	ReportModellingActionImpl reportModellingActionImpl = new ReportModellingActionImpl();

	/**
	 * Constructor : Initializing the excel without occurrence
	 * 
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception
	 */
	public ReportModelling( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		reportModellingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( reportModellingExcelMap );
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
	public ReportModelling( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		reportModellingExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( reportModellingExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		repModellingNm = ExcelHolder.getKey( map, "ReportModellingName" );
		prefix = ExcelHolder.getKey( map, "Prefix" );
		summaryType = ExcelHolder.getKey( map, "SummaryType" );
		summaryName = ExcelHolder.getKey( map, "SummaryName" );

	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariableName( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		repModellingNm = ExcelHolder.getKey( map, "ReportModellingName" );
	}

	/*
	 * This method is for 'Report Modelling' screen common method
	 */
	private void reportModellingScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Report Modelling" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		reportModellingMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Summary" );
	}

	/*
	 * This method is for 'Report Modelling' screen column validation
	 */
	public void reportModellingColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportModellingScreen();
				colmHdrs = ExcelHolder.getKey( reportModellingMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "Summary", colmHdrs, "Report Modelling" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Report Modelling' report creation
	 */
	public void reportModellingCreation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportModellingScreen();
				initializeVariable( reportModellingMap );
				boolean repModellingNmPresent = reportModellingFilterSearch( repModellingNm, prefix, summaryType, summaryName );
				if ( !repModellingNmPresent )
				{

					reportModellingActionImpl.clickNewAction( clientPartition );
					ReportModellingDetailImpl reportModellingDetailImpl = new ReportModellingDetailImpl( reportModellingMap );
					reportModellingDetailImpl.configReportModelling();
					Log4jHelper.logInfo( "'Report Modelling' is successfully created with 'Report Modelling Name '" + repModellingNm + " and prefix " + prefix );

				}
				else

					Log4jHelper.logInfo( "'Report Modelling ' is already avilable with 'Report Modelling Name'" + repModellingNm + " or prefix " + prefix );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * Method to change the Report Modelling status to Accepted
	 */
	public void reportModellingAccepted() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			reportModellingScreen();
			repModellingNm = ExcelHolder.getKey( reportModellingMap, "ReportModellingName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean changeStatus = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			GenericHelper.waitForLoadmask();
			if ( changeStatus )
			{
				GridHelper.clickRow( "SearchGrid", repModellingNm, "Name" );
				String value = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if ( value.contains( "Draft" ) )
				{
					reportModellingActionImpl.clickOnChangeStatus( "Change Status", "Accept Report Modelling" );
					PopupHelper.waitForPopup( searchScreenWaitSec );
					assertTrue( PopupHelper.isTextPresent( "The Report modelling cannot be edited after accepted. Are you sure you wish to change the Report modelling status to accepted?" ), "Popup message is not matched" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psGenericHelper.waitforHeaderElement( "Summary" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( GridHelper.isValuePresent( "SearchGrid", "Accepted", "Status" ), " The status is not changed to Accepted" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				else
					Log4jHelper.logInfo( "Report Modelling status is already changed to accepted" + repModellingNm );
			}
			else
				Log4jHelper.logInfo( "Report Modelling is not available with name " + repModellingNm );
		}
	}

	/*
	 * Method to change the Report Modelling status to Discontinue
	 */
	public void reportModellingDicountinue() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			reportModellingScreen();
			repModellingNm = ExcelHolder.getKey( reportModellingMap, "ReportModellingName" );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean changeStatus = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			GenericHelper.waitForLoadmask();
			if ( changeStatus )
			{
				GridHelper.clickRow( "SearchGrid", repModellingNm, "Name" );
				String value = GridHelper.getCellValue( "SearchGrid", 1, "Status" );
				if ( value.contains( "Accepted" ) )
				{
					reportModellingActionImpl.clickOnChangeStatus( "Change Status", "Discontinue Report Rated Modelling" );
					PopupHelper.waitForPopup( searchScreenWaitSec );
					assertTrue( PopupHelper.isTextPresent( "This change cannot be reverted back. Are you sure you wish to change the Report modelling status to discontinued?" ), "Popup message is not matched" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "YesButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					ButtonHelper.click( "SearchButton" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					psGenericHelper.waitforHeaderElement( "Summary" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					PSSearchGridHelper.gridFilterSearchWithTextBox( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					assertTrue( GridHelper.isValuePresent( "SearchGrid", "Discontinued", "Status" ), " The status is not changed to Discontinued" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
				}
				else
					Log4jHelper.logInfo( "Report Modelling status is already changed to Discontinued" + repModellingNm );
			}
			else
				Log4jHelper.logInfo( "Report Modelling is not available with name " + repModellingNm );
		}
	}

	/*
	 * This method is for 'Report Modelling' report edit
	 */
	public void reportModellingEdit() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				reportModellingScreen();
				initializeVariable( reportModellingMap );
				boolean repModellingNmPresent = reportModellingFilterSearch( repModellingNm, prefix, summaryType, summaryName );
				if ( repModellingNmPresent )
				{
					GridHelper.clickRow( "SearchGrid", repModellingNm, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
					reportModellingActionImpl.clickOnAction( "Common Tasks", "Edit" );
					ReportModellingDetailImpl reportModellingDetailImpl = new ReportModellingDetailImpl( reportModellingMap );
					reportModellingDetailImpl.modifyReportModelling();
					Log4jHelper.logInfo( "'Report Modelling' is successfully updated with 'Report Modelling Name '" + repModellingNm );

				}
				else

					Log4jHelper.logInfo( "'Report Modelling ' is not avilable with 'Report Modelling Name'" + repModellingNm );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	// Method: for Report Modelling deletion action
	public void reportModellingDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			reportModellingScreen();
			initializeVariableName( reportModellingMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			boolean isRepModellingNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			assertTrue( isRepModellingNmPresent, "Report Modelling is not available in the screen with  name: -'" + repModellingNm );
			psGenericHelper.clickDeleteOrUnDeleteAction( repModellingNm, "Name", "Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			isRepModellingNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			assertTrue( isRepModellingNmPresent, repModellingNm + " is not present" );
			Log4jHelper.logInfo( "Report Modelling is deleted successfully with the value-:'" + repModellingNm );
		}

	}

	// Method: for Report Modelling Undeletion action
	public void reportModellingUnDelete() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			reportModellingScreen();
			initializeVariableName( reportModellingMap );
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
			boolean isRepModellingNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			assertTrue( isRepModellingNmPresent, "Report Modelling is not available in the screen with  name: -'" + repModellingNm );
			psGenericHelper.clickDeleteOrUnDeleteAction( repModellingNm, "Name", "Undelete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
			isRepModellingNmPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );
			assertTrue( isRepModellingNmPresent, repModellingNm + " is not present" );
			Log4jHelper.logInfo( "Report Modelling is undeleted successfully with the  value:  '" + repModellingNm );
		}

	}

	// Method: for Report Modelling filter search
	protected boolean reportModellingFilterSearch( String repModellingNm, String prefix, String summaryType, String summaryName ) throws Exception
	{

		boolean flag = false;

		flag = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_name_txtId", repModellingNm, "Name" );

		if ( flag == false )
		{
			ButtonHelper.click( "ClearButton" );
			GenericHelper.waitForLoadmask();
			flag = psGenericHelper.isGridTextValuePresent( "PS_Detail_repModelling_prefix_txtId", prefix, "Prefix" );
			GenericHelper.waitForLoadmask();
		}
		if ( summaryType.contentEquals( "Aggregation" ) )
			advanceSearchFiltersHelper.aggregationConfigAdvanceSearch( "Aggregation Configuration", summaryName );
		else if ( summaryType.contentEquals( "Bilateral Modelling" ) )
			advanceSearchFiltersHelper.brdModelingAdvanceSearch( "Brd Modelling", summaryName );
		else
			FailureHelper.failTest( "Summary type should be 'Aggregation' or 'Bilateral Modelling' but found-: " + summaryType );
		return flag;
	}
}
