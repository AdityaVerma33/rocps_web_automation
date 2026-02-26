package com.subex.rocps.automation.helpers.application.eventandaggregation.eventandaggregationconfig;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class EventAndAggregationActionImpl
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	// This method is used to click on 'New' action 
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PSDetail_Event_Aggregation_Conf_detail_Page_XPath" );

	}

	//This method is used to click on action
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm );
	}

	//This method is an overloaded method to click on action
	public void clickOnAction( String parentActionNm, String childActionNm, String waitForPageLoadXPath ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, waitForPageLoadXPath );
	}

}
