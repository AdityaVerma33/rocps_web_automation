package com.subex.rocps.automation.helpers.application.reaggregation.reaggregationrequest;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CalendarHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;

public class ReAggregationRequestSearchImpl extends PSAcceptanceTest
{
	GridFilterSearchHelper gridHelperObj = new GridFilterSearchHelper();
	
	public boolean reAggregationFilterOperation(String createdDate, String description) throws Exception
	{
		/*	if(!createdDate.isEmpty())
				CalendarHelper.setOnDate( "PS_Detail_reaggregation_calender_TxtBx", createdDate );
			else*/
			CalendarHelper.setToday( "PS_Detail_reaggregation_calender_TxtBx" );
		ButtonHelper.click( "SearchButton" );
		return GridHelper.isValuePresent( "SearchGrid", description, "Description" );
	}
	
	
}
