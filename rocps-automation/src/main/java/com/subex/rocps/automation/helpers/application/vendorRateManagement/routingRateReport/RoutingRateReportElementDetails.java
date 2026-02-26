package com.subex.rocps.automation.helpers.application.vendorRateManagement.routingRateReport;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class RoutingRateReportElementDetails extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> elementDetailsExcelMap = null;
	protected Map<String, String> elementDetailsMap = null;
	protected Map<String, String> routingRateReportMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int colSize;
	protected int index;
	protected String opportunityCostColmnHdrs;
	protected String opportunityCostMapKeys;
	protected String penaltyCostColmnHdrs;
	protected String penaltyCostMapKeys;
	protected String committedDealColmnHdrs;
	protected String commitedDealMapKeys;
	protected String nonCommittedDealColmnHdrs;
	protected String nonCommitedDealMapKeys;
	protected String noOpBreakoutColmnHdrs;
	protected String noOpBreakoutMapKeys;
	protected String noSysBreakoutColmnHdrs;
	protected String noSysBreakoutMapKeys;
	protected String noOriginOpBreakoutColmnHdrs;
	protected String noOriginOpBreakoutMapKeys;
	protected String noOriginSysBreakoutColmnHdrs;
	protected String noOriginSysBreakoutMapKeys;
	protected String penaltRemovedColmnHdrs;
	protected String penaltyRemovedMapKeys;
	protected String penaltyRemovedRow;
	protected String penaltyAddedRow;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	PSActionImpl psActionImpl = new PSActionImpl();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	DataVerificationHelper dataVerificationHelper = new DataVerificationHelper();

	/**Constructor
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public RoutingRateReportElementDetails( String path, String workBookName, String sheetName, String testCaseName, Map<String, String> routingRateReportMap ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.routingRateReportMap = routingRateReportMap;
		elementDetailsExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( elementDetailsExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVarValidateSrchResult( Map<String, String> map ) throws Exception
	{
		opportunityCostColmnHdrs = ExcelHolder.getKey( map, "OpportunityCostColmnHdrs" );
		opportunityCostMapKeys = ExcelHolder.getKey( map, "OpportunityCostMapKeys" );
		penaltyCostColmnHdrs = ExcelHolder.getKey( map, "PenaltyCostColmnHdrs" );
		penaltyCostMapKeys = ExcelHolder.getKey( map, "PenaltyCostMapKeys" );
		committedDealColmnHdrs = ExcelHolder.getKey( map, "CommittedDealColmnHdrs" );
		commitedDealMapKeys = ExcelHolder.getKey( map, "CommittedDealMapKeys" );
		nonCommittedDealColmnHdrs = ExcelHolder.getKey( map, "NonCommittedDealColmnHdrs" );
		nonCommitedDealMapKeys = ExcelHolder.getKey( map, "NonCommittedDealMapKeys" );
		noOpBreakoutColmnHdrs = ExcelHolder.getKey( map, "NoOperatorBreakoutColmnHdrs" );
		noOpBreakoutMapKeys = ExcelHolder.getKey( map, "NoOperatorBreakoutMapKeys" );
		noSysBreakoutColmnHdrs = ExcelHolder.getKey( map, "NoSystemBreakoutColmnHdrs" );
		noSysBreakoutMapKeys = ExcelHolder.getKey( map, "NoSystemBreakoutMapKeys" );
		noOriginOpBreakoutColmnHdrs = ExcelHolder.getKey( map, "NoOriginOperatorBreakoutColmnHdrs" );
		noOriginOpBreakoutMapKeys = ExcelHolder.getKey( map, "NoOriginOperatorBreakoutMapKeys" );
		noOriginSysBreakoutColmnHdrs = ExcelHolder.getKey( map, "NoOriginSystemBreakoutColmnHdrs" );
		noOriginSysBreakoutMapKeys = ExcelHolder.getKey( map, "NoOriginSystemBreakoutColmnHdrs" );
		penaltRemovedColmnHdrs = ExcelHolder.getKey( map, "PenaltyRemovedColmnHdrs" );
		penaltyRemovedMapKeys = ExcelHolder.getKey( map, "PenaltyRemovedMapKeys" );
		penaltyRemovedRow = ExcelHolder.getKey( map, "PenaltyRemovedRow" );
		penaltyAddedRow = ExcelHolder.getKey( map, "PenaltyAddedRow" );

	}

	public void validateElementDetailsSearchResult() throws Exception
	{
		for ( index = 0; index < colSize; index++ )
		{
			elementDetailsMap = excelHolderObj.dataMap( index );
			validateTopPanelValues();
			verificationOfSearchResult();
			routingRateAction();
		}
		navigateToRoutingRateReport();
	}

	private void routingRateAction() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( penaltyRemovedRow ) )
			penaltyRemovedAction( penaltyRemovedRow );
		if ( ValidationHelper.isNotEmpty( penaltyAddedRow ) )
			penaltyAddedAction( penaltyAddedRow );
	}

	private void penaltyRemovedAction( String penaltyRemovedRow ) throws Exception
	{

		int clickRowNum = psDataComponentHelper.getRowNumOfGrid( "SearchGrid", penaltyRemovedRow );
		assertTrue( clickRowNum > 0, penaltyRemovedRow + " row value not found on Elements details" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", clickRowNum, "System Band" );
		psActionImpl.clickOnAction( "Routing Rate Actions", "Remove Penalty" );
	}

	private void penaltyAddedAction( String penaltyAddedRow ) throws Exception
	{

		int clickRowNum = psDataComponentHelper.getRowNumOfGrid( "SearchGrid", penaltyAddedRow );
		assertTrue( clickRowNum > 0, penaltyAddedRow + " row value not found on Elements details" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "SearchGrid", clickRowNum, "System Band" );
		psActionImpl.clickOnAction( "Routing Rate Actions", "Add Penalty" );
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "System Element" );
		ButtonHelper.click( "PS_Detail_routingRateReport_penaltyRemoved_BtnId" );
		assertTrue( GridHelper.getRowCount( "SearchGrid" ) == 0, "Penalty is not added successfully" );
		Log4jHelper.logInfo( "Penalty is added successfully" );
	}

	private void verificationOfSearchResult() throws Exception
	{
		initializeVarValidateSrchResult( elementDetailsMap );
		String columnhdrId = GenericHelper.getORProperty( "PS_Detail_routingRateReport_colmnHdrId" );
		if ( ValidationHelper.isNotEmpty( opportunityCostMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_opportunityCost_BtnId", columnhdrId, opportunityCostColmnHdrs, opportunityCostMapKeys, "Opportunity Cost " );
		if ( ValidationHelper.isNotEmpty( penaltyCostMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_penaltyCost_BtnId", columnhdrId, penaltyCostColmnHdrs, penaltyCostMapKeys, "Penalty Cost " );
		if ( ValidationHelper.isNotEmpty( commitedDealMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_committedDeal_BtnId", columnhdrId, committedDealColmnHdrs, commitedDealMapKeys, "Committed Deal " );
		if ( ValidationHelper.isNotEmpty( nonCommitedDealMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_nonCommittedDeal_BtnId", columnhdrId, nonCommittedDealColmnHdrs, nonCommitedDealMapKeys, "Non Committed Deal " );
		if ( ValidationHelper.isNotEmpty( noOpBreakoutMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_noOpBreakout_BtnId", columnhdrId, noOpBreakoutColmnHdrs, noOpBreakoutMapKeys, "No Operator Breakout" );
		if ( ValidationHelper.isNotEmpty( noSysBreakoutMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_noSysBreakout_BtnId", columnhdrId, noSysBreakoutColmnHdrs, noSysBreakoutMapKeys, "No System Breakout" );
		if ( ValidationHelper.isNotEmpty( noOriginOpBreakoutMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_noOrigOpBreakout_BtnId", columnhdrId, noOriginOpBreakoutColmnHdrs, noOriginOpBreakoutMapKeys, "No Origin Operator Breakout" );
		if ( ValidationHelper.isNotEmpty( noOriginSysBreakoutMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_noOrigSysBreakout_BtnId", columnhdrId, noOriginSysBreakoutColmnHdrs, noOriginSysBreakoutMapKeys, "No  Origin System Breakout" );
		if ( ValidationHelper.isNotEmpty( penaltyRemovedMapKeys ) )
			verificationSearchResult( "PS_Detail_routingRateReport_penaltyRemoved_BtnId", columnhdrId, penaltRemovedColmnHdrs, penaltyRemovedMapKeys, "Penalty Removed " );

	}

	private void navigateToRoutingRateReport() throws Exception
	{
		driver.navigate().back();
		ElementHelper.waitForElementToDisappear( GenericHelper.getORProperty( "PS_Detail_routingRateReport_waitForElementDetaisPagelXpath" ), searchScreenWaitSec );
	}

	/*
	 * This overloaded method is for 'Routing Rate Report 's Element Details' Routing rate search result without task button id
	 */
	private void verificationSearchResult( String columnHdrId, String columnHdrs, String mapKeys, String taskResultName ) throws Exception
	{
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "System Element" );
		dataVerificationHelper.validateDataWithoutSorting( "SearchGrid", columnHdrId, elementDetailsMap, columnHdrs, mapKeys, false );
		Log4jHelper.logInfo( "'" + taskResultName + "' validate search result completed successfully for 'Routing Rate Report's Element Details\n" );

	}

	/*
	 * This overloaded method is for 'Routing Rate Report's Element Details' Routing rate search result with task button id
	 */
	private void verificationSearchResult( String taskBtnId, String columnHdrId, String columnHdrs, String mapKeys, String taskResultName ) throws Exception
	{
		ButtonHelper.click( "SearchButton" );
		psGenericHelper.waitforHeaderElement( "System Element" );
		ButtonHelper.click( taskBtnId );
		psGenericHelper.waitforHeaderElement( "System Element" );
		dataVerificationHelper.validateDataWithoutSorting( "SearchGrid", columnHdrId, elementDetailsMap, columnHdrs, mapKeys, false );
		Log4jHelper.logInfo( "'" + taskResultName + "' validate search result completed successfully for 'Routing Rate Report's Element Details\n" );

	}

	private void validateTopPanelValues() throws Exception
	{
		psGenericHelper.waitforHeaderElement( "System Element" );
		String currDt = DateHelper.getCurrentDateTime( "MM/dd/yyyy" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_routingRateReport_operator_comboId" ), routingRateReportMap.get( "Operator" ), " operator value is not matched in \"Elements Details\" screen" );
		assertEquals( ComboBoxHelper.getValue( "PS_Detail_routingRateReport_opratorTariff_combId" ), routingRateReportMap.get( "OperatorTariff" ), " operator tariff value is not matched in \"Elements Details\" screen" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_routingRateReport_validOn_CalandarTxtId" ), currDt, " valid on value is not matched in \"Elements Details\" screen" );
	}
}
