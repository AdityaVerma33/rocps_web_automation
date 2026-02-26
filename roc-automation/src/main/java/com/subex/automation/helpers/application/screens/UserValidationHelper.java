package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class UserValidationHelper extends ROCAcceptanceTest {

	public void updateUserValidation(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Force Alpha Numeric").size(); i++) 
			{
				boolean forceAlphaNumeric = ValidationHelper.isTrue(excelData.get("Force Alpha Numeric").get(i));
				String userNameMaxLength = excelData.get("Username Maximum Length").get(i);
				String userNameMinLength = excelData.get("Username Minimum Length").get(i);
				
				updateUserValidation(forceAlphaNumeric, userNameMaxLength, userNameMinLength);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateUserValidation(boolean forceAlphaNumeric, String userNameMaxLength, String userNameMinLength) throws Exception
	{
		try {
			String detailScreenTitle = navigateToUserValidation();
			
			if (forceAlphaNumeric)
				PropertyGridHelper.checkCheckBox("Force Alpha Numeric");
			else
				PropertyGridHelper.unCheckCheckBox("Force Alpha Numeric");
			
			PropertyGridHelper.typeInTextBox("User Name Maximum Length *", userNameMaxLength);
			PropertyGridHelper.typeInTextBox("User Name Minimum Length *", userNameMinLength);
			
			saveUserValidation(detailScreenTitle);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String navigateToUserValidation() throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Users", "User Search" );
			
			if(NavigationHelper.isActionPresent("Special Actions")) {
				NavigationHelper.navigateToAction("Special Actions");
				
				if(NavigationHelper.isActionPresent("User Validations")) {
					NavigationHelper.navigateToAction("User Validations");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					String detailScreenTitle = NavigationHelper.getScreenTitle();
					
					return detailScreenTitle;
				}
				else {
					FailureHelper.failTest("Action 'User Validations' is not found below 'Special Actions' in Users screen.");
				}
			}
			else {
				FailureHelper.failTest("Action 'Special Actions' is not found in Users screen.");
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveUserValidation(String detailScreenTitle) throws Exception {
		try {
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			assertTrue(LabelHelper.isTitleNotPresent(detailScreenTitle), "User Validation save did not happen.");
			Log4jHelper.logInfo("User Validation updated successfully.");
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}