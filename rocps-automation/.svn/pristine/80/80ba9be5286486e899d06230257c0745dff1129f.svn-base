package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.utils.PSStringUtils;

public class TimingDeviationComponent {
	PSGenericHelper genObj = new PSGenericHelper();
	PSStringUtils strObj = new PSStringUtils();
	
	public void timeDeviationComponent(String resultantEntity, String foreignKey, String dateTimeColumn, String graceDays, String isGraceDaysRequired, String include) throws Exception{
		PSEntityComboHelper.selectUsingGridFilterTextBox("trigger-resEntityTbl", "Entity Search", "entEntity", resultantEntity, "Entity");
		ComboBoxHelper.select("foreignTableColumn_gwt_uid_", foreignKey);
		ComboBoxHelper.select("datetimeTableColumn_gwt_uid_", dateTimeColumn);
		TextBoxHelper.type("ptdcGraceDays", graceDays);
		if(ValidationHelper.isTrue(isGraceDaysRequired	))
			CheckBoxHelper.check("tdcExceptedDateRequired_InputElement");
		if(!ValidationHelper.isTrue(include))
			{RadioHelper.click("conditionGroupExclude_InputElement");
			RadioHelper.click("conditionGroupExclude_InputElement");}
		
	}
	
	public void selectValues(String values) throws Exception{
		String[] valuesArr= strObj.stringSplitFirstLevel(values);
		for(int i=0;i<valuesArr.length;i++){
		genObj.dualListSelection("Available Values", valuesArr[i]);}
		
	}
	

}
