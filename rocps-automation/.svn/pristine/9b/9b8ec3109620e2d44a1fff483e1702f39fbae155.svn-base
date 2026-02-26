package com.subex.rocps.automation.helpers.application.prepayments;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.prepayments.prepayments.PrePaymentsDetailImpl;
import com.subex.rocps.automation.helpers.application.prepayments.prepayments.PrePaymentsSearchImpl;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PrePayments extends PSAcceptanceTest {

	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> paymentExcel = null;
	protected Map<String, String> paymentMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	protected String account;
	protected String transactionReference;
	String transactionDate;
	String billProfile;
	String currency;
	String amount;
	String effectiveAmount;
	String paymentMethod;
	String comments;
	String createdBy;
	String colHeaders;
	String results;
	String changeStatusColHeaders;
	String changeStatusResults;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	PrePaymentsDetailImpl paymentDetailObj = new PrePaymentsDetailImpl();
	PrePaymentsSearchImpl paymentSearchObj = new PrePaymentsSearchImpl();
	DataVerificationHelper dataVerifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the map
	 * passed
	 */
	@Test
	public PrePayments(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		paymentExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(paymentExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name appears
	 * more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public PrePayments(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		paymentExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(paymentExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Pre- Payments
	 * 
	 */
	public void prePaymentsCreation() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Pre-Payments");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				paymentMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(paymentMap);
				boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
						createdBy, account);
				if (!isPrePaymentPresent)
					newPrePaymentsConfig();
				else
					Log4jHelper.logInfo("Pre- Payments  is already present for " + billProfile);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void newPrePaymentsConfig() throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		paymentDetailObj.prepaymentConfiguration(transactionReference, transactionDate, billProfile, currency, amount,
				paymentMethod, comments);
		paymentDetailObj.savePrePayments(transactionReference);
		Log4jHelper.logInfo("Pre- Payments is created successfully:" + billProfile);
		if (!results.isEmpty())
			dataVerifyObj.validateData("grid_column_header_searchGrid_", colHeaders, "SearchGrid", results);

	}

	/*
	 * This method is to change status prepayments
	 */
	public void prePaymentsChangeStatus() throws Exception {

		NavigationHelper.navigateToScreen("Pre-Payments");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			paymentMap = excelHolderObj.dataMap(paramVal);

			initializeVariables(paymentMap);
			boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
					createdBy, account);
			if (isPrePaymentPresent) {

				genericHelperObj.waitforHeaderElement("Currency");
				String actualVal = GridHelper.getCellValue("SearchGrid", 1, "Status");
				if (actualVal.contains("Draft")) {
					//assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
					GridHelper.clickRow("SearchGrid", transactionReference, "Transaction Reference");
					GenericHelper.waitForLoadmask();
					dataVerifyObj.validateData("grid_column_header_searchGrid_", colHeaders, "SearchGrid", results);
					PSGenericHelper.waitForParentActionElementTOBeclickable("Status");
					NavigationHelper.navigateToAction("Status", "Approve Pre-Payment");
					GenericHelper.waitForLoadmask();
					assertTrue(paymentDetailObj.isPopUpValidation(),"Popup Text is not matched");
					GenericHelper.waitForLoadmask();
					Thread.sleep(2000);
					ButtonHelper.click("SearchButton");
					genericHelperObj.waitforHeaderElement("Currency");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					assertTrue(GridHelper.isValuePresent("SearchGrid", "Approved", "Status"));
					dataVerifyObj.validateData("grid_column_header_searchGrid_", changeStatusColHeaders, "SearchGrid",
							changeStatusResults);
					Log4jHelper.logInfo("Pre-Payments status is changed successfully with name " + billProfile);
				}
				else 
					Log4jHelper.logInfo("Pre-Payments is already changed to Accepted status " + billProfile);

			} else
				Log4jHelper.logInfo("Pre-Payments is not available with name " + billProfile);
		}
	}

	/*
	 * This method is to Reverse prepayments
	 */
	public void prePaymentsReverse() throws Exception {

		NavigationHelper.navigateToScreen("Pre-Payments");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			paymentMap = excelHolderObj.dataMap(paramVal);

			initializeVariables(paymentMap);
			boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
					createdBy, account);
			if (isPrePaymentPresent) {
				assertTrue(GridHelper.isValuePresent("SearchGrid", "Approved", "Status"));
				GridHelper.clickRow("SearchGrid", transactionReference, "Transaction Reference");
				GenericHelper.waitForLoadmask();
				PSGenericHelper.waitForParentActionElementTOBeclickable("Reverse");
				NavigationHelper.navigateToAction("Reverse", "Reverse Pre-Payment");
				GenericHelper.waitForLoadmask();
				assertEquals(NavigationHelper.getScreenTitle(), "Edit Pre-Payment Details");
				if (ValidationHelper.isNotEmpty(amount))
					TextBoxHelper.type("pprmAmt", amount);
				paymentDetailObj.savePrePayments(transactionReference);
				Log4jHelper.logInfo("Pre- Payments is edited successfully:" + billProfile);
				GridHelper.sortGrid("SearchGrid", "Created On");
				GridHelper.sortGrid("SearchGrid", "Created On");
				assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
				dataVerifyObj.validateData("grid_column_header_searchGrid_", paymentMap, "SearchGrid", colHeaders,
						results);
				assertTrue(reversedStatusCheck());
				Log4jHelper.logInfo("Pre-Payments is reverse action is validation successfully " + billProfile);

			} else
				Log4jHelper.logInfo("Pre-Payments is not available with name " + billProfile);
		}
	}

	public void editPrePayments() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Pre-Payments");
			for (paramVal = 0; paramVal < colSize; paramVal++) {
				paymentMap = excelHolderObj.dataMap(paramVal);

				initializeVariables(paymentMap);
				boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
						createdBy, account);
				if (isPrePaymentPresent) {
					int row = GridHelper.getRowNumber("SearchGrid", transactionReference, "Transaction Reference");
					NavigationHelper.navigateToEdit("SearchGrid", row);
					assertEquals(NavigationHelper.getScreenTitle(), "Edit Pre-Payment Details");
					paymentDetailObj.editPrepaymentConfiguration(transactionReference, transactionDate, billProfile,
							currency, amount, paymentMethod, comments);
					paymentDetailObj.savePrePayments(transactionReference);
				} else
					Log4jHelper.logInfo("Pre- Payments  is already present for " + billProfile);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to check the reverse status
	 */
	public boolean reversedStatusCheck() throws Exception {
		boolean flag = false;
		paymentSearchObj.prePaymentsSearch(billProfile, transactionReference, createdBy, account);
		SearchGridHelper.gridFilterSearchWithComboBox("pprmReversedFl_gwt_uid_", "Yes", "Reversed");
		int row = GridHelper.getRowCount("SearchGrid");
		if (row > 0)
			flag = true;

		return flag;
	}

	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception {
		NavigationHelper.navigateToScreen("Pre-Payments");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			paymentMap = excelHolderObj.dataMap(paramVal);
			String searchScreenColumns = ExcelHolder.getKey(paymentMap, "SearchScreenColumns");
			ArrayList<String> excelColumnNames = new ArrayList<String>();
			String[] searchGridColumnsArr = searchScreenColumns.split(regex, -1);
			for (int col = 0; col < searchGridColumnsArr.length; col++) {
				excelColumnNames.add(searchGridColumnsArr[col]);
			}
			genericHelperObj.totalColumns(excelColumnNames);
		}

	}

	/*
	 * This method is to delete PrePayments
	 */
	public void prePaymentsDelete() throws Exception {

		NavigationHelper.navigateToScreen("Pre-Payments");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			paymentMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(paymentMap, "Partition");
			billProfile = ExcelHolder.getKey(paymentMap, "BillProfile");
			transactionReference = ExcelHolder.getKey(paymentMap, "TransactionReference");
			createdBy = ExcelHolder.getKey(paymentMap, "CreatedBy");
			account = ExcelHolder.getKey(paymentMap, "Account");

			if (ValidationHelper.isTrue(configProp.getProperty("clientPartitionFlag"))
					&& ValidationHelper.isNotEmpty(clientPartition))
				genericHelperObj.collapsableXpath();
			genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
			boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
					createdBy, account);
			if (isPrePaymentPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(transactionReference, "Transaction Reference", "Delete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
				assertTrue(paymentSearchObj.prePaymentsSearch(billProfile, transactionReference, createdBy, account));
				Log4jHelper.logInfo("Pre-Payments is deleted successfully with name " + billProfile);
			} else
				Log4jHelper.logInfo("Pre-Payments is not available with name " + billProfile);
		}
	}

	/*
	 * This method is to un delete amount threshold
	 */
	public void prePaymentsUnDelete() throws Exception {

		NavigationHelper.navigateToScreen("Pre-Payments");
		for (paramVal = 0; paramVal < colSize; paramVal++) {
			paymentMap = excelHolderObj.dataMap(paramVal);

			clientPartition = ExcelHolder.getKey(paymentMap, "Partition");
			billProfile = ExcelHolder.getKey(paymentMap, "BillProfile");
			transactionReference = ExcelHolder.getKey(paymentMap, "TransactionReference");
			genericHelperObj.selectPartitionFilter(clientPartition, "Deleted Items");
			GenericHelper.waitForLoadmask();
			boolean isPrePaymentPresent = paymentSearchObj.prePaymentsSearch(billProfile, transactionReference,
					createdBy, account);
			if (isPrePaymentPresent) {
				genericHelperObj.clickDeleteOrUnDeleteAction(transactionReference, "Transaction Reference", "Undelete");
				GenericHelper.waitForLoadmask();
				genericHelperObj.selectPartitionFilter(clientPartition, "Non Deleted Items");
				assertTrue(paymentSearchObj.prePaymentsSearch(billProfile, transactionReference, createdBy, account));
				Log4jHelper.logInfo("Pre-Payments is un deleted successfully : " + billProfile);
			} else
				Log4jHelper.logInfo("Pre-Payments is not available : " + billProfile);
		}
	}

	/*
	 * This method is to initialize instance variables
	 */
	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		account = ExcelHolder.getKey(map, "Account");
		billProfile = ExcelHolder.getKey(map, "BillProfile");
		transactionReference = ExcelHolder.getKey(map, "TransactionReference");
		transactionDate = ExcelHolder.getKey(map, "TransactionDate");
		amount = ExcelHolder.getKey(map, "Amount");
		effectiveAmount = ExcelHolder.getKey(map, "EffectiveAmount");
		paymentMethod = ExcelHolder.getKey(map, "PaymentMethod");
		comments = ExcelHolder.getKey(map, "Comments");
		createdBy = ExcelHolder.getKey(map, "CreatedBy");
		currency = ExcelHolder.getKey(map, "Currency");
		colHeaders = ExcelHolder.getKey(map, "ColHeaders");
		results = ExcelHolder.getKey(map, "Results");
		changeStatusColHeaders = ExcelHolder.getKey(map, "ChangeStatusColHeaders");
		changeStatusResults = ExcelHolder.getKey(map, "ChangeStatusResults");

	}

}
