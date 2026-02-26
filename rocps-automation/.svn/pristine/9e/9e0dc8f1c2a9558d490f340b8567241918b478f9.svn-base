package com.subex.rocps.automation.helpers.application.aggregation.aggregationresult;

import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;

public class ConfigureGridColumnsImpl extends PSAcceptanceTest
{

	public void configureGrid( String colValue, String colHeaders ) throws Exception
	{
		String regex = new PSStringUtils().regexFirstLevelDelimeter();

		NavigationHelper.navigateToAction( "Aggregation Actions", "Configure Grid View" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		assertEquals( NavigationHelper.getScreenTitle(), "Configure Grid Columns" );

		String[] colHeadersArr = colHeaders.split( regex, -1 );
		String[] colValueArr = colValue.split( regex, -1 );
		for ( int row = 0; row < colHeadersArr.length; row++ )
		{
			int rowNo = GridHelper.getRowNumber( "tableGridColumn", colHeadersArr[row], "Caption" );
			
			String val = GridHelper.getCellValue( "tableGridColumn", rowNo, "Sort" );
			
			if(val.contains( "none" ))
				GridHelper.updateGridComboBox( "tableGridColumn", "tgcSort_gwt_uid_", rowNo, "Sort", colValueArr[row] );

		}

		ButtonHelper.click( "rocpsConfigureGrid.OK" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}
}
