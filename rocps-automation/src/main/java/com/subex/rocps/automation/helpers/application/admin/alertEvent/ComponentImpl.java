package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSActionImpl;

public class ComponentImpl
{
	
	public void newAlertEvent(String partition)throws Exception{
		PSActionImpl actionObj = new PSActionImpl();
		actionObj.clickOnActionWithPartition( "Common Tasks", "New", "Component Based Alert Event", partition );
		GenericHelper.waitForLoadmask();
	}
	public void scheduleFrequency(String frequency, String dayOf, String alignmentDate) throws Exception{
		if (!ValidationHelper.isEmpty(frequency))
			ComboBoxHelper.select("alertEvent_frequencyModel_gwt_uid_", frequency);
		if (!ValidationHelper.isEmpty(dayOf))
			ComboBoxHelper.select("dayOfMonth_gwt_uid_", dayOf);
		if (!ValidationHelper.isEmpty(alignmentDate))
			ComboBoxHelper.select("alignmentDate_gwt_uid_", alignmentDate);
	}
}
