package com.subex.automation.helpers.component;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.CalendarElementHelper;
import com.subex.automation.helpers.componentHelpers.TextBoxElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class CalendarHelper extends ComponentHelper {
	
	public static boolean isPresent( String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
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
	
	public static boolean isPresent( String calendarWrapper, String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
			
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
	
	public static void setNow( String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ImageHelper.click("Calendar_Now");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setNow( String calendarWrapper, String calendarID ) throws Exception {
		try {
			calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ImageHelper.click("Calendar_Now");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setOnDate( String calendarID, String onDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(onDate)) {
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("On");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, onDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setOnDate( String calendarWrapper, String calendarID, String onDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(onDate)) {
				calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("On");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, onDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setAfterDate( String calendarID, String afterDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(afterDate)) {
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("After");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, afterDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setAfterDate( String calendarWrapper, String calendarID, String afterDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(afterDate)) {
				calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("After");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, afterDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setBeforeDate( String calendarID, String beforeDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(beforeDate)) {
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("Before");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, beforeDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setBeforeDate( String calendarWrapper, String calendarID, String beforeDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(beforeDate)) {
				calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("Before");
					
					String txtBoxLocator = or.getProperty("Calendar_From_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, beforeDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setBetweenDate( String calendarID, String fromDate, String toDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(fromDate) && ValidationHelper.isNotEmpty(toDate)) {
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("Between");
					
					TextBoxHelper.type("Calendar_From_TextBox", fromDate);
					String txtBoxLocator = or.getProperty("Calendar_To_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, toDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setBetweenDate( String calendarWrapper, String calendarID, String fromDate, String toDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(fromDate) && ValidationHelper.isNotEmpty(toDate)) {
				calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
				calendarID = GenericHelper.getORProperty(calendarID);
				WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
				
				if (element != null) {
					MouseHelper.click(element);
					ButtonHelper.click("Between");
					
					TextBoxHelper.type("Calendar_From_TextBox", fromDate);
					String txtBoxLocator = or.getProperty("Calendar_To_TextBox");
					WebElement txtBoxElement = TextBoxElementHelper.getElement(txtBoxLocator);
					TextBoxElementHelper.typeInBox(txtBoxElement, txtBoxLocator, toDate);
					ElementHelper.keyPress(txtBoxElement, Keys.ENTER);
				}
				else {
					FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setToday( String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Today");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setToday( String calendarWrapper, String calendarID ) throws Exception {
		try {
			calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Today");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setLast7Days( String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Last 7 Days");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setLast7Days( String calendarWrapper, String calendarID ) throws Exception {
		try {
			calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Last 7 Days");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setLast30Days( String calendarID ) throws Exception {
		try {
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Last 30 Days");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setLast30Days( String calendarWrapper, String calendarID ) throws Exception {
		try {
			calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
			calendarID = GenericHelper.getORProperty(calendarID);
			WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
			
			if (element != null) {
				MouseHelper.click(element);
				ButtonHelper.click("Last 30 Days");
			}
			else {
				FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setDate( String calendarID, String type, String fromDate, String toDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(type)) {
				switch (type) {
				case "On":
					setOnDate(calendarID, fromDate);
					break;
					
				case "After":
					setAfterDate(calendarID, fromDate);
					break;
					
				case "Before":
					setBeforeDate(calendarID, fromDate);
					break;
					
				case "Between":
					setBetweenDate(calendarID, fromDate, toDate);
					break;
					
				case "":
				case "Today":
					setToday(calendarID);
					break;
					
				case "Last 7 Days":
					setLast7Days(calendarID);
					break;
					
				case "Last 30 Days":
					setLast30Days(calendarID);
					break;
				
				case "na":
				case "NA":
					TextBoxHelper.type(calendarID, fromDate);
					break;
					
				default:
					calendarID = GenericHelper.getORProperty(calendarID);
					WebElement element = CalendarElementHelper.getElement(calendarID);
					
					if (element != null) {
						MouseHelper.click(element);
						ButtonHelper.click(type);
					}
					else {
						FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
					}
					break;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setDate( String calendarWrapper, String calendarID, String type, String fromDate, String toDate ) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(type)) {
				switch (type) {
				case "On":
					setOnDate(calendarWrapper, calendarID, fromDate);
					break;
					
				case "After":
					setAfterDate(calendarWrapper, calendarID, fromDate);
					break;
					
				case "Before":
					setBeforeDate(calendarWrapper, calendarID, fromDate);
					break;
					
				case "Between":
					setBetweenDate(calendarWrapper, calendarID, fromDate, toDate);
					break;
					
				case "Today":
					setToday(calendarWrapper, calendarID);
					break;
					
				case "Last 7 Days":
					setLast7Days(calendarWrapper, calendarID);
					break;
					
				case "Last 30 Days":
					setLast30Days(calendarWrapper, calendarID);
					break;
					
				case "":
					TextBoxHelper.type(calendarWrapper, calendarID, fromDate);
					break;
					
				default:
					calendarWrapper = GenericHelper.getORProperty(calendarWrapper);
					calendarID = GenericHelper.getORProperty(calendarID);
					WebElement element = CalendarElementHelper.getElement(calendarWrapper, calendarID);
					
					if (element != null) {
						MouseHelper.click(element);
						ButtonHelper.click(type);
					}
					else {
						FailureHelper.failTest("Calendar '" + calendarID + "' not found.");
					}
					break;
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}