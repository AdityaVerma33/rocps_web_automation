package com.subex.rocps.automation.helpers.application.Sales.offerRule;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class OfferRuleDetail extends PSAcceptanceTest
{
	protected Map<String, String> offerRuleDetailsMap = null;
	protected String offerRuleName;
	protected String eventType;

	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param offerRuleDetailsMap
	 */
	public OfferRuleDetail( Map<String, String> offerRuleDetailsMap )
	{

		this.offerRuleDetailsMap = offerRuleDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		offerRuleName = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );

	}

	/*
	 * This method is for configure Markup rule
	 */
	public void configureOfferRule() throws Exception
	{

	}

}
