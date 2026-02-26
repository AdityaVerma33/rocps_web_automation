package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LabelElementHelper extends AcceptanceTest {
	
	private static final String targetString = "labelIdOrValue";
	
	private static String[] getLabelLocators() throws Exception {
		try {
			String[] labelLocators = {"Label_ById", "Label_ByText", "Common_Label", "Common_Label_BySpan", "Label_InTree"};
			
			return labelLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for label
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
				String[] locators = getLabelLocators();
				
				for (int i = 0; i < locators.length; i++) {
					if (ElementHelper.isElementPresent(or.getProperty(locators[i]).replace(targetString, idOrXpath))) {
						locator = or.getProperty(locators[i]).replace(targetString, idOrXpath);
						break;
					}
				}
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for label with wrapper
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
					String[] locators = getLabelLocators();
					
					for (int i = 0; i < locators.length; i++) {
						if (ElementHelper.isElementPresent(wrapperLocator + or.getProperty(locators[i]).replace(targetString, idOrXpath))) {
							locator = wrapperLocator + or.getProperty(locators[i]).replace(targetString, idOrXpath);
							break;
						}
					}
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
				String[] locators = getLabelLocators();
				element = ElementHelper.getElement(locators, targetString, idOrXpath);
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
			
			if (wrapper != null) {
				WebElement element = ElementHelper.getElement(wrapper, idOrXpath);
				
				if (element == null) {
					String[] locators = getLabelLocators();
					element = ElementHelper.getElement(locators, wrapper, targetString, idOrXpath);
				}
				
				return element;
			}
			else
				return getElement(idOrXpath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}