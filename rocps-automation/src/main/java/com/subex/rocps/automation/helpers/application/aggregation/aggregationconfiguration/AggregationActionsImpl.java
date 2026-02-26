package com.subex.rocps.automation.helpers.application.aggregation.aggregationconfiguration;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class AggregationActionsImpl extends PSAcceptanceTest {

	PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * Method for clicking new action in aggregation search screen
	 */
	public void clickNewAction(String clientPartition) throws Exception {

		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for changing status : Draft -> Accepted
	 */
	public void setAggregationStatusAccepted(String aggregationName) throws Exception {

		GridHelper.clickRow("SearchGrid", aggregationName, "Name");		
		assertTrue(GridHelper.isValuePresent( "SearchGrid", "Draft", "Status" ));
		NavigationHelper.navigateToAction("Change Status", "Accept Aggregation Configuration");
		PopupHelper.isTextPresent(
				"The aggregation cannot be edited after accepting. Are you sure you wish to change the aggregation status to accepted? ");
		ButtonHelper.click("yes");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method for clicking view result action
	 */
	public void viewResult(String aggregationName) throws Exception {
		GridHelper.clickRow("SearchGrid", aggregationName, "Name");
		NavigationHelper.navigateToAction("Aggregation Actions", "View Results");
		GenericHelper.waitForLoadmask();
		String AggrNametrimmedtmp = aggregationName.length() > 10 ? aggregationName.substring(0, 9) : aggregationName;
		String AggrNametrimmed="Results - " + AggrNametrimmedtmp + "...";
		//assertEquals( NavigationHelper.getScreenTitle(), AggrNametrimmed );
		
	}

	/*
	 * Method for Discontinue Aggregation Configuration
	 */
	public void discontinueAggregationConfiguration(String aggregationName) throws Exception {
		GridHelper.clickRow("SearchGrid", aggregationName, "Name");
		NavigationHelper.navigateToAction("Change Status", "Accept Aggregation Configuration");
		PopupHelper.isTextPresent(
				"This change cannot be reverted. Are you sure you wish to change the status of the Aggregation Configuration to Discontinued? If Yes, Please do the necessary changes in respective Aggregation Processor and Stream Stages.");
		ButtonHelper.click("yes");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking delete action in aggregation search screen
	 */
	public void clickDeleteAction(String aggregationName) throws Exception {
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
		genHelperObj.clickDeleteOrUnDeleteAction(aggregationName, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in aggregation search screen
	 */
	public void clickUnDeleteAction(String aggregationName) throws Exception {
		assertTrue(GridHelper.isValuePresent("SearchGrid", "Draft", "Status"));
		genHelperObj.clickDeleteOrUnDeleteAction(aggregationName, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}

}
