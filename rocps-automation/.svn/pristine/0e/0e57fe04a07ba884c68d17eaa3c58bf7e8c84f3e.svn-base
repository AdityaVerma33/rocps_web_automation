package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.componentHelpers.RadioElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;

public class EntityAddedDeletedImpl extends AlertDetailsImpl {
	
	public void newAlertEvent(String partition)throws Exception{
		PSActionImpl actionObj = new PSActionImpl();
		actionObj.clickOnActionWithPartition( "Common Tasks", "New", "Entity Added/Deleted Alert Event", partition );
		GenericHelper.waitForLoadmask();
	}
	
	public void alertGenerationCondition(String componentType)throws Exception{
		if(ValidationHelper.isTrue(componentType)){
			RadioHelper.click("PS_Detail_entityAlert_entityAdded");
		}
		else{
			RadioHelper.click("conditionGroupEntityDeleted_InputElement");
		}
	}
}

