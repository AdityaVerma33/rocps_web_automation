package com.subex.rocps.automation.helpers.application.bulkentityselection;

import java.util.Map;

import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class RapOutSelection extends PSAcceptanceTest implements SelectItemInterface  {
	DataSelectionHelper dsObj = new DataSelectionHelper();
	PSGenericHelper genericHelperObj = new PSGenericHelper();
	
	public void selectItems(String item) throws Exception{
		
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		genericHelperObj.waitforPopupHeaderElement( "Tadig" );
		SearchGridHelper.gridFilterSearchWithComboBox("procConfigType_gwt_uid_", "Rap Out", "Configuration Type");
		dsObj.dataFilterSearchGridComboBox("SearchGrid", "roamingDefinition_tadigCode_gwt_uid_", item, "Tadig" );

		
	}
		
	
}

