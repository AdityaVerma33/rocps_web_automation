package com.subex.rocps.automation.helpers.application.bulkentityselection;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class DialStringSelection extends PSAcceptanceTest implements SelectItemInterface {
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void selectItems(String item) throws Exception {

		dsObj.dialStringSelection(item);
	}

}