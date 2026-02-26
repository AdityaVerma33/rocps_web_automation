package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.RadioElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RadioHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Radio button is present in GUI.
	 * If Radio button is not present, test case will fail.
	 * @param idOrXpath - id of the Radio button.
	 * @return 
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(idOrXpath);
			
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
	 * This method is used to check if the Radio button is present in GUI.
	 * If Radio button is not present, test case will fail.
	 * @param radioWrapper - Div or Table id within which the Radio button is present.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception 
	 */
	public static boolean isPresent( String radioWrapper, String idOrXpath ) throws Exception {
		try {
			radioWrapper = GenericHelper.getORProperty(radioWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(radioWrapper, idOrXpath);
			
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
	 * This method is used to click a Radio button
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception
	 */
	public static void click( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isChecked = RadioElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
			}
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to click a Radio button
	 * @param radioWrapper - Div or Table id within which the Radio button is present.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception
	 */
	public static void click( String radioWrapper, String idOrXpath ) throws Exception {
		try {
			radioWrapper = GenericHelper.getORProperty(radioWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(radioWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isChecked = RadioElementHelper.getValue(element);
				if (!isChecked) {
					MouseHelper.click(element);
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickIfEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isEnabled = RadioElementHelper.enabled(element);
				if (isEnabled) {
					boolean isChecked = RadioElementHelper.getValue(element);
					if (!isChecked) {
						MouseHelper.click(element);
					}
				}
			}
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickIfEnabled( String radioWrapper, String idOrXpath ) throws Exception {
		try {
			radioWrapper = GenericHelper.getORProperty(radioWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = RadioElementHelper.getElement(radioWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isEnabled = RadioElementHelper.enabled(element);
				if (isEnabled) {
					boolean isChecked = RadioElementHelper.getValue(element);
					if (!isChecked) {
						MouseHelper.click(element);
					}
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Radio button is checked.
	 * If Radio button is not checked, test case will fail.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception 
	 */
	public static boolean isChecked( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isChecked = RadioElementHelper.getValue(idOrXpath);
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Radio button is checked.
	 * If Radio button is not checked, test case will fail.
	 * @param radioWrapper - Div or Table id within which the Radio button is present.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception 
	 */
	public static boolean isChecked( String radioWrapper, String idOrXpath ) throws Exception {
		try {
			radioWrapper = GenericHelper.getORProperty(radioWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isChecked = RadioElementHelper.getValue(radioWrapper, idOrXpath);
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Radio button is enabled.
	 * If Radio button is not enabled, test case will fail.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception 
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isEnabled = RadioElementHelper.enabled(idOrXpath);
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Radio button is enabled.
	 * If Radio button is not enabled, test case will fail.
	 * @param radioWrapper - Div or Table id within which the Radio button is present.
	 * @param idOrXpath - id of the Radio button.
	 * @throws Exception 
	 */
	public static boolean isEnabled(String radioWrapper, String idOrXpath ) throws Exception {
		try {
			radioWrapper = GenericHelper.getORProperty(radioWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isEnabled = RadioElementHelper.enabled(radioWrapper, idOrXpath);
			
			return isEnabled;
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
	
	public static boolean isDisabled(String radioWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(radioWrapper, idOrXpath);
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(txtBoxWrapper, idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(txtBoxWrapper, idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not enabled");
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
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(txtBoxWrapper, idOrXpath);
			if (isEnabled)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}