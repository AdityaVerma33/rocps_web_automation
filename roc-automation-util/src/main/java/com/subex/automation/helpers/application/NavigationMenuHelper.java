package com.subex.automation.helpers.application;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.CanvasElementHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.automation.helpers.util.JSoupHelper;

public class NavigationMenuHelper extends AcceptanceTest {
	
	public void navigateToOptions(String screenName) throws Exception {
		try {
			LoginHelper login = new LoginHelper();
			login.login();
			
			MouseHelper.click("Options_Icon");
			if (ElementHelper.isElementPresent("Options_Menu"))
				MouseHelper.click("Options_Icon");
			GenericHelper.waitForElement("Options_Menu", detailScreenWaitSec);
			String screenLocator = or.getProperty("Options_Options").replace("screenName", screenName);
		    MouseHelper.click(screenLocator);
		    GenericHelper.waitForLoadmask(searchScreenWaitSec);
		    GenericHelper.waitForAJAXReady(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean navigationMenu(String screenName, boolean isROCView) throws Exception {
		try {
			String selectionName = screenName.replace("...", "");
			if (selectionName.length() > 32)
				selectionName = selectionName.substring(0, 29) + "...";
			
			if (isROCView && !screenName.equals("Visualizer")) {
				isROCView = false;
				driver.switchTo().defaultContent();
			}

			LoginHelper login = new LoginHelper();
			login.login();
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
			TextBoxHelper.type( "ScreenSelect_Textbox", selectionName );
			Thread.sleep(500);
			
			if (ElementHelper.getXpathCount("NavigationMenu_Items") <= 8) {
				if (ElementHelper.isElementPresent("NavigationMenu_Scroll"))
					ElementHelper.scrollOneLevelDown("ScreenSelect_Textbox", 1);
			}
			
			String selectScreen = null;
			String locator = or.getProperty("ScreenSelect_Xpath");
			
			if (ElementHelper.isElementPresent(locator.replace("ScreenName", screenName)))
				selectScreen = locator.replace("ScreenName", screenName);
			else if (ElementHelper.isElementPresent(locator.replace("ScreenName", screenName + "s")))
				selectScreen = locator.replace("ScreenName", screenName + "s");
			else {
				WebElement wrapper = ElementHelper.getElement("ScreenSelect_Wrapper");
				
				if (wrapper != null) {
					selectionName = ElementHelper.getText(wrapper);
					screenName = selectionName;
					selectScreen = locator.replace("ScreenName", selectionName);
				}
				else
					FailureHelper.failTest("The screen name '" + screenName + "' specified is not found in Navigation Menu.");
			}
			
			MouseHelper.click( selectScreen );
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			// This code is to click Cancel Confirmation dialog. 
			if (ButtonHelper.isPresent("DiscardButton")) {
				ButtonHelper.click("DiscardButton");
			}
			
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			return isROCView;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void exitNavigationMenu() throws Exception {
		try {
			if (ElementHelper.isElementPresent("MegaMenu_Wrapper") || ElementHelper.isElementPresent("NavigationMenu_Wrapper")) {
//				ElementHelper.pressEscape("NavigationMenu");
				ButtonHelper.click("NavigationMenu");
			}
			else {
				List<WebElement> navigationPanels = ElementHelper.getElements("NavigationMenu_EmptySpace");
				int clickIndex = navigationPanels.size() - 1;
				MouseHelper.click(navigationPanels.get(clickIndex));
				
				if (ElementHelper.isElementPresent("NavigationMenu_EmptySpace")) {
//					ElementHelper.pressEscape("NavigationMenu");
					ButtonHelper.click("NavigationMenu");
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getScreenNames() throws Exception {
		try {
			ButtonHelper.click("NavigationMenu");
			String[] screenNames;
			
			if (ElementHelper.isElementPresent("MegaMenu_Wrapper")) {
				screenNames = getMegaMenuScreens();
			}
			else {
				screenNames = getNavigationMenuScreens();
			}

			exitNavigationMenu();
			
			return screenNames;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getGroupScreenNames() throws Exception {
		try {
			String screenLocator = or.getProperty("NavigationMenu_Group_Screens");
			List<WebElement> elements = ElementHelper.getElements(screenLocator);
			String[] screenNames = new String[elements.size()];
			
			for (int i = 0; i < elements.size(); i++)
				screenNames[i] = ElementHelper.getText(elements.get(i));
			
			return screenNames;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getNavigationMenuScreens() throws Exception {
		try {
			String[] screenNames = new String[300];
			int count = 0;
			JSoupHelper jSoup = new JSoupHelper();
			Elements navigationMenuPanel = jSoup.getElements("table#Navigation-panel");
			
			if (navigationMenuPanel != null) {
				Element navigationMenu = navigationMenuPanel.get(0);
				Elements menuPanels = navigationMenu.select("div[id^=verticalPanel]");
				
				if (menuPanels != null) {
					for (int i = 0; i < menuPanels.size(); i++) {
						Elements screens = menuPanels.get(i).select("div[id^=id-]");
						
						if (screens != null) {
							int length = screens.size();
							
							for (int j = 0; j < length; j++) {
								screenNames[count] = screens.get(j).id().replace("id-", "");
								
								count++;
							}
						}
					}
				}
				else {
					if (!ElementHelper.isElementPresent("NavigationMenu_Wrapper")) {
						if (ElementHelper.isElementPresent("SparkToolBar"))
							screenNames = getScreenNames();
					}
				}
			}
			else {
				FailureHelper.failTest("Navigation Menu did not open.");
			}
			
			screenNames = StringHelper.resizeStringArray(screenNames, count);
			return screenNames;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private String[] getMegaMenuScreens() throws Exception {
		try {
			String[] screenNames = new String[300];
			int count = 0;
			JSoupHelper jSoup = new JSoupHelper();
			Element megaMenu = jSoup.getElements("div#mega-menu-container-quick").get(0);
			Elements menuPanels = megaMenu.select("div[id^=verticalPanel]");
			
			if (menuPanels != null) {
				Elements quickMenuItems = menuPanels.get(0).select("div[id^=id-]");
				
				if (quickMenuItems != null) {
					List<WebElement> quickLinkElements = ElementHelper.getElements("MegaMenu_QuickLinks_Panel");
					
					for (int i = 0; i < quickMenuItems.size()-1; i++) {
						if (i > 0 && i % 10 == 0)
							ElementHelper.scrollDown("QuickLinks_ById", true);
						MouseHelper.mouseOver(quickLinkElements.get(i));
						Elements screensPanel = jSoup.getElements("table[id^=item-menu-try-redraw]");
						Elements panels = screensPanel.get(0).select("div[id^=verticalPanel]");
						
						for (int j = 0; j < panels.size(); j++) {
							Elements screenPanels = panels.get(j).select("table#item-menu");
							
							if (ValidationHelper.isNotEmpty(screenPanels)) {
								int length = screenPanels.size();
								
								for (int k = 0; k < length; k++) {
									Element tempScreen = screenPanels.get(k);
									if (tempScreen != null) {
										Elements screens = tempScreen.select("div[id^=id-]");
										
										if (screens != null && screens.size() > 0) {
											String screenName = screens.get(0).id().replace("id- ", "").replace("id-", "");
											if (screenName.equals("Metrics"))
												screenNames[count] = "Metric";
											else
												screenNames[count] = screenName;
											count++;
										}
									}
								}
							}
						}
					}
				}
				else {
					if (!ElementHelper.isElementPresent("NavigationMenu_Wrapper")) {
						if (ElementHelper.isElementPresent("SparkToolBar"))
							screenNames = getScreenNames();
					}
				}
			}
			
			screenNames = StringHelper.resizeStringArray(screenNames, count);
			return screenNames;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getOptionsMenuItems() throws Exception {
		try {
			MouseHelper.click("Options_Icon");
			List<WebElement> optionsItems = ElementHelper.getElements("OptionsMenu_Items");
			String[] menuItems = new String[5];
			
			if (optionsItems != null) {
				menuItems = new String[optionsItems.size()];
				
				for (int i = 0; i < optionsItems.size(); i++) {
					menuItems[i] = optionsItems.get(i).getText();
				}
			}
			MouseHelper.click("LoggedIn_User");
			
			menuItems = StringHelper.resizeStringArray(menuItems);
			return menuItems;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getGroupActions() throws Exception {
		try {
			List<WebElement> groupActions = ElementHelper.getElements("ActionMenu_Groups");
			String[] groupItems = new String[5];
			if (groupActions != null) {
				groupItems = new String[groupActions.size()];
				
				for (int i = 0; i < groupActions.size(); i++) {
					groupItems[i] = groupActions.get(i).getText();
				}
			}
			
			groupItems = StringHelper.resizeStringArray(groupItems);
			return groupItems;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getSubMenuActions() throws Exception {
		try {
			List<WebElement> subMenuActions = ElementHelper.getElements("ActionMenu_SubMenu_Items");
			String[] subMenuItems = new String[5];
			if (subMenuActions != null) {
				subMenuItems = new String[subMenuActions.size()];
				
				for (int i = 0; i < subMenuActions.size(); i++) {
					subMenuItems[i] = subMenuActions.get(i).getText();
				}
			}
			
			subMenuItems = StringHelper.resizeStringArray(subMenuItems);
			return subMenuItems;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String[] getSubSubMenuActions() throws Exception {
		try {
			List<WebElement> subSubMenuActions = ElementHelper.getElements("ActionMenu_SubSubMenu_Items");
			String[] subSubMenuItems = new String[5];
			if (subSubMenuActions != null) {
				subSubMenuItems = new String[subSubMenuActions.size()];
				
				for (int i = 0; i < subSubMenuActions.size(); i++) {
					subSubMenuItems[i] = subSubMenuActions.get(i).getText();
				}
			}
			
			subSubMenuItems = StringHelper.resizeStringArray(subSubMenuItems);
			return subSubMenuItems;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isSearchScreenLoaded() throws Exception {
		try {
			if (ElementHelper.isElementPresent("SearchScreen_Title"))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isDetailScreenLoaded() throws Exception {
		try {
			boolean screenLoaded = false;
			if (ElementHelper.isElementPresent("DetailScreen_Title"))
				screenLoaded = true;
			else if (ElementHelper.isElementPresent("Popup_Title"))
				screenLoaded = true;
			
			return screenLoaded;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public boolean isPopupLoaded() throws Exception {
		try {
			if (ElementHelper.isElementPresent("Popup_Title"))
				return true;
			else
				return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void setFocusOnDNA() throws Exception {
		try {
			String canvasLocator = CanvasElementHelper.getLocator(or.getProperty("DNA_CanvasID"));
			if (canvasLocator != null) {
				ElementHelper.dragAndDrop(canvasLocator, 0, 30);
				String nodeLocator = canvasLocator + or.getProperty("DNA_Nodes");
				int size = ElementHelper.getXpathCount(nodeLocator);
				
				if (size > 0) {
					MouseHelper.rightClick(nodeLocator + "[" + (size-1) + "]");
					ElementHelper.pressEscape("DNA_RightClick_Panel");
					WebElement noElement = ElementHelper.getElement("NoButton");
					
					if (noElement != null) {
						ElementHelper.click(noElement);
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
						GenericHelper.waitForAJAXReady(searchScreenWaitSec);
					}
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public WebElement getActionElement (String actionText) throws Exception {
		try {
			WebElement element = null;
			
			if (actionText.startsWith("/")) {
				element = ElementHelper.getElement(actionText);
			}
			else {
				String contextMenuParentXpath = or.getProperty("ContextMenu_GroupAction").replace("actionName", actionText);
				String parentXpath = or.getProperty("GroupActionName").replace("actionName", actionText);
				String childXpath = or.getProperty("SubMenuActionName").replace("actionName", actionText);
				String subChildXpath = or.getProperty("SubSubMenuActionName").replace("actionName", actionText);
				String detailParentXpath = or.getProperty("Detail_ParentToolbar").replace("actionName", actionText);
				String detailChildXpath = or.getProperty("Detail_ChildToolbar").replace("actionName", actionText);
				
				String product = ValidationHelper.checkProduct(configProp.getProduct());
				if (!product.equals("FM")) {
					element = ElementHelper.getElement(subChildXpath);
					
					if (element == null)
						element = ElementHelper.getElement(childXpath);
					
					if (element == null)
						element = ElementHelper.getElement(contextMenuParentXpath);
					
					if (element == null)
						element = ElementHelper.getElement(parentXpath);
					
					if (element == null)
						element = ElementHelper.getElement(detailChildXpath);
					
					if (element == null)
						element = ElementHelper.getElement(detailParentXpath);
				}
			}
			return element;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public String getMissingScreens(String[] actualScreens, int count) throws Exception {
		try {
			String missingScreens = "";
			for (int i = 0; i < count; i++) {
				if (i == 0)
					missingScreens = actualScreens[i];
				else
					missingScreens = missingScreens + ", " + actualScreens[i];
			}
			
			return missingScreens;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}