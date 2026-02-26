package com.subex.rocps.sprintTestCase.bklg207;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillParameterActImpl extends PSAcceptanceTest {
	
	PSActionImpl psActionImpl = new PSActionImpl();
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	
	/* This method is used to click on 'New' action in 'Bill Parameter' */
	public void clickNewAction( String clientPartition ) throws Exception
	{
		psActionImpl.clickNewAction( clientPartition, "PSDetail_Bill_Parameter_detail_Page_XPath" );

	}

	/* This method is used to click on action in 'Bill Parameter' */
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		psActionImpl.clickOnAction( parentActionNm, childActionNm, "PSDetail_Bill_Parameter_detail_Page_XPath" );
	}

}
