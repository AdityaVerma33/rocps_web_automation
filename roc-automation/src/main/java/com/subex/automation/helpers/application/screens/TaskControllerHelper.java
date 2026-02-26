package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TaskControllerHelper extends ROCAcceptanceTest {
	
	public void createTaskController(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String tcName = excelData.get("Name").get(i);
				
				String[] propertyName = testData.getStringValue(excelData.get("Property Name").get(i), firstLevelDelimiter);
				String[] propertyValue = testData.getStringValue(excelData.get("Property Value").get(i), firstLevelDelimiter);
				
				createTaskController(partition, tcName, propertyName, propertyValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTaskController(String partition, String tcName, String[] propertyName, String[] propertyValue) throws Exception {
		try {
			int row = navigateToTaskController(tcName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Task Controller", "TaskController_Name");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("TaskController_Name", tcName);
			
			for (int i = 0; i < propertyName.length; i++) {
				switch (propertyName[i]) {
				case "Controller Id":
				case "Controller Listen Port *":
				case "Max Weightage *":
				case "Process Executable":
				case "Static Max Weightage *":
				case "Stream Controller Connect Timeout (seconds) *":
				case "Stream Controller Ping Interval (Minutes) *":
				case "Task Progress Update Interval (seconds) *":
					PropertyGridHelper.typeInTextBox(propertyName[i], propertyValue[i]);
					break;
				
				case "Machine *":
					PropertyGridHelper.selectInComboBox(propertyName[i], propertyValue[i]);
					break;
					
				case "Task Log Output Directory *":
					PropertyGridHelper.typeInDataDir(propertyName[i], propertyValue[i]);
					break;
					
				default:
					break;
				}
			}
			
			saveTaskController(tcName, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setTaskControllerCapability(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String tcName = excelData.get("Name").get(i);
				String[] stream = testData.getStringValue(excelData.get("Stream").get(i), firstLevelDelimiter);
				String[] streamStage = testData.getStringValue(excelData.get("Stream Stage").get(i), firstLevelDelimiter);
				boolean[] isStatic = testData.getBooleanValue(excelData.get("Is Static").get(i), firstLevelDelimiter);
				String[] capability = testData.getStringValue(excelData.get("Capability").get(i), firstLevelDelimiter);
				String[] weightagePerTask = testData.getStringValue(excelData.get("Weightage Per Task").get(i), firstLevelDelimiter);
				String[] distribution = testData.getStringValue(excelData.get("Distribution").get(i), firstLevelDelimiter);
				
				setTaskControllerCapability(tcName, stream, streamStage, isStatic, capability, weightagePerTask, distribution);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setTaskControllerCapability(String tcName, String[] stream, String[] streamStage, boolean[] isStatic, String[] capability,
			String[] weightagePerTask, String[] distribution) throws Exception {
		try {
			int row = navigateToTaskController(tcName);
			
			if (row == 0) {
				FailureHelper.failTest("Task Controller '" + tcName + "' is not found");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToEdit("SearchGrid", row, "TC_TCCapability_Button");
				assertTrue(LabelHelper.isTitlePresent("Edit Task Controller"));
				
				ButtonHelper.click("TC_TCCapability_Button");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Task Controller Capabilities"), "Task Controller Capabilities popup did not appear.");
				
				setCapability(stream, streamStage, isStatic, capability, weightagePerTask, distribution);
				
				ButtonHelper.click("TC_TCCapability_OK");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitleNotPresent("Task Controller Capabilities"), "Task Controller Capabilities save did not happen.");
				
				saveTaskController(tcName, detailScreenTitle, true);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToTaskController(String tcName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Controllers", "Controller Search" );
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TaskController_Name", tcName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setCapability(String[] stream, String[] streamStage, boolean[] isStatic, String[] capability, String[] weightagePerTask,
			String[] distribution) throws Exception {
		try {
			SearchHelper searchHelper = new SearchHelper();
			
			for (int i = 0; i < stream.length; i++) {
				ButtonHelper.click("Clear Filters");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				searchHelper.clickFilterIcon("TC_TCCapability_Stream_Icon");
				TextBoxHelper.type("TC_TCCapability_Stream", stream[i]);
				searchHelper.clickSearch();
				
				searchHelper.clickFilterIcon("TC_TCCapability_StreamStage_Icon");
				TextBoxHelper.type("TC_TCCapability_StreamStage", streamStage[i]);
				searchHelper.clickSearch();
				
				GridHelper.click("TC_TCCapability_Grid");
				GridHelper.clickRow("TC_TCCapability_Grid", streamStage[i], "Stream Stage");
				
				if (weightagePerTask != null && weightagePerTask.length > i)
					setCapability(isStatic[i], capability[i], weightagePerTask[i], distribution[i]);
				else
					setCapability(isStatic[i], capability[i], null, null);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveTaskController(String tcName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (LabelHelper.isTitlePresent("Reinitialise")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			if (LabelHelper.isTitlePresent("Information")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Task Controller save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", tcName, "Name"), "Value '" + tcName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Task Controller '" + tcName + "' updated.");
			else
				Log4jHelper.logInfo("Task Controller '" + tcName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setCapability(boolean isStatic, String capability, String weightagePerTask, String distribution) throws Exception {
		try {
			ButtonHelper.click("TC_TCCapability_SetCapability");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Set Capability"));
			
			if (isStatic)
				TextBoxHelper.type("TC_TCCapability_Capability", capability);
			else {
				if (weightagePerTask == null || distribution == null) {
					FailureHelper.failTest("Weigtage per Task and Distribution cannot be empty for Dynamic Task Controller Capability.");
				}
				else {
					CheckBoxHelper.uncheck("TC_TCCapability_Static");
					TextBoxHelper.type("TC_TCCapability_WeightagePerTask", weightagePerTask);
					TextBoxHelper.type("TC_TCCapability_Distribution", distribution);
				}
			}
			
			ButtonHelper.click("TC_TCCapability_Capability_OK");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent("Set Capability"), "Set Capability for Stream Stage did not get saved.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}