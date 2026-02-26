package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.componentHelpers.LinkElementHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class LinkHelper extends ComponentHelper {
	
	/**
	 * checks whether link is present or not
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public static boolean isPresent( String linkId ) throws Exception {
		try {
			String locator = LinkElementHelper.getLocator(linkId);
			
			if (locator == null) {
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
	
	public static boolean isPresent( String linkWrapper, String linkId ) throws Exception {
		try {
			String locator = LinkElementHelper.getLocator(linkWrapper, linkId);
			
			if (locator == null) {
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
	 * Clicks the link with text
	 * @param linkText
	 * @throws Exception 
	 */
	public static void click( String linkId ) throws Exception {
		try {
			linkId = GenericHelper.getORProperty(linkId);
			WebElement element = LinkElementHelper.getElement(linkId);
			
			if (element == null) {
				FailureHelper.failTest("Link '" + linkId + "' is not found.");
			}
			else
				MouseHelper.click(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click( String linkWrapper, String linkId ) throws Exception {
		try {
			linkWrapper = GenericHelper.getORProperty(linkWrapper);
			linkId = GenericHelper.getORProperty(linkId);
			WebElement element = LinkElementHelper.getElement(linkWrapper, linkId);
			
			if (element == null) {
				FailureHelper.failTest("Link '" + linkId + "' is not found.");
			}
			else
				MouseHelper.click(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * returns whether link is enabled or not
	 * @param fieldInputLocator
	 * @return
	 * @throws Exception 
	 */
	public static boolean isEnabled( String linkId ) throws Exception {
		try {
			linkId = GenericHelper.getORProperty(linkId);
			WebElement element = LinkElementHelper.getElement(linkId);
			
			if (element == null) {
				FailureHelper.failTest("Link '" + linkId + "' is not found.");
			}
			else {
				if((ElementHelper.getAttribute(element, "class").contains("roc-menu-item-disabled"))) {
					return false;
				}
				else
					return true;
			}
			
			return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isDisabled( String linkId ) throws Exception {
		try {
			linkId = GenericHelper.getORProperty(linkId);
			WebElement element = LinkElementHelper.getElement(linkId);
			
			if (element == null) {
				FailureHelper.failTest("Link '" + linkId + "' is not found.");
			}
			else {
				if(!(ElementHelper.getAttribute(element, "class").contains("roc-menu-item-disabled"))) {
					return false;
				}
				else
					return true;
			}
			
			return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isLinkEnabled( String linkId ) throws Exception {
		try {
			linkId = GenericHelper.getORProperty(linkId);
			WebElement element = LinkElementHelper.getElement(linkId);
			
			if (element == null) {
				return false;
			}
			else {
				if((ElementHelper.getAttribute(element, "class").contains("roc-menu-item-disabled"))) {
					return false;
				}
			}
			
			return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}