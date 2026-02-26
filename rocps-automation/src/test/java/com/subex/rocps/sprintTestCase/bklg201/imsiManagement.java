package com.subex.rocps.sprintTestCase.bklg201;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.SearchHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class imsiManagement extends PSAcceptanceTest {
	private static final PSGenericHelper genHelperObj = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public void createNewImsiManagement() throws Exception {
		try {
			GenericHelper.waitForLoadmask();
			NavigationHelper.navigateToAction("Common Tasks", "New");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("trigger-roamingdefinition");
			filterStatus(imsiManagementExecution.roamingDefinition);
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("ok");
			GenericHelper.waitForLoadmask();
			PSStringUtils pSStringUtils = new PSStringUtils();
			String types[] = pSStringUtils.stringSplitFirstLevel(imsiManagementExecution.typesFromExcel);
			String values[] = pSStringUtils.stringSplitFirstLevel(imsiManagementExecution.ValesFromExcel);

			for (int i = 0; i < types.length; i++) {
				ButtonHelper.click("msinToolbar.Add");
				GridHelper.updateGridComboBox("msinGrid", "TypeEditor_gwt_uid_", i+1, "Type", "MSIN", types[i]);
				GridHelper.updateGridTextBox("msinGrid", "msinEditor", i+1, 2, "MSIN", values[i]);
			}

			ButtonHelper.click("Save");
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;

		}

	}

	public void filterStatus(String roamingDefinition) throws Exception {
		GenericHelper.waitForLoadmask();
		ElementHelper.click("grid_column_header_filtersearchGrid_tadigCode$ptdgCode");
		GenericHelper.waitForLoadmask();
		ComboBoxHelper.select("tadigCode_gwt_uid_", roamingDefinition);
		GenericHelper.waitForLoadmask();
		SearchHelper searchHelper = new SearchHelper();
		searchHelper.clickSearch();
	}

	public void MCCMNCCompare() throws Exception {
		String MCC = "123";
		String MNC = "356";
		GenericHelper.waitForLoadmask();
		String MCC1 = GridHelper.getCellValue("prdfMnc", 1, 4);
		String MNC1 = GridHelper.getCellValue("prdfMcc", 1, 5);
		assertEquals(MCC, MCC1, "MNC Values are not matching");
		assertEquals(MNC, MNC1, "MCC Values are not matching");
	}

}
