package com.subex.rocps.sprintTestCase.bklg173;

import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest {

	
	
	@org.testng.annotations.Test(priority = 4)
	public void RateSheet() throws Exception {

		try {
			String Path;
			String WorkbookName;
			String sheetName;
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "TestData.xlsx";
			sheetName = "RatesheetImport_sheet5";
			testCaseName = "RatesheetImport";
			ratesheetImport ratesheetobj = new ratesheetImport();
			ratesheetobj.rateSheet(Path, WorkbookName, sheetName, testCaseName);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}
	 

	

}
