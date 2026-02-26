package com.subex.rocps.automation.helpers.application.roaming.imsiManagement;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class IMSIManagementActionImpl extends PSAcceptanceTest
{

	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();
	PSActionImpl psActionImpl = new PSActionImpl();

	/* This method is used to click on 'New' action in 'IMSI Management'*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_IMSIManag_detail_xpath" );

	}

	/* This method is used to click on  action in 'IMSI Management'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_IMSIManag_detail_xpath" );
	}
}
