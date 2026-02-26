package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.TabElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Tab related selenium wrapper api's.
 */
public class TabHelper extends ComponentHelper {

	/**
	 * This method checks if Tab is present in GUI. If not present, test case will fail.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	public static boolean isPresent( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(idOrXpath);
			
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
	
	/**
	 * This method checks if Tab is present in GUI. If not present, test case will fail.
	 * @param tabWrapper - Div or Table id within which the tab is present.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isPresent( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			tabWrapper = GenericHelper.getORProperty(tabWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(tabWrapper, idOrXpath);
			
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
	
	/**
	 * This method is used to go to a tab with specified text.
	 * @param idOrXpath - the tab text
	 * @throws Exception 
	 */
	public static void gotoTab( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not found.");
			}
			else {
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/**
	 * This method is used to go to a tab with specified text.
	 * @param tabWrapper - Div or Table id within which the tab is present.
	 * @param idOrXpath - the tab text
	 * @throws Exception 
	 */
	public static void gotoTab( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			tabWrapper = GenericHelper.getORProperty(tabWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(tabWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not found.");
			}
			else {
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if tab is enabled or not. If not, test case will fail.
	 * @param idOrXpath - the tab text
	 * @return
	 * @throws Exception 
	 */
	public static boolean isEnabled( String idOrXpath ) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not enabled.");
			}
			else {
				if(ElementHelper.getAttribute(element, "class").contains("disable"))
					return false;
				else
					return true;
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if tab is enabled or not. If not, test case will fail.
	 * @param tabWrapper - Div or Table id within which the tab is present.
	 * @param idOrXpath - the tab text
	 * @throws Exception 
	 */
	public static boolean isEnabled( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			tabWrapper = GenericHelper.getORProperty(tabWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(tabWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not found.");
			}
			else {
				if(ElementHelper.getAttribute(element, "class").contains("disable"))
					return false;
				else
					return true;
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if tab is disabled or not. If not, test case will fail.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @throws Exception 
	 */
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
	 * This method is used to check if tab is disabled or not. If not, test case will fail.
	 * @param tabWrapper - Div or Table id within which the tab is present.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isDisabled( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(tabWrapper, idOrXpath);
			return !isEnabled;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if tab is currently selected or not. If not, test case will fail.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isSelected(String idOrXpath) throws Exception {
		try {
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isSelected = TabElementHelper.isSelected(element);
				return isSelected;
			}
			
			return false;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if tab is currently selected or not. If not, test case will fail.
	 * @param tabWrapper - Div or Table id within which the tab is present.
	 * @param idOrXpath - the tab text
	 * @return 
	 * @throws Exception 
	 */
	public static boolean isSelected(String tabWrapper, String idOrXpath) throws Exception {
		try {
			tabWrapper = GenericHelper.getORProperty(tabWrapper);
			idOrXpath = GenericHelper.getORProperty(idOrXpath);
			WebElement element = TabElementHelper.getElement(tabWrapper, idOrXpath);
			
			if (element == null) {
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not found.");
			}
			else {
				boolean isSelected = TabElementHelper.isSelected(element);
				return isSelected;
			}
			
			return false;
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
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertPresent( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isPresent = isPresent(tabWrapper, idOrXpath);
			if (!isPresent)
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not present");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			
			if (isEnabled)
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertEnabled( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(tabWrapper, idOrXpath);
			
			if (isEnabled)
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not enabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(idOrXpath);
			
			if (!isEnabled)
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void assertDisabled( String tabWrapper, String idOrXpath ) throws Exception {
		try {
			boolean isEnabled = isEnabled(tabWrapper, idOrXpath);
			
			if (!isEnabled)
				FailureHelper.failTest("Tab '" + idOrXpath + "' is not disabled");
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}