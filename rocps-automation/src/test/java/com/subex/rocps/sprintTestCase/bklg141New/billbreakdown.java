package com.subex.rocps.sprintTestCase.bklg141New;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class billbreakdown extends PSAcceptanceTest
{

	String dpName;
	String billedUsageValue;

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;

	public billbreakdown() throws Exception
	{

	}

	public void billbreakdowndp( String columnHeadername, int dpvalue ) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Bill" );
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterAdvancedSearch( "billProfile", "CIR Stmt Bill - 11 - CIR E2E", "Bill Profile" );

			GenericHelper.waitForLoadmask();
			GridHelper.clickRow( "searchGrid", 1, 1 );
			GenericHelper.waitInSeconds( "10" );

			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction( "Bill Breakdowns", "Test" );

			ArrayList<String> values = GridHelper.getColumnValues( "drillDownGrid", 8 );

			int ExpectedDecimalPrecision = dpvalue;
			for ( int i = 0; i < values.size(); i++ )
			{
				int integerPlaces = values.get( i ).indexOf( '.' ) + 1;
				int decimalPlaces = values.get( i ).length() - integerPlaces;
				System.out.println( " UI value : " + columnHeadername + " = " + values.get( i ) );
				assertEquals( decimalPlaces, ExpectedDecimalPrecision );
			}

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	public void initializeInstanceVariables()
	{

		clientPartition = dpMap.get( "ClientPartition" );

	}
}
