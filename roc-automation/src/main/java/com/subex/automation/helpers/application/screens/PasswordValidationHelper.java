package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.scripts.TestDataHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PasswordValidationHelper extends ROCAcceptanceTest {

	public void updatePasswordValidation(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			TestDataHelper testData = new TestDataHelper();
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Name").size(); i++) 
			{
				String name = excelData.get("Name").get(i);
				String component = excelData.get("Component").get(i);
				String[][] propertyValues = testData.getStringValue(excelData.get("Property Values").get(i), firstLevelDelimiter, secondLevelDelimiter);
				
				updatePasswordValidation(name, component, propertyValues);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updatePasswordValidation(String name, String component, String[][] propertyValues) throws Exception
	{
		try {
			String detailScreenTitle = navigateToPasswordValidation();
			int row = GridHelper.getRowNumber("PasswordValidation_Grid", name, "Password  Validation");
			
			if (row == 0) {
				ButtonHelper.click("PasswordValidation_Add");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				
				ComboBoxHelper.select("PasswordValidation_Popup", "PasswordValidation_Component", component);
				TextBoxHelper.type("PasswordValidation_Popup", "PasswordValidation_Name", name);
			}
			else {
				GridHelper.clickRow("PasswordValidation_Grid", row, 1);
				ButtonHelper.click("PasswordValidation_Edit");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			
			String detailScreenTitle1 = NavigationHelper.getScreenTitle();
			
			if (ValidationHelper.isNotEmpty(propertyValues)) {
				for (int i = 0; i < propertyValues.length; i++) {
					if (ValidationHelper.isNotEmpty(propertyValues[i]) && propertyValues[i].length > 1) {
						PropertyGridHelper.updateProperty("PasswordValidation_Popup", propertyValues[i][0], propertyValues[i][1], null);
					}
				}
			}
			
			savePasswordValidation(detailScreenTitle1);
			
			UserValidationHelper userValidation = new UserValidationHelper();
			userValidation.saveUserValidation(detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String navigateToPasswordValidation() throws Exception {
		try {
			UserValidationHelper userValidation = new UserValidationHelper();
			String detailScreenTitle = userValidation.navigateToUserValidation();
			TabHelper.gotoTab("Password Validation");
			
			return detailScreenTitle;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void savePasswordValidation(String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("PasswordValidation_OK");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "Password Validation save did not happen.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}