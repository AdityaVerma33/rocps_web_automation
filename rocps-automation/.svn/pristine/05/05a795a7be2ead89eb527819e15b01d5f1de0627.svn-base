package com.subex.rocps.automation.helpers.application.networkConfiguraiton.rulestringset;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

public class RuleStringSetActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	
	/*
	 * This method is to click new action
	 */
	public void clickNewAction(String clientPartition) throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}
	
	/*
	 * This method is to add rule string
	 */
	public void addRuleString() throws Exception
	{
		ButtonHelper.click("ruleGridToolbar.Add");	
		GenericHelper.waitForLoadmask();
	}
	
	public void movedownRuleString(String match) throws Exception
	{
		GridHelper.clickRow( "ruleStringGrid", match, "Match" );
		NavigationHelper.navigateToAction("ruleGridToolbar.moveDown");
		GenericHelper.waitForLoadmask();
	}
	
	public void moveupRuleString(String match) throws Exception
	{
		GridHelper.clickRow( "ruleStringGrid", match, "Match" );
		NavigationHelper.navigateToAction("ruleGridToolbar.moveUp");
		GenericHelper.waitForLoadmask();
	}
	
	public void deleteRuleString(String match) throws Exception
	{
		GridHelper.clickRow( "ruleStringGrid", match, "Match" );
		NavigationHelper.navigateToAction("ruleGridToolbar.Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to  delete rule string set
	 */
	public void clickDeleteAction(String name) throws Exception {
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask();

	}
	
	/*
	 * This method is to undelete rulestringset
	 */
	public void clickUnDeleteACtion(String name) throws Exception
	{
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
