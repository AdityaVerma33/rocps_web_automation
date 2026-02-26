package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TextBoxElementHelper extends AcceptanceTest {
	
	private static final String targetString = "idOrName";
	
	private static String[] getTextBoxLocators() throws Exception {
		try {
			String[] textBoxLocators = {"TextBox_ById", "TextBox_ByName", "TextBox_DatePicker", "TextBox_SearchBox",
										"TextBox_With_TableWrapper", "TextBox_With_DivWrapper"};
			
			return textBoxLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for textbox input
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
				String[] textBoxLocators = getTextBoxLocators();
				locator = LocatorHelper.getLocator(textBoxLocators, targetString, idOrXpath);
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the xpath locator for textbox input with wrapper
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
					String[] textBoxLocators = getTextBoxLocators();
					locator = LocatorHelper.getLocator(textBoxLocators, wrapperLocator, targetString, idOrXpath);
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
			
			if (element == null && !idOrXpath.startsWith("/")) {
				String[] textBoxLocators = getTextBoxLocators();
				element = ElementHelper.getElement(textBoxLocators, targetString, idOrXpath);
			}
			
			if (element != null) {
				WebElement element1 = element;
				if (!element.getTagName().equals("input")) {
					element = ElementHelper.getElement(element1, "/tbody/tr/td[1]/input");
					
					if (element == null)
						element = ElementHelper.getElement(element1, "//input");
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
			WebElement element = null;
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			
			if (wrapperElement != null) {
				if (idOrXpath.startsWith("/")) {
					element = ElementHelper.getElement(wrapperElement, idOrXpath);
				}
				else {
					String[] textBoxLocators = getTextBoxLocators();
					element = ElementHelper.getElement(textBoxLocators, wrapperElement, targetString, idOrXpath);
				}
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isGridTextBox(WebElement element) throws Exception {
		try {
			if (ElementHelper.isElementPresent(element, "Grid_TextBox_Wrapper"))
				return true;
			else
				return false;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void typeInBox( WebElement element, String locator, String value ) throws Exception {
		try {
			String currentValue = ElementHelper.getValue(element);
			
			if (ValidationHelper.isEmpty(currentValue) || !currentValue.equals(value)) {
				ElementHelper.clear(element);
				ElementHelper.pressKey(element, Keys.TAB);
				element.sendKeys(value);
			}
		}
		catch (WebDriverException e) {
			FailureHelper.setError("Text Box '" + locator + "' is not clickable");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInGridBox( WebElement element, String idOrXpath, String value ) throws Exception {
		try {
			String currentValue = ElementHelper.getValue(element);
			
			if (ValidationHelper.isNotEmpty(currentValue) || !currentValue.equals(value)) {
				ElementHelper.clear(element);
				element.sendKeys(value);
			}
		}
		catch (WebDriverException e) {
			FailureHelper.setError("Text Box '" + idOrXpath + "' is not clickable");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void typeInPropertyGridBox( WebElement element, String propertyName, String value ) throws Exception {
		try {
			String currentValue = ElementHelper.getValue(element);
			
			if (ValidationHelper.isNotEmpty(currentValue) || !currentValue.equals(value)) {
				ElementHelper.clear(element);
				element.sendKeys(value);
				ElementHelper.pressKey(element, Keys.TAB);
			}
		}
		catch (WebDriverException e) {
			FailureHelper.setError("Text Box for property '" + propertyName + "' is not clickable");
			FailureHelper.setErrorMessage(e);
			throw e;
		}
		catch(Exception e)
		{
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue( WebElement element ) throws Exception {
		try {
			if (element == null)
				return null;
			else
				return ElementHelper.getAttribute(element, "value");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isEnabled( WebElement element ) throws Exception {
		try {
			String disabled = ElementHelper.getAttribute(element, "disabled");
			String readOnly = ElementHelper.getAttribute(element, "readonly");
			
			if(disabled !=null || readOnly !=null) {
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
}