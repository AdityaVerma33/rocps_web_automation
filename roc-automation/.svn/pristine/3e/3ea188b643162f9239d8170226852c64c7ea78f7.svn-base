package com.subex.automation.testcases.regressiontesting.usermanagement.usermgnt_issues;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.util.FailureHelper;

public class test161864 extends testUserManagement {
	
	final String sheetName = "test161864";
	
	public test161864() throws Exception {
		super();
	}
	
	@Test(priority=1, description="Test case 1 of Bug 161864")
	public void testCase1() throws Exception {
		try {
			// Validate that creating a new role with all the privileges works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "RolesAllPrivilege", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Test case 2 of Bug 161864")
	public void testCase2() throws Exception {
		try {
			// Validate that creating a new role with some privileges works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "RolesSomePrivilege", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Test case 3 of Bug 161864")
	public void testCase3() throws Exception {
		try {
			// Validate that editing an existing role and providing all the privileges works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "EditRoleAll", 1);
			
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "EditRoleAll", 1 );
			String partition = excelData.get("Partition").get(0);
			String roleName = excelData.get("Role Name").get(0);
			
			roles.navigateToRoles(roleName, partition);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			roles.assignRolePrivileges(true, null, null, null, null, null, null);
			roles.assignReferenceTables(true, null, null);
			roles.saveRoles(roleName, detailScreenTitle);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Test case 4 of Bug 161864")
	public void testCase4() throws Exception {
		try {
			// Validate that editing an existing role and providing some privileges works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "EditRoleSome", 1);
			roles.createRole(path, fileName, sheetName, "EditRoleSome", 2);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Test case 5 of Bug 161864")
	public void testCase5() throws Exception {
		try {
			// Validate that editing an existing role and clicking on Save without change works fine
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "EditRole", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}