package com.subex.automation.helpers.performance;

import java.io.File;
import java.util.Arrays;

import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.file.SortFiles;
import com.subex.automation.helpers.report.ReportHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PerformanceReporter extends AcceptanceTest {

	public static void addPerformance() throws Exception {
		try {
			if(calculatePerformance) {
				String downloadDir = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report\\");
				String browser = configProp.getBrowser();
				
				if (browser.equalsIgnoreCase("firefox")) {
					FirefoxReader.reader(downloadDir);
				}
				else if (browser.equalsIgnoreCase("chrome")) {
					ChromeReader.reader(downloadDir);
				}
				
				if (calculatePerformance)
					testCaseName = "Performance";
				String reportDir = GenericHelper.getPath(automationOS, reportLocation + "\\Performance_Report\\" + testCaseName);
				FileHelper.copyFiles(automationOS, downloadDir, reportDir, false);
				addSummaryReport();
				FileHelper.cleanUpDir(automationOS, downloadDir, "Yes");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addPerformance(String directoryName) throws Exception {
		try {
			if(calculatePerformance) {
				String downloadDir = GenericHelper.getPath(automationOS, configProp.getDownloadDirectory() + "\\Performance_Report\\");
				String browser = configProp.getBrowser();
				if (browser.equalsIgnoreCase("firefox")) {
					FirefoxReader.reader(downloadDir,directoryName);
				}
				else if (browser.equalsIgnoreCase("chrome")) {
					ChromeReader.reader(downloadDir);
				}
				
				String reportDir = GenericHelper.getPath(automationOS, reportLocation + "\\Performance_Report\\" + directoryName);
				FileHelper.copyFiles(automationOS, downloadDir+directoryName, reportDir, false);
				addSummaryReport(directoryName);
				//FileHelper.cleanUpDir(automationOS, downloadDir, "Yes");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addSummaryReport() throws Exception {
		try {
			if(calculatePerformance) {
				String fileName = generateSummaryReport();
				
				if (fileName != null) {
//					ReportHelper.clearStepKeyContent();
					ReportHelper.updateStepKey("Performance Summary Report", "Blue", fileName);
//					stepName = "Performance";
//					
//					ReportHelper.reportSuccess("Test case succeeded.", false, null);
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void addSummaryReport(String directoryName) throws Exception {
		try {
			if(calculatePerformance) {
				String fileName = generateSummaryReport(directoryName);
				
				if (fileName != null) {
//					ReportHelper.clearStepKeyContent();
					ReportHelper.updateStepKey("Performance Summary Report", "Blue", fileName);
//					stepName = "Performance";
					
					//ReportHelper.reportSuccess("Test case succeeded.", false, null);
				}
			}
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String generateSummaryReport() throws Exception {
		try {
			if (FileHelper.checkDirectoryExists(reportLocation)) {
//				if (performanceSummary != null && performanceSummary.size() > 1) {
//					String fileName = reportLocation + "\\" + testCaseName + "_Consolidated_Report.xlsx";
//					ExcelWriter.writeToExcel(fileName, "Report", performanceSummary);
//					performanceSummary = new TreeMap<Integer, Object[]>();
//					performanceSummary.put(0, new Object[] {"Screen Name", "Time Taken"});
//					
//					return fileName;
//				}
			}

			return null;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String generateSummaryReport(String directoryName) throws Exception {
		try {
			if (FileHelper.checkDirectoryExists(reportLocation)) {
//				if (performanceSummary != null && performanceSummary.size() > 1) {
//					String fileName = reportLocation + "\\" + directoryName + "_Consolidated_Report.xlsx";
//					ExcelWriter.writeToExcel(fileName, "Report", performanceSummary);
//					performanceSummary = new TreeMap<Integer, Object[]>();
//					performanceSummary.put(0, new Object[] {"Screen Name", "Time Taken"});
//					
//					return fileName;
//				}
			}

			return null;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static File[] sortFiles(File[] listOfFiles) throws Exception {
		try {
			SortFiles[] filesToSort = new SortFiles[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++)
				filesToSort[i] = new SortFiles(listOfFiles[i]);

			Arrays.sort(filesToSort);

			for (int i = 0; i < listOfFiles.length; i++)
				listOfFiles[i] = filesToSort[i].f;
			
			return listOfFiles;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}