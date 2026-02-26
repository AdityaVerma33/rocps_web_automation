package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CalendarElementHelper extends AcceptanceTest {
	
	private static String[] getLocators() throws Exception {
		try {
			String[] textBoxLocators = {"Calendar_ByDiv", "Calendar_ByImg"};
			
			return textBoxLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath ) throws Exception {
		try {
			WebElement calendar = ElementHelper.getElement(idOrXpath);
			WebElement element = null;
			
			if (calendar != null) {
				String[] calendarLocators = getLocators();
				element = ElementHelper.getElement(calendarLocators, calendar);
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
			WebElement calendar = ElementHelper.getElement(wrapperId, idOrXpath);
			WebElement element = null;
			
			if (calendar != null) {
				String[] calendarLocators = getLocators();
				element = ElementHelper.getElement(calendarLocators, calendar);
			}
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}