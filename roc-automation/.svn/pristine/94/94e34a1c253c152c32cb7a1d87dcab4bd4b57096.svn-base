package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TriggerHelper extends ROCAcceptanceTest {

	public void createTrigger(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				String stream = excelData.get("Stream").get(i);
				String[] propertyValues = testData.getStringValue(excelData.get("Trigger Properties").get(i), firstLevelDelimiter);
				String[] actionName = testData.getStringValue(excelData.get("Action Name").get(i), firstLevelDelimiter);
				String[] actionType = testData.getStringValue(excelData.get("Action Type").get(i), firstLevelDelimiter);
				String[][] actionProperties = testData.getStringValue(excelData.get("Action Properties").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] inputPins = testData.getStringValue(excelData.get("Input Pins").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createTrigger(partition, name, type, stream, propertyValues, actionName, actionType, actionProperties, inputPins);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTrigger(String partition, String name, String type, String stream, String[] propertyValues, String[] actionName,
			String[] actionType, String[][] actionProperties, String[][] inputPins) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Triggers", "Trigger Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Trigger_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("Trigger '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "Trigger_Name");
				
				TextBoxHelper.type("Trigger_Name", name);
				ComboBoxHelper.select("Trigger_Type", type);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				ComboBoxHelper.select("Trigger_Stream", stream);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (type.equals("Task Completed Trigger")) {
					PropertyGridHelper.selectInComboBox("Poll Stream Stage *", propertyValues[0]);
				}
				else if (type.equals("File Trigger")) {
					PropertyGridHelper.typeInTextBox("File Pattern *", propertyValues[0]);
					PropertyGridHelper.typeInDataDir("Poll Directory *", propertyValues[1], secondLevelDelimiter);
					PropertyGridHelper.typeInTextBox("Trigger Timeout (seconds) *", propertyValues[2]);
					PropertyGridHelper.typeInDataDir("Zero Length File Directory", propertyValues[3], secondLevelDelimiter);
				}
				
				addAction(actionName, actionType, actionProperties, inputPins);
				
				saveTrigger(name, detailScreenTitle);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteTrigger(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				deleteTrigger(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void deleteTrigger(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Triggers", "Trigger Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Trigger_Name", name, "Name");

			if (row > 0) {
				NavigationHelper.delete("SearchGrid", name, "Name");
				Log4jHelper.logWarning("Trigger '" + name + "' is deleted.");
			}
			else {
				FailureHelper.failTest("Trigger '" + name + "' is not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addAction(String[] actionName, String[] actionType, String[][] actionProperties, String[][] inputPins) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(actionName)) {
				for (int i = 0; i < actionName.length; i++) {
					ButtonHelper.click("Trigger_Action_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Action"), "Action popup did not appear.");
					
					ComboBoxHelper.select("Trigger_Action_Type", actionType[i]);
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					Thread.sleep(500);
					TextBoxHelper.type("Trigger_Action_Name", actionName[i]);
					
					switch (actionType[i]) {
						case "Move File Action":
						case "Copy File Action":
							PropertyGridHelper.typeInDataDir("Popup_Panel", "Target Directory *", actionProperties[i][0], configProp.getThirdLevelDelimiter());
							break;
							
						case "Create Task Action":
						case "Create File Task Action":
							PropertyGridHelper.selectInComboBox("Popup_Panel", "Target Stream Stage *", actionProperties[i][0]);
							break;
							
						case "Rename File Action":
							PropertyGridHelper.typeInTextBox("Popup_Panel", "Renamed File Prefix", actionProperties[i][0]);
							PropertyGridHelper.typeInTextBox("Popup_Panel", "Renamed File Suffix", actionProperties[i][1]);
							break;
							
						default:
							break;
					}
					
					addInputPin(inputPins[i][0], inputPins[i][1]);
					
					ButtonHelper.click("Trigger_Action_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addInputPin(String source, String sourcePin) throws Exception {
		try {
			GridHelper.updateGridComboBox("Trigger_InputPins_Grid", "Trigger_InputPins_Source", 1, "Source", "Input Pin", source);
			
			GridHelper.updateGridComboBox("Trigger_InputPins_Grid", "Trigger_InputPins_SourcePin", 1, "Source Pin", "Input Pin", sourcePin);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveTrigger(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Trigger save did not happen.");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("Trigger '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}