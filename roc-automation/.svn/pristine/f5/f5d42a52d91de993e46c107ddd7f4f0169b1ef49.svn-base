package com.subex.automation.helpers.scripts;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.config.ElementReader;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class WebElementHelper extends ROCAcceptanceTest {
	
	static String[] scriptHeaders = {"TC Name", "Component", "Wrapper ID", "ID or Text or Xpath", "Component Action", "Value"};
	
	@Test
	public void testWebElements() throws Exception {
		LoginHelper login = new LoginHelper();
		login.login();
		
		String path = automationPath + "//src//main//resources";
		String fileName = "ElementsScripts.xlsx";
		ExcelReader excelReader = new ExcelReader();
		HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, "Steps", scriptHeaders );
		
		for(int i=0; i <excelData.get(scriptHeaders[0]).size(); i++) {
			try {
				String testCaseName = excelData.get(scriptHeaders[0]).get(i);
				testReport = ReportHelper.startReport(report, testCaseName);
				
				String component = excelData.get(scriptHeaders[1]).get(i);
				
				if (!component.equals("")) {
					TestDataHelper testData = new TestDataHelper();
					String compWrapper = excelData.get(scriptHeaders[2]).get(i);
					String compIdOrText = excelData.get(scriptHeaders[3]).get(i);
					String componentAction = excelData.get(scriptHeaders[4]).get(i);
					String[] values = testData.updateValue(null, excelData.get(scriptHeaders[5]).get(i), 1, true);
					
					ScriptHelper.updateReportParams(component, compWrapper, compIdOrText, componentAction, values);
					ScriptHelper.performAction(component, compWrapper, compIdOrText, componentAction, values);
					
					String sheetName = NavigationHelper.getScreenTitle();
					if (ValidationHelper.isEmpty(sheetName))
						sheetName = testCaseName;
					sheetName = sheetName.replace(" ", "_");
					if (ValidationHelper.isEmpty(sheetName))
						sheetName = "Sheet_" + (i+1);
					
					ElementReader.getAllElements(sheetName);
				}
				
				ReportHelper.clearStepKeyContent();
				stepName = "";
				ReportHelper.reportSuccess("Step completed.", true, testCaseName);
			}
			catch (Exception e) {
				FailureHelper.setErrorMessage(e);
				ReportHelper.reportFailure(e);
			}
			
			testReport = ReportHelper.endReport(report, testReport);
		}
	}
	
	public void updateReportParams(String category, String screenName, String dataFileName, String sheetName, String tcID) throws Exception {
		try {
			
			if (ValidationHelper.isNotEmpty(category))
				ReportHelper.updateStepKey("Category", "Blue", category);
			
			if (ValidationHelper.isNotEmpty(screenName))
				ReportHelper.updateStepKey("Screen Name", "Blue", screenName);
			
			if (ValidationHelper.isNotEmpty(dataFileName))
				ReportHelper.updateStepKey("Data File Name", "Blue", dataFileName);
			
			if (ValidationHelper.isNotEmpty(sheetName))
				ReportHelper.updateStepKey("Sheet Name", "Blue", sheetName);
			
			if (ValidationHelper.isNotEmpty(tcID))
				ReportHelper.updateStepKey("TC ID", "Blue", tcID);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}