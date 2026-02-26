package com.subex.rocps.sprintTestCase.bklg141New;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class carrierinvoice extends PSAcceptanceTest
{

	String dpName;

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;

	public carrierinvoice() throws Exception
	{

	}

	public void carrierinvoicesearch( String columnHeadername, int dpvalue ) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Carrier Invoice" );

			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterAdvancedSearch( "invoiceTemplate", "MultiCI_WSS", "Template" );
			GenericHelper.waitForLoadmask();
			filterStatus();

			GenericHelper.waitForLoadmask();

			GridHelper.clickRow( "SearchGrid", 1, 1 );

			NavigationHelper.navigateToAction( or.getProperty( "CommonTasks" ), or.getProperty( "Edit" ) );
			NavigationHelper.navigateToAction( or.getProperty( "Usage" ) );

			ArrayList<String> values = GridHelper.getColumnValues( "Usggrid", columnHeadername );

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

	public void filterStatus() throws Exception
	{
		ElementHelper.click( "//div[@id='grid_column_header_filtersearchGrid_statusCode$pscdDisplay']" );
		ComboBoxHelper.select( "statusCode_gwt_uid_", "Draft" );
		ButtonHelper.click( "search" );
	}

	public void initializeInstanceVariables()
	{

		clientPartition = dpMap.get( "ClientPartition" );

	}
}
