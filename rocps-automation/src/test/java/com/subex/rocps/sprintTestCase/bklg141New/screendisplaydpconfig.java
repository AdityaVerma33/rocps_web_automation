package com.subex.rocps.sprintTestCase.bklg141New;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class screendisplaydpconfig extends PSAcceptanceTest
{

	String dpName;
	String billedUsageValue;

	int colSize;
	int paramVal;
	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	public int dp;

	public screendisplaydpconfig() throws Exception
	{

	}

	public void screendisplaydpconfigdp( String scrName, String fieldType, int dp ) throws Exception
	{

		this.dp = dp;

		try
		{
			NavigationHelper.navigateToScreen( "Reference Tables" );

			ComboBoxHelper.select( "displayString_gwt_uid_", "Screen Display Dp Config" );

			GenericHelper.waitForLoadmask();

			int rowCount = GridHelper.getRowCount( "gridPanel" );
			boolean dataExistFlag = false;
			for ( int i = 1; i <= rowCount; i++ )
			{
				ArrayList<String> existingScrDP = GridHelper.getRowValues( "gridPanel", i );
				if ( existingScrDP.get( 1 ).equals( scrName ) )
				{
					if ( existingScrDP.get( 2 ).equals( fieldType ) )
					{

						GridHelper.clickRow( "gridPanel", i, 3 );
						dataExistFlag = true;

						System.out.println( "DP is Edited " );
						break;
					}

				}

			}

			if ( dataExistFlag == true )
			{
				System.out.println( "DP is Edited " );

				NavigationHelper.navigateToAction( "Common Tasks", "Edit" );
				GenericHelper.waitForLoadmask();
				ComboBoxHelper.select( "psddDecimalPrecision_gwt_uid_", dp + "" );
				ButtonHelper.click( "Save" );
				GenericHelper.waitForLoadmask();
			}
			if ( dataExistFlag == false )
			{
				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToAction( "Common Tasks", "New" );
				GenericHelper.waitForLoadmask();
				
				ComboBoxHelper.select( "psddScreenName_gwt_uid_", scrName );
				ComboBoxHelper.select( "fieldType_pftyType_gwt_uid_", fieldType );
				ComboBoxHelper.select( "psddDecimalPrecision_gwt_uid_", dp + "" );
				ButtonHelper.click( "Save" );
			}

		}

		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
			throw e;
		}
	}

	/*public void initializeInstanceVariables()
	{

		clientPartition = dpMap.get( "ClientPartition" );

	}*/
}
