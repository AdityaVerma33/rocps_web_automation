package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ElementCreateHelper extends ROCAcceptanceTest {
	
	public void createElement(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception 
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence);
			
			for(int i =0 ; i < excelData.get("Element Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String elementSet = excelData.get("Element Set").get(i);
				String country = excelData.get("Country").get(i);
				String elementName = excelData.get("Element Name").get(i);
				String matchString = excelData.get("Match String").get(i);
				String fromDate = excelData.get("From Date").get(i);
				String toDate = excelData.get("To Date").get(i);
				String parentElement = excelData.get("Parent Element").get(i);
				String reportGroup = excelData.get("Report Group").get(i);
				boolean exactMatch = ValidationHelper.isTrue(excelData.get("Exact Match").get(i));
				boolean usedForMatching = ValidationHelper.isTrue(excelData.get("For Matching").get(i));
				
				createElement(partition, elementSet, country, elementName, matchString, fromDate, toDate, parentElement, reportGroup, exactMatch, usedForMatching);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createElement(String partition, String elementSet, String country, String elementName, String matchString, String fromDate,
			String toDate, String parentElement, String reportGroup, boolean exactMatch, boolean usedForMatching) throws Exception
	{
		try {
			int row = navigateToElements(partition, elementName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Element", "Elements_Name");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			if (!isPresent)
				ComboBoxHelper.select( "Elements_ElementSet", elementSet );
			ComboBoxHelper.select( "Elements_Country", country );
			
			TextBoxHelper.type( "Elements_Name", elementName );
			TextBoxHelper.type( "Elements_MatchString", matchString );
			fromDate = fromDate.replace(" 00:00:00", "");
			TextBoxHelper.type( "Elements_FromDate", fromDate );
			toDate = toDate.replace(" 00:00:00", "");
			TextBoxHelper.type( "Elements_ToDate", toDate );
			
			if (ValidationHelper.isNotEmpty(parentElement))
				EntityComboHelper.selectUsingGridFilterTextBox("Elements_ParentElement", "Element Search", "Elements_Name", parentElement, "Name");
			ComboBoxHelper.select( "Elements_ReportGroup", reportGroup );
			
			if (exactMatch)
				CheckBoxHelper.check( "Elements_MustExactlyMatch" );
			
			if (!usedForMatching)
				CheckBoxHelper.uncheck( "Elements_UsedForMatching" );
			
			saveElement(elementName, detailScreenTitle, isPresent);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToElements(String partition, String elementName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Elements", "Element Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Elements_Name", elementName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveElement(String elementName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Element save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", elementName, "Name"), "Value '" + elementName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Element '" + elementName + "' updated.");
			else
				Log4jHelper.logInfo("Element '" + elementName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}