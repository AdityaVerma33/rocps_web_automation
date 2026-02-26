package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AlertTextImpl extends PSAcceptanceTest
{
	PSStringUtils stringObj = new PSStringUtils();
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void switchToAlertTextTab() throws Exception
	{
		ElementHelper.click( or.getProperty( "PS_Detail_AlertOtherDetails_alerttxt_Tab" ) );
		GenericHelper.waitForLoadmask();
	}

	public void fieldInformationConfiguration( String parameter, String column, String joiningTable, String joiningColumn, String displayColumn ) throws Exception
	{
		String[] columns = stringObj.stringSplitFirstLevel( column );
		String[] joiningTables = stringObj.stringSplitFirstLevel( joiningTable );
		String[] displayColumns = stringObj.stringSplitFirstLevel( displayColumn );
		String[] parameters = stringObj.stringSplitFirstLevel( parameter );
		String[] joiningColumns = stringObj.stringSplitFirstLevel( joiningColumn );
		for ( int i = 0; i < parameters.length; i++ )
		{
			if ( !columns[i].contentEquals( "" ) )
			{
				int row = GridHelper.getRowNumber( "PS_Detail_AlertOtherDetails_alerttxt_Grid", parameters[i] );
				if ( ValidationHelper.isNotEmpty( columns[i] ) )
				{
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 2 );
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 2 );
					ComboBoxHelper.select( "PS_Detail_AlertOtherDetails_alerttxt_Column", columns[i] );
				}
				if ( ValidationHelper.isNotEmpty( joiningTables[i] ) )
				{
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 3 );
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 3 );

					tableInstanceEntitySearch( joiningTables[i] );
				}
				if ( ValidationHelper.isNotEmpty( joiningColumns[i] ) )
				{
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 4 );
					ComboBoxHelper.select( "PS_Detail_AlertOtherDetails_alerttxt_JoiningColumn", joiningColumns[i] );
				}
				if ( ValidationHelper.isNotEmpty( displayColumns[i] ) )
				{
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 5 );
					GridHelper.clickRow( "PS_Detail_AlertOtherDetails_alerttxt_Grid", row, 5 );
					ComboBoxHelper.select( "PS_Detail_AlertOtherDetails_alerttxt_DisplayColumn", displayColumns[i] );
				}
			}

		}
	}

	public void tableInstanceEntitySearch( String value ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforEntityElement();
		GenericHelper.waitForLoadmask();
		EntityComboHelper.clickEntityIcon( "PS_Detail_AlertOtherDetails_alerttxt_JoiningTable" );
		genericHelperObj.waitforPopupHeaderElement( "Schema" );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "SearchButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforPopupHeaderElement( "Schema" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.scrollforHeaderElement( "SearchGrid", "Definition Name" );
		genericHelperObj.scrollforHeaderElement( "SearchGrid", "Table Name" );
		int row = PSSearchGridHelper.gridFilterSearchWithTextBox( "TableInst_TableName", value, "Table Name" );
		boolean isValue = GridHelper.isValuePresent( "Detail_popUpWindowId", "SearchGrid", value, "Table Name" );
		assertTrue( isValue, "Table Instance Search with  table name :'" + value + "'  is not found in 'Table Instance Search' popupScreen " );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "Detail_popUpWindowId", "SearchGrid", row, "Table Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.clickIfEnabled( "Detail_popUpWindowId", "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforPopupHeaderElementToDisappear( "Schema" );
	}

}
