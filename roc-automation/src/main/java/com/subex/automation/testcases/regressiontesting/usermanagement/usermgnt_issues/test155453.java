package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test155453 extends testUserManagement {
	
	final String sheetName = "test155453";
	static ArrayList<String> expectedGroupNames = null;
	static String[][] expectedScreenNames = null;
	static String[] expectedScreens = null;
	
	final String[] groupNames = {"Admin", "Monitoring", "System", "Security"};
	final boolean[] selectAllScreens = {false, false, false, false};
	final String[][] screenNames = {{"Table Definition", "Table Instance", "Entity Export"},
							{"Task", "Collected File"},
							{"File Source", "File Collection", "Recurring Task"},
							{"User", "Role"}};
	
	public test155453() throws Exception {
		super();
	}
	
	private void navigateToNewRole() throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Roles", "Role Search" );
			NavigationHelper.navigateToNew("Common", "Roles_RoleName");
			
			TextBoxHelper.type("Roles_RoleName", "Test_Role");
			TabHelper.gotoTab("Role Privileges");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void navigateToEditRole() throws Exception {
		try {
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "Roles", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			roles.navigateToRoles(roleName, partition);
			TabHelper.gotoTab("Role Privileges");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getScreens() throws Exception {
		try {
			ArrayList<String> screens = GridHelper.getColumnValues("Roles_Privileges_Screens_Grid", "screens");
			int screenLength = screens.size();
			expectedScreens = new String[screenLength];
			
			for (int i = 0; i < screenLength; i++)
				expectedScreens[i] = screens.get(i);
			
			return expectedScreens;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void getGroupsAndScreens() throws Exception {
		try {
			expectedGroupNames = GridHelper.getColumnValues("Roles_Privileges_Group_Grid", "Screens");
			int groupLength = expectedGroupNames.size();
			expectedScreenNames = new String[groupLength][];
			
			for (int i = 0; i < groupLength; i++) {
				GridHelper.clickRow("Roles_Privileges_Group_Grid", expectedGroupNames.get(i), "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				ArrayList<String> screens = GridHelper.getColumnValues("Roles_Privileges_Screens_Grid", "screens");
				int screenLength = screens.size();
				expectedScreenNames[i] = new String[screenLength];
				
				for (int j = 0; j < screenLength; j++)
					expectedScreenNames[i][j] = screens.get(j);
			}
			
			GridHelper.click("Roles_Privileges_Group_Grid");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkUnassignedScreens(String[] expectedScreens, String[] screenNames) throws Exception {
		try {
			for (int i = 0; i < expectedScreens.length; i++) {
				int index = StringHelper.searchArray(screenNames, expectedScreens[i]);
				
				if (index >= 0)
					assertFalse(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreens[i], "screens"));
				else
					assertTrue(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreens[i], "screens"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkUnassignedScreens(String[] groupNames, String[][] screenNames) throws Exception {
		try {
			for (int i = 0; i < expectedGroupNames.size(); i++) {
				String groupName = expectedGroupNames.get(i);
				GridHelper.clickRow("Roles_Privileges_Group_Grid", groupName, "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				boolean isPresent = false;
				
				for (int j = 0; j < groupNames.length; j++) {
					if (groupName.equals(groupNames[j])) {
						isPresent = true;
						for (int k = 0; k < expectedScreenNames[i].length; k++) {
							int index = StringHelper.searchArray(screenNames[j], expectedScreenNames[i][k]);
							
							if (index >= 0)
								assertFalse(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][k], "screens"));
							else
								assertTrue(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][k], "screens"));
						}
					}
				}
				
				if (!isPresent) {
					for (int j = 0; j < expectedScreenNames[i].length; j++) {
						assertTrue(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][j], "screens"));
					}
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
	
	private void checkAssignedScreens(String[] expectedScreens, String[] screenNames) throws Exception {
		try {
			for (int i = 0; i < expectedScreens.length; i++) {
				int index = StringHelper.searchArray(screenNames, expectedScreens[i]);
				
				if (index >= 0)
					assertTrue(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreens[i], "screens"));
				else
					assertFalse(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreens[i], "screens"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void checkAssignedScreens(String[] groupNames, String[][] screenNames) throws Exception {
		try {
			for (int i = 0; i < expectedGroupNames.size(); i++) {
				String groupName = expectedGroupNames.get(i);
				GridHelper.clickRow("Roles_Privileges_Group_Grid", groupName, "Screens");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				boolean isPresent = false;
				
				for (int j = 0; j < groupNames.length; j++) {
					if (groupName.equals(groupNames[j])) {
						isPresent = true;
						for (int k = 0; k < expectedScreenNames[i].length; k++) {
							int index = StringHelper.searchArray(screenNames[j], expectedScreenNames[i][k]);
							
							if (index >= 0)
								assertTrue(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][k], "screens"));
							else
								assertFalse(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][k], "screens"));
						}
					}
				}
				
				if (!isPresent) {
					for (int j = 0; j < expectedScreenNames[i].length; j++) {
						assertFalse(GridHelper.isValuePresent("Roles_Privileges_Screens_Grid", expectedScreenNames[i][j], "screens"));
					}
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
	
	@Test(priority=1, description="Test case 1 of Bug 155453")
	public void testCase1() throws Exception {
		try {
			// In New Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a group
			navigateToNewRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			getScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			checkUnassignedScreens(expectedScreens, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 155453")
	public void testCase2() throws Exception {
		try {
			// In New Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a different groups
			navigateToNewRole();
			
			getGroupsAndScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			ArrayList<String> actualGroupNames = GridHelper.getColumnValues("Roles_Privileges_Group_Grid", "Screens");
			assertEquals(actualGroupNames, expectedGroupNames, "Group Names are not matching.");
			
			checkUnassignedScreens(groupNames, screenNames);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 155453")
	public void testCase3() throws Exception {
		try {
			// In New Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a group followed by another group and then the first group
			navigateToNewRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens1 = getScreens();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens2 = getScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens1, screenNames[0]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens2, screenNames[2]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens1, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 155453")
	public void testCase4() throws Exception {
		try {
			// In New Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a group
			navigateToNewRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			getScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			checkAssignedScreens(expectedScreens, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test case 5 of Bug 155453")
	public void testCase5() throws Exception {
		try {
			// In New Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a different groups
			navigateToNewRole();
			
			getGroupsAndScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			ArrayList<String> actualGroupNames = GridHelper.getColumnValues("Roles_Privileges_Group_Grid", "Screens");
			assertEquals(actualGroupNames, expectedGroupNames, "Group Names are not matching.");
			
			checkAssignedScreens(groupNames, screenNames);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Test case 3 of Bug 155453")
	public void testCase6() throws Exception {
		try {
			// In New Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a group followed by another group and then the first group
			navigateToNewRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens1 = getScreens();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens2 = getScreens();
			
			RolesHelper roles = new RolesHelper();
			roles.assignRolePrivileges(groupNames, selectAllScreens, screenNames);
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens1, screenNames[0]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens2, screenNames[2]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens1, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=7, description="Test case 7 of Bug 155453")
	public void testCase7() throws Exception {
		try {
			// In Edit Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a group
			navigateToEditRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			getScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			checkUnassignedScreens(expectedScreens, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=8, description="Test case 8 of Bug 155453")
	public void testCase8() throws Exception {
		try {
			// In Edit Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a different groups
			navigateToEditRole();
			
			getGroupsAndScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			ArrayList<String> actualGroupNames = GridHelper.getColumnValues("Roles_Privileges_Group_Grid", "Screens");
			assertEquals(actualGroupNames, expectedGroupNames, "Group Names are not matching.");
			
			checkUnassignedScreens(groupNames, screenNames);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=9, description="Test case 9 of Bug 155453")
	public void testCase9() throws Exception {
		try {
			// In Edit Roles screen, when "Show unassigned privileges" option is checked,
			// validate that only unassigned screens are visible on selecting a group followed by another group and then the first group
			navigateToEditRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens1 = getScreens();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens2 = getScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowUnassignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens1, screenNames[0]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens2, screenNames[2]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkUnassignedScreens(expectedScreens1, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=10, description="Test case 10 of Bug 155453")
	public void testCase10() throws Exception {
		try {
			// In Edit Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a group
			navigateToEditRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			getScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			checkAssignedScreens(expectedScreens, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=11, description="Test case 11 of Bug 155453")
	public void testCase11() throws Exception {
		try {
			// In Edit Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a different groups
			navigateToEditRole();
			
			getGroupsAndScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			ArrayList<String> actualGroupNames = GridHelper.getColumnValues("Roles_Privileges_Group_Grid", "Screens");
			assertEquals(actualGroupNames, expectedGroupNames, "Group Names are not matching.");
			
			checkAssignedScreens(groupNames, screenNames);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=12, description="Test case 12 of Bug 155453")
	public void testCase12() throws Exception {
		try {
			// In Edit Roles screen, when "Show assigned privileges" option is checked,
			// validate that only assigned screens are visible on selecting a group followed by another group and then the first group
			navigateToEditRole();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens1 = getScreens();
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String[] expectedScreens2 = getScreens();
			GridHelper.click("Roles_Privileges_Group_Grid");
			
			CheckBoxHelper.check("Roles_ShowAssignedPrivileges");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens1, screenNames[0]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "System", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens2, screenNames[2]);
			
			GridHelper.clickRow("Roles_Privileges_Group_Grid", "Admin", "Screens");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			checkAssignedScreens(expectedScreens1, screenNames[0]);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}