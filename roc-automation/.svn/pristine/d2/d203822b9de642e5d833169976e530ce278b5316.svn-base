package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class SettingsHelper extends ROCAcceptanceTest {
	
	public void updateSettings(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Tab Name").size(); i++) 
			{
				String[] tabName = testData.getStringValue(excelData.get("Tab Name").get(i), firstLevelDelimiter);
				String[][] propertyName = testData.getStringValue(excelData.get("Property Name").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] propertyValue = testData.getStringValue(excelData.get("Property Value").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateSettings(tabName, propertyName, propertyValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateSettings(String[] tabName, String[][] propertyName, String[][] propertyValue) throws Exception
	{
		try {
			navigateToSettings();
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			for (int i = 0; i < tabName.length; i++) {
				TabHelper.gotoTab(tabName[i]);
				String wrapperID = getWrapperID(tabName[i]);
				
				if (ValidationHelper.isNotEmpty(propertyName) && ValidationHelper.isNotEmpty(propertyName[i])) {
					for (int j = 0; j < propertyName[i].length; j++) {
						PropertyGridHelper.updateProperty(wrapperID, propertyName[i][j], propertyValue[i][j], configProp.getThirdLevelDelimiter());
					}
				}
			}
			
			saveSettings(detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getWrapperID(String tabName) throws Exception {
		try {
			int index = tabName.indexOf(" ");
			String temp1 = tabName.substring(0, index);
			String temp2 = tabName.substring(index+1);
			String wrapperID = temp1.toLowerCase().trim() + temp2.trim();
			
			return wrapperID;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void navigateToSettings() throws Exception {
		try {
			NavigationHelper.navigateToScreen("Settings", "Edit Settings");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElement("Settings_OK", searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveSettings(String detailScreenTitle) throws Exception {
		try {
			if (ButtonHelper.isPresent("Settings_OK")) {
				ButtonHelper.click("Settings_OK");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Settings save did not happen.");
				Log4jHelper.logInfo("Settings updated");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void closeSettings() throws Exception {
		try {
			if (ButtonHelper.isPresent("Settings_Cancel")) {
				ButtonHelper.click("Settings_Cancel");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				assertFalse(ButtonHelper.isPresent("Settings_Cancel"), "Settings close did not happen.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}