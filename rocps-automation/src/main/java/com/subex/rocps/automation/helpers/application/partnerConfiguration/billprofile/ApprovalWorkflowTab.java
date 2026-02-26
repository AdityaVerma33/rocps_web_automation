package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import java.util.Map;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class ApprovalWorkflowTab extends PSAcceptanceTest
{
	protected Map<String, String> bipApproWflwMap = null;
	protected String billApprovalWflw;
	protected String creditApprovalWflw;
	protected String disputeApprovalWflw;

	/**
	 *Constructor
	 * @param bipApproWflwMap
	 */
	public ApprovalWorkflowTab( Map<String, String> bipApproWflwMap )
	{

		this.bipApproWflwMap = bipApproWflwMap;
	}

	/*
	 * Method: initialising instance variables
	 */
	/*protected void initializeVariables( Map<String, String> map ) throws Exception
	{
		billApprovalWflw = ExcelHolder.getKey( map, "BillApprovalWorkflow" );
		creditApprovalWflw = ExcelHolder.getKey( map, "CreditApprovalWorkflow" );
		disputeApprovalWflw = ExcelHolder.getKey( map, "DisputeApprovalWorkflow" );
	}
	*/
	/*
	 * Method: initialising instance variables
	 */
	protected void initializeVariables() throws Exception
	{
		billApprovalWflw = bipApproWflwMap.get( "BillApprovalWorkflow" );
		creditApprovalWflw = bipApproWflwMap.get( "CreditApprovalWorkflow" );
		disputeApprovalWflw = bipApproWflwMap.get( "DisputeApprovalWorkflow" );
	}

	public void approvalWflwTabConfig() throws Exception
	{
		TabHelper.gotoTab( GenericHelper.getORProperty( "detail_bip_ApprWflwTab_xpath" ) );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "detail_bip_ApprWflwTab_labelxpath" ), searchScreenWaitSec );
		approvalWflwconfig();
	}
	/*
	 * Method for configuring Approval Workflow
	 */

	private void approvalWflwconfig() throws Exception
	{
		initializeVariables();
		if ( ValidationHelper.isNotEmpty( billApprovalWflw ) )
			ComboBoxHelper.select( "detail_bip_billApprWflw_comboId", billApprovalWflw );
		if ( ValidationHelper.isNotEmpty( creditApprovalWflw ) )
			ComboBoxHelper.select( "detail_bip_creditApprWflw_comboId", creditApprovalWflw );
		if ( ValidationHelper.isNotEmpty( disputeApprovalWflw ) )
			ComboBoxHelper.select( "detail_bip_disputeApprWflw_comboId", disputeApprovalWflw );
	}

}
