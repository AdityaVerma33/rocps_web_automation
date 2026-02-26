package com.subex.automation.helpers.application;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.selenium.ROCAcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CopyHelper extends ROCAcceptanceTest {
	
	/**
	 * This method is used to Copy Selected Cell
	 * @param gridId
	 * @param rowNum
	 * @param colNum
	 * @throws Exception
	 */
	public void copySelectedCell(String gridId, int rowNum, int colNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, colNum);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Cell(s)");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copySelectedCell(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, columnHeader);
				int colNum = GridHelper.getColumnNumber(gridId, columnHeader);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Cell(s)");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copySelectedColumn(String gridId, int rowNum, int colNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, colNum);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Column");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copySelectedColumn(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, columnHeader);
				int colNum = GridHelper.getColumnNumber(gridId, columnHeader);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Column");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copySelectedRow(String gridId, int rowNum, int colNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, colNum);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Row(s)");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copySelectedRow(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, columnHeader);
				int colNum = GridHelper.getColumnNumber(gridId, columnHeader);
				NavigationHelper.rightClickAction(gridId, rowNum, colNum, "Copy", "Selected Row(s)");
				assertTrue(LabelHelper.isTitlePresent("Copy"));
			}
			else {
				FailureHelper.failTest("Row number cannot be 0");
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void copyAllRows(String gridId) throws Exception {
		try {
			GridHelper.clickRow(gridId, 1, 1);
			NavigationHelper.rightClickAction(gridId, 1, 1, "Copy", "All Rows");
			assertTrue(LabelHelper.isTitlePresent("Copy"));
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getCopyContent() throws Exception {
		try {
			if (LabelHelper.isTitlePresent("Copy")) {
				return TextAreaHelper.getValue("Copy_Textarea");
			}
			else {
				FailureHelper.failTest("Copy popup is not found.");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void closeCopyPopup() throws Exception {
		try {
			if (LabelHelper.isTitlePresent("Copy")) {
				ButtonHelper.click("Copy_Close");
				GenericHelper.waitForElementToDisappear("Copy_Close", searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}