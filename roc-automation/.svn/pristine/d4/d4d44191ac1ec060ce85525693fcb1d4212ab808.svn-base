package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class test150466 extends testUserManagement {
	
	final String sheetName = "test150466";
	
	public test150466() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 150466")
	public void testCase1() throws Exception {
		try {
			// When Administrator Role is opened in view mode, none of the options in the screen should be editable and Save should be disabled
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			UserHelper users = new UserHelper();
			users.createUser(path, fileName, sheetName, "Users", 1);
			
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			String roleName = "Administrator";
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Roles_RoleName", roleName, "Name");
			GridHelper.clickRow("SearchGrid", row, 1);
			NavigationHelper.navigateToAction("Common Tasks", "View");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("Roles_RoleName", detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("View Role"));
			
			assertTrue(TextBoxHelper.isDisabled("Roles_RoleName"));
			assertTrue(CheckBoxHelper.isDisabled("Roles_ShowAssignedPrivileges"));
			assertTrue(CheckBoxHelper.isDisabled("Roles_ShowUnassignedPrivileges"));
			
			String[] properties = {"Administrator Role", "Edit Administrator Role Properties", "Edit Administrator User Properties",
					"View data without masking"};
			for (int i = 0; i < properties.length; i++)
				PropertyGridHelper.isDisabled(properties[i]);
			
			TabHelper.gotoTab("Role Privileges");
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Group_SelectAll"));
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Group_DeselectAll"));
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Screens_SelectAll"));
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Screens_DeselectAll"));
			GridHelper.clickRow("Roles_Privileges_Screens_Grid", "Day Group", "screens");
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Actions_SelectAll"));
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Actions_DeselectAll"));
			String[] actions = {"Browse", "New", "Edit", "Delete", "Export"};
			for (int i = 0; i < actions.length; i++)
				assertTrue(TreeHelper.isCheckBoxDisabled("Roles_Privileges_Actions_Tree", "Common Tasks", actions[i]));
			
			TabHelper.gotoTab("Reference Tables");
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Group_SelectAll"));
			assertTrue(ButtonHelper.isDisabled("Roles_Privileges_Group_DeselectAll"));
			int rowNum = GridHelper.getRowNumber("Roles_ReferenceTable_Grid", "Alert Severity", "Reference Table");
			for (int i = 0; i < actions.length; i++) {
				if (GridCheckBoxHelper.isChecked("Roles_ReferenceTable_Grid", rowNum, actions[i])) {
					GridHelper.updateGridCheckBox("Roles_ReferenceTable_Grid", rowNum, actions[i], false);
					assertTrue(GridCheckBoxHelper.isChecked("Roles_ReferenceTable_Grid", rowNum, actions[i]));
				}
				else {
					GridHelper.updateGridCheckBox("Roles_ReferenceTable_Grid", rowNum, actions[i], true);
					assertFalse(GridCheckBoxHelper.isChecked("Roles_ReferenceTable_Grid", rowNum, actions[i]));
				}
			}
			
			assertTrue(ButtonHelper.isDisabled("SaveButton"));
			assertFalse(ButtonHelper.isPresent("CancelButton"));
			assertTrue(ButtonHelper.isPresent("CloseButton"));
			
			ButtonHelper.click("CloseButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("View Role"));
			assertTrue(LabelHelper.isTitlePresent("Role Search"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}