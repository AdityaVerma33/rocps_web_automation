package com.subex.rocps.automation.helpers.application.bills.hotbill;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.bills.billadjustments.BillAdjustmentsImpl;
import com.subex.rocps.automation.helpers.application.bills.billrequest.BillRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.bills.billrequest.BillRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillActionImpl extends PSAcceptanceTest
{

	protected String billProfile;
	protected String currency;
	protected String salesTax;
	protected String billPeriodFrom;
	protected String testBill;
	protected String regenerateBill;
	protected String reVersionBill;
	protected String billInformation;
	protected String billCurrencyBreakdown;
	protected String billSalesTaxBreakdown;
	protected String currencyBreakdownAmt;
	protected String salesTaxBreakdownAmt;
	protected String billStatus;
	protected String createBillRequest;
	protected String billProfileName;
	protected String testBillCheckBox;
	protected String account;
	BillRequestSearchImpl searchObj = new BillRequestSearchImpl();
	DataVerificationHelper dataverifyObj = new DataVerificationHelper();
	BillAdjustmentsImpl adjustmentsObj = new BillAdjustmentsImpl();
	String regerate = "One or more of the selected bill(s) do not have any changes. Do you still wish to regenerate?";
	String regenrateText1 = "Are you sure you wish to regenerate the selected bill(s)?";
	String taskmsg = "Bill Task created with task id";
	String createTestbill = "Are you sure you want to create test bill request?";
	String reversionText = "Are you sure you wish to reversion the selected bill(s)?";
	String reversion = "One or more of the selected bill(s) do not have any changes. Do you still wish to reversion?";
	String reversionfailure = "Bill cannot be reversioned as there are one or more credit note(s) are linked";
	String adjustmentsTask = "Adjustments Task(s) created with task id(s)";
	String adjustmentsrunning = "Adjustment task is running for one or more selected bill(s)";
	String adjustmentsnoChange = "One or more selected bill(s) do not have any changes. Do you still wish to create adjustments?";

	/*
	 * This method is to create new Test Bill
	 */
	public void newBillRequest( String clientPartition, String billProfileName, String billPeriod, String testBillCheckBox ) throws Exception
	{
		BillRequestDetailImpl testobj = new BillRequestDetailImpl();
		testobj.createNewBillRequest( clientPartition );
		testobj.billRequestDetails( billProfileName, billPeriod, testBillCheckBox );
		testobj.saveBillRequest();
	}

	/*
	 * This method is to perform create TestBill Action
	 */
	public void createTestBill( String accName, String billProfileName ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		NavigationHelper.navigateToAction( "Actions", "Create Test Bill" );
		GenericHelper.waitForLoadmask();
		assertTrue( confirmPopup( createTestbill ) );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "YesButton" );
		GenericHelper.waitForLoadmask();
		assertTrue( taskCreationPopUp( taskmsg ) );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill Action, Create Test Bill is completed successfully for bill profile" + billProfileName );
	}

	/*
	 * This method is to perform regenerate Bill Action
	 */
	public void regenerateBill( String accName, String billprofileName ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		GenericHelper.waitForLoadmask();
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Actions" );
		NavigationHelper.navigateToAction( "Actions", "Regenerate Bill" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( confirmPopup( regenrateText1, regerate ), "Bill cannot be regenrated" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask();
		assertTrue( taskCreationPopUp( taskmsg ) );
		ButtonHelper.clickIfEnabled( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill Action, Regenrate Bill is completed successfully for bill profile" + billprofileName );
	}
	/*
	 * This method is to perform new Bill Version action
	 */

	public void newBillVersion( String accName, String billprofileName ) throws Exception
	{

		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Actions" );
		NavigationHelper.navigateToAction( "Actions", "New Bill Version" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertFalse( confirmPopup( reversionfailure ), "Bill cannot be reversioned" );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask();
		assertTrue( taskCreationPopUp( taskmsg ) );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill Action,new Bill version is completed successfully for bill profile" + billprofileName );
	}

	/*
	 * This method is for Change Bill status Action
	 */

	public void changeBillStatus( String accName, String billProfileName, String changeBillStatus ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Actions" );
		NavigationHelper.navigateToAction( "Actions", "Change Bill Status" );
		GenericHelper.waitForLoadmask();
		if ( changeBillStatus.contentEquals( "Regenerate" ) )
			RadioHelper.click( "PS_Detail_BillAction_Regenrate_radioBtn" );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill Action, change Bill Status is completed successfully for bil Profile" + billProfileName );
	}

	/*
	 * This method is for Bill Breakdown Action
	 */

	public void billBreakdownAction( Map<String, String> billmap, String breakdownColHeaders, String breakdownResults, String accName, String breakdownAction ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Breakdowns" );
		NavigationHelper.navigateToAction( "Breakdowns", breakdownAction );
		GenericHelper.waitForLoadmask();
		String title = NavigationHelper.getScreenTitle();
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask();
		if ( title.contains( breakdownAction ) )
			usageBreakdownAction( billmap, breakdownColHeaders, breakdownResults );
		else if ( title.contains( "Credit" ) )
			creditsBreakdownAction( billmap, breakdownColHeaders, breakdownResults );
		else if ( title.contains( "Non-usage" ) )
			nonUsageBreakdownAction( billmap, breakdownColHeaders, breakdownResults );

	}

	public void usageBreakdownAction( Map<String, String> billmap, String breakdownColHeaders, String breakdownResults ) throws Exception
	{
		GridHelper.sortGrid( "PS_Detail_BillAction_usageBreakdown_gridID", "Transaction Amount" );
		GenericHelper.waitForLoadmask();
		if ( ValidationHelper.isNotEmpty( breakdownResults ) ) {
			dataverifyObj.validateDataWithoutSorting( "PS_Detail_BillAction_usageBreakdown_gridID", "grid_column_header_drillDownGrid_", billmap, breakdownColHeaders, breakdownResults, false );
		}
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "CloseButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Usage Bill Breakdown is Validated successfully" );
	}

	public void nonUsageBreakdownAction( Map<String, String> billmap, String breakdownColHeaders, String breakdownResults ) throws Exception
	{
		GridHelper.sortGrid( "SearchGrid", "Billed Amount" );
		GenericHelper.waitForLoadmask();
		if ( ValidationHelper.isNotEmpty( breakdownResults ) )
			dataverifyObj.validateData( "popupWindow", "grid_column_header_searchGrid_", billmap, "SearchGrid", breakdownColHeaders, breakdownResults );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "CancelButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Non Usage Bill Breakdown is Validated successfully" );
	}

	public void creditsBreakdownAction( Map<String, String> billmap, String breakdownColHeaders, String breakdownResults ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "Credit Notes Search" );
		GridHelper.sortGrid( "Detail_eventDefn_gridID", "Total Amount" );
		GenericHelper.waitForLoadmask();
		if ( ValidationHelper.isNotEmpty( breakdownResults ) )
			dataverifyObj.validateData( "popupWindow", "grid_column_header_searchGrid_", billmap, "Detail_eventDefn_gridID", breakdownColHeaders, breakdownResults );
		GenericHelper.waitForLoadmask();
		ButtonHelper.click( "CancelButton" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Credits Bill Breakdown is Validated successfully" );
	}

	/*
	 * This method is for Bill Adjustments Action
	 */
	public void billAdjustments( String breakdownName, Map<String, String> dataMap, String columnHeaderNames, String rowNumsCol, String accName ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		NavigationHelper.navigateToAction( "Adjustments", "Compute Adjustments" );
		GenericHelper.waitForLoadmask();
		String popuptitle = NavigationHelper.getScreenTitle();
		if ( popuptitle.contains( "Confirm" ) )
		{
			assertTrue( confirmPopup( adjustmentsnoChange ) );
			ButtonHelper.click( "OK_TRT_Button" );
			GenericHelper.waitForLoadmask();
		}
		assertTrue( taskCreationPopUp( adjustmentsTask ) );
		ButtonHelper.click( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask();
		Log4jHelper.logInfo( "Bill Task is created successfully for Bil Adjustments Task" );
		NavigationHelper.navigateToAction( "Adjustments", "View Adjustments" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Bill Adjustments Search" );
		adjustmentsObj.billDataAdjustment( breakdownName, dataMap, columnHeaderNames, rowNumsCol );
		Log4jHelper.logInfo( "Bill Adjustments are validated successfully for Bil profile" + billProfileName );
		NavigationHelper.navigateToScreen( "Bill" );
	}

	/*
	 * This method is to perform bill currency Breakdown Action
	 */

	public void billCurrencyBreakdown( String currencyColHeaders, String currencyResults, String accName ) throws Exception
	{	
		GenericHelper.waitForLoadmask();
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		GenericHelper.waitForLoadmask();
		NavigationHelper.navigateToAction( "Information", "Bill Currency Breakdown" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "View Bill Currency Breakdown" );
		if ( ValidationHelper.isNotEmpty( currencyResults ) ) {
			dataverifyObj.validateData( "grid_column_header_undefined_", currencyColHeaders, "currencyBreakdownGrid", currencyResults );
		}
		ButtonHelper.click( "PS_Detail_BillAction_billCurrency_closeBtn" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Bill Search" );
		Log4jHelper.logInfo( "Bill Action, Bill Currency Breakdown is Validated successfully" );
	}

	/*
	 * This method is to perform Bill SalesTax Breakdown Action
	 */
	public void billSalesTaxBreakdown( String salesTaxColHeaders, String salesTaxResults, String accName ) throws Exception
	{
		GridHelper.clickRow( "Detail_eventDefn_gridID", accName, "Account" );
		NavigationHelper.navigateToAction( "Information", "Bill Sales Tax Breakdown" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "View Bill Sales Tax Breakdown" );
		if ( ValidationHelper.isNotEmpty( salesTaxResults ) )
			dataverifyObj.validateData( "grid_column_header_undefined_", salesTaxColHeaders, "salesTaxBreakdownGrid", salesTaxResults );
		ButtonHelper.click( "PS_Detail_BillAction_billSalesTax_closeBtn" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Bill Search" );
		Log4jHelper.logInfo( "Bill Action, Bill Sales Tax Breakdown is validated successfully" );
	}

	/*
	 * This method is for task creation Popup message check
	 */
	private boolean taskCreationPopUp( String taskmsg ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Information" );
		return PopupHelper.isTextPresent( "PS_popup_billAction_InformationID", taskmsg );
	}

	/*
	 * This method is for Confirmation Popup message check
	 */
	private boolean confirmPopup( String popupmsg ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "Confirm" );
		return PopupHelper.isTextPresent( "PS_popup_billAction_InformationID", popupmsg );
	}

	/*
	 * This method is for Confirmation Popup message check
	 */
	private boolean confirmPopup( String popupmsg1, String popupmsg2 ) throws Exception
	{
		assertEquals( NavigationHelper.getScreenTitle(), "Confirm" );
		boolean popupmsg = PopupHelper.isTextPresent( "PS_popup_billAction_InformationID", popupmsg1 ) || PopupHelper.isTextPresent( "PS_popup_billAction_InformationID", popupmsg2 );
		return popupmsg;
	}
}
