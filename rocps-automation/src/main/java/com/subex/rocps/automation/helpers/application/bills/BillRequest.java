package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.bills.billrequest.*;
import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillRequest extends PSAcceptanceTest {
	protected ExcelReader excelData = null;
	protected Map<String, ArrayList<String>> billReqExcel = null;
	protected Map<String, String> billReqMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int paramVal;
	protected int colSize;
	protected String clientPartition;
	public String billProfile;
	public String billProfileName;
	public String billPeriod;
	public String testBillCheckBox;
	public String account;
	public String productionPath;
	public String createdBy;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	DataVerificationHelper dataverifyObj = new DataVerificationHelper();

	/*
	 * Constructor for initializing excel Identifying the column size from the
	 * map passed
	 */
	public BillRequest(String path, String workBookName, String sheetName, String testCaseName) throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billReqExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName);
		excelHolderObj = new ExcelHolder(billReqExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Overloaded constructor for reading data from excel if test case name
	 * appears more than once
	 * 
	 * @Param occurance : Test case instance in excel sheet Constructor for
	 * initializing excel Identifying the column size from the map
	 */
	public BillRequest(String path, String workBookName, String sheetName, String testCaseName, int occurance)
			throws Exception {
		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		excelData = new ExcelReader();
		billReqExcel = excelData.readDataByColumn(this.path, this.workBookName, this.sheetName, this.testCaseName,
				occurance);
		excelHolderObj = new ExcelHolder(billReqExcel);
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * Configuring the Billing Cycle
	 * 
	 * @method : isBillingCyclePresent returns false then Billing Cycle is
	 * configured isBillingCyclePresent returns true then Billing Cycle is not
	 * configured
	 */
	public void billRequestCreation() throws Exception {
		try {
			for (paramVal = 0; paramVal < colSize; paramVal++) {

				billReqMap = excelHolderObj.dataMap(paramVal);
				NavigationHelper.navigateToScreen("Bill Request");

				newBillRequest();
				Log4jHelper.logInfo("Bill Request Completed");

			}
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	protected void newBillRequest() throws Exception {
		BillRequestDetailImpl detailObj = new BillRequestDetailImpl();
		BillRequestSearchImpl searchObj = new BillRequestSearchImpl();
		initializeVariables(billReqMap);
		searchObj.filterOperations(productionPath, createdBy, account, billProfileName, testBillCheckBox);
		String timeStamp = searchObj.getSystemTimeStamp();
		detailObj.createNewBillRequest(clientPartition);
		detailObj.billRequestDetails(billProfileName, billPeriod, testBillCheckBox);
		detailObj.saveBillRequest();
		searchObj.recordsAfterDate("PS_Details_billRequest_ScheduledDate_Filter",
				"PS_Details_billRequest_ScheduledDate_Calendar", timeStamp, "Scheduled Date");
		searchObj.checkTaskStatus();
		searchObj.checkRequestStatus();
		searchObj.checkPeriodStatus();

	}

	public void initializeVariables(Map<String, String> Map) throws Exception {
		billProfile = ExcelHolder.getKey(Map, "BillProfile");
		billPeriod = ExcelHolder.getKey(Map, "BillPeriod");
		billProfileName = ExcelHolder.getKey(Map, "BillProfileName");
		testBillCheckBox = ExcelHolder.getKey(Map, "TestBillCheckBox");
		clientPartition = ExcelHolder.getKey(Map, "Partition");
		account = ExcelHolder.getKey(Map, "Account");
		productionPath = ExcelHolder.getKey(Map, "ProductionPath");
		createdBy = ExcelHolder.getKey(Map, "CreatedBy");

	}
	
	/*
	 * This method is to validate search screen columns
	 */
	public void searchScreenColumnsValidation() throws Exception
	{		
		NavigationHelper.navigateToScreen( "Bill Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		for ( paramVal = 0; paramVal < colSize; paramVal++ )
		{
			billReqMap = excelHolderObj.dataMap( paramVal );
			String searchScreenColumns = ExcelHolder.getKey( billReqMap, "SearchScreenColumns" );
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

}
