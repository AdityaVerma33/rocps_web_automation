package com.subex.automation.helpers.performance;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.subex.automation.helpers.application.BrowserHelper;
import com.subex.automation.helpers.application.LoginHelper;
import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.NavigationMenuHelper;
import com.subex.automation.helpers.application.ROCHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.enums.ActionsEnum;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PerformanceReader extends ROCAcceptanceTest {
	
	private static String commonGroupLocator = null;
	private static String ruleGroupLocator = null;
	private static String editActionLocator = null;
	private static boolean checkEdit = true;
	private static boolean isFM = false;
	
	private FirefoxOptions setCommonCapabilities() throws Exception {
		try {
			String downloadPath = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Client_Downloads");
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			FirefoxProfile profile = AcceptanceTest.setFirefoxProfile();
			profile = AcceptanceTest.setFileDownloadProperties(profile, downloadPath);
			options.setProfile(profile);

			return options;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	@Test
	@Parameters ({"browser", "name"})
	public void readPerformance(String browserName,String testName) throws Exception {
		RemoteWebDriver driver =null;
		ExtentReports report = null;
		
		try {
			FirefoxOptions options = setCommonCapabilities();
			driver = new RemoteWebDriver(new     URL("http://localhost:4444/wd/hub"), options);
			
			driver.get( configProp.getClientURL() );
			String product = ValidationHelper.checkProduct(configProp.getProduct());
			if (product.equals("FM"))
				isFM = true;
			
			LoginHelper login = new LoginHelper();
			login.login();
			login.handleFMOpenAlarmsPopup();
			String directoryName = testName + "_" + browserName;
			report = initializeReport(directoryName, true);
			HarReader.generateHar(directoryName,"Home Page");
			
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			String[] screenNames = navigationMenu.getScreenNames();
			if (ValidationHelper.isNotEmpty(screenNames)) {
				commonGroupLocator = or.getProperty("GroupActionName").replace("actionName", ActionsEnum.COMMON_TASKS.getName());
				ruleGroupLocator = or.getProperty("GroupActionName").replace("actionName", "Rule Action");
				editActionLocator = or.getProperty("SubMenuActionName").replace("actionName", ActionsEnum.EDIT.getName());
				BrowserHelper browser = new BrowserHelper();
				
				for (int i = 0; i < 2 ; i++) {
					if (ValidationHelper.isNotEmpty(screenNames[i])) {
						
						if (i > 0 && (i % 105) == 0) {
							browser.refresh();
							login.logout();
							login.login();
						}
						
					
						capturePerformance(report, directoryName, screenNames[i]);
						ROCHelper rocHelper = new ROCHelper();
						rocHelper.exitDetailScreen();
					
					}
				}
				
				PerformanceReporter.addPerformance(directoryName);
			}
		}
		catch (Exception e) {
			PerformanceReporter.addPerformance();
			FailureHelper.setErrorMessage(e);
			ReportHelper.reportFailure(e);
		}
		finally {
			if (testReport != null)
				testReport = ReportHelper.endReport(report, testReport);	
		}
		
		stopDriver();
	}
	
	private void capturePerformance(ExtentReports report, String fileName, String screenName) throws Exception {
		ExtentTest testReport = null;	
	try {
		if (ValidationHelper.isNotEmpty(screenName)) {
			testCaseName = screenName;
			testReport = ReportHelper.startReport(report, testCaseName);
			
			// Search performance capture
			captureSearchPerformance(testReport, fileName, screenName);
			
			// Edit performance capture
			if (checkEdit)
				captureEditPerformance(testReport,fileName,screenName);
		}
	} catch (Exception e) {
		FailureHelper.setErrorMessage(e);
		ReportHelper.reportFailure(e);
	} finally {
		testReport = ReportHelper.endReport(report, testReport);
		ReportHelper.clearStepKeyContent();
		stepName = "";
	}
}
	
//	private static void capturePerformance(String screenName) throws Exception {
//		try {
//			if (ValidationHelper.isNotEmpty(screenName)) {
//				testCaseName = screenName;
//				testReport = ReportHelper.startReport(report, testCaseName);
//				
//				// Search performance capture
//				captureSearchPerformance(screenName);
//				
//				// Edit performance capture
//				if (checkEdit)
//					captureEditPerformance(screenName);
//			}
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			ReportHelper.reportFailure(e);
//		} finally {
//			testReport = ReportHelper.endReport(report, testReport);
//			ReportHelper.clearStepKeyContent();
//			stepName = "";
//		}
//	}
	
//	private static void capturePerformance(RemoteWebDriver driver,ExtentReports report, String fileName, String screenName) throws Exception {
//			ExtentTest testReport = null;	
//		try {
//			if (ValidationHelper.isNotEmpty(screenName)) {
//				testCaseName = screenName;
//				testReport = ReportHelper.startReport(report, testCaseName);
//				
//				// Search performance capture
//				captureSearchPerformance(driver,testReport, fileName, screenName);
//				
//				// Edit performance capture
//				if (checkEdit)
//					captureEditPerformance(driver,testReport,fileName,screenName);
//			}
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			ReportHelper.reportFailure(driver,e);
//		} finally {
//			testReport = ReportHelper.endReport(report, testReport);
//			ReportHelper.clearStepKeyContent();
//			stepName = "";
//		}
//	}
	
//	private static void captureSearchPerformance(String screenName) throws Exception {
//		try {
//			// Handling Session timeout
//			if (ElementHelper.isElementPresent("LoginScreen")) {
//				LoginHelper.login();
//				LoginHelper.handleFMOpenAlarmsPopup();
//			}
//			
//			stepName = screenName;
//			NavigationHelper.navigateToScreen(screenName);
//			
//			if (!isFM) {
//				String locator = or.getProperty("SearchScreen_Panel");
//				GenericHelper.waitForElement(locator, configProp.getSearchScreenWaitSec());
//			}
//			
//			GenericHelper.waitForElementToDisappear("Loading_Mask", configProp.getSearchScreenWaitSec());
//			
//			String fileName = HarReader.generateHar(stepName);
//			ReportHelper.reportSuccess("Performance captured in '" + fileName + ".xlsx'", true, testCaseName);
//			
//			if(isFM) {
//				if (ButtonHelper.isPresent("SearchButton")) {
//					ButtonHelper.click("SearchButton");
//					GenericHelper.waitForLoadmask(searchScreenWaitSec);
//				}
//				String fileNameSearch = HarReader.generateHar(stepName+"_Search");
//				ReportHelper.reportSuccess("Performance captured in '" + fileNameSearch + ".xlsx'", true, testCaseName);
//			}
//			
//			
//			
//			
//			
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//        	throw e;
//		}
//	}
	
//	private static void captureSearchPerformance(RemoteWebDriver driver,ExtentTest testReport, String filename, String screenName) throws Exception {
//		try {
//			// Handling Session timeout
//			if (ElementHelper.isElementPresent(driver, "LoginScreen")) {
//				LoginHelper.login(driver);
//				LoginHelper.handleFMOpenAlarmsPopup(driver);
//			}
//			
//			stepName = screenName;
//			NavigationHelper.navigateToScreen(driver,filename, screenName);
//			
//			if (!isFM) {
//				String locator = or.getProperty("SearchScreen_Panel");
//				GenericHelper.waitForElement(driver, locator, configProp.getSearchScreenWaitSec());
//			}
//			
//			GenericHelper.waitForElementToDisappear(driver, "Loading_Mask", configProp.getSearchScreenWaitSec());
//			
//			String fileName = HarReader.generateHar(driver, filename, stepName);
//			if(fileName != null) {
//				Log4jHelper.logInfo(fileName);
//				ReportHelper.reportSuccess(driver,testReport,"Performance captured in '" + fileName + ".xlsx'", true, testCaseName);
//			}
//			if(isFM) {
//				if (ElementHelper.isElementPresent(driver, "SearchButton")) {
//					ButtonHelper.click(driver, "SearchButton");
//					CommonHelper.waitForLoadmask(driver);
//				}
//				String fileNameSearch = HarReader.generateHar(driver, filename, stepName+"_Search");
//				ReportHelper.reportSuccess(driver,testReport,"Performance captured in '" + fileNameSearch + ".xlsx'", true, testCaseName);
//			}
//			
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//        	throw e;
//		}
//	}
	
	private void captureSearchPerformance(ExtentTest testReport, String filename, String screenName) throws Exception {
		try {
			// Handling Session timeout
			if (ElementHelper.isElementPresent("LoginScreen")) {
				LoginHelper login = new LoginHelper();
				login.login();
				login.handleFMOpenAlarmsPopup();
			}
			
			stepName = screenName;
			NavigationHelper.navigateToScreen(filename, screenName);
			
			if (!isFM) {
				GenericHelper.waitForElement("SearchScreen_Panel", configProp.getSearchScreenWaitSec());
			}
			
			GenericHelper.waitForElementToDisappear("Loading_Mask", configProp.getSearchScreenWaitSec());
			
			String fileName = HarReader.generateHar(filename, stepName);
			if(fileName != null) {
				Log4jHelper.logInfo(fileName);
				ReportHelper.reportSuccess(testReport,"Performance captured in '" + fileName + ".xlsx'", true, testCaseName);
			}
			if(isFM) {
				if (ButtonHelper.isPresent("SearchButton")) {
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				}
				String fileNameSearch = HarReader.generateHar(filename, stepName+"_Search");
				ReportHelper.reportSuccess(testReport,"Performance captured in '" + fileNameSearch + ".xlsx'", true, testCaseName);
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void captureEditPerformance(String screenName) throws Exception {
		try {
			int rows = GridHelper.getRowCount("SearchGrid");
			stepName = screenName + " Edit";
			
			if (rows > 0) {
				GridHelper.clickRow("SearchGrid", 1, 1);
				
					if (ElementHelper.isElementPresent(commonGroupLocator)) 
						MouseHelper.click(commonGroupLocator);
					if (isFM && ElementHelper.isElementPresent(ruleGroupLocator))
						MouseHelper.click(ruleGroupLocator);

					if (ElementHelper.isElementPresent(editActionLocator)) {
						MouseHelper.click(editActionLocator);
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						stepName = NavigationHelper.getScreenTitle();
						if (ValidationHelper.isEmpty(stepName))
							stepName = testCaseName;
						editAndSave();

						String fileName = HarReader.generateHar(stepName);
						ReportHelper.reportSuccess("Performance captured in '" + fileName + "'", false, testCaseName);
					}
					else {
						ReportHelper.reportSuccess("Edit action is not available in this screen", false, testCaseName);
					}
			}
			else {
				ReportHelper.reportSuccess("No rows found in search grid to edit", false, testCaseName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
	public void captureEditPerformance(ExtentTest testReport,String filename,String screenName) throws Exception {
		try {
			int rows = GridElementHelper.getRowCount("SearchGrid");
			stepName = screenName + " Edit";
			
			if (rows > 0) {
				GridElementHelper.click("SearchGrid", 1, 1);
				
					if (ElementHelper.isElementPresent(commonGroupLocator)) 
						MouseHelper.click(commonGroupLocator);
					if (isFM && ElementHelper.isElementPresent(ruleGroupLocator))
						MouseHelper.click(ruleGroupLocator);

					if (ElementHelper.isElementPresent(editActionLocator)) {
						MouseHelper.click(editActionLocator);
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						stepName = NavigationHelper.getScreenTitle();
						if (ValidationHelper.isEmpty(stepName))
							stepName = testCaseName;
						editAndSave();

						String fileName = HarReader.generateHar(filename,stepName);
						ReportHelper.reportSuccess(testReport,"Performance captured in '" + fileName + "'", false, testCaseName);
					}
					else {
						ReportHelper.reportSuccess("Edit action is not available in this screen", false, testCaseName);
					}
			}
			else {
				ReportHelper.reportSuccess("No rows found in search grid to edit", false, testCaseName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
//	public static void captureEditPerformance(RemoteWebDriver driver,ExtentTest testReport,String filename,String screenName) throws Exception {
//		try {
//			int rows = GridElementHelper.getRowCount("SearchGrid");
//			stepName = screenName + " Edit";
//			
//			if (rows > 0) {
//				GridElementHelper.click("SearchGrid", 1, 1);
//				
//					if (ElementHelper.isElementPresent(driver,commonGroupLocator)) 
//						MouseHelper.click(driver,commonGroupLocator);
//					if (isFM && ElementHelper.isElementPresent(driver,ruleGroupLocator))
//						MouseHelper.click(driver,ruleGroupLocator);
//
//					if (ElementHelper.isElementPresent(driver,editActionLocator)) {
//						MouseHelper.click(driver,editActionLocator);
//						CommonHelper.waitForLoadmask(driver);
//						stepName = NavigationMenuHelper.getScreenTitle(driver);
//						if (ValidationHelper.isEmpty(stepName))
//							stepName = testCaseName;
//						editAndSave(driver);
//
//						String fileName = HarReader.generateHar(driver,filename,stepName);
//						ReportHelper.reportSuccess(driver,testReport,"Performance captured in '" + fileName + "'", false, testCaseName);
//					}
//					else {
//						ReportHelper.reportSuccess("Edit action is not available in this screen", false, testCaseName);
//					}
//			}
//			else {
//				ReportHelper.reportSuccess("No rows found in search grid to edit", false, testCaseName);
//			}
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//        	throw e;
//		}
//	}
	
	private void editAndSave() throws Exception {
		try {
			List<WebElement> tabs = ElementHelper.getElements("Tab_Elements");
			if (tabs == null) {
				tabs = ElementHelper.getElements("Workflow_Elements");
			}
			
			if (tabs != null) {
				for (int i = 0; i < tabs.size(); i++) {
					MouseHelper.click(tabs.get(i));
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
			PerformanceHelper.cleanLog();
			ButtonHelper.click("Save");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			
			if (ElementHelper.isElementPresent("YesButton")) {
				ButtonHelper.click("YesButton");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
        	throw e;
		}
	}
	
//	private static void editAndSave(RemoteWebDriver driver) throws Exception {
//		try {
//			List<WebElement> tabs = ElementHelper.getElements(driver, "Tab_Elements");
//			if (tabs == null) {
//				tabs = ElementHelper.getElements(driver, "Workflow_Elements");
//			}
//			
//			if (tabs != null) {
//				for (int i = 0; i < tabs.size(); i++) {
//					MouseHelper.click(tabs.get(i));
//					CommonHelper.waitForLoadmask(driver);
//				}
//			}
//			PerformanceHelper.cleanLog(driver);
//			ButtonHelper.click(driver,"Save");
//			CommonHelper.waitForLoadmask(driver);
//			
//			if (ButtonHelper.isEPresent(driver, "YesButton")) {
//				ButtonHelper.click(driver, "YesButton");
//				CommonHelper.waitForLoadmask(driver);
//			}
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//        	throw e;
//		}
//	}
}