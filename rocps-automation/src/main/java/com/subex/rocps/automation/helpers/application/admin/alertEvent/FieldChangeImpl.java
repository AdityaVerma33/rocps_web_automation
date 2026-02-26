package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class FieldChangeImpl {
	public void newAlertEvent(String partition)throws Exception{
		ButtonHelper.click("Common Tasks");
		PSActionImpl actionObj = new PSActionImpl();
		actionObj.clickOnActionWithPartition( "Common Tasks", "New", "Field Change Alert Event", partition );
		GenericHelper.waitForLoadmask();
	}
	
public void fieldChangeConfiguration(String column, String value )throws Exception{
	ComboBoxHelper.select("PS_Detail_fieldChange_column",column);
	TextBoxHelper.type("PS_Detail_fieldChange_value", value);
}

public void save(String value, String colHeader) throws Exception{
	PSGenericHelper genObj = new PSGenericHelper();
	genObj.detailSave( "PS_Detail_fieldChange_save", value, colHeader );
}
}
