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
import com.subex.automation.helpers.component.LinkHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CaseInstanceHelper extends ROCAcceptanceTest {
	
	public void verifyCaseInstance(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				boolean isPendingCase = ValidationHelper.isTrue(excelData.get("Enable Pending Filter").get(i));
				boolean isNewCase = ValidationHelper.isTrue(excelData.get("Enable New Filter").get(i));
				boolean isOpenCase = ValidationHelper.isTrue(excelData.get("Enable Open Filter").get(i));
				boolean isResolvedCase = ValidationHelper.isTrue(excelData.get("Enable Resolved Filter").get(i));
				boolean isClosedCase = ValidationHelper.isTrue(excelData.get("Enable Closed Filter").get(i));
				String caseTemplate = excelData.get("Case Template").get(i);
				
				int resultCount = testData.getIntegerValue(excelData.get("Result Count").get(i));
				String resultExcelPath = excelData.get("Result Excel Path").get(i);
				String resultExcelFilename = excelData.get("Result Excel Filename").get(i);
				String resultExcelSheetname = excelData.get("Result Excel Sheetname").get(i);
				String resultExcelTCName = excelData.get("Result Excel TCName").get(i);
				
				verifyCaseInstance(isPendingCase, isNewCase, isOpenCase, isResolvedCase, isClosedCase, caseTemplate, resultCount, resultExcelPath,
						resultExcelFilename, resultExcelSheetname, resultExcelTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyCaseInstance(boolean isPendingCase, boolean isNewCase, boolean isOpenCase, boolean isResolvedCase, boolean isClosedCase,
			String caseTemplate, int resultCount, String resultExcelPath, String resultExcelFilename, String resultExcelSheetname,
			String resultExcelTCName) throws Exception {
		try {
			navigateToCaseInstance(isPendingCase, isNewCase, isOpenCase, isResolvedCase, isClosedCase, caseTemplate);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows >= resultCount) {
				Log4jHelper.logInfo("Expected number of cases are found in Case Instance screen.");
				
				if(ValidationHelper.isNotEmpty(resultExcelPath)) {
					DataAssertion dataAssertion = new DataAssertion();
					dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
				}
			}
			else {
				FailureHelper.failTest("Expected number of cases '" + resultCount + "' are not found in Case Instance screen. Found '" + rows + "' cases.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseInstance(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Result Count").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String externalReference = excelData.get("External Reference").get(i);
				String caseTemplate = excelData.get("Case Template").get(i);
				String description = excelData.get("Description").get(i);
				String severity = excelData.get("Severity").get(i);
				String priority = excelData.get("Priority").get(i);
				String caseExpires = excelData.get("Case Expires").get(i);
				String cost = excelData.get("Cost").get(i);
				
				String recoveredValue = excelData.get("Recovered Value").get(i);
				String unrecoverableValue = excelData.get("Unrecoverable Value").get(i);
				boolean trueLeakage = ValidationHelper.isTrue(excelData.get("True Leakage").get(i));
				String falseRecoveredValue = excelData.get("False Recovered Value").get(i);
				
				String[] casePropertyName = testData.getStringValue(excelData.get("Case Property Name").get(i), firstLevelDelimiter);
				String[] casePropertyValue = testData.getStringValue(excelData.get("Case Property Value").get(i), firstLevelDelimiter);
				
				String[] notes =  testData.getStringValue(excelData.get("Notes").get(i), firstLevelDelimiter);
				boolean stepComplete = ValidationHelper.isTrue(excelData.get("Step Complete").get(i));
				String nextStep = excelData.get("Next Step").get(i);
				String closureCategory = excelData.get("Closure Category").get(i);
				
				createCaseInstance(partition, externalReference, caseTemplate, description, severity, priority, caseExpires, cost, recoveredValue,
						unrecoverableValue, trueLeakage, falseRecoveredValue, casePropertyName, casePropertyValue, notes, stepComplete, nextStep,
						closureCategory);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createCaseInstance(String partition, String externalReference, String caseTemplate, String description, String severity,
			String priority, String caseExpires, String cost, String recoveredValue, String unrecoverableValue, boolean trueLeakage,
			String falseRecoveredValue, String[] casePropertyName, String[] casePropertyValue, String[] notes, boolean stepComplete,
			String nextStep, String closureCategory) throws Exception {
		try {
			navigateToCaseInstance(false, true, false, false, false, caseTemplate);
			String detailScreenTitle = NavigationHelper.navigateToNew(partition, "CaseInstance_ExternalReference");
			
			TextBoxHelper.type("CaseInstance_ExternalReference", externalReference);

			EntityComboHelper.selectUsingGridFilterTextBox("CaseInstance_CaseTemplate_Entity", "Case Template Search", "CaseTemplate_Name", caseTemplate, "Name");
			assertEquals(EntityComboHelper.getValue("CaseInstance_CaseTemplate_Entity"), caseTemplate);
			TextBoxHelper.type("CaseInstance_Description", description);

			TextBoxHelper.type("CaseInstance_Severity", severity);
			ComboBoxHelper.select("CaseInstance_Priority", priority);
			TextBoxHelper.type("CaseInstance_CaseExpires", caseExpires);
			TextBoxHelper.type("CaseInstance_Cost", cost);
			TextBoxHelper.type("CaseInstance_RecoveredValue", recoveredValue);
			TextBoxHelper.type("CaseInstance_UnrecoverableValue", unrecoverableValue);
			
			if(trueLeakage)
				RadioHelper.click("CaseInstance_LeakageType_True");
			else {
				RadioHelper.click("CaseInstance_LeakageType_False");
				TextBoxHelper.type("CaseInstance_FalseRecoveredValue", falseRecoveredValue);
			}
			
			updateProperties(casePropertyName, casePropertyValue);
			
			addNotes(notes);
			
			if(stepComplete) {
				CheckBoxHelper.check("CaseInstance_StepComplete");
				if(ComboBoxHelper.isEnabled("CaseInstance_NextStep"))
					ComboBoxHelper.select("CaseInstance_NextStep", nextStep);
				
				if(ComboBoxHelper.isEnabled("CaseInstance_ClosureCategory")){
					ComboBoxHelper.select("CaseInstance_ClosureCategory", closureCategory);
				}
			}

			saveCaseInstance(description, detailScreenTitle, trueLeakage);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyCaseDetails(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Case Template").size(); i++)
			{
				boolean isPendingCase = ValidationHelper.isTrue(excelData.get("Enable Pending Filter").get(i));
				boolean isNewCase = ValidationHelper.isTrue(excelData.get("Enable New Filter").get(i));
				boolean isOpenCase = ValidationHelper.isTrue(excelData.get("Enable Open Filter").get(i));
				boolean isResolvedCase = ValidationHelper.isTrue(excelData.get("Enable Resolved Filter").get(i));
				boolean isClosedCase = ValidationHelper.isTrue(excelData.get("Enable Closed Filter").get(i));
				String caseTemplate = excelData.get("Case Template").get(i);
				
				int rowNo = testData.getIntegerValue(excelData.get("Row Number").get(i));
				String externalReference = excelData.get("External Reference").get(i);
				String description = excelData.get("Description").get(i);
				String severity = excelData.get("Severity").get(i);
				String priority = excelData.get("Priority").get(i);
				String caseExpires = excelData.get("Case Expires").get(i);
				String cost = excelData.get("Cost").get(i);
				
				String recoveredValue = excelData.get("Recovered Value").get(i);
				String unrecoverableValue = excelData.get("Unrecoverable Value").get(i);
				boolean trueLeakage = ValidationHelper.isTrue(excelData.get("True Leakage").get(i));
				String falseRecoveredValue = excelData.get("False Recovered Value").get(i);
				
				String[] casePropertyName = testData.getStringValue(excelData.get("Case Property Name").get(i), firstLevelDelimiter);
				String[] casePropertyValue = testData.getStringValue(excelData.get("Case Property Value").get(i), firstLevelDelimiter);
				
				verifyCaseDetails(isPendingCase, isNewCase, isOpenCase, isResolvedCase, isClosedCase, caseTemplate, rowNo, externalReference,
						description, severity, priority, caseExpires, cost, recoveredValue, unrecoverableValue, trueLeakage, falseRecoveredValue,
						casePropertyName, casePropertyValue);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyCaseDetails(boolean isPendingCase, boolean isNewCase, boolean isOpenCase, boolean isResolvedCase, boolean isClosedCase,
			String caseTemplate, int rowNo, String externalReference, String description, String severity,
			String priority, String caseExpires, String cost, String recoveredValue, String unrecoverableValue, boolean trueLeakage,
			String falseRecoveredValue, String[] casePropertyName, String[] casePropertyValue) throws Exception {
		try {
			navigateToCaseInstance(isPendingCase, isNewCase, isOpenCase, isResolvedCase, isClosedCase, caseTemplate);
			int rows = GridHelper.getRowCount("SearchGrid");
			
			if (rows >= rowNo) {
				GridHelper.clickRow("SearchGrid", rowNo, 1);
				NavigationHelper.navigateToAction("Common Tasks");
				if (NavigationHelper.isActionPresent("Edit"))
					NavigationHelper.navigateToAction("Edit");
				else
					NavigationHelper.navigateToAction("View");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				if (ValidationHelper.isNotEmpty(externalReference))
					assertEquals(TextBoxHelper.getValue("CaseInstance_ExternalReference"), externalReference);
				
				assertEquals(EntityComboHelper.getValue("CaseInstance_CaseTemplate_Entity"), caseTemplate);
				if (ValidationHelper.isNotEmpty(description))
					assertEquals(TextBoxHelper.getValue("CaseInstance_Description"), description);
				
				if (ValidationHelper.isNotEmpty(severity))
					assertEquals(TextBoxHelper.getValue("CaseInstance_Severity"), severity);
				
				if (ValidationHelper.isNotEmpty(priority))
					assertEquals(ComboBoxHelper.getValue("CaseInstance_Priority"), priority);
				
				if (ValidationHelper.isNotEmpty(caseExpires))
					assertEquals(TextBoxHelper.getValue("CaseInstance_CaseExpires"), caseExpires);
				
				if (ValidationHelper.isNotEmpty(cost))
					assertEquals(TextBoxHelper.getValue("CaseInstance_Cost"), cost);
				
				if (ValidationHelper.isNotEmpty(recoveredValue))
					assertEquals(TextBoxHelper.getValue("CaseInstance_RecoveredValue"), recoveredValue);
				
				if (ValidationHelper.isNotEmpty(unrecoverableValue))
					assertEquals(TextBoxHelper.getValue("CaseInstance_UnrecoverableValue"), unrecoverableValue);
				
				if (ValidationHelper.isNotEmpty(falseRecoveredValue))
					assertEquals(TextBoxHelper.getValue("CaseInstance_FalseRecoveredValue"), falseRecoveredValue);
				
				if (ValidationHelper.isNotEmpty(casePropertyName)) {
					for(int i = 0; i < casePropertyName.length; i++) {
						if (ValidationHelper.isNotEmpty(casePropertyValue) && ValidationHelper.isNotEmpty(casePropertyValue[i]))
							assertEquals(PropertyGridHelper.getValue("CaseInstance_PropertiesWrapperId", casePropertyName[i]), casePropertyValue[i], "Value not proper for property '" + casePropertyName[i] + "'");
					}
				}
				
				if (ButtonHelper.isPresent("CancelButton")) {
					ButtonHelper.click("CancelButton");
				}
				else {
					ButtonHelper.click("CloseButton");
				}
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Row number '" + rowNo + "' is not found in Case Instance grid.");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private void navigateToCaseInstance(boolean isPendingCase, boolean isNewCase, boolean isOpenCase, boolean isResolvedCase, boolean isClosedCase,
			String caseTemplate) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Case Instances", "Case Instance Search");
			
			if (!isPendingCase)
				CheckBoxHelper.uncheck("CaseInstance_Pending");
			if (isNewCase)
				CheckBoxHelper.check("CaseInstance_New");
			if (!isOpenCase)
				CheckBoxHelper.uncheck("CaseInstance_Open");
			if (isResolvedCase)
				CheckBoxHelper.check("CaseInstance_Resolved");
			if (isClosedCase)
				CheckBoxHelper.check("CaseInstance_Closed");
			
			EntityComboHelper.clickEntityIcon("CaseInstance_CaseTemplate");
			String dropdown = GenericHelper.getORProperty("CaseInstance_CaseTemplate_Dropdown").replace("templateName", caseTemplate);
			MouseHelper.click(dropdown);
			ButtonHelper.click("CaseInstance_CaseTemplate_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void updateProperties(String[] casePropertyName, String[] casePropertyValue) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(casePropertyName)) {
				LinkHelper.click("CaseInstance_Properties");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Properties"));
				
				for(int i = 0; i < casePropertyName.length; i++){
					PropertyGridHelper.updateProperty("CaseInstance_Properties_Popup_Wrapper", casePropertyName[i], casePropertyValue[i], null);
				}
				
				ButtonHelper.click("CaseInstance_Properties_Popup_OK");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}
	
	private void addNotes(String[] notes) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(notes)) {
				LinkHelper.click("CaseInstance_Notes");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Notes"));
				
				for (int i = 0; i < notes.length; i++) {
					ButtonHelper.click("CaseInstance_Notes_Add");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					assertTrue(LabelHelper.isTitlePresent("Add/Edit Notes"));
					
					TextAreaHelper.type("CaseInstance_Notes_AddNotes_Textarea", notes[i]);
					ButtonHelper.click("CaseInstance_Notes_AddNotes_OK");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
				
				ButtonHelper.click("CaseInstance_Notes_OK");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}

	}
	
	private void saveCaseInstance(String description, String detailScreenTitle, boolean trueLeakage) throws Exception {
		try {
			ButtonHelper.click("CaseInstance_Details_Save");
			if(trueLeakage)
				ButtonHelper.click("CaseInstance_Details_Yes");
			
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Case Instance save did not happen");
			
			ButtonHelper.click("CaseInstance_Search_Button");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", description, "Case Description"), "Value '" + description + "' is not found in grid.");
			Log4jHelper.logInfo("Case Instance '" + description + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}