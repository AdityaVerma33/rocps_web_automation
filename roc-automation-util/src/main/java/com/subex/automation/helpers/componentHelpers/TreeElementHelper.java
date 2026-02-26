package com.subex.automation.helpers.componentHelpers;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

public class TreeElementHelper extends AcceptanceTest {
	
	private static final String treeTargetString = "treeId";
	private static final String nodeTargetString = "treeText";
	private static final String childTargetString = "child";
	
	private static String[] getTreeLocators() throws Exception {
		try {
			String[] nodeLocators = {"Tree_ByDiv"};
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getNodeLocators() throws Exception {
		try {
			String[] nodeLocators = {"Tree_ByText", "Tree_ByStartsWithText", "Tree_ByContainsText"};
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getChildLocators() throws Exception {
		try {
			String[] nodeLocators = {"Tree_Child_ByText", "Tree_Child_ByStartsWithText", "Tree_Child_ByContainsText"};
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String[] getExpandLocators() throws Exception {
		try {
			String[] nodeLocators = {"Tree_Expand_ByImg1", "Tree_Expand_ByImg2"};
			
			return nodeLocators;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTreeLocator( String idOrXpath ) throws Exception {
		try {
			String locator = null;
			if (idOrXpath.contains("/")) {
				if (ElementHelper.isElementPresent(idOrXpath))
					locator = idOrXpath;
			}
			else {
				String[] locators = getTreeLocators();
				locator = LocatorHelper.getLocator(locators, treeTargetString, idOrXpath);
			}
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTreeLocator( String wrapperId, String idOrXpath ) throws Exception {
		try {
			String locator = null;
			String wrapperLocator = LocatorHelper.getWrapperLocator(wrapperId);
			
			if (wrapperLocator != null) {
				if (idOrXpath.startsWith("/")) {
					if (ElementHelper.isElementPresent(wrapperLocator + idOrXpath))
						locator = wrapperLocator + idOrXpath;
				}
				else {
					String[] locators = getTreeLocators();
					locator = LocatorHelper.getLocator(locators, wrapperLocator, treeTargetString, idOrXpath);
				}
			}
			else
				locator = getTreeLocator(idOrXpath);
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTreeLocator( String idOrXpath, String parentId, String childId ) throws Exception {
		try {
			String locator = getLocator(idOrXpath, parentId);
			String childLocator = null;
			
			if (locator != null) {
				String[] childLocators = getChildLocators();
				childLocator = LocatorHelper.getLocator(childLocators, locator, childTargetString, childId);
			}
			
			return childLocator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getTreeLocator( String wrapperId, String idOrXpath, String parentId, String childId ) throws Exception {
		try {
			String locator = getLocator(wrapperId, idOrXpath, parentId);
			String childLocator = null;
			
			if (locator != null) {
				String[] childLocators = getChildLocators();
				childLocator = LocatorHelper.getLocator(childLocators, locator, childTargetString, childId);
			}
			
			return childLocator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getTreeElement( String idOrXpath ) throws Exception {
		try {
			WebElement element = ElementHelper.getElement(idOrXpath);
			
			if (!idOrXpath.contains("/") && element == null) {
				String[] locators = getTreeLocators();
				element = ElementHelper.getElement(locators, treeTargetString, idOrXpath);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getTreeElement( String wrapperId, String idOrXpath ) throws Exception {
		try {
			WebElement wrapperElement = ElementHelper.getWrapperElement(wrapperId);
			WebElement element = null;
			
			if (wrapperElement != null) {
				element = ElementHelper.getElement(wrapperElement, idOrXpath);
				
				if (!idOrXpath.startsWith("/") && element == null) {
					String[] locators = getTreeLocators();
					element = ElementHelper.getElement(locators, wrapperElement, treeTargetString, idOrXpath);
				}
			}
			else
				element = getTreeElement(idOrXpath);
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getTreeElement( String idOrXpath, String parentId, String childId ) throws Exception {
		try {
			WebElement parentElement = getElement(idOrXpath, parentId);
			WebElement element = null;
			
			if (parentElement != null) {
				String[] locators = getChildLocators();
				element = ElementHelper.getElement(locators, parentElement, childTargetString, childId);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getTreeElement( String wrapperId, String idOrXpath, String parentId, String childId ) throws Exception {
		try {
			WebElement parentElement = getElement(wrapperId, idOrXpath, parentId);
			WebElement element = null;
			
			if (parentElement != null) {
				String[] locators = getChildLocators();
				element = ElementHelper.getElement(locators, parentElement, childTargetString, childId);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getFinalLocator(String treeId, String locator, String treeText) throws Exception {
		try {
//			String[] possibleText = {treeText, treeText.toUpperCase()};
			String[] nodeLocators = getNodeLocators();
//			String parentCSSQuery = "div#" + treeId + ",table#" + treeId;
//			JSoupHelper jSoup = new JSoupHelper();
//			Elements parentElement = jSoup.getElements(parentCSSQuery);
//			
//			for (int i = 0; i < possibleText.length; i++) {
//				for (int j = 0; j < nodeLocators.length; j++) {
//					Elements elements = jSoup.getElements(parentElement, nodeLocators[j][0].replace(nodeTargetString, treeText));
//					if (elements != null && elements.size() > 0) {
//						locator = or.getProperty(nodeLocators[j][1]).replace(nodeTargetString, possibleText[i]);
//						break;
//					}
//				}
//			}
			
			WebElement tree = getTreeElement(treeId);
			if (tree != null) {
				WebElement element = ElementHelper.getElement(nodeLocators, tree, nodeTargetString, treeText);
				
				if (element != null)
					return LocatorHelper.getLocator(element);
				else
					return null;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static String getFinalLocator(String wrapperId, String treeId, String locator, String treeText) throws Exception {
		try {
//			String[] possibleText = {treeText, treeText.toUpperCase()};
			String[] nodeLocators = getNodeLocators();
//			String wrapperCSSQuery = "div#" + wrapperId + ",table#" + wrapperId;
//			String parentCSSQuery = "div#" + treeId + ",table#" + treeId;
//			JSoupHelper jSoup = new JSoupHelper();
//			Elements parentElement = jSoup.getElements(wrapperCSSQuery, parentCSSQuery);
//			
//			for (int i = 0; i < possibleText.length; i++) {
//				for (int j = 0; j < nodeLocators.length; j++) {
//					Elements elements = jSoup.getElements(parentElement, nodeLocators[j][0].replace(nodeTargetString, treeText));
//					if (elements != null && elements.size() > 0) {
//						locator = or.getProperty(nodeLocators[j][1]).replace(nodeTargetString, possibleText[i]);
//						break;
//					}
//				}
//			}

			WebElement tree = getTreeElement(wrapperId, treeId);
			if (tree != null) {
				WebElement element = ElementHelper.getElement(nodeLocators, tree, nodeTargetString, treeText);
				
				if (element != null)
					return LocatorHelper.getLocator(element);
				else
					return null;
			}
			
			return null;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator( String idOrXpath, String treeText ) throws Exception {
		try {
			String locator = getTreeLocator(idOrXpath);
			
			if (locator != null) {
				locator = getFinalLocator(idOrXpath, locator, treeText);
			}
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getLocator( String wrapperId, String idOrXpath, String treeText ) throws Exception {
		try {
			String locator = getTreeLocator(wrapperId, idOrXpath, treeText);
			
			if (locator == null) {
				locator = getFinalLocator(wrapperId, idOrXpath, locator, treeText);
			}
			
			return locator;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static WebElement getFinalElement(WebElement treeElement, String treeText) throws Exception {
		try {
			WebElement element = null;
			
			if (treeText.startsWith("//"))
				element = ElementHelper.getElement(treeElement, treeText);
			else {
				String[] possibleText = {treeText, treeText.toUpperCase()};
				String[] nodeLocators = getNodeLocators();
				
				for (int i = 0; i < possibleText.length; i++) {
					element = ElementHelper.getElement(nodeLocators, treeElement, nodeTargetString, possibleText[i]);
					if (element != null)
						break;
				}
			}
			
			return element;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String idOrXpath, String treeText ) throws Exception {
		try {
			WebElement element = getTreeElement(idOrXpath);
			
			if (element != null) {
				element = getFinalElement(element, treeText);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getElement( String wrapperId, String idOrXpath, String treeText ) throws Exception {
		try {
			WebElement element = getTreeElement(wrapperId, idOrXpath);
			
			if (element != null) {
				element = getFinalElement(element, treeText);
			}
			else {
				element = getTreeElement(wrapperId, idOrXpath, treeText);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getExpandElement( String treeId, String treeText ) throws Exception {
		try {
			WebElement parentElement = getElement(treeId, treeText);
			WebElement element = null;
			
			if (parentElement != null) {
				String[] locators = getExpandLocators();
				element = ElementHelper.getElement(locators, parentElement);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static WebElement getExpandElement( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			WebElement parentElement = getElement(treeWrapper, treeId, treeText);
			WebElement element = null;
			
			if (parentElement != null) {
				String[] locators = getExpandLocators();
				element = ElementHelper.getElement(locators, parentElement);
			}
			
			return element;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] isExpanded(WebElement element) throws Exception {
		try {
			String isExpanded = "false";
			String locator = LocatorHelper.getLocator(element);
			String[] expandLocators = {"Tree_ExpandCollapse_Status", "EditableTree_ExpandCollapse_Status"};
			String[] expandAttributes = {"aria-expanded", "aria-hidden"};
			
			WebElement expandElement = null;
			
			for (int i = 0; i < expandLocators.length; i++) {
				expandElement = ElementHelper.getElement(locator + or.getProperty(expandLocators[i]));
				
				if (expandElement != null) {
					for (int j = 0; j < expandAttributes.length; j++) {
						String expandAttribue = ElementHelper.getAttribute(expandElement, expandAttributes[j]);
						if (expandAttribue != null && ValidationHelper.isTrue(expandAttribue)) {
							isExpanded = "true";
							break;
						}
					}
				}
			}
			
			return new Object[] {expandElement, isExpanded};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTreeGridExpanded(WebElement element) throws Exception {
		try {
			boolean isExpanded = false;
			String src = ElementHelper.getAttribute(element, "src");
			
			if (src != null && src.contains("minus.png")) {
				isExpanded = true;
			}
			
			return isExpanded;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static Object[] isChecked(WebElement element) throws Exception {
		try {
			String isChecked = "false";
			WebElement checkBoxElement = ElementHelper.getElement(element, "EditableTree_CheckBox_Checked");
			
			if (checkBoxElement != null) {
				isChecked = "true";
			}
			else {
				checkBoxElement = ElementHelper.getElement(element, "EditableTree_CheckBox_Unchecked");
			}
			
			return new Object[] {checkBoxElement, isChecked};
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String[] getValues( WebElement element ) throws Exception {
		try {
			List<WebElement> treeElements = ElementHelper.getElements(element, "Tree_Item");
			String[] values = new String[treeElements.size()];
			
			for (int i = 0; i < treeElements.size(); i++) {
				values[i] = ElementHelper.getText(treeElements.get(i));
			}
			
			return values;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}