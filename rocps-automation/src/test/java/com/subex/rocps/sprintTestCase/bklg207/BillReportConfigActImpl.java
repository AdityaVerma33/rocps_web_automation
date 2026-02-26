package com.subex.rocps.sprintTestCase.bklg207;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class BillReportConfigActImpl
{
	
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	
	/* This method is used to click on 'New' action in 'Bill Report Configuration' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PSDetail_Bill_Report_Conf_detail_Page_XPath" );

	}

	/* This method is used to click on action in 'Bill Report Configuration' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PSDetail_Bill_Report_Conf_detail_Page_XPath" );
	}

}
