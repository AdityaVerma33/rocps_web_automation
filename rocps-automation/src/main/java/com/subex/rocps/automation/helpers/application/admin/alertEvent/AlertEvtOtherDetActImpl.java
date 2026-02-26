package com.subex.rocps.automation.helpers.application.admin.alertEvent;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AlertEvtOtherDetActImpl  extends PSAcceptanceTest{
	PSStringUtils stringObj = new PSStringUtils();

	public void switchToActionsTab() throws Exception {
		ElementHelper.click(or.getProperty("PS_Detail_AlertOtherDetails_ActionsTab"));
		GenericHelper.waitForLoadmask();
	}

	public void submitSql(String value) throws Exception {
		String[] values = stringObj.stringSplitFirstLevel(value);
		for (int i = 0; i < values.length; i++) {
			if(!values[i].equals("")){
			ElementHelper.click("PS_Detail_AlertOtherDetails_submitSql_Add");
			if (i == 0) {
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_submitSql_Grid", i + 1, 1);
			}
			GridHelper.clickRow("PS_Detail_AlertOtherDetails_submitSql_Grid", i + 1, 1);
			TextBoxHelper.type("PS_Detail_AlertOtherDetails_submitSql_Editor", value);}
		}
	}

	public void runProgram(String pathofProgram, String value) throws Exception {
		ElementHelper.click(or.getProperty("PS_Detail_AlertOtherDetails_runProg_Tab"));
		String[] paths = stringObj.stringSplitFirstLevel(pathofProgram);
		String[] values = stringObj.stringSplitFirstLevel(value);
		for (int i = 0; i < values.length; i++) {
			if (!paths[i].equals("")) {
				ElementHelper.click("PS_Detail_AlertOtherDetails_runProg_Add");
				if (i == 0) {
					GridHelper.clickRow("PS_Detail_AlertOtherDetails_runProg_Grid", i + 1, 1);
				}
				GridHelper.clickRow("", i + 1, 1);
				TextBoxHelper.type("PS_Detail_AlertOtherDetails_runProg_Path", paths[i]);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_runProg_Grid", i + 1, 2);
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_runProg_Grid", i + 1, 2);
				TextBoxHelper.type("PS_Detail_AlertOtherDetails_runProg_Value", values[i]);
			}
		}
	}

	public void components(String component) throws Exception {
		ElementHelper.click(or.getProperty( "PS_Detail_AlertOtherDetails_component_Tab"));
		String[] components = stringObj.stringSplitFirstLevel(component);
		for (int i = 0; i < components.length; i++) {
			if (!components[i].equals("")) {
				ElementHelper.click("PS_Detail_AlertOtherDetails_component_Add");
				if (i == 0) {
					GridHelper.clickRow("PS_Detail_AlertOtherDetails_component_grid", i + 1, 1);
				}
				GridHelper.clickRow("PS_Detail_AlertOtherDetails_component_grid", i + 1, 1);
				ComboBoxHelper.select("PS_Detail_AlertOtherDetails_component_comboBox", components[i]);
			}
		}
	}

}
