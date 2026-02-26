package com.subex.rocps.sprintTestCase.bklg113;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExecNRTRDERoamConf extends PSAcceptanceTest {
	@org.testng.annotations.Test(priority = 4)
	public void newNRTRDERoamingConfiguration() throws Exception {

		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "TestData.xlsx";
			sheetName = "BKLG113_NRTRDERoamConfCreat";
			testCaseName = "NRTRDERoamingConfiguration";
			System.out.println(Path);
			NRTRDERoamingConfiguration nrtrdeobj = new NRTRDERoamingConfiguration(Path, WorkbookName, sheetName, testCaseName);
			nrtrdeobj.newRoamingConfiguration();

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}

	public void editNRTRDERoamingConfiguration() throws Exception {
		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "TestData.xlsx";
			sheetName = "BKLG113_NRTRDERoamConfCreat";
			testCaseName = "NRTRDE Roaming Configuration";
			NRTRDERoamingConfiguration nrtrdeobj = new NRTRDERoamingConfiguration(Path, WorkbookName, sheetName, testCaseName);
			nrtrdeobj.editRoamingConfiguration();
			;
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}

}
