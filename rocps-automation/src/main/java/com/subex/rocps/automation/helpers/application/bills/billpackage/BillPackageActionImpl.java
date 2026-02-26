package com.subex.rocps.automation.helpers.application.bills.billpackage;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillPackageActionImpl extends PSAcceptanceTest {
	PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is to create new Bill package
	 */
	public void createNewbillPackageConfig(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to edit Bill Package
	 */
	public void editBillPackage() throws Exception {
		NavigationHelper.navigateToAction("Common Task", "Edit");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking delete action in bill Package search screen
	 */
	public void clickDeleteAction(String billPackageName) throws Exception {

		genericHelperObj.clickDeleteOrUnDeleteAction(billPackageName, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in bill Package search screen
	 */
	public void clickUnDeleteAction(String billPackageName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(billPackageName, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}

}
