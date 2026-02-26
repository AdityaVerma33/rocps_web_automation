package com.subex.rocps.automation.helpers.application.matchandrate.Emr;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.customexception.ScreenTitleException;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.matchandrate.EventMatchRuleServiceDetails;
import com.subex.rocps.automation.helpers.application.matchandrate.mnrInterfaces.EMRServicesStrategy;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SurchargeRuleServices extends PSAcceptanceTest implements EMRServicesStrategy
{

	protected String services;
	protected String legCode;
	protected String legCodeArr[];
	protected Map<String, String> map;
	protected String[] svcArr;
	protected PSStringUtils dataUtilObj = new PSStringUtils();
	protected String surchargeRuleTestCaseNm;
	protected String surchargeRuleTestCaseNmArr[];
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	protected void initializeVariables( Map<String, String> map ) throws Exception
	{
		services = ExcelHolder.getKey( map, "Services" );
		legCode = ExcelHolder.getKey( map, "LegCode" );
		surchargeRuleTestCaseNm = ExcelHolder.getKey( map, "SurchargeRuleTestCaseNm" );

	}

	@Override
	public void addServices( Map<String, String> map ) throws Exception
	{
		initializeVariables( map );
		svcArr = dataUtilObj.stringSplitFirstLevel( services );
		legCodeArr = dataUtilObj.stringSplitFirstLevel( legCode );
		surchargeRuleTestCaseNmArr = dataUtilObj.stringSplitFirstLevel( surchargeRuleTestCaseNm );
		for ( int index = 0; index < svcArr.length; index++ )
		{
			String screenTitle = null;
			if ( svcArr[index].equalsIgnoreCase( "Service" ) )
			{
				matchRuleServiceConfig( index, map );
				if ( ValidationHelper.isNotEmpty( surchargeRuleTestCaseNm ) )
					configureSurchargeTab( index, map );
				clickOnOkButton();
				screenTitle = NavigationHelper.getScreenTitle();
				if ( screenTitle == null )
					throw new ScreenTitleException( "screen title not found" );
			}
			else if ( svcArr[index].equalsIgnoreCase( "RevenueShareService" ) )
			{
				matchRuleRevenueServiceConfig( index, map );
				if ( ValidationHelper.isNotEmpty( surchargeRuleTestCaseNm ) )
					configureSurchargeTab( index, map );
				clickOnOkButton();
				screenTitle = NavigationHelper.getScreenTitle();
				if ( screenTitle == null )
					throw new ScreenTitleException( "screen title not found" );
			}

		}

	}

	@Override
	public void editServices( Map<String, String> map ) throws Exception
	{
		// TODO Auto-generated method stub

	}

	protected void configureSurchargeTab( int index, Map<String, String> map ) throws Exception
	{
		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		String workbookName = ExcelHolder.getKey( map, "WorkBookName" );
		String sheetName = ExcelHolder.getKey( map, "SheetName" );
		TabHelper.gotoTab( "//*[@id='tabbedPanel']//div[text()='Surcharge']" );
		Map<String, String> surchargeTestCaseMap = psDataComponentHelper.getTestCaseMap( path, workbookName, sheetName, surchargeRuleTestCaseNmArr[index] );
        EveMatchRuleSurchargeServDetail evRuleSurchargeServDetailOb=new EveMatchRuleSurchargeServDetail( surchargeTestCaseMap );
        evRuleSurchargeServDetailOb.configSurchargeRule();
	}

	private void clickOnOkButton() throws Exception
	{
		ButtonHelper.click( "PSPopUp_emrSvcPopupSaveId" );
		GenericHelper.waitForLoadmask();
	}

	/*
	 * Method: for configuring event match rule revenue share service
	 */
	public void matchRuleServiceConfig( int index, Map<String, String> map ) throws Exception
	{

		EventMatchRuleServiceDetails emrSvcObj = new EventMatchRuleServiceDetails( map );
		emrSvcObj.selectService( "PSDetail_emrAddServiceMenuXpath", "PSDetail_emrServiceId", legCodeArr[index] );
		emrSvcObj.basicDetails( index );
		emrSvcObj.ratingDetails( index );
		emrSvcObj.tariffConfigurationPerService( index );

	}

	/*
	 * Method: for configuring event match rule service
	 */
	public void matchRuleRevenueServiceConfig( int index, Map<String, String> map ) throws Exception
	{

		EventMatchRuleServiceDetails emrSvcObj = new EventMatchRuleServiceDetails( map );
		emrSvcObj.selectService( "PSDetail_emrAddServiceMenuXpath", "PSDetail_emrRevServiceId", legCodeArr[index] );
		emrSvcObj.basicDetails( index );
		emrSvcObj.ratingDetails( index );
		emrSvcObj.tariffConfigurationPerService( index );
		emrSvcObj.revenueRating( index );

	}
}
