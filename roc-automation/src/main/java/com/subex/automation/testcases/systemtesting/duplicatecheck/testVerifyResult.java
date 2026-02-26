package com.subex.automation.testcases.systemtesting.duplicatecheck;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.screens.AlertsHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyResult extends ROCAcceptanceTest {
	String path = null;
	final String fileName = "DuplicateCheckFlow_TestData.xlsx";
	final String sheetName = "DuplicateCheck";
	final String dataFileName = "Duplicate1.dat";
	
	public testVerifyResult() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Verify Alerts", dependsOnGroups = { "run1" }, groups = { "verify" })
	public void verifyDuplicateCheckAlert() throws Exception {
		try {
			AlertsHelper alerts = new AlertsHelper();
			String alertSource = "Duplicate Check Flow - Stream - DC Tag Duplicates";
			String alertText = "Duplicate Record found for File: '" + dataFileName + "'.";
			alerts.verifyAlertInstance("Task", "Today", null, null, "3,013", alertSource, alertText, 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify Result", dependsOnMethods = { "verifyDuplicateCheckAlert" }, groups = { "verify" })
	public void verifyJ2SResult1() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify Result", dependsOnMethods = { "verifyJ2SResult1" }, groups = { "verify" })
	public void verifyJ2SResult2() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 2);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Result", dependsOnMethods = { "verifyJ2SResult2" }, groups = { "verify" })
	public void verifyJ2SResult3() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 3);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify Result", dependsOnMethods = { "verifyJ2SResult3" }, groups = { "verify" })
	public void verifyJ2SResult4() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "J2S", 4);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}