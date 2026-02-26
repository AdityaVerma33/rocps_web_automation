package com.subex.rocps.automation.helpers.application.matchandrate.eventmodellingdefinition;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class TableDefinationSearchImpl extends PSAcceptanceTest
{

	public void tableDefnEntitySearch( String tabledefEvenType ) throws Exception
	{
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "Table Definition Search" );
		TextBoxHelper.type( "PS_Detail_EveModDefn_tabDefnSearch_GridWrapper", "PS_Detail_EveModDefn_tabDefnSearch_tableNm_txtID", tabledefEvenType );
		ButtonHelper.click( "PS_Detail_EveModDefn_tabDefnSearch_GridWrapper", "PS_Detail_EveModDefn_tabDefnSearch_SAVE_BtnID" );
		GenericHelper.waitForLoadmask();
		GridHelper.clickRow( "PS_Detail_EveModDefn_tabDefnSearch_GridWrapper", "PS_Detail_EveModDefn_tabDefnSearch_GridId", tabledefEvenType, "Name" );
		GenericHelper.waitForLoadmask();
	}

	public void clickOkButtonOnTableDefnSearch() throws Exception
	{
		ButtonHelper.click( "PS_Detail_EveModDefn_tabDefnSearch_GridWrapper", "PS_Detail_EveModDefn_tabDefnSearch_OK_BtnID" );
		GenericHelper.waitForLoadmask();
		assertEquals( NavigationHelper.getScreenTitle(), "New " + "Event Modelling Definition" );
	}
}
