package com.subex.automation.helpers.component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.TextAreaElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * TextArea related methods
 */
public class TextAreaHelper extends ComponentHelper {
	
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				return false;
			}
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isPresent( String txtAreaWrapper, String idOrXpath ) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				return false;
			}
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns the value inside TextArea
	 * @param TextAreaLocator
	 * @return
	 * @throws Exception 
	 */
	public static String getValue( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null)
				return null;
			else
				return ElementHelper.getValue(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns the value inside TextArea
	 * @param TextAreaLocator
	 * @return
	 * @throws Exception 
	 */
	public static String getValue( String txtAreaWrapper, String idOrXpath ) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				return null;
			}
			else
				return ElementHelper.getValue(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	private static void typeInBox( WebElement element, String value ) throws Exception {
		try {
//			String locator = LocatorHelper.getLocator(element);
			String currentValue = TextAreaElementHelper.getValue(element);
			
			if (ValidationHelper.isEmpty(currentValue) || !currentValue.equals(value)) {
				element.clear();
				ElementHelper.pressKey(element, Keys.TAB);
				element.sendKeys(value);
				
//				if(ElementHelper.isElementPresent(locator)) {
//					ElementHelper.click(element);
//					ElementHelper.pressKey(element, Keys.TAB);
//				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * Type into TextArea.
	 * @param TextAreaLocator - the TextArea locator
	 * @param value - value to be set in the TextArea
	 * @throws Exception 
	 */
	public static void type( String idOrXpath, String value ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element != null) {
				typeInBox(element, value);
			}
			else {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void type( String txtAreaWrapper, String idOrXpath, String value ) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(txtAreaWrapper, idOrXpath);
			
			if (element != null) {
				typeInBox(element, value);
			}
			else {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns whether TextArea is enabled or not
	 * @param TextAreaId
	 * @return
	 * @throws Exception 
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if(element.getAttribute("disabled")!= null || element.getAttribute("readonly")!= null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns whether TextArea is enabled or not
	 * @param TextAreaId
	 * @return
	 * @throws Exception 
	 */
	public static boolean isEnabled( String txtAreaWrapper, String idOrXpath ) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(txtAreaWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if(element.getAttribute("disabled")!= null || element.getAttribute("readonly")!= null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns whether TextArea is disabled or not
	 * @param TextAreaId
	 * @return
	 * @throws Exception 
	 */
	public static boolean isDisabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if(element.getAttribute("disabled") == null || element.getAttribute("readonly") == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns whether TextArea is disabled or not
	 * @param TextAreaId
	 * @return
	 * @throws Exception 
	 */
	public static boolean isDisabled( String txtAreaWrapper, String idOrXpath ) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(txtAreaWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if(element.getAttribute("disabled") == null || element.getAttribute("readonly") == null) {
				return false;
			}
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void isValuePresent( String idOrXpath, String expectedValue ) throws Exception {
		try {
			String value = getValue(idOrXpath);
			
			if (!value.equals(expectedValue)) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' does not have expected value '" + expectedValue + "'.");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void isValuePresent( String txtAreaWrapper, String idOrXpath, String expectedValue ) throws Exception {
		try {
			String value = getValue(txtAreaWrapper, idOrXpath);
			
			if (!value.equals(expectedValue)) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' does not have expected value '" + expectedValue + "'.");
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasValidation(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if (ElementHelper.getAttribute(element, "class").contains("roc-field-invalid"))
				return true;
			else
				return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean hasValidation(String txtAreaWrapper, String idOrXpath) throws Exception {
		try {
			txtAreaWrapper = GenericHelper.getORProperty(txtAreaWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextAreaElementHelper.getElement(txtAreaWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text Area '" + idOrXpath + "' is not found.");
			}
			
			if (ElementHelper.getAttribute(element, "class").contains("roc-field-invalid"))
				return true;
			else
				return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}