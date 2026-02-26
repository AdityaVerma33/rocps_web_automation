package com.subex.automation.helpers.application;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridCheckBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.db.TableHelper;
import com.subex.automation.helpers.scripts.ExecuteScript;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class ROCHelper extends AcceptanceTest {

	public String getTomcatContextPath() throws Exception {
		try {
			String[] tomcat = new String[100];
			String clientURL = configProp.getClientURL();
			
			if (clientURL.contains("/")) {
				tomcat = clientURL.split("/");
			}
			else {
				tomcat = clientURL.split("\\\\");
			}
			
			return tomcat[3];
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String readProductVersion() throws Exception {
		try{
			String product = ValidationHelper.checkProduct(configProp.getProduct());
			
			if (!product.equalsIgnoreCase("FM")) {
				String[] versionNumber = null;
				String tomcatPath = configProp.getTomcatPath();
				
				if (ValidationHelper.isNotEmpty(tomcatPath)) {
					String versionFileName = GenericHelper.getPath(applicationOS, tomcatPath + "\\webapps\\" + getTomcatContextPath() + "\\WEB-INF\\classes\\version.txt");
					
					if (applicationOS.equalsIgnoreCase("Windows"))
						versionNumber = FileHelper.readFileContent(applicationOS, versionFileName);
					else {
						String command = "cat " + versionFileName;
						RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
						versionNumber = remoteMachine.executeScripts(command, false);
					}
					
					if (ValidationHelper.isNotEmpty(versionNumber) && !versionNumber[0].contains("No such file or directory"))
						return versionNumber[0];
					else
						return null;
				}
				else
					return null;
			}
			else
				return "FM";
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getDeployPath(String deploy) throws Exception {
		try {
			if (applicationOS.equalsIgnoreCase("Windows")) {
				if (FileHelper.checkDirectoryExists(deploy + "\\bin"))
					deployPath = deploy;
				else if (FileHelper.checkDirectoryExists(deploy + "\\deploy\\bin"))
					deployPath = deploy + "\\deploy";
				else if (FileHelper.checkDirectoryExists(deploy + "\\deploy\\deploy\\bin"))
					deployPath = deploy + "\\deploy\\deploy";
			}
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				if (remoteMachine.checkDirectoryExists(deploy + "/bin"))
					deployPath = deploy;
				else if (remoteMachine.checkDirectoryExists(deploy + "/deploy/bin"))
					deployPath = deploy + "/deploy";
				else if (remoteMachine.checkDirectoryExists(deploy + "/deploy/deploy/bin"))
					deployPath = deploy + "/deploy/deploy";
			}
			
			if (deployPath == null)
				deployPath = GenericHelper.getPath(applicationOS, configProp.getDeployPath());
			
			return deployPath;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * Wait for gxt loading icon to disappear.
	 * @throws Exception 
	 * 
	 */
	public void waitForGXTLoading() throws Exception {
		long ctime = ( new Date() ).getTime();
		getAlert();
		final int waitTime = 300100;
		
		if (ElementHelper.isElementPresent("GXT_Loading_Mask")) {
			ElementHelper.waitForAttribute("GXT_Loading_Mask", "style", "display: none;", ctime);
		}
		
		ctime = ( new Date() ).getTime();
		while(ElementHelper.getXpathCount("Application_Screen") < 2)
		{
			if ( ( ( new Date() ).getTime() - ctime ) > waitTime ) {
				FailureHelper.failTest("Failed to proceed - loading timed out (" + waitTime + " ms)");
			}
		}
	}
	
	public void getAlert() throws Exception {
		JavascriptExecutor jsExecutor = null;
		
		try{
			jsExecutor = (JavascriptExecutor) driver;
			if((Boolean) jsExecutor.executeScript("return window.alert != undefined")) {
				jsExecutor.executeScript("window.alert = function(){}");
				jsExecutor.executeScript("window.confirm = function(){return true;}");
//				Log4jHelper.logInfo("***Windows Alert accepted***");
			}
		}
		catch(NoAlertPresentException noAlert) {
			// Skipping if no alert found
		}
		catch (WebDriverException e) {
			if((Boolean) jsExecutor.executeScript("return window.alert != undefined")) {
				jsExecutor.executeScript("window.alert = function(){}");
				jsExecutor.executeScript("window.confirm = function(){return true;}");
//				Log4jHelper.logInfo("***Windows Alert accepted***");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void closeWindowsPopup() throws Exception {
		JavascriptExecutor jsExecutor = null;
		
		try{
			jsExecutor = (JavascriptExecutor) driver;
			if((Boolean) jsExecutor.executeScript("return window.location != undefined"))
				jsExecutor.executeScript("window.open(window.location, '_self').close();");
		}
		catch(NoSuchWindowException noWindow) {
			// Skipping if no popup found
		}
		catch (WebDriverException e) {
			if((Boolean) jsExecutor.executeScript("return window.location != undefined"))
				jsExecutor.executeScript("window.open(window.location, '_self').close();");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getPartitionName(String tableName, String[] dbDetails) throws Exception {
		ResultSet rs = null;
		try {
			TableHelper tableHelper = new TableHelper();
			String actualTableName = tableHelper.checkTableInstExists(tableName, dbDetails);
			
			if (actualTableName != null) {
				String query = "SELECT TCL_NAME FROM TABLE_COLUMN WHERE TCL_ID IN "
								+ "(SELECT TIN_PARTITION_TCL_ID FROM TABLE_INST WHERE TIN_TABLE_NAME LIKE '" + actualTableName +"')";
				rs = ExecuteScript.exeQuery(query, dbDetails);
				String partitionColumn = null;
				
				if (rs != null) {
					if (rs.next())
						partitionColumn = rs.getString("TCL_NAME");
				}
				
				return partitionColumn;
			}
			else {
				FailureHelper.failTest("Table '" + tableName + "' does not exist in the System.");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		finally {
			ExecuteScript.closeConnection(rs);
		}
	}
	
	public Wait<WebDriver> waitFor()
    {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>( driver );
		wait.withTimeout( Duration.ofSeconds(20) );
		wait.pollingEvery( Duration.ofSeconds(5) );
		wait.ignoring( NoSuchElementException.class );
		wait.ignoring( ElementNotInteractableException.class );
		return wait;
    }
   
   /*
	 * This method is used to handle failure conditions like previous test failures, Session Expiry during execution.
	 * @throws Exception
	 */
	public void handleFailures() throws Exception {
		try {
			handleDetailScreen();
			
			if (!ButtonHelper.isPresent("NavigationMenu")) {
				if (TextBoxHelper.isPresent("Login_Username_TextBox")) {
					LoginHelper login = new LoginHelper();
					login.loginWithConfigPropertyDetails();
				}
				else {
					BrowserHelper browser = new BrowserHelper();
					browser.refreshWithoutCheck();
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void handleDetailScreen() throws Exception {
		try {
			int count = 1;
			while (count < 2) {
				ElementHelper.pressEscape();
				
				if (ButtonHelper.isPresent("CancelButton")) {
					ButtonHelper.click("CancelButton");
				}
				
				if (ButtonHelper.isPresent("DiscardButton")) {
					ButtonHelper.click("DiscardButton");
				}
				
				if (ButtonHelper.isPresent("YesButton")) {
					ButtonHelper.click("YesButton");
				}
				
				if (ButtonHelper.isPresent("CloseButton")) {
					ButtonHelper.click("CloseButton");
				}
				
				if (ButtonHelper.isPresent("OKButton")) {
					ButtonHelper.click("OKButton");
				}
				
				if (ButtonHelper.isPresent("Dialog_Close_Icon")) {
					ButtonHelper.click("Dialog_Close_Icon");
				}
				
				count++;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exitDetailScreen() throws Exception {
		try {
			List<WebElement> dialogs = ElementHelper.getDialogElements();
			
			if (dialogs != null && dialogs.size() > 0) {
				while (true) {
					if (dialogs != null && dialogs.size() > 0)
						closeDialogs(dialogs);
					else
						break;
					
					dialogs = ElementHelper.getDialogElements();
				}
			}
			else {
				closeDetailScreen();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void closeDialogs(List<WebElement> dialogs) throws Exception {
		try {
			for (int i = dialogs.size()-1; i >= 0; i--) {
				WebElement dialog = dialogs.get(i);
				closeDialog(dialog);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void closeDialog(WebElement dialog) throws Exception {
		try {
			boolean isDialogOpen = ElementHelper.isClickable(dialog);
			
			if (isDialogOpen) {
				if (ElementHelper.isElementPresent(dialog, "Dialog_Close_Icon")) {
					WebElement element = ElementHelper.getElement(dialog, "Dialog_Close_Icon");
					String isHidden = ElementHelper.getAttribute(element, "aria-hidden");
					
					if (isHidden == null) {
						ElementHelper.click(element);
						discardChanges();
						
						boolean isClickable = ElementHelper.isClickable(dialog);
						if (isClickable)
							isDialogOpen = true;
						else
							isDialogOpen = false;
					}
				}
	
				if (isDialogOpen) {
					String[] locators = {"CancelButton", "OKButton", "CloseButton"};
					
					for (int i = 0; i < locators.length; i++) {
						WebElement element = ElementHelper.getElement(dialog, locators[i]);
						
						if (element != null && ElementHelper.isClickable(element)) {
							ElementHelper.click(element);
							discardChanges();
							boolean isClickable = ElementHelper.isClickable(dialog);

							if (isClickable)
								isDialogOpen = true;
							else
								isDialogOpen = false;
							if (!isDialogOpen)
								break;
						}
					}
				}
			}
		} catch (WebDriverException e) {
			ElementHelper.pressEscape(dialog);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void closeDetailScreen() throws Exception {
		try {
			WebElement element = ElementHelper.getElement("Dialog_Close_Icon");
			
			if (element != null) {
				String isHidden = ElementHelper.getAttribute(element, "aria-hidden");
				
				if (isHidden == null) {
					ElementHelper.click(element);
					discardChanges();
				}
			}
			
			if (ButtonHelper.isPresent("CancelButton")) {
				ButtonHelper.click("CancelButton");
				discardChanges();
			}
			else if (ButtonHelper.isPresent("OKButton")) {
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
			else if (ButtonHelper.isPresent("CloseButton")) {
				ButtonHelper.click("CloseButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void discardChanges() throws Exception {
		try {
			if (PopupHelper.isPresent()) {
				if (ButtonHelper.isPresent("DiscardButton"))
					ButtonHelper.click("DiscardButton");
				else if (ButtonHelper.isPresent("YesButton"))
					ButtonHelper.click("YesButton");
				else {
					if (ButtonHelper.isPresent("DiscardButton"))
						ButtonHelper.click("DiscardButton");
					else
						ButtonHelper.click("YesButton");
				}
				
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void handleSessionTimeout() throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			
			if (ElementHelper.isElementPresent("LoginScreen") && login.isLoginSuccessful()) {
				String screenName = NavigationHelper.getScreenName();
				
				login.login();
				
				if (ValidationHelper.isNotEmpty(screenName) && (!screenName.startsWith("New ") && !screenName.startsWith("Edit ") && !screenName.startsWith("View ")))
					NavigationHelper.navigateToScreen(screenName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void licenseExpiryPopup() throws Exception {
		try {
			if (ElementHelper.isElementPresent("License_Expired_Dialog1") || ElementHelper.isElementPresent("License_Expired_Dialog2")) {
				boolean navigationMenuOpen = false;
				
				if (ElementHelper.isElementPresent("NavigationMenu_Wrapper")) {
					navigationMenuOpen = true;
					ElementHelper.pressEscape("NavigationMenu_Wrapper");
				}
				
				String[] dialogButtons = {"License_Expired_Dialog_OKButton2", "License_Expired_Dialog_OKButton1"};
				for (int i = 0; i < dialogButtons.length; i++) {
					while(ButtonHelper.isPresent(dialogButtons[i])) {
						ButtonHelper.click(dialogButtons[i]);
					}
				}
				
				if (navigationMenuOpen)
					ElementHelper.pressEscape("NavigationMenu_Wrapper");
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void updateCollectionTimes(String frequencyMultiplier, String frequency, String nextSchedule, String[][] dayGroups) throws Exception {
		try {
			TextBoxHelper.type("Scheduling_FrequencyMultiplier", frequencyMultiplier);
			ComboBoxHelper.select("Scheduling_Frequency", frequency);
			TextBoxHelper.type("Scheduling_NextScheduled", nextSchedule);
			
			addDayGroup("DayGroup_Grid", dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private WebElement getCellElement(String gridId, int rowIndex, int colIndex) throws Exception {
		try {
			WebElement element = GridElementHelper.getCellElement(gridId, rowIndex, colIndex);
			
			if (!ElementHelper.isClickable(element)) {
				ElementHelper.scrollToView(element, false);
				new Actions(driver).moveToElement(element).click().perform();
				Thread.sleep(1000);
				element = GridElementHelper.getCellElement(gridId, rowIndex, colIndex);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private WebElement getCellElement(String gridWrapper, String gridId, int rowIndex, int colIndex) throws Exception {
		try {
			WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, rowIndex, colIndex);
			
			if (!ElementHelper.isClickable(element)) {
				ElementHelper.scrollToView(element, false);
				new Actions(driver).moveToElement(element).click().perform();
				Thread.sleep(1000);
				element = GridElementHelper.getCellElement(gridWrapper, gridId, rowIndex, colIndex);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void clickAction(String action) throws Exception {
		try {
			if ( action.equalsIgnoreCase( "yes" ) ) {
				if (ButtonHelper.isPresent("DayGroup_YesButton"))
					ButtonHelper.click( "DayGroup_YesButton" );
				else
					ButtonHelper.click( "DayGroup_YesButton2" );
			}
			else if ( action.equalsIgnoreCase( "No" ) ) {
				if (ButtonHelper.isPresent("DayGroup_NoButton"))
					ButtonHelper.click( "DayGroup_NoButton" );
				else
					ButtonHelper.click( "DayGroup_NoButton2" );
			}
			else
				ButtonHelper.click(action);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addDayGroupAction(String gridId, int rowIndex, String[] dayGroups) throws Exception {
		try {
			for ( int j = 1; j < dayGroups.length; j += 3 ) {
				int col1 = Integer.parseInt( dayGroups[j] );
				int col2 = Integer.parseInt( dayGroups[j+1] );
				
				if (col1 == 1 && col2 == 49) {
					GridHelper.rightClick(gridId, rowIndex, 1);
				}
				else {
					WebElement element1 = getCellElement(gridId, rowIndex, col1);
					MouseHelper.mouseDown( element1 );
					
					WebElement element2 = getCellElement(gridId, rowIndex, col2);
					MouseHelper.mouseUp( element2 );
					
					element2 = getCellElement(gridId, rowIndex, col2);
					MouseHelper.rightClick( element2 );
				}
				
				clickAction(dayGroups[j+2]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void addDayGroupAction(String gridWrapper, String gridId, int rowIndex, String[] dayGroups) throws Exception {
		try {
			for ( int j = 1; j < dayGroups.length; j += 3 ) {
				int col1 = Integer.parseInt( dayGroups[j] );
				int col2 = Integer.parseInt( dayGroups[j+1] );
				
				if (col1 == 1 && col2 == 49) {
					GridHelper.rightClick(gridWrapper, gridId, rowIndex, 1);
				}
				else {
					WebElement element1 = getCellElement(gridWrapper, gridId, rowIndex, col1);
					MouseHelper.mouseDown( element1 );
					
					WebElement element2 = getCellElement(gridWrapper, gridId, rowIndex, col2);
					MouseHelper.mouseUp( element2 );
					
					element2 = getCellElement(gridWrapper, gridId, rowIndex, col2);
					MouseHelper.rightClick( element2 );
				}
				
				clickAction(dayGroups[j+2]);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDayGroup(String gridId, String value) throws Exception {
		try {
			String[][] dayGroups = StringHelper.convertTo2DArray(value, configProp.getFirstLevelDelimiter(), configProp.getSecondLevelDelimiter());
			
			addDayGroup(gridId, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDayGroup(String gridWrapper, String gridId, String value) throws Exception {
		try {
			String[][] dayGroups = StringHelper.convertTo2DArray(value, configProp.getFirstLevelDelimiter(), configProp.getSecondLevelDelimiter());
			
			addDayGroup(gridWrapper, gridId, dayGroups);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDayGroup(String gridId, String[][] dayGroups) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(dayGroups)) {
				gridId = GenericHelper.getORProperty(gridId);
				
				for ( int i = 0; i < dayGroups.length; i++ ) {
					int rowIndex = GridHelper.getRowNumber( gridId, dayGroups[i][0], 1 );
					
					if (rowIndex == 0) {
						String actionLocator = or.getProperty("DayGroup_Add");
						GridHelper.rightClickSubMenu( gridId, actionLocator, dayGroups[i][0] );
						rowIndex = GridHelper.getRowNumber( gridId, dayGroups[i][0], 1 );
					}
					
					addDayGroupAction(gridId, rowIndex, dayGroups[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDayGroup(String gridWrapper, String gridId, String[][] dayGroups) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(dayGroups)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				
				for ( int i = 0; i < dayGroups.length; i++ ) {
					int rowIndex = GridHelper.getRowNumber( gridWrapper, gridId, dayGroups[i][0], 1 );
					
					if (rowIndex == 0) {
						String actionLocator = or.getProperty("DayGroup_Add");
						GridHelper.rightClickSubMenu( gridWrapper, gridId, actionLocator, dayGroups[i][0] );
						rowIndex = GridHelper.getRowNumber( gridWrapper, gridId, dayGroups[i][0], 1 );
					}
					
					addDayGroupAction(gridWrapper, gridId, rowIndex, dayGroups[i]);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void addDayGroup(String gridId, String[] value, String delimiter) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				gridId = GenericHelper.getORProperty(gridId);
				
				for ( int i = 0; i < value.length; i++ ) {
					String[] dayGroups = value[i].split(delimiter, -1);
					int rowIndex = GridHelper.getRowNumber( gridId, dayGroups[0], 1 );
					
					if (rowIndex == 0) {
						String actionLocator = or.getProperty("DayGroup_Add");
						GridHelper.rightClickSubMenu( gridId, actionLocator, dayGroups[0] );
						rowIndex = GridHelper.getRowNumber( gridId, dayGroups[0], 1 );
					}
					
					addDayGroupAction(gridId, rowIndex, dayGroups);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add Team Notification across ROC screens.
	 * @param team - Array of Team names that has to be added for Notification.
	 * @param teamOptions - 2 dimensional array of options to select for each team in the order Column Header, Value.
	 * Eg., team = {"Team1", "Team2"}, teamOptions = {{"Email on New", "Sms on New"}, {"Email on New", "Sms on New"}}
	 * @throws Exception
	 */
	public void addTeamNotification(String[] team, String[][] teamOptions) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(team)) {
				for ( int i = 0; i < team.length; i++ ) {
					int rowNum = GridHelper.getRowNumber( "TeamNotification_Grid", team[i], "Team" );
					
					if (rowNum == 0) {
						if (ButtonHelper.isDisabled("TeamNotification_Add")) {
							FailureHelper.failTest("Team Notification 'Add' button is disabled.");
						}
						else {
							ButtonHelper.click("TeamNotification_Add");
							rowNum = GridHelper.getRowCount( "TeamNotification_Grid" );
							GridHelper.updateGridComboBox("TeamNotification_Grid", "TeamNotification_Team", rowNum, "Team", "Supervisor", team[i]);
						}
					}
					
					for ( int j = 0; j < teamOptions[i].length; j++ ) {
						GridCheckBoxHelper.check("TeamNotification_Grid", rowNum, teamOptions[i][j]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to add User Notification across ROC screens.
	 * @param user - Array of Usernames that has to be added for Notification.
	 * @param userOptions - 2 dimensional array of options to select for each user in the order Column Header, Value.
	 * Eg., user = {"testuser1", "testuser2"}, userOptions = {{"Email on New", "Sms on New"}, {"Email on New", "Sms on New"}}
	 * @throws Exception
	 */
	public void addUserNotification(String[] user, String[][] userOptions) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(user)) {
				for ( int i = 0; i < user.length; i++ ) {
					int rowNum = GridHelper.getRowNumber( "UserNotification_Grid", user[i], "User" );
					
					if (rowNum == 0) {
						if (ButtonHelper.isDisabled("UserNotification_Add")) {
							FailureHelper.failTest("User Notification 'Add' button is disabled.");
						}
						else {
							ButtonHelper.click("UserNotification_Add");
							rowNum = GridHelper.getRowCount( "UserNotification_Grid" );
							GridHelper.updateGridComboBox("UserNotification_Grid", "UserNotification_User", rowNum, "User", "First Name", user[i]);
						}
					}

					for ( int j = 0; j < userOptions[i].length; j++ ) {
						GridCheckBoxHelper.check("UserNotification_Grid", rowNum, userOptions[i][j]);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}