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
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ComparisonMeasureHelper extends ROCAcceptanceTest {
	
	public void createComparisonMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String name = excelData.get("Name").get(i);
				String source1Measure = excelData.get("Source1 Query Measure").get(i);
				String source2Measure = excelData.get("Source2 Query Measure").get(i);
				String [][] comparisonMapping = testData.getStringValue(excelData.get("Comparison Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [][] summaryInfo = testData.getStringValue(excelData.get("Summary Information").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String dataMatchMeasure = excelData.get("Data Match Measure").get(i);
				boolean executeADM = ValidationHelper.isTrue(excelData.get("Execute ADM").get(i));
				String [] expressionType = testData.getStringValue(excelData.get("DM Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("DM Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("DM Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("DM Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("DM Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("DM Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("DM Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("DM Expression Right Indent").get(i), firstLevelDelimiter);
				
				createComparisonMeasure(partition, name, source1Measure, source2Measure, comparisonMapping, summaryInfo, caseTemplateName,
						casePropertyMapping, isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails,
						dataMatchMeasure, executeADM, expressionType, filterName, expClause, expLeftIndent, expExpression1,
						expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createComparisonMeasure(String partition, String name, String source1Measure, String source2Measure, String[][] comparisonMapping,
			String[][] summaryInfo, String caseTemplateName, String[][] casePropertyMapping, boolean isExistingTable, String rtTableName,
			String rtDisplayName, boolean rtTruncateBeforeLoad, String[][] rtColumnDetails, String dataMatchMeasure, boolean executeADM,
			String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator,
			String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Measures", "Measure Search");
			int row = SearchGridHelper.gridFilterSearchWithTextBox("Measure_Name", name, "Name");

			if (row >0 ){
				Log4jHelper.logWarning("Measure '" + name + "' is already present.");
			}
			else {
				NavigationHelper.navigateToAction("Common Tasks", "New", "Comparison Measure");
				NavigationHelper.selectPartition(partition);
				GenericHelper.waitForElement("CM_Name", detailScreenWaitSec);
				String detailScreenTitle = NavigationHelper.getScreenTitle();
				
				updateComparisonMeasure(name, source1Measure, source2Measure, comparisonMapping, summaryInfo, caseTemplateName, casePropertyMapping,
						isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails, dataMatchMeasure, executeADM, expressionType,
						filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				MeasureHelper measure = new MeasureHelper();
				measure.saveMeasure("Comparison Measure", name, detailScreenTitle);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateComparisonMeasure(String path, String workBookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, workBookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Name").size(); i++)
			{
				String name = excelData.get("Name").get(i);
				String source1Measure = excelData.get("Source1 Query Measure").get(i);
				String source2Measure = excelData.get("Source2 Query Measure").get(i);
				String [][] comparisonMapping = testData.getStringValue(excelData.get("Comparison Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String [][] summaryInfo = testData.getStringValue(excelData.get("Summary Information").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				boolean isExistingTable = ValidationHelper.isTrue(excelData.get("Is Existing Table").get(i));
				String rtTableName = excelData.get("Reporting Table Name").get(i);
				String rtDisplayName = excelData.get("Reporting Table Display Name").get(i);
				boolean rtTruncateBeforeLoad = ValidationHelper.isTrue(excelData.get("RT Truncate Before Load").get(i));
				String[][] rtColumnDetails = testData.getStringValue(excelData.get("RT Column Details").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String caseTemplateName = excelData.get("Case Template Name").get(i);
				String[][] casePropertyMapping = testData.getStringValue(excelData.get("Case Property Mapping").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				String dataMatchMeasure = excelData.get("Data Match Measure").get(i);
				boolean executeADM = ValidationHelper.isTrue(excelData.get("Execute ADM").get(i));
				String [] expressionType = testData.getStringValue(excelData.get("DM Expression Type").get(i), firstLevelDelimiter);
				String [] filterName = testData.getStringValue(excelData.get("DM Filter Name").get(i), firstLevelDelimiter);
				String [] expClause = testData.getStringValue(excelData.get("DM Expression Clause").get(i), firstLevelDelimiter);
				String [] expLeftIndent = testData.getStringValue(excelData.get("DM Expression Left Indent").get(i), firstLevelDelimiter);
				String [] expExpression1 = testData.getStringValue(excelData.get("DM Expression Expression1").get(i), firstLevelDelimiter);
				String [] expOperator = testData.getStringValue(excelData.get("DM Expression Operator").get(i), firstLevelDelimiter);
				String [] expExpression2 = testData.getStringValue(excelData.get("DM Expression Expression2").get(i), firstLevelDelimiter);
				String [] expRightIndent = testData.getStringValue(excelData.get("DM Expression Right Indent").get(i), firstLevelDelimiter);
				
				updateComparisonMeasure(name, source1Measure, source2Measure, comparisonMapping, summaryInfo, caseTemplateName, casePropertyMapping,
						isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails, dataMatchMeasure, executeADM, expressionType,
						filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateComparisonMeasure(String name, String source1Measure, String source2Measure, String[][] comparisonMapping,
			String[][] summaryInfo, String caseTemplateName, String[][] casePropertyMapping, boolean isExistingTable, String rtTableName,
			String rtDisplayName, boolean rtTruncateBeforeLoad, String[][] rtColumnDetails, String dataMatchMeasure, boolean executeADM,
			String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator,
			String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			TextBoxHelper.type("CM_Name", name);
			EntityComboHelper.selectUsingGridFilterTextBox("CM_Source1", "Measure Search", "Measure_Name", source1Measure, "Name");
			assertEquals(EntityComboHelper.getValue("CM_Source1"), source1Measure);
			ComboBoxHelper.select("CM_Source2", source2Measure);
			
			addComparisonMappings(comparisonMapping);
			addSummaryInfo(summaryInfo);
			
			MeasureHelper measure = new MeasureHelper();
			measure.linkCaseTemplate("Measure_CaseTemplate", caseTemplateName, casePropertyMapping);
			measure.addReportingTable("Measure_ReportingTable", isExistingTable, rtTableName, rtDisplayName, rtTruncateBeforeLoad, rtColumnDetails);
			
			addDataMeasure(dataMatchMeasure, executeADM, expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator,
					expExpression2, expRightIndent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addComparisonMappings (String[][] comparisonMapping) 
			throws Exception {
		try {
			String gridId = "CM_Mappings_Grid";
			int rows = GridHelper.getRowCount(gridId);
			
			for (int i = 0; i < comparisonMapping.length; i++) {
				int row = GridHelper.getRowNumber(gridId, comparisonMapping[i][1], "Source 1  Column");
				
				if (row == 0) {
					ButtonHelper.click("CM_Mappings_Add");
					row = rows + 1;
					rows++;
				}
				else {
					String value = GridHelper.getCellValue(gridId, row, "Component");
					if (!value.equals(comparisonMapping[i][0])) {
						ButtonHelper.click("CM_Mappings_Add");
						row = rows + 1;
						rows++;
					}
				}
				
				GridHelper.updateGridComboBox(gridId, "CM_Mappings_Component", row, "Component", "Source 1  Column", comparisonMapping[i][0]);
				
				GridHelper.updateGridComboBox(gridId, "CM_Mappings_Source1Column", row, "Source 1  Column", "Component", comparisonMapping[i][1]);
				
				GridHelper.updateGridTextBox(gridId, "CM_Mappings_Source1OutputColumn", row, "Source 1  Output  Column", "Component", comparisonMapping[i][2]);
				
				GridHelper.updateGridComboBox(gridId, "CM_Mappings_Source2Column", row, "Source 2  Column", "Component", comparisonMapping[i][3]);
				
				GridHelper.updateGridTextBox(gridId, "CM_Mappings_Source2OutputColumn", row, "Source 2  Output  Column", "Component", comparisonMapping[i][4]);
				
				GridHelper.updateGridTextBox(gridId, "CM_Mappings_OutputColumnName", row, "Output  Column  Name", "Component", comparisonMapping[i][5]);
				
				GridHelper.updateGridTextBox(gridId, "CM_Mappings_OutputDisplayName", row, "Output  Display  Name", "Component", comparisonMapping[i][6]);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void addSummaryInfo (String[][] summaryInfo) 
			throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(summaryInfo) && ValidationHelper.isNotEmpty(summaryInfo[0])) {
				TabHelper.gotoTab("Summary Information");
				
				for (int i = 0; i < summaryInfo.length; i++) {
					int row = i + 1;
					MouseHelper.click("CM_SummaryInfo_Add");
					
					GridHelper.updateGridComboBox("CM_SummaryInfo_Grid", "CM_SummaryInfo_Aggregate", row, "Aggregate", "Source 1  Column", summaryInfo[i][0]);
					
					GridHelper.updateGridComboBox("CM_SummaryInfo_Grid", "CM_SummaryInfo_Source1Column", row, "Source 1  Column", "Aggregate", summaryInfo[i][1]);
					
					GridHelper.updateGridComboBox("CM_SummaryInfo_Grid", "CM_SummaryInfo_Source2Column", row, "Source 2  Column", "Aggregate", summaryInfo[i][2]);
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	//	Attach ADM
	public void addDataMeasure (String dataMatchMeasure, boolean executeADM, String[] expressionType, String[] filterName, String[] expClause,
			String[] expLeftIndent, String[] expExpression1, String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(dataMatchMeasure)) {
				EntityComboHelper.clickEntityIcon("CM_LinkDM");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GenericHelper.waitForElement("CM_LinkDM_DataMatch", detailScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Automated Data Match"));
				
				ComboBoxHelper.select("CM_LinkDM_DataMatch", dataMatchMeasure);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				ButtonHelper.click("CM_LinkDM_OK");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				assertTrue(LabelHelper.isTitleNotPresent("Automated Data Match"));
				
				if (executeADM)
					CheckBoxHelper.check("CM_LinkDM_ExecuteDataMatch");
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