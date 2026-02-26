package com.subex.automation.helpers.componentHelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class PopupElementHelper extends AcceptanceTest {
	
	private static String targetString = "popupId";
	
	private static String[] getLocators() throws Exception {
		try {
			String[] popupLocators = {"Popup_Panel", "Popup_Wrapper", "ConfirmationPopup"};
			
			return popupLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator() throws Exception {
		try {
			String locator = null;
			String[] popupLocators = getLocators();
			locator = LocatorHelper.getLocator(popupLocators, targetString, "window-scroll-panel");
			
			return locator;
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
				String[] popupLocators = getLocators();
				locator = LocatorHelper.getLocator(popupLocators, targetString, idOrXpath);
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement() throws Exception {
		try {
			String[] popupLocators = getLocators();
			WebElement element = ElementHelper.getElement(popupLocators);
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String idOrXpath) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (element == null) {
				String[] popupLocators = getLocators();
				element = ElementHelper.getElement(popupLocators, targetString, idOrXpath);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method asserts whether the confirmBox for delete is present or not
	 * @param key - delete / change / record-count
	 * @param count - No. of records for Record Count
	 */
	public static void assertConfirmBoxPresent( String key, String count ) throws Exception {
		try {
			HashMap<String, String> confirmationBoxes = new HashMap<String, String>();
			confirmationBoxes.put( "delete", "Are you sure you wish to delete the selected item?" );
			confirmationBoxes.put( "change", "Changes have been made. Are you sure you wish to lose these changes?" );
			confirmationBoxes.put("undelete", "Are you sure you wish to undelete the selected item?");
			confirmationBoxes.put( "deletes", "Are you sure you wish to delete the selected items?" );
			confirmationBoxes.put("undeletes", "Are you sure you wish to undelete the selected items?");
			if (key.equals("record-count") && !count.equals("")) {
				confirmationBoxes.put( "record-count", "Search query returned '" + count + "' rows." );
			}
			if (key.equals("case-count") && !count.equals(""))
				confirmationBoxes.put( "case-count", "Total number of groups matched : " + count);
	
			if(!PopupHelper.isPresent("Popup_Wrapper")) {
				FailureHelper.failTest("Confirm box of type " + key + " not found.");
			}
			
			String actual = LabelHelper.getText("Popup_Panel");
			if(!(actual.equals(confirmationBoxes.get( key )))) {
				if (key.equals("record-count")) {
					String[] actualCount = actual.split("'");
					FailureHelper.failTest("Expected count '" + count + "' not found in record count dialog. Actual count found is '" + actualCount[1] + "'.");
				}
				else if (key.equals("case-count")) {
					String[] actualCount = actual.split(":");
					FailureHelper.failTest("Expected number of groups '" + count + "' not found in case count dialog. Actual number found is '" + actualCount[1] + "'.");
				}
				else {
					FailureHelper.failTest("Confirm box of type " + key + " not found.");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method asserts whether the confirmBox for delete is present or not 
	 * @param key - delete / change / record-count
	 * @param count - No. of records for Record Count
	 */
	public static List<String> getPopupList(String count) throws Exception {
		try {
			List<String> popupList = new ArrayList<String>();
			popupList.add( "Any changes made will be discarded. Are you sure, you want to lose these changes?" );
			popupList.add( "Are you sure you wish to delete the selected item?" );
			popupList.add( "Are you sure you wish to delete the selected items?" );
			popupList.add( "Are you sure you wish to undelete the selected item?");
			popupList.add( "Are you sure you wish to undelete the selected items?");
			popupList.add( "Are you sure you want to Logout?");
			popupList.add("Search query returned no rows.");
			popupList.add("Search query returned " + count + " row.");
			popupList.add("Search query returned '" + count + "' rows");
			popupList.add("Do you wish to overwrite the last run of this measure request and regenerate its KPI results, Case items, and Reporting Table entries?");
			popupList.add("Measure Drill Down is a performance intensive operation. Do you want to continue?");
			popupList.add("Do you wish to overwrite the last run of this measure request and regenerate its KPI results, case items, and trending rows?");
			
			return popupList;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}