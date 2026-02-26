package com.subex.automation.testcases.regressiontesting.rocra.zen;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.rocra.ZenDefinitionHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureHelper;
import com.subex.automation.helpers.application.screens.DataMatchMeasureRequest;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class testMinimumUnderMatch extends ROCAcceptanceTest {
	
	static String path = null;
	
	final String fileName = "Zen_TestData.xlsx";
	final String sheetName = "MinUnderMatch";
	
	public testMinimumUnderMatch() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=1, description="data _match _results not matching with Min_undermatch _percentage")
	public void testMinimumUnderMatchNotMatching() throws Exception {
		try {
			// data _match _results not matching with Min_undermatch _percentage
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			
			DataMatchMeasureRequest measureRequest = new DataMatchMeasureRequest();
			measureRequest.createMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			
			ZenDefinitionHelper zenDfn = new ZenDefinitionHelper();
			zenDfn.createZenDefinition(path, fileName, sheetName, "ZenDefinition1", 1);
			
			measureRequest.scheduleMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			measureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
			measureRequest.verifyZenResult(path, fileName, sheetName, "VerifyZenResult1", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test(priority=2, description="data _match _results  matching with Min_undermatch _percentage")
	public void testMinimumUnderMatchMatching() throws Exception {
		try {
			// data _match _results  matching with Min_undermatch _percentage
			DataMatchMeasureHelper dataMatchMeasure = new DataMatchMeasureHelper();
			dataMatchMeasure.createDataMatchMeasure(path, fileName, sheetName, "DataMatchMeasure", 1);
			
			DataMatchMeasureRequest measureRequest = new DataMatchMeasureRequest();
			measureRequest.createMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			
			measureRequest.scheduleMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			measureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);			
			
			ZenDefinitionHelper zenDfn = new ZenDefinitionHelper();
			zenDfn.createZenDefinition(path, fileName, sheetName, "ZenDefinition2", 1);
			
			measureRequest.scheduleMeasureRequest(path, fileName, sheetName, "DataMatchMeasureRequest", 1);
			measureRequest.verifyMeasureRequest(path, fileName, sheetName, "VerifyMeasureRequest", 1);
			measureRequest.verifyZenResult(path, fileName, sheetName, "VerifyZenResult2", 1);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}