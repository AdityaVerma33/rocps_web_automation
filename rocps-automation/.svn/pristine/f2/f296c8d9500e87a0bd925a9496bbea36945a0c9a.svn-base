package com.subex.rocps.sprintTestCase.bklg13;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class bulkEntitySave {

	protected PSGenericHelper genHelperObj = new PSGenericHelper();
	protected String clientPartition;
	bulkExportRequest bulkObj2 = new bulkExportRequest();

	public void bulkEntityConfigation(String clientPartition, String requestName, String entityName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bulk Entity Export");
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction("Common Tasks", "New");
			GenericHelper.waitForLoadmask();
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type("pberName", requestName);
			ButtonHelper.click("exportGridToolbar.Add");
			GridHelper.clickRow("exportGrid", 1, 1); // Combo box click.
			GridHelper.clickRow("exportGrid", 1, 1);
			ComboBoxHelper.select("PS_Details_bulkEntity", entityName);
			GenericHelper.waitForLoadmask();
			MouseHelper.click("PS_Details_chedkEditor");
			CheckBoxHelper.check("PS_Details_checkbox");
			if (!entityName.equals("RouteGroup Conf"))
				bulkObj2.checkDependentEntities();
			ButtonHelper.click("SaveButton");
			GenericHelper.waitForLoadmask();
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	public void bulkEntityConfigurationSelectSpecificItem(String clientPartition, String requestName, String entityName)
			throws Exception {
		try {
			NavigationHelper.navigateToScreen("Bulk Entity Export");
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction("Common Tasks", "New");
			GenericHelper.waitForLoadmask();
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type("pberName", requestName);
			ButtonHelper.click("exportGridToolbar.Add");
			GridHelper.clickRow("exportGrid", 1, 1); // Combo box click.
			GridHelper.clickRow("exportGrid", 1, 1);
			ComboBoxHelper.select("PS_Details_bulkEntity", entityName);
			// checkDependentEntities(bulkExportRequest.depe);
			GenericHelper.waitForLoadmask();
			MouseHelper.click("PS_Details_chedkEditor");
			ButtonHelper.click("PS_Details_selectItem");

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

}
