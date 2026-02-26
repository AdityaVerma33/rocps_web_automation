package com.subex.rocps.sprintTestCase.bklg171;

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
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.sprintTestCase.bklg171.Roamingexex;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class roaming extends PSAcceptanceTest {
	public void roamingFileStatustoTAPError(String fileName) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming File Status");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("PS_FileNameid");
			TextBoxHelper.type("PS_FileName_Textbox", fileName);
			ButtonHelper.click("PS_FileName_Search");
			GenericHelper.waitForLoadmask();
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("PS_Details_view");
			ButtonHelper.click("PS_Details_error");
			GenericHelper.waitForLoadmask();
			PSGenericHelper psGenericHelperObj = new PSGenericHelper();

			String[] columnsSplit;

			columnsSplit = Roamingexex.tapColumns.split("\\|");

			for (int i = 0; i < columnsSplit.length; i++)
				System.out.println(columnsSplit[i]);

			List<String> tapErrorcolumnValues = psGenericHelperObj.getGridColumns("grid_column_header_searchGrid_");
			GenericHelper.waitForLoadmask();
			assertEquals(columnsSplit.length, tapErrorcolumnValues.size() - 1);
			for (int i = 0; i < columnsSplit.length; i++) {
				System.out.println(columnsSplit[i]);
				assertTrue(tapErrorcolumnValues.contains(columnsSplit[i]),
						"Value is not present in tapErrorcolumnValues" + columnsSplit[i]);
			}

		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}

	public void roamingFileStatustoRoamingRecords(String fileName, String callType) throws Exception {
		try {
			NavigationHelper.navigateToScreen("Roaming File Status");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("PS_FileNameid");
			TextBoxHelper.type("PS_FileName_Textbox", fileName);
			ButtonHelper.click("PS_FileName_Search");
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("PS_Details_view");
			ButtonHelper.click("PS_Details_RoamingRec");
			GenericHelper.waitForLoadmask();
			ComboBoxHelper.select("ptctCallType_gwt_uid_", callType);
			ButtonHelper.click("PS_search_roamingRecords");
			PSGenericHelper psGenericHelperObj = new PSGenericHelper();

			String[] roamingRecordscolumnsSplit;

			roamingRecordscolumnsSplit = Roamingexex.RoamingRecordColumns.split("\\|");

			for (int i = 0; i < roamingRecordscolumnsSplit.length; i++)
				System.out.println(roamingRecordscolumnsSplit[i]);

			List<String> roamingRecordcolumnValues = psGenericHelperObj
					.getGridColumns("grid_column_header_searchGrid_");
			for (int i = 0; i < roamingRecordcolumnValues.size(); i++) {
				if (roamingRecordcolumnValues.get(i) == "" || roamingRecordcolumnValues.get(i) == null)
					roamingRecordcolumnValues.remove(i);
			}
			assertEquals(roamingRecordscolumnsSplit.length, roamingRecordcolumnValues.size() - 1);
			for (int i = 0; i < roamingRecordscolumnsSplit.length; i++) {
				System.out.println(roamingRecordscolumnsSplit[i]);
				assertTrue(roamingRecordcolumnValues.contains(roamingRecordscolumnsSplit[i].trim()),
						"Value is not present in roamingrecordcolumnValues" + roamingRecordscolumnsSplit[i]);
			}
		} catch (Exception e) {
			FailureHelper.reportFailure(e);
			throw e;
		}
	}
}
