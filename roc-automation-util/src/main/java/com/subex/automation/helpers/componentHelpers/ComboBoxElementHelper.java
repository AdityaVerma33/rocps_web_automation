package com.subex.automation.helpers.componentHelpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ComboBoxElementHelper extends AcceptanceTest {
	
	private static final String targetString = "comboId";
	
	private static String[] getDropDownLocators() throws Exception {
		try {
			String[] comboLocators = {"ComboBox_Dropdown_ByTitle", "ComboBox_Dropdown_ByText"};
			
			return comboLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String idOrXpath) throws Exception {
		try {
			String locator = null;
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				if (ElementHelper.isElementPresent(or.getProperty("ComboBox_ById").replace("comboId", idOrXpath)))
					locator = or.getProperty("ComboBox_ById").replace("comboId", idOrXpath);
			}
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String wrapperId, String idOrXpath) throws Exception {
		try {
			String locator = null;
			String wrapper = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(wrapper + idOrXpath))
					locator = wrapper + idOrXpath;
			}
			else {
				if (ElementHelper.isElementPresent(wrapper + or.getProperty("ComboBox_ById").replace("comboId", idOrXpath)))
					locator = wrapper + or.getProperty("ComboBox_ById").replace("comboId", idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String idOrXpath) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);

			if (element == null) {
				String locator1 = or.getProperty("ComboBox_ById_Wrapper").replace("comboId", idOrXpath);
				String locator2 = or.getProperty("ComboBox_ById").replace("comboId", idOrXpath);
				element = ElementHelper.getElement(locator1);
				
				if (element == null)
					element = ElementHelper.getElement(locator2);
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String wrapperId, String idOrXpath) throws Exception {
		try {
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = getElement(wrapperElement, idOrXpath);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(WebElement wrapperElement, String idOrXpath) throws Exception {
		try {
			WebElement element = null;
			
			if (wrapperElement != null) {
				element = ElementHelper.getElement(wrapperElement, idOrXpath);
				
				if (element == null) {
					String locator1 = or.getProperty("ComboBox_ById_Wrapper").replace("comboId", idOrXpath);
					String locator2 = or.getProperty("ComboBox_ById").replace("comboId", idOrXpath);
					element = ElementHelper.getElement(wrapperElement, locator1);
					
					if (element == null)
						element = ElementHelper.getElement(wrapperElement, locator2);
				}
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for combo box arrow
	 * @param idOrXpath
	 * @return
	 * @throws Exception 
	 */
	public static String getArrowLocator( String idOrXpath ) throws Exception {
		try {
			String locator = getLocatorByKey(idOrXpath, "ComboBox_Arrow");
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getSelectCombo( String idOrXpath ) throws Exception {
		try {
			String locator = or.getProperty("ComboBox_BySelect").replace("idOrXpath", idOrXpath);
			WebElement element = ElementHelper.getElement(locator);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getSelectCombo( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement wrapper = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = getSelectCombo(wrapper, idOrXpath);
				
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getSelectCombo( WebElement wrapper, String idOrXpath ) throws Exception {
		try {
			WebElement element = null;
			
			if (wrapper != null) {
				element = ElementHelper.getElement(wrapper, idOrXpath);
				
				if (element == null) {
					String locator = or.getProperty("ComboBox_BySelect").replace("idOrXpath", idOrXpath);
					element = ElementHelper.getElement(wrapper, locator);
				}
			}
			else
				return null;
				
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getArrowElement( WebElement wrapperLocator ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(wrapperLocator, "ComboBox_Arrow");
			
			if (element != null) {
				if (!ElementHelper.isClickable(element))
					ElementHelper.waitForClickableElement(element, searchScreenWaitSec);
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getDropdown( String idOrXpath ) throws Exception {
		try {
			String[] locators = {"ComboBox_Dropdown"};
			WebElement element = ElementHelper.getElement(locators, targetString, idOrXpath);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getDropdown( WebElement wrapper, String idOrXpath ) throws Exception {
		try {
			String[] locators = {"ComboBox_Dropdown"};
			WebElement element = ElementHelper.getElement(locators, wrapper, targetString, idOrXpath);
	
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for combo box dropdown
	 * @param idOrXpath
	 * @return
	 */
	public static WebElement getDropdownElement( String idOrXpath, String value ) throws Exception {
		try {
			WebElement dropDown = getDropdown(idOrXpath);
			WebElement element = null;
			String[] locators = getDropDownLocators();
			
			if (dropDown != null) {
				MouseHelper.mouseOver(dropDown);
				element = ElementHelper.getElement(locators, dropDown, "value", value);
			}
			else
				element = ElementHelper.getElement(locators, "value", value);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getDropdownElement( WebElement wrapper, String idOrXpath, String value ) throws Exception {
		try {
			WebElement dropDown = getDropdown(wrapper, idOrXpath);
			WebElement element = null;
			String[] locators = getDropDownLocators();
			
			if (dropDown != null) {
				MouseHelper.mouseOver(dropDown);
				element = ElementHelper.getElement(locators, dropDown, "value", value);
			}
			else
				element = ElementHelper.getElement(locators, "value", value);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for combo box dropdown list
	 * @param idOrXpath
	 * @return
	 */
	public static List<WebElement> getDropdownListLocator( String idOrXpath ) throws Exception {
		try {
			List<WebElement> elements = null;
			
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					elements = driver.findElements(By.xpath(idOrXpath));
			}
			else {
				String locator = or.getProperty("ComboBox_Dropdown_List").replace("comboId", idOrXpath);
				if (ElementHelper.isElementPresent(locator)) {
					elements = driver.findElements(By.xpath(locator));
				}
			}
			
			return elements;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for combo box dropdown list with wrapper
	 * @param wrapperId
	 * @param idOrXpath
	 * @return
	 */
	public static List<WebElement> getDropdownListLocator( String wrapperId, String idOrXpath ) throws Exception {
		try {
			List<WebElement> elements = null;
			String wrapper = null;
			wrapper = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(wrapper + idOrXpath))
					elements = driver.findElements(By.xpath(wrapper + idOrXpath));
			}
			else {
				String xpath = or.getProperty("ComboBox_Dropdown_List").replace("comboId", idOrXpath);
				if (ElementHelper.isElementPresent(wrapper + xpath))
					elements = driver.findElements(By.xpath(wrapper + xpath));
			}
	
			return elements;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocatorByKey(String idOrXpath, String orKey) throws Exception {
		try {
			String locator = null;
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				if (ElementHelper.isElementPresent(or.getProperty(orKey).replace("comboId", idOrXpath)))
					locator = or.getProperty(orKey).replace("comboId", idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getSearchBoxElement(String idOrXpath) throws Exception {
		try {
			if (idOrXpath.contains("'gwt_uid_'"))
				idOrXpath = idOrXpath.replace("'gwt_uid_'", "'floater_gwt_uid_'");
			if (idOrXpath.startsWith("//") && !idOrXpath.contains("//input"))
				idOrXpath = idOrXpath + "//input[@type='text']";
			
			WebElement element = ElementHelper.getElement(idOrXpath);
			if (element == null)
				element = ElementHelper.getElement(or.getProperty("ComboBox_SearchBox_ById").replace("comboId", idOrXpath));
			
			boolean isClickable = ElementHelper.isClickable(element);
			if (isClickable)
				return element;
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getSearchBoxElement(WebElement wrapper, String idOrXpath) throws Exception {
		try {
			if (idOrXpath.contains("'gwt_uid_'"))
				idOrXpath = idOrXpath.replace("'gwt_uid_'", "'floater_gwt_uid_'");
			if (idOrXpath.startsWith("//") && !idOrXpath.contains("//input"))
				idOrXpath = idOrXpath + "//input[@type='text']";
			
			WebElement element = ElementHelper.getElement(wrapper, idOrXpath);
			if (element == null)
				element = ElementHelper.getElement(wrapper, or.getProperty("ComboBox_SearchBox_ById").replace("comboId", idOrXpath));
			
			boolean isClickable = ElementHelper.isClickable(element);
			if (isClickable)
				return element;
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValueLocator( String wrapperLocator ) throws Exception {
		try {
			String locator = LocatorHelper.getLocator(wrapperLocator) + or.getProperty("ComboBox_Value");
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for combo box value box
	 * @param idOrXpath
	 * @return
	 */
	public static String getValueLocator( WebElement wrapperElement ) throws Exception {
		try {
			String locator = LocatorHelper.getLocator(wrapperElement);
			if (!locator.endsWith("span"))
				locator = locator + or.getProperty("ComboBox_Value");
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getValueElement( WebElement wrapperElement ) throws Exception {
		try {
			WebElement element = wrapperElement;
			
			if (!wrapperElement.getTagName().equals("span"))
				element = ElementHelper.getElement(wrapperElement, "ComboBox_Value");
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getSelectValue(WebElement wrapper) throws Exception {
		try {
			String value = null;
			String optionsLocator1 = or.getProperty("ComboBox_Dropdown_Value").replace("dropDownValue", value);
			String optionsLocator2 = or.getProperty("ComboBox_Dropdown_StartsValue").replace("dropDownValue", value);
			String[] valueLocators = {optionsLocator1, optionsLocator2};
			
			for (int i = 0; i < valueLocators.length; i++) {
				if (ElementHelper.isElementPresent(wrapper, valueLocators[i])) {
					value = ElementHelper.getAttribute(wrapper, valueLocators[i], "value");
					break;
				}
			}
			
			if (value == null) {
				String optionsLocator3 = or.getProperty("ComboBox_Dropdown_Text").replace("dropDownValue", value);
				String optionsLocator4 = or.getProperty("ComboBox_Dropdown_StartsText").replace("dropDownValue", value);
				String[] textLocators = {optionsLocator3, optionsLocator4};
				
				for (int i = 0; i < textLocators.length; i++) {
					if (ElementHelper.isElementPresent(wrapper, valueLocators[i])) {
						value = ElementHelper.getText(wrapper, valueLocators[i]);
						break;
					}
				}
			}
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( WebElement comboWrapper ) throws Exception {
		try {
			WebElement comboElement = getValueElement(comboWrapper);
			return ElementHelper.getText(comboElement);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String idOrXpath ) throws Exception {
		try {
			WebElement comboWrapper = getElement(idOrXpath);
			
			if (comboWrapper != null) {
//				String locator = getValueLocator(comboWrapper);
				return ElementHelper.getText(comboWrapper);
			}
			else {
				comboWrapper = getSelectCombo(idOrXpath);
				
				if (comboWrapper != null) {
					String value = getSelectValue(comboWrapper);
					return value;
				}
				else
					return null;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			WebElement comboWrapper = ElementHelper.getWrapperElement(comboBoxWrapper);
			return getValue(comboWrapper, idOrXpath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( WebElement comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			WebElement comboWrapper = getElement(comboBoxWrapper, idOrXpath);
			
			if (comboWrapper != null) {
				String locator = getValueLocator(comboWrapper);
				return LabelHelper.getText(locator);
			}
			else {
				comboWrapper = getSelectCombo(comboBoxWrapper, idOrXpath);
				
				if (comboWrapper != null) {
					String value = getSelectValue(comboWrapper);
					return value;
				}
				else
					return null;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getAllValues( WebElement comboWrapper, String idOrXpath ) throws Exception {
		try {
			WebElement arrowElement = getArrowElement(comboWrapper);
			boolean dropDownAppeared = false;
			int tryCount = 1;
			
			while (!dropDownAppeared && tryCount <= 3) {
				MouseHelper.click(arrowElement);
				List<WebElement> ele = getDropdownListLocator(idOrXpath);
				
				if (ele == null)
					dropDownAppeared = false;
				else {
					dropDownAppeared = true;
					
					String[] values = new String[ele.size()];
					for (int i = 0; i < ele.size(); i++) {
						values[i] = ele.get(i).getText();
					}
					
					arrowElement = getArrowElement(comboWrapper);
					MouseHelper.click(arrowElement);
					return values;
				}
				
				if (dropDownAppeared)
					tryCount = 3;
				else
					tryCount++;
			}
			
			return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getAllValues( WebElement comboWrapper, String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			WebElement arrowElement = getArrowElement(comboWrapper);
			boolean dropDownAppeared = false;
			int tryCount = 1;
			
			while (!dropDownAppeared && tryCount <= 3) {
				MouseHelper.click(arrowElement);
				List<WebElement> ele = getDropdownListLocator(comboBoxWrapper, idOrXpath);
				
				if (ele == null)
					dropDownAppeared = false;
				else {
					dropDownAppeared = true;
					
					String[] values = new String[ele.size()];
					for (int i = 0; i < ele.size(); i++) {
						values[i] = ele.get(i).getText();
					}
					
					arrowElement = getArrowElement(comboWrapper);
					MouseHelper.click(arrowElement);
					return values;
				}
				
				if (dropDownAppeared)
					tryCount = 3;
				else
					tryCount++;
			}
			
			return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInSearchBox(String searchBoxIdOrXPath, String value) throws Exception {
		try {
			if (searchBoxIdOrXPath != null) {
				WebElement searchBoxElement = getSearchBoxElement(searchBoxIdOrXPath);
				
				if (searchBoxElement != null) {
					String temp = value;
					
					if (value.contains("(")) {
						String[] dummy = value.split("\\(");
						temp = dummy[0];
					}
					else {
						int length = value.length();
						if (length > 9)
							temp = value.substring(0, (length-1));
					}
					
					ElementHelper.waitForClickableElement(searchBoxElement, detailScreenWaitSec);
					TextBoxElementHelper.typeInBox(searchBoxElement, searchBoxIdOrXPath, temp);
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInSearchBox(WebElement comboBoxWrapper, String searchBoxIdOrXPath, String value) throws Exception {
		try {
			if (comboBoxWrapper != null) {
				if (searchBoxIdOrXPath != null) {
					WebElement searchBoxElement = getSearchBoxElement(comboBoxWrapper, searchBoxIdOrXPath);
					
					if (searchBoxElement != null) {
						String temp = value;
						
						if (value.contains("(")) {
							String[] dummy = value.split("\\(");
							temp = dummy[0];
						}
						else {
							int length = value.length();
							if (length > 9)
								temp = value.substring(0, (length-1));
						}
						
						ElementHelper.waitForClickableElement(searchBoxElement, detailScreenWaitSec);
						TextBoxElementHelper.typeInBox(searchBoxElement, searchBoxIdOrXPath, temp);
					}
				}
			}
			else
				typeInSearchBox(searchBoxIdOrXPath, value);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInSearchBoxTruncated(String searchBoxIdOrXPath, String value, String truncatedValue) throws Exception {
		try {
			if (searchBoxIdOrXPath != null) {
				WebElement searchBoxElement = getSearchBoxElement(searchBoxIdOrXPath);
				
				if (searchBoxElement != null) {
					String temp = value;
					if (!value.equals(truncatedValue))
						temp = truncatedValue.replace("...", "");
					
					if (value.contains("(")) {
						String[] dummy = value.split("\\(");
						temp = dummy[0];
					}
					
					ElementHelper.waitForClickableElement(searchBoxElement, detailScreenWaitSec);
					TextBoxElementHelper.typeInBox(searchBoxElement, searchBoxIdOrXPath, temp);
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInSearchBoxTruncated(WebElement comboBoxWrapper, String searchBoxIdOrXPath, String value, String truncatedValue) throws Exception {
		try {
			if (comboBoxWrapper != null) {
				if (searchBoxIdOrXPath != null) {
					WebElement searchBoxElement = getSearchBoxElement(comboBoxWrapper, searchBoxIdOrXPath);
					
					if (searchBoxElement != null) {
						String temp = value;
						if (!value.equals(truncatedValue))
							temp = truncatedValue.replace("...", "");
						
						if (value.contains("(")) {
							String[] dummy = value.split("\\(");
							temp = dummy[0];
						}
						
						ElementHelper.waitForClickableElement(searchBoxElement, detailScreenWaitSec);
						TextBoxElementHelper.typeInBox(searchBoxElement, searchBoxIdOrXPath, temp);
					}
				}
			}
			else
				typeInSearchBoxTruncated(searchBoxIdOrXPath, value, truncatedValue);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean isDropDownOpen(WebElement comboWrapper) throws Exception {
		try {
			String classAttribute = ElementHelper.getAttribute(comboWrapper, "class");
			
			if (classAttribute.endsWith("CK") || classAttribute.endsWith("MK") || classAttribute.endsWith("HL") || classAttribute.contains("IK"))
				return true;
			else
				return false;
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String selectValue(WebElement comboWrapper, String idOrXpath, WebElement arrowElement, String valueLocator, String value) throws Exception {
		try {
			int tryCount = 1;
			boolean retry = true;
			WebElement dropDown = null;
			String currentValue = getValue(comboWrapper);
			
			while (retry && tryCount <= 3) {
				MouseHelper.click(arrowElement);
				typeInSearchBox(idOrXpath, value);
				dropDown = getDropdownElement(idOrXpath, value);
				
				if (dropDown != null) {
					if (!isDropDownOpen(comboWrapper)) {
						MouseHelper.click(arrowElement);
						typeInSearchBox(idOrXpath, value);
					}
					
					ElementHelper.actionClick(dropDown);
				}
				
				if (ElementHelper.isElementPresent(valueLocator)) {
					comboWrapper = getElement(idOrXpath);
					currentValue = getValue(comboWrapper);
					
					if (isValueSelected(value, currentValue)) {
						tryCount = 3;
						retry = false;
					}
					else
						tryCount++;
				}
				else {
					currentValue =value;
					tryCount = 3;
					retry = false;
				}
			}
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String selectValue(WebElement comboWrapper, WebElement comboBoxWrapper, String idOrXpath, WebElement arrowElement, String valueLocator, String value) throws Exception {
		try {
			String currentValue = null;
			
			if (comboBoxWrapper == null) {
				currentValue = selectValue(comboWrapper, idOrXpath, arrowElement, valueLocator, value);
			}
			else {
				int tryCount = 1;
				boolean retry = true;
				WebElement dropDown = null;
				currentValue = getValue(comboBoxWrapper, idOrXpath);
				
				while (retry && tryCount <= 3) {
					MouseHelper.click(arrowElement);
					typeInSearchBox(comboBoxWrapper, idOrXpath, value);
					dropDown = getDropdownElement(comboBoxWrapper, idOrXpath, value);
					
					if (dropDown != null) {
						if (!isDropDownOpen(comboWrapper)) {
							MouseHelper.click(arrowElement);
							typeInSearchBox(comboBoxWrapper, idOrXpath, value);
						}
						
						ElementHelper.actionClick(dropDown);
					}
					
					if (ElementHelper.isElementPresent(valueLocator)) {
						currentValue = getValue(comboBoxWrapper, idOrXpath);
						
						if (isValueSelected(value, currentValue)) {
							tryCount = 3;
							retry = false;
						}
						else
							tryCount++;
					}
					else {
						currentValue = value;
						tryCount = 3;
						retry = false;
					}
				}
			}
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String selectTruncatedValue(WebElement comboWrapper, String idOrXpath, WebElement arrowElement, String valueLocator, String value, String truncatedValue) throws Exception {
		try {
			int tryCount = 1;
			boolean retry = true;
			WebElement dropDown = null;
			String currentValue = getValue(comboWrapper);
			
			while (retry && tryCount <= 3) {
				MouseHelper.click(arrowElement);
				typeInSearchBoxTruncated(idOrXpath, value, truncatedValue);
				dropDown = getDropdownElement(idOrXpath, value);
				if (dropDown == null)
					dropDown = getDropdownElement(idOrXpath, truncatedValue);
				
				if (dropDown != null) {
					if (!isDropDownOpen(comboWrapper)) {
						MouseHelper.click(arrowElement);
						typeInSearchBoxTruncated(idOrXpath, value, truncatedValue);
					}
					
					ElementHelper.actionClick(dropDown);
				}
				
				if (ElementHelper.isElementPresent(valueLocator)) {
					comboWrapper = getElement(idOrXpath);
					currentValue = getValue(comboWrapper);
					
					if (isValueSelected(value, truncatedValue, currentValue)) {
						tryCount = 3;
						retry = false;
					}
					else
						tryCount++;
				}
				else {
					tryCount = 3;
					retry = false;
				}
			}
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String selectTruncatedValue(WebElement comboWrapper, WebElement comboBoxWrapper, String idOrXpath, WebElement arrowElement, String valueLocator, String value, String truncatedValue) throws Exception {
		try {
			int tryCount = 1;
			boolean retry = true;
			WebElement dropDown = null;
			String currentValue = getValue(comboWrapper);
			
			while (retry && tryCount <= 3) {
				MouseHelper.click(arrowElement);
				typeInSearchBoxTruncated(comboBoxWrapper, idOrXpath, value, truncatedValue);
				dropDown = getDropdownElement(comboBoxWrapper, idOrXpath, value);
				if (dropDown == null)
					dropDown = getDropdownElement(comboBoxWrapper, idOrXpath, truncatedValue);
				
				if (dropDown != null) {
					if (!isDropDownOpen(comboWrapper)) {
						MouseHelper.click(arrowElement);
						typeInSearchBoxTruncated(comboBoxWrapper, idOrXpath, value, truncatedValue);
					}
					
					ElementHelper.actionClick(dropDown);
				}
				
				if (ElementHelper.isElementPresent(valueLocator)) {
					currentValue = getValue(comboWrapper);
					
					if (isValueSelected(value, truncatedValue, currentValue)) {
						tryCount = 3;
						retry = false;
					}
					else
						tryCount++;
				}
				else {
					tryCount = 3;
					retry = false;
				}
			}
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValueSelected(String value, String currentValue) throws Exception {
		try {
			if (currentValue.equals(value.trim()) || currentValue.startsWith(value))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValueSelected(String value, String truncatedValue, String currentValue) throws Exception {
		try {
			if (currentValue.equals(value.trim()))
				return true;
			else if (currentValue.equals(truncatedValue))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled(WebElement element) throws Exception {
		try {
			if (element.getAttribute("disabled") != null)
				return false;
			else if (element.getAttribute("readonly") != null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean enabled(String idOrXpath) throws Exception {
		try {
			String locator = getLocator(idOrXpath);
			if (locator != null) {
				if (ElementHelper.isElementPresent(locator + or.getProperty("ComboBox_Sibling")))
					locator = locator + or.getProperty("ComboBox_Sibling");
				
				WebElement element = ElementHelper.getElement(locator);
				return isEnabled(element);
			}
			else {
				FailureHelper.failTest("Combo Box '" + idOrXpath + "' is not found.");
			}
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean enabled(String comboBoxWrapper, String idOrXpath) throws Exception {
		try {
			String locator = getLocator(comboBoxWrapper, idOrXpath);
			if (locator != null) {
				if (ElementHelper.isElementPresent(locator + or.getProperty("ComboBox_Sibling")))
					locator = locator + or.getProperty("ComboBox_Sibling");
				
				WebElement element = ElementHelper.getElement(locator);
				return isEnabled(element);
			}
			else {
				FailureHelper.failTest("Combo Box '" + idOrXpath + "' is not found.");
			}
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static boolean select(String locator, String value) throws Exception {
		try {
			Select sel = new Select(driver.findElement(By.xpath(locator)));
			boolean isSelected = false;
			
			String optionsLocator1 = or.getProperty("ComboBox_Dropdown_Value").replace("dropDownValue", value);
			String[] valueLocators = {optionsLocator1};
			
			for (int i = 0; i < valueLocators.length; i++) {
				if (ElementHelper.isElementPresent(valueLocators[i])) {
					String tagValue = ElementHelper.getAttribute(valueLocators[i], "value");
					sel.selectByValue(tagValue);
					isSelected = true;
					break;
				}
			}
			
			if (!isSelected) {
				String optionsLocator2 = or.getProperty("ComboBox_Dropdown_Text").replace("dropDownValue", value);
				String[] textLocators = {optionsLocator2};
				
				for (int i = 0; i < textLocators.length; i++) {
					if (ElementHelper.isElementPresent(valueLocators[i])) {
						String tagText = LabelHelper.getText(valueLocators[i]);
						sel.selectByVisibleText(tagText);
						isSelected = true;
						break;
					}
				}
			}
			
			return isSelected;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectForSelectTag(String idOrXpath, String value) throws Exception {
		try {
			String locator = or.getProperty("ComboBox_BySelect").replace("idOrXpath", idOrXpath);
			boolean isSelected = select(locator, value);

			if (!isSelected)
				FailureHelper.failTest("Value '" + value + "' does not exists in ComboBox '" + idOrXpath + "'");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectForSelectTag(String comboBoxWrapper, String idOrXpath, String value) throws Exception {
		try {
			String locator = or.getProperty("ComboBox_BySelect").replace("idOrXpath", idOrXpath);
			String wrapperLocator = LocatorHelper.getWrapperLocator(comboBoxWrapper);
			if (wrapperLocator != null)
				locator = wrapperLocator + locator;
			boolean isSelected = select(locator, value);

			if (!isSelected)
				FailureHelper.failTest("Value '" + value + "' does not exists in ComboBox '" + idOrXpath + "'");
			
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void select(WebElement element, String value) throws Exception {
		try {
			if (element != null) {
				Select select = new Select(element);
				List<WebElement> options = select.getOptions();
				boolean optionSelected = false;
				
				for (int i = 0; i < options.size(); i++) {
					String actualValue = options.get(i).getText();
					
					if (value.equals(actualValue)) {
						select.selectByVisibleText(value);
						optionSelected = true;
						break;
					}
				}
				
				if (!optionSelected)
					FailureHelper.failTest("Value '" + value + "' is not found in Combo Box");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}