package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class FileSourceHelper extends ROCAcceptanceTest {

	public void createFileSource(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String type = excelData.get("Type").get(i);
				String maxConcurrency = excelData.get("Max Concurrent").get(i);
				String stabilitySeconds = excelData.get("Stability Seconds").get(i);
				String[] propertyValues = testData.getStringValue(excelData.get("Property Values").get(i), firstLevelDelimiter);
				
				createFileSource(partition, name, type, maxConcurrency, stabilitySeconds,  propertyValues);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createFileSource(String partition, String name, String type, String maxConcurrency, String stabilitySeconds,  String[] propertyValues) throws Exception {
		try {
			NavigationHelper.navigateToScreen("File Sources", "File Source Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FileSource_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("File Sources '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "FileSource_Name");
				
				TextBoxHelper.type("FileSource_Name", name);
				ComboBoxHelper.select("FileSource_Type", type);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				TextBoxHelper.type("FileSource_MaxCocurrent", maxConcurrency);
				TextBoxHelper.type("FileSource_StabilitySecs", stabilitySeconds);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GenericHelper.waitForAJAXReady(detailScreenWaitSec);
				
				if (type.equals("Local File Source")) {
					PropertyGridHelper.typeInTextBox("Batch Size *", propertyValues[0]);
					PropertyGridHelper.typeInDataDir("Local Directory *", propertyValues[1]);
					
				}
				else if (type.equals("FTP File Source")) {
					PropertyGridHelper.typeInTextBox("Batch Files *", propertyValues[0]);
					PropertyGridHelper.typeInTextBox("FTP Password *", propertyValues[1]);
					PropertyGridHelper.typeInTextBox("FTP Server *", propertyValues[2]);
					PropertyGridHelper.typeInTextBox("FTP Username *", propertyValues[3]);
					PropertyGridHelper.typeInTextBox("Root Directory", propertyValues[4]);		
					PropertyGridHelper.clickCheckBox("Use Passive Mode", propertyValues[5]);
				}
				else if (type.equals("SFTP File Source")) {
					PropertyGridHelper.typeInTextBox("Root Directory", propertyValues[0]);
					PropertyGridHelper.typeInTextBox("SFTP Password *", propertyValues[1]);
					PropertyGridHelper.typeInTextBox("SFTP Server *", propertyValues[2]);
					PropertyGridHelper.typeInTextBox("SFTP Username *", propertyValues[3]);
				}
				
				saveFileSource(name, detailScreenTitle);
			}
		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void reintialiseFileSource(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				reintialiseFileSource(name);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void reintialiseFileSource(String name) throws Exception {
		try {
			NavigationHelper.navigateToScreen("File Sources", "File Source Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("FileSource_Name", name, "Name");

			if (row > 0) {
				GridHelper.clickRow("SearchGrid", row, 1);
				NavigationHelper.navigateToAction("Source Actions", "Reinitialise Source");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Information"));
				assertTrue(PopupHelper.isTextPresent("The selected file source has been successfully reinitialised."));
				
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				Log4jHelper.logWarning("File Source '" + name + "' is reinitialised.");
			}
			else {
				FailureHelper.failTest("File Source '" + name + "' is not found.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveFileSource(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "File Source save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("File Source '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}