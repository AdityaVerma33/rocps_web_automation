package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.filters.PSSearchGridHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class EmailConfigurationImpl extends PSAcceptanceTest{
	PSStringUtils stringObj = new PSStringUtils();
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void emailGrid(String emailColumn, String joiningTable, String joiningColumn, String displayColumn,
			String columnHeader) throws Exception {
		String[] columns = stringObj.stringSplitFirstLevel(emailColumn);
		String[] joiningTables = stringObj.stringSplitFirstLevel(joiningTable);
		String[] displayColumns = stringObj.stringSplitFirstLevel(displayColumn);
		String[] columnHeaders = stringObj.stringSplitFirstLevel(columnHeader);
		String[] joiningColumns = stringObj.stringSplitFirstLevel(joiningColumn);

		for (int i = 0; i < columns.length; i++) {
			if (!columns[i].equals("")) {
				MouseHelper.scrollAndClick(ElementHelper.getElement(or.getProperty( "PS_Detail_AlertOtherDetails_email_Add")));
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 1);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 1);
				ComboBoxHelper.select("PS_Detail_AlertOtherDetails_email_Column", columns[i]);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 2);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 2);
				tableInstanceEntitySearch( joiningTables[i] );
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 3);
				ComboBoxHelper.select("PS_Detail_AlertOtherDetails_email_JoiningColumn", joiningColumns[i]);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 4);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 4);
				ComboBoxHelper.select("PS_Detail_AlertOtherDetails_email_DisplayColumn", displayColumns[i]);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 5);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_email_Grid", i + 1, 5);
				TextBoxHelper.type("PS_Detail_AlertOtherDetails_email_colHeaders", columnHeaders[i]);
			}

		}

	}

	public void message(String header, String footer, String subject) throws Exception {
		if (ValidationHelper.isNotEmpty(subject)){
			//MouseHelper.scrollAndClick(ElementHelper.getElement("alertEvent.alertEmailInfo.emailSubString"));
			TextAreaHelper.type("PS_Detail_AlertOtherDetails_email_subject", subject);}
		if (ValidationHelper.isNotEmpty(header))
			TextBoxHelper.type("PS_Detail_AlertOtherDetails_email_header", header);
		if (ValidationHelper.isNotEmpty(footer))
			TextBoxHelper.type("PS_Detail_AlertOtherDetails_email_footer", footer);
	}
	
	public void tableInstanceEntitySearch( String value ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforEntityElement();
		GenericHelper.waitForLoadmask();
		EntityComboHelper.clickEntityIcon( "trigger-joiningTableEditor" );
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
