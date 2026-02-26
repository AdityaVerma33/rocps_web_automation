package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.data.DataAssertion;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class JumpToResultHelper extends ROCAcceptanceTest {
	
	public void verifyResult(String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName,
			String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				DataAssertion dataAssertion = new DataAssertion();
				
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ValidationHelper.isNotEmpty(expressionType))
					applyCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				else {
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				}
				
				dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String selectView, String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName,
			String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				DataAssertion dataAssertion = new DataAssertion();
				
				ButtonHelper.click("ClearButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				if (ValidationHelper.isNotEmpty(selectView))
					ComboBoxHelper.select("JumpToSummary_SelectView", selectView);
				
				if (ValidationHelper.isNotEmpty(expressionType))
					applyCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				else {
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
					
					for (int i = 0; i < 2; i++) {
						if (ImageHelper.isPresent("AuditRequest_SummaryResult_Expand")) {
							ImageHelper.click("AuditRequest_SummaryResult_Expand");
							Thread.sleep(1000);
						}
					}
				}
				
				dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String resultExcelPath, String resultExcelFilename, String[] resultExcelSheetname, String[] resultExcelTCName,
			String[][] expressionType, String[][] filterName, String[][] expClause, String[][] expLeftIndent, String[][] expExpression1,
			String[][] expOperator, String[][] expExpression2, String[][] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				for (int i = 0; i < resultExcelSheetname.length; i++) {
					if (ValidationHelper.isNotEmpty(resultExcelSheetname[i])) {
						if(ValidationHelper.isNotEmpty(expressionType) && ValidationHelper.isNotEmpty(expressionType[i])) {
							String[] filters = null;
							if (ValidationHelper.isNotEmpty(filterName) && ValidationHelper.isNotEmpty(filterName[i]))
								filters = filterName[i];
							
							String[] clause = null;
							if (ValidationHelper.isNotEmpty(expClause) && ValidationHelper.isNotEmpty(expClause[i]))
								clause = expClause[i];
							
							String[] leftIndent = null;
							if (ValidationHelper.isNotEmpty(expLeftIndent) && ValidationHelper.isNotEmpty(expLeftIndent[i]))
								leftIndent = expLeftIndent[i];
							
							String[] expression2 = null;
							if (ValidationHelper.isNotEmpty(expExpression2) && ValidationHelper.isNotEmpty(expExpression2[i]))
								expression2 = expExpression2[i];
							
							String[] rightIndent = null;
							if (ValidationHelper.isNotEmpty(expRightIndent) && ValidationHelper.isNotEmpty(expRightIndent[i]))
								rightIndent = expRightIndent[i];
							verifyResult(resultExcelPath, resultExcelFilename, resultExcelSheetname[i], resultExcelTCName[i], expressionType[i],
								filters, clause, leftIndent, expExpression1[i], expOperator[i], expression2, rightIndent);
						}
						else {
							verifyResult(resultExcelPath, resultExcelFilename, resultExcelSheetname[i], resultExcelTCName[i], null,
									null, null, null, null, null, null, null);
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyResult(String selectView, String resultExcelPath, String resultExcelFilename, String[] resultExcelSheetname, String[] resultExcelTCName,
			String[][] expressionType, String[][] filterName, String[][] expClause, String[][] expLeftIndent, String[][] expExpression1,
			String[][] expOperator, String[][] expExpression2, String[][] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				for (int i = 0; i < resultExcelSheetname.length; i++) {
					if (ValidationHelper.isNotEmpty(resultExcelSheetname[i])) {
						if(ValidationHelper.isNotEmpty(expressionType) && ValidationHelper.isNotEmpty(expressionType[i])) {
							String[] filters = null;
							if (ValidationHelper.isNotEmpty(filterName) && ValidationHelper.isNotEmpty(filterName[i]))
								filters = filterName[i];
							
							String[] clause = null;
							if (ValidationHelper.isNotEmpty(expClause) && ValidationHelper.isNotEmpty(expClause[i]))
								clause = expClause[i];
							
							String[] leftIndent = null;
							if (ValidationHelper.isNotEmpty(expLeftIndent) && ValidationHelper.isNotEmpty(expLeftIndent[i]))
								leftIndent = expLeftIndent[i];
							
							String[] expression2 = null;
							if (ValidationHelper.isNotEmpty(expExpression2) && ValidationHelper.isNotEmpty(expExpression2[i]))
								expression2 = expExpression2[i];
							
							String[] rightIndent = null;
							if (ValidationHelper.isNotEmpty(expRightIndent) && ValidationHelper.isNotEmpty(expRightIndent[i]))
								rightIndent = expRightIndent[i];
							verifyResult(resultExcelPath, resultExcelFilename, resultExcelSheetname[i], resultExcelTCName[i], expressionType[i],
								filters, clause, leftIndent, expExpression1[i], expOperator[i], expression2, rightIndent);
						}
						else {
							verifyResult(selectView, resultExcelPath, resultExcelFilename, resultExcelSheetname[i], resultExcelTCName[i], null,
									null, null, null, null, null, null, null);
						}
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyDrillDown(String gridId, String resultValue, String valueColumn, String drillDownActionGroup, String drillDownAction,
			String resultExcelPath, String resultExcelFilename, String resultExcelSheetname, String resultExcelTCName, String[] expressionType,
			String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1, String[] expOperator,
			String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				DataAssertion dataAssertion = new DataAssertion();
				BrowserHelper browser = new BrowserHelper();
				int rowNum = GridHelper.getRowNumber(gridId, resultValue, valueColumn);
				
				if (rowNum > 0) {
					GridHelper.clickRow(gridId, rowNum, valueColumn);
					NavigationHelper.navigateToAction(drillDownActionGroup, drillDownAction);
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
					
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
					}
					
					if (ValidationHelper.isNotEmpty(expressionType))
						applyCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
							
					dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname, resultExcelTCName);
					browser.back();
				}
				else {
					FailureHelper.failTest("Expected result value '" + resultValue + "' not found in Jump to Results screen.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyDrillDown(String gridId, String resultValue, String valueColumn, String drillDownActionGroup, String drillDownAction,
			String resultExcelPath, String resultExcelFilename, String[] resultExcelSheetname, String[] resultExcelTCName, String[][] expressionType,
			String[][] filterName, String[][] expClause, String[][] expLeftIndent, String[][] expExpression1, String[][] expOperator,
			String[][] expExpression2, String[][] expRightIndent) throws Exception {
		try {
			if(ValidationHelper.isNotEmpty(resultExcelPath) && ValidationHelper.isNotEmpty(resultExcelSheetname)) {
				DataAssertion dataAssertion = new DataAssertion();
				BrowserHelper browser = new BrowserHelper();
				int rowNum = GridHelper.getRowNumber(gridId, resultValue, valueColumn);
				int waitTime = configProp.getCustomScreenWaitSec();
				
				if (rowNum > 0) {
					GridHelper.clickRow(gridId, rowNum, valueColumn);
					NavigationHelper.navigateToAction(drillDownActionGroup, drillDownAction);
					GenericHelper.waitForLoadmask(waitTime);
					
					if (ButtonHelper.isPresent("YesButton")) {
						ButtonHelper.click("YesButton");
						GenericHelper.waitForLoadmask(waitTime);
					}
					
					GenericHelper.waitForElement("JumpToResult_From", waitTime);
					for (int i = 0; i < resultExcelSheetname.length; i++) {
						if (ValidationHelper.isNotEmpty(resultExcelSheetname[i])) {
							ButtonHelper.click("ClearButton");
							GenericHelper.waitForLoadmask(searchScreenWaitSec);
							
							if (ValidationHelper.isNotEmpty(expressionType))
								applyCondition(expressionType[i], filterName[i], expClause[i], expLeftIndent[i], expExpression1[i], expOperator[i], expExpression2[i], expRightIndent[i]);
							else {
								ButtonHelper.click("SearchButton");
								GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
							}
							
							dataAssertion.testDataAssertion(resultExcelPath, resultExcelFilename, resultExcelSheetname[i], resultExcelTCName[i]);
						}
					}
					
					browser.back();
				}
				else {
					FailureHelper.failTest("Expected result value '" + resultValue + "' not found in Jump to Results screen.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyExportAllRows(String path, String WorkbookName, String workSheetName, String testCaseName, int occurance) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurance );
			
			for(int i = 0; i < excelData.get("Comparison File").size(); i++) {
				String measureRequestName = excelData.get("Measure Request Name").get(i);
				String exportDirectory = excelData.get("Export Directory").get(i);
				String downloadDirectory = excelData.get("Download Directory").get(i);
				String comparisonFile = excelData.get("Comparison File").get(i);
				String[] linesValuesToIgnore = testData.getStringValue(excelData.get("Lines Values To Ignore").get(i), firstLevelDelimiter);
				
				verifyExportAllRows(measureRequestName, exportDirectory, downloadDirectory, comparisonFile, linesValuesToIgnore);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void verifyExportAllRows(String measureRequestName, String exportDirectory, String downloadDirectory, String comparisonFile, String[] linesValuesToIgnore) throws Exception {
		try {
			JumpToSearchHelper jumpToSearch = new JumpToSearchHelper();
			jumpToSearch.verifyExportAllRows(measureRequestName, exportDirectory, downloadDirectory, comparisonFile, linesValuesToIgnore);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void applyCondition(String[] expressionType, String[] filterName, String[] expClause, String[] expLeftIndent, String[] expExpression1,
			String[] expOperator, String[] expExpression2, String[] expRightIndent) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(expressionType)) {
				GenericHelper.expandSearchFilterPanel();
				QueryFilterHelper queryFilter = new QueryFilterHelper();
				queryFilter.addCondition(expressionType, filterName, expClause, expLeftIndent, expExpression1, expOperator, expExpression2, expRightIndent);
				
				ButtonHelper.click("Search");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}