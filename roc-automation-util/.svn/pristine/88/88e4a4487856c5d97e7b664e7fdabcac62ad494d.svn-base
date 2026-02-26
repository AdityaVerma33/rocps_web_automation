package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.JSoupHelper;

public class PropertyGridElementHelper extends AcceptanceTest {
	
	final private static String textBoxKey = "PropertyGrid_TextBox";
	final private static String comboBoxKey1 = "PropertyGrid_Dropdown_1";
	final private static String comboBoxKey2 = "PropertyGrid_Dropdown_2";
	final private static String checkBoxKey = "PropertyGrid_CheckBox";
	final private static String datePickerKey = "PropertyGrid_Date";
	final private static String dataDirKey = "PropertyGrid_DataDir_Icon";
	
	public static String getLocator(String gridId, int row) throws Exception {
		try {
			String locator = null;
			String gridLocatorByTable = or.getProperty("PropertyGrid_RowCol_ByTable").replace("gridId", gridId).replace("row", String.valueOf(row));
			String gridLocatorByDiv = or.getProperty("PropertyGrid_RowCol_ByDiv").replace("gridId", gridId).replace("row", String.valueOf(row));
			
			if (ElementHelper.isElementPresent(gridLocatorByTable))
				locator = gridLocatorByTable;
			else if (ElementHelper.isElementPresent(gridLocatorByDiv))
				locator = gridLocatorByDiv;
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String wrapperId, String gridId, int row) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = GridElementHelper.getLocator(wrapperId);
			
			if (wrapperLocator != null) {
				String gridLocatorByTable = or.getProperty("PropertyGrid_RowCol_ByTable").replace("gridId", gridId).replace("row", String.valueOf(row));
				String gridLocatorByDiv = or.getProperty("PropertyGrid_RowCol_ByDiv").replace("gridId", gridId).replace("row", String.valueOf(row));
				
				if (ElementHelper.isElementPresent(wrapperLocator + gridLocatorByTable))
					locator = wrapperLocator + gridLocatorByTable;
				else if (ElementHelper.isElementPresent(wrapperLocator + gridLocatorByDiv))
					locator = wrapperLocator + gridLocatorByDiv;
			}
			else
				locator = getLocator(gridId, row);
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getPropertNames(String propertyName, boolean useWrapper) throws Exception {
		try {
			String[] propertyNames = null;
			JSoupHelper jSoup = new JSoupHelper();
			
			if (useWrapper) {
    			propertyNames = jSoup.getTexts("#FORM, #window-scroll-panel", "#property, #expressionGrid", "div[class*=roc-property-label]");
			}
    		else {
    			propertyNames = jSoup.getTexts("#property, #expressionGrid", "div[class*=roc-property-label]");
    		}
			
			int index = StringHelper.searchArray(propertyNames, propertyName);
			return index;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getPropertNames(String propertyName, boolean useWrapper, String wrapperId) throws Exception {
		try {
			String[] propertyNames = null;
			JSoupHelper jSoup = new JSoupHelper();
			
			if (useWrapper) {
				if (wrapperId.startsWith("//"))
					wrapperId = "FORM, #window-scroll-panel";
				
				propertyNames = jSoup.getTexts("#" + wrapperId, "#property, #expressionGrid", "div[class*=roc-property-label]");
			}
    		else
    			propertyNames = jSoup.getTexts("#property, #expressionGrid", "div[class*=roc-property-label]");
			
			int index = StringHelper.searchArray(propertyNames, propertyName);
			return index;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComponentLocator(String orKey, String propertyName, boolean useWrapper) throws Exception {
		try {
			String locator = null;
			String compLocator = or.getProperty(orKey).replace("property", propertyName);
			
			if (useWrapper) {
				String wrapper = or.getProperty("Dialog_Wrapper");
				
				if (ElementHelper.isElementPresent(wrapper + compLocator)) {
					locator = wrapper + compLocator;
				}
			}
			else {
				if (ElementHelper.isElementPresent(compLocator)) {
					locator = compLocator;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComponentLocator(String orKey, String propertyName, String wrapperId) throws Exception {
		try {
			String locator = null;
			String compLocator = or.getProperty(orKey).replace("property", propertyName);
			
			if (ValidationHelper.isNotEmpty(wrapperId)) {
				if (wrapperId.startsWith("/")) {
					if (ElementHelper.isElementPresent(wrapperId + compLocator)) {
						locator = wrapperId + compLocator;
					}
				}
				else {
					WebElement element = ElementHelper.getWrapperElement(wrapperId);
					
					if (element != null) {
						wrapperId = LocatorHelper.getLocator(element);
						
						if (ElementHelper.isElementPresent(wrapperId + compLocator)) {
							locator = wrapperId + compLocator;
						}
					}
				}
			}
			else {
				if (ElementHelper.isElementPresent(compLocator)) {
					locator = compLocator;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComponentLocator(String[] orKeys, String propertyName, boolean useWrapper) throws Exception {
		try {
			String locator = null;
			if (useWrapper) {
				String wrapper = or.getProperty("Dialog_Wrapper");
				for (int i = 0; i < orKeys.length; i++) {
					if (ElementHelper.isElementPresent(wrapper + or.getProperty(orKeys[i]).replace("property", propertyName))) {
						locator = wrapper + or.getProperty(orKeys[i]).replace("property", propertyName);
						break;
					}
				}
			}
			else {
				for (int i = 0; i < orKeys.length; i++) {
					if (ElementHelper.isElementPresent(or.getProperty(orKeys[i]).replace("property", propertyName))) {
						locator = or.getProperty(orKeys[i]).replace("property", propertyName);
						break;
					}
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComponentLocator(String[] orKeys, String propertyName, String wrapperId) throws Exception {
		try {
			String locator = null;
			
			if (ValidationHelper.isNotEmpty(wrapperId)) {
				for (int i = 0; i < orKeys.length; i++) {
					if (ElementHelper.isElementPresent(wrapperId + or.getProperty(orKeys[i]).replace("property", propertyName))) {
						locator = wrapperId + or.getProperty(orKeys[i]).replace("property", propertyName);
						break;
					}
				}
			}
			else {
				for (int i = 0; i < orKeys.length; i++) {
					if (ElementHelper.isElementPresent(or.getProperty(orKeys[i]).replace("property", propertyName))) {
						locator = or.getProperty(orKeys[i]).replace("property", propertyName);
						break;
					}
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getWrapperElement() throws Exception {
		try {
			String wrapper = or.getProperty("Dialog_Wrapper");
			WebElement element = ElementHelper.getElement(wrapper);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getWrapperElement(String wrapperId) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(wrapperId);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getComponentElement(WebElement wrapperElement, String orKey, String propertyName) throws Exception {
		try {
			WebElement element = null;
			String propLocator = or.getProperty(orKey).replace("property", propertyName);
			
			if (wrapperElement != null)
				element = ElementHelper.getElement(wrapperElement, propLocator);
			else
				element = ElementHelper.getElement(propLocator);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getComponentElement(String[] orKeys, String propertyName, boolean useWrapper) throws Exception {
		try {
			WebElement element = null;
			
			if (useWrapper) {
				String wrapper = or.getProperty("Dialog_Wrapper");
				for (int i = 0; i < orKeys.length; i++) {
					String propLocator = or.getProperty(orKeys[i]).replace("property", propertyName);
					element = ElementHelper.getElement(wrapper + propLocator);
					if (element != null)
						break;
				}
			}
			else {
				for (int i = 0; i < orKeys.length; i++) {
					String propLocator = or.getProperty(orKeys[i]).replace("property", propertyName);
					element = ElementHelper.getElement(propLocator);
					if (element != null)
						break;
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getComponentElement(WebElement wrapperElement, String[] orKeys, String propertyName) throws Exception {
		try {
			WebElement element = null;
			
			for (int i = 0; i < orKeys.length; i++) {
				String propLocator = or.getProperty(orKeys[i]).replace("property", propertyName);
				if (wrapperElement != null)
					element = ElementHelper.getElement(wrapperElement, propLocator);
				else
					element = ElementHelper.getElement(propLocator);
				if (element != null)
					break;
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickCell( String locator, int rowNum ) throws Exception {
		try {
			if (locator != null) {
				if (LabelHelper.getText(locator).equals("")) {
					int lastIndex = locator.lastIndexOf("//");
					locator = locator.substring(0, lastIndex);
				}
				MouseHelper.click(locator);
			}
			else {
				FailureHelper.failTest("Expected row '" + rowNum + "' not found in grid 'property'");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickProperty( WebElement wrapperElement, String propertyName, int index ) throws Exception {
    	try {
    		if (index >= 0) {
				String locator = or.getProperty("PropertyGrid_Property").replace("property", propertyName);
				WebElement element = null;
				if (wrapperElement == null)
					element = ElementHelper.getElement(locator);
				else
					element = ElementHelper.getElement(wrapperElement, locator);
				
				if (element != null) {
					ElementHelper.click(element);
				}
				else {
					FailureHelper.failTest("Property '" + propertyName + "' not found.");
				}
			}
			else {
				FailureHelper.failTest("Property '" + propertyName + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void scrollDown(String locator, boolean fullScroll) throws Exception {
		try {
			PropertyGridElementHelper.clickCell(locator, 1);
			WebElement element = ElementHelper.getElement(locator);
			
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
	
	public static void scrollOneLevelDownPropertyGrid(String locator, int numberOfMoveDown) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(locator);
			
			for (int i = 0; i < numberOfMoveDown; i++) {
				ElementHelper.click(element);
				ElementHelper.pressKey(element, Keys.ARROW_DOWN);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getComponent( WebElement wrapperElement, String propertyName ) throws Exception {
		try {
			String component = null;
			WebElement element = PropertyGridElementHelper.getComponentElement(wrapperElement, textBoxKey, propertyName);
						
			if (element != null)
				component = "textbox";
			else {
				element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey1, propertyName);
				if (element != null)
					component = "combobox";
				else {
					element = PropertyGridElementHelper.getComponentElement(wrapperElement, comboBoxKey2, propertyName);
					if (element != null)
						component = "combobox";
					else {
						element = PropertyGridElementHelper.getComponentElement(wrapperElement, checkBoxKey, propertyName);
						if (element != null)
							component = "checkbox";
						else {
							element = PropertyGridElementHelper.getComponentElement(wrapperElement, datePickerKey, propertyName);
							if (element != null)
								component = "datepicker";
							else {
								element = PropertyGridElementHelper.getComponentElement(wrapperElement, dataDirKey, propertyName);
								if (element != null)
									component = "datadir";
								else {
									FailureHelper.failTest("Property '" + propertyName + "' not found.");
								}
							}
						}
					}
				}
			}
			
			return component;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}