package com.subex.rocps.automation.helpers.application.admin.alertGroup;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AlertGroupImpl {
	PSStringUtils strObj = new PSStringUtils();
	PSGenericHelper genObj = new PSGenericHelper();
	public void headerDetails(String name, String guidedEntity, String resultEntity, String resultComponent) throws Exception{
		TextBoxHelper.type("PS_Detail_AlertGroup_name", name);
		PSEntityComboHelper.selectUsingGridFilterTextBox("PS_Detail_AlertGroup_guidedEnt", "Entity Search","entEntity", guidedEntity, "Entity");
		if(!ValidationHelper.isEmpty(resultEntity))
			EntityComboHelper.select("PS_Detail_AlertGroup_resultEnt", "Entity Search", resultEntity, "Entity");
		if(!ValidationHelper.isEmpty(resultComponent))
			ComboBoxHelper.select("PS_Detail_AlertGroup_resultComp", resultComponent);
	}
	
	public void alertDefinitions(String definition, String alertNo, String alertSeverity, String alertText) throws Exception{
		String[] alertNos = strObj.stringSplitFirstLevel(alertNo);
		String[] alertSeverities = strObj.stringSplitFirstLevel(alertSeverity);
		String[] alertTexts = strObj.stringSplitFirstLevel(alertText);
		for(int i=0;i<Integer.parseInt(definition);i++){
			ButtonHelper.click("PS_Detail_AlertGroup_add");
			TextBoxHelper.type("PS_Detail_AlertGroup_alertNo", alertNos[i]);
			ComboBoxHelper.select("PS_Detail_AlertGroup_severity", alertSeverities[i]);
			TextBoxHelper.type("PS_Detail_AlertGroup_text", alertTexts[i]);
		}
		ButtonHelper.click("PS_Detail_AlertGroup_dfnSave");
		GenericHelper.waitForLoadmask();
	}

	public void createNewGroup(String partition) throws Exception{
		genObj.clickNewAction(partition);
		
	}
	
	public void save(String value, String colHeader) throws Exception{
		GenericHelper.waitForLoadmask();
		genObj.detailSave( "PS_Detail_AlertGroup_save", value, colHeader );
	}
}
