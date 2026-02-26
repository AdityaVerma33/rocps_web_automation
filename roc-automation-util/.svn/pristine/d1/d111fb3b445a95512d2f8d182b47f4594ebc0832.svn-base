package com.subex.automation.helpers.componentHelpers;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.selenium.AcceptanceTest;
import com.subex.automation.helpers.util.FailureHelper;


public class SearchHelper extends AcceptanceTest {
	
	private void clickFilterIcon(WebElement headerElement, String gridId, String columnHeader) throws Exception {
		try {
			if (headerElement != null) {
				WebElement filterElement = ElementHelper.getElement(headerElement, "Grid_Filter_Icon");
				
				if (filterElement != null) {
					ElementHelper.scrollToView(filterElement, true);
					if (!ElementHelper.isClickable(filterElement))
						ElementHelper.waitForClickableElement(filterElement, searchScreenWaitSec);
					MouseHelper.click(filterElement);
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (!ElementHelper.isElementPresent("Grid_Filter_Panel")) {
						headerElement = GridElementHelper.getHeaderElement(gridId, columnHeader);
						filterElement = ElementHelper.getElement(headerElement, "Grid_Filter_Icon");
						
						if (!ElementHelper.isClickable(filterElement))
							ElementHelper.waitForClickableElement(filterElement, searchScreenWaitSec);
						MouseHelper.click(filterElement);
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
				}
				else {
					FailureHelper.failTest("Filter icon not found for column '" + columnHeader + "' in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickFilterIcon(String iconId) throws Exception {
		try {
			iconId = GenericHelper.getORProperty(iconId);
			ElementHelper.waitForClickableElement(iconId, searchScreenWaitSec);
			MouseHelper.click(iconId);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickFilterIcon(String gridId, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			WebElement headerElement = GridElementHelper.getHeaderElement(gridId, columnHeader);
			
			clickFilterIcon(headerElement, gridId, columnHeader);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickFilterIcon(String gridWrapper, String gridId, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			GenericHelper.waitForAJAXReady(searchScreenWaitSec);
			WebElement headerElement = GridElementHelper.getHeaderElement(gridWrapper, gridId, columnHeader);
			
			clickFilterIcon(headerElement, gridId, columnHeader);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickSearch() throws Exception {
		try {
			String[] locators = {"Grid_SearchIcon", "Grid_SearchButton", "New_GridFilter_Search"};
			WebElement element = null;
			if (ElementHelper.isElementPresent("Grid_Filter_Panel"))
				element = ElementHelper.getElement(locators);
			else
				element = ElementHelper.getElement("SearchButton");
			
			if (element != null) {
				MouseHelper.click(element);
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				
				element = ElementHelper.getElement(locators);
				if (element != null) {
					MouseHelper.click(element);
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
			}
			else
				FailureHelper.failTest("Search Button/Icon not found in grid");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void clickArrowIcon(WebElement headerElement, String gridId, String columnHeader) throws Exception {
		try {
			if (headerElement != null) {
				if (ElementHelper.isElementPresent(headerElement, "Grid_Arrow_Icon")) {
					WebElement filterElement = ElementHelper.getElement(headerElement, "Grid_Arrow_Icon");
					
					ElementHelper.scrollToView(filterElement, true);
					MouseHelper.click(filterElement);
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
					
					if (!ElementHelper.isElementPresent("Grid_Arrow_Panel")) {
						filterElement = ElementHelper.getElement(headerElement, "Grid_Arrow_Icon");
						MouseHelper.click(filterElement);
						GenericHelper.waitForLoadmask(searchScreenWaitSec);
					}
				}
				else {
					FailureHelper.failTest("Filter icon not found for column '" + columnHeader + "' in grid '" + gridId + "'");
				}
			}
			else {
				FailureHelper.failTest("Expected column '" + columnHeader + "' not found in grid '" + gridId + "'");
			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickArrowIcon(String iconId) throws Exception {
		try {
			iconId = GenericHelper.getORProperty(iconId);
			MouseHelper.click(iconId);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickArrowIcon(String gridId, String columnHeader) throws Exception {
		try {
			gridId = GenericHelper.getORProperty(gridId);
			WebElement headerElement = GridElementHelper.getHeaderElement(gridId, columnHeader);
			
			clickArrowIcon(headerElement, gridId, columnHeader);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void clickArrowIcon(String gridWrapper, String gridId, String columnHeader) throws Exception {
		try {
			gridWrapper = GenericHelper.getORProperty(gridWrapper);
			gridId = GenericHelper.getORProperty(gridId);
			WebElement headerElement = GridElementHelper.getHeaderElement(gridWrapper, gridId, columnHeader);
			
			clickArrowIcon(headerElement, gridId, columnHeader);
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}