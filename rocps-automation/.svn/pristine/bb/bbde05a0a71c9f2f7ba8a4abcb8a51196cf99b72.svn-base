package com.subex.rocps.automation.helpers.application.bills.billrequest;

import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillRequestSearchImpl extends PSAcceptanceTest {

	public PSGenericHelper genericHelperObj = new PSGenericHelper();
	public long timeLimit;
	public String timeStampString;

	/*
	 * This Method is used for various filter operations required
	 */

	public void filterOperations(String productionPath, String createdBy, String account, String billProfile,
			String testBillCheckBox) throws Exception {
		ComboBoxHelper.select("PS_Details_billRequest_PrdPath", productionPath);
		ComboBoxHelper.select("PS_Details_billRequest_CreatedBy", createdBy);
		GridFilterSearchHelper obj = new GridFilterSearchHelper();
		obj.accountFilter("PS_Details_billRequest_accountEntityfilter", "PS_Details_billRequest_account_txtID", account,
				"Account");
		// obj.billProfileFilter("PS_Details_billRequest_BillPrfFilter",
		// billProfile);
		obj.billProfileAdvanceFilter("PS_Details_billRequest_grid", "Bill Profile", billProfile);
		filterBillType(testBillCheckBox);
		CalendarHelper.setToday("PS_Details_billRequest_CalendarID");
		ButtonHelper.click("PS_Details_billRequest_Search");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This Method is used to check the Task Status of the Bill Request.
	 */

	public void checkTaskStatus() throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "Bill Request Search");
		sortDescendingScheduledDate();
		waitForTaskCompletion("PS_Details_billRequest_grid");
	}

	/*
	 * This Method is used to check the Period Status of the Bill Request.
	 */

	public void checkPeriodStatus() throws Exception {
		String acceptedStatus = "Completed";
		String actualValue = GridHelper.getCellValue("PS_Details_billRequest_grid", 1, "Period Status").trim();
		assertEquals(actualValue, acceptedStatus, "Bill Request Failed");

	}
	
	public void checkRequestStatus() throws Exception {
		String acceptedStatus = "Completed";
		String actualValue = GridHelper.getCellValue("PS_Details_billRequest_grid", 1, "Request Status").trim();
		assertEquals(actualValue, acceptedStatus, "Bill Request Failed");

	}

	/*
	 * This Method is used to set a time limit for the checking task Status.
	 */

	public void changeToMilliSeconds() throws Exception {
		long seconds = Long.parseLong(configProp.getProperty("SearchLoopTimeLimitInSecs"));
		timeLimit = seconds * 1000;
	}

	/*
	 * This Method is used to sort the column in descending order
	 */

	public void sortDescendingScheduledDate() throws Exception {
		ButtonHelper.click("PS_Details_billRequest_ScheduledDate");
		ButtonHelper.click("PS_Details_billRequest_ScheduledDate");
	}

	/*
	 * This Method is used to filter on the basis of Bill Type
	 */

	public void filterBillType(String testBillCheckBox) throws Exception {
		if (ValidationHelper.isTrue(testBillCheckBox)) {
			SearchGridHelper.gridFilterSearchWithComboBox("PS_Details_billRequest_TestBillComboBox", "Yes",
					"Test Bill");
		} else {
			SearchGridHelper.gridFilterSearchWithComboBox("PS_Details_billRequest_TestBillComboBox", "No", "Test Bill");
		}

	}

	/* This method is to get the System time stamp */

	public String getSystemTimeStamp() throws Exception {
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		timeStampString = timeStamp.toString();
		timeStampString = timeStampString.substring(0, timeStampString.length() - 4);
		return timeStampString;

	}

	/*
	 * This method is to filter and get no of Records after a particular
	 * ScheduledDate
	 */

	public void recordsAfterDate(String filterIconID, String calendarID, String scheduledDate, String columnHeader)
			throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon(filterIconID);
		CalendarHelper.setAfterDate(calendarID, scheduledDate);
		ButtonHelper.click("PS_Details_billRequest_Search");

	}

	/*
	 * This Method is to wait for the task to complete or for defined time
	 * limit, whichever is first.
	 */

	public void waitForTaskCompletion(String gridId) throws Exception {
		ArrayList<String> acceptedStatus = new ArrayList<String>();
		changeToMilliSeconds();
		acceptedStatus.add("Failed");
		acceptedStatus.add("Completed");
		long startTime = System.currentTimeMillis();
		String actualValue = GridHelper.getCellValue(gridId, 1, "Task Status").trim();
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		while ((!actualValue.equals(acceptedStatus.get(0)) && !actualValue.equals(acceptedStatus.get(1)))
				&& (System.currentTimeMillis() - startTime) < timeLimit) {
			ButtonHelper.click("PS_Details_billRequest_Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			actualValue = GridHelper.getCellValue(gridId, 1, "Task Status").trim();
		}
		GenericHelper.waitForLoadmask();
		assertTrue(acceptedStatus.contains(actualValue),"Bill Task status is not in Completed:");
	}

	/*
	 * this method is to check task status from bill Request screen
	 */
	public void billRequestStatus(String productionPath, String createdBy, String billProfileName, String accName,
			String testBillCheckBox, String timeStamp) throws Exception {

		NavigationHelper.navigateToScreen("Bill Request");
		assertEquals(NavigationHelper.getScreenTitle(), "Bill Request Search");
		filterOperations(productionPath, createdBy, accName, billProfileName, testBillCheckBox);
		recordsAfterDate("PS_Details_billRequest_ScheduledDate_Filter", "PS_Details_billRequest_ScheduledDate_Calendar",
				timeStamp, "Scheduled Date");
		checkTaskStatus();
		checkRequestStatus();
		checkPeriodStatus();		
		Log4jHelper.logInfo("Bill Task is completed successfully for bill profile" + billProfileName);
	}
}
