package com.subex.rocps.automation.helpers.application.tariffs.ratesheetimportrequest;

import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;

public class RateSheetImportRequestSearchImpl extends PSAcceptanceTest
{
	public boolean filterOperation(String tariff, String fileName) throws Exception
	{
		PSSearchGridHelper.gridFilterSearchWithTextBox( "tariff", tariff, "Tariff" );
		
		PSSearchGridHelper.gridFilterSearchWithTextBox( "prirFileName", fileName, "File Name" );
		
		return GridHelper.isValuePresent( "SearchGrid", tariff, "Tariff" );
	}

	public void calender(String filterIconID) throws Exception {
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickFilterIcon(filterIconID);
		CalendarHelper.setToday("prirScheduledDttm");
		ButtonHelper.click("SearchButton");
		GridHelper.sortGrid( "SearchGrid", "Created On" );
		GridHelper.sortGrid( "SearchGrid", "Created On" );	
	}
}
