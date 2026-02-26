package com.subex.rocps.automation.helpers.application.Sales.salesProposal;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class SalesProposalDetail extends PSAcceptanceTest
{
	protected Map<String, String> salesProposalDetailsMap = null;
	protected String salesProposalName;
	protected String eventType;

	PSStringUtils psStringUtils = new PSStringUtils();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/**Constructor
	 * @param salesProposalDetailsMap
	 */
	public SalesProposalDetail( Map<String, String> salesProposalDetailsMap )
	{

		this.salesProposalDetailsMap = salesProposalDetailsMap;
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		salesProposalName = ExcelHolder.getKey( map, "Name" );
		eventType = ExcelHolder.getKey( map, "EventType" );

	}

	/*
	 * This method is for configure Markup rule
	 */
	public void configureSalesProposal() throws Exception
	{

	}
}
