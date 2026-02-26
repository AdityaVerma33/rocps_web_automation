package com.subex.automation.testcases.systemtesting.rocflows;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.ROCFlowHelper;
import com.subex.automation.helpers.application.screens.RolesHelper;
import com.subex.automation.helpers.application.screens.UserHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testROCFlows extends ROCAcceptanceTest
{
	private static String path = null;
	final String fileName = "ROCFlows_TestData.xlsx";
	final String sheetName = "ROCFlows";
	
	public testROCFlows() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create ROC Flow", groups = { "ROCFlows" })
	public void createROCFlow() throws Exception {	
		try {
			Log4jHelper.logInfo("Running ROC Flows Flow");
			ROCFlowHelper rocFlow = new ROCFlowHelper();
			rocFlow.createROCFlow(path, fileName, sheetName, "AddROCFlow", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Edit ROC Flow", dependsOnMethods={"createROCFlow"}, groups = { "ROCFlows" })
	public void editROCFlow() throws Exception {	
		try {
			ROCFlowHelper rocFlow = new ROCFlowHelper();
			rocFlow.editROCFlow(path, fileName, sheetName, "EditROCFlow", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Create Role", dependsOnMethods={"editROCFlow"}, groups = { "ROCFlows" })
	public void createRole() throws Exception {	
		try {
			RolesHelper roles = new RolesHelper();
			roles.createRole(path, fileName, sheetName, "Roles", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Create User", dependsOnMethods={"createRole"}, groups = { "ROCFlows" })
	public void createUser() throws Exception {	
		try {
			UserHelper user = new UserHelper();
			user.createUser(path, fileName, sheetName, "User", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify ROC Flow Validation", dependsOnMethods={"createUser"}, groups = { "ROCFlows" })
	public void verifyROCFlowValidation() throws Exception {	
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, "ROCFlow", 1 );
			String partition = excelData.get("Partition").get(0);
			String name = excelData.get("Name").get(0);
			
			NavigationHelper.navigateToScreen("ROC Flows", "ROC Flows Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("ROCFlows_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("ROC Flow '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "ROCFlows_Name");
				
				ROCFlowHelper rocFlow = new ROCFlowHelper();
				rocFlow.updateROCFlow(path, fileName, sheetName, "ROCFlow", 1);
				
				ButtonHelper.click("SaveButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(detailScreenTitle));
				assertTrue(LabelHelper.isTextPresent("no ROC Flow Toolbar Items are configured"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Validate ROC Flow", dependsOnMethods={"createUser"}, groups = { "ROCFlows" })
	public void validateROCFlow() throws Exception {
		ROCFlowHelper rocFlow = new ROCFlowHelper();
		
		try {
			LoginHelper login = new LoginHelper();
			login.login(path, fileName, sheetName, "UserLogin", 1);
			
			rocFlow.validateROCFlow(path, fileName, sheetName, "ValidateROCFlow", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			LoginHelper login = new LoginHelper();
			login.loginWithConfigPropertyDetails();
			
			rocFlow.deleteROCFlow(path, fileName, sheetName, "AddROCFlow", 1);
		}
	}
}