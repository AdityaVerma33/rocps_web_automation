package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

/*
 * Tab related selenium wrapper api's.
 */
public class TabElementHelper extends AcceptanceTest {
	
	private static final String targetString = "tabIdOrName";
	
	private static String[] getTabLocators() throws Exception {
		try {
			String[] textBoxLocators = {"Tab_ById", "Tab_Div_ByText", "Tab_Span_ByText"};
			
			return textBoxLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String idOrXpath) throws Exception {
		try {
			String locator = null;
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				String[] tabLocators = getTabLocators();
				locator = LocatorHelper.getLocator(tabLocators, targetString, idOrXpath);
			}
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
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
					String[] tabLocators = getTabLocators();
					locator = LocatorHelper.getLocator(tabLocators, wrapperLocator, targetString, idOrXpath);
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
	
	public static WebElement getTabPanel() throws Exception {
		try {
			WebElement element = ElementHelper.getElement("Popup_Panel", "Tab_Panel");
			
			if (element == null)
				element = ElementHelper.getElement("Tab_Panel");
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getTabPanel(String wrapperId) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(wrapperId, "Tab_Panel");
			
			if (element == null)
				element = ElementHelper.getElement("Tab_Panel");
			
			if (element == null)
				element = ElementHelper.getElement(wrapperId);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String idOrXpath) throws Exception {
		try {
			WebElement tabPanel = getTabPanel();
			WebElement element = null;
			
			if (tabPanel != null) {
				element = ElementHelper.getElement(tabPanel, idOrXpath);
				
				if (element == null) {
					String[] tabLocators = getTabLocators();
					element = ElementHelper.getElement(tabLocators, tabPanel, targetString, idOrXpath);
				}
			}
			else {
				element = ElementHelper.getElement(idOrXpath);
				
				if (element == null) {
					String[] tabLocators = getTabLocators();
					element = ElementHelper.getElement(tabLocators, targetString, idOrXpath);
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
			WebElement wrapperElement = getTabPanel(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				if (idOrXpath.startsWith("/")) {
					element= ElementHelper.getElement(wrapperElement, idOrXpath);
				}
				else {
					String[] tabLocators = getTabLocators();
					element = ElementHelper.getElement(tabLocators, wrapperElement, targetString, idOrXpath);
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
	
	public static boolean isSelected(WebElement element) throws Exception {
		try {
			WebElement parent = ElementHelper.getElement(element, "Active_Tab_Parent");
			if(parent != null)
				return true;
			else {
				String classAttribute = ElementHelper.getAttribute(element, "class");
				if (classAttribute != null && classAttribute.contains("selected"))
					return true;
				else
					return false;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}