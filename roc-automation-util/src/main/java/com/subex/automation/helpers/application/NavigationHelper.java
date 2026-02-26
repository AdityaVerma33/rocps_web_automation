package com.subex.automation.helpers.application;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.data.StringHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;

/**
 * Navigation methods for all ROC based products.
 * @author madhu.duraisamy
 *
 */
public class NavigationHelper extends AcceptanceTest {
	
	private static String screenName = null;
	static String[] nonMenu = {"Change Password", "Settings", "Logout", "About", "License Info", "User Manual"};
	private static boolean isROCView = false;
	
	/**
	 * This method is used to navigate to a Screen through Navigation Menu
	 * @param screenName - Screen Name
	 * @throws Exception
	 */
	public static void navigateToScreen( String currentScreenName ) throws Exception {
		try {
//			if(calculatePerformance) {
//				HarReader.generateHar();
//			}
			
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			currentScreenName = currentScreenName.replace("...", "");
			if (Arrays.asList(nonMenu).contains(currentScreenName)) {
				screenName = currentScreenName;
				navigationMenu.navigateToOptions(screenName);
			}
			else {
				screenName = currentScreenName;
				isROCView = navigationMenu.navigationMenu(screenName, isROCView);
				
				if (currentScreenName.equals("DNA")) {
					navigationMenu.setFocusOnDNA();
				}
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private static void checkScreenTitle( String currentScreenName, String screenTitle ) throws Exception {
		try {
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			String product = ValidationHelper.checkProduct(configProp.getProduct());
			if (!product.equals("FM")) {
				String currentTitle = getScreenTitle();
				if(!screenTitle.equals(currentTitle)) {
					if (screenTitle.length() > 23) {
						String temp = screenTitle.substring(0, 19) + "...";
						
						if(!temp.equals(currentTitle)) {
							FailureHelper.failTest("Screen with title '" + screenTitle + "' not found after clicking screen '" + currentScreenName + "'");
						}
					}
				}
				
				if (currentScreenName.equals("DNA"))
					navigationMenu.setFocusOnDNA();
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to navigate to a Screen through Navigation Menu
	 * @param screenName - Screen Name
	 * @param screenTitle - Screen title to be validated on screen load
	 * @throws Exception
	 */
	public static void navigateToScreen( String currentScreenName, String screenTitle ) throws Exception {
		try {
//			if(calculatePerformance) {
//				HarReader.generateHar();
//			}
			
			if (configProp.getBrowser().equalsIgnoreCase("ie"))
				Thread.sleep(3000);
			
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			if (Arrays.asList(nonMenu).contains(currentScreenName)) {
				screenName = currentScreenName;
				navigationMenu.navigateToOptions(screenName);
			}
			else {
				boolean navigate = false;
				String currentScreen = getScreenTitle();
				
				if (currentScreen == null || !currentScreen.equals(screenTitle))
					navigate = true;
				
				if (screenTitle.equals("Reference Tables"))
					navigate = true;
				
				if (navigate) {
					screenName = currentScreenName;
					isROCView = navigationMenu.navigationMenu(screenName, isROCView);
				}
				
				checkScreenTitle(currentScreenName, screenTitle);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to navigate to a Screen through Navigation Menu
	 * @param screenName - Screen Name
	 * @param refTable = Reference Table combo value
	 * @param screenTitle - Screen title to be validated on screen load
	 * @throws Exception
	 */
	public static void navigateToScreen(String screenName, String refTable, String screenTitle) throws Exception {
		try {
			NavigationHelper.navigateToScreen(screenName, screenTitle);
			
			if (!refTable.equals("")) {
				ComboBoxHelper.select("displayString_gwt_uid_", refTable);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void navigateToReferenceTable(String refTableEntity) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Reference Tables", "Reference Table Search");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			String currentRefTbl = ComboBoxHelper.getValue("ReferenceTable_DropDownArrow");
			if (ValidationHelper.isEmpty(currentRefTbl) || !currentRefTbl.equals(refTableEntity)) {
				ComboBoxHelper.select("ReferenceTable_DropDownArrow", refTableEntity);
				Thread.sleep(1000);
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to do toolbar action on Search screen.
	 * @param actionText - Toolbar action name to be clicked.
	 * @throws Exception
	 */
	public static void navigateToAction( String actionText ) throws Exception {
		try {
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			Thread.sleep(200);
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			WebElement element = navigationMenu.getActionElement(actionText);
			
			if (element != null) {
				ElementHelper.waitForClickableElement(element, 20);
				MouseHelper.click( element );
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GenericHelper.waitForAJAXReady(detailScreenWaitSec);
				
				screenName = getScreenTitle();
			}
			else {
				FailureHelper.failTest("Action '" + actionText + "' is not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to do toolbar action on Search screen.
	 * @param parentText - Toolbar group name.
	 * @param childText - Toolbar action name to be clicked inside the toolbar group.
	 * @throws Exception
	 */
	public static void navigateToAction( String parentText, String childText ) throws Exception {
		try {
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			Thread.sleep(200);
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			WebElement element = navigationMenu.getActionElement(parentText);
			
			if (element != null) {
				ElementHelper.waitForClickableElement(element, 20);
				MouseHelper.click( element );
				element = navigationMenu.getActionElement(parentText);
				MouseHelper.mouseOver(element);
				element = navigationMenu.getActionElement(childText);
				
				if (element != null) {
					MouseHelper.click( element );
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
					GenericHelper.waitForAJAXReady(detailScreenWaitSec);
					screenName = getScreenTitle();
				}
				
				else {
					FailureHelper.failTest("Child action '" + childText + "' is not found.");
				}
			}
			else {
				FailureHelper.failTest("Parent action '" + parentText + "' is not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/**
	 * This method is used to do toolbar action on Search screen.
	 * @param parentText - Toolbar group name.
	 * @param childText - Toolbar sub-group name.
	 * @param subChildText - Toolbar action name to be clicked inside the toolbar group.
	 * @throws Exception
	 */
	public static void navigateToAction( String parentText, String childText, String subChildText ) throws Exception {
		try {
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			Thread.sleep(200);
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			WebElement element = navigationMenu.getActionElement(parentText);
			
			if (element != null) {
				ElementHelper.waitForClickableElement(element, 20);
				MouseHelper.click( element );
				element = navigationMenu.getActionElement(childText);
				
				if (element != null) {
					MouseHelper.click( element );
					MouseHelper.mouseOver(element);
					element = navigationMenu.getActionElement(subChildText);
					
					if (element != null) {
						MouseHelper.click( element );
						GenericHelper.waitForLoadmask(detailScreenWaitSec);
						GenericHelper.waitForAJAXReady(detailScreenWaitSec);
						screenName = getScreenTitle();
					}
					else {
						FailureHelper.failTest("Sub-child action '" + subChildText + "' is not found.");
					}
				}
				else {
					FailureHelper.failTest("Child action '" + childText + "' is not found.");
				}
			}
			else {
				FailureHelper.failTest("Parent action '" + parentText + "' is not found.");
			}
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void navigateToNew() throws Exception {
		try {
			boolean isElementPresent = ElementHelper.isElementPresent("CommonTasks");
			if (!isElementPresent) {
				ROCHelper rocHelper = new ROCHelper();
				rocHelper.handleDetailScreen();
			}
			
			navigateToAction("Common Tasks", "New");
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void selectPartition(String partition) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(partition)) {
				if (ElementHelper.isElementPresent("SecurityPartition_Popup")) {
					ComboBoxHelper.select("Security_Partition_Dropdown", partition);
					ButtonHelper.click("OKButton");
					GenericHelper.waitForLoadmask(detailScreenWaitSec);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String navigateToNew(String securityPartition) throws Exception {
		try {
			if (ValidationHelper.isEmpty(securityPartition))
				securityPartition = "Common";
			
			navigateToNew();
			selectPartition(securityPartition);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			Thread.sleep(1000);
			return getScreenTitle();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String navigateToNew(String securityPartition, String waitForComponent) throws Exception {
		try {
			if (ValidationHelper.isEmpty(securityPartition))
				securityPartition = "Common";
			
			navigateToNew();
			selectPartition(securityPartition);
			GenericHelper.waitForLoadmask(detailScreenWaitSec);
			GenericHelper.waitForElement(waitForComponent, detailScreenWaitSec);
			return getScreenTitle();
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean navigateToNewOrEdit (int rowNum, String partition, String screenTitle) throws Exception {
		try {
			if (rowNum > 0) {
				navigateToEdit("SearchGrid", rowNum);
				assertTrue(LabelHelper.isTitlePresent("Edit " + screenTitle), "Detail screen 'Edit " + screenTitle + "' did not appear.");
				
				return true;
			}
			else {
				NavigationHelper.navigateToNew(partition);
				assertTrue(LabelHelper.isTitlePresent("New " + screenTitle), "Detail screen 'New " + screenTitle + "' did not appear.");
				
				return false;
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean navigateToNewOrEdit (int rowNum, String partition, String screenTitle, String waitForComponent) throws Exception {
		try {
			if (rowNum > 0) {
				navigateToEdit("SearchGrid", rowNum, waitForComponent);
				assertTrue(LabelHelper.isTitlePresent("Edit " + screenTitle), "Detail screen 'Edit " + screenTitle + "' did not appear.");
				
				return true;
			}
			else {
				NavigationHelper.navigateToNew(partition, waitForComponent);
				assertTrue(LabelHelper.isTitlePresent("New " + screenTitle), "Detail screen 'New " + screenTitle + "' did not appear.");
				
				return false;
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String navigateToEdit (String gridId, int rowNum) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, 1);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				NavigationHelper.navigateToAction("Common Tasks", "Edit");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				Thread.sleep(1000);
				return getScreenTitle();
			}
			else {
				FailureHelper.failTest("Row number cannot be 0.");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String navigateToEdit (String gridId, int rowNum, String waitForComponent) throws Exception {
		try {
			if (rowNum > 0) {
				GridHelper.clickRow(gridId, rowNum, 1);
				GenericHelper.waitForAJAXReady(searchScreenWaitSec);
				NavigationHelper.navigateToAction("Common Tasks", "Edit");
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				GenericHelper.waitForElement(waitForComponent, detailScreenWaitSec);
				return getScreenTitle();
			}
			else {
				FailureHelper.failTest("Row number cannot be 0.");
				return null;
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setScreenName(String screenName) throws Exception {
		try {
			NavigationHelper.screenName = screenName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getScreenName() throws Exception {
		try {
			return screenName;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static String getScreenTitle() throws Exception {
		try {
			String screenTitle = null;
			int tryCount=1;
			
			if (ElementHelper.isElementPresent("LoginScreen")) {
				screenTitle = "Login Screen";
			}
			else {
				String popupLocator = or.getProperty("Popup_Title");
				
				if (PopupHelper.isPresent("Popup_Title")) {
					List<WebElement> popups = ElementHelper.getElements(or.getProperty("Popup_Wrapper") + popupLocator);
					
					if (popups != null && popups.size() > 0) {
						int index = popups.size() - 1;
						screenTitle = popups.get(index).getText();
					}
				}
				else if (ElementHelper.isElementPresent("SearchScreen_Title")) {
					screenTitle = LabelHelper.getText("SearchScreen_Title");
				}
				else if (ElementHelper.isElementPresent("DetailScreen_Title")) {
					String actionTitle = LabelHelper.getText("DetailScreen_ActionTitle");
					
					if (actionTitle != null) {
						screenTitle = actionTitle + " " + LabelHelper.getText("DetailScreen_FormTitle");
					}
				}
				else if (ElementHelper.isElementPresent("HomePage_Title")) {
					screenTitle = LabelHelper.getText("HomePage_Title");
				}
				else if (ElementHelper.isElementPresent("ROCView_Visualizer")) {
					screenTitle = "Dashboard";
					driver.switchTo().frame(or.getProperty("ROCView_Iframe"));
					isROCView = true;
				}
				else if (ElementHelper.isElementPresent("BOP_Panel")) {
					String locator = "//*[contains(@class,'ra-heading')]/h2";
					screenTitle = LabelHelper.getText(locator);
				}
				
				if(screenTitle == null) {
					while(!ElementHelper.isElementPresent("SearchScreen_Title") && tryCount <= 10) {
						tryCount++;
						screenTitle = LabelHelper.getText("SearchScreen_Title");
					}
				}
			}
			
			if (screenTitle != null)
				screenTitle = screenTitle.replace("\n", " ").replace("\r", " ");
			return screenTitle;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTitlePresent( String screenTitle ) throws Exception  {
		try {
			boolean isPresent = false;
			String product = ValidationHelper.checkProduct(configProp.getProduct());
			
			if (!product.equals("FM")) {
				String currentTitle = getScreenTitle();
				
				if (ValidationHelper.isNotEmpty(currentTitle)) {
					if (currentTitle.contains("...")) {
						currentTitle = currentTitle.replace("...", "");
						if (currentTitle.length() < screenTitle.length()) {
							if (screenTitle.startsWith(currentTitle))
								isPresent = true;
						}
						else {
							if (currentTitle.startsWith(screenTitle))
								isPresent = true;
						}
					}
					else {
						if (currentTitle.equals(screenTitle) || currentTitle.contains(screenTitle))
							isPresent = true;
					}
				}
			}
			
			return isPresent;
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isActionPresent( String actionText ) throws Exception  {
		try {
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			WebElement element = navigationMenu.getActionElement(actionText);

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
	
	/**
	 * This method is used to check the expected screen names in Navigation Menu
	 * @param screenNames - All the screen names as ; separated, e.g., Elements;Bands;Tariffs
	 * @throws Exception
	 */
	public static void checkScreens( String[] screenNames ) throws Exception {
		try {
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			String[] actualScreens = navigationMenu.getScreenNames();
			int length = actualScreens.length;
			
			if (screenNames.length == length) {
				int[] indexes = new int[length];
				int count = 0;
				
				for (int i = 0; i < length; i++) {
					int index = StringHelper.searchArray(screenNames, actualScreens[i]);
					if (index == -1) {
						indexes[count] = i;
						count++;
					}
				}
				
				if (count > 0) {
					indexes = GenericHelper.resizeIntArray(indexes, count);
					
					if (indexes == null || indexes.length == 0) {
						String missingScreens = navigationMenu.getMissingScreens(actualScreens, count);
						FailureHelper.failTest("The screens (" + missingScreens + ") are expected. But are not found in Navigation Menu.");
					}
				}
			}
			else {
				if (screenNames.length > length)
					FailureHelper.failTest("There are less number of screens (" + screenNames.length + ") in Navigation Menu than expected (" + length + ")");
				else
					FailureHelper.failTest("There are more number of screens (" + screenNames.length + ") in Navigation Menu than expected (" + length + ")");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void checkScreens( String[] groupNames, String[][] screenNames ) throws Exception {
		try {
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			
			for (int i = 0; i < groupNames.length; i++) {
				navigateToGroup(groupNames[i]);
				
				String[] actualScreens = navigationMenu.getGroupScreenNames();
				
				for (int j = 0; j < screenNames[i].length; j++) {
					int index = StringHelper.searchArray(actualScreens, screenNames[i][j]);
					if (index == -1) {
						FailureHelper.failTest("The screen '" + screenNames[i][j] + "' is not found under '" + groupNames[i] + "' in Navigation Menu.");
					}
				}
				
				navigationMenu.exitNavigationMenu();
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	/**
	 * This method is used to check the expected screen names in Navigation Menu
	 * @param screenNames - All the screen names as ; separated, e.g., Elements;Bands;Tariffs
	 * @throws Exception
	 */
	public static void checkOptionsScreens( String[] screenNames ) throws Exception {
		try {
			NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
			String[] actualScreens = navigationMenu.getOptionsMenuItems();
			int length = actualScreens.length;
			
			if (screenNames.length == length) {
				int[] indexes = new int[length];
				int count = 0;
				
				for (int i = 0; i < length; i++) {
					int index = StringHelper.searchArray(screenNames, actualScreens[i]);
					if (index == -1) {
						indexes[count] = i;
						count++;
					}
				}
				
				if (count > 0) {
					indexes = GenericHelper.resizeIntArray(indexes, count);
					
					if (ValidationHelper.isEmpty(indexes)) {
						String missingScreens = navigationMenu.getMissingScreens(actualScreens, count);
						FailureHelper.failTest("The screens (" + missingScreens + ") are expected. But are not found in Options Menu.");
					}
				}
			}
			else {
				if (screenNames.length > length)
					FailureHelper.failTest("There are less number of screens (" + screenNames.length + ") in Options Menu than expected (" + length + ")");
				else
					FailureHelper.failTest("There are more number of screens (" + screenNames.length + ") in Options Menu than expected (" + length + ")");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void setAsQuickLink( String[] groupName, String[][] screenNames ) throws Exception {
		try {
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
			
			for (int i = 0; i < groupName.length; i++) {
				String groupLocator = or.getProperty("MegaMenu_GroupNames").replace("groupName", groupName[i]);
				MouseHelper.mouseOver(groupLocator);
				
				for (int j = 0; j < screenNames[i].length; j++) {
					String screenLocator = or.getProperty("NavigationMenu_Screen_Star").replace("screenName", screenNames[i][j]);
					
					if (!LabelHelper.getText(screenLocator).equals("★"))
						ButtonHelper.click(screenLocator);
				}
			}
			
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void navigateThroughQuickLinks( String screenName, String screenTitle ) throws Exception {
		try {
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
			MouseHelper.mouseOver("QuickLinks_ById");
			
			String screenLocator = or.getProperty("NavigationMenu_Screen").replace("screenName", screenName);
			if (ElementHelper.isElementPresent(screenLocator)) {
				MouseHelper.click( screenLocator );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				// This code is to click Cancel Confirmation dialog. 
				if (ButtonHelper.isPresent("DiscardButton")) {
					ButtonHelper.click("DiscardButton");
				}
				
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				checkScreenTitle(screenName, screenTitle);
			}
			else {
				FailureHelper.failTest("Screen '' is not found under Quick Links in Navigation Menu");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void navigateToGroup( String groupName ) throws Exception {
		try {
			ButtonHelper.click("NavigationMenu");
			Thread.sleep(100);
			String groupLocator = or.getProperty("MegaMenu_GroupNames").replace("groupName", groupName);
			MouseHelper.mouseOver(groupLocator);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void navigateThroughGroup( String groupName, String screenName, String screenTitle ) throws Exception {
		try {
			navigateToGroup(groupName);
			
			String screenLocator = or.getProperty("NavigationMenu_Screen").replace("screenName", screenName);
			if (ElementHelper.isElementPresent(screenLocator)) {
				MouseHelper.click( screenLocator );
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				// This code is to click Cancel Confirmation dialog. 
				if (ButtonHelper.isPresent("DiscardButton")) {
					ButtonHelper.click("DiscardButton");
				}
				
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				checkScreenTitle(screenName, screenTitle);
			}
			else {
				FailureHelper.failTest("Screen '' is not found under Quick Links in Navigation Menu");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClickAction( String gridId, int rowNum, int colNum, String actionText ) throws Exception {
		try {
			GridHelper.rightClick(gridId, rowNum, colNum);
			Thread.sleep(1000);
			
			navigateToAction(actionText);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClickAction( String gridId, int rowNum, int colNum, String parentText, String childText ) throws Exception {
		try {
			GridHelper.rightClick(gridId, rowNum, colNum);
			Thread.sleep(1000);
			
			navigateToAction(parentText, childText);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public static void rightClickAction( String gridId, int rowNum, int colNum, String parentText, String childText, String subChildText ) throws Exception {
		try {
			GridHelper.rightClick(gridId, rowNum, colNum);
			Thread.sleep(1000);
			
			navigateToAction(parentText, childText, subChildText);
		}
		catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void delete( String gridId, String name, String nameColumnHeader ) throws Exception {
		try {
			GridHelper.clickRow(gridId, name, nameColumnHeader);
			NavigationHelper.navigateToAction("Common Tasks", "Delete");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Delete"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber(gridId, name, nameColumnHeader);
			assertTrue(row == 0, "After delete action, expected '0' but found '" + row + "' row number.");
			
			GenericHelper.expandSearchFilterPanel();
			if (ComboBoxHelper.isPresent("Include_Combo")) {
				ComboBoxHelper.select("Include_Combo", "Deleted Items");
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(GridHelper.isRowDeleted(gridId, name, nameColumnHeader));
			}
			
			GenericHelper.collapseSearchFilterPanel();
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void delete( String gridWrapper, String gridId, String name, String nameColumnHeader ) throws Exception {
		try {
			GridHelper.clickRow(gridWrapper, gridId, name, nameColumnHeader);
			NavigationHelper.navigateToAction("Common Tasks", "Delete");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Delete"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber(gridWrapper, gridId, name, nameColumnHeader);
			assertTrue(row == 0, "After delete action, expected '0' but found '" + row + "' row number.");
			
			GenericHelper.expandSearchFilterPanel();
			if (ComboBoxHelper.isPresent("Include_Combo")) {
				ComboBoxHelper.select("Include_Combo", "Deleted Items");
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(GridHelper.isRowDeleted(gridWrapper, gridId, name, nameColumnHeader));
			}
			GenericHelper.collapseSearchFilterPanel();
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void undelete( String gridId, String name, String nameColumnHeader ) throws Exception {
		try {
			GenericHelper.expandSearchFilterPanel();
			if (ComboBoxHelper.isPresent("Include_Combo")) {
				ComboBoxHelper.select("Include_Combo", "Deleted Items");
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			GenericHelper.collapseSearchFilterPanel();
			
			GridHelper.clickRow(gridId, name, nameColumnHeader);
			NavigationHelper.navigateToAction("Common Tasks", "Undelete");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Undelete"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber(gridId, name, nameColumnHeader);
			assertTrue(row == 0);
			
			GenericHelper.expandSearchFilterPanel();
			if (ComboBoxHelper.isPresent("Include_Combo")) {
				ComboBoxHelper.select("Include_Combo", "Non Deleted Items");
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertFalse(GridHelper.isRowDeleted(gridId, name, nameColumnHeader));
			}
			GenericHelper.collapseSearchFilterPanel();
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void undelete( String gridWrapper, String gridId, String name, String nameColumnHeader ) throws Exception {
		try {
			GridHelper.clickRow(gridWrapper, gridId, name, nameColumnHeader);
			NavigationHelper.navigateToAction("Common Tasks", "Undelete");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			assertTrue(LabelHelper.isTitlePresent("Undelete"));
			ButtonHelper.click("YesButton");
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			Thread.sleep(1000);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			
			int row = GridHelper.getRowNumber(gridWrapper, gridId, name, nameColumnHeader);
			assertTrue(row == 0);
			
			GenericHelper.expandSearchFilterPanel();
			if (ComboBoxHelper.isPresent("Include_Combo")) {
				ComboBoxHelper.select("Include_Combo", "Non Deleted Items");
				ButtonHelper.click("SearchButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertFalse(GridHelper.isRowDeleted(gridWrapper, gridId, name, nameColumnHeader));
			}
			GenericHelper.collapseSearchFilterPanel();
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}