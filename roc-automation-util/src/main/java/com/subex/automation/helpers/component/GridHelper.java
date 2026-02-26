package com.subex.automation.helpers.component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.jsoup.select.Elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.GridElementHelper;
import com.subex.automation.helpers.componentHelpers.LocatorHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GridHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if Grid is present in GUI
	 * If not present, test case will fail.
	 * @param gridId - id of the grid
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isPresent( String gridId ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			Elements element = GridElementHelper.getGridElement(gridId);
			
			if(element == null)
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
	 * This method is used to check if Grid is present in GUI
	 * If not present, test case will fail.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @throws Exception 
	 */
	public static boolean isPresent( String gridWrapper, String gridId ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			Elements element = GridElementHelper.getGridElement(gridWrapper, gridId);
			
			if(element == null)
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
	 * This method is used to click in the Grid.
	 * @param gridId - id of the grid
	 * @throws Exception
	 */
	public static void click( String gridId ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getEmptyGridElement(gridId);
			
			if (element != null) {
				boolean isClicked = false;
				
				if (ElementHelper.isClickable(element)) {
					MouseHelper.click(element);
					isClicked = true;
				}
				
				if (!isClicked)
					GridElementHelper.click(gridId, 1, 1);
			}
			else {
				FailureHelper.failTest("Grid '" + gridId + "' not found");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click in the Grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @throws Exception
	 */
	public static void click( String gridWrapper, String gridId ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getEmptyGridElement(gridWrapper, gridId);
			
			if (element != null) {
				boolean isClicked = false;
				
				if (ElementHelper.isClickable(element)) {
					MouseHelper.click(element);
					isClicked = true;
				}
				
				if (!isClicked)
					clickRow(gridWrapper, gridId, 1, 1);
			}
			else {
				FailureHelper.failTest("Grid '" + gridId + "' not found");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickRow( String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			GridElementHelper.clickCell(gridId, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickRow( String gridWrapper, String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String locator = LocatorHelper.getLocator(gridWrapper, gridId);
			
			GridElementHelper.clickCell(locator, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a row in the Grid.
	 * @param gridId - id of the grid
	 * @param assertValue - Row Value to find the row number to be clicked
	 * @param assertColumnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void clickRow( String gridId, String cellValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridId = GenericHelper.getORProperty(gridId);
				int cols = GridElementHelper.getColumnsInGrid(gridId);
				
				if (cols > 0) {
					for (int i = 1; i <= cols; i++) {
						int row = GridElementHelper.getRow(gridId, cellValue, i);
						
						if (row > 0) {
							GridElementHelper.clickCell(gridId, row, i);
						}
						else {
							FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
						}
					}
				}
				else {
					FailureHelper.failTest("There are no columns in grid '" + gridId + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickRow( String gridId, int rowNum, String valueColumnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, valueColumnHeader);
			
			if (col > 0) {
				GridElementHelper.clickCell(gridId, rowNum, col);
			}
			else {
				FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickRow( String gridId, int rowNum, String valueColumnHeader, int columnOccurance ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int[] col = GridElementHelper.getColumns(gridId, valueColumnHeader);
			
			if (ValidationHelper.isNotEmpty(col)) {
				GridElementHelper.clickCell(gridId, rowNum, col[columnOccurance-1]);
			}
			else {
				FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickRow( String gridWrapper, String gridId, int rowNum, String valueColumnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, valueColumnHeader);
			
			if (col > 0) {
				GridElementHelper.clickCell(gridWrapper, gridId, rowNum, col);
			}
			else {
				FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a row in the Grid.
	 * @param gridId - id of the grid
	 * @param assertValue - Row Value to find the row number to be clicked
	 * @param assertColumnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void clickRow( String gridId, String cellValue, String valueColumnHeader ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridId = GenericHelper.getORProperty(gridId);
				int col = GridElementHelper.getColumn(gridId, valueColumnHeader);
				
				if (col > 0) {
					int row = GridElementHelper.getRow(gridId, cellValue, col);
					
					if (row > 0) {
						GridElementHelper.clickCell(gridId, row, col);
					}
					else {
						FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a row in the Grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be clicked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void clickRow( String gridWrapper, String gridId, String cellValue, String valueColumnHeader ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				int col = GridElementHelper.getColumn(gridWrapper, gridId, valueColumnHeader);
				
				if (col > 0) {
					int row = GridElementHelper.getRow(gridWrapper, gridId, cellValue, col);
					
					if (row > 0) {
						GridElementHelper.clickCell(gridWrapper, gridId, row, col);
					}
					else
						FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
				}
				else {
					FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a row in the Grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @param assertValue - Row Value to find the row number to be clicked
	 * @param assertColumnHeader - Column Header Value or Number where row value has to be searched.
	 * @param columnHeader - Column Header Number to be clicked.
	 * @throws Exception
	 */
	public static void clickRow( String gridWrapper, String gridId, String cellValue, String valueColumnHeader, String clickColumnHeader ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				int col = GridElementHelper.getColumn(gridWrapper, gridId, valueColumnHeader);
				
				if (col > 0) {
					int row = GridElementHelper.getRow(gridWrapper, gridId, cellValue, col);
					
					if (row > 0) {
						col = GridElementHelper.getColumn(gridWrapper, gridId, clickColumnHeader);
						
						if (col > 0) {
							GridElementHelper.clickCell(gridWrapper, gridId, row, col);
						}
						else {
							FailureHelper.failTest("Expected column '" + clickColumnHeader + "' not found in grid '" + gridId + "'");
						}
					}
					else {
						FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected column '" + valueColumnHeader + "' not found in grid '" + gridId + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickAngularRow( String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			GridElementHelper.clickAngularCell(gridId, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to double click a row in the Grid.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be clicked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void doubleClick( String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			GridElementHelper.doubleClickCell(gridId, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to double click a row in the Grid.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be clicked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void doubleClick( String gridWrapper, String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			GridElementHelper.doubleClickCell(gridWrapper, gridId, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClick( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				GridElementHelper.doubleClickCell(gridId, rowNum, col);
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClick( String gridWrapper, String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				GridElementHelper.doubleClickCell(gridWrapper, gridId, rowNum, col);
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to double click a row in the Grid.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be clicked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void doubleClick( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridId = GenericHelper.getORProperty(gridId);
				int col = GridElementHelper.getColumn(gridId, columnHeader);
				
				if (col > 0) {
					int row = GridElementHelper.getRow(gridId, cellValue, col);
					
					if (row > 0)
						GridElementHelper.doubleClickCell(gridId, row, col);
					else {
						FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to double click a row in the Grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be clicked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @throws Exception
	 */
	public static void doubleClick( String gridWrapper, String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(cellValue)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
				
				if (col > 0) {
					int row = GridElementHelper.getRow(gridWrapper, gridId, cellValue, col);
					
					if (row > 0 )
						GridElementHelper.doubleClickCell(gridWrapper, gridId, row, col);
					else {
						FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClickAngularRow( String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			GridElementHelper.doubleClickAngularCell(gridId, rowNum, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridId, int startRowNum, int noOfRows ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int colNum = 1;
			
			GridElementHelper.clickMultipleCells(gridId, startRowNum, noOfRows, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridWrapper, String gridId, int startRowNum, int noOfRows ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int colNum = 1;
			
			GridElementHelper.clickMultipleCells(gridWrapper, gridId, startRowNum, noOfRows, colNum);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridId, String startRowValue, int noOfRows ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(startRowValue)) {
				gridId = GenericHelper.getORProperty(gridId);
				int colNum = 1;
				int rowNum = GridElementHelper.getRow(gridId, startRowValue, colNum);
				
				if (rowNum > 0) {
					GridElementHelper.clickMultipleCells(gridId, rowNum, noOfRows, colNum);
				}
				else {
					FailureHelper.failTest("Expected row with value '" + startRowValue + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridWrapper, String gridId, String startRowValue, int noOfRows ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(startRowValue)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				int colNum = 1;
				int rowNum = GridElementHelper.getRow(gridWrapper, gridId, startRowValue, colNum);
				
				if (rowNum > 0) {
					GridElementHelper.clickMultipleCells(gridWrapper, gridId, rowNum, noOfRows, colNum);
				}
				else {
					FailureHelper.failTest("Expected row with value '" + startRowValue + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridId, String startRowValue, String endRowValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(startRowValue)) {
				gridId = GenericHelper.getORProperty(gridId);
				int colNum = 1;
				int startRowNum = GridElementHelper.getRow(gridId, startRowValue, colNum);
				int endRowNum = GridElementHelper.getRow(gridId, endRowValue, colNum);
				
				if (startRowNum > 0) {
					if (endRowNum > 0) {
						GridElementHelper.clickMultipleCells(gridId, startRowNum, endRowNum, colNum);
					}
					else {
						FailureHelper.failTest("Expected row with value '" + endRowValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected row with value '" + startRowValue + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridWrapper, String gridId, String startRowValue, String endRowValue ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(startRowValue)) {
				gridWrapper = GenericHelper.getORProperty(gridWrapper);
				gridId = GenericHelper.getORProperty(gridId);
				int colNum = 1;
				int startRowNum = GridElementHelper.getRow(gridWrapper, gridId, startRowValue, colNum);
				int endRowNum = GridElementHelper.getRow(gridWrapper, gridId, endRowValue, colNum);
				
				if (startRowNum > 0) {
					if (endRowNum > 0) {
						GridElementHelper.clickMultipleCells(gridWrapper, gridId, startRowNum, endRowNum, colNum);
					}
					else {
						FailureHelper.failTest("Expected row with value '" + endRowValue + "' not found in grid '" + gridId + "'");
					}
				}
				else {
					FailureHelper.failTest("Expected row with value '" + startRowValue + "' not found in grid '" + gridId + "'");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to sort a grid
	 * @param gridId - id of the grid
	 * @param headerValue - Column Header Value based on which grid has to be sorted
	 * @throws Exception
	 */
	public static void sortGrid( String gridId, String headerValue ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			
			if (!headerValue.equals("") && !headerValue.equals("null") && !headerValue.equals("none")) {
				WebElement element = GridElementHelper.getHeaderElement(gridId, headerValue);
				
				if (element != null) {
					MouseHelper.click(element);
				}
				else {
					FailureHelper.failTest("Grid does not have header '" + headerValue + "'. So could not sort.");
				}
			}
			else {
				FailureHelper.failTest("Header value is empty. So could not sort grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to sort a grid
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - id of the grid
	 * @param headerValue - Column Header Value based on which grid has to be sorted
	 * @throws Exception
	 */
	public static void sortGrid( String gridWrapper, String gridId, String headerValue ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			
			if (!headerValue.equals("") && !headerValue.equals("null") && !headerValue.equals("none")) {
				WebElement element = GridElementHelper.getHeaderElement(gridWrapper, gridId, headerValue);
				
				if (element != null) {
					MouseHelper.click(element);
				}
				else {
					FailureHelper.failTest("Grid does not have header '" + headerValue + "'. So could not sort.");
				}
			}
			else {
				FailureHelper.failTest("Header value is empty. So could not sort grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setPagination(String gridId, String paginationLimit) throws Exception {
		ComboBoxHelper.select(gridId, "Pagination_Combo", paginationLimit);
		ButtonHelper.click("SearchButton");
		GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
	}
	
	public static void setPagination(String gridId, int paginationLimit) throws Exception {
		ComboBoxHelper.select(gridId, "Pagination_Combo", paginationLimit + " per page");
		ButtonHelper.click("SearchButton");
		GenericHelper.waitForLoadmask(configProp.getCustomScreenWaitSec());
	}
	
	/**
	 * This method is used to scroll down in a grid.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param columnHeader - Column Header Value or Number to find the cell to click.
	 * @throws Exception
	 */
	public static void scrollDown(String gridId, String firstVisibleRowNo, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridId, firstVisibleRowNo, col);
				
				if (row > 0) {
					String locator = GridElementHelper.getCellLocator(gridId, row, col);
					
					GridElementHelper.click(gridId, row, col);
					WebElement element = ElementHelper.getElement(locator);
					ElementHelper.pressKey(element, Keys.END);
				}
				else {
					if (row == 0) {
						FailureHelper.failTest("Expected row with value '" + firstVisibleRowNo + "' not found in grid '" + gridId + "'");
					}
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll down in a grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param columnHeader - Column Header Value or Number to find the cell to click.
	 * @throws Exception
	 */
	public static void scrollDown(String gridWrapper, String gridId, String firstVisibleRowNo, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridWrapper, gridId, firstVisibleRowNo, col);
				
				if (row > 0) {
					GridElementHelper.click(gridId, row, col);
					WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, row, col);
					
					if (element != null)
						ElementHelper.pressKey(element, Keys.END);
				}
				else {
					if (row == 0) {
						FailureHelper.failTest("Expected row with value '" + firstVisibleRowNo + "' not found in grid '" + gridId + "'");
					}
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollDown(String gridId, int firstVisibleRowNo, boolean fullScroll) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = 1;
			
			WebElement element = GridElementHelper.getCellElement(gridId, firstVisibleRowNo, col);
			MouseHelper.click(element);
			
			if (fullScroll)
				ElementHelper.pressKey(element, Keys.END);
			else
				ElementHelper.pressKey(element, Keys.PAGE_DOWN);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll down in a grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param columnHeader - Column Header Value or Number to find the cell to click.
	 * @throws Exception
	 */
	public static void scrollDown(String gridWrapper, String gridId, int firstVisibleRowNo, boolean fullScroll) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = 1;
			
			WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, firstVisibleRowNo, col);
			MouseHelper.click(element);
			
			if (fullScroll)
				ElementHelper.pressKey(element, Keys.END);
			else
				ElementHelper.pressKey(element, Keys.PAGE_DOWN);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll up in a grid.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param columnHeader - Column Header Value or Number to find the cell to click.
	 * @throws Exception
	 */
	public static void scrollUp(String gridId, String firstVisibleRowNo, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridId, firstVisibleRowNo, col);
				
				if (row > 0) {
					String locator = GridElementHelper.getCellLocator(gridId, row, col);
					
					GridElementHelper.click(gridId, row, col);
					WebElement element = ElementHelper.getElement(locator);
					if (element != null)
						ElementHelper.pressKey(element, Keys.HOME);
				}
				else {
					if (row == 0) {
						FailureHelper.failTest("Expected row with value '" + firstVisibleRowNo + "' not found in grid '" + gridId + "'");
					}
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to scroll up in a grid.
	 * @param gridWrapper - Div or Table id within which the grid is present.
	 * @param gridId - Grid's unique DIV ID.
	 * @param firstVisibleRowNo - First visible row number in the grid.
	 * @param columnHeader - Column Header Value or Number to find the cell to click.
	 * @throws Exception
	 */
	public static void scrollUp(String gridWrapper, String gridId, String firstVisibleRowNo, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridWrapper, gridId, firstVisibleRowNo, col);
				
				if (row > 0) {
					GridElementHelper.click(gridId, row, col);
					WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, row, col);
					
					if (element != null)
						ElementHelper.pressKey(element, Keys.HOME);
				}
				else {
					if (row == 0) {
						FailureHelper.failTest("Expected row with value '" + firstVisibleRowNo + "' not found in grid '" + gridId + "'");
					}
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollToTop(String gridId, String value, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			clickRow(gridId, value, columnHeader);
			
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int row = GridElementHelper.getRow(gridId, value, col);
			
			for (int i = 0; i < row; i++)
				ButtonHelper.click("Move Up");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollToTop(String gridWrapper, String gridId, String value, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			clickRow(gridWrapper, gridId, value, columnHeader);
			
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			int row = GridElementHelper.getRow(gridWrapper, gridId, value, col);
			
			for (int i = 0; i < row; i++)
				ButtonHelper.click(gridWrapper, "Move Up");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollToBottom(String gridId, String value, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			clickRow(gridId, value, columnHeader);
			
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int rows = GridElementHelper.getRowCount(gridId);
			int row = GridElementHelper.getRow(gridId, value, col);
			
			for (int i = 0; i < rows-row; i++)
				ButtonHelper.click("Move Down");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollToBottom(String gridWrapper, String gridId, String value, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			clickRow(gridWrapper, gridId, value, columnHeader);
			
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			int rows = GridElementHelper.getRowCount(gridWrapper, gridId);
			int row = GridElementHelper.getRow(gridWrapper, gridId, value, col);
			
			for (int i = 0; i < rows-row; i++)
				ButtonHelper.click(gridWrapper, "Move Down");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasNoResult(String gridId) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			
			if (ElementHelper.isElementPresent(or.getProperty("Empty_SearchGrid").replace("gridId", gridId)))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasNoResult(String wrapperId, String gridId) throws Exception {
		try {
			wrapperId = GenericHelper.getORProperty(wrapperId);
			gridId = GenericHelper.getORProperty(gridId);
			String wrapper = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (ElementHelper.isElementPresent(wrapper + or.getProperty("Empty_SearchGrid").replace("gridId", gridId)))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getCellElement(gridId, rowNum, colNum);
			MouseHelper.rightClick(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String gridWrapper, String gridId, int rowNum, int colNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, rowNum, colNum);
			MouseHelper.rightClick(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridId, cellValue, col);
				
				if (row > 0) {
					WebElement element = GridElementHelper.getCellElement(gridId, row, col);
					MouseHelper.rightClick(element);
				}
				else {
					FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String gridWrapper, String gridId, String value, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridWrapper, gridId, value, col);
				
				if (row > 0 ) {
					WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, row, col);
					MouseHelper.rightClick(element);
				}
				else {
					FailureHelper.failTest("Expected row with value '" + value + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * To right click and click sub action in the grid
	 * @param gridId
	 * @param mainActionId (<A> tag id)
	 * @param subActionText
	 * @throws Exception
	 */
	public static void rightClickSubMenu( String gridId, String mainActionId, String subActionId ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getEmptyGridElement(gridId);
			MouseHelper.rightClick(element);
			MouseHelper.mouseOver(mainActionId);
			
			String locator = GridElementHelper.getSubMenuElement(mainActionId, subActionId);
			if (locator != null)
				MouseHelper.click(locator);
			else
				FailureHelper.failTest("Sub Action '" + subActionId + "' is not found");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClickSubMenu( String gridWrapper, String gridId, String mainActionId, String subActionId ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String gridLocator = GridElementHelper.getEmptyGridLocator(gridWrapper, gridId);
			MouseHelper.rightClick(gridLocator);
			MouseHelper.mouseOver(mainActionId);
			
			String locator = GridElementHelper.getSubMenuElement(mainActionId, subActionId);
			if (locator != null)
				MouseHelper.click(locator);
			else
				FailureHelper.failTest("Sub Action '" + subActionId + "' is not found");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount ( String gridId ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int rows = GridElementHelper.getRowCount(gridId);

			return rows;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount ( String gridWrapper, String gridId ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int rows = GridElementHelper.getRowCount(gridWrapper, gridId);

			return rows;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount ( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			ArrayList<String> colValues = getColumnValues(gridId, col);
			int rows = 0;
			
			if (ValidationHelper.isNotEmpty(colValues)) {
				for (int i = 0; i < colValues.size(); i++) {
					if (colValues.get(i).equals(cellValue))
						rows++;
				}
			}
			
			return rows;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount ( String gridWrapper, String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			ArrayList<String> colValues = getColumnValues(gridWrapper, gridId, col);
			int rows = 0;
			
			if (ValidationHelper.isNotEmpty(colValues)) {
				for (int i = 0; i < colValues.size(); i++) {
					if (colValues.get(i).equals(cellValue))
						rows++;
				}
			}
			
			return rows;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellValue( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String value = GridElementHelper.getValue(gridId, rowNum, columnNum);
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellValue( String gridWrapper, String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			
			String value = GridElementHelper.getValue(gridWrapper, gridId, rowNum, columnNum);
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellValue( String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			String value = GridElementHelper.getValue(gridId, rowNum, col);
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellValue( String gridWrapper, String gridId, int rowNum, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			String value = GridElementHelper.getValue(gridWrapper, gridId, rowNum, col);
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getRowValues( String gridId, int rowNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			ArrayList<String> value = GridElementHelper.getRowValues(gridId, rowNum);
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getRowValues( String gridWrapper, String gridId, int rowNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			ArrayList<String> value = GridElementHelper.getRowValues(gridWrapper, gridId, rowNum);
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getColumnValues( String gridId, int columnNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			ArrayList<String> value = new ArrayList<String>();
			int rows = GridElementHelper.getRowCount(gridId);
			
			for (int i = 0; i < rows; i++) {
				value.add(getCellValue(gridId, (i+1), columnNum));
			}
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getColumnValues( String gridId, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int colNum = GridElementHelper.getColumn(gridId, columnHeader);
			ArrayList<String> value = getColumnValues(gridId, colNum);
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getColumnValues( String gridWrapper, String gridId, int columnNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			ArrayList<String> value = new ArrayList<String>();
			int rows = GridElementHelper.getRowCount(gridWrapper, gridId);
			
			for (int i = 0; i < rows; i++) {
				value.add(getCellValue(gridWrapper, gridId, (i+1), columnNum));
			}
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateValue (String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getCellValue(gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value)) {
				String dateFormat = configProp.getDateFormat() + " " + configProp.getTimeFormat();
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				value = format.parse(value) + "";
			}
			
			return value;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateValue (String gridWrapper, String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getCellValue(gridWrapper, gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value)) {
				String dateFormat = configProp.getDateFormat() + " " + configProp.getTimeFormat();
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				value = format.parse(value) + "";
			}
			
			return value;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateTimeValue (String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getCellValue(gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value)) {
				String dateFormat = configProp.getDateFormat() + " " + configProp.getTimeFormat();
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				value = format.parse(value) + "";
			}
			
			return value;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDateTimeValue (String gridWrapper, String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getCellValue(gridWrapper, gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value)) {
				String dateFormat = configProp.getDateFormat() + " " + configProp.getTimeFormat();
				SimpleDateFormat format = new SimpleDateFormat(dateFormat);
				value = format.parse(value) + "";
			}
			
			return value;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getBooleanValue (String gridId, int rowNum, int colNum) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String locator = GenericHelper.getORProperty("Grid_Boolean_CheckBox");
			WebElement element = GridElementHelper.getCellElement(gridId, rowNum, colNum);
			boolean istrue = false;
			
			if (element != null)
				istrue = ElementHelper.isElementPresent(element, locator);
			
			return !istrue;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getBooleanValue (String gridWrapper, String gridId, int rowNum, int colNum) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String locator = GenericHelper.getORProperty("Grid_Boolean_CheckBox");
			WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, rowNum, colNum);
			boolean istrue = false;
			
			if (element != null)
				istrue = ElementHelper.isElementPresent(element, locator);
			
			return !istrue;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getBooleanValue (String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String locator = GenericHelper.getORProperty("Grid_Boolean_CheckBox");
			int colNum = GridElementHelper.getColumn(gridId, columnHeader);
			WebElement element = GridElementHelper.getCellElement(gridId, rowNum, colNum);
			boolean istrue = false;
			
			if (element != null)
				istrue = ElementHelper.isElementPresent(element, locator);
			
			return !istrue;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getBooleanValue (String gridWrapper, String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String locator = GenericHelper.getORProperty("Grid_Boolean_CheckBox");
			int colNum = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, rowNum, colNum);
			boolean istrue = false;
			
			if (element != null)
				istrue = ElementHelper.isElementPresent(element, locator);
			
			return !istrue;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumber( String gridId, String cellValue, int colNum ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int rowNum = GridElementHelper.getRow(gridId, cellValue, colNum);
			
			return rowNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumber( String gridWrapper, String gridId, String cellValue, int colNum ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int rowNum = GridElementHelper.getRow(gridWrapper, gridId, cellValue, colNum);
			
			return rowNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumber( String gridId, String cellValue ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumnsInGrid(gridId);
			
			if (col > 0) {
				for (int i = 1; i < (col+1); i++) {
					int rowNum = GridElementHelper.getRow(gridId, cellValue, i);
					if (rowNum > 0)
						return rowNum;
				}
			}
			
			return 0;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumber( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int rowNum = GridElementHelper.getRow(gridId, cellValue, col);
			
			return rowNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumber( String gridWrapper, String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			int rowNum = GridElementHelper.getRow(gridWrapper, gridId, cellValue, col);
			
			return rowNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowNumberContains( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int rowNum = GridElementHelper.getRowContains(gridId, cellValue, col);
			
			return rowNum;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] getRowNumbers( String gridId, String cellValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			ArrayList<String> colValues = getColumnValues(gridId, col);
			
			if (ValidationHelper.isNotEmpty(colValues)) {
				int[] rows = new int[1000];
				int length = 0;
				
				for (int i = 0; i < colValues.size(); i++) {
					if (colValues.get(i).equals(cellValue)) {
						rows[length++] = i + 1;
					}
				}
				
				rows = GenericHelper.resizeIntArray(rows, length);
				return rows;
			}
			
			
			return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumnNumber ( String gridId, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getHeaderColumn(gridId, columnHeader);
			
			return col;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumnNumber ( String gridWrapper, String gridId, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getHeaderColumn(gridWrapper, gridId, columnHeader);
			
			return col;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridId, String value ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int cols = GridElementHelper.getColumnsInGrid(gridId);
			
			if (cols > 0) {
				for (int i = 1; i <= cols; i++) {
					int row = GridElementHelper.getRow(gridId, value, i);
					
					if (row > 0)
						return true;
				}
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridId, String value, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			int row = GridElementHelper.getRow(gridId, value, col);
			
			if (row == 0)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridWrapper, String gridId, String value, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			int row = GridElementHelper.getRow(gridWrapper, gridId, value, col);
			
			if (row == 0)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridId, int rowNo, String value, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				String expectedValue = GridElementHelper.getValue(gridId, rowNo, col);
				
				if(expectedValue.equals(value))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent(String gridWrapper, String gridId, int rowNo, String value, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper,gridId, columnHeader);
			String expectedValue = GridElementHelper.getValue(gridWrapper,gridId, rowNo, col);
			
			if(expectedValue.equals(value))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridId, String assertValue, String assertColumnHeader, String expectedValue, String columnHeader ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridId, assertValue, col);
			
			col = GridElementHelper.getColumn(gridId, columnHeader);
			String actualValue = GridElementHelper.getValue(gridId, row, col);
			
			if (expectedValue.equals(actualValue))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String gridWrapper, String gridId, String assertValue, String assertColumnHeader, String expectedValue, String columnHeader ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, assertColumnHeader);
			int row = GridElementHelper.getRow(gridWrapper, gridId, assertValue, col);
			
			col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			String actualValue = GridElementHelper.getValue(gridWrapper, gridId, row, col);
			
			if (expectedValue.equals(actualValue))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isRowValuesPresent ( String gridId , String assertValue, String assertColumnHeader, String gridvalues) throws Exception{
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int colNum = GridElementHelper.getColumn(gridId, assertColumnHeader);
			int rowNum = GridElementHelper.getRow(gridId, assertValue, colNum);

			String[] rowValues = gridvalues.split(":");
			ArrayList<String> values = GridElementHelper.getRowValues(gridId, rowNum);
			
			if (ValidationHelper.isNotEmpty(values)) {
				for (int i = 0; i < rowValues.length; i++) {
					if (rowValues[i].equals(values.get(i))) {
						return true;
					}
				}
			}
			
			return false;
		}
		catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	} 
	
	public static boolean isRowValuesPresent ( String gridWrapper, String gridId , String assertValue, String assertColumnHeader, String gridvalues) throws Exception{
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int colNum = GridElementHelper.getColumn(gridWrapper, gridId, assertColumnHeader);
			int rowNum = GridElementHelper.getRow(gridWrapper, gridId, assertValue, colNum);
			
			String[] rowValues = gridvalues.split(":");
			ArrayList<String> values = GridElementHelper.getRowValues(gridWrapper, gridId, rowNum);
			
			if (ValidationHelper.isNotEmpty(values)) {
				for (int i = 0; i < rowValues.length; i++) {
					if (rowValues[i].equals(values.get(i))) {
						return true;
					}
				}
			}
			
			return false;
		}
		catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDatePresent (String gridId, String expectedValue, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getDateValue(gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value) && expectedValue.equals(value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDateTimePresent (String gridId, String expectedValue, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getDateTimeValue(gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value) && expectedValue.equals(value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDateTimePresent (String gridWrapper, String gridId, String expectedValue, int rowNum, String columnHeader) throws Exception {
		try {
			String value = getDateTimeValue(gridWrapper, gridId, rowNum, columnHeader);
			
			if (ValidationHelper.isNotEmpty(value) && expectedValue.equals(value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isBooleanValuePresent (String gridId, boolean expectedValue, int rowNum, int colNum) throws Exception {
		try {
			boolean value = getBooleanValue(gridId, rowNum, colNum);
			
			if ((expectedValue && value) || (!expectedValue && !value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isBooleanValuePresent (String gridWrapper, String gridId, boolean expectedValue, int rowNum, int colNum) throws Exception {
		try {
			boolean value = getBooleanValue(gridWrapper, gridId, rowNum, colNum);
			
			if ((expectedValue && value) || (!expectedValue && !value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isBooleanValuePresent (String gridId, boolean expectedValue, int rowNum, String columnHeader) throws Exception {
		try {
			boolean value = getBooleanValue(gridId, rowNum, columnHeader);
			
			if ((expectedValue && value) || (!expectedValue && !value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isBooleanValuePresent (String gridWrapper, String gridId, boolean expectedValue, int rowNum, String columnHeader) throws Exception {
		try {
			boolean value = getBooleanValue(gridWrapper, gridId, rowNum, columnHeader);
			
			if ((expectedValue && value) || (!expectedValue && !value))
				return true;
			else
				return false;
		} catch (Exception e){
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the row is in deleted state or not.
	 * If not, test case will fail.
	 * @param gridId - id of the grid
	 * @param rowNum - Row Value or Number to be checked
	 * @param columnHeader - Column Header Value or Number where row value has to be searched.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isRowDeleted(String gridId, String cellValue, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridId, cellValue, col);
				
				if (row > 0) {
					WebElement element = GridElementHelper.getCellElement(gridId, row, col);
					String style = ElementHelper.getAttribute(element, "style");
					if (style.contains("line-through"))
						return true;
				}
				else {
					FailureHelper.failTest("Expected row with value '" + cellValue + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isRowDeleted(String gridWrapper, String gridId, String rowNum, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			int col = GridElementHelper.getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				int row = GridElementHelper.getRow(gridWrapper, gridId, rowNum, col);
				
				if (row > 0) {
					WebElement element = GridElementHelper.getCellElement(gridWrapper, gridId, row, col);
					String style = ElementHelper.getAttribute(element, "style");
					if (style.contains("line-through"))
						return true;
				}
				else {
					FailureHelper.failTest("Expected row with value '" + rowNum + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isImagePresent(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getImageElement(gridId, rowNum, columnHeader);
			
			if (element != null)
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isImagePresent(String gridId, int rowNum, String columnHeader, String srcValue) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getImageElement(gridId, rowNum, columnHeader);
			
			if (element != null) {
				String src = ElementHelper.getAttribute(element, "src");
				if (src.contains(srcValue))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isImagePresent(String gridWrapper, String gridId, int rowNum, String columnHeader, String srcValue) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getImageElement(gridWrapper, gridId, rowNum, columnHeader);
			
			if (element != null) {
				String src = ElementHelper.getAttribute(element, "src");
				if (src.contains(srcValue))
					return true;
				else
					return false;
			}
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isErrorIconPresent(String gridId) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getErrorIconElement(gridId);
			return GridElementHelper.isErrorIconPresent(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isErrorIconPresent(String gridWrapper, String gridId) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = GridElementHelper.getErrorIconElement(gridWrapper, gridId);
			return GridElementHelper.isErrorIconPresent(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridTextBox(String gridId, String textBoxId, int rowNum, String columnName, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnName);
				if(!TextBoxHelper.isPresent(textBoxId))
					clickRow(gridId, rowNum, columnName);
				TextBoxHelper.type(textBoxId, value);
				click(gridId);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridTextBox(String gridId, String textBoxId, int rowNum, String columnName, String clickColumnName, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnName);
				if(!TextBoxHelper.isPresent(textBoxId))
					clickRow(gridId, rowNum, columnName);
				TextBoxHelper.type(textBoxId, value);
				clickRow(gridId, rowNum, clickColumnName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridTextBox(String gridId, String textBoxId, int rowNum, int columnNum, String clickColumnName, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnNum);
				if(!TextBoxHelper.isPresent(textBoxId))
					clickRow(gridId, rowNum, columnNum);
				TextBoxHelper.type(textBoxId, value);
				clickRow(gridId, rowNum, clickColumnName);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridTextBox(String gridId, String textBoxId, int rowNum, int columnNum, int clickColumnNum, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnNum);
				if(!TextBoxHelper.isPresent(textBoxId))
					clickRow(gridId, rowNum, columnNum);
				TextBoxHelper.type(textBoxId, value);
				clickRow(gridId, rowNum, clickColumnNum);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridComboBox(String gridId, String comboId, int rowNum, String valueColumnHeader, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				int columnNum = GridHelper.getColumnNumber(gridId, valueColumnHeader);
				clickRow(gridId, rowNum, valueColumnHeader);
				if (!ComboBoxHelper.isPresent(comboId))
					clickRow(gridId, rowNum, valueColumnHeader);
				ComboBoxHelper.select(gridId, comboId, rowNum, columnNum, value);
				click(gridId);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridComboBox(String gridId, String comboId, int rowNum, String valueColumnHeader, String clickColumnHeader, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				int columnNum = GridHelper.getColumnNumber(gridId, valueColumnHeader);
				clickRow(gridId, rowNum, valueColumnHeader);
				if (!ComboBoxHelper.isPresent(comboId))
					clickRow(gridId, rowNum, valueColumnHeader);
				ComboBoxHelper.select(gridId, comboId, rowNum, columnNum, value);
				clickRow(gridId, rowNum, clickColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridComboBox(String gridId, String comboId, int rowNum, int columnNum, String clickColumnHeader, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnNum);
				if (!ComboBoxHelper.isPresent(comboId))
					clickRow(gridId, rowNum, columnNum);
				ComboBoxHelper.select(gridId, comboId, rowNum, columnNum, value);
				clickRow(gridId, rowNum, clickColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridCheckBox(String gridId, String checkBoxId, int rowNum, String columnName, boolean value) throws Exception {
		try {
			if (value)
				GridCheckBoxHelper.check(gridId, checkBoxId, rowNum, columnName);
			else
				GridCheckBoxHelper.uncheck(gridId, checkBoxId, rowNum, columnName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridCheckBox(String gridId, String checkBoxId, int rowNum, String columnName, String value) throws Exception {
		try {
			boolean bolValue = ValidationHelper.isTrue(value);
			
			if (bolValue)
				GridCheckBoxHelper.check(gridId, checkBoxId, rowNum, columnName);
			else
				GridCheckBoxHelper.uncheck(gridId, checkBoxId, rowNum, columnName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridCheckBox(String gridId, int rowNum, String columnName, String value) throws Exception {
		try {
			boolean bolValue = ValidationHelper.isTrue(value);
			
			if (bolValue)
				GridCheckBoxHelper.check(gridId, rowNum, columnName);
			else
				GridCheckBoxHelper.uncheck(gridId, rowNum, columnName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridCheckBox(String gridId, int rowNum, String columnName, boolean value) throws Exception {
		try {
			if (value)
				GridCheckBoxHelper.check(gridId, rowNum, columnName);
			else
				GridCheckBoxHelper.uncheck(gridId, rowNum, columnName);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void updateGridEntityCombo(String gridId, String entityComboId, int rowNum, String columnName, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				clickRow(gridId, rowNum, columnName);
				if(!EntityComboHelper.isPresent(entityComboId))
					clickRow(gridId, rowNum, columnName);
				EntityComboHelper.clickEntityIcon(entityComboId);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortAscending( String gridId, String headerValue ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String sortLocator = GridElementHelper.getSortType("Ascending");
			
			WebElement element = GridElementHelper.getHeaderArrowElement(gridId, headerValue);
			MouseHelper.click(element);
			MouseHelper.click(sortLocator);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortAscending( String gridWrapper, String gridId, String headerValue ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String sortLocator = GridElementHelper.getSortType("Ascending");
			
			WebElement element = GridElementHelper.getHeaderArrowElement(gridWrapper, gridId, headerValue);
			MouseHelper.click(element);
			MouseHelper.click(sortLocator);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortDescending( String gridId, String headerValue ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			String sortLocator = GridElementHelper.getSortType("Descending");
			
			WebElement element = GridElementHelper.getHeaderArrowElement(gridId, headerValue);
			MouseHelper.click(element);
			MouseHelper.click(sortLocator);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortDescending( String gridWrapper, String gridId, String headerValue ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			String sortLocator = GridElementHelper.getSortType("Descending");
			
			WebElement element = GridElementHelper.getHeaderArrowElement(gridWrapper, gridId, headerValue);
			MouseHelper.click(element);
			MouseHelper.click(sortLocator);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}