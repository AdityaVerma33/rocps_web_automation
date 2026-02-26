package com.subex.rocps.sprintTestCase.bklg109;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class fileUploadCategory extends PSAcceptanceTest {

	PSGenericHelper genHelperObj = new PSGenericHelper();
	String clientPartition;
	Map<String, String> dpMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	public fileUploadCategory() throws Exception {

	}

	public void fileUploadCategoryctg(String category) throws Exception {

		try {
			NavigationHelper.navigateToScreen("Reference Tables");
			ComboBoxHelper.select("displayString_gwt_uid_", "File Upload Category");
			GenericHelper.waitForLoadmask();

			int rowCount = GridHelper.getRowCount("gridPanel");
			boolean dataExistFlag = false;
			for (int i = 1; i <= rowCount; i++) {
				ArrayList<String> existingData = GridHelper.getRowValues("gridPanel", i);
				if ((existingData.get(1).equals(category))) {

					dataExistFlag = true;
					Log4jHelper.logInfo("File Upload Category already exists and the Category is" + category);

					break;
				}

			}
			if (dataExistFlag == false) {

				GenericHelper.waitForLoadmask();
				NavigationHelper.navigateToAction("Common Tasks", "New");
				TextBoxHelper.type("//input[@id='pfucName']", category);
				Log4jHelper.logInfo("Configured Category is " + category);

				ButtonHelper.click("Save");

			}
		} catch (Exception e) {
			FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	public void initializeInstanceVariables() {

		clientPartition = dpMap.get("ClientPartition");

	}
}
