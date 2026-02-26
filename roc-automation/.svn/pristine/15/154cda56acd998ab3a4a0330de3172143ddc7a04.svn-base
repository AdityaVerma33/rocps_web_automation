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

public class StringRuleSetHelper extends ROCAcceptanceTest {

	public void createStringRuleSet(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				
				String[] stringRules = testData.getStringValue(excelData.get("String Rules").get(i), firstLevelDelimiter);
				String[][] ruleSteps = testData.getStringValue(excelData.get("Rule Steps").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] ruleMatches = testData.getStringValue(excelData.get("Rule Matches").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				createStringRuleSet(partition, name, stringRules, ruleSteps, ruleMatches);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createStringRuleSet(String partition, String name, String[] stringRules, String[][] ruleSteps, String[][] ruleMatches) throws Exception {
		try {
			NavigationHelper.navigateToScreen("String Rule Sets", "String Rule Set Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("SRS_Name", name, "Name");

			if (row > 0) {
				Log4jHelper.logWarning("String Rule Set '" + name + "' is already present.");
			}
			else {
				String detailScreenTitle = NavigationHelper.navigateToNew(partition, "SRS_Name");
				
				updateStringRuleSet(name, stringRules, ruleSteps, ruleMatches);
				
				saveStringRuleSet(name, detailScreenTitle);
			}
		
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStringRuleSet(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				
				String[] stringRules = testData.getStringValue(excelData.get("String Rules").get(i), firstLevelDelimiter);
				String[][] ruleSteps = testData.getStringValue(excelData.get("Rule Steps").get(i), firstLevelDelimiter, secondLevelDelimiter);
				String[][] ruleMatches = testData.getStringValue(excelData.get("Rule Matches").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updateStringRuleSet(name, stringRules, ruleSteps, ruleMatches);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateStringRuleSet(String name, String[] stringRules, String[][] ruleSteps, String[][] ruleMatches) throws Exception {
		try {
			TextBoxHelper.type("SRS_Name", name);
			
			addStringRules(stringRules, ruleSteps);
			
			addRuleMatches(ruleMatches);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addStringRules(String[] stringRules, String[][] ruleSteps) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(stringRules)) {
				for (int i = 0; i < stringRules.length; i++) {
					int row = GridHelper.getRowNumber("SRS_StringRules_Grid", stringRules[i], "Name");
					
					if (row == 0) {
						ButtonHelper.click("SRS_StringRules_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("String Rule"));
						
						TextBoxHelper.type("SRS_StringRule_Name", stringRules[i]);
						
						addRuleSteps(ruleSteps[i]);
						
						ButtonHelper.click("SRS_StringRule_Save");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
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
	
	public void addRuleSteps(String[] ruleSteps) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(ruleSteps)) {
				for (int i = 0; i < ruleSteps.length; i++) {
					String[] ruleStep = ruleSteps[i].split("#", -1);
					ButtonHelper.click("SRS_StringRule_RuleSteps_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("String Rule Step"));
					
					TextBoxHelper.type("SRS_StringRule_RuleStep_MatchString", ruleStep[0]);
					TextBoxHelper.type("SRS_StringRule_RuleStep_OutputString", ruleStep[1]);
					TextBoxHelper.type("SRS_StringRule_RuleStep_FieldString", ruleStep[4]);
					
					ButtonHelper.click("SRS_StringRule_RuleStep_Save");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				
				for (int i = 0; i < ruleSteps.length; i++) {
					String[] ruleStep = ruleSteps[i].split("#", -1);
					
					if (ValidationHelper.isNotEmpty(ruleStep[2]) || ValidationHelper.isNotEmpty(ruleStep[3])) {
						GridHelper.clickRow("SRS_StringRule_RuleSteps_Grid", (i+1), "Match String");
						ButtonHelper.click("SRS_StringRule_RuleSteps_Edit");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						assertTrue(LabelHelper.isTitlePresent("String Rule Step"));
						
						ComboBoxHelper.select("SRS_StringRule_RuleStep_MatchNextStep", ruleStep[2]);
						ComboBoxHelper.select("SRS_StringRule_RuleStep_NonmatchNextStep", ruleStep[3]);
						
						ButtonHelper.click("SRS_StringRule_RuleStep_Save");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
					}
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
	
	public void addRuleMatches(String[][] ruleMatches) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(ruleMatches)) {
				for (int i = 0; i < ruleMatches.length; i++) {
					ButtonHelper.click("SRS_RuleMatches_Add");
					
					GridHelper.updateGridTextBox("SRS_RuleMatches_Grid", "SRS_RuleMatches_MatchString", (i+1), "Match String", "String Rule", ruleMatches[i][0]);
					
					GridHelper.updateGridComboBox("SRS_RuleMatches_Grid", "SRS_RuleMatches_StringRule", (i+1), "String Rule", "Match String", ruleMatches[i][1]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveStringRuleSet(String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "String Rule Set save did not happen");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"), "Value '" + name + "' is not found in grid.");
			Log4jHelper.logInfo("String Rule Set '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}