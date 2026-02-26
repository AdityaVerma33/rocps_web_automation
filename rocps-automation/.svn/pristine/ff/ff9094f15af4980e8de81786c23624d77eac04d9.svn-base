package com.subex.rocps.sprintTestCase.bklg141New;

import org.testng.annotations.Test;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class decimalprecision extends PSAcceptanceTest
{
	
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	
	int expectedDecimalPlace;

	
	@Test
	public decimalprecision() throws Exception
	{

	}

	public void configureDecimalPrecision( String tblDfn, String configName, String columnName, int dpvalue ) throws Exception
	{
		try
		{
			NavigationHelper.navigateToScreen( "Decimal Precision" );
			GenericHelper.waitForLoadmask();
			boolean isDecimalPrecisionPresent = genericHelperObj.isGridTextValuePresent( "tableDfn$tbdName", tblDfn, "Table Name" );
			if ( !isDecimalPrecisionPresent )
			{
				NavigationHelper.navigateToAction( "Common Tasks", "New" );
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type( or.getProperty( "decimalPrecisionName" ), configName );
				ElementHelper.click( or.getProperty( "PS_Config_Name" ) );
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type( or.getProperty( "PS_TBL_Name" ), tblDfn );
				ElementHelper.click( "//button[@id='search']" );
				GenericHelper.waitForLoadmask();

				GridHelper.clickRow( "searchGrid", 1, 1 );
				ElementHelper.click( "//button[@id='ok']" );
				GenericHelper.waitForLoadmask();
				GenericHelper.waitInSeconds( "2" );

				int rowNumber = GridHelper.getRowNumber( "columnGrid", columnName, "Column  Name" );
				GridHelper.clickRow( "columnGrid", rowNumber, 4 );

				TextBoxHelper.clear( or.getProperty( "PS_DP_Text" ) );
				String val = String.valueOf( dpvalue );
				TextBoxHelper.type( or.getProperty( "PS_DP_Text" ), val );

				GenericHelper.waitInSeconds( "2" );
				ButtonHelper.click( "decimalPrecisionDetail.save" );
				GenericHelper.waitForLoadmask();

			}
			else
			{
				SearchGridHelper.gridFilterSearchWithTextBox( "tableDfn$tbdName", tblDfn, "Table Name" );
				GridHelper.clickRow( "searchGrid", 1, 1 );
				NavigationHelper.navigateToAction( "Common Tasks", "Edit" );

				GenericHelper.waitForLoadmask();
				int rowNumber = GridHelper.getRowNumber( "columnGrid", columnName, "Column  Name" );

				String cellValue = GridHelper.getCellValue( "columnGrid", rowNumber, 4 );
				expectedDecimalPlace = Integer.parseInt( cellValue );

				Log4jHelper.logInfo( "Modified decimal precision " + expectedDecimalPlace );
			}

		}
		catch ( Exception e )
		{

			throw e;
		}
	}
}
