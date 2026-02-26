package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ButtonElementHelper extends AcceptanceTest {
	
	private static final String targetString = "idOrText";
	
	private static String[] getButtonLocators() throws Exception {
		try {
			String[] buttonLocators = {"Button_ById", "Button_ByText", "Button_ByDivId", "Button_ByDivText", "DNA_Button"};
			
			return buttonLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for button
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
				String[] buttonLocators = getButtonLocators();
				locator = LocatorHelper.getLocator(buttonLocators, targetString, idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for button with wrapper
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
					String[] buttonLocators = getButtonLocators();
					locator = LocatorHelper.getLocator(buttonLocators, wrapperLocator, targetString, idOrXpath);
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
				String[] buttonLocators = getButtonLocators();
				element = ElementHelper.getElement(buttonLocators, targetString, idOrXpath);
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for button with wrapper
	 * @param wrapperId
	 * @param idOrXpath
	 * @return
	 * @throws Exception 
	 */
	public static WebElement getElement( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				element = ElementHelper.getElement(wrapperElement, idOrXpath);
				
				if (element == null) {
					String[] buttonLocators = getButtonLocators();
					element = ElementHelper.getElement(buttonLocators, wrapperElement, targetString, idOrXpath);
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
	 * returns the text in button
	 * @param idOrXpath
	 * @return
	 */
	public static String getText( String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return null;
			}
			else
				return ElementHelper.getText(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the text in button
	 * @param idOrXpath
	 * @return
	 * @throws Exception 
	 */
	public static String getText(String buttonWrapper, String idOrXpath ) throws Exception {
		try {
			WebElement element = getElement(buttonWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Button '" + idOrXpath + "' is not found.");
				return null;
			}
			else
				return ElementHelper.getText(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}