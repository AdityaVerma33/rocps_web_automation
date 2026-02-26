package com.subex.rocps.automation.helpers.application.matchandrate.Emr;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
import com.subex.rocps.automation.helpers.application.customexception.StringLengthException;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EveMatchRuleSurchargeServDetail extends PSAcceptanceTest
{

	protected Map<String, String> eveMRSurchargeDetailsmap = null;
	protected String surchargeRuleName;
	protected String surchargeRuleNameArr[];
	protected String surchargeRatingFrom;
	protected String surchargeRatingFromArr[];
	protected String surchargeRatingTo;
	protected String surchargeRatingToArr[];
	protected String surchargeFromDt;
	protected String surchargeFromDtArr[];
	protected String surchargeToDt;
	protected String surchargeToDtArr[];
	protected String surchargeTariff;
	protected String surchargeTariffArr[];
	protected String surchargeTariffsDt;
	protected String surchargeTariffsDtArr[];
	PSStringUtils psStringUtils = new PSStringUtils();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param eveMRSurchargeDetailsmap
	 * @throws Exception 
	 */
	public EveMatchRuleSurchargeServDetail( Map<String, String> eveMRSurchargeDetailsmap ) throws Exception
	{

		this.eveMRSurchargeDetailsmap = eveMRSurchargeDetailsmap;
		intialiseVariables( eveMRSurchargeDetailsmap );
		intialiseArray();
	}

	/*
	 * Method: for initializing variables
	 */
	protected void intialiseVariables( Map<String, String> map ) throws Exception
	{
		surchargeRuleName = map.get( "SurchargeRuleName" );
		surchargeRatingFrom = map.get( "SurchargeRatingFrom" );
		surchargeRatingTo = map.get( "SurchargeRatingTo" );
		surchargeFromDt = map.get( "SurchargeFromDt" );
		surchargeToDt = map.get( "SurchargeToDt" );
		surchargeTariff = map.get( "SurchargeTariffs" );
		surchargeTariffsDt = map.get( "SurchargeTariffsDt" );
	}

	/*
	 * Method: for initializing arrays
	 */
	protected void intialiseArray() throws Exception
	{
		surchargeRuleNameArr = psStringUtils.stringSplitFirstLevel( surchargeRuleName );
		surchargeRatingFromArr = psStringUtils.stringSplitFirstLevel( surchargeRatingFrom );
		surchargeRatingToArr = psStringUtils.stringSplitFirstLevel( surchargeRatingTo );
		surchargeFromDtArr = psStringUtils.stringSplitFirstLevel( surchargeFromDt );
		surchargeToDtArr = psStringUtils.stringSplitFirstLevel( surchargeToDt );
		surchargeTariffArr = psStringUtils.stringSplitFirstLevel( surchargeTariff );
		surchargeTariffsDtArr = psStringUtils.stringSplitFirstLevel( surchargeTariffsDt );

	}

	/*
	 * Method: for configure surcharge rule
	 */
	public void configSurchargeRule() throws Exception
	{
		String gridId = GenericHelper.getORProperty( "PSdetail_emr_surchargeRule_gridId" );
		for ( int i = 0; i < surchargeRuleNameArr.length; i++ )
		{
			String expectedRowValue = surchargeRuleNameArr[i];
			boolean isDataPresent = psDataComponentHelper.isDataPresentInGrid( gridId, expectedRowValue );
			if ( !isDataPresent )
			{
				configSurchargeRuleGridField( gridId, i + 1 );
				configureTariff( gridId, i + 1 );
			}
			else
				Log4jHelper.logInfo( "The given cell value is already avilable with name: " + expectedRowValue + " in the properties grid" );
		}

	}

	/*
	 * Method: for configure surcharge rule grid field
	 */
	private void configSurchargeRuleGridField( String gridId, int row ) throws Exception
	{
		ButtonHelper.click( "PSdetail_emr_surchargeRule_add_BtnId" );
		psDataComponentHelper.selectGridEntityComboSelection( gridId, "PSdetail_emr_surchargeRule_name_triggerId", row, "Surcharge  Rule", surchargeRuleNameArr[row - 1], "psurName", "Name" );
		GridHelper.updateGridComboBox( gridId, "PSdetail_emr_surchargeRule_ratingFrom_comboId", row, "Rating  From", surchargeRatingFromArr[row - 1] );
		GridHelper.updateGridComboBox( gridId, "PSdetail_emr_surchargeRule_ratingTo_comboId", row, "Rating  To", surchargeRatingToArr[row - 1] );
		GridHelper.updateGridTextBox( gridId, "PSdetail_emr_surchargeRule_fromDt_txtId", row, "From", surchargeFromDtArr[row - 1] );
		GridHelper.updateGridTextBox( gridId, "PSdetail_emr_surchargeRule_ToDt_txtId", row, "To", surchargeToDtArr[row - 1] );
	}

	/*
	 * Method: for configure tariff
	 */
	private void configureTariff( String gridId, int row ) throws Exception
	{
		GridHelper.clickRow( gridId, row, "Surcharge  Rule" );
		psDataComponentHelper.selectTariffs( surchargeTariffArr[row - 1], surchargeTariffsDtArr[row - 1], "Surcharge  Tariff", "surchargeTariffTimeLinePanel", "PSdetail_emr_surchargeRule_tariff_txtId", "PSdetail_emr_surchargeRule_tariffpopup_BtnId" );
	}

}
