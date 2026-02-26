package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.CheckBoxElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Check box related methods.
 * @author madhu.duraisamy
 *
 */
public class CheckBoxHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the check box is present in the GUI
	 * If not present, test case will fail. 
	 * @param idOrXpath - id of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			
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
	 * This method is used to check if the check box is present in the GUI
	 * If not present, test will fail. 
	 * @param checkBoxWrapper - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isPresent( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(checkBoxWrapper, idOrXpath);
			
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
	 * This method is used to check the check box.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static void check( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Check  box '" + idOrXpath + "' is not found.");
			}
			else {
				CheckBoxElementHelper.check(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check the check box.
	 * @param checkBoxWrapper - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static void check( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			checkBoxWrapper = GenericHelper.getORProperty(checkBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Check  box '" + idOrXpath + "' is not found.");
			}
			else {
				CheckBoxElementHelper.check(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check the check box.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Check  box '" + idOrXpath + "' is not found.");
			}
			else {
				CheckBoxElementHelper.uncheck(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to un-check the check box.
	 * @param checkBoxWrapper - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static void uncheck( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(checkBoxWrapper + idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Check  box '" + idOrXpath + "' is not found.");
			}
			else {
				CheckBoxElementHelper.uncheck(element);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the check box is checked.
	 * If not checked, test case will fail.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static boolean isChecked( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			boolean isChecked = CheckBoxElementHelper.getValue(element);

			return isChecked;	
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the check box is checked.
	 * If not checked, test case will fail.
	 * @param checkBoxWrapper - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static boolean isChecked( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			checkBoxWrapper = GenericHelper.getORProperty(checkBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(checkBoxWrapper, idOrXpath);
			boolean isChecked = CheckBoxElementHelper.getValue(element);
			
			return isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotChecked( String idOrXpath ) throws Exception {
		try {
			boolean isChecked = isChecked(idOrXpath);
			return !isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isNotChecked( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isChecked = isChecked(checkBoxWrapper, idOrXpath);
			return !isChecked;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the check box is enabled.
	 * If not enabled, test case will fail.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(idOrXpath);
			boolean isEnabled = CheckBoxElementHelper.enabled(element);
			
			return isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the check box is enabled.
	 * If not enabled, test case will fail.
	 * @param checkBoxWrapper - Div or Table id within which the check box is present.
	 * @param idOrXpath - id of the check box.
	 * @throws Exception
	 */
	public static boolean isEnabled(String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			checkBoxWrapper = GenericHelper.getORProperty(checkBoxWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = CheckBoxElementHelper.getElement(checkBoxWrapper, idOrXpath);
			boolean isEnabled = CheckBoxElementHelper.enabled(element);
			
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
	
	public static boolean isDisabled(String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(checkBoxWrapper, idOrXpath);
			return !isEnabled;
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
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(checkBoxWrapper, idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void isassertEnabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled(String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(checkBoxWrapper, idOrXpath);
			if (!isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not enabled");
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
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String checkBoxWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(checkBoxWrapper, idOrXpath);
			if (isEnabled)
				FailureHelper.failTest("Check box '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}