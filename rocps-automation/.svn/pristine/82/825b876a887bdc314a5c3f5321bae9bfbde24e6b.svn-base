package com.subex.rocps.automation.helpers.application.bills.billperiod;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;

public class BillPeriodSearchImpl {

	/*
	 * This method is for filter operations for Filters : Account, Bill Profile,
	 * From Date
	 */

	public void filterOperations(String accName, String billProfileName, String fromDate) throws Exception {
		ButtonHelper.click("PS_Details_billPeriod_Clear");
		GridFilterSearchHelper filterObj = new GridFilterSearchHelper();
		filterObj.accountFilter("grid_column_header_filtersearchGrid_billProfile$account$paccName",
				"PS_Details_billPeriod_AccountTextBox", accName, "Account");
		GenericHelper.waitForLoadmask();
		filterObj.billProfileAdvanceFilter("PS_Details_billPeriod_grid", "Bill Profile", billProfileName);
		GenericHelper.waitForLoadmask();
		CalendarHelper.setOnDate("PS_Details_billPeriod_Calendar", fromDate);
		ButtonHelper.click("PS_Details_billPeriod_Search");
		GenericHelper.waitForLoadmask();

	}

	/*
	 * This method is for filter operations for Filters : Account, Bill Profile,
	 * From Date, To Date
	 */

	public void filterOperations(String accName, String billProfileName, String fromDate, String toDate)
			throws Exception {
		ButtonHelper.click("PS_Details_billPeriod_Clear");
		GridFilterSearchHelper filterObj = new GridFilterSearchHelper();
		filterObj.accountFilter("grid_column_header_filtersearchGrid_billProfile$account$paccName",
				"PS_Details_billPeriod_AccountTextBox", accName, "Account");
		GenericHelper.waitForLoadmask();
		filterObj.billProfileAdvanceFilter("PS_Details_billPeriod_grid", "Bill Profile", billProfileName);
		GenericHelper.waitForLoadmask();
		CalendarHelper.setBetweenDate("PS_Details_billPeriod_Calendar", fromDate, toDate);
		ButtonHelper.click("PS_Details_billPeriod_Search");
		GenericHelper.waitForLoadmask();

	}
}
