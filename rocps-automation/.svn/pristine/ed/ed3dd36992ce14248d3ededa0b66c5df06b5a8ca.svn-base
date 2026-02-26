package com.subex.rocps.sprintTestCase.bklg44;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.ImageHelper;
import com.subex.automation.helpers.component.LabelHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import net.bytebuddy.description.type.TypeDescription.Generic;

import com.subex.automation.helpers.component.SearchGridHelper;;

@SuppressWarnings("unused")
public class Deal extends PSAcceptanceTest {

	private String oldDealRates;
	private String newDealRates;

	public void dealcopyAction(String Deal_Name, String band_Name) throws Exception {
		try {

			NavigationHelper.navigateToScreen("Deal");
			GenericHelper.waitForLoadmask();
			SearchGridHelper.gridFilterSearchWithTextBox("Deal_Nameid", Deal_Name, "Deal Name");
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("Deal_tariffPeriod");
			ButtonHelper.click("Deal_tierRate");
			ComboBoxHelper.select("Band_Group", "BG1");
			TextBoxHelper.type("Band_Name", band_Name);
			ButtonHelper.click("Tier_Rates_Search");
			GenericHelper.waitForLoadmask();
			// GenericHelper.waitForElement(xpath, waitInSec);
			ArrayList<String> oldDealRateValues = GridHelper.getRowValues("tierRateGrid", 1);
			StringBuilder strVal = new StringBuilder();
			for (String str : oldDealRateValues) {
				strVal.append(str).append(";");
			}
			oldDealRates = strVal.toString();

			System.out.println("oldDealRateValues = " + strVal);
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("DiscardButton");
			GenericHelper.waitForLoadmask();

			NavigationHelper.navigateToAction("Common Tasks", "Copy Deal");
			GenericHelper.waitForLoadmask();
			GenericHelper.waitForLoadmask();
			NavigationHelper.getScreenTitle();
		}

		catch (Exception e) {

		}
	}

	public void dealCopyDateValidation(String fromDate, String toDate) throws Exception {
		try {

			TextBoxHelper.type("pdelFromDttm", fromDate);
			TextBoxHelper.type("pdelToDttm", toDate);
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("copyDealDetail.OK");
			GenericHelper.waitForLoadmask();
			String errorGUI = ElementHelper.getText("errorText");
			String errorUser = "One or more mandatory fields has to be filled and cannot be empty or invalid.";
			assertEquals(errorGUI, errorUser);

		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void dealcopyCreation(String fromDate, String toDate) throws Exception {
		try {
			boolean copyrates;
			TextBoxHelper.type("pdelFromDttm", fromDate);
			TextBoxHelper.type("pdelToDttm", toDate);
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("copyDealDetail.OK");
			GenericHelper.waitForLoadmask();

			// GenericHelper.waitForElement("Copy_msg", 20);
			String msg = "Deal copy success with new deal contract number";
			String completeMsg = LabelHelper.getText("Copy_msg");
			assertTrue(completeMsg.contains(msg));
			String contract_No = "";
			int SingleQuoteCount = 0;
			for (int i = 0; i < completeMsg.length(); i++) {
				if (completeMsg.charAt(i) == '\'') {
					SingleQuoteCount++;
				}
				if (SingleQuoteCount == 1 && completeMsg.charAt(i) != '\'') {
					contract_No = contract_No + completeMsg.charAt(i);
				}
			}
			GenericHelper.waitForElement("Copy_msg", 20);
			ButtonHelper.click("Copied_popup");
			SearchGridHelper.gridFilterSearchWithTextBox("Deal_Contractno", contract_No, "Contract No");
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);

		} catch (Exception e) {

		}

	}

	public void dealCopyRatesCreation(String fromDate, String toDate) throws Exception {
		try {
			boolean copyrates;
			TextBoxHelper.type("pdelFromDttm", fromDate);
			TextBoxHelper.type("pdelToDttm", toDate);
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("copyDealDetail.OK");
			GenericHelper.waitForLoadmask();
			String msg = "Deal copy success with new deal contract number";
			String completeMsg = LabelHelper.getText("Copy_msg");
			assertTrue(completeMsg.contains(msg));
			String contract_No = "";
			int SingleQuoteCount = 0;
			for (int i = 0; i < completeMsg.length(); i++) {
				if (completeMsg.charAt(i) == '\'') {
					SingleQuoteCount++;
				}
				if (SingleQuoteCount == 1 && completeMsg.charAt(i) != '\'') {
					contract_No = contract_No + completeMsg.charAt(i);
				}
			}

			ButtonHelper.click("Copied_popup");
			SearchGridHelper.gridFilterSearchWithTextBox("Deal_Contractno", contract_No, "Contract No");
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1, 2);
			ButtonHelper.click("Deal_tariffPeriod");
			ButtonHelper.click("Deal_tierRate");
			ButtonHelper.click("Tier_Rates_Search");
			GenericHelper.waitForLoadmask();
			ArrayList<String> newDealRateValues = GridHelper.getRowValues("tierRateGrid", 1);
			StringBuilder strVal = new StringBuilder();
			for (String str : newDealRateValues) {
				strVal.append(str).append(";");
			}
			newDealRates = strVal.toString();
			System.out.println("newDealRateValues = " + strVal);
			assertEquals(oldDealRates, newDealRates);
			ButtonHelper.click("CancelButton");
			GenericHelper.waitForLoadmask();
			ButtonHelper.click("DiscardButton");
			GenericHelper.waitForLoadmask();

		} catch (Exception e) {

		}

	}

	public boolean checkDateValidation(String fromDate, String toDate) throws ParseException {

		Date dealFrom = new SimpleDateFormat("MM/dd/yyyy").parse(fromDate);
		Date dealToDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDate);

		if (dealFrom.compareTo(dealToDate) > 0) {
			return false;
		}
		return true;
	}

}
