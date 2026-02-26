package com.subex.automation.helpers.performance;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class HarReader extends AcceptanceTest {

	public static void generateHar() throws Exception {
		try {
			
			generateHARFile();
			clearHAR();
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String generateHar(String screen) throws Exception {
		try {
			
			String fileName = generateHARFile(screen);
			clearHAR();
			
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String generateHar(String filename, String screen) throws Exception {
		try {
			String fileName = null;
			if(screen.equalsIgnoreCase(" ") || screen.contains("null") || screen.length() == 0)
				screen = NavigationHelper.getScreenTitle();
			
			fileName = generateHARFile(filename, screen);
			clearHAR();
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void generateHARFile() throws Exception {
		try {
			String screen = NavigationHelper.getScreenName();
			if (screen == null)
				screen = NavigationHelper.getScreenTitle();
			if (screen == null)
				screen = "Home";
			
			generateHARFile(screen);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String generateHARFile(String screen) throws Exception {
		try {
			String fileName = screen.replace(" ", "_") + "_" + DateHelper.getCurrentDateTime("yyyyMMdd_HHmmss");
			
			if ( driver instanceof JavascriptExecutor ) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				if((Boolean) executor.executeScript("return window.HAR != undefined")) {
					executor.executeScript( "var options = { token: 'test', json: true, fileName: '" + fileName + "'};" + "HAR.triggerExport(options).then(result => {});" );
				}
				else {
					if (configProp.getBrowser().equalsIgnoreCase("ie")) {
						IEPerformance.getPerformance(executor, screen, fileName);
					}
					else {
						LogEntries perfLogs = driver.manage().logs().get(LogType.PERFORMANCE);
						getChromePerformance(screen, fileName, perfLogs);
					}
				}
			}
			
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String generateHARFile(String filename, String screen) throws Exception {
		try {
			
			String fileName = filename + "_" + screen.replace(" ", "_") + "_" + DateHelper.getCurrentDateTime("yyyyMMdd_HHmmss");
			
			if ( driver instanceof JavascriptExecutor ) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				if((Boolean) executor.executeScript("return window.HAR != undefined")) {
					executor.executeScript( "var options = { token: 'test', json: true, fileName: '" + fileName + "'};" + "HAR.triggerExport(options).then(result => {});" );
				}
				else {
					if (configProp.getBrowser().equalsIgnoreCase("ie")) {
						IEPerformance.getPerformance(executor, screen, fileName);
					}
					else {
						LogEntries perfLogs = driver.manage().logs().get(LogType.PERFORMANCE);
						getChromePerformance(screen, fileName, perfLogs);
					}
				}
			}
			
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clearHAR() throws Exception {
		try {
			if ( driver instanceof JavascriptExecutor ) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				if((Boolean) executor.executeScript("return window.HAR != undefined")) {
					executor.executeScript( "HAR.clear({token: 'test'});" );
				}
				else {
					if (configProp.getBrowser().equalsIgnoreCase("ie")) {
						IEPerformance.clearPerformance(executor);
					}
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void getChromePerformance(String screen, String fileName, LogEntries perfLogs) throws Exception {
		try {
			if (perfLogs != null) {
				ArrayList<String> content = new ArrayList<String>();

				for (LogEntry entry : perfLogs) {
					String temp = entry.toString();
					temp = temp.substring(temp.indexOf("{"));
					content.add(temp);
					content.add("\n");
				}

				String path = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report\\");
				FileHelper.writeToFile(GenericHelper.getPath(automationOS, path + fileName + ".txt"), content);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}