package com.subex.rocps.automation.helpers.application.bulkentityselection;

import java.util.Map;

import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class RouteGroupSelection extends PSAcceptanceTest implements SelectItemInterface  {
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	
	public void selectItems(String item) throws Exception{
		
		if ( !ElementHelper.isElementPresent( "//div[text()='Route Group Search']" ) )
			ElementHelper.waitForElement( "//div[text()='Route Group Search']", searchScreenWaitSec );
		dsObj.dataFilterSearchGridColTxt( "SearchGrid", "Search_routeName_gridColTxtId", item, "Name" );
	

		
	}
		
	
}

