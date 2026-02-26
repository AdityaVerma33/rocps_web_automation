package com.subex.automation.helpers.application.screens;

import java.util.ArrayList;
import java.util.HashMap;

import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ChangePasswordHelper extends ROCAcceptanceTest {

	public void changePassword(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception
	{
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String userName = excelData.get("Username").get(i);
				String currentPassword = excelData.get("Current Password").get(i);
				String newPassword = excelData.get("New Password").get(i);
				String confirmPassword = excelData.get("Confirm Password").get(i);
				
				changePassword(userName, currentPassword, newPassword, confirmPassword);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void changePassword(String userName, String currentPassword, String newPassword, String confirmPassword) throws Exception
	{
		try {
			NavigationHelper.navigateToScreen( "Change Password", "Change Password" );
			GenericHelper.waitForElement("ChangePassword_OK", searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Change Password"));
			
			LoginHelper login = new LoginHelper();
			login.updatePassword(userName, currentPassword, newPassword, confirmPassword);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}