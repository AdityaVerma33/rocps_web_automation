package com.subex.automation.testcases.regressiontesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.application.screens.TaskControllerHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testSkipRecordOnError extends ROCAcceptanceTest {
	
	private static String path = null;
	private static String dataDir = null;
	final String fileName = "ETLRegression_TestData.xlsx";
	final String sheetName = "SkipOnError";
	final String dataLocation = "\\src\\main\\resources\\Data\\ASCII";
	final String folderName = "Reference";
	
	public testSkipRecordOnError() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Parse with error data file")
	public void parseWithErrorData()throws Exception
	{
		try {
			dataDir = configProp.getDataDirPath();
			//Create output&Input directory for parse
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Input/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/Collected Files/" + folderName, true);
			FileHelper.cleanUpDir(applicationOS, dataDir + "/ParseOutput/" + folderName, true);
			
			// Start Controllers
			ControllerHelper controller = new ControllerHelper();
			controller.startStreamController();
			controller.startTaskController(configProp.getTaskControllerExeFile());
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample_Error.dat", "Sample1.dat", true);
			
			TaskControllerHelper taskController = new TaskControllerHelper();
			taskController.setTaskControllerCapability(path, fileName, sheetName, "TCCapability", 1);
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, "ETLTask", 1);
			
			taskSearch.filterOnTree("Reference Stream", "Reference Parse");
			int row = GridHelper.getRowNumber("SearchGrid", "Reference Parse", "Stream Stage");
			String warning = GridHelper.getCellValue("SearchGrid", row, "Warning");
			assertEquals(warning, "Warning");
			
			GridHelper.rightClick("SearchGrid", row, 1);
			Thread.sleep(1000);
			assertTrue(NavigationHelper.isActionPresent("Task Warning"));
			NavigationHelper.navigateToAction("Task Warning");
			assertTrue(NavigationHelper.isActionPresent("View Warning"));
			NavigationHelper.navigateToAction("View Warning");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Warning"));
			assertTrue(PopupHelper.isTextPresent("Task completed with Record Errors (Decoding : 2, Mapping : 0)."));
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			GridHelper.clickRow("SearchGrid", row, 1);
			Thread.sleep(1000);
			NavigationHelper.navigateToAction("Task Details");
			assertTrue(NavigationHelper.isActionPresent("View Rejected Record"));
			ElementHelper.pressEscape();
			taskSearch.clearFilterOnTree();
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
//	, dependsOnMethods = { "parseWithErrorData" }
	@Test(priority=2, description="Parse with proper data file")
	public void parseWithProperData() throws Exception {
		try {
			FileHelper.copyFile(applicationOS, automationPath + dataLocation, dataDir + "/Input/" + folderName, "Data_Load_Sample1.dat", "Sample2.dat", true);
			Thread.sleep(60000);
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskCount(path, fileName, sheetName, "ETLTaskCount", 1);
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
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