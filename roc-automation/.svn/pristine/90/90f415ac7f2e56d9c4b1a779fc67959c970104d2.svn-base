package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class StreamControllerHelper extends ROCAcceptanceTest {
	
	public void createStreamController(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String scName = excelData.get("Name").get(i);
				String[] propertyName = testData.getStringValue(excelData.get("Property Name").get(i), firstLevelDelimiter);
				String[] propertyValue = testData.getStringValue(excelData.get("Property Value").get(i), firstLevelDelimiter);
				
				createStreamController(partition, scName, propertyName, propertyValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createStreamController(String partition, String scName, String[] propertyName, String[] propertyValue) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Controllers", "Controller Search" );
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			NavigationHelper.navigateToAction("Stream Controller", "Configure");
			NavigationHelper.selectPartition(partition);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement("StreamController_Name", detailScreenWaitSec);
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("StreamController_Name", scName);
			setProperties(propertyName, propertyValue);
			
			saveStreamController(scName, detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void setProperties(String[] propertyName, String[] propertyValue) throws Exception {
		try {
			for (int i = 0; i < propertyName.length; i++) {
				switch (propertyName[i]) {
				case "Client Listen Port *":
				case "Controller Listen Port *":
				case "External Alert Poller Timeout (seconds) *":
				case "Max Tasks Per Stream Stage Poll *":
				case "Pending Task Poller Timeout (seconds) *":
				case "Process Executable":
				case "Task Controller Connect Timeout (seconds) *":
				case "Task Delegator Timeout (seconds) *":
				case "Task Poller Timeout (seconds) *":
					PropertyGridHelper.typeInTextBox(propertyName[i], propertyValue[i]);
					break;
				
				case "Machine *":
					PropertyGridHelper.selectInComboBox(propertyName[i], propertyValue[i]);
					break;
					
				case "Process Chain Task Log Output Directory *":
					PropertyGridHelper.typeInDataDir(propertyName[i], propertyValue[i], secondLevelDelimiter);
					break;
					
				case "Use SSL communication":
					if (ValidationHelper.isTrue(propertyValue[i]))
						PropertyGridHelper.checkCheckBox(propertyName[i]);
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveStreamController(String scName, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Stream Controller save did not happen.");
			
			if (detailScreenTitle.startsWith("Edit"))
				Log4jHelper.logInfo("Stream Controller '" + scName + "' updated.");
			else
				Log4jHelper.logInfo("Stream Controller '" + scName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}