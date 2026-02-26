package com.subex.rocps.automation.helpers.application.partnerConfiguration.account;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class AccountActionImpl extends PSAcceptanceTest {
	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * This method is to click new action in account
	 */
	public void clickNewAction(String clientPartition) throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
	}

	/*
	 * This method is for billProfile Action
	 */
	public void billProfileAction() throws Exception {

		PSGenericHelper.waitForParentActionElementTOBeclickable( "Bill Profile" );
		NavigationHelper.navigateToAction("Bill Profile", "New Bill Profile");
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		assertEquals(ElementHelper.isElementPresent(or.getProperty("Detail_screenTitleXpath")), true,
				"detail screen is not opened");
	}
	
	/*
	 * this method is for billprofile action
	 */
	public void operatorAction() throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Operator" );
		NavigationHelper.navigateToAction("Operator", "New Operator");
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		assertEquals(NavigationHelper.getScreenTitle(), "New Operator", "operator screen is not opened");
	}
	
	/*
	 * this method is to view aggregation results action
	 */
	public void viewAggreagtionResultsAction(String billProfile, String aggregationName) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "View Aggregation Results" );
		NavigationHelper.navigateToAction("View Aggregation Results", billProfile, aggregationName);		
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		assertEquals(NavigationHelper.getScreenTitle(), "Results - VoiceAggr...", "Aggregation screen is not opened");
	}
	
	/*
	 * this method is for account change status
	 */
	public void changeStatusAction() throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Change Status" );
		NavigationHelper.navigateToAction("Change Status", "Terminate Account");
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		assertEquals(NavigationHelper.getScreenTitle(), "Confirm", "Terminate Account popup is not opened");
	}
	/*
	 * this method is for view product bundles
	 */
	public void viewProductAction(String billProfileName) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "Products" );
		NavigationHelper.navigateToAction( "Products", billProfileName );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals(NavigationHelper.getScreenTitle(), "Product Instance Search", "Product Instance screen is not opened");		
	}
	
	/*
	 * This method is for view Event Match Rules
	 */
	public void viewEventMatchRules(String eventMatchRule) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "View Event Match Rules" );
		NavigationHelper.navigateToAction( "View Event Match Rules", eventMatchRule );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals(NavigationHelper.getScreenTitle(), "Edit Event Match Rule", "Edit Event Match Rule screen is not opened");
		
	}
	/*
	 * This method is to view event usage
	 */
	public void viewEventUsage(String billProfile) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable( "View Event Usage" );
		NavigationHelper.navigateToAction( "View Event Usage", billProfile );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals(NavigationHelper.getScreenTitle(), "Event Usage Search", "Event Usage search screen is not opened");
		
	}
}
