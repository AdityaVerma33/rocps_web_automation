package com.subex.rocps.automation.helpers.application.roaming.testSIMManagement;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class TestSIMManagementAction extends PSAcceptanceTest
{

	PSActionImpl psActionImpl = new PSActionImpl();
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Test SIM Management'*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_TestSIMManag_detail_xpath" );
	}

	/* This method is used to click on  action in 'Test SIM Management'*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_TestSIMManag_detail_xpath" );
	}
}
