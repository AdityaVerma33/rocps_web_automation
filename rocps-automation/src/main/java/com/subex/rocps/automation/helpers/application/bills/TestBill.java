package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billrequest.BillRequestSearchImpl;
import com.subex.rocps.automation.helpers.application.bills.hotbill.*;
import com.subex.rocps.automation.helpers.application.bills.testbill.TestBillActionImpl;
import com.subex.rocps.automation.helpers.application.bills.testbill.TestBillSearchImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class TestBill extends PSAcceptanceTest {
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> testBillExcel = null;
	protected Map<String, String> testBillMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String billProfile;
	protected String clientPartition;
	protected String account;
	protected String billperiodFrom;
	protected String billStatus;
	protected String billPeriod;
	protected String testBillCheckBox;
	protected String currency;
	protected String salesTax;
	protected String billCurrencyBreakdown;
	protected String billSalesTaxBreakdown;
	protected String currencyBreakdownAmt;
	protected String salesTaxBreakdownAmt;
	protected String createTestbill;
	protected String billPeriodFrom;
	protected String billProfileName;
	protected String productionPath;
	protected String createdBy;
	protected String columnHeaders;
	protected String results;
	protected String salesTaxColumnHeaders;
	protected String salesTaxResults;
	protected String currencyColumnHeaders;
	protected String currencyResults;
	protected String creditBreakdownKeys;
	protected String creditBreakdownColHeaders;
	protected String creditBreakdownActionName;
	protected String usageBreakdownKeys;
	protected String usageBreakdownColHeaders;
	protected String usageBreakdownActionName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	BillActionImpl billActionObj = new BillActionImpl();
	BillSearchImpl billSearchObj = new BillSearchImpl();
	BillRequestSearchImpl billreqObj = new BillRequestSearchImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();
	TestBillSearchImpl testbilSearchObj = new TestBillSearchImpl();
	TestBillActionImpl testbillObj = new TestBillActionImpl();
	protected String timeStamp;

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	@Test
	public TestBill(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		testBillExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(testBillExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public TestBill(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		testBillExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(testBillExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the TestBill
	 * 
	 */
	public void testBillCreation() throws Exception {
		try {
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				testBillMap = excelHolderObj.dataMap(paramVal);
				NavigationHelper.navigateToScreen("Test Bill");
				GenericHelper.waitForLoadmask();
				assertEquals(NavigationHelper.getScreenTitle(), "Test Bill Search");
				newTestBill();
			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	protected void newTestBill() throws Exception {
		BillActionImpl billActionObj = new BillActionImpl();
		initializeVariables(testBillMap);
		timeStamp = billreqObj.getSystemTimeStamp();
		billActionObj.newBillRequest(clientPartition, billProfileName, billPeriod, testBillCheckBox);
		validateTestBill();

	}

	/*
	 * This Method is to validate Test Bill
	 */
	public void validateTestBill() throws Exception {
		billreqObj.billRequestStatus(productionPath, createdBy, billProfileName, account, testBillCheckBox, timeStamp);
		NavigationHelper.navigateToScreen("Test Bill");
		assertEquals(NavigationHelper.getScreenTitle(), "Test Bill Search");
		testbilSearchObj.filterOperations(account, billProfileName, billPeriodFrom);
		if (ValidationHelper.isNotEmpty(results))
			dataVerifyObj.validateData("grid_column_header_searchGrid_", columnHeaders, "searchGrid", results);
		Log4jHelper.logInfo("Test Bill created is successfully validated" + billProfileName);

		if (ValidationHelper.isNotEmpty(currencyColumnHeaders))
			testbillObj.billCurrencyBreakdown(currencyColumnHeaders, currencyResults, account);
			Log4jHelper.logInfo("Test Bill Currency Breakdown in successfully validated" + billProfileName);

		if (ValidationHelper.isNotEmpty(salesTaxColumnHeaders))
			testbillObj.billSalesTaxBreakdown(salesTaxColumnHeaders, salesTaxResults, account);
			Log4jHelper.logInfo("Test Bill Sales Tax Breakdown in successfully validated" + billProfileName);

		if (ValidationHelper.isNotEmpty(usageBreakdownKeys))
			testbillObj.testBillBreakdownAction(testBillMap, usageBreakdownColHeaders, usageBreakdownKeys, account,
					usageBreakdownActionName);

		if (ValidationHelper.isNotEmpty(creditBreakdownKeys))
			testbillObj.testBillBreakdownAction(testBillMap, creditBreakdownColHeaders, creditBreakdownKeys, account,
					creditBreakdownActionName);

	}
	
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Test Bill" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			testBillMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( testBillMap, "SearchScreenColumns" );
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			PSStringUtils strObj = new PSStringUtils();
			String[] searchGridColumnsArr = strObj.stringSplitFirstLevel( searchScreenColumns );
			for ( int col = 0; col < searchGridColumnsArr.length; col++ )
			{
				excelColumnNames.add( searchGridColumnsArr[col] );
			}
			genericHelperObj.totalColumns( excelColumnNames );
		}

	}

	public void initializeVariables(Map<String, String> map) throws Exception {
		account = ExcelHolder.getKey(map, "Account");
		// billProfile = ExcelHolder.getKey(map, "BillProfile");
		//billperiodFrom = ExcelHolder.getKey(map, "BillPeriodFrom");
		billStatus = ExcelHolder.getKey(map, "BillStatus");
		billPeriod = ExcelHolder.getKey(map, "BillPeriod");
		testBillCheckBox = ExcelHolder.getKey(map, "TestBillCheckBox");
		// billProfile = ExcelHolder.getKey(map, "BillProfile");
		//billPeriod = ExcelHolder.getKey(map, "BillPeriod");
		clientPartition = ExcelHolder.getKey(map, "Partition");
		//billPeriodFrom = ExcelHolder.getKey(map, "BillPeriodFrom");
		//account = ExcelHolder.getKey(map, "Account");
		productionPath = ExcelHolder.getKey(map, "ProductionPath");
		createdBy = ExcelHolder.getKey(map, "CreatedBy");
		billProfileName = ExcelHolder.getKey(map, "BillProfileName");
		currencyColumnHeaders = ExcelHolder.getKey(map, "CurrencyColumnHeaders");
		salesTaxColumnHeaders = ExcelHolder.getKey(map, "SalesTaxColumnHeaders");
		currencyResults = ExcelHolder.getKey(map, "CurrencyResults");
		salesTaxResults = ExcelHolder.getKey(map, "SalesTaxResults");
		columnHeaders = ExcelHolder.getKey(map, "ColumnHeaders");
		results = ExcelHolder.getKey(map, "BillResults");
		creditBreakdownKeys = ExcelHolder.getKey(map, "CreditBreakdownKeys");
		creditBreakdownColHeaders = ExcelHolder.getKey(map, "CreditBreakdownColumnHeaders");
		creditBreakdownActionName = ExcelHolder.getKey(map, "CreditBreakdownActionName");
		usageBreakdownKeys = ExcelHolder.getKey(map, "UsageBreakdownKeys");
		usageBreakdownColHeaders = ExcelHolder.getKey(map, "UsageBreakdownColumnHeaders");
		usageBreakdownActionName = ExcelHolder.getKey(map, "UsageBreakdownActionName");
	}
}
