package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TariffClassHelper extends ROCAcceptanceTest {
	
	public void createTariffClass(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String tariffType = excelData.get("Tariff Type").get(i);
				String metricType = excelData.get("Metric Type").get(i);
				String[] elementSets = testData.getStringValue(excelData.get("Element Set").get(i), firstLevelDelimiter);
				String[] elementSetTypes = testData.getStringValue(excelData.get("Element Set Type").get(i), firstLevelDelimiter);
				String[] dayGroups = testData.getStringValue(excelData.get("Day Groups").get(i), firstLevelDelimiter);
				String[] bands = testData.getStringValue(excelData.get("Bands").get(i), firstLevelDelimiter);
				
				createTariffClass(partition, name, tariffType, metricType, elementSets, elementSetTypes, dayGroups, bands);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createTariffClass (String partition, String name, String tariffType, String metricType, String[] elementSets,
			String[] elementSetTypes, String[] dayGroups, String[] bands) throws Exception {
		try{
			NavigationHelper.navigateToScreen("Tariff Classes", "Tariff Class Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("TariffClass_Name", name, "Name");

			if (row >0 ){
				Log4jHelper.logWarning("Tariff Classe '" + name + " ' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "TariffClass_Name");
				
				TextBoxHelper.type("TariffClass_Name", name);
				ComboBoxHelper.select("TariffClass_TariffType", tariffType);
				ComboBoxHelper.select("TariffClass_MetricType", metricType);

				TariffHelper tariff = new TariffHelper();
				tariff.addElementSets("TariffClass_ElementSet_Grid", "TariffClass_ElementSet_Add", elementSets, elementSetTypes);
				
				addTariffClassDayGroup(dayGroups);
				addBands(bands);
				
				saveTariffClass(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addTariffClassDayGroup(String[] dayGroups) throws Exception {
		try {
			int rows = GridHelper.getRowCount("TariffClass_DayGroup_Grid");
			rows++;
			
			for (int i = 0; i < dayGroups.length; i++){
				int row = GridHelper.getRowNumber("TariffClass_DayGroup_Grid", dayGroups[i], "Day Group");
				
				if (row == 0) {
					MouseHelper.click("TariffClass_DayGroup_Add");
					Thread.sleep(100);
					GridHelper.updateGridComboBox("TariffClass_DayGroup_Grid", "TariffClass_DayGroup_DayGroup", rows, "Day Group", "Index", dayGroups[i]);
					rows++;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}


	public void addBands(String[] bands) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(bands)) {
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < bands.length; i++){
					TextBoxHelper.type("TariffClass_Band_Name", bands[i]);
					ButtonHelper.click("TariffClass_Band_Search");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					
					MouseHelper.click("TariffClass_Band_AddBands");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					entitySearch.selectUsingGridFilterTextBox("Band Search", "Bands_Name", bands[i], "Name");
					assertTrue(PopupHelper.isTextPresent("1 new band(s) have been created and linked to the tariff class."), "Band addition confirmation popup did not appear.");
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
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

	private void saveTariffClass(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElementToDisappear("SaveButton", detailScreenWaitSec);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Tariss Class save did not happen.");
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Grid does not have value '" + name + "'");
			Log4jHelper.logInfo("Tariff Class Search '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}