package com.subex.rocps.automation.helpers.application.products.productArgType;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class ProductArgTypeActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in Product Argument Type*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_prodArgType_basicDetails_xpath" ), searchScreenWaitSec );
	}
	
	/* This method is used to click on action in Product Argument Type*/
	public void clickOnAction( String parentActionNm, String childActionNm ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( parentActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_prodArgType_basicDetails_xpath" ), searchScreenWaitSec );
	}
}
