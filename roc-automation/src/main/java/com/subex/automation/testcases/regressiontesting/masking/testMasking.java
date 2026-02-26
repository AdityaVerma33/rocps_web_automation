package com.subex.automation.testcases.regressiontesting.masking;

import com.subex.automation.helpers.application.CopyHelper;
import com.subex.automation.helpers.application.ExportHelper;
import com.subex.automation.helpers.application.screens.TaskSearchHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.UnzipHelper;

public class testMasking extends ROCAcceptanceTest {
	
	protected static String path = null;
	final String fileName = "Masking_TestData.xlsx";
	
	public testMasking() throws Exception {
		try {
			path = automationPath + "\\src\\main\\resources\\Regression\\";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void copySelectedCell(int rowNum, String columnHeader, String expectedValue) throws Exception {
		CopyHelper copy = new CopyHelper();
		
		try {
			copy.copySelectedCell("SearchGrid", rowNum, columnHeader);
			String value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			copy.closeCopyPopup();
		}
	}
	
	protected void copySelectedColumn(int rowNum, String columnHeader, String expectedValue) throws Exception {
		CopyHelper copy = new CopyHelper();
		
		try {
			copy.copySelectedColumn("SearchGrid", rowNum, columnHeader);
			String value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			copy.closeCopyPopup();
		}
	}
	
	protected String copySelectedRow(int rowNum, String columnHeader, String expectedValue) throws Exception {
		CopyHelper copy = new CopyHelper();
		
		try {
			copy.copySelectedRow("SearchGrid", rowNum, columnHeader);
			String value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			copy.closeCopyPopup();
		}
	}
	
	protected void copySelectedRow(int rowNum, String columnHeader, String expectedValue1, String expectedValue2) throws Exception {
		try {
			String value = copySelectedRow(rowNum, columnHeader, expectedValue1);
			
			assertTrue(value.contains(expectedValue2), "Expected value '" + expectedValue2 + "' is not found in Copy popup.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String copyAllRows(String expectedValue) throws Exception {
		CopyHelper copy = new CopyHelper();
		
		try {
			copy.copyAllRows("SearchGrid");
			String value = copy.getCopyContent();
			assertTrue(value.contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Copy popup.");
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} finally {
			copy.closeCopyPopup();
		}
	}
	
	protected void copyAllRows(String expectedValue1, String expectedValue2) throws Exception {
		try {
			String value = copyAllRows(expectedValue1);
			
			assertTrue(value.contains(expectedValue2), "Expected value '" + expectedValue2 + "' is not found in Copy popup.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String[] exportSelectedRows(int startRowNum, int endRowNum, String columnHeader, String expectedValue) throws Exception {
		try {
			ExportHelper export = new ExportHelper();
			String exportFile = export.exportSelectedRows("SearchGrid", startRowNum, endRowNum, columnHeader);
			String[] content = FileHelper.readFileContent(automationOS, exportFile);
			assertTrue(content[1].contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Export file.");
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void exportSelectedRows(int startRowNum, int endRowNum, String columnHeader, String expectedValue1, String expectedValue2) throws Exception {
		try {
			String[] content = exportSelectedRows(startRowNum, endRowNum, columnHeader, expectedValue1);
			
			assertTrue(content[1].contains(expectedValue2), "Expected value '" + expectedValue2 + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String[] exportConfiguredRows(String expectedValue) throws Exception {
		try {
			ExportHelper export = new ExportHelper();
			String exportFile = export.exportConfiguredRows("SearchGrid");
			String[] content = FileHelper.readFileContent(automationOS, exportFile);
			assertTrue(content[1].contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Export file.");
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void exportConfiguredRows(String expectedValue1, String expectedValue2) throws Exception {
		try {
			String[] content = exportConfiguredRows(expectedValue1);
			
			assertTrue(content[1].contains(expectedValue2), "Expected value '" + expectedValue2 + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected String[] exportAllRows(String sheetName, String testCaseName, String exportZipFileFilter, boolean isTxtExport, String expectedValue) throws Exception {
		try {
			ExportHelper export = new ExportHelper();
			export.exportAllRows();
			
			TaskSearchHelper taskSearch = new TaskSearchHelper();
			taskSearch.verifyTaskStatus(path, fileName, sheetName, testCaseName, 1);
			
			String exportDirectory = configProp.getDataDirPath() + "/ExportData";
			String exportFile = FileHelper.getLastModifiedFile(applicationOS, exportDirectory, exportZipFileFilter + "*");
			
			UnzipHelper unzip = new UnzipHelper();
			unzip.unzip(applicationOS, exportFile, exportDirectory);
			
			String extension = "txt";
			if (!isTxtExport)
				extension = "csv";
			exportFile = FileHelper.getLastModifiedFile(applicationOS, exportDirectory, exportZipFileFilter + "*.*" + extension);
			String[] content = FileHelper.readFileContent(applicationOS, exportDirectory + "/" + exportFile);
			assertTrue(content[1].contains(expectedValue), "Expected value '" + expectedValue + "' is not found in Export file.");
			FileHelper.deleteFile(applicationOS, exportDirectory + "/" + exportFile);
			
			return content;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	protected void exportAllRows(String sheetName, String testCaseName, String exportZipFileFilter, boolean isTxtExport, String expectedValue1, String expectedValue2) throws Exception {
		try {
			String[] content = exportAllRows(sheetName, testCaseName, exportZipFileFilter, isTxtExport, expectedValue1);
			
			assertTrue(content[1].contains(expectedValue2), "Expected value '" + expectedValue2 + "' is not found in Export file.");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}