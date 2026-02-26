package com.subex.rocps.automation.helpers.application.system;

import org.testng.Assert;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class StreamImpl extends PSAcceptanceTest {

	PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * This method is to click new Action
	 */

	public void clickNewAction(String streamName, String partition) throws Exception {
		
			genHelperObj.clickNewAction(partition);
			GenericHelper.waitForLoadmask(searchScreenWaitSec);
			}

	/*
	 * This method is for Edit Stream action
	 */
	public void clickEditAction(String streamName) throws Exception {

		NavigationHelper.navigateToAction("Common Tasks", "Edit");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to add stream Stage button
	 */
	public void addStreamStageButton() throws Exception {

		ButtonHelper.click("streamStageTBModel.add");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to edit stream stage button
	 */
	public void editStreamStageButton() throws Exception {

		ButtonHelper.click("streamStageTBModel.edit");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to validate screen title
	 */
	public void validateScreenTitle() throws Exception {
		boolean flag = false;
		String screenName = NavigationHelper.getScreenTitle();
		Assert.assertEquals("New Stream", screenName, " screen name not matching");
	}
	
	/*
	 * This method is to delete Stream
	 */
	public void clickDeleteAction(String name) throws Exception {		
		GridHelper.clickRow("SearchGrid", name, "Name");
		NavigationHelper.navigateToAction("Common Tasks", "Delete");
		GenericHelper.waitForLoadmask();
		assertEquals(NavigationHelper.getScreenTitle(), "confirm");
		ButtonHelper.click("YesButton");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to un delete Stream
	 */
	public void clickUnDeleteAction(String name) throws Exception {
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
