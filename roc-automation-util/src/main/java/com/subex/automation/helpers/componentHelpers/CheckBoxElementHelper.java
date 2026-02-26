package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CheckBoxElementHelper extends AcceptanceTest {
	
	private static final String targetString = "checkBoxId";
	
	private static String[] getLocators() throws Exception {
		try {
			String[] checkBoxLocators = {"CheckBox_ById", "CheckBox_Img_ById", "Node_ByDiv"};
			
			return checkBoxLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for checkbox
	 * @param idOrXpath
	 * @return
	 */
	public static String getLocator( String idOrXpath ) throws Exception {
		try {
			String locator = null;
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				String[] locators = getLocators();
				locator = LocatorHelper.getLocator(locators, targetString, idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for checkbox with wrapper
	 * @param wrapperId
	 * @param idOrXpath
	 * @return
	 * @throws Exception 
	 */
	public static String getLocator( String wrapperId, String idOrXpath ) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (wrapperLocator != null) {
				if (idOrXpath.startsWith("/")) {
					if (ElementHelper.isElementPresent(wrapperLocator + idOrXpath))
						locator = wrapperLocator + idOrXpath;
				}
				else {
					String[] locators = getLocators();
					locator = LocatorHelper.getLocator(locators, wrapperLocator, targetString, idOrXpath);
				}
			}
			else {
				locator = getLocator(idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				if (!idOrXpath.startsWith("/")) {
					String[] locators = getLocators();
					element = ElementHelper.getElement(locators, targetString, idOrXpath);
				}
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement wrapper = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapper != null) {
				element = ElementHelper.getElement(wrapper, idOrXpath);
				
				if (element == null) {
					String[] locators = getLocators();
					element = ElementHelper.getElement(locators, wrapper, targetString, idOrXpath);
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
	 * return value for input type check box
	 */
	public static boolean getValue( WebElement element ) throws Exception {
		try {
			String isChecked = ElementHelper.getAttribute(element, "checked");
			String cellChecked = ElementHelper.getAttribute(element, "src");
				
			if(isChecked != null || (cellChecked != null && cellChecked.contains("cellchecked")))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean enabled(WebElement element) throws Exception {
		try {
			if (ElementHelper.getAttribute(element, "disabled") != null)
				return false;
			else if (ElementHelper.getAttribute(element, "readonly") != null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * return value for img type check box.
	 * @throws Exception 
	 */
	public static boolean checked(String wrapperId, String checkboxLocator ) throws Exception {
		try {
			WebElement element = getElement(wrapperId, checkboxLocator);
			
			if (element != null) {
				String attribute = ElementHelper.getAttribute(element, "checked");
				if(ValidationHelper.isNotEmpty(attribute))
					return true;
				else {
					attribute = ElementHelper.getAttribute(element, "class");
					if(attribute != null && attribute.contains("x-grid3-check-col-on"))
						return true;
					else {
						attribute = ElementHelper.getAttribute(element, "src");
						if(attribute != null && attribute.contains("unchecked"))
							return true;
						else
							return false;
					}
				}
			}
			else {
				FailureHelper.failTest("Check Box '" + checkboxLocator + "' is not found.");
				return false;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void check( WebElement element ) throws Exception {
		try {
			boolean isChecked = CheckBoxElementHelper.getValue(element);
			if (!isChecked)
				MouseHelper.click(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void uncheck( WebElement element ) throws Exception {
		try {
			boolean isChecked = CheckBoxElementHelper.getValue(element);
			if (isChecked)
				MouseHelper.click(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}