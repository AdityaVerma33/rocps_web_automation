package com.subex.automation.helpers.application;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.FileHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ExportHelper extends ROCAcceptanceTest {
	
	int waitTimeInSecs = 10;
	
	public ExportHelper() {
		
	}
	
	public ExportHelper(int waitTimeInSeconds) throws Exception {
		try {
			waitTimeInSecs = waitTimeInSeconds;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to Export Selected Cell
	 * @param gridId
	 * @param rowNum
	 * @param colNum
	 * @return 
	 * @throws Exception
	 */
	public String exportSelectedRows(String gridId, int rowNum, int colNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, colNum);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Export", "Selected Row(s)");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
				return fileName;
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportSelectedRows(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, columnHeader);
				int colNum = GridHelper.getColumnNumber(gridId, columnHeader);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Export", "Selected Row(s)");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
				return fileName;
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportSelectedRows(String gridId, int startRowNum, int endRowNum, String columnHeader) throws Exception {
		try {
			if (startRowNum > 0) {
				int noOfRows = (endRowNum - startRowNum) + 1;
				GridHelper.clickMultipleCells(gridId, startRowNum, noOfRows);
				NavigationHelper.navigateToAction("Export", "Selected Row(s)");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
				return fileName;
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportConfiguredRows() throws Exception {
		try {
			NavigationHelper.navigateToAction("Export", "Configured Rows");
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportConfiguredRows(String gridId) throws Exception {
		try {
			int rows = GridHelper.getRowCount(gridId);
			
			if (rows > 0)
				NavigationHelper.rightClickAction(gridId, 1, 1, "Export", "Configured Rows");
			else
				NavigationHelper.navigateToAction("Export", "Configured Rows");
			
			GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
			String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
			return fileName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportConfiguredRows(String gridId, int rowNum, int colNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, colNum);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Export", "Configured Rows");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
				return fileName;
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String exportConfiguredRows(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, columnHeader);
				int colNum = GridHelper.getColumnNumber(gridId, columnHeader);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Export", "Configured Rows");
				GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
				String fileName = FileHelper.fileDownloadSikuli(waitTimeInSecs);
				return fileName;
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exportAllRows() throws Exception {
		try {
			NavigationHelper.navigateToAction("Export", "All Rows");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		    GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Information"));
			
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exportAllRows(String gridId) throws Exception {
		try {
			int rows = GridHelper.getRowCount(gridId);
			
			if (rows > 0)
				NavigationHelper.rightClickAction(gridId, 1, 1, "Export", "All Rows");
			else
				NavigationHelper.navigateToAction("Export", "All Rows");
			
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		    GenericHelper.waitForElement("OKButton", searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Information"));
			
			ButtonHelper.click("OKButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}