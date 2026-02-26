package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.PopupElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class PopupHelper extends ComponentHelper {
	
	/**
	 * This method is used to check if the Confirmation popup is present in GUI.
	 * @return 
	 * @throws Exception
	 */
	public static boolean isPresent() throws Exception {
		try {
			WebElement element = PopupElementHelper.getElement();
			
			if (element == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if the Confirmation popup is present in GUI.
	 * @param popupId - Confirmation popup div or table id
	 * @return 
	 * @throws Exception
	 */
	public static boolean isPresent(String popupId) throws Exception {
		try {
			popupId = GenericHelper.getORProperty(popupId);
			WebElement element = PopupElementHelper.getElement(popupId);
			
			if (element == null)
				return false;
			else
				return true;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check if Confirmation popup with specified text is present in GUI.
	 * @param message - Confirmation popup message
	 * @return 
	 * @throws Exception
	 */
	public static boolean isTextPresent(String message) throws Exception {
		try {
			message = GenericHelper.getORProperty(message);
			WebElement element = PopupElementHelper.getElement();
			
			if (element != null) {
				String popupMessage = ElementHelper.getText(element);
				if (popupMessage.equals(message) || popupMessage.contains(message))
					return true;
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTextPresent(String popupId, String message) throws Exception {
		try {
			popupId = GenericHelper.getORProperty(popupId);
			message = GenericHelper.getORProperty(message);
			WebElement element = PopupElementHelper.getElement(popupId);
			
			if (element != null) {
				String popupMessage = ElementHelper.getText(element);
				if (popupMessage.equals(message) || popupMessage.contains(message))
					return true;
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForPopup(int waitInSecs) throws Exception {
		try {
			String popupId = GenericHelper.getORProperty("Popup_Wrapper");
			GenericHelper.waitForElement(popupId, waitInSecs);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void waitForPopup(String popupId, int waitInSecs) throws Exception {
		try {
			popupId = GenericHelper.getORProperty(popupId);
			GenericHelper.waitForElement(popupId, waitInSecs);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}