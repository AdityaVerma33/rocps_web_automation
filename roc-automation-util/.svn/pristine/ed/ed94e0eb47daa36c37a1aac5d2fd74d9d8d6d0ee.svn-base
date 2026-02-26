package com.subex.automation.helpers.componentHelpers;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.JSoupHelper;

public class GridElementHelper extends AcceptanceTest {
	
	private static Document document = null;
	private static String gridId = null;
	private static int rowNo = -1;
	private static int colNo = -1;
	private static boolean isDivTag = true;
	private static String rowLocator = "tr[class*=NG], tr[class*=MH], tr[class*=CG]";
	
	private static String[] getGridColumnLocators() throws Exception {
		try {
			String[] gridLocators = {"Grid_Column_ByDiv", "Grid_Column_ByInput", "Grid_Column_ByTriggerDiv",
									"Grid_Column_BySpanInput", "Grid_Column"};
			
			return gridLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isFirstTimeClick(String gridID, int row, int col) throws Exception {
		try {
			boolean firstTimeClick = true;
			
			if (ValidationHelper.isEmpty(gridId) || !gridId.equals(gridID)) {
				gridId = gridID;
				rowNo = row;
				colNo = col;
			}
			else {
				if (rowNo == row && colNo == col)
					firstTimeClick = false;
				else {
					rowNo = row;
					colNo = col;
				}
			}
			
			return firstTimeClick;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static Elements getTableElement(Elements gridByDiv) throws Exception {
		try {
			Elements gridDiv = gridByDiv.select("div.MH, div.GNHAT4HCMH");
			Elements gridByTable = null;
			if (ValidationHelper.isNotEmpty(gridDiv))
				gridByTable = gridDiv.select("table");
			else
				gridByTable = gridByDiv.select("table");
			
			if (ValidationHelper.isNotEmpty(gridByTable))
				return gridByTable;
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Elements getGridElement( String gridId ) throws Exception {
		try {
			JSoupHelper jSoup = new JSoupHelper();
			Elements gridByDiv = jSoup.getElements("div#" + gridId + ", div." + gridId);
			
			if (gridByDiv == null) {
				isDivTag = false;
				gridByDiv = jSoup.getElements("table#" + gridId + ", table." + gridId);
			}
			else
				isDivTag = true;
			
			if (gridByDiv != null && gridByDiv.size() > 0) {
				Elements gridByTable = getTableElement(gridByDiv);
				
				return gridByTable;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Elements getGridElement( String gridWrapper, String gridId ) throws Exception {
		try {
			JSoupHelper jSoup = new JSoupHelper();
			Elements wrapper = jSoup.getElements("div#" + gridWrapper + ", table#" + gridWrapper);
			Elements gridByDiv = wrapper.select("div#" + gridId + ", table#" + gridId);
			Elements gridByTable = getTableElement(gridByDiv);
			
			return gridByTable;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String gridId) throws Exception {
		try {
			String locator = null;
			
			if (gridId.contains("/")) {
				if (ElementHelper.isElementPresent(gridId))
					locator = gridId;
			}
			else {
				JSoupHelper jSoup = new JSoupHelper();
				document = jSoup.initializeDocument();
				Elements gridByDiv = document.select("div#" + gridId);
				Elements gridByTable = document.select("table#" + gridId);
				
				if (ValidationHelper.isNotEmpty(gridByDiv))
					locator = or.getProperty("Grid_Div_ById").replace("gridId", gridId);
				else if (ValidationHelper.isNotEmpty(gridByTable))
					locator = or.getProperty("Grid_Table_ById").replace("gridId", gridId);
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellLocator( String gridId, int row) throws Exception {
		try {
			Elements gridTable = getGridElement(gridId);
			String locator = null;
			
			if (ValidationHelper.isNotEmpty(gridTable)) {
				Elements tableRows = gridTable.select( rowLocator );
				
				if (ValidationHelper.isNotEmpty(tableRows)) {
					Element tableRow = tableRows.get(row-1);
					
					if (tableRow != null) {
						if (isDivTag) {
							locator = or.getProperty("Grid_Div_ById").replace("gridId", gridId);
							locator = locator + or.getProperty("Grid_Row_ByDiv").replace("rowNo", String.valueOf(row));
						}
						else {
							locator = or.getProperty("Grid_Table_ById").replace("gridId", gridId);
							locator = locator + or.getProperty("Grid_Row_ByTable").replace("rowNo", String.valueOf(row));
						}
					}
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellLocator( String gridWrapper, String gridId, int row) throws Exception {
		try {
			String wrapperLocator = LocatorHelper.getWrapperLocator(gridWrapper);
			Elements gridTable = getGridElement(gridWrapper, gridId);
			String locator = null;
			
			if (ValidationHelper.isNotEmpty(gridTable)) {
				Elements tableRows = gridTable.select( rowLocator );
				
				if (ValidationHelper.isNotEmpty(tableRows)) {
					Element tableRow = tableRows.get(row-1);
				
					if (tableRow != null) {
						if (isDivTag) {
							locator = wrapperLocator + or.getProperty("Grid_Div_ById").replace("gridId", gridId);
							locator = locator + or.getProperty("Grid_Row_ByDiv").replace("rowNo", String.valueOf(row));
						}
						else {
							locator = wrapperLocator + or.getProperty("Grid_Table_ById").replace("gridId", gridId);
							locator = locator + or.getProperty("Grid_Row_ByTable").replace("rowNo", String.valueOf(row));
						}
					}
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getCellLocator( String gridId, int row, int col ) throws Exception {
		try {
			String locator = getCellLocator(gridId, row);
			
			if(ElementHelper.isElementPresent(locator + "/td["+col+"]//div"))
				return locator + "/td["+col+"]//div";
	
			 else if (ElementHelper.isElementPresent(locator + "/td["+col+"]//input"))
				 return locator + "/td["+col+"]//input";
			
			 else if (ElementHelper.isElementPresent(locator + "/td["+col+"]//div[@id='trigger-id']"))
				 return locator + "/td["+col+"]//div[@id='trigger-id']";
			
			 else if (ElementHelper.isElementPresent(locator + "/td["+col+"]//span/input"))
				 return locator + "/td["+col+"]//span/input";
			 
			 else
				 return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String gridId) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(gridId);
			
			if (element == null) {
				String locator = or.getProperty("Grid_Div_ById").replace("gridId", gridId);
				element = ElementHelper.getElement(locator);
				
				if (element == null) {
					locator = or.getProperty("Grid_Table_ById").replace("gridId", gridId);
					element = ElementHelper.getElement(locator);
				}
			}
			
			if (element!= null && element.getTagName().equalsIgnoreCase("div"))
				isDivTag = true;
			else
				isDivTag = false;
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String wrapperId, String gridId ) throws Exception {
		try {
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				element = ElementHelper.getElement(wrapperElement, gridId);
				
				if (element == null) {
					String locator = or.getProperty("Grid_Div_ById").replace("gridId", gridId);
					element = ElementHelper.getElement(wrapperElement, locator);
					
					if (element == null) {
						locator = or.getProperty("Grid_Table_ById").replace("gridId", gridId);
						element = ElementHelper.getElement(wrapperElement, locator);
					}
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getRowElement( String gridId, int row) throws Exception {
		try {
			WebElement gridElement = getElement(gridId);
			WebElement element = null;
			
			if (gridElement != null) {
				String rowNo = String.valueOf(row);
				String locator = null;
				
				if (isDivTag)
					locator = or.getProperty("Grid_Row_ByDiv").replace("rowNo", rowNo);
				else
					locator = or.getProperty("Grid_Row_ByTable").replace("rowNo", rowNo);
				
				element = ElementHelper.getElement(gridElement, locator);
				
				if (element != null) {
					String style = ElementHelper.getAttribute(element, "style");
					if (style.contains("display: none;")) {
						rowNo = (Integer.parseInt(rowNo) + 1) + "";
						if (isDivTag)
							locator = or.getProperty("Grid_Row_ByDiv").replace("rowNo", rowNo);
						else
							locator = or.getProperty("Grid_Row_ByTable").replace("rowNo", rowNo);
						element = ElementHelper.getElement(gridElement, locator);
					}
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getRowElement( String wrapperId, String gridId, int row) throws Exception {
		try {
			WebElement gridElement = getElement(wrapperId, gridId);
			WebElement element = null;
			
			if (gridElement != null) {
				String rowNo = String.valueOf(row);
				String locator = or.getProperty("Grid_Row_ByDiv").replace("rowNo", rowNo);
				element = ElementHelper.getElement(gridElement, locator);
				
				if (element == null) {
					locator = or.getProperty("Grid_Row_ByTable").replace("rowNo", rowNo);
					element = ElementHelper.getElement(gridElement, locator);
					
					if (element == null) {
						locator = or.getProperty("Grid_Row").replace("rowNo", rowNo);
						element = ElementHelper.getElement(gridElement, locator);
					}
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static WebElement getCellElement(WebElement rowElement, int col) throws Exception {
		try {
			String[] colLocators = getGridColumnLocators();
			String colNum = String.valueOf(col);
			WebElement element = null;
			
			for (int i = 0; i < colLocators.length; i++) {
				String colLocator = or.getProperty(colLocators[i]).replace("colNo", colNum);
				element = ElementHelper.getElement(rowElement, colLocator);
				
				if (element != null)
					break;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getCellElement( String gridId, int row, int col ) throws Exception {
		try {
			WebElement rowElement = getRowElement(gridId, row);
			WebElement element = null;
			
			if (rowElement != null) {
				element = getCellElement(rowElement, col);
				
				if (element != null && ElementHelper.getText(element).equals("")) {
					String tblLocator = LocatorHelper.getLocator(element);
					int lastIndex = tblLocator.lastIndexOf("//");
					tblLocator = tblLocator.substring(0, lastIndex);
					element = ElementHelper.getElement(tblLocator);
				}
				
				GridElementHelper.gridId = gridId;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getCellElement( String gridWrapper, String gridId, int row, int col ) throws Exception {
		try {
			WebElement rowElement = getRowElement(gridWrapper, gridId, row);
			WebElement element = null;
			
			if (rowElement != null) {
				element = getCellElement(rowElement, col);
						
				if (element != null && ElementHelper.getText(element).equals("")) {
					String tblLocator = LocatorHelper.getLocator(element);
					int lastIndex = tblLocator.lastIndexOf("//");
					tblLocator = tblLocator.substring(0, lastIndex);
					element = ElementHelper.getElement(tblLocator);
				}
			}
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getAngularCellElement( String gridId, int row, int col ) throws Exception {
		try {
			WebElement rowElement = getRowElement(gridId, row);
			WebElement element = null;
			
			if (rowElement != null) {
				element = getCellElement(rowElement, col);
				
				if (element != null && ElementHelper.getText(element).equals("")) {
					String tblLocator = LocatorHelper.getLocator(element);
					int lastIndex = tblLocator.lastIndexOf("//");
					tblLocator = tblLocator.substring(0, lastIndex);
					element = ElementHelper.getElement(tblLocator);
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getHeaderElement(WebElement gridElement, String headerValue) throws Exception {
		try {
			String headerValueLocator = or.getProperty("Grid_HeaderValue").replace("headerValue", headerValue);
			List<WebElement> elements = ElementHelper.getElements(gridElement, headerValueLocator);
			WebElement element = null;
			
			if (elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					WebElement tempElement =  elements.get(i);
					String value = tempElement.getText();
					if (ValidationHelper.isEmpty(value)) {
						value = ElementHelper.getAttribute(tempElement, "innerHTML");
						value = value.replace("&nbsp;", "");
					}
					if (value.trim().equals(headerValue))
						element = tempElement;
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getHeaderElement(String gridId, String headerValue) throws Exception {
		try {
			WebElement grid = getElement(gridId);
			WebElement element = getHeaderElement(grid, headerValue);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getHeaderElement(String gridWrapper, String gridId, String headerValue) throws Exception {
		try {
			WebElement grid = getElement(gridWrapper, gridId);
			WebElement element = getHeaderElement(grid, headerValue);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getSortType( String type ) throws Exception {
		try {
			String locator = null;
			
			if (type.equalsIgnoreCase("Ascending"))
				locator = "Grid_SortAscending";
			else
				locator = "Grid_SortDescending";
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getHeaderArrowElement(String gridId, String headerValue) throws Exception {
		try {
			WebElement element = null;
			
			if (!headerValue.equals("") && !headerValue.equals("null") && !headerValue.equals("none")) {
				element = getHeaderElement(gridId, headerValue);
				
				if (element != null)
					element = ElementHelper.getElement(element, "Grid_HeaderArrow");
				
				if (element == null) {
					FailureHelper.failTest("Grid does not have header '" + headerValue + "'. So could not sort.");
				}
			}
			else {
				FailureHelper.failTest("Header value is empty. So could not sort grid '" + gridId + "'");
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getHeaderArrowElement(String gridWrapper, String gridId, String headerValue) throws Exception {
		try {
			WebElement element = null;
			
			if (!headerValue.equals("") && !headerValue.equals("null") && !headerValue.equals("none")) {
				element = getHeaderElement(gridWrapper, gridId, headerValue);
				
				if (element != null)
					element = ElementHelper.getElement(element, "Grid_HeaderArrow");
				
				if (element == null) {
					FailureHelper.failTest("Grid does not have header '" + headerValue + "'. So could not sort.");
				}
			}
			else {
				FailureHelper.failTest("Header value is empty. So could not sort grid '" + gridId + "'");
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getSubMenuElement(String mainActionId, String subActionId) throws Exception {
		try {
			String subActionLocator = or.getProperty("Grid_Main_SubAction").replace("subActionId", subActionId);
			String subActionLocator1 = or.getProperty("Grid_SubAction").replace("subActionId", subActionId);
			String locator = null;
			
			if(ElementHelper.isElementPresent(subActionLocator.replace("mainActionId", "dayGroupMenu")))
				locator = subActionLocator.replace("mainActionId", "dayGroupMenu");
			else if(!mainActionId.startsWith("/") && ElementHelper.isElementPresent(subActionLocator.replace("mainActionId", mainActionId)))
				locator = subActionLocator.replace("mainActionId", mainActionId);
			else if(mainActionId.startsWith("/") && ElementHelper.isElementPresent(mainActionId + subActionLocator1))
				locator = mainActionId + subActionLocator1;
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String trimHeader( String headerName ) throws Exception {
		try {
			headerName = headerName.trim();
			if (headerName.contains("  "))
				headerName = headerName.replace("  ", " ");
			
			if (headerName.contains("\u00A0"))
				headerName = headerName.replace("\u00A0", "");
			
			return headerName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getColumnNumber(List<WebElement> headers, String headerName) throws Exception {
		try {
			int colNo = 0;
			
			if (headers != null && headers.size() > 0) {
				int length = headers.size(); 
				String[] value = new String[length];
				headerName = trimHeader(headerName);
				
				for (int i = 0; i < length; i++) {
					WebElement rowValue = headers.get( i );
					String innerText = rowValue.getAttribute("innerHTML");
					String text = rowValue.getText().trim();
					value[i] = innerText.replace("&nbsp;", "").trim();
					
					if (value[i].equals(headerName) || value[i].equals(" " + headerName) || value[i].equals(headerName + " ") || value[i].equals(" " + headerName + " ")) {
						colNo = i + 1;
						break;
					}
					
					if (text.equals(headerName) || text.equals(" " + headerName) || text.equals(headerName + " ") || text.equals(" " + headerName + " ")) {
						value[i] = text;
						colNo = i + 1;
						break;
					}
				}
				
				if (colNo == 0) {
					int index = StringHelper.arrayContains(value, headerName);
					if (index >= 0)
						colNo = index + 1;
				}
			}
			
			if (colNo == 0 && ValidationHelper.isInteger(headerName))
				colNo = Integer.parseInt(headerName);
			
			if (colNo == 0)
				FailureHelper.failTest("Column Name '" + headerName + "' is not found in grid '" + gridId + "'");
			return colNo;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getHeader( String gridId, int colNumber ) throws Exception {
		try {
			WebElement grid = getElement(gridId);
			
			if (grid != null) {
				GridElementHelper.gridId = gridId;
				List<WebElement> headers = ElementHelper.getElements(grid, "Grid_Header");
				
				if (headers == null || headers.size() == 0) {
					headers = ElementHelper.getElements(grid, "//tr[contains(@class,'roc-property-grid-col-header')]/td");
				}
				
				if (headers != null && headers.size() > 0) {
					String value = headers.get(colNumber-1).getText();
					return value;
				}
				else
					return null;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getHeader( String gridWrapper, String gridId, int colNumber ) throws Exception {
		try {
			WebElement grid = getElement(gridWrapper, gridId);
			
			if (grid != null) {
				GridElementHelper.gridId = gridId;
				List<WebElement> headers = ElementHelper.getElements(grid, "Grid_Header");
				if (headers == null || headers.size() == 0) {
					headers = ElementHelper.getElements(grid, "//tr[contains(@class,'roc-property-grid-col-header')]/td");
					String value = headers.get(colNumber-1).getText();
					return value;
				}
				else
					return null;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getHeaderColumn( String gridId, String headerName ) throws Exception {
		try {
			WebElement grid = getElement(gridId);
			
			if (grid != null) {
				GridElementHelper.gridId = gridId;
				List<WebElement> headers = ElementHelper.getElements(grid, "Grid_Header");
				if (headers == null || headers.size() == 0)
					headers = ElementHelper.getElements(grid, "//tr[contains(@class,'roc-property-grid-col-header')]/td");
				int colNo = getColumnNumber(headers, headerName);
				
				return colNo;
			}
			else
				return 0;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getHeaderColumn( String gridWrapper, String gridId, String headerName ) throws Exception {
		try {
			WebElement grid = getElement(gridWrapper, gridId);
			
			if (grid != null) {
				GridElementHelper.gridId = gridId;
				List<WebElement> headers = ElementHelper.getElements(grid, "Grid_Header");
				int colNo = getColumnNumber(headers, headerName);
				
				return colNo;
			}
			else
				return 0;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isComponentVisible() throws Exception {
		try {
			
			if (ElementHelper.isElementPresent("Editable_Grid_Component"))
				return true;
			else
				return false;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean checkIfEditableGrid(String gridId, int col) throws Exception{
		try {
			String colNum = String.valueOf(col);
			String locator = or.getProperty("Editable_Grid").replace("gridId", gridId).replace("colNum", colNum);
			
			if (ElementHelper.isElementPresent(locator))
				return false;
			else
				return true;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean checkIfEditableGrid(String gridWrapper, String gridId, int col) throws Exception {
		try {
			String colNum = String.valueOf(col);
			String wrapperLocator = LocatorHelper.getWrapperLocator(gridWrapper);
			String locator = or.getProperty("Editable_Grid").replace("gridId", gridId).replace("colNum", colNum);
			
			if (ElementHelper.isElementPresent(wrapperLocator + locator))
				return false;
			else
				return true;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void clickCell(WebElement element, boolean firstTimeClick, boolean scrolled) throws Exception {
		try {
			if (!scrolled)
				ElementHelper.scrollToView(element, true);
			
			if (firstTimeClick) {
				boolean isClickable = ElementHelper.isClickable(element);
				
				if (isClickable)
					MouseHelper.click(element, firstTimeClick);
			}
			else
				MouseHelper.click(element, firstTimeClick);
			Thread.sleep(100);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickAngularCell( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement element = getAngularCellElement( gridId, rowNum, columnNum);
			
			if (element != null) {
				GridElementHelper.gridId = gridId;
				if (rowNum > 30)
					ElementHelper.scrollToView(element, false);
				
				MouseHelper.click(element);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClickAngularCell( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement element = getAngularCellElement( gridId, rowNum, columnNum);
			
			if (element != null) {
				GridElementHelper.gridId = gridId;
				
				if (rowNum > 30)
					ElementHelper.scrollToView(element, false);
				
				MouseHelper.doubleClick(element);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickCell( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement element = getCellElement( gridId, rowNum, columnNum);
			
			if (element != null) {
				GridElementHelper.gridId = gridId;
				
				if (!ElementHelper.isClickable(element))
					ElementHelper.scrollToView(element, false);
				
				MouseHelper.click(element);
				Thread.sleep(100);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickCell( String gridWrapper, String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			WebElement element = getCellElement( gridWrapper, gridId, rowNum, columnNum);
			
			if (element != null) {
				if (!ElementHelper.isClickable(element))
					ElementHelper.scrollToView(element, false);
				
				MouseHelper.click(element);
				Thread.sleep(100);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickCell( String gridId, int rowNum, int columnNum, boolean firstTimeClick ) throws Exception {
		try {
			WebElement element = getCellElement( gridId, rowNum, columnNum);
			
			if (element != null) {
				boolean scrolled = false;
				if (rowNum > 10)
					scrolled = ElementHelper.scrollToView(element, false);
				
				clickCell(element, firstTimeClick, scrolled);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickCell( String gridWrapper, String gridId, int rowNum, int colNum, boolean firstTimeClick ) throws Exception {
		try {
			WebElement element = getCellElement( gridWrapper, gridId, rowNum, colNum);
			
			if (element != null) {
				boolean scrolled = false;
				if (rowNum > 10)
					scrolled = ElementHelper.scrollToView(element, false);

				clickCell(element, firstTimeClick, scrolled);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getRowNo(Elements gridData, String value, int colNum) throws Exception {
		try {
			int totalRows = 0;
			if (ValidationHelper.isNotEmpty(gridData)) {
				Elements rows = gridData.select( rowLocator );
				
				if (ValidationHelper.isNotEmpty(rows)) {
					int size = rows.size();
					switch (value) {
					case "LatestRow":
						totalRows = size;
						break;
						
					case "PreviousRow":
					case "ExistingRow":
						if (size > 1)
							totalRows = size - 1;
						else if (size == 1)
							totalRows = 1;
						break;

					default:
						for (int i = 0; i < rows.size(); i++) {
							Elements cols = rows.get(i).select("td");
							Element col = cols.get(colNum-1);
							
							if (col != null) {
								String text = col.text();
								text = text.replace("   ", "").replace("  ", "").replace("\u00A0", "");
								text = text.trim();
								if (text.equals(value))
									return i+1;
							}
						}
						break;
					}
				}
			}
			
			if (totalRows == 0 && ValidationHelper.isInteger(value)) {
				totalRows = Integer.parseInt(value);
				if (totalRows > 1000)
					totalRows = 0;
			}
			
			return totalRows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getRowNoContains(Elements gridData, String value, int colNum) throws Exception {
		try {
			int totalRows = 0;
			if (ValidationHelper.isNotEmpty(gridData)) {
				Elements rows = gridData.select( rowLocator );
				
				if (ValidationHelper.isNotEmpty(rows)) {
					int size = rows.size();
					switch (value) {
					case "LatestRow":
						totalRows = size;
						break;
						
					case "PreviousRow":
					case "ExistingRow":
						if (size > 1)
							totalRows = size - 1;
						else if (size == 1)
							totalRows = 1;
						break;

					default:
						for (int i = 0; i < rows.size(); i++) {
							Elements cols = rows.get(i).select("td");
							Element col = cols.get(colNum-1);
							
							if (col != null) {
								String text = col.text();
								text = text.replace("   ", "").replace("  ", "").replace("\u00A0", "");
								text = text.trim();
								if (text.equals(value) || text.contains(value))
									return i+1;
							}
						}
						break;
					}
				}
			}
			
			if (totalRows == 0 && ValidationHelper.isInteger(value))
				totalRows = Integer.parseInt(value);
			
			return totalRows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
		
	public static void doubleClickCell( String gridId, int row, int colNum ) throws Exception {
		try {
			WebElement element = getCellElement( gridId, row, colNum );
			
			if (element != null) {
				GridElementHelper.gridId = gridId;
				ElementHelper.doubleClick(element);
			}
			else
				FailureHelper.failTest("Expected row '" + row + "' not found in grid '" + gridId + "'");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClickCell( String gridWrapper, String gridId, int row, int colNum ) throws Exception {
		try {
			WebElement element = getCellElement( gridWrapper, gridId, row, colNum );
			
			if (element != null) {
				GridElementHelper.gridId = gridId;
				ElementHelper.doubleClick(element);
			}
			else
				FailureHelper.failTest("Expected row '" + row + "' not found in grid '" + gridId + "'");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getRowCount(Elements gridData) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(gridData)) {
				Elements rows = gridData.select( rowLocator );
				
				if (ValidationHelper.isNotEmpty(rows)) {
					int totalRows = rows.size();
					
					if (totalRows == 1) {
						Element row = rows.get(0);
						
						if (row != null) {
							Elements rowDiv = row.select("div");
							
							if (rowDiv != null && rowDiv.size() > 0) {
								Element column = rowDiv.get(0);
								
								if (column != null) {
									String value = column.text();
						
									if (value.equals("No Results Found."))
										return 0;
									else
										return totalRows;
								}
							}
						}
					}
					else
						return totalRows;
				}
			}
			
			return 0;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount( String gridId ) throws Exception {
		try {
			Elements gridData = getGridElement(gridId);
			int rows = getRowCount(gridData);
			
			return rows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowCount( String wrapperId, String gridId) throws Exception {
		try {
			Elements gridData = getGridElement(wrapperId, gridId);
			int rows = getRowCount(gridData);
			
			return rows;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumn ( String gridId, String columnHeader ) throws Exception {
		try {
			int col = 0;
			boolean isColInteger = ValidationHelper.isInteger(columnHeader);
			
			if (isColInteger)
				col = Integer.parseInt(columnHeader);
			else
				col = getHeaderColumn(gridId, columnHeader);
			
			return col;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumn ( String gridWrapper, String gridId, String columnHeader ) throws Exception {
		try {
			int col = 0;
			boolean isColInteger = ValidationHelper.isInteger(columnHeader);
			
			if (isColInteger)
				col = Integer.parseInt(columnHeader);
			else
				col = getHeaderColumn(gridWrapper, gridId, columnHeader);
			
			return col;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] getColumns ( String gridId, String columnHeader ) throws Exception {
		try {
			boolean isColInteger = ValidationHelper.isInteger(columnHeader);
			
			if (isColInteger) {
				int[] col = new int[1];
				col[0] = Integer.parseInt(columnHeader);
				return col;
			}
			else {
				int cols = getColumnsInGrid(gridId);
				int[] col = new int[cols];
				int count = 0;
				
				for (int i = 0; i < cols; i++) {
					String value = getHeader(gridId, (i+1)).trim();
					if (value!= null && value.equals(columnHeader))
						col[count++] = i + 1;
				}
				
				col = GenericHelper.resizeIntArray(col, count);
				return col;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int[] getColumns ( String gridWrapper, String gridId, String columnHeader ) throws Exception {
		try {
			boolean isColInteger = ValidationHelper.isInteger(columnHeader);
			
			if (isColInteger) {
				int[] col = new int[1];
				col[0] = Integer.parseInt(columnHeader);
				return col;
			}
			else {
				int cols = getColumnsInGrid(gridWrapper, gridId);
				int[] col = new int[cols];
				int count = 0;
				
				for (int i = 0; i < cols; i++) {
					String value = getHeader(gridWrapper, gridId, (i+1));
					if (value.equals(columnHeader))
						col[count++] = i + 1;
				}
				
				col = GenericHelper.resizeIntArray(col, count);
				return col;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int verifyRowNumber(String rowNum, int rows, int row) throws Exception {
		try {
			if (row == 0) {
				boolean isRowInteger = ValidationHelper.isInteger(rowNum);
				
				if (isRowInteger) {
					row = Integer.parseInt(rowNum);
					if (row > rows)
						row = 0;
				}
				else if (rowNum.equalsIgnoreCase("LatestRow"))
					row = rows;
				else if (rowNum.equalsIgnoreCase("ExistingRow") || rowNum.equalsIgnoreCase("PreviousRow")) {
					if (rows <= 1)
						row = rows;
					else
						row = rows - 1;
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRow ( String gridId, String rowNum, int colNum ) throws Exception {
		try {
			if (colNum > 10) {
				WebElement element = getCellElement(gridId, 1, colNum);
				ElementHelper.scrollToView(element, true);
			}
			
			Elements gridData = getGridElement(gridId);
			int row = getRowNo(gridData, rowNum, colNum);
			
			if (row == 0) {
				int rows = getRowCount(gridId);
				row = verifyRowNumber(rowNum, rows, row);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRow ( String gridWrapper, String gridId, String rowNum, int colNum ) throws Exception {
		try {
			if (colNum > 10) {
				WebElement element = getCellElement(gridWrapper, gridId, 1, colNum);
				ElementHelper.scrollToView(element, true);
			}
			
			Elements gridData = getGridElement(gridWrapper, gridId);
			int row = getRowNo(gridData, rowNum, colNum);
			
			if (row == 0) {
				int rows = getRowCount(gridWrapper, gridId);
				row = verifyRowNumber(rowNum, rows, row);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getRowContains ( String gridId, String rowNum, int colNum ) throws Exception {
		try {
			Elements gridData = getGridElement(gridId);
			int row = getRowNoContains(gridData, rowNum, colNum);
			
			if (row == 0) {
				int rows = getRowCount(gridId);
				row = verifyRowNumber(rowNum, rows, row);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getEmptyGridLocator(String gridId) throws Exception {
		try {
			String locator = null;
			if (gridId.contains("/")) {
				if (ElementHelper.isElementPresent(gridId))
					locator = gridId;
			}
			else {
				if (ElementHelper.isElementPresent(or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId)))
					locator = or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId);
				else if (ElementHelper.isElementPresent(or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId)))
					locator = or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId);
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getEmptyGridLocator(String gridWrapper, String gridId) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = LocatorHelper.getWrapperLocator(gridWrapper);
			
			if (wrapperLocator != null) {
				if (gridId.contains("/")) {
					if (ElementHelper.isElementPresent(wrapperLocator + gridId))
						locator = gridId;
				}
				else {
					if (ElementHelper.isElementPresent(wrapperLocator + or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId)))
						locator = wrapperLocator + or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId);
					else if (ElementHelper.isElementPresent(wrapperLocator + or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId)))
						locator = wrapperLocator + or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId);
				}
			}
			else
				locator = getEmptyGridLocator(gridId);
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getEmptyGridElement(String gridId) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(gridId);
			
			if (element == null) {
				String locator = or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId);
				element = ElementHelper.getElement(locator);
				
				if (element == null) {
					locator = or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId);
					element = ElementHelper.getElement(locator);
				}
			}
			
			if (element != null) {
				WebElement tempElement = ElementHelper.getElement(element, "Grid_Container");
				if (tempElement != null)
					element = tempElement;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getEmptyGridElement(String gridWrapper, String gridId) throws Exception {
		try {
			WebElement wrapper = ElementHelper.getElement(gridWrapper);
			WebElement element = ElementHelper.getElement(wrapper, gridId);
			
			if (element == null) {
				String locator = or.getProperty("Grid_Empty_Div_ById").replace("gridId", gridId);
				element = ElementHelper.getElement(wrapper, locator);
				
				if (element == null) {
					locator = or.getProperty("Grid_Empty_Table_ById").replace("gridId", gridId);
					element = ElementHelper.getElement(wrapper, locator);
				}
			}
			
			if (element != null) {
				WebElement tempElement = ElementHelper.getElement(element, "Grid_Container");
				if (tempElement != null)
					element = tempElement;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int getTotalColumns(Elements tableRows) throws Exception {
		try {
			int colNo = 0;
			Elements headers = tableRows.select( "th" );
			int length = headers.size(); 
			
			for (int i = 0; i < length; i++) {
				String headerValue = headers.attr("role");
				
				if (headerValue != null && headerValue.equalsIgnoreCase("button"))
					colNo++;
				else {
					headerValue = headers.attr("__gwt_header");
					
					if (headerValue != null && headerValue.startsWith("header"))
						colNo++;
				}
			}
			
			return colNo;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumnsInGrid( String gridId ) throws Exception {
		try {
			Elements tableRows = getGridElement(gridId);
			
			if (tableRows != null) {
				GridElementHelper.gridId = gridId;
				int colNo = getTotalColumns(tableRows);
				return colNo;
			}
			else
				return 0;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getColumnsInGrid( String gridWrapper, String gridId ) throws Exception {
		try {
			Elements tableRows = getGridElement(gridWrapper, gridId);
			
			if (tableRows != null) {
				GridElementHelper.gridId = gridId;
				int colNo = getTotalColumns(tableRows);
				return colNo;
			}
			else
				return 0;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getRowValues(String gridId, int rowNum ) throws Exception {
		try {
			int cols = getColumnsInGrid(gridId);
			ArrayList<String> values = new ArrayList<String>();
			
			for (int i = 1; i <= cols; i++) {
				WebElement element = getCellElement( gridId, rowNum, i);
				
				if(element != null)
					values.add(element.getText());
				else
					values.add("");
			}
			
			return values;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static ArrayList<String> getRowValues(String gridWrapper, String gridId, int rowNum ) throws Exception {
		try {
			int cols = getColumnsInGrid(gridWrapper, gridId);
			ArrayList<String> values = new ArrayList<String>();
			
			for (int i = 1; i <= cols; i++) {
				WebElement element = getCellElement( gridWrapper, gridId, rowNum, i);
				
				if(element != null)
					values.add(element.getText());
				else
					values.add("");
			}
			
			return values;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getValue(Elements gridData, int rowNum, int colNum) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(gridData)) {
				Elements table = gridData.select("table");
				
				if (ValidationHelper.isNotEmpty(table)) {
					Elements rows = table.select( rowLocator );
					
					if (ValidationHelper.isNotEmpty(rows)) {
						Element row = rows.get((rowNum-1));
						
						if (row != null) {
							Elements cols = row.select("td");
							
							if (ValidationHelper.isNotEmpty(cols)) {
								Element col = cols.get(colNum-1);
								if (col != null)
									return col.text().replace("  ", "").replace("\u00A0", ""); 
							}
						}
					}
				}
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue(String gridId, int rowNum, int colNum) throws Exception {
		try {
			Elements gridData = getGridElement(gridId);
			String value = getValue(gridData, rowNum, colNum);
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue(String gridWrapper, String gridId, int rowNum, int colNum) throws Exception {
		try {
			Elements gridData = getGridElement(gridWrapper, gridId);
			String value = getValue(gridData, rowNum, colNum);
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click( String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			if (rowNum > 0 && columnNum > 0) {
				boolean isEditableGrid = checkIfEditableGrid(gridId, columnNum);
				
				if (isEditableGrid) {
					boolean firstTimeClick = isFirstTimeClick(gridId, rowNum, columnNum);
					
					clickCell(gridId, rowNum, columnNum, firstTimeClick);
					if (!isComponentVisible())
						clickCell(gridId, rowNum, columnNum, firstTimeClick);
				}
				else
					clickCell(gridId, rowNum, columnNum);
			}
			else {
				if (rowNum == 0) {
					FailureHelper.failTest("Expected row with value '" + rowNum + "' not found in grid '" + gridId + "'");
				}
				else if (columnNum == 0) {
					FailureHelper.failTest("Expected column '" + columnNum + "' not found in grid '" + gridId + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click( String gridWrapper, String gridId, int rowNum, int columnNum ) throws Exception {
		try {
			if (rowNum > 0 && columnNum > 0) {
				boolean isEditableGrid = checkIfEditableGrid(gridWrapper, gridId, columnNum);
				
				if (isEditableGrid) {
					boolean firstTimeClick = isFirstTimeClick(gridId, rowNum, columnNum);
					
					clickCell(gridWrapper, gridId, rowNum, columnNum, firstTimeClick);
					if (!isComponentVisible())
						clickCell(gridWrapper, gridId, rowNum, columnNum, firstTimeClick);
				}
				else
					clickCell(gridWrapper, gridId, rowNum, columnNum);
			}
			else {
				if (rowNum == 0) {
					FailureHelper.failTest("Expected row with value '" + rowNum + "' not found in grid '" + gridId + "'");
				}
				else if (columnNum == 0) {
					FailureHelper.failTest("Expected column '" + columnNum + "' not found in grid '" + gridId + "'");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getImageElement(String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			WebElement element = null;
			GridElementHelper.gridId = gridId;
			int col = getColumn(gridId, columnHeader);
			
			if (col > 0) {
				element = getCellElement(gridId, rowNum, col);
				
				if (element != null) {
					element = ElementHelper.getElement(element, ".//img");
					
					return element;
				}
				else {
					FailureHelper.failTest("Expected row '" + rowNum + "' is not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' is not found in grid '" + gridId + "'");
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getImageElement(String gridWrapper, String gridId, int rowNum, String columnHeader) throws Exception {
		try {
			WebElement element = null;
			GridElementHelper.gridId = gridId;
			int col = getColumn(gridWrapper, gridId, columnHeader);
			
			if (col > 0) {
				element = getCellElement(gridWrapper, gridId, rowNum, col);
				
				if (element != null) {
					element = ElementHelper.getElement(element, "//img");
					
					return element;
				}
				else {
					FailureHelper.failTest("Expected row '" + rowNum + "' is not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' is not found in grid '" + gridId + "'");
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridId, int startRowNum, int noOfRows, int colNum ) throws Exception {
		try {
			WebElement element1 = getCellElement( gridId, startRowNum, colNum);
			
			if (element1 != null) {
				noOfRows--;
				if (noOfRows > 30)
					ElementHelper.scrollToView(element1, false);
				
				WebElement element2 = getCellElement( gridId, (startRowNum+noOfRows), colNum);
				if (element2 != null) {
					Actions action = new Actions(driver);
					action.keyDown(element1, Keys.SHIFT).build().perform();
					action.keyUp(element2, Keys.SHIFT).build().perform();
				}
				else {
					FailureHelper.failTest("Expected number of rows '" + noOfRows + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected row '" + startRowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickMultipleCells( String gridWrapper, String gridId, int startRowNum, int noOfRows, int colNum ) throws Exception {
		try {
			WebElement element1 = getCellElement( gridWrapper, gridId, startRowNum, colNum);
			
			if (element1 != null) {
				noOfRows--;
				if (noOfRows > 30)
					ElementHelper.scrollToView(element1, false);
				
				WebElement element2 = getCellElement( gridWrapper, gridId, (startRowNum+noOfRows), colNum);
				if (element2 != null) {
					Actions action = new Actions(driver);
					action.keyDown(element1, Keys.SHIFT).build().perform();
					action.keyUp(element2, Keys.SHIFT).build().perform();
				}
				else {
					FailureHelper.failTest("Expected number of rows '" + noOfRows + "' not found in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected row '" + startRowNum + "' not found in grid '" + gridId + "'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isErrorIconPresent(WebElement element) throws Exception {
		try {
			if (element == null)
				return false;
			else {
				String style = ElementHelper.getAttribute(element, "style");
				if (ValidationHelper.isNotEmpty(style) && style.contains("display: none;"))
					return false;
				else
					return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getErrorIconElement(String gridId) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = getElement(gridId);
			GridElementHelper.gridId = gridId;
			
			if (element != null) {
				element = ElementHelper.getElement(element, "Grid_Error_Icon");
			}
			else {
				FailureHelper.failTest("Grid '" + gridId + "' is not found.");
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getErrorIconElement(String gridWrapper, String gridId) throws Exception {
		try {
			WebElement element = getElement(gridWrapper, gridId);
			GridElementHelper.gridId = gridId;
			
			if (element != null) {
				element = ElementHelper.getElement(element, "Grid_Error_Icon");
			}
			else {
				FailureHelper.failTest("Grid '" + gridId + "' is not found.");
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}