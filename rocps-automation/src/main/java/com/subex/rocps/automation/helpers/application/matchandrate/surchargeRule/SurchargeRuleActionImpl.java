package com.subex.rocps.automation.helpers.application.matchandrate.surchargeRule;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class SurchargeRuleActionImpl extends PSAcceptanceTest
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Surcharge Rule' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_surchargeRule_detailXpath" );

	}

	/* This method is used to click on action in 'Surcharge Rule' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_surchargeRule_detailXpath" );
	}

}
