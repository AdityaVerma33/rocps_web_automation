package com.subex.rocps.automation.helpers.application.filters;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class GridFilterSearchHelper extends PSAcceptanceTest {
	PSGenericHelper genericObj = new PSGenericHelper();

	/*
	 * This method is for bill Profile Filter search
	 */
	public void billProfileFilter(String txtID, String value) throws Exception {

		SearchGridHelper.gridFilterAdvancedSearch(txtID, value, "Bill Profile");
	}

	/*
	 * This method is for Account Filter search
	 */
	public void accountFilter(String filterIconID, String txtBoxId, String value, String columnHeader)
			throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		if (ElementHelper.isElementPresent("//div[@id='popupWindow']"))
			genericObj.waitforPopupHeaderElement("Account");
		else
			genericObj.waitforHeaderElement("Account");
		searchHelper.clickFilterIcon(filterIconID);
		GenericHelper.waitForLoadmask();
		TextBoxHelper.type("Grid_Filter_Panel", txtBoxId, value);
		GenericHelper.waitForLoadmask();
		String LabelLocator = or.getProperty("New_GridFilter_Label").replace("value", value);
		String checkBxXpath = GenericHelper.getORProperty("PS_Detail_BillAction_chkBx_Xpath").replace("value", value);
		Thread.sleep(2000);
		ElementHelper.waitForElement(LabelLocator, searchScreenWaitSec);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		if(!ElementHelper.isElementPresent(checkBxXpath))
			Thread.sleep(2000);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		for(int i=0;i<5;i++)
		{
			try {
				CheckBoxHelper.check(checkBxXpath);
		      break;
			}
			catch (Exception e) {
				Log4jHelper.logInfo(" this exception "+e.getMessage()+" in account filter ");
			}
			finally {
				Log4jHelper.logInfo(" Retrying for accountfilter with "+(i+1));
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		
		}
		GenericHelper.waitForLoadmask();
		searchHelper.clickSearch();
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for selecting bill profile using grid filter text box
	 */
	public void billProfileFilterTxtSearch(String windowWrapperId, String billProfileName) throws Exception {

		String screenName = NavigationHelper.getScreenTitle();
		Assert.assertEquals(screenName, "Bill Profile Search",
				" Screen titles are not matching on advance search operation of Bill Profile");
		SearchGridHelper.gridFilterSearchWithTextBox(windowWrapperId, "PS_searchBillProfile_gridColFilterTxtId",
				billProfileName, "Bill Profile Name");
		GenericHelper.waitForLoadmask();
		boolean rowValExists = GridHelper.isValuePresent(windowWrapperId, "SearchGrid", billProfileName,
				"Bill Profile Name");
		Assert.assertTrue(rowValExists, "Service name does not exist : " + billProfileName);
		GridHelper.clickRow("PS_popUpWindowId", "SearchGrid", billProfileName, "Bill Profile Name");
		ButtonHelper.clickIfEnabled("OK_Button_ByID");
		GenericHelper.waitForLoadmask();

	}

	/*
	 * Method for selecting bill profile using advance filter
	 */
	public void billProfileAdvanceFilter(String gridId, String filterHeaderName, String billProfileName)
			throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		String advanceSearchBtn = GenericHelper.getORProperty("PS_suggestionFilterAdvanceTextXpath")
				.replace("filterTxtId", "billProfile");
		String bipSearchBtnLocator = GenericHelper.getORProperty("PS_suggestionFilterSearchBtnXpath")
				.replace("filterTxtId", "billProfile");
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		// genericObj.waitforHeaderElement( "Profile Name" );
		searchHelper.clickFilterIcon(gridId, filterHeaderName);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		ElementHelper.click(advanceSearchBtn);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		Thread.sleep(1000);
		genericObj.waitforPopupHeaderElement("Bill Profile Name");
		searchHelper.clickFilterIcon("PS_popUpWindowId", "SearchGrid", "Bill Profile Name");
		TextBoxHelper.type("PS_popUpWindowId", "PS_searchBillProfile_gridColFilterTxtId", billProfileName);
		GenericHelper.waitForLoadmask();
		searchHelper.clickSearch();
		String bipGridRowXpath = "//div[@id='grid_column_header_filtersearchGrid_pbipName']/ancestor::div[@id='popupWindow']//div[@id='searchGrid']//div[contains(@class, 'roc-datagrid')]/div/div//table/tbody/tr//div[text()='txtVal']";
		String locator = bipGridRowXpath.replace("txtVal", billProfileName);
		ElementHelper.waitForElement(locator, searchScreenWaitSec);
		boolean rowValExists = ElementHelper.isElementPresent(locator);
		Assert.assertTrue(rowValExists, "bill profile does not exist" + "billProfileName");
		ButtonHelper.click(locator);
		GenericHelper.waitForLoadmask();
		ButtonHelper.clickIfEnabled("PS_bipPopUpSaveBtnXpath");
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement(bipSearchBtnLocator, searchScreenWaitSec);
		ButtonHelper.click(bipSearchBtnLocator);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * thos method is for bill profile advance search in bill screens
	 */
	public void testbillProfileAdvanceSearch(String gridId, String filterHeaderName, String billProfileName)
			throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		String advanceSearchBtn = GenericHelper.getORProperty("PS_suggestionFilterAdvanceTextXpath")
				.replace("filterTxtId", "billProfile");
		String bipSearchBtnLocator = GenericHelper.getORProperty("PS_suggestionFilterSearchBtnXpath")
				.replace("filterTxtId", "billProfile");
		genericObj.waitforHeaderElement("Profile Type");
		searchHelper.clickFilterIcon(gridId);
		ElementHelper.waitForElement(advanceSearchBtn, searchScreenWaitSec);
		ElementHelper.click(advanceSearchBtn);
		GenericHelper.waitForLoadmask();
		Thread.sleep(1000);
		genericObj.waitforPopupHeaderElement("Bill Profile Name");
		searchHelper.clickFilterIcon("PS_popUpWindowId", "SearchGrid", "Bill Profile Name");
		TextBoxHelper.type("PS_popUpWindowId", "PS_searchBillProfile_gridColFilterTxtId", billProfileName);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		searchHelper.clickSearch();
		String bipGridRowXpath = "//div[@id='grid_column_header_filtersearchGrid_pbipName']/ancestor::div[@id='popupWindow']//div[@id='searchGrid']//div[contains(@class, 'roc-datagrid')]/div/div//table/tbody/tr//div[text()='txtVal']";
		String locator = bipGridRowXpath.replace("txtVal", billProfileName);
		ElementHelper.waitForElement(locator, searchScreenWaitSec);
		boolean rowValExists = ElementHelper.isElementPresent(locator);
		Assert.assertTrue(rowValExists, "bill profile does not exist" + "billProfileName");
		ButtonHelper.click(locator);
		GenericHelper.waitForLoadmask();
		ButtonHelper.clickIfEnabled("PS_bipPopUpSaveBtnXpath");
		GenericHelper.waitForLoadmask();
		ElementHelper.waitForElement(bipSearchBtnLocator, searchScreenWaitSec);
		ButtonHelper.click(bipSearchBtnLocator);
		GenericHelper.waitForLoadmask();
	}

	public static int gridFilterAdvancedSearch(String txtBoxIdOrXpath, String value, String columnHeader)
			throws Exception {
		try {
			int row = 0;

			if (ValidationHelper.isNotEmpty(value)) {
				SearchHelper searchHelper = new SearchHelper();
				boolean isWrapperPresent = ElementHelper.isElementPresent("popupWindow");

				if (isWrapperPresent)
					searchHelper.clickFilterIcon("popupWindow", "SearchGrid", columnHeader);
				else
					searchHelper.clickFilterIcon("SearchGrid", columnHeader);

				String LabelLocator = or.getProperty("New_GridFilter_Label").replace("value", value);
				String ckBoxLocator = or.getProperty("New_GridFilter_CheckBox");
				TextBoxHelper.type("Grid_Filter_Panel", txtBoxIdOrXpath, value);
				String txtBox = GenericHelper.getORProperty(txtBoxIdOrXpath);
				ElementHelper.waitForAttribute(txtBox, "class", "gwt-TextBox IsEntitySuggest popUpFieldStyle",
						searchScreenWaitSec);
				Thread.sleep(1000);
				ElementHelper.waitForElement(LabelLocator, searchScreenWaitSec);
				CheckBoxHelper.check(LabelLocator + ckBoxLocator);
				GenericHelper.waitForLoadmask();
				searchHelper.clickSearch();
				GenericHelper.waitForLoadmask();
				if (isWrapperPresent)
					row = GridHelper.getRowNumber("popupWindow", "SearchGrid", value, columnHeader);
				else
					row = GridHelper.getRowNumber("SearchGrid", value, columnHeader);

			}

			return row;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is to filter and sort the calender filter
	 */
	public void calender(String calenderID, String filterColHeader) throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon("SearchGrid", filterColHeader);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		CalendarHelper.setToday(calenderID);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		ButtonHelper.click("SearchButton");
		GridHelper.sortGrid("SearchGrid", filterColHeader);
		GridHelper.sortGrid("SearchGrid", filterColHeader);
	}

}
