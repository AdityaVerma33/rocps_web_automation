package com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconciliationrequest;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class InvoiceReconciliationReuestActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void clickNewAction( String clientPartition ) throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}
	
	
	public void viewReconResults() throws Exception
	{
		NavigationHelper.navigateToAction( "Reconcilation", "View Reconcilation" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	//	assertEquals( NavigationHelper.getScreenTitle(), "View Invoice Recon Config" );
	}
	
	public void viewResult() throws Exception
	{
		NavigationHelper.navigateToAction( "Reconcilation", "View Results" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Invoice Reconciliat..." );
	}
	public void clickScheduleNowAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Schedule Now" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void clickRescheduleAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Reconciliation Request Actions", "Reschedule" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void clickBaselineAction() throws Exception
	{
		NavigationHelper.navigateToAction("Reconciliation Actions", "Baseline" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
}
