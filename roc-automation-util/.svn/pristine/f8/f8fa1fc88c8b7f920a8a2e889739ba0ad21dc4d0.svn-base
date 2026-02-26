package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class RadioElementHelper extends AcceptanceTest {

	/*
	 * returns the xpath locator for radio button
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
				if (ElementHelper.isElementPresent(or.getProperty("RadioButton_ById").replace("radioId", idOrXpath)))
					locator = or.getProperty("RadioButton_ById").replace("radioId", idOrXpath);
			}
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for radio button with wrapper
	 * @param wrapperId
	 * @param idOrXpath
	 * @return
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
					if (ElementHelper.isElementPresent(wrapperLocator + or.getProperty("RadioButton_ById").replace("radioId", idOrXpath)))
						locator = wrapperLocator + or.getProperty("RadioButton_ById").replace("radioId", idOrXpath);
				}
			}
			else
				locator = getLocator(idOrXpath);
			
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
				String radioLocator = or.getProperty("RadioButton_ById").replace("radioId", idOrXpath);
				element = ElementHelper.getElement(radioLocator);
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
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				element = ElementHelper.getElement(wrapperElement, idOrXpath);
				
				if (element == null) {
					String radioLocator = or.getProperty("RadioButton_ById").replace("radioId", idOrXpath);
					element = ElementHelper.getElement(wrapperElement, radioLocator);
				}
			}
			else
				element = getElement(idOrXpath);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * return value for input type Radio button
	 */
	public static boolean getValue( String idOrXpath ) throws Exception {
		try {
			WebElement element = getElement(idOrXpath);
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			return getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean getValue( WebElement element ) throws Exception {
		try {
			if(ElementHelper.getAttribute(element, "checked") != null)
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * return value for input type Radio button
	 * @return
	 */
	public static boolean getValue( String radioWrapper, String idOrXpath ) throws Exception {
		try {	
			WebElement element = getElement(radioWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			}
			
			if(ElementHelper.getAttribute(element, "checked") != null)
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
	
	public static boolean enabled(String idOrXpath) throws Exception {
		try {
			WebElement element = getElement(idOrXpath);
			
			if (element == null)
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			
			return enabled(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean enabled(String radioWrapper, String idOrXpath) throws Exception {
		try {
			WebElement element = getElement(radioWrapper, idOrXpath);
			
			if (element != null)
				return enabled(element);
			else
				FailureHelper.failTest("Radio button '" + idOrXpath + "' is not found.");
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}