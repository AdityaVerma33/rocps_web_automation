package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.subex.automation.helpers.componentHelpers.ComboBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ComboBoxHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Combo box is present in GUI.
	 * If combo box is not present, test case will fail.
	 * @param idOrXpath - id of the combo box.
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ComboBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				element = ComboBoxElementHelper.getSelectCombo(idOrXpath);
				
				if (element == null)
					return false;
			}
			
			return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Combo box is present in GUI.
	 * If combo box is not present, test case will fail.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isPresent( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ComboBoxElementHelper.getElement(comboBoxWrapper, idOrXpath);

			if (element == null) {
				element = ComboBoxElementHelper.getSelectCombo(comboBoxWrapper, idOrXpath);
				
				if (element == null)
					return false;
			}
			
			return true;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void select( String value ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				String idOrXpath = GenericHelper.getORProperty("Common_Select_Combo");
				WebElement element = ElementHelper.getElement(idOrXpath);
				
				Select sel = new Select(element);
				sel.selectByValue(value);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to select specified value in Combo Box
	 * @param idOrXpath - id of the combo box.
	 * @param value - value to be selected.
	 * @throws Exception
	 */
	public static void select( String idOrXpath, String value ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			if (ValidationHelper.isNotEmpty(value)) {
				WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
				
				if (comboWrapper != null) {
					ElementHelper.waitForClickableElement(comboWrapper, detailScreenWaitSec);
					String valueLocator = ComboBoxElementHelper.getValueLocator(comboWrapper);
					WebElement valueElement = ComboBoxElementHelper.getValueElement(comboWrapper);
					
					if (valueLocator == null )
						FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
					else {
						WebElement arrowElement = ComboBoxElementHelper.getArrowElement(comboWrapper);
						ElementHelper.waitForClickableElement(arrowElement, detailScreenWaitSec);
						String currentValue = ComboBoxElementHelper.getValue(valueElement);
						
						if (value.length() >= 32) {
							String truncatedValue = value.substring(0, 29) + "...";
							
							if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue)) {
								currentValue = ComboBoxElementHelper.selectTruncatedValue(comboWrapper, idOrXpath, arrowElement, valueLocator, value, truncatedValue);
								
								if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
						else {
							if (!value.equals(currentValue)) {
								currentValue = ComboBoxElementHelper.selectValue(comboWrapper, idOrXpath, arrowElement, valueLocator, value);
								
								if (!value.equals(currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
					}
				}
				else
					ComboBoxElementHelper.selectForSelectTag(idOrXpath, value);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to select specified value in Combo Box
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @param value - value to be selected.
	 * @throws Exception
	 */
	public static void select( String comboBoxWrapper, String idOrXpath, String value ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
				idOrXpath = GenericHelper.getORProperty(idOrXpath);
				WebElement wrapper = ElementHelper.getWrapperElement(comboBoxWrapper);
				WebElement comboWrapper = ComboBoxElementHelper.getElement(wrapper, idOrXpath);
				
				if (comboWrapper != null) {
					ElementHelper.waitForClickableElement(comboWrapper, detailScreenWaitSec);
					WebElement arrowElement = ComboBoxElementHelper.getArrowElement(comboWrapper);
					ElementHelper.waitForClickableElement(arrowElement, detailScreenWaitSec);
					String valueLocator = ComboBoxElementHelper.getValueLocator(comboWrapper);
					
					if (valueLocator == null )
						FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
					else {
						String currentValue = ComboBoxElementHelper.getValue(valueLocator);
						
						if (value.length() >= 32) {
							String truncatedValue = value.substring(0, 29) + "...";
							
							if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue)) {
								currentValue = ComboBoxElementHelper.selectTruncatedValue(comboWrapper, wrapper, idOrXpath, arrowElement, valueLocator, value, truncatedValue);
								
								if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
						else {
							if (!ComboBoxElementHelper.isValueSelected(value, currentValue)) {
								currentValue = ComboBoxElementHelper.selectValue(comboWrapper, wrapper, idOrXpath, arrowElement, valueLocator, value);
								
								if (!value.equals(currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
					}
				}
				else
					ComboBoxElementHelper.selectForSelectTag(comboBoxWrapper, idOrXpath, value);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void select( String gridId, String idOrXpath, int rowNum, int columnNum, String value) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			
			if (ValidationHelper.isNotEmpty(value)) {
				WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
				
				if (comboWrapper != null) {
					ElementHelper.waitForClickableElement(comboWrapper, detailScreenWaitSec);
					String valueLocator = ComboBoxElementHelper.getValueLocator(comboWrapper);
					WebElement valueElement = ComboBoxElementHelper.getValueElement(comboWrapper);
					
					if (valueLocator == null )
						FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
					else {
						WebElement arrowElement = ComboBoxElementHelper.getArrowElement(comboWrapper);
						ElementHelper.waitForClickableElement(arrowElement, detailScreenWaitSec);
						String currentValue = ComboBoxElementHelper.getValue(valueElement);
						
						if (value.length() >= 32) {
							String truncatedValue = value.substring(0, 29) + "...";
							
							if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue)) {
								currentValue = ComboBoxElementHelper.selectTruncatedValue(comboWrapper, idOrXpath, arrowElement, valueLocator, value, truncatedValue);
								if (!ComboBoxHelper.isPresent(idOrXpath)) {
									GridHelper.clickRow(gridId, rowNum, columnNum);
									currentValue = ComboBoxHelper.getValue(idOrXpath);
								}
							
								if (!ComboBoxElementHelper.isValueSelected(value, truncatedValue, currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
						else {
							if (!value.equals(currentValue)) {
								currentValue = ComboBoxElementHelper.selectValue(comboWrapper, idOrXpath, arrowElement, valueLocator, value);
								if (!ComboBoxHelper.isPresent(idOrXpath)) {
									GridHelper.clickRow(gridId, rowNum, columnNum);
									currentValue = ComboBoxHelper.getValue(idOrXpath);
								}
								
								if (!value.equals(currentValue))
									FailureHelper.failTest("Expected value '" + value + "' not found in Combo box");
							}
						}
					}
				}
				else
					ComboBoxElementHelper.selectForSelectTag(idOrXpath, value);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the specified value is selected in the combo box
	 * @param idOrXpath - id of the combo box.
	 * @param expectedValue - value to be verified.
	 * @throws Exception 
	 */
	public static boolean isValuePresent( String idOrXpath, String expectedValue ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ComboBoxElementHelper.getValue(idOrXpath);
			
			if (!value.equals(expectedValue))
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
	 * This method is used to check if the specified value is selected in the combo box
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @param expectedValue - value to be verified.
	 * @throws Exception 
	 */
	public static boolean isValuePresent( String comboBoxWrapper, String idOrXpath, String expectedValue ) throws Exception {
		try {
			comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String value = ComboBoxElementHelper.getValue(comboBoxWrapper, idOrXpath);
			
			if (!value.equals(expectedValue))
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
	 * This method is used to check if the combo box is enabled or not.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the combo box.
	 * @throws Exception 
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isEnabled = ComboBoxElementHelper.enabled(idOrXpath);
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the combo box is enabled or not.
	 * If not enabled, test case will fail.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @throws Exception 
	 */
	public static boolean isEnabled( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			boolean isEnabled = ComboBoxElementHelper.enabled(comboBoxWrapper, idOrXpath);
			
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
	
	/**
	 * This method is used to check if the combo box is enabled or not.
	 * If not enabled, test case will fail.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @throws Exception 
	 */
	public static boolean isDisabled( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(comboBoxWrapper, idOrXpath);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the combo box is mandatory or not.
	 * If not mandatory, test case will fail.
	 * @param idOrXpath - id of the combo box.
	 * @throws Exception 
	 */
	public static boolean hasValidation(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ComboBoxElementHelper.getElement(idOrXpath);
					
			if (element != null) {
				String classAttribute = ElementHelper.getAttribute(element, "class");
				if (!classAttribute.contains("roc-field-invalid"))
					return false;
				else
					return true;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the combo box is mandatory or not.
	 * If not mandatory, test case will fail.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @throws Exception 
	 */
	public static boolean hasValidation(String comboBoxWrapper, String idOrXpath) throws Exception {
		try {
			comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = ComboBoxElementHelper.getElement(comboBoxWrapper, idOrXpath);
			
			if (element != null) {
				String classAttribute = ElementHelper.getAttribute(element, "class");
				if (!classAttribute.contains("roc-field-invalid"))
					return false;
				else
					return true;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is a specified values are present in Combo Box drop down in the same order.
	 * @param idOrXpath - id of the combo box.
	 * @param value - values to be verified as ; separated.
	 * @return 
	 * @throws Exception
	 */
	public static boolean containsValue(String idOrXpath, String value) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, idOrXpath);
				
				if (actualValues == null)
					return false;
				else {
					for (int i = 0; i < actualValues.length; i++) {
						if (value.equals(actualValues[i])) {
							return true;
						}
					}
				}
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is a specified values are present in Combo Box drop down in the same order.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @param value - values to be verified as ; separated.
	 * @return 
	 * @throws Exception
	 */
	public static boolean containsValue(String comboBoxWrapper, String idOrXpath, String value) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(comboBoxWrapper, idOrXpath);
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, comboBoxWrapper, idOrXpath);
				
				if (actualValues == null)
					return false;
				else {
					for (int i = 0; i < actualValues.length; i++) {
						if (value.equals(actualValues[i])) {
							return true;
						}
					}
				}
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is a specified values are present in Combo Box drop down in the same order.
	 * @param idOrXpath - id of the combo box.
	 * @param value - values to be verified as ; separated.
	 * @return 
	 * @throws Exception
	 */
	public static boolean containsValues(String idOrXpath, String[] values) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			int count = 0;
			int length = values.length;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, idOrXpath);
				
				if (actualValues == null)
					return false;
				else {
					for (int i = 0; i < actualValues.length; i++) {
						for (int j = 0; j < values.length; j++) {
							if (values[j].equals(actualValues[i])) {
								count++;
								break;
							}
						}
					}
				}
			}
			
			if (count == length)
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check is a specified values are present in Combo Box drop down in the same order.
	 * @param comboBoxWrapper - Div or Table id within which the combo box is present.
	 * @param idOrXpath - id of the combo box.
	 * @param value - values to be verified as ; separated.
	 * @return 
	 * @throws Exception
	 */
	public static boolean containsValues(String comboBoxWrapper, String idOrXpath, String[] values) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(comboBoxWrapper, idOrXpath);
			int count = 0;
			int length = values.length;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, comboBoxWrapper, idOrXpath);
				
				if (actualValues == null)
					return false;
				else {
					for (int i = 0; i < actualValues.length; i++) {
						for (int j = 0; j < values.length; j++) {
							if (values[j].equals(actualValues[i])) {
								count++;
								break;
							}
						}
					}
				}
			}
			
			if (count == length)
				return true;
			else
				return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String currentValue = ComboBoxElementHelper.getValue(idOrXpath);
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getValue(String comboBoxWrapper, String idOrXpath) throws Exception {
		try {
			comboBoxWrapper = GenericHelper.getORProperty(comboBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			String currentValue = ComboBoxElementHelper.getValue(comboBoxWrapper, idOrXpath);
			
			return currentValue;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getAllValues(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			String[] actualValues = null;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, idOrXpath);
			}
			
			return actualValues;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getAllValues(String comboBoxWrapper, String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			String[] actualValues = null;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, comboBoxWrapper, idOrXpath);
			}
			
			return actualValues;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getValuesCount(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			int length = 0;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, idOrXpath);
				length = actualValues.length;
			}
			
			return length;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getValuesCount(String comboBoxWrapper, String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement comboWrapper = ComboBoxElementHelper.getElement(idOrXpath);
			int length = 0;
			
			if (comboWrapper == null) {
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not found.");
			}
			else {
				String[] actualValues = ComboBoxElementHelper.getAllValues(comboWrapper, comboBoxWrapper, idOrXpath);
				length = actualValues.length;
			}
			
			return length;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(comboBoxWrapper, idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not present");
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
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(comboBoxWrapper, idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not enabled");
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
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String comboBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(comboBoxWrapper, idOrXpath);
			if (isEnabled)
				FailureHelper.failTest("Combo box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}