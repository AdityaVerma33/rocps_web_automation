package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.application.ReferenceTableHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

public class AlertReferenceTableImpl {
	PSGenericHelper genObj = new PSGenericHelper();
	public void configureAlertStream(String partition, String stream, String code)throws Exception {
		genObj.clickNewAction(partition);
		TextBoxHelper.type("PS_Detail_AlertEvtStream_name", stream);
		TextBoxHelper.type("PS_Detail_AlertEvtStream_code", code);
		ButtonHelper.click("ok");

		
		
	}
	
	public void configureAlertEventGroup(String partition, String groupName, String isActive)throws Exception {
		genObj.clickNewAction(partition);
		TextBoxHelper.type("PS_Detail_AlertEvtGroup_name", groupName);
		if(ValidationHelper.isTrue(isActive))
			CheckBoxHelper.check("PS_Detail_AlertEvtGroup_isactive");
		ButtonHelper.click("ok");
}
}
