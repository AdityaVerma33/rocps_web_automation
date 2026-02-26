package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.componentHelpers.LabelElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Label related selenium wrapper api's for GXT.
 */
public class LabelHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Label is present in GUI.
	 * If Label is not present, test case will fail.
	 * @param idOrXpath - id of the Label.
	 * @throws Exception 
	 */
	public static boolean isPresent(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String locator = LabelElementHelper.getLocator(idOrXpath);
			if (locator == null) {
				return false;
			}
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Label is present in GUI.
	 * If Label is not present, test case will fail.
	 * @param lblWrapper - Div or Table id within which the Label is present.
	 * @param idOrXpath - id of the Label.
	 * @throws Exception 
	 */
	public static boolean isPresent(String lblWrapper, String idOrXpath) throws Exception {
		try {
			lblWrapper = GenericHelper.getORProperty(lblWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String locator = LabelElementHelper.getLocator(lblWrapper, idOrXpath);
			
			if (locator == null) {
				return false;
			}
			else
				return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Label has the specified value.
	 * If not, test case will fail.
	 * @param idOrXpath - id of the Label.
	 * @param expectedValue
	 * @throws Exception 
	 */
	public static boolean isValuePresent( String idOrXpath, String expectedValue ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			expectedValue = expectedValue.replace("  ", " ").replace("\u00A0", "");
			String locator = LabelElementHelper.getLocator(idOrXpath);
			String value = null;
			
			if (locator != null)
				value = getText(locator);
			
			if (locator == null || (value != null && !value.equals(expectedValue))) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Label has the specified value.
	 * If not, test case will fail.
	 * @param lblWrapper - Div or Table id within which the Label is present.
	 * @param idOrXpath - id of the Label.
	 * @param expectedValue
	 * @throws Exception 
	 */
	public static boolean isValuePresent( String lblWrapper, String idOrXpath, String expectedValue ) throws Exception {
		try {
			lblWrapper = GenericHelper.getORProperty(lblWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			expectedValue = expectedValue.replace("  ", " ").replace("\u00A0", "");
			String locator = LabelElementHelper.getLocator(lblWrapper, idOrXpath);
			String value = null;
			
			if (locator != null)
				value = getText(locator);
			
			if (locator == null || (value != null && !value.equals(expectedValue))) {
				return false;
			}
			else {
				return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Screen has the specified title.
	 * If not, test case will fail.
	 * @param screenTitle - Screen (Search of Detail or Pop-up) title
	 * @throws Exception
	 */
	public static boolean isTitlePresent( String screenTitle ) throws Exception {
		try {
			boolean isPresent = NavigationHelper.isTitlePresent(screenTitle);
			return isPresent;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTitleNotPresent( String screenTitle ) throws Exception {
		try {
			boolean isPresent = isTitlePresent(screenTitle);
			return !isPresent;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTextPresent(String text) throws Exception {
		try {
			WebElement element = LabelElementHelper.getElement(text);
			
			if(element != null)
				return true;
			else if (driver.getPageSource().contains(text))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTextPresent(String lblWrapper, String text) throws Exception {
		try {
			WebElement element = LabelElementHelper.getElement(lblWrapper, text);
			
			if(element != null)
				return true;
			else if (driver.getPageSource().contains(text))
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getText(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			return ElementHelper.getText(idOrXpath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}