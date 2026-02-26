package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillProfileBillProductionTab extends PSAcceptanceTest
{

	protected String billPackage;
	protected String productionPath;
	protected String consolidatedBills;
	protected String suppressBillProductionFlag;
	protected String suppressBillProductionDate;
	protected String produceBillFlag;
	protected String billGeneration;
	protected String marketingMessage;
	protected String defultMarketingMessageFl;
	protected String includeExtractBillFl;
	protected String configProvisionAmtFlag;
	protected String provisionAmtType;
	protected String provisionAmount;
	protected String provisionCurrency;
	protected String amountThreshold;
	protected String lineOfBusiness;
	protected Map<String, String> bipBilProdMap = null;

	/*
	 * Constructor initialising map and paramVal
	 */
	public BillProfileBillProductionTab( Map<String, String> bipMap )
	{

		this.bipBilProdMap = bipMap;

		initInstVariables();
	}

	/*
	 * Method: initialising instance variables
	 */
	protected void initInstVariables()
	{

		billPackage = bipBilProdMap.get( "BillPackage" );
		productionPath = bipBilProdMap.get( "ProductionPath" );
		consolidatedBills = bipBilProdMap.get( "ConsolidatedBills" );
		suppressBillProductionFlag = bipBilProdMap.get( "SuppressBillProductionFlag" );
		suppressBillProductionDate = bipBilProdMap.get( "SuppressBillProductionDate" );
		produceBillFlag = bipBilProdMap.get( "ProduceBillFlag" );
		billGeneration = bipBilProdMap.get( "BillGeneration" );
		marketingMessage = bipBilProdMap.get( "MarketingMessage" );
		defultMarketingMessageFl = bipBilProdMap.get( "DefultMarketingMessageFl" );
		includeExtractBillFl = bipBilProdMap.get( "IncludeXdrBillFlag" );
		configProvisionAmtFlag = bipBilProdMap.get( "ConfigProvisionAmtFlag" );
		provisionAmtType = bipBilProdMap.get( "ProvisionAmtType" );
		provisionAmount = bipBilProdMap.get( "ProvisionAmount" );
		provisionCurrency = bipBilProdMap.get( "ProvisionCurrency" );
		amountThreshold = bipBilProdMap.get( "AmountThreshold" );
		lineOfBusiness = bipBilProdMap.get( "LineOfBusiness" );

	}

	/*
	 * Method for configuring bill production tab config
	 */
	public void billProductionTabConfig() throws Exception
	{

		TabHelper.gotoTab( "//div[text()='Bill Production']" );
		GenericHelper.waitForElement( "//div[@id='tabBillPackage']", "5" );

		billPackageConfig();
		billProductionConfig();
		billGenerationConfig();
		accrualsProvisionDetails();
		//extraInformation();

	}

	/*
	 * Method for edit bill production tab config
	 */
	public void editbillProductionTabConfig() throws Exception
	{

		TabHelper.gotoTab( "//div[text()='Bill Production']" );
		GenericHelper.waitForElement( "//div[@id='tabBillPackage']", "5" );

		editbillPackageConfig();
		editbillProductionConfig();
		editbillGenerationConfig();
		editaccrualsProvisionDetails();
		extraInformation();

	}

	/*
	 * Method for configuring bill package panel
	 */
	protected void billPackageConfig() throws Exception
	{

		ComboBoxHelper.select( "detail_bip_billPackage_comboId", billPackage );
	}

	/*
	 * Method for configuring bill package panel
	 */
	protected void editbillPackageConfig() throws Exception
	{

		if ( ValidationHelper.isNotEmpty( billPackage ) )
			ComboBoxHelper.select( "detail_bip_billPackage_comboId", billPackage );
	}

	/*
	 * Method for configuring bill generation
	 */
	protected void billGenerationConfig() throws Exception
	{

		ComboBoxHelper.select( "detail_bip_billGeneration_comboId", billGeneration );

		if ( !marketingMessage.isEmpty() )
		{

			ButtonHelper.click( "detail_bip_marketingMeg_entitySrchId" );
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox( "SearchGrid", "detail_bip_marketingMsgName_gridFilterId", marketingMessage, "Name" );
			GridHelper.clickRow( "SearchGrid", marketingMessage, "Name" );
			ButtonHelper.click( "OK_Button_ByID" );
		}

		if ( !Boolean.valueOf( defultMarketingMessageFl ) )
			CheckBoxHelper.uncheck( "detail_bip_defaultMarketingMsgFlag_checkId" );
		if ( ValidationHelper.isTrue( includeExtractBillFl ) )
			CheckBoxHelper.check( "detail_bip_includeXdrBillFlag_checkId" );

	}
	/*
	 * Method for edit bill generation
	 */
	protected void editbillGenerationConfig() throws Exception
	{
		
		if ( ValidationHelper.isTrue( billGeneration ) )
		   ComboBoxHelper.select( "detail_bip_billGeneration_comboId", billGeneration );

		if ( !marketingMessage.isEmpty() )
		{

			ButtonHelper.click( "detail_bip_marketingMeg_entitySrchId" );
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox( "SearchGrid", "detail_bip_marketingMsgName_gridFilterId", marketingMessage, "Name" );
			GridHelper.clickRow( "SearchGrid", marketingMessage, "Name" );
			ButtonHelper.click( "OK_Button_ByID" );
		}
		if ( ValidationHelper.isTrue( defultMarketingMessageFl ) )
			CheckBoxHelper.check( "detail_bip_defaultMarketingMsgFlag_checkId" );
		if ( ValidationHelper.isFalse( defultMarketingMessageFl ) )
			CheckBoxHelper.check( "detail_bip_defaultMarketingMsgFlag_checkId" );
		if ( ValidationHelper.isTrue( includeExtractBillFl ) )
			CheckBoxHelper.check( "detail_bip_includeXdrBillFlag_checkId" );
		if ( ValidationHelper.isFalse( includeExtractBillFl ) )
			CheckBoxHelper.uncheck( "detail_bip_includeXdrBillFlag_checkId" );

	}

	/*
	 * Method for configuring bill production config
	 */
	protected void billProductionConfig() throws Exception
	{

		ComboBoxHelper.select( "detail_bip_billProduction_comboId", productionPath );

		ComboBoxHelper.select( "detail_bip_consolidateBills_comboId", consolidatedBills );

		if ( Boolean.valueOf( suppressBillProductionFlag ) )
		{
			RadioHelper.click( "detail_bip_suppressBillProduction_radioId" );
			TextBoxHelper.type( "detail_bip_suppressBillDate_calXpath", suppressBillProductionDate );
		}

	}

	/*
	 * Method for edit bill production config
	 */
	protected void editbillProductionConfig() throws Exception
	{
		if(ValidationHelper.isNotEmpty( productionPath ))
		ComboBoxHelper.select( "detail_bip_billProduction_comboId", productionPath );

		if(ValidationHelper.isNotEmpty( consolidatedBills ))
		ComboBoxHelper.select( "detail_bip_consolidateBills_comboId", consolidatedBills );

		if ( Boolean.valueOf( suppressBillProductionFlag ) )
		{
			RadioHelper.click( "detail_bip_suppressBillProduction_radioId" );
			TextBoxHelper.type( "detail_bip_suppressBillDate_calXpath", suppressBillProductionDate );
		}

	}

	/*
	 * Method for configuring accruals provision
	 */
	protected void accrualsProvisionDetails() throws Exception
	{
		if ( Boolean.valueOf( configProvisionAmtFlag ) )
		{
			CheckBoxHelper.check( "detail_bip_configProvisionAmt_checkId" );
			ComboBoxHelper.select( "detail_bip_provisionAmtType_comboId", provisionAmtType );
			TextBoxHelper.type( "detail_bip_provisionAmtInput_TxtId", provisionAmount );
			ComboBoxHelper.select( "accrualsProvisions", "provisionAmtProvisionCurrency_gwt_uid_", provisionCurrency );
		}
	}
	
	protected void extraInformation() throws Exception
	{
		if(ValidationHelper.isNotEmpty(lineOfBusiness  ))
			ComboBoxHelper.select( "Line_Of_Business_gwt_uid_", lineOfBusiness );
	}


	/*
	 * Method for edit accruals provision
	 */
	protected void editaccrualsProvisionDetails() throws Exception
	{
		if ( ValidationHelper.isTrue( configProvisionAmtFlag ) )
		{
			CheckBoxHelper.check( "detail_bip_configProvisionAmt_checkId" );
			ComboBoxHelper.select( "detail_bip_provisionAmtType_comboId", provisionAmtType );
			TextBoxHelper.type( "detail_bip_provisionAmtInput_TxtId", provisionAmount );
			ComboBoxHelper.select( "accrualsProvisions", "provisionAmtProvisionCurrency_gwt_uid_", provisionCurrency );
		}
		if ( ValidationHelper.isFalse( configProvisionAmtFlag ) )
			CheckBoxHelper.uncheck( "detail_bip_configProvisionAmt_checkId" );
	}

}
