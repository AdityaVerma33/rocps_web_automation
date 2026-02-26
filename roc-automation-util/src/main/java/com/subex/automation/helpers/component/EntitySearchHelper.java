package com.subex.automation.helpers.component;

import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class EntitySearchHelper extends ComponentHelper {
	
	private static String gridId = "SearchGrid";
	private static String gridWrapper = "popupWindow";
	
	public EntitySearchHelper() throws Exception {
		try {
			gridWrapper = "popupWindow";
			gridId = "SearchGrid";
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public EntitySearchHelper(String gridID) throws Exception {
		try {
			gridId = gridID;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public EntitySearchHelper(String gridWrapperID, String gridID) throws Exception {
		try {
			gridWrapper = gridWrapperID;
			gridId = gridID;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	private void selectValue(int row, String value, String valueColumnHeader) throws Exception {
		try {
			if (row > 0) {
				GridHelper.clickRow(gridWrapper, gridId, value, valueColumnHeader);
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
			else
				FailureHelper.failTest("Row with value '" + value + "' not found in Entity Search screen");
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void select(String entityScreenTitle, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				if (ButtonHelper.isPresent("SearchButton")) {
					ButtonHelper.click("SearchButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				GridHelper.clickRow(gridWrapper, gridId, value, valueColumnHeader);
				ButtonHelper.click("OKButton");
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectUsingSearchTextBox(String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				int row = SearchGridHelper.searchWithTextBox(txtBoxIdOrXpath, value, valueColumnHeader);
				selectValue(row, value, valueColumnHeader);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectUsingSearchComboBox(String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				int row = SearchGridHelper.searchWithComboBox(comboIdOrXpath, value, valueColumnHeader);
				selectValue(row, value, valueColumnHeader);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectUsingGridFilterTextBox(String entityScreenTitle, String txtBoxIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				int row = SearchGridHelper.gridFilterSearchWithTextBox(txtBoxIdOrXpath, value, valueColumnHeader);
				selectValue(row, value, valueColumnHeader);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectUsingGridFilterComboBox(String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				int row = SearchGridHelper.gridFilterSearchWithComboBox(comboIdOrXpath, value, valueColumnHeader);
				selectValue(row, value, valueColumnHeader);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
	
	public void selectUsingGridFilterAdvancedSearch(String entityScreenTitle, String comboIdOrXpath, String value, String valueColumnHeader) throws Exception {
		try {
			if (ValidationHelper.isNotEmpty(value)) {
				GenericHelper.waitForLoadmask(searchScreenWaitSec);
				assertTrue(LabelHelper.isTitlePresent(entityScreenTitle), "Entity Search '" + entityScreenTitle + "' did not appear.");
				if (ButtonHelper.isPresent("ClearButton")) {
					ButtonHelper.click("ClearButton");
					GenericHelper.waitForLoadmask(searchScreenWaitSec);
				}
				
				int row = SearchGridHelper.gridFilterAdvancedSearch(comboIdOrXpath, value, valueColumnHeader);
				selectValue(row, value, valueColumnHeader);
			}
		} catch (AssertionError e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}
}