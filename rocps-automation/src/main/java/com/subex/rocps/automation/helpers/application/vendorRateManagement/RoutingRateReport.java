package com.subex.rocps.automation.helpers.application.vendorRateManagement;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.vendorRateManagement.routingRateReport.RoutingRateReportElementDetails;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class RoutingRateReport extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> routingRateReportExcelMap = null;
	protected Map<String, String> routingRateReportMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String colmHdrs;
	protected int colSize;
	protected int index;
	protected String operator;
	protected String operatorTariff;
	protected String validOn;
	protected String clientPartition;
	protected String directMatchColmnHdrs;
	protected String directMatchMapKeys;
	protected String opportunityCostColmnHdrs;
	protected String opportunityCostMapKeys;
	protected String reverseOpportCostColmnHdrs;
	protected String reverseOpportCostMapKeys;
	protected String penaltyCostColmnHdrs;
	protected String penaltyCostMapKeys;
	protected String committedDealColmnHdrs;
	protected String commitedDealMapKeys;
	protected String nonCommittedDealColmnHdrs;
	protected String nonCommitedDealMapKeys;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
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
	public RoutingRateReport( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		routingRateReportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( routingRateReportExcelMap );
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
	public RoutingRateReport( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		routingRateReportExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( routingRateReportExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		clientPartition = ExcelHolder.getKey( map, "Partition" );
		operator = ExcelHolder.getKey( map, "Operator" );
		operatorTariff = ExcelHolder.getKey( map, "OperatorTariff" );
		validOn = ExcelHolder.getKey( map, "ValidOn" );
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVarValidateSrchResult( Map<String, String> map ) throws Exception
	{
		directMatchColmnHdrs = ExcelHolder.getKey( map, "DirectMatchColmnHdrs" );
		directMatchMapKeys = ExcelHolder.getKey( map, "DirectMatchMapKeys" );
		opportunityCostColmnHdrs = ExcelHolder.getKey( map, "OpportunityCostColmnHdrs" );
		opportunityCostMapKeys = ExcelHolder.getKey( map, "OpportunityCostMapKeys" );
		reverseOpportCostColmnHdrs = ExcelHolder.getKey( map, "ReverseOpportunityCostColmnHdrs" );
		reverseOpportCostMapKeys = ExcelHolder.getKey( map, "ReverseOpportunityCostMapKeys" );
		penaltyCostColmnHdrs = ExcelHolder.getKey( map, "PenaltyCostColmnHdrs" );
		penaltyCostMapKeys = ExcelHolder.getKey( map, "PenaltyCostMapKeys" );
		committedDealColmnHdrs = ExcelHolder.getKey( map, "CommittedDealColmnHdrs" );
		commitedDealMapKeys = ExcelHolder.getKey( map, "CommittedDealMapKeys" );
		nonCommittedDealColmnHdrs = ExcelHolder.getKey( map, "NonCommittedDealColmnHdrs" );
		nonCommitedDealMapKeys = ExcelHolder.getKey( map, "NonCommittedDealMapKeys" );

	}

	/*
	 * This method is for 'Routing Rate Report' screen common method
	 */
	private void routingRateReportScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Routing Rate Report" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		routingRateReportMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "System Band" );
	}

	/*
	 * This method is for 'Routing Rate Report' screen column validation
	 */
	public void routingRateReportColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				routingRateReportScreen();
				colmHdrs = ExcelHolder.getKey( routingRateReportMap, "SearchScreenColumns" );
				psGenericHelper.screenColumnValidation( "System Band", colmHdrs, "Routing Rate Report" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Routing Rate Report' Routing rate calculation
	 */
	public void routingRateReportCalculation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				routingRateReportScreen();
				initializeVariable( routingRateReportMap );
				reCalculate();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Routing Rate Report' Routing rate search result
	 */
	public void routingRateReportSearchResult() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				routingRateReportScreen();
				initializeVariable( routingRateReportMap );
				routingRateSearchFilter();
				verifyRoutingReportSearchResult();
				viewElementDetailsAction();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}

	}

	/*
	 * This method is for 'Routing Rate Report' Routing rate search result
	 */
	private void verifyRoutingReportSearchResult() throws Exception
	{
		initializeVarValidateSrchResult( routingRateReportMap );
		String columnhdrId = GenericHelper.getORProperty( "PS_Detail_routingRateReport_colmnHdrId" );
		if ( ValidationHelper.isNotEmpty( directMatchMapKeys ) )
			verificationSearchResult( columnhdrId, directMatchColmnHdrs, directMatchMapKeys, "Direct Match" );

		if ( ValidationHelper.isNotEmpty( opportunityCostMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_opportunityCost_BtnId", columnhdrId, opportunityCostColmnHdrs, opportunityCostMapKeys, "Opportunity Cost" );

		if ( ValidationHelper.isNotEmpty( reverseOpportCostMapKeys ) )
			verificationSearchResult( columnhdrId, reverseOpportCostColmnHdrs, reverseOpportCostMapKeys, "Reverse Opportunity Cost" );

		if ( ValidationHelper.isNotEmpty( penaltyCostMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_penaltyCost_BtnId", columnhdrId, penaltyCostColmnHdrs, penaltyCostMapKeys, "Penalty Cost" );

		if ( ValidationHelper.isNotEmpty( commitedDealMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_committedDeal_BtnId", columnhdrId, committedDealColmnHdrs, commitedDealMapKeys, "Committed Deal" );

		if ( ValidationHelper.isNotEmpty( nonCommitedDealMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_nonCommittedDeal_BtnId", columnhdrId, nonCommittedDealColmnHdrs, nonCommitedDealMapKeys, "Non Committed Deal" );

	}

	/*
	 * This overloaded method is for 'Routing Rate Report' Routing rate search result without task button id
	 */
	private void verificationSearchResult( String columnHdrId, String columnHdrs, String mapKeys, String taskResultName ) throws Exception
	{
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "System Band" );
		dataVerificationHelper.validateDataWithoutSorting( "SearchGrid", columnHdrId, routingRateReportMap, columnHdrs, mapKeys, false );
		Log4jHelper.logInfo( "'" + taskResultName + "' validate search result completed successfully for 'Routing Rate Report\n" );

	}

	/*
	 * This overloaded method is for 'Routing Rate Report' Routing rate search result with task button id
	 */
	private void verificationSearchResult( String taskBtnId, String columnHdrId, String columnHdrs, String mapKeys, String taskResultName ) throws Exception
	{
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "System Band" );
		ButtonHelper.click( taskBtnId );
		psGenericHelper.waitforHeaderElement( "System Band" );
		dataVerificationHelper.validateDataWithoutSorting( "SearchGrid", columnHdrId, routingRateReportMap, columnHdrs, mapKeys, false );
		Log4jHelper.logInfo( "'" + taskResultName + "' validate search result completed successfully for 'Routing Rate Report\n" );

	}

	/*
	 * This overloaded method is for 'Routing Rate Report'view element details action
	 */
	private void viewElementDetailsAction() throws Exception
	{
		String viewElementDetailsTC = routingRateReportMap.get( "ViewElementTestCase" );
		String viewElementDetailsClickedRow = routingRateReportMap.get( "ViewElementRowMapKeys" );
		String viewElemDetailsTCArr[] = null;
		String viewElemDetRowArr[] = null;
		if ( ValidationHelper.isNotEmpty( viewElementDetailsTC ) )
		{
			viewElemDetailsTCArr = psStringUtils.stringSplitFirstLevel( viewElementDetailsTC );
			viewElemDetRowArr = psStringUtils.stringSplitFirstLevel( viewElementDetailsClickedRow );
			for ( int i = 0; i < viewElemDetailsTCArr.length; i++ )
			{
				String rowValue=routingRateReportMap.get( viewElemDetRowArr[i] );
				ButtonHelper.click( "SearchButton" );
				psGenericHelper.waitforHeaderElement( "System Band" );
				int rowNum = psDataComponentHelper.getRowNumOfGrid( "SearchGrid", rowValue);
				assertTrue( rowNum>0, rowValue+" row value not found" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "SearchGrid", rowNum, "System Band" );
				psActionImpl.clickOnAction( "Routing Rate Actions", "View Element Details", "PS_Detail_routingRateReport_waitForElementDetaisPagelXpath" );
				RoutingRateReportElementDetails roRateReportElementDetOb = new RoutingRateReportElementDetails( path, workBookName, sheetName, viewElemDetailsTCArr[i], routingRateReportMap );
				roRateReportElementDetOb.validateElementDetailsSearchResult();
			}
		}

	}

	/*
	 * This overloaded method is for 'Routing Rate Report' earch filter
	 */
	private void routingRateSearchFilter() throws Exception
	{

		String currDt = DateHelper.getCurrentDateTime( "MM/dd/yyyy" );
		if ( ValidationHelper.isEmpty( validOn ) )
			validOn = currDt;
		ComboBoxHelper.select( "PS_Detail_routingRateReport_operator_comboId", operator );
		ComboBoxHelper.select( "PS_Detail_routingRateReport_opratorTariff_combId", operatorTariff );
		TextBoxHelper.type( "PS_Detail_routingRateReport_validOn_CalandarTxtId", validOn );
	}

	/*
	 * This overloaded method is for 'Routing Rate Report' calculate
	 */
	private void reCalculate() throws Exception
	{
		routingRateSearchFilter();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "PS_Detail_routingRateReport_reCalculate_BtnId" );
		if ( PopupHelper.isTextPresent( "Routing rate calculation will take several minutes. Do you want to continue?" ) )
			ButtonHelper.click( "YesButton" );
		if ( PopupHelper.isTextPresent( "Task" ) )
			ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}

}
