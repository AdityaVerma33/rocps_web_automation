package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testTruncateBeforeLoad extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "TruncateBeforeLoad";
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference";
	
	public testTruncateBeforeLoad() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Parse with proper data file")
	public void testParse()throws Exception
	{
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			String dataDir = configProp.getDataDirPath();
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample3.dat", true);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		}
	}
}