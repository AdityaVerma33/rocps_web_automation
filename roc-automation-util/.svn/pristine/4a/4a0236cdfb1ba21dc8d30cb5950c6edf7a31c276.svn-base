package com.subex.automation.helpers.component;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class SearchGridHelper extends AcceptanceTest {
	
	private static String gridId = "SearchGrid";
	private static String gridWrapper = "popupWindow";
	
	public SearchGridHelper(String gridID) throws Exception {
		try{
			gridId = gridID;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public SearchGridHelper(String gridWrapperID, String gridID) throws Exception {
		try{ 
			gridWrapper = gridWrapperID;
			gridId = gridID;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static int searchWithTextBox(String txtBoxIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent) {
					TextBoxHelper.type(gridWrapper, txtBoxIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					TextBoxHelper.type(txtBoxIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithTextBox(String txtWrapper, String txtBoxIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				TextBoxHelper.type(txtWrapper, txtBoxIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent) {
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void searchWithComboBox(String comboIdOrXpath, String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent) {
					ComboBoxHelper.select(gridWrapper, comboIdOrXpath, value);
					searchHelper.clickSearch();
				}
				else {
					ComboBoxHelper.select(comboIdOrXpath, value);
					searchHelper.clickSearch();
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithComboBox(String comboIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent) {
					ComboBoxHelper.select(gridWrapper, comboIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					ComboBoxHelper.select(comboIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithComboBox(String comboWrapper, String comboIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				ComboBoxHelper.select(comboWrapper, comboIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent) {
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithComboBoxValue(String comboIdOrXpath, String value, String columnHeader, String expectedValue) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent) {
					ComboBoxHelper.select(gridWrapper, comboIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					ComboBoxHelper.select(comboIdOrXpath, value);
					searchHelper.clickSearch();
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithComboBoxValue(String comboWrapper, String comboIdOrXpath, String value, String columnHeader, String expectedValue) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				ComboBoxHelper.select(comboWrapper, comboIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent) {
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				}
				else {
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
				}
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithAdvancedSearch(String txtBoxIdOrXpath, String value, String columnHeader) throws Exception{
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				String halfValue = value.substring(0, (value.length()/2));
				TextBoxHelper.type(txtBoxIdOrXpath, halfValue);
				Thread.sleep(500);
				List<WebElement> elements = ElementHelper.getElements("AdvancedSearch_Dropdown");
				
				if (elements != null && elements.size() > 0) {
					for (int i = 0; i < elements.size(); i++) {
						WebElement tempElement = elements.get(i);
						String innerHtml = ElementHelper.getAttribute(tempElement, "innerHTML");
						innerHtml = innerHtml.replace("<strong>", "");
						innerHtml = innerHtml.replace("</strong>", "");
						innerHtml = innerHtml.replace("\n", "");
						
						if (innerHtml.equals(value)) {
							MouseHelper.click(elements.get(i));
							break;
						}
					}
				}
				
				searchHelper.clickSearch();
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int searchWithAdvancedSearch(String txtBoxWrapper, String txtBoxIdOrXpath, String value, String columnHeader) throws Exception{
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				String LabelLocator = or.getProperty("New_GridFilter_Label").replace("value", value);
				String ckBoxLocator = or.getProperty("New_GridFilter_CheckBox");
				TextBoxHelper.type(txtBoxWrapper, txtBoxIdOrXpath, value);
				String txtBox = GenericHelper.getORProperty(txtBoxIdOrXpath);
				ElementHelper.waitForAttribute(txtBox, "class", "gwt-TextBox IsEntitySuggest popUpFieldStyle", searchScreenWaitSec);
				CheckBoxHelper.check(LabelLocator + ckBoxLocator);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterSearchWithTextBox(String txtBoxIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				TextBoxHelper.type("Grid_Filter_Panel", txtBoxIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterSearchWithTextBox(String txtBoxWrapper, String txtBoxIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				TextBoxHelper.type(txtBoxWrapper, txtBoxIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterSearchWithComboBox(String comboIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				ComboBoxHelper.select("Grid_Filter_Panel", comboIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterSearchWithComboBox(String comboWrapper, String comboIdOrXpath, String value, String columnHeader) throws Exception {
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				ComboBoxHelper.select(comboWrapper, comboIdOrXpath, value);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterAdvancedSearch(String txtBoxIdOrXpath, String value, String columnHeader) throws Exception{
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				String LabelLocator = or.getProperty("New_GridFilter_Label").replace("value", value);
				String ckBoxLocator = or.getProperty("New_GridFilter_CheckBox");
				TextBoxHelper.type("Grid_Filter_Panel", txtBoxIdOrXpath, value);
				String txtBox = GenericHelper.getORProperty(txtBoxIdOrXpath);
				ElementHelper.waitForAttribute(txtBox, "class", "gwt-TextBox IsEntitySuggest popUpFieldStyle", searchScreenWaitSec);
				CheckBoxHelper.check(LabelLocator + ckBoxLocator);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int gridFilterAdvancedSearch(String txtBoxWrapper, String txtBoxIdOrXpath, String value, String columnHeader) throws Exception{
		try {
			int row = 0;
			
			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				String LabelLocator = or.getProperty("New_GridFilter_Label").replace("value", value);
				String ckBoxLocator = or.getProperty("New_GridFilter_CheckBox");
				TextBoxHelper.type(txtBoxWrapper, txtBoxIdOrXpath, value);
				String txtBox = GenericHelper.getORProperty(txtBoxIdOrXpath);
				ElementHelper.waitForAttribute(txtBox, "class", "gwt-TextBox IsEntitySuggest popUpFieldStyle", searchScreenWaitSec);
				
				CheckBoxHelper.check(LabelLocator + ckBoxLocator);
				searchHelper.clickSearch();
				
				if (isWrapperPresent)
					row = GridHelper.getRowNumber(gridWrapper, gridId, value, columnHeader);
				else
					row = GridHelper.getRowNumber(gridId, value, columnHeader);
			}
			
			return row;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void gridFilterSearchWithCalendar(String calendarIdOrXpath, String type, String fromDate, String toDate, String columnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(type) || ValidationHelper.isNotEmpty(fromDate)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				CalendarHelper.setDate("Grid_Filter_Panel", calendarIdOrXpath, type, fromDate, toDate);
				searchHelper.clickSearch();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void gridFilterSearchWithCalendar(String calendarWrapper, String calendarIdOrXpath, String type, String fromDate, String toDate, String columnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(type) || ValidationHelper.isNotEmpty(fromDate)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
				
				if (isWrapperPresent)
					searchHelper.clickFilterIcon(gridWrapper, gridId, columnHeader);
				else
					searchHelper.clickFilterIcon(gridId, columnHeader);
				
				CalendarHelper.setDate(calendarWrapper, calendarIdOrXpath, type, fromDate, toDate);
				searchHelper.clickSearch();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setPagination(String value) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				ComboBoxHelper.select("Pagination_Combo", value);
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTotalRecordsFetched() throws Exception {
		try {
			String text = ElementHelper.getAttribute("Pagination_TotalRecordsFetched", "innerHTML");
			return text.trim();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortAscending(String columnHeader) throws Exception {
		try {
			SearchHelper searchHelper = new SearchHelper();
			boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
			
			if (isWrapperPresent)
				searchHelper.clickArrowIcon(gridWrapper, gridId, columnHeader);
			else
				searchHelper.clickArrowIcon(gridId, columnHeader);
			ButtonHelper.click("Grid_SortAscending");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void sortDescending(String columnHeader) throws Exception {
		try {
			SearchHelper searchHelper = new SearchHelper();
			boolean isWrapperPresent = ElementHelper.isElementPresent(gridWrapper);
			
			if (isWrapperPresent)
				searchHelper.clickArrowIcon(gridWrapper, gridId, columnHeader);
			else
				searchHelper.clickArrowIcon(gridId, columnHeader);
			ButtonHelper.click("Grid_SortDescending");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}