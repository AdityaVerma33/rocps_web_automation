package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class LocatorHelper extends AcceptanceTest {
	
	public static String getWrapperLocator(String wrapperId) throws Exception {
		try {
			String locator = null;
			
			if (wrapperId != null) {
				if (ElementHelper.isElementPresent(wrapperId)) {
						locator = wrapperId;
				}
				else if (!wrapperId.startsWith("/")) {
					if (ElementHelper.isElementPresent(or.getProperty("Wrapper_Locator").replace("wrapperId", wrapperId)))
						locator = or.getProperty("Wrapper_Locator").replace("wrapperId", wrapperId);
				}
			}
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String idOrXpath) throws Exception {
		try {
			String locator = getWrapperLocator(idOrXpath);
			
			if (locator == null) {
				locator = LabelElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = TextBoxElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = ComboBoxElementHelper.getValueLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = ButtonElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = ImageElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = LinkElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = TabElementHelper.getLocator(idOrXpath);
			}
			
			if (locator == null) {
				locator = TextAreaElementHelper.getLocator(idOrXpath);
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String wrapperId, String idOrXpath) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = getWrapperLocator(wrapperId);
			
			if (wrapperLocator != null) {
				if (idOrXpath.contains("/")) {
					if (ElementHelper.isElementPresent(idOrXpath))
						locator =  wrapperLocator + idOrXpath;
				}
				else {
					locator = getWrapperLocator(idOrXpath);
				}
			}
			else
				locator = getLocator(idOrXpath);
			
			if (locator == null) {
				locator = TextBoxElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			if (locator == null) {
				locator = ButtonElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			if (locator == null) {
				locator = ImageElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			if (locator == null) {
				locator = LinkElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			if (locator == null) {
				locator = TabElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			if (locator == null) {
				locator = TextAreaElementHelper.getLocator(wrapperId, idOrXpath);
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getIconLocator(String iconId) throws Exception {
		try {
			if (ElementHelper.isElementPresent(or.getProperty("Entity_Search_Icon_Div_ById").replace("entityId", iconId)))
				return or.getProperty("Entity_Search_Icon_Div_ById").replace("entityId", iconId);
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getIconLocator(String wrapperId, String iconId) throws Exception {
		try {
			String wrapper = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (ElementHelper.isElementPresent(wrapper + or.getProperty("Entity_Search_Icon_Div_ById").replace("entityId", iconId)))
				return wrapper + or.getProperty("Entity_Search_Icon_Div_ById").replace("entityId", iconId);
			else
				return null;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(WebElement element, String splitKey) throws Exception {
		try {
			String[] temp = element.toString().split(splitKey);
			String locator = null;
			
			for (int i = 1; i < temp.length; i++) {
				int length = temp[i].length();
				String tLocator = temp[i].substring(0, length-1);
				tLocator = tLocator.replace("]] ->", "").replace("]]", "]");
				
				String[] temp1 = tLocator.toString().split(" xpath: ");
				for (int j = 0; j < temp1.length; j++) {
					if (j == 0) {
						if (temp1[0].startsWith("//") || temp1[0].startsWith("/"))
							tLocator = temp1[j];
						else if (temp1[0].startsWith(".//") || temp1[0].startsWith("./"))
							tLocator = temp1[j].substring(1);
						else
							tLocator = "//*[@id='" + temp1[0] + "']";
					}
					else
						tLocator = tLocator + temp1[j];
				}
				
				if (i == 1)
					locator = tLocator;
				else
					locator = locator + tLocator;
			}
			
			if (locator != null) {
				return locator;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocatorForID(WebElement element) throws Exception {
		try {
			String locator = getLocator(element, "id: ");
			
			if (locator != null) {
				if (!locator.startsWith("//")) {
					String tagName = element.getTagName();
					locator = "//" + tagName + "[@id='" + locator + "']";
				}
				
				locator = locator.replace("./", "/");
//				int count = ElementHelper.getXpathCount(locator);
//				if (count > 1)
//					locator = locator + "[" + count + "]";
				return locator;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocatorForName(WebElement element) throws Exception {
		try {
			String[] temp = element.toString().split("name: ");
			String locator = null;
			
			for (int i = 1; i < temp.length; i++) {
				int length = temp[i].length();
				String tLocator = temp[i].substring(0, length-1);
				tLocator = tLocator.replace("]] ->", "").replace("]]", "]");
				
				if (i == 1)
					locator = tLocator;
				else
					locator = locator + tLocator;
			}
			
			if (locator != null) {
				locator = locator.replace("./", "/");
				int count = ElementHelper.getXpathCount(locator);
				if (count > 1)
					locator = locator + "[" + count + "]";
				return locator;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(WebElement element) throws Exception {
		try {
			if (element != null) {
				String locator = getLocatorForID(element);
							
				if (locator == null)
					locator = getLocatorForName(element);
				
				if (locator == null)
					locator = getLocator(element, "xpath: ");
				
				return locator;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String[] locators, String targetString, String replaceString) throws Exception {
		try {
			String locator = null;
			
			for (int i = 0; i < locators.length; i++) {
				String ckbLocator = or.getProperty(locators[i]).replace(targetString, replaceString);
				if (ElementHelper.isElementPresent(ckbLocator)) {
					int count = ElementHelper.getXpathCount(ckbLocator);
					if (count > 1)
						locator = ckbLocator + "[" + count + "]";
					else
						locator = ckbLocator;
					break;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator(String[] locators, String wrapperLocator, String targetString, String replaceString) throws Exception {
		try {
			String locator = null;
			
			for (int i = 0; i < locators.length; i++) {
				String ckbLocator = or.getProperty(locators[i]).replace(targetString, replaceString);
				if (ElementHelper.isElementPresent(wrapperLocator + ckbLocator)) {
					int count = ElementHelper.getXpathCount(wrapperLocator + ckbLocator);
					if (count > 1)
						locator = wrapperLocator + ckbLocator + "[" + count + "]";
					else
						locator = wrapperLocator + ckbLocator;
					break;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}