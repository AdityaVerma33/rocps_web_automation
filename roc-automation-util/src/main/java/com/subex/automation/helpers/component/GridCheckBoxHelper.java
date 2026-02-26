package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.CheckBoxElementHelper;
import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Grid Check box related methods.
 * @author madhu.duraisamy
 *
 */
public class GridCheckBoxHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if grid check box is present in the GUI
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static boolean isPresent( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is present in the GUI
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static boolean isPresent( String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, columnHeader);
			
			WebElement gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void check( String gridId, String idOrXpath, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, columnNum);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				boolean isChecked = CheckBoxElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
				
				gridElement = GridElementHelper.getCellElement(gridId, rowNum, columnNum);
				element = ElementHelper.getElement(gridElement, idOrXpath);
				isChecked = CheckBoxElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void check( String gridWrapperId, String gridId, String idOrXpath, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, columnNum);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				boolean isChecked = CheckBoxElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
				
				gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, columnNum);
				element = ElementHelper.getElement(gridElement, idOrXpath);
				isChecked = CheckBoxElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void check( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			
			check(gridId, "//img", rowNum, columnNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void check( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int columnNum = GridElementHelper.getColumn(gridId, columnHeader);
			
			check(gridId, "//img", rowNum, columnNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void check( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int row = GridElementHelper.getRow(gridId, cellValue, col);
			
			check(gridId, "//img", row, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check Check Box present in editable grid.
	 * This method can be used in case the row number is static.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void check( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			check(gridId, idOrXpath, rowNum, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void check( String gridId, String idOrXpath, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int row = GridElementHelper.getRow(gridId, cellValue, col);
			
			check(gridId, idOrXpath, row, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check Check Box present in editable grid.
	 * This method can be used in case the row number is static.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void check( String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, columnHeader);
			
			check(gridWrapperId, gridId, idOrXpath, rowNum, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check Check Box present in editable grid.
	 * This method can be used in case the row number is dynamic.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void check( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridId, assertRowValue, col);
			check(gridId, idOrXpath, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check Check Box present in editable grid.
	 * This method can be used in case the row number is dynamic.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void check( String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridWrapperId, gridId, assertRowValue, col);
			check(gridWrapperId, gridId, idOrXpath, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void uncheck( String gridId, String idOrXpath, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, columnNum);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				boolean isChecked = CheckBoxElementHelper.getValue(element);
				if (isChecked) {
					MouseHelper.click(element);
				}
				
				gridElement = GridElementHelper.getCellElement(gridId, rowNum, columnNum);
				element = ElementHelper.getElement(gridElement, idOrXpath);
				isChecked = CheckBoxElementHelper.getValue(element);
				if (isChecked) {
					MouseHelper.click(element);
				}
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' is not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void uncheck( String gridWrapperId, String gridId, String idOrXpath, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, columnNum);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				boolean isChecked = CheckBoxElementHelper.getValue(element);
				if (isChecked) {
					MouseHelper.click(element);
				}
				
				gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, columnNum);
				element = ElementHelper.getElement(gridElement, idOrXpath);
				isChecked = CheckBoxElementHelper.getValue(element);
				if (isChecked) {
					MouseHelper.click(element);
				}
			}
			else {
				FailureHelper.failTest("CheckBox not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void uncheck( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			uncheck(gridId, "//img", rowNum, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check Check Box present in editable grid.
	 * This method can be used in case the row number is static.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			uncheck(gridId, idOrXpath, rowNum, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check Check Box present in editable grid.
	 * This method can be used in case the row number is static.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, columnHeader);
			
			uncheck(gridWrapperId, gridId, idOrXpath, rowNum, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check Check Box present in editable grid.
	 * This method can be used in case the row number is dynamic.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridId, assertRowValue, col);
			col = GridElementHelper.getColumn(gridId, columnHeader);
			uncheck(gridId, idOrXpath, row, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check Check Box present in editable grid.
	 * This method can be used in case the row number is dynamic.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridWrapperId, gridId, assertRowValue, col);
			col = GridElementHelper.getColumn(gridWrapperId, gridId, columnHeader);
			uncheck(gridWrapperId, gridId, idOrXpath, row, col);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is checked.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static boolean isChecked( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			
			boolean isChecked = false;
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, col);
			
			if (gridElement != null) {
				WebElement element = ElementHelper.getElement(gridElement, "//img");
				
				if (element != null) {
					isChecked = CheckBoxElementHelper.getValue(element);
				}
				else {
					FailureHelper.failTest("CheckBox not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("CheckBox not found in grid '" + gridId + "'");
			}
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is checked.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @throws Exception
	 */
	public static boolean isChecked( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			boolean isChecked = false;
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				isChecked = CheckBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is checked.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isChecked( String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			boolean isChecked = false;
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				isChecked = CheckBoxElementHelper.getValue(element);
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is checked.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isChecked( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridId, assertRowValue, col);
			return isChecked(gridId, idOrXpath, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is checked.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isChecked( String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridWrapperId, gridId, assertRowValue, col);
			return isChecked(gridWrapperId, idOrXpath, gridId, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is enabled.
	 * If not enabled, test case will fail.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEnabled( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			
			boolean isEnabled = false;
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, "//img");
			
			if (element != null) {
				isEnabled = CheckBoxElementHelper.enabled(element);
			}
			else {
				FailureHelper.failTest("CheckBox not found in grid '" + gridId + "'");
			}
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is enabled.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEnabled( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			boolean isEnabled = false;
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				isEnabled = CheckBoxElementHelper.enabled(element);
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is enabled.
	 * If not enabled, test case will fail.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param rowNum - Row number of the check box.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEnabled(String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			boolean isEnabled = false;
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, columnHeader);
			WebElement gridElement = GridElementHelper.getCellElement(gridWrapperId, gridId, rowNum, col);
			WebElement element = ElementHelper.getElement(gridElement, idOrXpath);
			
			if (element != null) {
				isEnabled = CheckBoxElementHelper.enabled(element);
			}
			else {
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
			}
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is enabled.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEnabled( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridId, assertRowValue, col);
			return isEnabled(idOrXpath, gridId, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if grid check box is enabled.
	 * If not enabled, test case will fail.
	 * @param gridWrapperId - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @param gridId - id of the grid.
	 * @param assertRowValue - Row value using which row number has to be found.
	 * @param assertColumnHeader - Column number of column header value where the row value has to be checked for.
	 * @param columnHeader - Column number or column header value of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEnabled(String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader) throws Exception {
		try {
			gridWrapperId = GenericHelper.getORProperty(gridWrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			int col = GridElementHelper.getColumn(gridWrapperId, gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridWrapperId, gridId, assertRowValue, col);
			return isEnabled(gridWrapperId, idOrXpath, gridId, row, columnHeader);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, rowNum, columnHeader);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, rowNum, columnHeader);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled(String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, rowNum, columnHeader);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled(String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isPresent = isPresent(gridId, idOrXpath, rowNum, columnHeader);
			
			if (!isPresent)
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isPresent = isPresent(gridWrapperId, gridId, idOrXpath, rowNum, columnHeader);
			
			if (!isPresent)
				FailureHelper.failTest("CheckBox '" + idOrXpath + "' not found in grid '" + gridId + "'");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, rowNum, columnHeader);
			
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' at row number '" + rowNum + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled(String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, rowNum, columnHeader);
			
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' at row number '" + rowNum + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' for value '" + assertRowValue + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled(String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' for value '" + assertRowValue + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String gridId, String idOrXpath, int rowNum, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, rowNum, columnHeader);
			if (isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' at row number '" + rowNum + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled(String gridWrapperId, String gridId, String idOrXpath, int rowNum, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, rowNum, columnHeader);
			if (isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' at row number '" + rowNum + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader ) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' for value '" + assertRowValue + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled(String gridWrapperId, String gridId, String idOrXpath, String assertRowValue, String assertColumnHeader, String columnHeader) throws Exception {
		try {
			boolean isEnabled = isEnabled(gridWrapperId, gridId, idOrXpath, assertRowValue, assertColumnHeader, columnHeader);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' in grid '" + gridId + "' for value '" + assertRowValue + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}