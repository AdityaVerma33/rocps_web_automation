package com.subex.rocps.automation.helpers.application.products.productBundles;

import org.openqa.selenium.WebElement;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.NavigationMenuHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductBundlesActionImpl extends PSAcceptanceTest
{
	protected PSGenericHelper psgenericHelperobj = new PSGenericHelper();

	/* This method is used to click on 'New' action in Product Bundles*/
	public void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psgenericHelperobj.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bundle_deatails_basicDetailXpath" ), searchScreenWaitSec );
	}

	/* This method is used to click on 'Change Status ' action in Product Bundles*/
	public void changeStatusAccpted( String parentActionNm, String childActionNm ) throws Exception
	{
		NavigationMenuHelper navigationMenu = new NavigationMenuHelper();
		ProductUtil.waitForParentActionElementTOBeclickable( parentActionNm );
		String childXpath = "//div[@id='subMenu']//div[contains(text(),'Set Status')]";
		WebElement element = navigationMenu.getActionElement( parentActionNm );
		if ( element != null )
		{
			ElementHelper.waitForClickableElement( element, 20 );
			MouseHelper.click( element );
			element = navigationMenu.getActionElement( parentActionNm );
			MouseHelper.mouseOver( element );
			element = navigationMenu.getActionElement( childXpath );

			if ( element != null )
			{
				MouseHelper.click( element );
				GenericHelper.waitForLoadmask( detailScreenWaitSec );
				GenericHelper.waitForAJAXReady( detailScreenWaitSec );
			}

			else
			{
				FailureHelper.failTest( "Child action '" + childActionNm + "' is not found." );
			}
		}

		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/* This method is used to click on action in Product Bundles*/
	public void clickOnBundleAction( String parentActionNm, String childActionNm ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( parentActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_bundle_deatails_basicDetailXpath" ), searchScreenWaitSec );
	}

}
