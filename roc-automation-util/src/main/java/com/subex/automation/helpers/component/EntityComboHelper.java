package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.TextBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class EntityComboHelper extends ComponentHelper {
	
	public static boolean isPresent( String iconIdOrXpath ) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			return ElementHelper.isElementPresent(iconIdOrXpath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isPresent( String iconWrapperId, String iconIdOrXpath ) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			return ElementHelper.isElementPresent(iconWrapperId, iconIdOrXpath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String iconIdOrXpath, String expectedValue ) throws Exception {
		try {
			String actualValue = getValue(iconIdOrXpath);
			
			if (expectedValue.equals(actualValue))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String iconWrapperId, String iconIdOrXpath, String expectedValue ) throws Exception {
		try {
			String actualValue = getValue(iconWrapperId, iconIdOrXpath);
			
			if (expectedValue.equals(actualValue))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click Entity search icon.
	 * @param iconId - id of entity search icon.
	 * @throws Exception
	 */
	public static void clickEntityIcon(String iconIdOrXpath) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = null;
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (iconIdOrXpath.startsWith("/"))
				element = ElementHelper.getElement(iconIdOrXpath);
			else
				element = ElementHelper.getElement("trigger-" + iconIdOrXpath);
			
			if (element == null)
				element = ElementHelper.getElement(iconIdOrXpath, "EntityCombo_Icon");
			
			if (element != null) {
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Entity Icon '" + iconIdOrXpath + "' not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click Entity search icon.
	 * @param wrapperId - Div or Table id within which the entity search is present.
	 * @param iconId - id of entity search icon.
	 * @throws Exception
	 */
	public static void clickEntityIcon(String iconWrapperId, String iconIdOrXpath) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = null;
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			if (iconIdOrXpath.startsWith("/"))
				element = ElementHelper.getElement(iconWrapperId, iconIdOrXpath);
			else
				element = ElementHelper.getElement(iconWrapperId, "trigger-" + iconIdOrXpath);
			
			if (element == null)
				element = ElementHelper.getElement(iconIdOrXpath, "EntityCombo_Icon");
			
			if (element != null) {
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else {
				FailureHelper.failTest("Entity Icon '" + iconIdOrXpath + "' not found.");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String iconIdOrXpath ) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			String value = TextBoxHelper.getValue(iconIdOrXpath);
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( String iconWrapperId, String iconIdOrXpath ) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			String value = TextBoxHelper.getValue(iconWrapperId, iconIdOrXpath);
			
			return value;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void select(String iconIdOrXpath, String entityScreenTitle, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.select(entityScreenTitle, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void select(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);

				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.select(entityScreenTitle, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingSearchTextBox(String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingSearchTextBox(entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingSearchTextBox(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingSearchTextBox(entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingSearchComboBox(String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingSearchComboBox(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingSearchComboBox(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingSearchComboBox(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterTextBox(String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterTextBox(entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterTextBox(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterTextBox(entityScreenTitle, txtBoxIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterComboBox(String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterComboBox(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterComboBox(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterComboBox(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterAdvancedSearch(String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterAdvancedSearch(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectUsingGridFilterAdvancedSearch(String iconWrapperId, String iconIdOrXpath, String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
				iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				clickEntityIcon(iconWrapperId, iconIdOrXpath);
				
				EntitySearchHelper entitySearch = new EntitySearchHelper();
				entitySearch.selectUsingGridFilterAdvancedSearch(entityScreenTitle, comboIdOrXpath, value, valueColumnHeader);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( WebElement element ) throws Exception {
		try {
			String disabled = ElementHelper.getAttribute(element, "disabled");
			
			if(disabled !=null)
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( String iconIdOrXpath ) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = TextBoxElementHelper.getElement(iconIdOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Entity combo '" + iconIdOrXpath + "' is not found.");
				return false;
			}
			else {
				return isEnabled(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( String iconWrapperId, String iconIdOrXpath ) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = TextBoxElementHelper.getElement(iconWrapperId, iconIdOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Entity combo '" + iconIdOrXpath + "' is not found.");
				return false;
			}
			else {
				return isEnabled(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static boolean isDisabled( String iconIdOrXpath ) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			
			return TextBoxHelper.isDisabled(iconIdOrXpath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String iconWrapperId, String iconIdOrXpath ) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			
			return TextBoxHelper.isDisabled(iconWrapperId, iconIdOrXpath);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasValidation( String iconIdOrXpath ) throws Exception {
		try {
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = ElementHelper.getElement(iconIdOrXpath);
			
			return ElementHelper.hasValidation(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasValidation( String iconWrapperId, String iconIdOrXpath ) throws Exception {
		try {
			iconWrapperId = GenericHelper.getORProperty(iconWrapperId);
			iconIdOrXpath = GenericHelper.getORProperty(iconIdOrXpath);
			WebElement element = ElementHelper.getElement(iconWrapperId, iconIdOrXpath);
			
			return ElementHelper.hasValidation(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}