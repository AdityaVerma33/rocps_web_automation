package com.subex.rocps.automation.helpers.application.payments.paymAndCollections;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class PaymentAndCollectionsActImpl extends PSAcceptanceTest
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Payments And Collections' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_paymAndCollect_detailXpath" );

	}

	/* This method is used to click on action in 'Payments And Collections' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_paymAndCollect_detailXpath" );
	}
	/* This method is used to click on action in 'Payments And Collections' */
	public void performAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm );
	}

	/* This method is used to click on action in 'Payments And Collections' */
	public void clickOnActionWithPartition( String parentActionNm, String childActionNm, String clientPartition ) throws Exception
	{
		psActionImpl.clickOnActionWithPartition( parentActionNm, childActionNm, clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_paymAndCollect_detailXpath" ), searchScreenWaitSec );

	}

}
