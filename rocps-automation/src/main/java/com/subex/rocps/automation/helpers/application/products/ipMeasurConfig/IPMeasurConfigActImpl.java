package com.subex.rocps.automation.helpers.application.products.ipMeasurConfig;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class IPMeasurConfigActImpl extends PSAcceptanceTest
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'IP Measurement Configuration' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_ipMeasuConfig_detailXpath" );

	}

	/* This method is used to click on action in 'IP Measurement Configuration' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_ipMeasuConfig_detailXpath" );
	}
}
