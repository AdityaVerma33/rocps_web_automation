package com.subex.automation.testcases.systemtesting.filesequencecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.SettingsHelper;
import com.subex.automation.helpers.application.screens.StreamHelper;
import com.subex.automation.helpers.application.screens.TableDefinitionHelper;
import com.subex.automation.helpers.application.screens.TableInstanceHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testPrerequisite1 extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "FileSequenceCheck_TestData.xlsx";
	final String sheetName = "FileSequenceCheck";
	
	public testPrerequisite1() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Create Table Definition", groups = { "prerequisite1" })
	public void createTD() throws Exception {
		try {
			Log4jHelper.logInfo("Running File Sequence Check Flow");
			ControllerHelper controller = new ControllerHelper();
			controller.stopServices();
			
			SettingsHelper settings = new SettingsHelper();
			settings.updateSettings(path, fileName, sheetName, "UpdateSettings", 1);
			
			StreamHelper stream = new StreamHelper();
			stream.createStream(path, fileName, sheetName, "Stream", 1);
			
			TableDefinitionHelper tableDefinition = new TableDefinitionHelper();
			tableDefinition.importFromDiamond(path, fileName, sheetName, "ImportFromDiamond", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Create Table Instance", dependsOnMethods = { "createTD" }, groups = { "prerequisite1" })
	public void createTableInstance() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.createTableInstance(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Truncate Table Instance", dependsOnMethods = { "createTableInstance" }, groups = { "prerequisite1" })
	public void truncateTable() throws Exception {
		try {
			TableInstanceHelper tableInstance = new TableInstanceHelper();
			tableInstance.truncateTable(path, fileName, sheetName, "TableInstance", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Manual data load into lookup table", dependsOnMethods = { "truncateTable" }, groups = { "prerequisite1" })
	public void manualDataLoad() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.manualDataLoad(path, fileName, sheetName, "ManualDataload", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Set Jump to Search property 'Max no.of days allowed for Jump To Search' to value 90", dependsOnMethods = { "truncateTable" }, groups = { "prerequisite1" })
	public void setJ2SNoOfDaysPropertyTo90Days() throws Exception {
		try {
			ExecuteScript.exeQuery("update property_inst set pri_value = '90' where prd_id = (select prd_id from property_dfn where prd_key like 'SvrMaxJumpSearchDays')");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}