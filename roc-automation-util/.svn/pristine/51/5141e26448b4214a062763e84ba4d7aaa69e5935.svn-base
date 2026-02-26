package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LinkElementHelper extends AcceptanceTest {
	
	private static final String targetString = "linkIdOrText";
	
	private static String[] getLocators() throws Exception {
		try {
			String[] linkLocators = {"Link_ByDiv", "Link_ById", "Link_ByText"};
			
			return linkLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for link
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
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for link with wrapper
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
					String[] locators = getLocators();
					locator = LocatorHelper.getLocator(locators, wrapperLocator, targetString, idOrXpath);
				}
			}
			else
				locator = getLocator(idOrXpath);
			
			return locator;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				String[] locators = getLocators();
				element = ElementHelper.getElement(locators, targetString, idOrXpath);
			}
			
			return element;
		}
		catch(Exception e)
		{
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
					String[] locators = getLocators();
					element = ElementHelper.getElement(locators, wrapperElement, targetString, idOrXpath);
				}
			}
			else
				element = getElement(idOrXpath);
			
			return element;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}