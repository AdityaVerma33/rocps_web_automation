package com.subex.automation.helpers.componentHelpers;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class DNAElementHelper extends AcceptanceTest {
	
//	private static final String replaceString = "treeText";
//	
//	private static void getPaths(String canvasId) {
//		try {
//			String canvasLocator = CanvasElementHelper.getLocator(canvasId);
//			String pathLocator = canvasLocator + or.getProperty("DNA_Path");
//			
//			String[] pathAttributes = ElementHelper.getAttributes(pathLocator, "d");
//			
//		} catch (Exception e) {
//			FailureHelper.setErrorMessage(e);
//			throw e;
//		}
//	}
	
	private static String getChildNode(String canvasLocator, String nodeText) throws Exception {
		try {
			String nodeLocator = or.getProperty("DNA_ChildNode");
			String truncatedText = CanvasElementHelper.getTruncatedText(nodeText);
			String locator = null;
			
			if (ElementHelper.isElementPresent(canvasLocator + nodeLocator.replace("childTreeText", nodeText)))
				locator = canvasLocator + nodeLocator.replace("childTreeText", nodeText);
			else if (ElementHelper.isElementPresent(canvasLocator + nodeLocator.replace("childTreeText", truncatedText)))
				locator = canvasLocator + nodeLocator.replace("childTreeText", truncatedText);
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDNALocator( String canvasLocator, String parentIdOrXpath, String childIdOrXpath ) throws Exception {
		try {
			String parentLocator = CanvasElementHelper.getNode(canvasLocator, parentIdOrXpath);
			String locator = null;
			
			if (parentLocator != null) {
				locator = getChildNode(parentLocator, childIdOrXpath);
			}
			else
				locator = CanvasElementHelper.getNode(canvasLocator, childIdOrXpath);
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getDNALocator( String canvasLocator, String parentIdOrXpath, String child1IdOrXpath, String child2IdOrXpath ) throws Exception {
		try {
			String parentLocator = CanvasElementHelper.getNode(canvasLocator, parentIdOrXpath);
			String locator = null;
			
			if (parentLocator != null) {
				locator = getChildNode(parentLocator, child1IdOrXpath);
				List<WebElement> elements = ElementHelper.getElements(locator);
				if (elements != null && elements.size() > 0) {
					int lastIndex = elements.size() - 1;
					WebElement element = elements.get(lastIndex);
					locator = LocatorHelper.getLocator(element);
				}
			}
			else
				locator = CanvasElementHelper.getNode(canvasLocator, child1IdOrXpath);
			
			if (locator != null) {
				String nodeLocator = or.getProperty("DNA_FinalNode").replace("childTreeText", child2IdOrXpath);
				if (ElementHelper.isElementPresent(locator + nodeLocator))
					locator = locator + nodeLocator;
				
				List<WebElement> elements = ElementHelper.getElements(locator);
				if (elements != null && elements.size() > 0) {
					int lastIndex = elements.size() - 1;
					WebElement element = elements.get(lastIndex);
					locator = LocatorHelper.getLocator(element);
				}
			}
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String wrapperId, String canvasId, String parentNodeText, String childNodeText) throws Exception {
		try {
			String canvasLocator = CanvasElementHelper.getLocator(wrapperId, canvasId);
			
			if (canvasLocator != null) {
				String locator = getDNALocator(canvasLocator, parentNodeText, childNodeText);
				
				if (locator != null) {
					WebElement element = ElementHelper.getElement(locator);
					return element;
				}
				else
					return null;
			}
			else {
				canvasLocator = CanvasElementHelper.getLocator(wrapperId);
				String locator = getDNALocator(canvasLocator, canvasId, parentNodeText, childNodeText);
				
				if (locator != null) {
					WebElement element = ElementHelper.getElement(locator);
					return element;
				}
				else
					return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String wrapperId, String canvasId, String firstParentText, String nextParentText, String childText) throws Exception {
		try {
			String canvasLocator = CanvasElementHelper.getLocator(wrapperId, canvasId);
			String locator = getDNALocator(canvasLocator, firstParentText, nextParentText, childText);
			
			if (locator != null) {
				WebElement element = ElementHelper.getElement(locator);
				return element;
			}
			else
				return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}