package com.subex.automation.testcases.systemtesting.etl;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.screens.CollectedFilesHelper;
import com.subex.automation.helpers.application.screens.JumpToSearchHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testVerifyResult extends ROCAcceptanceTest {
	
	private static String path = null;
	final String fileName = "ETLFlow_TestData.xlsx";
	final String sheetName = "ETLFlow";
	
	public testVerifyResult() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\System_Test_Flows\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="Verify Jump to Search for ASCII parsed data", dependsOnGroups = { "run" }, groups = { "verify" })
	public void verifyASCIIJ2S() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ASCIIJ2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="Verify Jump to Search for ASN1 parsed data", dependsOnMethods = { "verifyASCIIJ2S" }, groups = { "verify" })
	public void verifyASN1J2S() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyJumpToSearchResult(path, fileName, sheetName, "ASN1J2S", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=3, description="Verify Parse Statistics for ASCII parsed data", dependsOnMethods = { "verifyASN1J2S" }, groups = { "verify" })
	public void verifyASCIIParseStatistics() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.viewParseStatistics(path, fileName, sheetName, "ASCIIParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=4, description="Verify Parse Statistics for ASN1 parsed data", dependsOnMethods = { "verifyASCIIParseStatistics" }, groups = { "verify" })
	public void verifyASN1ParseStatistics() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.viewParseStatistics(path, fileName, sheetName, "ASN1ParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=5, description="Verify Raw Record for ASCII parsed data", dependsOnMethods = { "verifyASN1ParseStatistics" }, groups = { "verify" })
	public void verifyASCIIViewRawRecord() throws Exception {
		try {
			ControllerHelper controller = new ControllerHelper();
			controller.startServerService();
			
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.viewRawRecord(path, fileName, sheetName, "ASCIIViewRawRecord", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=6, description="Verify Raw Record for ASN1 parsed data", dependsOnMethods = { "verifyASN1ParseStatistics" }, groups = { "verify" })
	public void verifyASN1ViewRawRecord() throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.viewRawRecord(path, fileName, sheetName, "ASN1ViewRawRecord", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			ControllerHelper controller = new ControllerHelper();
			controller.stopServerService();
		}
	}
	
	@Test(priority=7, description="Verify Parse Statistics for ASN1 parsed data", dependsOnMethods = { "verifyASN1ViewRawRecord" }, groups = { "verify" })
	public void verifyParseStatistics() throws Exception {
		try {
			CollectedFilesHelper collectedFiles = new CollectedFilesHelper();
			collectedFiles.viewParseStatistics(path, fileName, sheetName, "ParseStatistics", 1);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}