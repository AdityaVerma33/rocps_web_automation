package com.subex.automation.testcases.regressiontesting.usermanagement;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class testSetAllPrivileges extends testUserManagement {

	final String sheetName = "RootRole";
	
	public testSetAllPrivileges() throws Exception {
		super();
	}
	
	private void verifyActions(int row, boolean shouldbePresent) throws Exception {
		try {
			GridHelper.clickRow("SearchGrid", row, 1);
			
			if (shouldbePresent) {
				assertTrue(NavigationHelper.isActionPresent("Special Actions"));
				NavigationHelper.navigateToAction("Special Actions");
				assertTrue(NavigationHelper.isActionPresent("Set All Privileges"));
				NavigationHelper.navigateToAction("Special Actions");
			}
			else {
				assertTrue(NavigationHelper.isActionPresent("Special Actions"));
				NavigationHelper.navigateToAction("Special Actions");
				assertFalse(NavigationHelper.isActionPresent("Set All Privileges"));
				NavigationHelper.navigateToAction("Special Actions");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Set All Privileges action")
	public void testAction() throws Exception {
		try {
			// Set All Privileges action
			String partition = "Reg P4";
			GenericHelper.insertPartition(partition);
			BrowserHelper browser = new BrowserHelper();
			browser.refresh();
			
			int row = navigateToUsers("Administrator");
			assertTrue(row > 0);
			verifyActions(row, false);
			
			String userName = "Root";
			row = navigateToUsers(userName);
			assertTrue(row > 0);
			verifyActions(row, true);
			
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks");
			assertTrue(NavigationHelper.isActionPresent("Edit"));
			
			NavigationHelper.navigateToAction("Edit");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Users_NewPassword", detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitlePresent("Edit User"));
			assertTrue(TextBoxHelper.isDisabled("Users_Username"));
			assertTrue(TextBoxHelper.isDisabled("Users_NewPassword"));
			assertTrue(TextBoxHelper.isDisabled("Users_UserDisplayName"));
			
			TabHelper.gotoTab("Roles And Partitions");
			String[] columnNames = {"Common", partition};
			int rowNo = GridHelper.getRowNumber("Users_RolePrivileges_Grid", userName);
			
			for (int i = 0; i < columnNames.length; i++) {
				assertTrue(GridCheckBoxHelper.isChecked("Users_RolePrivileges_Grid", rowNo, columnNames[i]));
			}
			
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			if (ButtonHelper.isPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			assertTrue(LabelHelper.isTitlePresent("User Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}