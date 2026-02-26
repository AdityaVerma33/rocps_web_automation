package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
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

public class BandHelper extends ROCAcceptanceTest {
	
	public void createBand(String path, String workBookName, String workSheetName, String testCaseName, int occurance) throws Exception 
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurance);
			
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String bandName = excelData.get("Name").get(i);
				String bandType = excelData.get("Type").get(i);
				boolean pointToPoint = ValidationHelper.isTrue(excelData.get("Point To Point").get(i));
				String externalRef = excelData.get("External Reference").get(i);
				String[] elementSets = testData.getStringValue(excelData.get("Element Set").get(i), firstLevelDelimiter);
				String[] elementSetTypes = testData.getStringValue(excelData.get("Element Set Type").get(i), firstLevelDelimiter);
				String[] fromElements = testData.getStringValue(excelData.get("From Element").get(i), firstLevelDelimiter);
				String[] toElements = testData.getStringValue(excelData.get("To Element").get(i), firstLevelDelimiter);
				
				createBand(partition, bandName, bandType, pointToPoint, externalRef, elementSets, elementSetTypes, fromElements, toElements);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createBand(String partition, String bandName, String bandType, boolean pointToPoint, String externalRef, String[] elementSets,
			String[] elementSetTypes, String[] fromElements, String[] toElements) throws Exception
	{
		try {
			int row = navigateToBands(partition, bandName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Band", "Bands_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type( "Bands_Name", bandName );
			ComboBoxHelper.select( "Bands_BandType", bandType );
			if(pointToPoint)
				CheckBoxHelper.check("Bands_PointToPoint");
			
			TextBoxHelper.type( "Bands_ExternalReference", externalRef);
			TariffHelper tariff = new TariffHelper();
			tariff.addElementSets("Bands_ElementSet_Grid", "Bands_ElementSet_Add", elementSets, elementSetTypes);
			
			if (pointToPoint) {
				addElementConnection(bandName, fromElements, toElements);
			}
			else {
				addElementConnection(bandName, toElements);
			}
			
			saveBand(bandName, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToBands(String partition, String bandName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bands", "Band Search");
			SearchGridHelper.gridFilterSearchWithTextBox("Bands_Name", bandName, "Name");
			int row = GridHelper.getRowNumber("SearchGrid", bandName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addElementConnection(String bandName, String[] toElements) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(toElements))
			{
				int length = toElements.length;
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				
				for (int i = 0; i < length; i++) {
					TextBoxHelper.type("Bands_ElementConnection_ToElementName", toElements[i]);
					ButtonHelper.click("searchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					boolean isPresent = GridHelper.isValuePresent("Bands_ElementConnection_Grid", toElements[i], "To Element Name");
					
					if (!isPresent) {
						ButtonHelper.click("Bands_ElementConnection_Add");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						
						entitySearch.selectUsingGridFilterTextBox("Element Search", "Elements_Name", toElements[i], "Name");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						CheckBoxHelper.check("Bands_ElementConnection_NewlyAddElement");
						assertTrue(GridHelper.isValuePresent("Bands_ElementConnection_Grid", toElements[i], "To Element Name"), "Grid does not have value '" + toElements[i] + "'");
						CheckBoxHelper.uncheck("Bands_ElementConnection_NewlyAddElement");
					}
				}
			}
			else {
				FailureHelper.failTest("Please specify To Elements for Band '" + bandName + "'. Elements are mandatory for Band");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addElementConnection(String bandName, String[] fromElements, String[] toElements) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(fromElements))
			{
				for (int i = 0; i < fromElements.length; i++) {
					TextBoxHelper.type("Bands_ElementConnection_FromElementName", fromElements[i]);
					TextBoxHelper.type("Bands_ElementConnection_ToElementName", toElements[i]);
					ButtonHelper.click("searchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					int rowNum = GridHelper.getRowNumber("Bands_ElementConnection_Grid", fromElements[i], "From  Element Name");
					boolean isPresent = false;
					
					if (rowNum > 0) {
						String value = GridHelper.getCellValue("Bands_ElementConnection_Grid", rowNum, "To Element Name");
						if (value.equals(toElements[i]))
							isPresent = true;
					}
					
					if (!isPresent) {
						ButtonHelper.click("Bands_ElementConnection_Add");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("New Element Connection"), "Element Connection popup did not appear.");
						
						EntityComboHelper.selectUsingGridFilterTextBox("Bands_PTP_FromElement", "Element Search", "Elements_Name", fromElements[i], "Name");
						EntityComboHelper.selectUsingGridFilterTextBox("Bands_PTP_ToElement", "Element Search", "Elements_Name", toElements[i], "Name");
						
						ButtonHelper.click("Bands_PTP_OK");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						assertTrue(GridHelper.isValuePresent("Bands_ElementConnection_Grid", fromElements[i], "From Element Name"), "Grid does not have value '" + fromElements[i] + "'");
						assertTrue(GridHelper.isValuePresent("Bands_ElementConnection_Grid", toElements[i], "To Element Name"), "Grid does not have value '" + toElements[i] + "'");
					}
				}
			}
			else {
				FailureHelper.failTest("Please specify To Elements for Band '" + bandName + "'. Elements are mandatory for Band");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveBand(String bandName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElementToDisappear("SaveButton", detailScreenWaitSec);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Band save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", bandName, "Name"), "Value '" + bandName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Band '" + bandName + "' updated.");
			else
				Log4jHelper.logInfo("Band '" + bandName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}