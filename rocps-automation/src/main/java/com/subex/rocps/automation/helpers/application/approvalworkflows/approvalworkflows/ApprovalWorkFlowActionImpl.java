package com.subex.rocps.automation.helpers.application.approvalworkflows.approvalworkflows;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PopupHelper;

public class ApprovalWorkFlowActionImpl extends PSAcceptanceTest
{
protected PSGenericHelper genHelperObj = new PSGenericHelper();
	
	/*
	 * This method is to click new action
	 */
	public void clickNewAction(String clientPartition) throws Exception {
		genHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}	
	
	public void addApprovalStage() throws Exception
	{
		ButtonHelper.click( "approvalStagesGridToolbar.Add" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Approval Stage" );
	}
	
	public void changeStatusAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Change Status", "Accept Approval Workflow" );
		GenericHelper.waitForLoadmask();
		assertTrue(PopupHelper.isTextPresent( "Are you sure you want to Accept Approval Workflow?" ));
		ButtonHelper.click( "YesButton" );
		Thread.sleep(1000);
		GenericHelper.waitForLoadmask(searchScreenWaitSec);
		assertTrue(PopupHelper.isTextPresent( "Approval Workflow is accepted successfully" ));
		ButtonHelper.click( "OK_TRT_Button" );
		GenericHelper.waitForLoadmask();
	}
	
	public void discontinueApprovalStatus() throws Exception
	{
		NavigationHelper.navigateToAction( "Change Status", "Discontinue Approval Workflow" );
		GenericHelper.waitForLoadmask();
	}

	public void clickEditAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		GenericHelper.waitForLoadmask();
	}
	/*
	 * This method is to  delete event Leg code group
	 */
	public void clickDeleteAction(String name) throws Exception {
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Delete");
		GenericHelper.waitForLoadmask();

	}
	
	/*
	 * This method is to undelete event Leg code group
	 */
	public void clickUnDeleteACtion(String name) throws Exception
	{
		genHelperObj.clickDeleteOrUnDeleteAction(name, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
