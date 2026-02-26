package com.subex.rocps.automation.helpers.application.bills.billbreakdownconfiguration;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillBreakdownConfigActionImpl extends PSAcceptanceTest {

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is to create new BillBreakdown Configuration
	 */
	public void createNewbillBreaskdownConfig(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to change the status of bill Configuration from Draft to
	 * Accept
	 */
	public void changeBillBreakdownConfigStatus(String name) throws Exception {
		GridHelper.clickRow("searchGrid", name, "Name");
		NavigationHelper.navigateToAction("Change Status", "Accept");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		ButtonHelper.click("YesButton");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}

	/*
	 * This method is to delete bill breakdown configuration
	 */
	public void clickDeleteAction(String name) throws Exception {
		assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
		genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}

	/*
	 * This method is to un delete bill breakdown configuration
	 */
	public void clickUnDeleteAction(String name) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
	}
}
