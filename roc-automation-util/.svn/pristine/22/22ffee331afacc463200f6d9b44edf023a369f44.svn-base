package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class CanvasElementHelper extends AcceptanceTest {
	
	private static final String targetString = "nodeText";
	
	private static String[] getNodeLocators() throws Exception {
		try {
			String[] nodeLocators = {"Node_ByText"};
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getNodePositionLocators(String targetString, String replaceString) throws Exception {
		try {
			String[] nodeLocators = {"Node_ByPosition"};
			
			for (int i = 0; i < nodeLocators.length; i++) 
				nodeLocators[i] = nodeLocators[i].replace(targetString, replaceString);
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator( String idOrXpath ) throws Exception {
		try {
			String locator = null;
			
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				String divId = or.getProperty("Canvas_ByDivID").replace("canvasId", idOrXpath);
				String tableId = or.getProperty("Canvas_ByTableID").replace("canvasId", idOrXpath);
				
				if (ElementHelper.isElementPresent(divId))
					locator = divId;
				else if (ElementHelper.isElementPresent(tableId))
					locator = tableId;
			}
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator( String wrapperId, String idOrXpath ) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (wrapperLocator != null) {
				if (idOrXpath.startsWith("/")) {
					if (ElementHelper.isElementPresent(wrapperLocator + idOrXpath))
						locator = wrapperLocator + idOrXpath;
				}
				else {
					String divId = or.getProperty("Canvas_ByDivID").replace("canvasId", idOrXpath);
					String tableId = or.getProperty("Canvas_ByTableID").replace("canvasId", idOrXpath);
					
					if (ElementHelper.isElementPresent(wrapperLocator + divId))
						locator = wrapperLocator + divId;
					else if (ElementHelper.isElementPresent(wrapperLocator + tableId))
						locator = wrapperLocator + tableId;
				}
			}
			else
				locator = getLocator(idOrXpath);
			
			return locator;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(wrapperId, idOrXpath);
			
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getTruncatedText(String nodeText) throws Exception {
		try {
			String truncatedText = nodeText;
			if (nodeText.length() >= 10)
				truncatedText = nodeText.substring(0, 7) + "...";
			
			return truncatedText;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	static String getNode(String canvasLocator, String nodeText) throws Exception {
		try {
			String truncatedText = getTruncatedText(nodeText);
			String locator = null;
			String[] nodeLocators = getNodeLocators();
			
			for (int i = 0; i < nodeLocators.length; i++) {
				String nodeLocator1 = or.getProperty(nodeLocators[i]).replace(targetString, nodeText);
				String nodeLocator2 = or.getProperty(nodeLocators[i]).replace(targetString, truncatedText);
				if (ElementHelper.isElementPresent(canvasLocator + nodeLocator1)) {
					locator = canvasLocator + nodeLocator1;
					break;
				}
				else if (ElementHelper.isElementPresent(canvasLocator + nodeLocator2)) {
					locator = canvasLocator + nodeLocator2;
					break;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNodeLocator(String canvasId, String nodeText) throws Exception {
		try {
			String canvasLocator = getLocator(canvasId);
			
			if (canvasLocator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return null;
			}
			else {
				String locator = getNode(canvasLocator, nodeText);
				return locator;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNodeLocator(String wrapperId, String canvasId, String nodeText) throws Exception {
		try {
			String canvasLocator = getLocator(wrapperId, canvasId);
			
			if (canvasLocator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return null;
			}
			else {
				String locator = getNode(canvasLocator, nodeText);
				return locator;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getNodePosition(String canvasLocator, String nodeText, int positionIndex) throws Exception {
		try {
			String truncatedText = getTruncatedText(nodeText);
			String index = String.valueOf(positionIndex);
			String locator = null;
			String[] nodeLocators = getNodePositionLocators("positionIndex", index);
			
			for (int i = 0; i < nodeLocators.length; i++) {
				String nodeLocator1 = or.getProperty(nodeLocators[i]).replace(targetString, nodeText).replace("positionIndex", index);
				String nodeLocator2 = or.getProperty(nodeLocators[i]).replace(targetString, truncatedText).replace("positionIndex", index);
				if (ElementHelper.isElementPresent(canvasLocator + nodeLocator1)) {
					locator = canvasLocator + nodeLocator1;
					break;
				}
				else if (ElementHelper.isElementPresent(canvasLocator + nodeLocator2)) {
					locator = canvasLocator + nodeLocator2;
					break;
				}
			}
			
			return locator;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNodeLocator(String canvasId, String nodeText, int positionIndex) throws Exception {
		try {
			String canvasLocator = getLocator(canvasId);
			
			if (canvasLocator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return null;
			}
			else {
				String locator = getNodePosition(canvasLocator, nodeText, positionIndex);
				return locator;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNodeLocator(String wrapperId, String canvasId, String nodeText, int positionIndex) throws Exception {
		try {
			String canvasLocator = getLocator(wrapperId, canvasId);
			
			if (canvasLocator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return null;
			}
			else {
				String locator = getNodePosition(canvasLocator, nodeText, positionIndex);
				return locator;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String canvasId, String nodeText) throws Exception {
		try {
			WebElement canvas = getElement(canvasId);
			WebElement element = null;
			
			if (canvas == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
			}
			else {
				String[] nodeLocators = getNodeLocators();
				element = ElementHelper.getElement(nodeLocators, canvas, targetString, nodeText);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String wrapperId, String canvasId, String nodeText) throws Exception {
		try {
			WebElement canvas = getElement(wrapperId, canvasId);
			WebElement element = null;
			
			if (canvas == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
			}
			else {
				String[] nodeLocators = getNodeLocators();
				element = ElementHelper.getElement(nodeLocators, canvas, targetString, nodeText);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(WebElement canvas, String nodeText) throws Exception {
		try {
			String[] nodeLocators = getNodeLocators();
			WebElement element = ElementHelper.getElement(nodeLocators, canvas, targetString, nodeText);
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String canvasId, int nodeIndex) throws Exception {
		try {
			WebElement canvas = getElement(canvasId);
			WebElement element = null;
			
			if (canvas == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
			}
			else {
				String[] nodeLocators = {"Node_ByPosition1"};
				element = ElementHelper.getElement(nodeLocators, canvas, "positionIndex", String.valueOf(nodeIndex));
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String canvasId, String nodeText, int nodeIndex) throws Exception {
		try {
			WebElement canvas = getElement(canvasId);
			WebElement element = null;
			
			if (canvas == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
			}
			else {
				String[] nodeLocators = getNodePositionLocators("positionIndex", String.valueOf(nodeIndex));
				element = ElementHelper.getElement(nodeLocators, canvas, targetString, nodeText);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getNodeElement(String wrapperId, String canvasId, String nodeText, int nodeIndex) throws Exception {
		try {
			WebElement canvas = getElement(wrapperId, canvasId);
			WebElement element = null;
			
			if (canvas == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
			}
			else {
				String[] nodeLocators = getNodePositionLocators("positionIndex", String.valueOf(nodeIndex));
				element = ElementHelper.getElement(nodeLocators, canvas, targetString, nodeText);
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNewNodeLocator(String canvasId) throws Exception {
		try {
			String locator = getLocator(canvasId);
			
			if (locator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return null;
			}
			else {
				String nodeLocator = or.getProperty("Node_Size");
				int nodes = ElementHelper.getXpathCount(locator + nodeLocator);
				if (nodes > 0)
					return locator + nodeLocator + "[" + nodes +"]";
				else
					return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static int getNodeCount(String canvasId) throws Exception {
		try {
			String locator = getLocator(canvasId);
			
			if (locator == null) {
				FailureHelper.failTest("Canvas with id '" + canvasId + "' not found");
				return 0;
			}
			else {
				return ElementHelper.getXpathCount(locator + or.getProperty("Node_Size"));
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getNodeText(String canvasId, int positionIndex) throws Exception {
		try {
			String canvasLocator = getLocator(canvasId);
			String nodeTextLocator =  canvasLocator + or.getProperty("DNA_Node_Text").replace("positionIndex", String.valueOf(positionIndex));
			
			return LabelHelper.getText(nodeTextLocator);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickNode(String canvasId, String nodeText, int positionIndex) throws Exception {
		try {
			WebElement element = getNodeElement(canvasId, nodeText, positionIndex);
			
			if (element == null)
				FailureHelper.failTest("Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'");
			else
				MouseHelper.click(element);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void resizeNode(String canvasId, String nodeText, int xOffset, int yOffset) throws Exception {
		try {
			WebElement canvas = getElement(canvasId);
			WebElement element = getNodeElement(canvas, nodeText);
			
			if (element == null)
				FailureHelper.failTest("Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'");
			else {
				MouseHelper.click(element);
				
				WebElement rightBottomElement = ElementHelper.getElement(canvas, "Canvas_RightBottom_Corner");
				ElementHelper.dragAndDrop(rightBottomElement, xOffset, yOffset);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void dragAndDrop(String canvasId, String nodeText, String dragTo) throws Exception {
		try {
			WebElement element = getNodeElement(canvasId, nodeText);
			
			if (element == null)
				FailureHelper.failTest("Node with text '" + nodeText + "' is not present in the canvas '" + canvasId + "'");
			else {
				MouseHelper.click(element);
				
				int[] offsets = getXYOffset(dragTo);
				int xOffset = offsets[0];
				int yOffset = offsets[1];
				ElementHelper.dragAndDrop(element, xOffset, yOffset);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static int[] getXYOffset(String dragTo) throws Exception {
		try {
			int xOffset = 0;
			int yOffset = 0;
			
			switch (dragTo.toLowerCase()) {
			case "rigth":
			case "right":
				xOffset = 50;
				yOffset = -20;
				break;
				
			case "left":
				xOffset = -20;
				yOffset = -50;
				break;
				
			case "top":
			case "up":
				yOffset = -30;
				xOffset = -20;
				break;
				
			case "bottom":
			case "down":
				yOffset = 50;
				xOffset = -20;
				break;
				
			default:
				break;
			}
			
			return new int[] {xOffset, yOffset};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean dragToPositions(String dragTo) throws Exception {
		try {
		String[] positions = {"rigth", "right", "left", "top", "up", "bottom", "down"};
		
		int index = StringHelper.searchArray(positions, dragTo.toLowerCase());
		if (index >= 0)
			return true;
		else
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}