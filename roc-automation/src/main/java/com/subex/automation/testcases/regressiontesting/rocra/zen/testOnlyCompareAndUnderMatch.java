package com.subex.automation.testcases.regressiontesting.rocra.zen;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.ControllerHelper;
import com.subex.automation.helpers.application.rocra.ZenDefinitionHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureRequest;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testOnlyCompareAndUnderMatch extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "Zen_TestData.xlsx";
	final String sheetName = "WithoutUnderMatch";
	
	public testOnlyCompareAndUnderMatch() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="check _only_compare _and under match in data match measure request screen")
	public void testZenOnlyCompareAndUnderMatch() throws Exception {
		try {
			// check _only_compare _and under match in data match measure request screen
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			
			DataMatchMeasureRequest measureRequest = new DataMatchMeasureRequest();
			measureRequest.createMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			
			ZenDefinitionHelper zenDfn = new ZenDefinitionHelper();
			zenDfn.createZenDefinition(path, fileName, sheetName, "ZenDefinition", 1);
			
			measureRequest.scheduleMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			measureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
			measureRequest.verifyZenResult(path, fileName, sheetName, "VerifyZenResult", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
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