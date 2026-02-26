package com.subex.rocps.automation.helpers.application.bills.billperiod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;

public class BillPeriodActionImpl extends PSAcceptanceTest{

	public String createTestBillConfirmMsg = "Are you sure you wish to create test Bills for the selected Bill Periods?";
	public String createTestBillInfoMsg = "Bill task created successfully with taskId:";
	public String closeBillPeriodConfirmMsg = "Closing the Bill Period will close all the previous periods. Are you sure you want to continue?";
	public String closeBillPeriodInfoMsg = "Bill Task created with task id";
	public String regenerateBillInfoMsg = "Bill Task created with task id";

	/*
	 * This Method is for Create Test Bill Action
	 */

	public void createTestBill() throws Exception {
		try {
			assertEquals(NavigationHelper.getScreenTitle(), "Bill Period Search");
			GridHelper.clickRow("searchGrid", 1, 1);
			NavigationHelper.navigateToAction("Bill Actions", "Create Test Bill");
			GenericHelper.waitForLoadmask();
			assertEquals(NavigationHelper.getScreenTitle(), "Confirm");
			assertTrue(PopupHelper.isTextPresent(createTestBillConfirmMsg));
			ButtonHelper.click("PS_Details_billPeriod_Ok");
			GenericHelper.waitForLoadmask();
			assertEquals(NavigationHelper.getScreenTitle(), "Information");
			assertTrue(PopupHelper.isTextPresent(createTestBillInfoMsg));
			ButtonHelper.click("PS_Details_billPeriod_Ok");
			GenericHelper.waitForLoadmask();

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * This Method is for Close Bill Period Action
	 */

	public void closeBillPeriod() throws Exception {
		try {
			assertEquals(NavigationHelper.getScreenTitle(), "Bill Period Search");
			GridHelper.clickRow("searchGrid", 1, 1);
			NavigationHelper.navigateToAction("Bill Actions", "Close Bill Period");
			GenericHelper.waitForLoadmask();
			assertTrue(PopupHelper.isTextPresent(closeBillPeriodConfirmMsg));
			assertEquals(NavigationHelper.getScreenTitle(), "Confirm");
			ButtonHelper.click("PS_Details_billPeriod_Ok");
			GenericHelper.waitForLoadmask();
			assertEquals(NavigationHelper.getScreenTitle(), "Information");
			assertTrue(PopupHelper.isTextPresent(closeBillPeriodInfoMsg));
			ButtonHelper.click("PS_Details_billPeriod_Ok");
			GenericHelper.waitForLoadmask();
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * This Method is for Regenerate Bill Action
	 */

	public void regenerateBill() throws Exception {
		try {
			assertEquals(NavigationHelper.getScreenTitle(), "Bill Period Search");
			GridHelper.clickRow("searchGrid", 1, 1);
			NavigationHelper.navigateToAction("Bill Actions", "Regenerate Bill");
			GenericHelper.waitForLoadmask();
			assertEquals(NavigationHelper.getScreenTitle(), "Information");
			assertTrue(PopupHelper.isTextPresent(regenerateBillInfoMsg));
			ButtonHelper.click("PS_Details_billPeriod_Ok");
			GenericHelper.waitForLoadmask();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
}
