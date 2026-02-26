package com.subex.rocps.automation.helpers.application.bills.billadjustments;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.DataVerificationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class BillAdjustmentsImpl {
	/*
	 * Method for navigating to bill adjustments screen
	 */
	public void navigateAdjustments() throws Exception {

		NavigationHelper.navigateToScreen("Bill Adjustments");
		GenericHelper.waitForLoadmask();
		screenAssertion();
	}

	/*
	 * Method for checking screen assertion
	 */
	public void screenAssertion() throws Exception {
		String screenName = NavigationHelper.getScreenTitle();
		Log4jHelper.logInfo("Screen title : " + screenName);
		Assert.assertEquals(screenName, "Bill Adjustments Search", "Screen name is not matching");
	}

	/*
	 * Method for validating data in adjustments screen
	 */
	public void validateAdjustments(Map<String, String> dataMap, String columnHeaderCell, String rowNumsCol)
			throws Exception {
		DataVerificationHelper dataVerfyObj = new DataVerificationHelper();
		dataVerfyObj.validateDataInResultScreen("grid_column_header_searchGrid_", dataMap, columnHeaderCell, rowNumsCol,
				false);
	}

	/*
	 * Method for adjusting all rows
	 */
	public void adjustAllRows() throws Exception {
		int totalRows = GridHelper.getRowCount("SearchGrid");
		GridHelper.clickMultipleCells("SearchGrid", 1, totalRows);
		NavigationHelper.navigateToAction("Bill Adjustment Actions", "Adjust in Next Bill");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for verifying and marking bill adjustment data
	 */
	public void billDataAdjustment(String breakdownName, Map<String, String> dataMap, String columnHeaderNames,
			String rowNumsCol) throws Exception {
		screenAssertion();
		ComboBoxHelper.select("billBreakdownConfig_gwt_uid_", breakdownName);
		ButtonHelper.click("SearchButton");
		GenericHelper.waitForLoadmask();
		if (ValidationHelper.isNotEmpty(rowNumsCol)) {
			validateAdjustments(dataMap, columnHeaderNames, rowNumsCol);
			adjustAllRows();
		}
	}

	
}
