package com.subex.automation.testcases.regressiontesting.rocra.zen;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.rocra.ZenDefinitionHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureRequest;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testWithoutExactMatch extends ROCAcceptanceTest {
	
	String path = null;
	final String fileName = "Zen_TestData.xlsx";
	final String sheetName = "WithoutExactMatch";
	
	public testWithoutExactMatch() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="uncheck _exact _match _in data_match measure_request")
	public void testZenWithoutUnderMatch() throws Exception {
		try {
			// uncheck _exact _match _in data_match measure_request
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
		}
	}
}