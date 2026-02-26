package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.TextBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Text box related methods
 * @author madhu.duraisamy
 */
public class TextBoxHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Text box is present in GUI.
	 * @param idOrXpath - id of the text box.
	 * @return true if text box is present, else false
	 * @throws Exception
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
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
	 * This method is used to check if the Text box is present in GUI.
	 * @param txtBoxWrapper - Div or Table id within which the text box is present.
	 * @param idOrXpath - id of the text box.
	 * @return true if text box is present, else false
	 * @throws Exception
	 */
	public static boolean isPresent( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(txtBoxWrapper, idOrXpath);
				
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
	
	public static void clear( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(idOrXpath);
			
			if (element != null) {
				element.clear();
			}
			else {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to type specified value into the text box.
	 * @param idOrXpath - id of the text box.
	 * @param value - value to be typed in the text box.
	 * @throws Exception 
	 */
	public static void type( String idOrXpath, String value ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				idOrXpath = GenericHelper.getORProperty(idOrXpath);
				WebElement element = TextBoxElementHelper.getElement(idOrXpath);
				
				if (element != null) {
					boolean isGridTextBox = TextBoxElementHelper.isGridTextBox(element);
					
					if (isGridTextBox || idOrXpath.contains("gwt_uid_"))
						TextBoxElementHelper.typeInGridBox(element, idOrXpath, value);
					else
						TextBoxElementHelper.typeInBox(element, idOrXpath, value);
				}
				else {
					FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to type specified value into the text box.
	 * @param txtBoxWrapper - Div or Table id within which the text box is present.
	 * @param idOrXpath - id of the text box.
	 * @param value - value to be typed in the text box.
	 * @throws Exception
	 */
	public static void type( String txtBoxWrapper, String idOrXpath, String value ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
				idOrXpath = GenericHelper.getORProperty(idOrXpath);
				WebElement element = TextBoxElementHelper.getElement(txtBoxWrapper, idOrXpath);
				
				if (element != null) {
					boolean isGridTextBox = TextBoxElementHelper.isGridTextBox(element);
					
					if (isGridTextBox || idOrXpath.contains("gwt_uid_"))
						TextBoxElementHelper.typeInGridBox(element, idOrXpath, value);
					else
						TextBoxElementHelper.typeInBox(element, idOrXpath, value);
				}
				else {
					FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box is enabled.
	 * If the text box is not enabled, test will fail.
	 * @param idOrXpath - id of the text box.
	 * @return true if text box is enabled, else false
	 * @throws Exception
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				return TextBoxElementHelper.isEnabled(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box is enabled.
	 * If the text box is not enabled, test will fail.
	 * @param txtBoxWrapper - Div or Table id within which the text box is present.
	 * @param idOrXpath - id of the text box.
	 * @return true if text box is enabled, else false
	 * @throws Exception
	 */
	public static boolean isEnabled( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(txtBoxWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				return TextBoxElementHelper.isEnabled(element);
			}
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
	
	public static boolean isDisabled( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(txtBoxWrapper, idOrXpath);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has the specified value.
	 * If the text box is empty or has different value, test will fail.
	 * @param idOrXpath - id of the text box.
	 * @param expectedValue - expected value
	 * @throws Exception
	 */
	public static boolean isValuePresent( String idOrXpath, String expectedValue ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = getValue(idOrXpath);
			
			if (value == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				if (!value.equals(expectedValue))
					return false;
				else
					return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has the specified value.
	 *  If the text box is empty or has different value, test will fail.
	 * @param txtBoxWrapper - Div or Table id within which the text box is present.
	 * @param idOrXpath - id of the text box.
	 * @param expectedValue - expected value
	 * @throws Exception
	 */
	public static boolean isValuePresent( String txtBoxWrapper, String idOrXpath, String expectedValue ) throws Exception {
		try {
			txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = getValue(txtBoxWrapper, idOrXpath);
			
			if (value == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				if (!value.equals(expectedValue))
					return false;
				else
					return true;
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the value inside text box
	 * @param textBoxLocator
	 * @return
	 */
	public static String getValue( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(idOrXpath);
			
			return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/*
	 * returns the value inside text box
	 * @param textBoxLocator
	 * @return
	 * @throws Exception 
	 */
	public static String getValue( String txtBoxWrapper, String idOrXpath ) throws Exception {
		try {
			txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(txtBoxWrapper, idOrXpath);
			
			return TextBoxElementHelper.getValue(element);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has validation highlighting.
	 * If not, test will fail.
	 * @param idOrXpath - id of the text box.
	 * @throws Exception
	 */
	public static boolean isMandatory(String idOrXpath) throws Exception {
		try {
			return hasValidation(idOrXpath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has validation highlighting.
	 * If not, test will fail.
	 * @param txtBoxWrapper
	 * @param idOrXpath - id of the text box.
	 * @throws Exception
	 */
	public static boolean isMandatory(String txtBoxWrapper, String idOrXpath) throws Exception {
		try {
			return hasValidation(txtBoxWrapper, idOrXpath);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has validation highlighting.
	 * If not, test will fail.
	 * @param idOrXpath - id of the text box.
	 * @throws Exception
	 */
	public static boolean hasValidation(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				return ElementHelper.hasValidation(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the text box has validation highlighting.
	 * If not, test will fail.
	 * @param txtBoxWrapper
	 * @param idOrXpath - id of the text box.
	 * @throws Exception
	 */
	public static boolean hasValidation(String txtBoxWrapper, String idOrXpath) throws Exception {
		try {
			txtBoxWrapper = GenericHelper.getORProperty(txtBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TextBoxElementHelper.getElement(txtBoxWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not found.");
				return false;
			}
			else {
				String classValue = ElementHelper.getAttribute(element, "class");
				if (!classValue.contains("roc-field-invalid")) {
					return false;
				}
				else
					return true;
			}
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not present");
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not present");
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not enabled");
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not enabled");
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not disabled");
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
				FailureHelper.failTest("Text box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}