package com.subex.rocps.automation.helpers.application.bills.billingcycle;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class BillingCycleActionImpl {

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is to create new Billing Cycle
	 */
	public void createNewBillingcycle(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();

	}

	/*
	 * This method is to edit Billing Cycle
	 */
	public void editBillingcycle() throws Exception {
		NavigationHelper.navigateToAction("Common Task", "Edit");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking delete action in Billing cycle search screen
	 */
	public void clickDeleteAction(String name) throws Exception {

		genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in Billing cycle search screen
	 */
	public void clickUnDeleteAction(String name) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
