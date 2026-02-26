package com.subex.rocps.automation.helpers.application.partnerConfiguration.agent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class AgentActionImpl extends PSAcceptanceTest {
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is to click new Action for Agent
	 */
	public void clicknewAgent(String clientPartition) throws Exception {
		assertEquals(NavigationHelper.getScreenTitle(), "Agent Search");
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
		NavigationHelper.selectPartition(clientPartition);
		assertEquals(NavigationHelper.getScreenTitle(), "New Agent");
	}

	/*
	 * This method is to save Agent
	 */
	public void saveAgent(String agent) throws Exception {
		ButtonHelper.click("Detail_agent_save_btn");
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals(NavigationHelper.getScreenTitle(), "Agent Search");
		ElementHelper.isElementPresent("//div[@id='searchPanel']");
		assertTrue(GridHelper.isValuePresent("SearchGrid", agent, "Agent"));
	}

}
