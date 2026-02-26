package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class StandardExpressionHelper extends ROCAcceptanceTest {
	
	public void createStandardExpression(String path, String WorkbookName, String workSheetName, String testCaseName, int occurence) throws Exception {
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, WorkbookName, workSheetName, testCaseName, occurence );
	
			for(int i = 0; i < excelData.get("Function Name").size(); i++)
			{
				String partition = excelData.get("Partition").get(i);
				String functionName = excelData.get("Function Name").get(i);
				String description = excelData.get("Description").get(i);
				String expression = excelData.get("Expression").get(i);
				String returnType = excelData.get("Return Type").get(i);
				String[] parameterLabel = testData.getStringValue(excelData.get("Parameter Label").get(i), firstLevelDelimiter);
				String[] parameterDatatype = testData.getStringValue(excelData.get("Parameter Datatype").get(i), firstLevelDelimiter);
				
				createStandardExpression(partition, functionName, description, expression, returnType, parameterLabel, parameterDatatype);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void createStandardExpression(String partition, String functionName, String description, String expression, String returnType,
			String[] parameterLabel, String[] parameterDatatype) throws Exception {
		try {
			int row = navigateToStandardExpression(functionName);
			boolean isPresent = NavigationHelper.navigateToNewOrEdit(row, partition, "Standard Expression", "StandardExp_FunctionName");
			String detailScreenTitle = NavigationHelper.getScreenTitle();
			
			TextBoxHelper.type("StandardExp_FunctionName", functionName);
			TextBoxHelper.type("StandardExp_Description", description);
			TextBoxHelper.type("StandardExp_Expression", expression);
			ComboBoxHelper.select("StandardExp_ReturnType", returnType);
			
			if (ValidationHelper.isNotEmpty(parameterLabel) && !parameterLabel.equals("")) {
				String gridId = "StandardExp_Parameters_Grid";
				for (int i = 0; i < parameterLabel.length; i++) {
					int rowNum = GridHelper.getRowNumber("StandardExp_Parameters_Grid", parameterLabel[i], "Label");
					if (rowNum == 0) {
						ButtonHelper.click("StandardExp_Parameters_Add");
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
					
					rowNum = i + 1;
					GridHelper.updateGridTextBox(gridId, "StandardExp_Parameter_Label", rowNum, "Label", "Name", parameterLabel[i]);
					
					GridHelper.updateGridComboBox(gridId, "StandardExp_Parameter_DataType", rowNum, "Data Type", "Name", parameterDatatype[i]);
					
					assertTrue(GridHelper.isValuePresent("StandardExp_Parameters_Grid", rowNum, parameterLabel[i], "Label"), "Label '" + parameterLabel[i] + "' is not found in grid.");
					assertTrue(GridHelper.isValuePresent("StandardExp_Parameters_Grid", rowNum, parameterDatatype[i], "Data Type"), "Data Type '" + parameterDatatype[i] + "' is not found in grid.");
				}
			}

			saveStandardExpression(functionName, detailScreenTitle, isPresent);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private int navigateToStandardExpression(String functionName) throws Exception {
		try {
			NavigationHelper.navigateToScreen( "Standard Expressions", "Standard Expression Search" );
			String fnName = functionName;
			if (!functionName.startsWith("Ste_"))
				fnName = "Ste_" + functionName;
			int row = SearchGridHelper.gridFilterSearchWithTextBox("StandardExp_FunctionName", fnName, "Name");
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void saveStandardExpression(String functionName, String detailScreenTitle, boolean isPresent) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(1000);
			
			if (PopupHelper.isTextPresent("Expression is being used at many places. Do you want to continue?")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Standard Expression save did not happen.");
			ButtonHelper.click("SearchButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			String fnName = functionName;
			if (!functionName.startsWith("Ste_"))
				fnName = "Ste_" + functionName;
			assertTrue(GridHelper.isValuePresent("SearchGrid", fnName, "Name"), "Value '" + fnName + "' is not found in grid.");
			if (isPresent)
				Log4jHelper.logInfo("Standard Expression '" + fnName + "' updated.");
			else
				Log4jHelper.logInfo("Standard Expression '" + fnName + "' created.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}