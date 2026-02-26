package com.subex.rocps.automation.helpers.application.products.productBundleDrillDown;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.ProductBundleDrillDownHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;

public class PBundleDrillDownActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();
	
	/* This method is used to click on action in Product Bundle Drill Down*/
	public void clickOnAction( String parentActionNm, String childActionNm,String popupFieldName ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( parentActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ProductBundleDrillDownHelper.waitForPopupFieldName( popupFieldName );
	}
}
