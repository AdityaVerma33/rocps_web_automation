package com.subex.automation.helpers.application;

import java.io.IOError;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.ComboBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.DBHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LoginHelper extends AcceptanceTest {
	private static String applicationName = null;
	private static String userName = null;
	private static String userPassword = null;
	private static boolean displayLoginInfo = false;
	private static String product = "ROC";
	private static boolean loginSuccess = true;
	
	private void setParameters(String appName, String username, String password) throws Exception {
		try {
			applicationName = appName;
			userName = username;
			userPassword = password;
			displayLoginInfo = getDisplayLoginProperty();
			product = ValidationHelper.checkProduct(configProp.getProduct());
			loginSuccess = true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void login() throws Exception {
		try {
			if (ElementHelper.isElementPresent("LoginScreen")) {
				if (applicationName == null || userName == null || userPassword == null) {
					String password = configProp.getApplicationPassword();
					login(configProp.getApplicationName(), configProp.getApplicationUsername(), password, password + "1");
				}
				else
					login(applicationName, userName, userPassword, null);
				
				currentWindowTitle = driver.getTitle();
				handleFMOpenAlarmsPopup();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void login(String path, String fileName, String sheetName, String testCaseName, int occurance) throws Exception {
		try {
			ExcelReader excelReader = new ExcelReader();
			HashMap<String, ArrayList<String>> excelData = excelReader.readDataByColumn( path, fileName, sheetName, testCaseName, occurance );
	
			for(int i = 0; i < excelData.get("Username").size(); i++) 
			{
				String appName = excelData.get("Application Name").get(i);
				String userName = excelData.get("Username").get(i);
				String password = excelData.get("Password").get(i);
				String resetPassword = excelData.get("Reset Password").get(i);
				
				login(appName, userName, password, resetPassword);
				
				if (TextBoxHelper.isPresent("Login_Username_TextBox"))
					login(appName, userName, resetPassword, resetPassword);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
		
	public void login(String appName, String username, String password, String resetPassword) throws Exception {
		try {
			if (!TextBoxHelper.isPresent("Login_Username_TextBox")) {
				String loggedUser = getLoginUser();
				if (ValidationHelper.isNotEmpty(loggedUser) && !loggedUser.equals(username))
					logout();
			}
			
			if (TextBoxHelper.isPresent("Login_Username_TextBox")) {
				setLoginDetails(appName, username, password);
				
				if (loginSuccess) {
					setParameters(appName, username, password);
					
					if(displayLoginInfo)
						acceptLoginSuccessfulPopUp(searchScreenWaitSec);
					
					passwordReset(userName, userPassword, resetPassword);
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void loginWithConfigPropertyDetails() throws Exception {
		try {
			if (!ElementHelper.isElementPresent("LoginScreen")) {
				String loggedUser = getLoginUser();
				if (ValidationHelper.isNotEmpty(loggedUser) && !loggedUser.equals(configProp.getApplicationUsername()))
					logout();
			}
			
			String password = configProp.getApplicationPassword();
			login(configProp.getApplicationName(), configProp.getApplicationUsername(), password, password + "1");
			
			currentWindowTitle = driver.getTitle();
			handleFMOpenAlarmsPopup();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getLoginScreenError() throws Exception {
		try {
			String value = LabelHelper.getText("errorMessage");
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isLoginSuccessful() throws Exception {
		try {
			String value = getLoginScreenError();
			
			if (ValidationHelper.isNotEmpty(value)) {
//				if (value.contains("Invalid Credentials"))
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setLoginDetails(String applicationName, String userName, String password) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(applicationName)) {
				WebElement application = ElementHelper.getElement("Login_Application_ComboBox");
				ComboBoxElementHelper.select(application, applicationName);
	        }
	        
	        TextBoxHelper.type( "Login_Username_TextBox", userName );
	        TextBoxHelper.type( "Login_Password_TextBox", password );
	        ButtonHelper.click("LoginButton");
	        Thread.sleep(2000);
	        
	        loginSuccess = isLoginSuccessful();
	        
	        if (loginSuccess) {
		        ROCHelper rocHelper = new ROCHelper();
		        rocHelper.waitForGXTLoading();
		        rocHelper.waitForGXTLoading();
		        GenericHelper.waitForLoadmask(searchScreenWaitSec);
		        Thread.sleep(1000);
		        GenericHelper.waitForLoadmask(searchScreenWaitSec);
	        }
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void logout() throws Exception {
		try {
			if (ImageHelper.isPresent("Options_Icon")) {
				NavigationHelper.navigateToScreen("Logout");
				GenericHelper.waitForElement("YesButton", searchScreenWaitSec);
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				GenericHelper.waitForElement("Login_Username_TextBox", searchScreenWaitSec);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void passwordReset(String username, String currentPassword, String newPassword) throws Exception {
		try {
			if(PopupHelper.isTextPresent("Your password has expired. You must reset your password before continuing.")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForElement("ChangePassword_CurrentPassword", searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent("Change Password"));
				
				if (ValidationHelper.isEmpty(newPassword))
					newPassword = "welcome1";
				
				updatePassword(username, currentPassword, newPassword, newPassword);
            
				userPassword = newPassword;
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updatePassword(String userName, String currentPassword, String newPassword, String confirmPassword) throws Exception
	{
		try {
			if (TextBoxHelper.isEnabled("ChangePassword_CurrentPassword"))
				TextBoxHelper.type("ChangePassword_CurrentPassword", currentPassword);
			TextBoxHelper.type("ChangePassword_NewPassword", newPassword);
			
			if (ValidationHelper.isEmpty(confirmPassword))
				confirmPassword = newPassword;
			TextBoxHelper.type("ChangePassword_ConfirmPassword", confirmPassword);
			
			saveChangePassword(userName);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void saveChangePassword(String userName) throws Exception
	{
		boolean hasFailed = false;
		
		try {
			ButtonHelper.click("ChangePassword_OK");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForElementToDisappear("ChangePassword_CurrentPassword", detailScreenWaitSec);
			Thread.sleep(1000);
			GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			
			assertTrue(PopupHelper.isTextPresent("The password for user " + userName + " has been successfully changed"),
					"Change Password save did not happen.");
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Log4jHelper.logInfo("The password for user " + userName + " has been successfully changed");
		} catch (AssertionError e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			hasFailed = true;
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			if (hasFailed) {
				ROCHelper rocHelper = new ROCHelper();
				rocHelper.handleDetailScreen();
			}
		}
	}
		
	public void acceptLoginSuccessfulPopUp(int waitSec) throws Exception {
		try {
			for(int i = 0; i < waitSec; i++) {
				if(ButtonHelper.isPresent("NavigationMenu")) {
					while(ElementHelper.isElementPresent("License_Expired_Dialog2")) {
						ButtonHelper.click("License_Expired_Dialog_OKButton2");
					}
					
					while(ElementHelper.isElementPresent("License_Expired_Dialog1")) {
						ButtonHelper.click("License_Expired_Dialog_OKButton1");
					}
					
					while(ElementHelper.isElementPresent("Successfull_Login_Dialog")) {
						ButtonHelper.click("OKButton");
					}
					
					break;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getLoginUser() throws Exception {
		try {
			String userDetails = null;
			if (ElementHelper.isElementPresent("LoggedIn_User"))
				userDetails = LabelHelper.getText("LoggedIn_User").replace("Welcome ", "");
			
			return userDetails;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getLoginUserDetails1() throws Exception {
		DBHelper dbHelper = null;
		
		try {
			String[] userDetails = new String[3];
			userDetails[0] = LabelHelper.getText("LoggedIn_User").replace("Welcome ", "");
			dbHelper = DBHelper.connectToReferenceDB(false);
				
			if (dbHelper != null) {
				ResultSet rs = dbHelper.dbConnection.createStatement().executeQuery("select usr_forename, usr_surname from user_tbl where usr_name like '" + userDetails[0] + "'");
				
				if (rs != null) {
					while (rs.next()) {
						if (rs.getString("USR_FORENAME") != null) {
							userDetails[1] = rs.getString("USR_FORENAME");
						}
						if (rs.getString("USR_SURNAME") != null) {
							userDetails[2] = rs.getString("USR_SURNAME");
						}
					}
				}
			}
			
			return userDetails;
		} catch (SQLException e) {
			Log4jHelper.logInfo("Could not connect to db due to exception: " + e.getMessage());
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			if (dbHelper != null)
				dbHelper.closeConnection();
		}
	}
	
	public boolean getDisplayLoginProperty() throws Exception {
		DBHelper dbHelper = null;
		try {
			boolean displayLoginProperty = true;
			dbHelper = DBHelper.connectToReferenceDB(false);
			
			if (dbHelper != null) {
					ResultSet rs = dbHelper.dbConnection.createStatement().executeQuery("SELECT PRI_VALUE FROM PROPERTY_INST WHERE PRD_ID = (SELECT PRD_ID FROM PROPERTY_DFN WHERE PRD_NAME LIKE 'Display Login Information')");
					if (rs != null) {
						while (rs.next()) {
							if (rs.getString("PRI_VALUE") != null) {
								displayLoginProperty = ValidationHelper.isTrue(rs.getString("PRI_VALUE"));
							}
						}
					}
				}
			
			return displayLoginProperty;
		} catch (IOError e) {
			Log4jHelper.logInfo("Could not connect to db due to exception: " + e.getMessage());
			return true;
		} catch (SQLException e) {
			Log4jHelper.logInfo("Could not connect to db due to exception: " + e.getMessage());
			return true;
		} catch (Exception e) {
			Log4jHelper.logInfo(e.getMessage());
			return true;
		}
		finally {
			if (dbHelper != null)
				dbHelper.closeConnection();
		}
	}
	
	public void handleFMOpenAlarmsPopup() throws Exception {
		try {
			if (product.equals("FM")) {
				String screenName = NavigationHelper.getScreenTitle();
				
				if (ValidationHelper.isNotEmpty(screenName) && screenName.equals("Open Alarms")) {
					ButtonHelper.click("CancelButton");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getUserLoginDate(String dateFormat, Statement stmt, String ulgMessage) throws Exception {
		try {
			String qResult = null;
			String query = "SELECT TO_CHAR(MAX(ULG_DTTM), '" + dateFormat + "') AS RESULT FROM USER_LOGIN WHERE USR_ID = "
							+ "(SELECT USR_ID FROM USER_TBL WHERE USR_NAME LIKE '" + userName + "') AND ULG_MESSAGE like '" + ulgMessage + "%'";
			ResultSet successRS = stmt.executeQuery(query);
			
			if (successRS != null) {
				successRS.next();
				qResult = successRS.getString("RESULT");
			}
			
			return qResult;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}