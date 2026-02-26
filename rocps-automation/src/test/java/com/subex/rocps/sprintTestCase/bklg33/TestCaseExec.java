package com.subex.rocps.sprintTestCase.bklg33;

import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TestCaseExec extends PSAcceptanceTest {

	
	
	@org.testng.annotations.Test(priority = 4)
	public void RateSheetImportFuturePeriod() throws Exception {

		try {
			String Path;
			String WorkbookName;
			String sheetName;
			
			String testCaseName;
			Path = System.getProperty("user.dir") + "\\src\\main\\resources\\";
			WorkbookName = "TestData.xlsx";
			sheetName = "RateSheetFutureDate_Sheet6";
			testCaseName = "RatesheetImportFuturePeriodUpdate";
			ratesheetImportFuturePeriodUpdate ratesheetimpobj = new ratesheetImportFuturePeriodUpdate();
			ratesheetimpobj.rateSheetFuturePeriodUpdate(Path, WorkbookName, sheetName, testCaseName);

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}

	}
	 

	
	
}
