package com.subex.rocps.automation.helpers.application.reaggregation.usgBackoutReq;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class UsageBackoutReqActImpl extends PSAcceptanceTest
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Usage Backout Request' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_usgBackoutReq_detailXpath" );

	}

	/* This method is used to click on action in 'Usage Backout Request' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_usgBackoutReq_detailXpath" );
	}
	/* This method is used to click on action in 'Usage Backout Request' */
	public void clickOnScheduleAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm );
	}
	
}
