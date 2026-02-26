package com.subex.rocps.automation.helpers.application.Sales.proposalRule;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class ProposalRuleDetail extends PSAcceptanceTest
{
	protected Map<String, String> proposalRuleDetailsMap = null;
	protected String proposalRuleName;
	protected String eventType;

	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param proposalRuleDetailsMap
	 */
	public ProposalRuleDetail( Map<String, String> proposalRuleDetailsMap )
	{

		this.proposalRuleDetailsMap = proposalRuleDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		proposalRuleName = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );

	}

	/*
	 * This method is for configure Markup rule
	 */
	public void configureProposalRule() throws Exception
	{

	}
}
