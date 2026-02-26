package com.subex.rocps.automation.helpers.application.reportingAndExtraction.repModelling;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class ReportModellingActionImpl extends PSAcceptanceTest
{
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in 'Report Modelling' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PS_Detail_repModelling_detailXpath" );

	}

	/* This method is used to click on action in 'Report Modelling' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PS_Detail_repModelling_detailXpath" );
	}

	/* This method is used to click on action in 'Report Modelling' */
	public void clickOnChangeStatus( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm );

	}

}
