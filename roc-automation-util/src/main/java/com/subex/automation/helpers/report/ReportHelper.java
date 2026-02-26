package com.subex.automation.helpers.report;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.XMLReader;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.RemoteMachineHelper;

public class ReportHelper extends AcceptanceTest {
	public static String reportConfigFile = null;
	static boolean reportStarted = false;
	static String previousFailedStepName = null;
	
	public static ExtentReports createReport(ExtentReports report, String extentReportFile) throws Exception {
		try {
			extentReportFile = extentReportFile + "_AT_Report.html";
			FileHelper.deleteFileIfExists(automationOS, extentReportFile);
			reportConfigFile = GenericHelper.getPath(automationOS, configProp.getUtilPath() + "\\src\\main\\resources\\extent-config.xml");
			report = new ExtentReports(extentReportFile);
			
			File extConfig = new File(reportConfigFile);
			report.loadConfig(extConfig);
			
			return report;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ExtentTest startReport(ExtentReports report, String testCaseName) throws Exception {
		try {
			reportStarted = true;
			previousFailedStepName = null;
			return report.startTest(testCaseName);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static Map<String, String> getSystemInfo() throws Exception {
		try {
			Map<String, String> sysInfo = new HashMap<String, String>();
			sysInfo.put("Test Environment OS", StringHelper.convertToCamelCase(applicationOS));
			String browser = StringHelper.convertToCamelCase(configProp.getBrowser());
			
//			sysInfo.put("Performance Calculated", String.valueOf(calculatePerformance));
			sysInfo.put("Browser", browser);
			return sysInfo;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ExtentTest endReport(ExtentReports report, ExtentTest testReport) throws Exception {
		try {
			if (testReport.getTest().getStatus().equals("Unknown")) {
				updateStepKey("Error Message", "Red", "Test case execution failed abruptly. Please check the TestDriver and TestScript file for empty rows or columns in between.");
				reportFailure();
			}
			
			report.endTest(testReport);
			clearStepKeyContent();
			stepName = "";
			previousFailedStepName = null;
			return testReport;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void closeReport(ExtentReports report) throws Exception {
		try {
			if (!reportStarted) {
				testReport = startReport(report, "Test");
				stepName = "Test";
				updateStepKey("Error Message", "Red", "Test case execution did not start. This may be because Firefox did not launch.");
				reportFailure();
				testReport = endReport(report, testReport);
			}
			
			if (testReport != null) {
				testReport = endReport(report, testReport);
			}
			
			Map<String, String> sysInfo = getSystemInfo();
			report.addSystemInfo(sysInfo);
			
			report.flush();
			report.close();
			reportStarted = false;
	        
	        String reportHeader = "Subex Report";
	        String testWidth = "300px";
	        String fileNameWithPath = FileHelper.getLastModifiedFile(automationOS, reportLocation, ".html");
	        String[][] linesToReplace = {{"<div class='logo-container'>", "<div class='logo-container' style='background-color:blue;'>"},
	        							{"href='http://extentreports.relevantcodes.com'", "style='pointer-events: none; cursor: default;' href='#'"},
	        							{"<span>ExtentReports</span>", "<span>" + reportHeader + "</span>"},
	        							{"<a href='#' data-activates='slide-out' class='button-collapse'><i class='fa fa-bars'></i></a>" ,""},
	        							{"label green lighten", "label indigo lighten"},
	        							{"label red lighten", "label indigo lighten"},
	        							{"<span>v2.41.1</span>", ""},
	        							{"html('ExtentReports')", "html('" + reportHeader + "')"},
	        							{"class='col _addedCell1'", "class='col _addedCell1' style='width:" + testWidth + ";'"}};
        	XMLReader.replaceLine(fileNameWithPath, linesToReplace);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateReportProperty(String reportConfigFile, String tagName, String tagValue) throws Exception {
		try {
			XMLReader.replaceValue(automationOS, reportConfigFile, tagName, tagValue);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void reportSuccess(String message, boolean takeScreenshot, String testCaseName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(stepName)) {
				if (ValidationHelper.isEmpty(stepKeys)) {
					updateStepKey("INFO", "Blue", message);
				}
				
				boolean attachScreenShot = ValidationHelper.isTrue(configProp.getEmbedImageInReport());
				if (attachScreenShot && takeScreenshot) {
					addScreenShotToStepKey("Blue", takeScreenshot);
				}
				
				testReport.log(LogStatus.PASS, stepName, stepKeys);
				clearStepKeyContent();
				previousFailedStepName = null;
//				AcceptanceTest.totalPass++;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void reportSuccess(ExtentTest testReport, String message, boolean takeScreenshot, String testCaseName) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(stepName)) {
				if (ValidationHelper.isEmpty(stepKeys)) {
					updateStepKey("INFO", "Blue", message);
				}
				
				if (takeScreenshot) {
					addScreenShotToStepKey("Blue", takeScreenshot);
				}
				
				testReport.log(LogStatus.PASS, stepName, stepKeys);
				clearStepKeyContent();
				previousFailedStepName = null;
//				AcceptanceTest.totalPass++;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void reportWarning(String message, boolean takeScreenshot, String testCaseName) throws Exception {
		try {
			if (ValidationHelper.isEmpty(stepKeys)) {
				updateStepKey("Warning", "Orange", message);
			}
			
			if (ValidationHelper.isEmpty(stepName))
				stepName = testCaseName;
			
			if (takeScreenshot) {
				addScreenShotToStepKey("Blue", false);
			}
			
			testReport.log(LogStatus.WARNING, stepName, stepKeys);
			previousFailedStepName = null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void reportFailure() throws Exception {
		try {
			if (!stepName.equals("") && (previousFailedStepName == null || !previousFailedStepName.equals(stepName))) {
				if (ValidationHelper.isEmpty(stepKeys)) {
					if (ValidationHelper.isNotEmpty(errorMsg))
						updateStepKey("Error Message", "Red", errorMsg + ". Refer log for exception.");
					else
						updateStepKey("Error Message", "Red", "Test case execution failed. Refer log file for exception.");
				}
				
				addScreenShotToStepKey("Red", true);
				testReport.log(LogStatus.FAIL, stepName, stepKeys);
				clearStepKeyContent();
				previousFailedStepName = stepName;
				result = "fail";
				if (errorMsg != null)
					errorMsg = null;
			}
		} catch (Exception ae) {
			FailureHelper.setErrorMessage(ae);
			throw ae;
		}
	}
	
	public static void reportFailure(Exception e) throws Exception {
		try {
			if (previousFailedStepName == null || !previousFailedStepName.equals(stepName)) {
				if (!stepKeys.contains("ERROR MESSAGE")) {
					FailureHelper.setErrorMessage(e);
				}
				
				reportFailure();
				
				if (errorMsg != null)
					errorMsg = null;
			}
		} catch (Exception ae) {
			FailureHelper.setErrorMessage(ae);
			throw ae;
		}
	}
	
	public static void reportFailure(AssertionError e) throws Exception {
		try {
			if (previousFailedStepName == null || !previousFailedStepName.equals(stepName)) {
				if (!stepKeys.contains("ERROR MESSAGE")) {
					FailureHelper.setErrorMessage(e);
				}
				
				reportFailure();
				
				if (errorMsg != null)
					errorMsg = null;
			}
		} catch (Exception ae) {
			FailureHelper.setErrorMessage(ae);
			throw ae;
		}
	}
	
	public static void reportFailure(StackOverflowError e) throws Exception {
		try {
			if (previousFailedStepName == null || !previousFailedStepName.equals(stepName)) {
				if (!stepKeys.contains("ERROR MESSAGE")) {
					FailureHelper.setErrorMessage(e);
				}
				
				reportFailure();
				
				if (errorMsg != null)
					errorMsg = null;
			}
		} catch (Exception ae) {
			FailureHelper.setErrorMessage(ae);
			throw ae;
		}
	}
	
	public static void reportSkip(String message) throws Exception {
		try {
			if (testReport != null)
				testReport.log(LogStatus.SKIP, stepName, message);
			previousFailedStepName = null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void reportError(String message) throws Exception {
		try {
			testReport.log(LogStatus.ERROR, stepName, message);
			previousFailedStepName = null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateStepKey(String keyWord, String keyWordColor, String keyValue) throws Exception {
		try {
			if (ValidationHelper.isEmpty(stepKeys))
				stepKeys = "[<span style=\"color:" + keyWordColor + ";\">" + keyWord.toUpperCase() + ":</span> " + keyValue + "]";
			else
				stepKeys = stepKeys + "<br>[<span style=\"color:" + keyWordColor + ";\">" + keyWord.toUpperCase() + ":</span> " + keyValue + "]";
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clearStepKeyContent() throws Exception {
		try {
			stepKeys = null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void addScreenShotToStepKey(String keyWordColor, boolean attachScreenShot) throws Exception {
		try {
			if (!stepKeys.contains("Show/Hide Screenshot") && driver != null && driver.toString() != null) {
				
				if (attachScreenShot) {
					String screenShot = captureScreen();
					if (screenShot != null) {
						String textStyle = "color:purple;font-weight:bold;text-decoration:underline;";
						String screenShotLabel = "<br><br><details><summary style=\"" + textStyle + "\">Show/Hide Screenshot</summary>";
						stepKeys = stepKeys + screenShotLabel + testReport.addBase64ScreenShot(screenShot);
					}
				}
				else {
					String fileName = reportLocation + "\\" + takeScreenShot(testCaseName);
					String linkTag = "<br>[<span style=\"color:" + keyWordColor + ";\">SCREENSHOT: </span><span style=\"color:Black;\">" + fileName + "</span>]";
					stepKeys = stepKeys + linkTag;
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String captureScreen() throws Exception{
		try {
			TakesScreenshot newScreen = (TakesScreenshot) driver;
			String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);

		    return "data:image/jpg;base64, " + scnShot ;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addStackTrace(String keyWorkColor, String[] stackTrace) throws Exception {
		try {
			String textStyle = "color:red;font-weight:bold;text-decoration:underline;";
			String traceStyle = "";
			
			for (int i = 0; i < stackTrace.length; i++) {
				traceStyle = traceStyle + "<br>" + stackTrace[i];
//				Log4jHelper.logError(stackTrace[i]);
			}
			
			String screenShot = "<br><br><details><summary style=\"" + textStyle + "\">Show/Hide Exception</summary>";
			String image = "<br><pre>" + traceStyle + "</pre></details>";
			stepKeys = stepKeys + screenShot + image;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	private static String takeScreenShot(String testCaseName) throws Exception {
		String fileNameWithPath = null;
		try {
	    	String dateTime = DateHelper.getCurrentDateTime("ddMMYYYY_HHmm");
	    	if (ValidationHelper.isEmpty(testCaseName)) {
	    		if (ValidationHelper.isEmpty(ReportHelper.testCaseName))
	    			testCaseName = "";
	    		else
	    			testCaseName = ReportHelper.testCaseName;
	    	}
	    	
	    	if (testCaseName.equals(stepName))
	    		fileNameWithPath = "ScreenShots\\" + testCaseName + "_" + dateTime + ".png";
	    	else
	    		fileNameWithPath = "ScreenShots\\" + testCaseName + "_" + stepName + "_" + dateTime + ".png";
	    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 
			FileUtils.copyFile(scrFile, new File(GenericHelper.getPath(automationOS, reportLocation + "\\" + fileNameWithPath)));
		}
        catch (Exception e) {
        	FailureHelper.setErrorMessage(e);
			throw e;
		}
        
        return fileNameWithPath;
    }
	
	public static void updateDocumentTitle(String product) throws Exception {
		try {
			updateReportProperty(reportConfigFile, "documentTitle", product + " Execution Report");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateReportName(String product, String versionNo) throws Exception {
		try {
			String reportHeader = "Execution Report";
			if (versionNo != null) {
				reportHeader = reportHeader + " - " + product + " " + versionNo;
			}
			
			updateReportProperty(reportConfigFile, "reportName", reportHeader);
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRunNumber(String reportDirectory) throws Exception {
		try {
			File dir = new File( reportDirectory );
			FileFilter filter = new FolderFilter();
			File[] listFolders = dir.listFiles( filter );
			
			if (listFolders != null && listFolders.length > 0 && listFolders[0] != null) {
				String folder1Name = listFolders[0].getName();
				int runCount = Integer.parseInt(folder1Name.substring(3));
				
				if (listFolders.length > 1 && listFolders[1] != null) {
					for (int i = 1; i < listFolders.length; i++) {
						if (listFolders[i].isDirectory()) {
							String folder2Name = listFolders[i].getName();
							String runNumber = folder2Name.substring(3);
							if (ValidationHelper.isInteger(runNumber)) {
								int folder2 = Integer.parseInt(runNumber);
								
								if (folder2 > runCount)
									runCount = folder2;
							}
						}
					}
				}
				
				return runCount+1;
			}
			else
				return 1;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRunNumber(String os, String reportDirectory) throws Exception {
		try {
			int runCount = 1;
			
			if (os.equalsIgnoreCase("Windows")) {
				runCount = getRunNumber(reportDirectory);
			}
			else {
				RemoteMachineHelper remoteMachine = new RemoteMachineHelper();
				String command1 = "cd " + reportDirectory;
				String[] result = remoteMachine.executeScripts(command1);
				String command = "ls " + reportDirectory + " | sort -Vr | head -1";
				result = remoteMachine.executeScripts(command);
				
				if (ValidationHelper.isNotEmpty(result)) {
					String runNo = result[0].replace("Run", "").replace("\n", "").replace("\u00A0", "");
					runCount = Integer.parseInt(runNo);
					runCount++;
				}
			}
			
			return runCount;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addSuccess(String message) throws Exception {
		try {
			ReportHelper.updateStepKey("Success", "Lime", message);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addDescription(String description) throws Exception {
		try {
			ReportHelper.updateStepKey("Description", "Blue", description);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addInformation(String information) throws Exception {
		try {
			ReportHelper.updateStepKey("Info", "Blue", information);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addInformation1(String information) throws Exception {
		try {
			ReportHelper.updateStepKey("Info", "DarkViolet", information);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addWarning(String message) throws Exception {
		try {
			ReportHelper.updateStepKey("Warning", "DarkOrange", message);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addWarning1(String message) throws Exception {
		try {
			ReportHelper.updateStepKey("Warning", "Brown", message);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}

class FolderFilter implements FileFilter {

	@Override
	public boolean accept( File file ) {
		if ( file.getName().startsWith("Run") ) {
			return true;
		}
		return false;
	}
}