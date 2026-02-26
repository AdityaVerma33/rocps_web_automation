package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import java.util.Map;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillProfilePaymentConfigTab extends PSAcceptanceTest
{

	protected String balanceThreshold;
	protected String amountThreshold;
	protected String balanceCurrency;
	protected String crossFxGroup;
	protected String allocationStrategy;
	protected Map<String, String> bipPaymtTabMap = null;

	/*
	 * Constructor initialising map and paramVal
	 */
	public BillProfilePaymentConfigTab( Map<String, String> bipMap )
	{

		this.bipPaymtTabMap = bipMap;

		initInstVariables();
	}

	/*
	 * Method: initialising instance variables
	 */
	protected void initInstVariables()
	{

		balanceThreshold = bipPaymtTabMap.get( "BalanceThreshold" );
		amountThreshold = bipPaymtTabMap.get( "AmountThreshold" );
		balanceCurrency = bipPaymtTabMap.get( "BalanceCurrency" );
		crossFxGroup = bipPaymtTabMap.get( "CrossFxGroup" );
		allocationStrategy = bipPaymtTabMap.get( "AllocationStrategy" );
	}

	/*
	 * Method for configuring payment configuration tab
	 */
	public void paymentConfigurationTabConfig() throws Exception
	{

		TabHelper.gotoTab( "//div[text()='Payment Configuration']" );
		TextBoxHelper.type( "detail_bip_balanceThreshold_txtId", balanceThreshold );

		ComboBoxHelper.select( "thresholdDetails", "detail_bip_amountThreshold_comboId", amountThreshold );

		ComboBoxHelper.select( "detail_bip_balanceCurrency_comboId", balanceCurrency );
		ComboBoxHelper.select( "detail_bip_crossFxGroup_comboId", crossFxGroup );

		ComboBoxHelper.select( "detail_bip_allocationStrategy_comboId", allocationStrategy );

	}

	/*
	 * Method for edit payment configuration tab
	 */
	public void editpaymentConfigurationTabConfig() throws Exception
	{

		TabHelper.gotoTab( "//div[text()='Payment Configuration']" );
		if ( ValidationHelper.isNotEmpty( balanceThreshold ) )
			TextBoxHelper.type( "detail_bip_balanceThreshold_txtId", balanceThreshold );
		if ( ValidationHelper.isNotEmpty( amountThreshold ) )
			ComboBoxHelper.select( "thresholdDetails", "detail_bip_amountThreshold_comboId", amountThreshold );
		if ( ValidationHelper.isNotEmpty( balanceCurrency ) )
			ComboBoxHelper.select( "detail_bip_balanceCurrency_comboId", balanceCurrency );
		if ( ValidationHelper.isNotEmpty( crossFxGroup ) )
			ComboBoxHelper.select( "detail_bip_crossFxGroup_comboId", crossFxGroup );

		if ( ValidationHelper.isNotEmpty( allocationStrategy ) )
			ComboBoxHelper.select( "detail_bip_allocationStrategy_comboId", allocationStrategy );

	}

}
