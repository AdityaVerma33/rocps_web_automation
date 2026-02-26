package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TextAreaElementHelper extends AcceptanceTest {
	
	private static final String targetString = "textAreaIdOrName";
	
	private static String[] getTextAreaLocators() throws Exception {
		try {
			String[] textAreaLocators = {"TextArea_ById", "TextArea_ByName"};
			
			return textAreaLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for TextArea input
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
				String[] locators = getTextAreaLocators();
				locator = LocatorHelper.getLocator(locators, targetString, idOrXpath);
			}
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for TextArea input with wrapper
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
					String[] locators = getTextAreaLocators();
					locator = LocatorHelper.getLocator(locators, wrapperLocator, targetString, idOrXpath);
				}
			}
			else
				locator = getLocator(idOrXpath);
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath ) throws Exception {
		try {
			WebElement element = null;
			
			if (idOrXpath.contains("/")) {
				element = ElementHelper.getElement(idOrXpath);
			}
			else {
				String[] locators = getTextAreaLocators();
				element = ElementHelper.getElement(locators, targetString, idOrXpath);
			}
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				if (idOrXpath.startsWith("/")) {
					element = ElementHelper.getElement(wrapperElement, idOrXpath);
				}
				else {
					String[] locators = getTextAreaLocators();
					element = ElementHelper.getElement(locators, wrapperElement, targetString, idOrXpath);
				}
			}
			else
				element = getElement(idOrXpath);
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( WebElement element ) throws Exception {
		try {
			String value = ElementHelper.getAttribute(element, "value");
			return value;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}