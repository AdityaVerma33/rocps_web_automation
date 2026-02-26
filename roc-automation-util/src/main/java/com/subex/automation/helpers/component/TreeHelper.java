package com.subex.automation.helpers.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.subex.automation.helpers.componentHelpers.TreeElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class TreeHelper extends ComponentHelper {
	
	public static boolean isPresent( String treeId ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isPresent( String treeWrapper, String treeId ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeId, treeText);
			
			if (element == null)
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			else {
				MouseHelper.scrollAndClick(element);
				element = TreeElementHelper.getElement(treeId, treeText);
				MouseHelper.click(element);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void click( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeWrapper, treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				MouseHelper.scrollAndClick(element);
				element = TreeElementHelper.getElement(treeWrapper, treeId, treeText);
				MouseHelper.click(element);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickChild( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null)
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + childText + "' under parent '" + parentText + "' is not found.");
			else {
//				MouseHelper.scrollAndClick(element);
				MouseHelper.click(element);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void clickChild( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null)
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + childText + "' under parent '" + parentText + "' is not found.");
			else {
//				MouseHelper.scrollAndClick(element);
				MouseHelper.click(element);
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClick(String treeId, String treeText) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeId, treeText);

			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				ElementHelper.scrollToView(element, true);
				
				Actions action = new Actions(driver);
				action.click(element);
				action.doubleClick(element);
				action.build().perform();
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void doubleClick( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeWrapper, treeId, treeText);

			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				ElementHelper.scrollToView(element, true);
				
				Actions action = new Actions(driver);
				action.click(element);
				action.doubleClick(element);
				action.build().perform();
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else
				MouseHelper.rightClick(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void rightClick( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeWrapper, treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else
				MouseHelper.rightClick(element);
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTreeExpanded( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				return isExpanded;
			}
			
			return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isTreeExpanded( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeWrapper, treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				return isExpanded;
			}
			
			return false;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void expandTree( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				WebElement expandElement = (WebElement) expandDetails[0];
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (expandElement != null && !isExpanded) {
					MouseHelper.click(element);
					Thread.sleep(1000);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void expandTree( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeWrapper, treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				WebElement expandElement = (WebElement) expandDetails[0];
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (expandElement != null && !isExpanded) {
					MouseHelper.click(element);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void expandTreeGrid( String gridId, String treeText ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = TreeElementHelper.getExpandElement(gridId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Grid '" + gridId + "' with text '" + treeText + "' is not found.");
			}
			else {
				boolean isExpanded = TreeElementHelper.isTreeGridExpanded(element);
				
				if (!isExpanded) {
					MouseHelper.click(element);
					Thread.sleep(1000);
					
					element = TreeElementHelper.getExpandElement(gridId, treeText);
					isExpanded = TreeElementHelper.isTreeGridExpanded(element);
					
					if (!isExpanded) {
						MouseHelper.click(element);
						Thread.sleep(1000);
					}
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void expandTreeGrid( String gridWrapper, String gridId, String treeText ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = TreeElementHelper.getExpandElement(gridWrapper, gridId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Grid '" + gridId + "' with text '" + treeText + "' is not found.");
			}
			else {
				boolean isExpanded = TreeElementHelper.isTreeGridExpanded(element);
				
				if (!isExpanded) {
					MouseHelper.click(element);
					Thread.sleep(1000);
					
					element = TreeElementHelper.getExpandElement(gridWrapper, gridId, treeText);
					isExpanded = TreeElementHelper.isTreeGridExpanded(element);
					
					if (!isExpanded) {
						MouseHelper.click(element);
						Thread.sleep(1000);
					}
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void collapseTree( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				WebElement expandElement = (WebElement) expandDetails[0];
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (expandElement != null && isExpanded) {
					MouseHelper.click(element);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void collapseTree( String treeWrapper, String treeId, String treeText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getExpandElement(treeWrapper, treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isExpanded(element);
				WebElement expandElement = (WebElement) expandDetails[0];
				boolean isExpanded = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (expandElement != null && isExpanded) {
					MouseHelper.click(element);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void collapseTreeGrid( String gridId, String treeText ) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = TreeElementHelper.getExpandElement(gridId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Grid '" + gridId + "' with text '" + treeText + "' is not found.");
			}
			else {
				boolean isExpanded = TreeElementHelper.isTreeGridExpanded(element);
				
				if (isExpanded) {
					MouseHelper.click(element);
					Thread.sleep(1000);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void collapseTreeGrid( String gridWrapper, String gridId, String treeText ) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement element = TreeElementHelper.getExpandElement(gridWrapper, gridId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Grid '" + gridId + "' with text '" + treeText + "' is not found.");
			}
			else {
				boolean isExpanded = TreeElementHelper.isTreeGridExpanded(element);
				
				if (isExpanded) {
					MouseHelper.click(element);
					Thread.sleep(1000);
				}
			}
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void checkCheckBox( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				WebElement ckElement = (WebElement) expandDetails[0];
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (ckElement != null && !isChecked)
					MouseHelper.click(ckElement);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void checkCheckBox( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				WebElement ckElement = (WebElement) expandDetails[0];
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (ckElement != null && !isChecked) {
					ElementHelper.scrollToView(ckElement, false);
					MouseHelper.click(ckElement);
				}
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void checkCheckBox( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				WebElement ckElement = (WebElement) expandDetails[0];
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (ckElement != null && !isChecked)
					MouseHelper.click(ckElement);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void unCheckCheckBox( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				WebElement ckElement = (WebElement) expandDetails[0];
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (ckElement != null && isChecked)
					MouseHelper.click(ckElement);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static void unCheckCheckBox( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				WebElement ckElement = (WebElement) expandDetails[0];
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (ckElement != null && isChecked)
					MouseHelper.click(ckElement);
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String treeId, String parentText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeId, parentText);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isValuePresent( String treeWrapper, String treeId, String parentText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getElement(treeWrapper, treeId, parentText);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isChildPresent( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isChildPresent( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null)
				return false;
			else
				return true;
		}
		catch(Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isChecked( String treeId, String treeText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, treeText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with text '" + treeText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				return isChecked;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isChecked( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				return isChecked;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isChecked( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				return isChecked;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isCheckBoxEnabled( String treeId, String parentText, String childText ) throws Exception {
		try {
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				boolean isEnabled = true;
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (isChecked) {
					unCheckCheckBox(treeId, parentText, childText);
					isEnabled = !isChecked(treeId, parentText, childText);
				}
				else {
					checkCheckBox(treeId, parentText, childText);
					isEnabled = isChecked(treeId, parentText, childText);
				}
				
				return isEnabled;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isCheckBoxEnabled( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			treeWrapper = GenericHelper.getORProperty(treeWrapper);
			treeId = GenericHelper.getORProperty(treeId);
			WebElement element = TreeElementHelper.getTreeElement(treeWrapper, treeId, parentText, childText);
			
			if (element == null) {
				FailureHelper.failTest("Tree '" + treeId + "' with Parent text '" + parentText + "' and Child text '" + childText + "' is not found.");
			}
			else {
				boolean isEnabled = true;
				Object[] expandDetails = TreeElementHelper.isChecked(element);
				boolean isChecked = ValidationHelper.isTrue((String) expandDetails[1]);
				
				if (isChecked) {
					unCheckCheckBox(treeId, parentText, childText);
					isEnabled = !isChecked(treeId, parentText, childText);
				}
				else {
					checkCheckBox(treeId, parentText, childText);
					isEnabled = isChecked(treeId, parentText, childText);
				}
				
				return isEnabled;
			}
			
			return false;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isCheckBoxDisabled( String treeId, String parentText, String childText ) throws Exception {
		try {
			return !isCheckBoxEnabled(treeId, parentText, childText);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public static boolean isCheckBoxDisabled( String treeWrapper, String treeId, String parentText, String childText ) throws Exception {
		try {
			return !isCheckBoxEnabled(treeWrapper, treeId, parentText, childText);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}