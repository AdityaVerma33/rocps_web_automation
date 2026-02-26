package com.subex.rocps.automation.helpers.application.carrierinvoice.invoicereconconfig;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

public class InvoiceReconConfigActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	public void clickNewAction( String clientPartition ) throws Exception
	{
		genericHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}
	
	public void viewOutputTablesAction(String name) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", name, "Name" );
		NavigationHelper.navigateToAction( "View Output Tables", "Output Tables" );
		assertEquals( NavigationHelper.getScreenTitle(), "View Output Tables" );
		
	}
	
	public void invoiceConfigRequestAction(String name) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", name, "Name" );
		NavigationHelper.navigateToAction( "Reconciliation Request", "Reconciliation Request" );
		assertEquals( NavigationHelper.getScreenTitle(), "Invoice Reconciliation Request" );
	}
	
	public void reorderAction(String name) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", name, "Name" );
		NavigationHelper.navigateToAction( "Reconciliation Request", "Reorder" );
		assertEquals( NavigationHelper.getScreenTitle(), "Edit Recon Reordering" );	
	}
}
