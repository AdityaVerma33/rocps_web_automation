package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billrequest.BillRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.bills.hotbill.BillActionImpl;
import com.subex.rocps.automation.helpers.application.bills.hotbill.BillSearchImpl;
import com.subex.rocps.automation.helpers.application.bills.testbill.TestBillActionImpl;
import com.subex.rocps.automation.helpers.application.bills.testbill.TestBillSearchImpl;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class Bills extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billExcel = null;
	protected Map<String, String> billMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billProfile;
	protected String billPeriodFrom;
	protected String createTestBill;
	protected String regenerateBill;
	protected String reVersionBill;
	protected String billInformation;
	protected String billStatus;
	protected String createBillRequest;
	protected String billProfileName;
	protected String testBillCheckBox;
	protected String clientPartition;
	protected String account;
	protected String billPeriod;
	protected String productionPath;
	protected String billPeriodTo;
	protected String createdBy;
	protected String changeBillStatus;
	protected String columnHeaders;
	protected String results;
	protected String currencyColHeaders;
	protected String currencyResults;
	protected String salesTaxColHeaders;
	protected String salesTaxResults;
	protected String breakdownColHeaders;
	protected String breakdownKeys;
	protected String creditBreakdownActionName;
	protected String creditBreakdownKeys;
	protected String creditBreakdownColHeaders;
	protected String usageBreakdownActionName;
	protected String usageBreakdownKeys;
	protected String usageBreakdownColHeaders;
	protected String nonUsageBreakdownActionName;
	protected String nonUsageBreakdownKeys;
	protected String nonUsageBreakdownColHeaders;
	protected String timeStamp;
	protected String breakdownName;
	protected String adjustmentColHeaders;
	protected String adjustmentKeys;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillActionImpl billActionObj = new BillActionImpl();
	BillSearchImpl billSearchObj = new BillSearchImpl();
	BillRequestSearchImpl billreqObj = new BillRequestSearchImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	TestBillSearchImpl testbilSearchObj = new TestBillSearchImpl();
	TestBillActionImpl testbillObj = new TestBillActionImpl();
	GridFilterSearchHelper filterObj = new GridFilterSearchHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	public Bills(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public Bills(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billExcel);
		colSize = excelHolderObj.totalColumns();
	}

	public void billAction() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bill");
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billMap = excelHolderObj.dataMap(paramVal);
				assertEquals(NavigationHelper.getScreenTitle(), "Bill Search");
				billGeneration();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void billGeneration() throws Exception {

		initializeVariables(billMap);

		if (ValidationHelper.isTrue(testBillCheckBox)) {
			timeStamp = billreqObj.getSystemTimeStamp();
			billActionObj.newBillRequest(clientPartition, billProfileName, billPeriod, testBillCheckBox);
			testBill();
		} else if (ValidationHelper.isFalse(testBillCheckBox)) {
			boolean isBillPresent = billSearchObj.filterSelection(billProfileName, account, billPeriodFrom);
			if (!isBillPresent) {
				timeStamp = billreqObj.getSystemTimeStamp();
				billActionObj.newBillRequest(clientPartition, billProfileName, billPeriod, testBillCheckBox);
				hotBill();
			} else
				hotBillVerification();
		} else if (ValidationHelper.isEmpty(testBillCheckBox) && ValidationHelper.isEmpty(regenerateBill)
				&& ValidationHelper.isEmpty(createTestBill) && ValidationHelper.isEmpty(reVersionBill)
				&& ValidationHelper.isEmpty(changeBillStatus)) {
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
			hotBillVerification();
		}

		if (ValidationHelper.isTrue(regenerateBill)) {
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
			assertEquals(billSearchObj.billStatusCheck(), billStatus);
			timeStamp = billreqObj.getSystemTimeStamp();
			billActionObj.regenerateBill(account, billProfileName);
			hotBill();
		}

		if (ValidationHelper.isTrue(createTestBill)) {
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
			billActionObj.createTestBill(account, billProfileName);
			testBill();
		}

		if (ValidationHelper.isNotEmpty(changeBillStatus)) {
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
			assertEquals(billSearchObj.billStatusCheck(), billStatus);
			billActionObj.changeBillStatus(account, billProfileName, changeBillStatus);
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
		}

		if (ValidationHelper.isTrue(reVersionBill)) {
			assertTrue(billSearchObj.filterSelection(billProfileName, account, billPeriodFrom));
			billSearchObj.latestVersionCheck();
			assertEquals(billSearchObj.billStatusCheck(), billStatus);
			timeStamp = billreqObj.getSystemTimeStamp();
			billActionObj.newBillVersion(account, billProfileName);
			hotBill();
			if (ValidationHelper.isNotEmpty(adjustmentKeys))
				billActionObj.billAdjustments(breakdownName, billMap, adjustmentColHeaders, adjustmentKeys, account);
		}
	}

	/*
	 * This method is for testbill Validation
	 */
	private void testBill() throws Exception {
		billreqObj.billRequestStatus(productionPath, createdBy, billProfileName, account, testBillCheckBox, timeStamp);
		NavigationHelper.navigateToScreen("Test Bill");
		assertEquals(NavigationHelper.getScreenTitle(), "Test Bill Search");
		testbilSearchObj.filterOperations(account, billProfileName, billPeriodFrom);

		if (ValidationHelper.isNotEmpty(results))
			dataVerifyObj.validateData("grid_column_header_searchGrid_", columnHeaders, "searchGrid", results);
		if (ValidationHelper.isNotEmpty(currencyColHeaders))
			testbillObj.billCurrencyBreakdown(currencyColHeaders, currencyResults, account);
		if (ValidationHelper.isNotEmpty(salesTaxColHeaders))
			testbillObj.billSalesTaxBreakdown(salesTaxColHeaders, salesTaxResults, account);
		if (ValidationHelper.isNotEmpty(usageBreakdownActionName))
			testbillObj.testBillBreakdownAction(billMap, usageBreakdownColHeaders, usageBreakdownKeys, account,
					usageBreakdownActionName);
		if (ValidationHelper.isNotEmpty(creditBreakdownActionName))
			testbillObj.testBillBreakdownAction(billMap, creditBreakdownColHeaders, creditBreakdownKeys, account,
					creditBreakdownActionName);
		if (ValidationHelper.isNotEmpty(nonUsageBreakdownActionName))
			billActionObj.billBreakdownAction(billMap, nonUsageBreakdownColHeaders, nonUsageBreakdownKeys, account,
					nonUsageBreakdownActionName);
		NavigationHelper.navigateToScreen("Bill");
	}

	/*
	 * This method is for hotbill Validation
	 */
	private void hotBill() throws Exception {
		billreqObj.billRequestStatus(productionPath, createdBy, billProfileName, account, testBillCheckBox, timeStamp);
		NavigationHelper.navigateToScreen("Bill");
		assertEquals(NavigationHelper.getScreenTitle(), "Bill Search");
		billSearchObj.filterSelection(billProfileName, account, billPeriodFrom);
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(results)) {
			dataVerifyObj.validateData("grid_column_header_searchGrid_", columnHeaders, "searchGrid", results);
		}
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(currencyColHeaders)) {
			billActionObj.billCurrencyBreakdown(currencyColHeaders, currencyResults, account);
		}
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(salesTaxColHeaders)) {
			billActionObj.billSalesTaxBreakdown(salesTaxColHeaders, salesTaxResults, account);
		}
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(usageBreakdownActionName)) {
			billActionObj.billBreakdownAction(billMap, usageBreakdownColHeaders, usageBreakdownKeys, account,
					usageBreakdownActionName);
		}
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(creditBreakdownActionName)) {
			billActionObj.billBreakdownAction(billMap, creditBreakdownColHeaders, creditBreakdownKeys, account,
					creditBreakdownActionName);
		}
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(nonUsageBreakdownActionName)) {
			billActionObj.billBreakdownAction(billMap, nonUsageBreakdownColHeaders, nonUsageBreakdownKeys, account,
					nonUsageBreakdownActionName);
		}
	}

	private void hotBillVerification() throws Exception {
		if (ValidationHelper.isNotEmpty(results))
			dataVerifyObj.validateData("grid_column_header_searchGrid_", columnHeaders, "searchGrid", results);
		if (ValidationHelper.isNotEmpty(currencyColHeaders))
			billActionObj.billCurrencyBreakdown(currencyColHeaders, currencyResults, account);
		if (ValidationHelper.isNotEmpty(salesTaxColHeaders))
			billActionObj.billSalesTaxBreakdown(salesTaxColHeaders, salesTaxResults, account);
		if (ValidationHelper.isNotEmpty(usageBreakdownActionName))
			billActionObj.billBreakdownAction(billMap, usageBreakdownColHeaders, usageBreakdownKeys, account,
					usageBreakdownActionName);
		if (ValidationHelper.isNotEmpty(creditBreakdownActionName))
			billActionObj.billBreakdownAction(billMap, creditBreakdownColHeaders, creditBreakdownKeys, account,
					creditBreakdownActionName);
		if (ValidationHelper.isNotEmpty(nonUsageBreakdownActionName))
			billActionObj.billBreakdownAction(billMap, nonUsageBreakdownColHeaders, nonUsageBreakdownKeys, account,
					nonUsageBreakdownActionName);
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("Bill");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			billMap = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(billMap, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel(searchScreenColumns);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genericHelperObj.totalColumns(excelColumnNames);
		}

	}

	/*
	 * This method is to validate search screen columns
	 */
	public void billAdjustmentSearchScreenColVal() throws Exception {
		NavigationHelper.navigateToScreen("Bill Adjustments");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		billMap = excelHolderObj.dataMap(0);
		String searchScreenColumns = ExcelHolder.getKey(billMap, "SearchScreenColumns");
		billProfileName = ExcelHolder.getKey(billMap, "Bill Profile");
		String billBreakdownConfig = ExcelHolder.getKey(billMap, "BillBreakdownConfig");
		ArrayList<String> excelColumnNames = new ArrayList<String>();
		PSStringUtils strObj = new PSStringUtils();
		ButtonHelper.click("//div[text()='Advanced Search']");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		filterObj.testbillProfileAdvanceSearch("PS_SearchFilter_TestBill_billprofile_EntityFilter", "Bill Profile",
				billProfileName);
		GridHelper.clickRow("SearchGrid", 1, "Account");
		ButtonHelper.click("OKButton");
		ComboBoxHelper.select("billBreakdownConfig_gwt_uid_", billBreakdownConfig);
		ButtonHelper.click("SearchButton");
		String[] searchGridColumnsArr = strObj.stringSplitFirstLevel(searchScreenColumns);
		for (int col = 0; col < searchGridColumnsArr.length; col++) {
			excelColumnNames.add(searchGridColumnsArr[col]);
		}
		genericHelperObj.totalColumns(excelColumnNames);

	}

	/*
	 * This method is to initialize instance variables
	 */
	public void initializeVariables(Map<String, String> map) throws Exception {
		clientPartition = ExcelHolder.getKey(map, "Partition");
		createTestBill = ExcelHolder.getKey(map, "CreateTestBill");
		regenerateBill = ExcelHolder.getKey(map, "RegenerateBill");
		reVersionBill = ExcelHolder.getKey(map, "ReVersionBill");
		// billProfile = ExcelHolder.getKey(map, "BillProfile");
		billProfileName = ExcelHolder.getKey(map, "BillProfileName");
		account = ExcelHolder.getKey(map, "Account");
		// billProfile = ExcelHolder.getKey(map, "BillProfile");
		billPeriodFrom = ExcelHolder.getKey(map, "BillPeriodFrom");
		billPeriod = ExcelHolder.getKey(map, "BillPeriod");
		testBillCheckBox = ExcelHolder.getKey(map, "TestBillCheckBox");
		productionPath = ExcelHolder.getKey(map, "ProductionPath");
		createdBy = ExcelHolder.getKey(map, "CreatedBy");
		changeBillStatus = ExcelHolder.getKey(map, "ChangeBillStatus");
		columnHeaders = ExcelHolder.getKey(map, "ColumnHeaders");
		results = ExcelHolder.getKey(map, "BillResults");
		currencyColHeaders = ExcelHolder.getKey(map, "CurrencyColumnHeaders");
		currencyResults = ExcelHolder.getKey(map, "CurrencyResults");
		salesTaxColHeaders = ExcelHolder.getKey(map, "SalesTaxColumnHeaders");
		salesTaxResults = ExcelHolder.getKey(map, "SalesTaxResults");
		billStatus = ExcelHolder.getKey(map, "BillStatus");
		creditBreakdownActionName = ExcelHolder.getKey(map, "CreditBreakdownActionName");
		creditBreakdownKeys = ExcelHolder.getKey(map, "CreditBreakdownKeys");
		creditBreakdownColHeaders = ExcelHolder.getKey(map, "CreditBreakdownColHeaders");
		usageBreakdownActionName = ExcelHolder.getKey(map, "UsageBreakdownActionName");
		usageBreakdownColHeaders = ExcelHolder.getKey(map, "UsageBreakdownColHeaders");
		usageBreakdownKeys = ExcelHolder.getKey(map, "UsageBreakdownKeys");

		nonUsageBreakdownActionName = billMap.get("NonUsageBreakdownActionName");
		nonUsageBreakdownColHeaders = billMap.get("NonUsageBreakdownColHeaders");
		nonUsageBreakdownKeys = billMap.get("NonUsageBreakdownKeys");

		breakdownName = ExcelHolder.getKey(map, "AdjustmentBreakdownName");
		adjustmentColHeaders = ExcelHolder.getKey(map, "AdjustmentColHeaders");
		adjustmentKeys = ExcelHolder.getKey(map, "AdjustmentKeys");
	}
}
