package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.ButtonElementHelper;
import com.subex.automation.helpers.componentHelpers.LocatorHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ButtonHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Button is present in GUI.
	 * If Button is not present, test case will fail.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static boolean isPresent( String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(idOrXpath);
			
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
	 * This method is used to check if the Button is present in GUI.
	 * If Button is not present, test case will fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @throws Exception
	 */
	public static boolean isPresent( String buttonWrapper, String idOrXpath) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(buttonWrapper, idOrXpath);
			
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
	 * This method is used to check if the Button is enabled or not.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				String disabledAttribute = ElementHelper.getAttribute(element, "disabled");
				String classAttribute = ElementHelper.getAttribute(element, "class");
				if(disabledAttribute != null || (ValidationHelper.isNotEmpty(classAttribute) && classAttribute.contains("disabled")))
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
	
	/**
	 * This method is used to check if the Button is enabled or not.
	 * If not enabled, test case will fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static boolean isEnabled(String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(buttonWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				if(ElementHelper.getAttribute(element, "disabled")==null)
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
	
	public static boolean isDisabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled(String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(buttonWrapper, idOrXpath);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Button has the specified text.
	 * If not, test case will fail.
	 * @param idOrXpath - id of the button.
	 * @param expectedText - expected text on the button
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isTextPresent( String idOrXpath, String expectedText ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ButtonElementHelper.getText(idOrXpath);
			
			if (value == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return false;
			}
			else if (!value.equals(expectedText))
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
	 * This method is used to check if the Button has the specified text.
	 * If not, test case will fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @param expectedText - expected text on the button
	 * @return 
	 * @throws Exception
	 */
	public static boolean isTextPresent( String buttonWrapper, String idOrXpath, String expectedText ) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ButtonElementHelper.getText(buttonWrapper, idOrXpath);
			
			if (value == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return false;
			}
			else if (!value.equals(expectedText))
				return false;
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getText( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ButtonElementHelper.getText(idOrXpath);
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Button has the specified text.
	 * If not, test case will fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @param expectedText - expected text on the button
	 * @return 
	 * @throws Exception
	 */
	public static String getText( String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ButtonElementHelper.getText(buttonWrapper, idOrXpath);
			
			return value;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	

	/**
	 * This method is used to click the Button
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void click( String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
			}
			else {
				String locator = LocatorHelper.getLocator(element);
				if (ElementHelper.isElementPresent("Toolbar", locator))
					ElementHelper.scrollToView(element, false);
				MouseHelper.click(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click the Button
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void click( String buttonWrapper, String idOrXpath) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(buttonWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
			}
			else {
				String locator = LocatorHelper.getLocator(element);
				if (ElementHelper.isElementPresent("Toolbar", locator))
					ElementHelper.scrollToView(element, true);
				MouseHelper.click(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click the Button if button is present in GUI.
	 * If not present, script will skip click and AT will not fail.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void clickIfPresent( String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(idOrXpath);
			
			if (element != null)
				MouseHelper.click(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click the Button if button is present in GUI.
	 * If not present, script will skip click and AT will not fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void clickIfPresent( String buttonWrapper, String idOrXpath) throws Exception {
		try {
			buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ButtonElementHelper.getElement(buttonWrapper, idOrXpath);
			
			if (element != null)
				MouseHelper.click(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickIfEnabled( String idOrXpath) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			
			if (isEnabled) {
				idOrXpath = GenericHelper.getORProperty(idOrXpath);
				WebElement element = ButtonElementHelper.getElement(idOrXpath);
				MouseHelper.click(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickIfEnabled( String buttonWrapper, String idOrXpath) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			
			if (isEnabled) {
				buttonWrapper = GenericHelper.getORProperty(buttonWrapper);
				idOrXpath = GenericHelper.getORProperty(idOrXpath);
				WebElement element = ButtonElementHelper.getElement(buttonWrapper, idOrXpath);
				MouseHelper.click(element);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String idOrXpath) throws Exception {
		try {
			boolean isPresent = isPresent(idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String buttonWrapper, String idOrXpath) throws Exception {
		try {
			boolean isPresent = isPresent(buttonWrapper, idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Button is enabled or not.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void assertEnabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Button is enabled or not.
	 * If not enabled, test case will fail.
	 * @param buttonWrapper - Div or Table id within which the button is present.
	 * @param idOrXpath - id of the button.
	 * @throws Exception 
	 */
	public static void assertEnabled(String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (isEnabled)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled(String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (isEnabled)
				FailureHelper.failTest("Button box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}