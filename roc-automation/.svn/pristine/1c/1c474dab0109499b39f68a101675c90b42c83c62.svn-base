package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.MeasureHelper;
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
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class KPIDefinitionHelper extends ROCAcceptanceTest{
	
	public void createKPIDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String inputMeasure = excelData.get("Input Measure").get(i);
				
				boolean applyGroupKPI = ValidationHelper.isTrue(excelData.get("Apply Group KPI").get(i));
				String inputAggregate = excelData.get("Input Aggregate").get(i);
				boolean generateNonViolatedRows = ValidationHelper.isTrue(excelData.get("Generate Non-Violated Rows").get(i));
				String minimum = excelData.get("Minimum").get(i);
				String maximum = excelData.get("Maximum").get(i);
				String gracePeriodValue = excelData.get("Grace Period Value").get(i);
				String gracePeriodFrequency = excelData.get("Grace Period Frequency").get(i);
				
				String[] exceptionRule = testData.getStringValue(excelData.get("Exception Rule").get(i), firstLevelDelimiter);
				String exceptionMinimum = excelData.get("Exception Minimum").get(i);
				String exceptionMaximum = excelData.get("Exception Maximum").get(i);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createKPIDefinition(partition, name, inputMeasure, applyGroupKPI, inputAggregate, generateNonViolatedRows, minimum, maximum, gracePeriodValue,
						gracePeriodFrequency, exceptionRule, exceptionMinimum, exceptionMaximum, caseTemplateName, casePropertyMapping);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createKPIDefinition(String partition, String name, String inputMeasure, boolean applyGroupKPI, String inputAggregate,
			boolean generateNonViolatedRows, String minimum, String maximum, String gracePeriodValue, String gracePeriodFrequency,
			String[] exceptionRule, String exceptionMinimum, String exceptionMaximum, String caseTemplateName, String[][] casePropertyMapping) throws Exception {
		try {
			NavigationHelper.navigateToScreen("KPI Definitions", "KPI Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("KPIDefinition_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("KPI Definition '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "KPIDefinition_Name");
				
				updateKPIDefinition(name, inputMeasure, applyGroupKPI, inputAggregate, generateNonViolatedRows, minimum, maximum,
						gracePeriodValue, gracePeriodFrequency, exceptionRule, exceptionMinimum, exceptionMaximum, caseTemplateName, casePropertyMapping);
				
				saveKPIDefinition(name, detailScreenTitle);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateKPIDefinition(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String inputMeasure = excelData.get("Input Measure").get(i);
				
				boolean applyGroupKPI = ValidationHelper.isTrue(excelData.get("Apply Group KPI").get(i));
				String inputAggregate = excelData.get("Input Aggregate").get(i);
				boolean generateNonViolatedRows = ValidationHelper.isTrue(excelData.get("Generate Non-Violated Rows").get(i));
				String minimum = excelData.get("Minimum").get(i);
				String maximum = excelData.get("Maximum").get(i);
				String gracePeriodValue = excelData.get("Grace Period Value").get(i);
				String gracePeriodFrequency = excelData.get("Grace Period Frequency").get(i);
				
				String[] exceptionRule = testData.getStringValue(excelData.get("Exception Rule").get(i), firstLevelDelimiter);
				String exceptionMinimum = excelData.get("Exception Minimum").get(i);
				String exceptionMaximum = excelData.get("Exception Maximum").get(i);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateKPIDefinition(name, inputMeasure, applyGroupKPI, inputAggregate, generateNonViolatedRows, minimum, maximum, gracePeriodValue,
						gracePeriodFrequency, exceptionRule, exceptionMinimum, exceptionMaximum, caseTemplateName, casePropertyMapping);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateKPIDefinition(String name, String inputMeasure, boolean applyGroupKPI, String inputAggregate, boolean generateNonViolatedRows,
			String minimum, String maximum, String gracePeriodValue, String gracePeriodFrequency, String[] exceptionRule, String exceptionMinimum,
			String exceptionMaximum, String caseTemplateName, String[][] casePropertyMapping) throws Exception {
		try {
			TextBoxHelper.type("KPIDefinition_Panel", "KPIDefinition_Name", name);
			
			if (EntityComboHelper.isEnabled("KPIDefinition_InputMeasure"))
				EntityComboHelper.selectUsingGridFilterTextBox("KPIDefinition_InputMeasure", "Measure Search", "Measure_Name", inputMeasure, "Name");
			assertEquals(EntityComboHelper.getValue("KPIDefinition_InputMeasure"), inputMeasure);
			
			if (applyGroupKPI)
				CheckBoxHelper.check("KPIDefinition_ApplyKPIAtGroupLevel");
			else
				CheckBoxHelper.uncheck("KPIDefinition_ApplyKPIAtGroupLevel");
			ComboBoxHelper.select("KPIDefinition_InputAggregate", inputAggregate);
			
			if (generateNonViolatedRows)
				CheckBoxHelper.check("KPIDefinition_GenerateNonviolatedRows");
			else
				CheckBoxHelper.uncheck("KPIDefinition_GenerateNonviolatedRows");
			TextBoxHelper.type("KPIDefinition_Minimum", minimum);
			TextBoxHelper.type("KPIDefinition_Maximum", maximum);
			
			if (ValidationHelper.isNotEmpty(gracePeriodValue)) {
				TextBoxHelper.type("KPIDefinition_GracePeriodUnits", gracePeriodValue);
				ComboBoxHelper.select("KPIDefinition_GracePeriod", gracePeriodFrequency);
			}
			
			if (ValidationHelper.isNotEmpty(exceptionRule)) {
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addStandardExpression(0, exceptionRule);
				
				TextBoxHelper.type("KPIDefinition_ExceptionMinimum", exceptionMinimum);
				TextBoxHelper.type("KPIDefinition_ExceptionMaximum", exceptionMaximum);
			}
			
			MeasureHelper measure = new MeasureHelper();
			measure.linkCaseTemplate("KPIDefinition_CaseTemplate", caseTemplateName, casePropertyMapping);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveKPIDefinition(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("KPIDefinition_Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo("KPI Definition '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}