package com.subex.rocps.automation.helpers.application.carrierinvoice.carrierinvoicetemplate;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;

public class CarrierInvoiceTemplateActionImpl extends PSAcceptanceTest
{
	
	public void switchToTab( String tabName ) throws Exception
	{
		String xpath = "//div[text()='tabName']";
		String tabname = GenericHelper.getORProperty( xpath ).replace( "tabName", tabName );
		TabHelper.gotoTab( tabname );
		GenericHelper.waitForLoadmask();
		assertTrue( TabHelper.isSelected( tabname ), "Tab is not opened");
	}
	
	public void  navigateToSaveAsAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Common Tasks", "Save As" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "New Excel Invoice Template");
	}
	
	
	public void  navigateToApproveAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Template Actions", "Approve" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void  navigateToDiscontinueAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Template Actions", "Discontinue" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void  navigateToMoveToDraftAction() throws Exception
	{
		NavigationHelper.navigateToAction( "Template Actions", "Move back to Draft" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	public void  navigateToViewOutputTablesAction() throws Exception
	{
		NavigationHelper.navigateToAction( "View Output Tables", "Output Tables" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "View Output Tables" );
	}
}
