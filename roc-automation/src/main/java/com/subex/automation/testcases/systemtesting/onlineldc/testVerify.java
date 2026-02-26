package com.subex.automation.testcases.systemtesting.onlineldc;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerify extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "OnlineLDC_TestData.xlsx";
	final String sheetName = "OnlineLDC";
	final String dataLocation = "/src/main/resources/Data/Online_LDC";
	
	public testVerify() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Verify the LDC Stitched table for the stitched records.", dependsOnGroups={"run"}, groups = { "verify" })
	public void assertionOnlineLdc() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Stop Controllers", dependsOnGroups={"run"}, groups = { "verify" })
	public void stopControllers() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.stopTaskController("Task Controller");
			controller.stopStreamController();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify LDC Directory", dependsOnGroups={"run"}, groups = { "verify" })
	public void verifyLDCDirectory() throws Exception {
		try {
			String dataDir = configProp.getDataDirPath();
			assertTrue(FileHelper.checkDirectoryExists(applicationOS, dataDir + "/Online_LDC"));
			assertTrue(FileHelper.checkDirectoryExists(applicationOS, dataDir + "/Online_LDC/stitched"));
			assertTrue(FileHelper.checkDirectoryExists(applicationOS, dataDir + "/Online_LDC/cachedump"));
			
			assertTrue(FileHelper.checkFileExists(dataDir + "/Online_LDC/cachedump/cacheDump_3_0.dump"));
			assertTrue(FileHelper.checkFileExists(dataDir + "/Online_LDC/cachedump/cacheDump_3_2.dump"));
			assertTrue(FileHelper.checkFileExists(dataDir + "/Online_LDC/cachedump/cacheDump_3_5.dump"));
			
			String fileName = FileHelper.getLastModifiedFile(applicationOS, dataDir + "/Online_LDC/stitched");
			assertTrue(fileName.startsWith("ldc_Online LDC Flow_"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}