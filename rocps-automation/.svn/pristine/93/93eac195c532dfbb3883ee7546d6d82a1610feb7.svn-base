package com.subex.rocps.automation.helpers.application.deal.deal;

import java.util.Arrays;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.component.TreeHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class DealDetailImpl extends PSAcceptanceTest
{
	protected String partition;
	protected String account;
	protected String currency;
	protected String from;
	protected String to;
	protected String contractNo;
	protected String bandType;
	protected String dealType;
	protected String incoming;
	protected String outgoing;
	protected String renewAfter;
	protected String applyRebate;
	protected String cashflowIn;
	protected String cashflowOut;
	protected String applyShortFall;
	protected String shortfalIn;
	protected String shortfalOut;
	protected String gracePeriodDays;
	protected String balancedDeal;
	protected String inRatio;
	protected String outRatio;
	protected String externalRateIn;
	protected String externalRateOut;
	protected String trafficType;
	protected String bandGroup;
	protected String band;
	protected String include;
	protected String tiersPerBandGroup;
	protected String gracePeriod;
	protected String bandFrom;
	protected String bandTo;
	protected String fromMinutes;
	protected String toMinutes;
	protected String commitment;
	protected String bestEffort;
	protected String baseTier;
	protected String backToNthTier;
	protected String capName;
	protected String totalVolume;
	protected String tierVolume;
	protected String condition;
	protected String rateType;
	protected String applyTo;
	protected String capPercentage;
	protected String ratePercentage;
	protected String capOn;
	protected String penalizeTotalTraffic;
	String[] bandGroupArr;
	String[] bandArr;
	String[] includeArr;
	String[] gracePeriodArr;
	String[] bandFromArr;
	String[] bandToArr;
	String[] fromMinutesArr;
	String[] toMinutesArr;
	String[] commitmentArr;
	String[] bestEffortArr;
	String[] baseTierArr;
	String[] backToNthTierArr;

	protected Map<String, String> map;
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genericObj = new PSGenericHelper();

	public DealDetailImpl( Map<String, String> map ) throws Exception
	{
		this.map = map;
		initialiseVariables( map );
	}

	public void newDeal() throws Exception
	{
		genericObj.clickNewAction( partition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue( ElementHelper.isElementPresent( "//div[text()='Deal Details']" ), "Detail Page is not loaded" );
	}

	public void basicDetails() throws Exception
	{
		PSDataComponentHelper.psSelectUsingGridFilterTextBox( "PS_Detail_Deal_account_filter_txtBxID", "Sales Region", "paccName", account, "Account Name" );
		ComboBoxHelper.select( "PS_Detail_Deal_currency_ComboID", currency );			
		TextBoxHelper.type( "PS_Detail_Deal_fromdate_txtID", date( from ) );
		TextBoxHelper.type( "PS_Detail_Deal_toDate_txtID", date( to ) );
		TextBoxHelper.type( "PS_Detail_Deal_contractNo_txtbxID", contractNo );
		ComboBoxHelper.select( "PS_Detail_Deal_bandType_comboID", bandType );
		if ( dealType.contains( "Tiered" ) )
		{
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
		if(!externalRateIn.isEmpty() || !externalRateOut.isEmpty() || !applyRebate.isEmpty())
			advanceConfigurations();
		}
		if ( dealType.contains( "Committed" ) )
		{
			dealType = "Tiered";
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
			advanceConfigurations();
		}

		if ( dealType.contains( "Balanced" )  )
		{
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
			if(PopupHelper.isTextPresent( "Tiers and Caps will be cleared. Do you wish to continue?" ))
				ButtonHelper.click( "YesButton" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			advanceConfigurations();
		}

	}

	public void advanceConfigurations() throws Exception
	{

		ButtonHelper.click( "advancedProperties" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertTrue(ElementHelper.isElementPresent( "//*[text()='Commitment']" ) );
			
		if ( dealType.contains( "Balanced" ) && !inRatio.isEmpty() || !outRatio.isEmpty() )
		{
			if ( !inRatio.isEmpty() )
				TextBoxHelper.type( "PS_Detail_Deal_inRatio_txtID", inRatio );
			if ( !outRatio.isEmpty() )
				TextBoxHelper.type( "PS_Detail_Deal_outRatio_txtID", outRatio );
		}else
		{		
			if ( ValidationHelper.isNotEmpty( renewAfter ) && ValidationHelper.isTrue( renewAfter ) )
				CheckBoxHelper.check( "PS_Detail_Deal_renewAfter_chcbx" );
			if (ValidationHelper.isNotEmpty( applyRebate ) &&  ValidationHelper.isTrue( applyRebate ) )
				CheckBoxHelper.check( "PS_Detail_Deal_applyRebate_chcbx" );

			if (ValidationHelper.isNotEmpty( cashflowIn ) &&  ValidationHelper.isTrue( cashflowIn ) )
				CheckBoxHelper.check( "PS_Detail_Deal_cashflowin_chckbx" );
			if (ValidationHelper.isNotEmpty( cashflowOut ) &&  ValidationHelper.isTrue( cashflowOut ) )
				CheckBoxHelper.check( "PS_Detail_Deal_cashflowout_chckbx" );

			if ( ValidationHelper.isNotEmpty( applyShortFall ) &&  ValidationHelper.isTrue( applyShortFall ) )
				CheckBoxHelper.check( "PS_Detail_Deal_applyshortfall_chckbx" );
			if ( ValidationHelper.isNotEmpty( shortfalIn ) &&  ValidationHelper.isTrue( shortfalIn ) )
				CheckBoxHelper.check( "PS_Detail_Deal_shortfallin_chckbx" );
			if ( ValidationHelper.isNotEmpty( shortfalOut ) &&  ValidationHelper.isTrue( shortfalOut ) )
				CheckBoxHelper.check( "PS_Detail_Deal_shortfallout_chckbx" );
			if ( ValidationHelper.isNotEmpty( gracePeriodDays ))
				TextBoxHelper.type( "PS_Detail_Deal_graceperiod_txtbxID", gracePeriodDays );
			if(ValidationHelper.isNotEmpty( externalRateIn ) && ValidationHelper.isTrue( externalRateIn ))
				CheckBoxHelper.check( "externalIn_InputElement" );
			if(ValidationHelper.isNotEmpty( externalRateOut ) && ValidationHelper.isTrue( externalRateOut ))
				CheckBoxHelper.check( "externalOut_InputElement" );
		}
		
		ButtonHelper.click( "PS_Detail_Deal_advProperty_okBtnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );

	}
	public void editBasicDetails() throws Exception
	{
		//if(ValidationHelper.isNotEmpty( account ))
			//PSDataComponentHelper.psSelectUsingGridFilterTextBox( "account", "Sales Region", "paccName", account, "Account Name" );
		
		ComboBoxHelper.select( "PS_Detail_Deal_currency_ComboID", currency );			
		TextBoxHelper.type( "PS_Detail_Deal_fromdate_txtID", date( from ) );
		TextBoxHelper.type( "PS_Detail_Deal_toDate_txtID", date( to ) );
		TextBoxHelper.type( "PS_Detail_Deal_contractNo_txtbxID", contractNo );
		ComboBoxHelper.select( "PS_Detail_Deal_bandType_comboID", bandType );
		if (! dealType.isEmpty() && dealType.contains( "Tiered" ) )
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
		if (!dealType.isEmpty()&& dealType.contains( "Committed" ) )
		{
			dealType = "Tiered";
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
			advanceConfigurations();
		}

		if ( !dealType.isEmpty()&& dealType.contains( "Balanced" ) && !inRatio.isEmpty() || !outRatio.isEmpty() )
		{
			ComboBoxHelper.select( "PS_Detail_Deal_dealType_ComboID", dealType );
			advanceConfigurations();
		}

	}
	/*
	 * This method is to remove the hr:min:ss from date
	 */
	private String date( String date )
	{
		String dateVal = null;
		if ( date.contains( "00:00:00" ) )
			dateVal = date.replace( "00:00:00", "" );
		else
			dateVal = date;

		return dateVal;
	}

	public void dealSave() throws Exception
	{
		ButtonHelper.click( "PS_Detail_Deal_save_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}
	
	

	public void initializeArray() throws Exception
	{
		bandGroupArr = strObj.stringSplitFirstLevel( bandGroup );
		bandArr = strObj.stringSplitFirstLevel( band );
		includeArr = strObj.stringSplitFirstLevel( include );
		gracePeriodArr = strObj.stringSplitFirstLevel( gracePeriod );
		bandFromArr = strObj.stringSplitFirstLevel( bandFrom );
		bandToArr = strObj.stringSplitFirstLevel( bandTo );
		fromMinutesArr = strObj.stringSplitFirstLevel( fromMinutes );
		toMinutesArr = strObj.stringSplitFirstLevel( toMinutes );
		commitmentArr = strObj.stringSplitFirstLevel( commitment );
		bestEffortArr = strObj.stringSplitFirstLevel( bestEffort );
		baseTierArr = strObj.stringSplitFirstLevel( baseTier );
		backToNthTierArr = strObj.stringSplitFirstLevel( backToNthTier );
	}

	public void initializeVariblesIncoming( Map<String, String> map ) throws Exception
	{
		trafficType = ExcelHolder.getKey( map, "TrafficType" );
		bandGroup = ExcelHolder.getKey( map, "BandGroup" );
		band = ExcelHolder.getKey( map, "Band" );
		include = ExcelHolder.getKey( map, "Include" );
		tiersPerBandGroup = ExcelHolder.getKey( map, "TiersPerBandGroup" );
		gracePeriod = ExcelHolder.getKey( map, "GracePeriod" );
		bandFrom = ExcelHolder.getKey( map, "BandFrom" );
		bandTo = ExcelHolder.getKey( map, "BandTo" );
		fromMinutes = ExcelHolder.getKey( map, "FromMinutes" );
		toMinutes = ExcelHolder.getKey( map, "ToMinutes" );
		commitment = ExcelHolder.getKey( map, "Commitment" );
		bestEffort = ExcelHolder.getKey( map, "BestEffort" );
		baseTier = ExcelHolder.getKey( map, "BaseTier" );
		backToNthTier = ExcelHolder.getKey( map, "BackToNthTier" );
	}

	public void initialiseVariables( Map<String, String> map ) throws Exception
	{
		partition = ExcelHolder.getKey( map, "Partition" );
		account = ExcelHolder.getKey( map, "Account" );
		currency = ExcelHolder.getKey( map, "Currency" );
		from = ExcelHolder.getKey( map, "From" );
		to = ExcelHolder.getKey( map, "To" );
		contractNo = ExcelHolder.getKey( map, "Contract No" );
		bandType = ExcelHolder.getKey( map, "BandType" );
		dealType = ExcelHolder.getKey( map, "DealType" );
		//incoming = ExcelHolder.getKey( map, "Incoming" );
		//outgoing = ExcelHolder.getKey( map, "Outgoing" );
		renewAfter = ExcelHolder.getKey( map, "RenewAfter" );
		applyRebate = ExcelHolder.getKey( map, "ApplyRebate" );
		cashflowIn = ExcelHolder.getKey( map, "CashflowIn" );
		cashflowOut = ExcelHolder.getKey( map, "CashflowOut" );
		applyShortFall = ExcelHolder.getKey( map, "ApplyShortFall" );
		shortfalIn = ExcelHolder.getKey( map, "ShortfalIn" );
		shortfalOut = ExcelHolder.getKey( map, "ShortfalOut" );
		gracePeriodDays = ExcelHolder.getKey( map, "GracePeriodDays" );
		balancedDeal = ExcelHolder.getKey( map, "BalancedDeal" );
		inRatio = ExcelHolder.getKey( map, "InRatio" );
		outRatio = ExcelHolder.getKey( map, "OutRatio" );
		externalRateIn = ExcelHolder.getKey( map, "ExternalRateIn" );
		externalRateOut = ExcelHolder.getKey( map, "ExternalRateOut" );

	}

}
