package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test145550 extends testUserManagement {
	
	final String sheetName = "test145550";
	
	public test145550() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 145550")
	public void testCase1() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel clicking on "Select All" action
			// should select all the actions under that screen
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.navigateToNew("Common", "Roles_RoleName");
			
			TabHelper.gotoTab("Role Privileges");
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			RolesHelper roles = new RolesHelper();
			String[][] actions = roles.getScreenActions();
			
			ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 1; j < actions[i].length; j++) {
					assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' is not checked.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 145550")
	public void testCase2() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel, select some of the actions and then click on "Select All" action.
			// All the actions under that screen should get selected
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.navigateToNew("Common", "Roles_RoleName");
			
			TabHelper.gotoTab("Role Privileges");
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			RolesHelper roles = new RolesHelper();
			String[][] actions = roles.getScreenActions();
			TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[0][0], "Browse");
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 2; j < actions[i].length; j+=2) {
					TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
					assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' did not get checked.");
				}
			}
			
			ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 1; j < actions[i].length; j++) {
					assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' is not checked.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 145550")
	public void testCase3() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel clicking on "Deselect All" action
			// should deselect all the actions under that screen
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.navigateToNew("Common", "Roles_RoleName");
			
			TabHelper.gotoTab("Role Privileges");
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			RolesHelper roles = new RolesHelper();
			String[][] actions = roles.getScreenActions();
			
			ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ButtonHelper.click("Roles_Privileges_Actions_DeselectAll");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 1; j < actions[i].length; j++) {
					assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' is checked.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 145550")
	public void testCase4() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel, select some of the actions and then click
			// on "Deselect All" action. All the actions under that screen should get deselected
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.navigateToNew("Common", "Roles_RoleName");
			
			TabHelper.gotoTab("Role Privileges");
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			RolesHelper roles = new RolesHelper();
			String[][] actions = roles.getScreenActions();
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 1; j < actions[i].length; j++) {
					TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
				}
			}
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 2; j < actions[i].length; j+=2) {
					TreeHelper.unCheckCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
					assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' did not get unchecked.");
				}
			}
			
			ButtonHelper.click("Roles_Privileges_Actions_DeselectAll");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			for (int i = 0; i < actions.length; i++) {
				for (int j = 1; j < actions[i].length; j++) {
					assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
							"Action '" + actions[i][j] + "' is checked.");
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test case 5 of Bug 145550")
	public void testCase5() throws Exception {
		try {
			// In Edit Roles screen, verify if in Role Privileges tab > Actions panel clicking on "Select All" action
			// should select all the actions under that screen
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			int row = roles.navigateToRoles(roleName, partition);
			
			if (row > 0) {
				TabHelper.gotoTab("Role Privileges");
				GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				String[][] actions = roles.getScreenActions();
				
				ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' is not checked.");
					}
				}
			}
			else {
				FailureHelper.failTest("Role '" + roleName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test case 6 of Bug 145550")
	public void testCase6() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel, select some of the actions and then click on "Select All" action.
			// All the actions under that screen should get selected
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			int row = roles.navigateToRoles(roleName, partition);
			
			if (row > 0) {
				TabHelper.gotoTab("Role Privileges");
				GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				String[][] actions = roles.getScreenActions();
				TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[0][0], "Browse");
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 2; j < actions[i].length; j+=2) {
						TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
						assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' did not get checked.");
					}
				}
				
				ButtonHelper.click("Roles_Privileges_Actions_SelectAll");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						assertTrue(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' is not checked.");
					}
				}
			}
			else {
				FailureHelper.failTest("Role '" + roleName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Test case 7 of Bug 145550")
	public void testCase7() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel clicking on "Deselect All" action
			// should deselect all the actions under that screen
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			int row = roles.navigateToRoles(roleName, partition);
			
			if (row > 0) {
				TabHelper.gotoTab("Role Privileges");
				GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				String[][] actions = roles.getScreenActions();
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
					}
				}
				
				ButtonHelper.click("Roles_Privileges_Actions_DeselectAll");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' is checked.");
					}
				}
			}
			else {
				FailureHelper.failTest("Role '" + roleName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Test case 8 of Bug 145550")
	public void testCase8() throws Exception {
		try {
			// In New Roles screen, verify if in Role Privileges tab > Actions panel, select some of the actions and then click
			// on "Deselect All" action. All the actions under that screen should get deselected
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			int row = roles.navigateToRoles(roleName, partition);
			
			if (row > 0) {
				TabHelper.gotoTab("Role Privileges");
				GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GridHelper.clickRow("Roles_Privileges_Screens_Grid", "File Source", "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				String[][] actions = roles.getScreenActions();
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						TreeHelper.checkCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
					}
				}
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 2; j < actions[i].length; j+=2) {
						TreeHelper.unCheckCheckBox("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]);
						assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' did not get unchecked.");
					}
				}
				
				ButtonHelper.click("Roles_Privileges_Actions_DeselectAll");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				for (int i = 0; i < actions.length; i++) {
					for (int j = 1; j < actions[i].length; j++) {
						assertFalse(TreeHelper.isChecked("Roles_Privileges_Actions_Tree", actions[i][0], actions[i][j]),
								"Action '" + actions[i][j] + "' is checked.");
					}
				}
			}
			else {
				FailureHelper.failTest("Role '" + roleName + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}