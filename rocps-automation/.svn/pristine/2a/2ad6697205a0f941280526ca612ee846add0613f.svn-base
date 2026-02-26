package com.subex.rocps.automation.helpers.application.bills.creditnotes;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;

public class CreditNotesActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper genHelperObj = new PSGenericHelper();

	/*
	 * This method is to click new action
	 */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		PSGenericHelper.waitForParentActionElementTOBeclickable("Common Tasks");
		genHelperObj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask();
	}
	
	public void clickEditAction( int row ) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", row, 1 );
		PSGenericHelper.waitForParentActionElementTOBeclickable("Common Tasks");
		genHelperObj.validateActionText( "Common Tasks", "Edit");
		NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
		GenericHelper.waitForLoadmask();
	}

	public void addBillLinkedToCredit() throws Exception
	{
		ButtonHelper.click( "billGridToolBar.Add" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Bill Search" );
	}

	public void addCreditLineItem() throws Exception
	{
		ButtonHelper.click( "creditGridToolbar.Add" );
		GenericHelper.waitForLoadmask();
		//assertEquals( NavigationHelper.getScreenTitle(), "Credit Line Item" );
	}

	public void clickApproveCreditNoteAction( int row ) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", row, "Status" );
		PSGenericHelper.waitForParentActionElementTOBeclickable("Credit Note Actions");
		genHelperObj.validateActionText( "Credit Note Actions", "Approve Credit Note" );
		NavigationHelper.navigateToAction( "Credit Note Actions", "Approve Credit Note" );
		GenericHelper.waitForLoadmask();
	}

	public void clickCancelCreditNoteAction( int row ) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", row, "Status" );
		PSGenericHelper.waitForParentActionElementTOBeclickable("Credit Note Actions");
		NavigationHelper.navigateToAction( "Credit Note Actions", "Cancel Credit Note" );
		GenericHelper.waitForLoadmask();
	}

	public void creditHistoryAction( int row ) throws Exception
	{
		GridHelper.clickRow( "SearchGrid", row, "Status" );
		PSGenericHelper.waitForParentActionElementTOBeclickable("Jump To");
		NavigationHelper.navigateToAction( "Jump To", "Credit History" );
		GenericHelper.waitForLoadmask();
	}

}
