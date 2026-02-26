package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class WorkflowHelper extends ROCAcceptanceTest{
	
	public void createWorkflow(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String initialStep = excelData.get("Initial Step").get(i);
				
				String[] fromStep = testData.getStringValue(excelData.get("From Step").get(i), firstLevelDelimiter);
				String[] toStep = testData.getStringValue(excelData.get("To Step").get(i), firstLevelDelimiter);
				
				createWorkflow(partition, name, initialStep, fromStep, toStep);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createWorkflow(String partition, String name, String initialStep, String[] fromStep, String[] toStep) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Workflows", "Workflow Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Workflow_Name", name, "Name");
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Workflow", "Workflow_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("Workflow_Name", name);
			ComboBoxHelper.select("Workflow_InitialStep", initialStep);
			String gridId = "Workflow_StepTransition_Grid";
			
			for (int i = 0; i < fromStep.length; i++) {
				int rowNum = -1;
				int[] rowNums = GridHelper.getRowNumbers(gridId, fromStep[i], "From");
				
				if (ValidationHelper.isNotEmpty(rowNums)) {
					for (int j = 0; j < rowNums.length; j++) {
						String toWorkstep = GridHelper.getCellValue(gridId, rowNums[j], "To");
						if (toWorkstep.equals(toStep[i])) {
							rowNum = j + 1;
							break;
						}
					}
				}
				
				if (rowNum == -1) {
					int rows = GridHelper.getRowCount(gridId);
					GridHelper.updateGridComboBox(gridId, "Workflow_StepTransition_From", rows, "From", "To", fromStep[i]);
					GridHelper.updateGridComboBox(gridId, "Workflow_StepTransition_To", rows, "To", "From", toStep[i]);
				}
			}
			
			saveWorkflow(name, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void saveWorkflow(String name, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			
			if (isPresent)
				Log4jHelper.logInfo("Workflow '" + name + "' updated.");
			else
				Log4jHelper.logInfo("Workflow '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}
