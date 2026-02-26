package com.subex.automation.helpers.application;

import com.subex.automation.helpers.application.screens.QueryFilterHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.EntitySearchHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class MeasureHelper extends ROCAcceptanceTest {
	
	public void addReportingTable(String entityComboId, boolean isExistingTable, String rtTableName, String rtDisplayName, boolean rtTruncateBeforeLoad,
			String[][] rtColumnDetails) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(rtTableName)) {
				EntityComboHelper.clickEntityIcon(entityComboId);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Reporting Table Mapping"));
				
				if (isExistingTable) {
					RadioHelper.click("ReportingTable_IsExistingTable");
					EntityComboHelper.selectUsingGridFilterTextBox("ReportingTable_TableInstance", "Table Instance Search", "TableInst_TableName", rtTableName, "Table Name");
				}
				else {
					RadioHelper.click("ReportingTable_NewTableInstance");
					TextBoxHelper.type("ReportingTable_NewTableName", rtTableName);
					TextBoxHelper.type("ReportingTable_NewDisplayName", rtDisplayName);
				}
				
				if (rtTruncateBeforeLoad)
					CheckBoxHelper.check("ReportingTable_TruncateBeforeLoad");
				
				if(ValidationHelper.isNotEmpty(rtColumnDetails)) {
					String gridId = "ReportingTable_Column_Grid";
					
					for (int i = 0; i < rtColumnDetails.length; i++) {
						if(ValidationHelper.isNotEmpty(rtColumnDetails[i])) {
							int rowNum = GridHelper.getRowNumber(gridId, rtColumnDetails[i][0], "Name");
							
							if (rowNum == 0) {
								NavigationHelper.navigateToAction("ReportingTable_Column_Add", rtColumnDetails[i][0]);
								GenericHelper.waitForLoadmask(detailScreenWaitSec);
								rowNum = GridHelper.getRowNumber(gridId, rtColumnDetails[i][0], "Name");
							}
							
							GridHelper.updateGridTextBox(gridId, "ReportingTable_Column_DisplayName", rowNum, "Display Name", "Type", rtColumnDetails[i][1]);
							
							GridHelper.updateGridComboBox(gridId, "ReportingTable_Column_Mappings", rowNum, "Mappings", "Type", rtColumnDetails[i][2]);
						}
					}
				}
				
				ButtonHelper.click("ReportingTable_OK");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (LabelHelper.isTitlePresent("Confirm")) {
					ButtonHelper.click("YesButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				assertTrue(LabelHelper.isTitleNotPresent("Reporting Table Mapping"));
				assertTrue(EntityComboHelper.isValuePresent(entityComboId, rtTableName));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void linkCaseTemplate(String entityComboId, String caseTemplateName, String[][] casePropertyMapping) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caseTemplateName)) {
				EntityComboHelper.clickEntityIcon(entityComboId);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Case Properties Mapping"));
				EntityComboHelper.selectUsingGridFilterTextBox("LinkCaseTemplate_CaseTemplate", "Case Template Search", "CaseTemplate_Name", caseTemplateName, "Name");
				
				if (ButtonHelper.isPresent("ok")) {
					ButtonHelper.click("ok");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				if(ValidationHelper.isNotEmpty(casePropertyMapping)) {
					String gridId = "LinkCaseTemplate_PropertyMapping_Grid";
					
					for (int i = 0; i < casePropertyMapping.length; i++) {
						int rowNum = GridHelper.getRowNumber(gridId, casePropertyMapping[i][0], "Name");
						
						GridHelper.updateGridComboBox(gridId, "LinkCaseTemplate_PropertyMapping", rowNum, "Mappings", "Name", casePropertyMapping[i][1]);
					}
				}
				
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitleNotPresent("Case Properties Mapping"));
				assertTrue(EntityComboHelper.isValuePresent(entityComboId, caseTemplateName));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void linkCaseTemplate(String caseTemplateName, boolean createSingleCase, boolean notify, String[][] casePropertyMapping, String expiresIn,
			String cost, String severity) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(caseTemplateName)) {
				EntityComboHelper.selectUsingGridFilterTextBox("LinkCaseTemplate_CaseTemplate", "Case Template Search", "CaseTemplate_Name", caseTemplateName, "Name");
				if (ButtonHelper.isPresent("OKButton")) {
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				if (createSingleCase)
					CheckBoxHelper.check("LinkCaseTemplate_CreateSingleCase");
				
				if (notify)
					CheckBoxHelper.check("LinkCaseTemplate_Notify");
				
				if(ValidationHelper.isNotEmpty(casePropertyMapping)) {
					String gridId = "LinkCaseTemplate_PropertyMapping_Grid";
					
					for (int i = 0; i < casePropertyMapping.length; i++) {
						int rowNum = GridHelper.getRowNumber(gridId, casePropertyMapping[i][0], "Name");
						
						GridHelper.updateGridComboBox(gridId, "LinkCaseTemplate_PropertyMapping", rowNum, "Mappings", "Name", casePropertyMapping[i][1]);
					}
				}
				
				TextBoxHelper.type("LinkCaseTemplate_ExpiresIn", expiresIn);
				TextBoxHelper.type("LinkCaseTemplate_Cost", cost);
				TextBoxHelper.type("LinkCaseTemplate_Severity", severity);
				
				ButtonHelper.click("LinkCaseTemplate_Save");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitleNotPresent("Case Properties Mapping"));
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addInputMeasure(String sourceGridId, String[] inputMeasure, String[] inputTable) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(inputMeasure)) {
				for (int i = 0; i < inputMeasure.length; i++ ) {
					boolean isPresent = GridHelper.isValuePresent("InputMeasure_Grid", inputMeasure[i], "Name");
					
					if (!isPresent) {
						ButtonHelper.click("InputMeasure_Add");
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
		
						EntitySearchHelper entitySearch = new EntitySearchHelper();
						entitySearch.selectUsingGridFilterTextBox("Measure Search", "Measure_Name", inputMeasure[i], "Name");
					}
					
					if (ValidationHelper.isNotEmpty(inputTable)) {
						isPresent = GridHelper.isValuePresent(sourceGridId, inputTable[i], "Table Name");
						
						if (!isPresent) {
							NavigationHelper.navigateToAction("Add Input Table", inputTable[i]);
						}
					}
//					else {
//						FailureHelper.failTest("Input Table details is missing in the data provided.");
//					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addHavingClause(String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] aggregate1,
			String[] expExpression1, String[] expOperator, String[] aggregate2, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(expressionType)) {
				QueryFilterHelper queryFilter = new QueryFilterHelper("QueryFilter_HavingClause_Grid");
				
				for (int i = 0; i < expressionType.length; i++) {
					ButtonHelper.click("QueryFilter_HavingClause_Grid", "QueryFilter_Expression_Add");
					
					if (expressionType[i].equals("Filter")) {
						queryFilter.addFilter(filterName[i]);
					}
					else if (expressionType[i].equals("Condition")) {
						queryFilter.addCondition((i+1), expClause[i], expLeftIndent[i], aggregate1[i], expExpression1[i],
								expOperator[i], aggregate2[i], expExpression2[i], expRightIndent[i]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveMeasure(String measureType, String name, String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);

			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle));
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(GridHelper.isValuePresent("SearchGrid", name, "Name"));
			Log4jHelper.logInfo(measureType + " '" + name + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}