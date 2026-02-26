package com.subex.rocps.automation.helpers.application.bills.testbill;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.bills.hotbill.BillActionImpl;
import com.subex.rocps.automation.helpers.application.bills.billrequest.BillRequestDetailImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestBillActionImpl extends PSAcceptanceTest {

	protected String billProfile;
	protected String billProfileName;
	protected String billPeriod;
	protected String testBillCheckBox;
	protected String numberOfBills;
	protected String clientPartition;
	protected String account;
	protected String currency;
	protected String salesTax;
	protected String billCurrencyBreakdown;
	protected String billSalesTaxBreakdown;
	protected String currencyBreakdownAmt;
	protected String salesTaxBreakdownAmt;
	protected String createTestbill;
	protected String billPeriodFrom;
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillActionImpl billDetailObj = null;
	public Map<String, String> testBillMap = null;
	BillRequestDetailImpl testobj = new BillRequestDetailImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * This method is to create new Test Bill
	 */
	public void newBillRequest(String clientPartition, String billProfileName, String billPeriod,
			String testBillCheckBox) throws Exception {

		testobj.createNewBillRequest(clientPartition);
		testobj.billRequestDetails(billProfileName, billPeriod, testBillCheckBox);
		testobj.saveBillRequest();
	}

	/*
	 * This method is to perform bill currency Breakdown Action
	 */
	public void billCurrencyBreakdown(String currencyColHeaders, String currencyResults, String accName)
			throws Exception {
		GridHelper.clickRow("searchGrid", accName, "Account");
		NavigationHelper.navigateToAction("Test Bill Information", "Currency Breakdowns");
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "View Test Bill Currency Breakdown");
		dataVerifyObj.validateData("grid_column_header_undefined_", currencyColHeaders, "currencyBreakdownGrid",
				currencyResults);
		ButtonHelper.click("testBillCurrencyBreakdownsDetail.Close");
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "Test Bill Search");
		Log4jHelper.logInfo("Bill Action, bill Currency Breakdown is validated successfully");
	}

	/*
	 * This method is to perform Bill SalesTax Breakdown Action
	 */
	public void billSalesTaxBreakdown(String salesTaxColHeaders, String salesTaxResults, String accName)
			throws Exception {
		GridHelper.clickRow("searchGrid", accName, "Account");
		NavigationHelper.navigateToAction("Test Bill Information", "Sales Tax Breakdowns");
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "View Test Bill Sales Tax Breakdown");
		dataVerifyObj.validateData("grid_column_header_undefined_", salesTaxColHeaders, "salesTaxBreakdownGrid",
				salesTaxResults);
		ButtonHelper.click("testBillSalesTaxBreakdownsDetail.Close");
		assertEquals(NavigationHelper.getScreenTitle(), "Test Bill Search");
		Log4jHelper.logInfo("Bill Action, bill Sales Tax Breakdown is validated successfully");
	}
	/*
	 * This method is for Bill Breakdown Action
	 */

	public void testBillBreakdownAction(Map<String, String> billmap, String breakdownColHeaders,
			String breakdownResults, String accName, String breakdownAction) throws Exception {
		GridHelper.clickRow("searchGrid", accName, "Account");
		NavigationHelper.navigateToAction("Test Bill Breakdowns", breakdownAction);
		GenericHelper.waitForLoadmask();
		String title = NavigationHelper.getScreenTitle();
		ButtonHelper.click("SearchButton");
		GenericHelper.waitForLoadmask();
		if (title.contains(breakdownAction))
			usageBreakdownAction(billmap, breakdownColHeaders, breakdownResults);
		else if (title.contains("Credit"))
			creditsBreakdownAction(billmap, breakdownColHeaders, breakdownResults);
		Log4jHelper.logInfo("pass");
	}

	public void usageBreakdownAction(Map<String, String> billmap, String breakdownColHeaders, String breakdownResults)
			throws Exception {
		GridHelper.sortGrid("drillDownGrid", "Transaction Amount");
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(breakdownResults))
			dataVerifyObj.validateData("grid_column_header_drillDownGrid_", billmap, "drillDownGrid",
					breakdownColHeaders, breakdownResults);
		GenericHelper.waitForLoadmask();
		ButtonHelper.click("CloseButton");
		GenericHelper.waitForLoadmask();
	}

	public void creditsBreakdownAction(Map<String, String> billmap, String breakdownColHeaders, String breakdownResults)
			throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "Credit Notes Search");
		GridHelper.sortGrid("searchGrid", "Total Amount");
		if (ValidationHelper.isNotEmpty(breakdownResults))
			dataVerifyObj.validateData("popupWindow", "grid_column_header_searchGrid_", billmap, "searchGrid",
					breakdownColHeaders, breakdownResults);
		GenericHelper.waitForLoadmask();
		ButtonHelper.click("CancelButton");
	}
}
