package com.subex.rocps.automation.helpers.application.bulkentityselection;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class EventLegCodeGroupSelection extends PSAcceptanceTest implements SelectItemInterface {
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void selectItems(String item) throws Exception {


		if ( !ElementHelper.isElementPresent( "//div[text()='Reference Table Search']" ) )
			ElementHelper.waitForElement( "//div[text()='Reference Table Search']", searchScreenWaitSec );
		GenericHelper.waitForLoadmask();
		GridHelper.clickRow( "SearchGrid",item,"Group Name");
		ButtonHelper.click( "OK_Button_ByID" );
	}

	}

