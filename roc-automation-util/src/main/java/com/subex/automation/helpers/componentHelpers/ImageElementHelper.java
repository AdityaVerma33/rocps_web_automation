package com.subex.automation.helpers.componentHelpers;

import java.util.HashMap;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class ImageElementHelper extends AcceptanceTest {
	
	private static final String targetString = "imageId";
	
	private static String[] getLocators() throws Exception {
		try {
			String[] imageLocators = {"Image_ById", "Image_BySrc", "Image_ByDivId", "Image_ByTableId"};
			
			return imageLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static HashMap<String, String> getIcons() throws Exception {
		try {
			HashMap<String, String> icons = new HashMap<String, String>();
			icons.put( "warning", "warning.png" );
			icons.put( "error", "error.png" );
			icons.put( "alert", "alert.png" );
			icons.put( "run", "run.png" );
			icons.put( "running", "run.png" );
			icons.put( "stop", "stop.png" );
			icons.put( "stopped", "stop.png" );
			icons.put( "pause", "pause.png" );
			icons.put( "paused", "pause.png" );
			icons.put( "information", "information.png" );
			icons.put( "forbidden", "forbidden.png" );
			icons.put( "refresh", "refresh.png" );
			icons.put( "delete", "delete.png" );
			
			return icons;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getIcon(String key) throws Exception {
		try {
			HashMap<String, String> icons = getIcons();
			String icon = icons.get(key);
			
			return icon;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String idOrXpath) throws Exception {
		try {
			WebElement element = getElement(idOrXpath);
			
			if (element != null) {
				String locator = LocatorHelper.getLocator(element);
				return locator;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String wrapperId, String idOrXpath) throws Exception {
		try {
			WebElement element = getElement(wrapperId, idOrXpath);
			
			if (element != null) {
				String locator = LocatorHelper.getLocator(element);
				return locator;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String idOrXpath) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (element == null && !idOrXpath.startsWith("/")) {
				String[] locators = getLocators();
				element = ElementHelper.getElement(locators, targetString, idOrXpath);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement(String wrapperId, String idOrXpath) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(wrapperId, idOrXpath);
			
			if (element == null && !idOrXpath.startsWith("/")) {
				String[] locators = getLocators();
				element = ElementHelper.getElement(locators, element, targetString, idOrXpath);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getIconElement(String idOrXpath, String key) throws Exception {
		try {
			String icon = getIcon(key);
			if (icon == null)
				icon = key;
			
			WebElement parent = ElementHelper.getElement(idOrXpath);
			String[] locators = getLocators();
			
			if (parent != null) {
				WebElement element = ElementHelper.getElement(locators, parent, targetString, icon);
				return element;
			}
			else
				FailureHelper.failTest("Icon wrapper '" + idOrXpath + "' not found.");
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getIconElement(String wrapperId, String idOrXpath, String key) throws Exception {
		try {
			String icon = getIcon(key);
			if (icon == null)
				icon = key;
			
			WebElement parent = ElementHelper.getElement(wrapperId, idOrXpath);
			String[] locators = getLocators();
			
			if (parent != null) {
				WebElement element = ElementHelper.getElement(locators, parent, targetString, icon);
				return element;
			}
			else
				FailureHelper.failTest("Icon wrapper '" + idOrXpath + "' not found.");
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}