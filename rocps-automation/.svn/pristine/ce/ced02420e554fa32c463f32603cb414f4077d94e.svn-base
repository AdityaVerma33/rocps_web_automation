package com.subex.rocps.automation.helpers.application.filters;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EntityFilterHelper extends PSAcceptanceTest {

	DataSelectionHelper dataFilterObj = new DataSelectionHelper();

	public void accountEntitySearch(String accName, String entitySearchBtnId) throws Exception {
		try {
			ButtonHelper.click(or.getProperty("Detail_parentAccountEntityId"));
			GenericHelper.waitForLoadmask();
			dataFilterObj.accountSelection(accName);

		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * This method is for agentEntitySearch in agent screen and account screen
	 */
	public void agentEntitySearch(String compName, String entitySearchBtnId) throws Exception {
		try {
			ButtonHelper.click(entitySearchBtnId);
			GenericHelper.waitForLoadmask();
			dataFilterObj.agentSelection(compName);
		} catch (Exception e) {

			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * Method: Event type selection using entitiy search
	 */
	public void eventTypeEntitySearch(String entSrchBtnId, String eventTypeName) throws Exception {
		ButtonHelper.click(entSrchBtnId);
		GenericHelper.waitForLoadmask();
		dataFilterObj.eventTypeSelection(eventTypeName);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method: route group selection using entity search
	 */
	public void routeGroupEntitySearch(String entSrchBtnId, String routeGroupName) throws Exception {
		ButtonHelper.click(entSrchBtnId);
		GenericHelper.waitForLoadmask();
		dataFilterObj.routeGrpSelection(routeGroupName);
		GenericHelper.waitForLoadmask();
	}

	public void dialStringEntitySearch(String entSrchBtnId, String dialStringName) throws Exception {
		ButtonHelper.click(entSrchBtnId);
		GenericHelper.waitForLoadmask();
		dataFilterObj.dialStringSelection(dialStringName);
		GenericHelper.waitForLoadmask();
	}

	public void identifierValEntitySearch(String entSrchBtnId, String identifierVal) throws Exception {
		ButtonHelper.click(entSrchBtnId);
		GenericHelper.waitForLoadmask();
		dataFilterObj.stringSelection(identifierVal);
		GenericHelper.waitForLoadmask();
	}
}
