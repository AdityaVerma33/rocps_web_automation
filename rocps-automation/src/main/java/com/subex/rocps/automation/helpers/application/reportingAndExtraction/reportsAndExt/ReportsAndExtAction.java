package com.subex.rocps.automation.helpers.application.reportingAndExtraction.reportsAndExt;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class ReportsAndExtAction extends PSAcceptanceTest
{
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/* This method is used to click on 'New' action in Reports And Extracts*/
	public void clickAdhocReqAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Report and Extract Actions" );
		psGenericHelper.validateActionText( "Report and Extract Actions", "Adhoc Request" );
		NavigationHelper.navigateToAction( "Report and Extract Actions", "Adhoc Request" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( "PS_Detail_reportAndExt_Details_xpath", searchScreenWaitSec );
	}

}
