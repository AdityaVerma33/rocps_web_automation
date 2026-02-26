package com.subex.rocps.automation.helpers.application.bills.billingcycle;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillingCycleDetailImpl extends PSAcceptanceTest {
	PSDataComponentHelper psDataComponentHelper = new PSDataComponentHelper();

	/*
	 * this method is for basic Details of Billing cycle
	 */
	public void basicDetails(String name, String franchise) throws Exception {

		TextBoxHelper.type("PS_Detail_billingCycle_name_txtID", name);
		ComboBoxHelper.select("PS_Detail_billingCycle_franchise_txtID", franchise);
	}

	/*
	 * this method is for editbasic Details of Billing cycle
	 */
	public void editbasicDetails(String name, String franchise) throws Exception {

		assertEquals(TextBoxHelper.getValue("PS_Detail_billingCycle_name_txtID"), name,
				"Billing cycle name is not matched");
		if (ValidationHelper.isNotEmpty(franchise))
			ComboBoxHelper.select("PS_Detail_billingCycle_franchise_txtID", franchise);
	}

	/*
	 * This method is for billing Cycle Frequency panel
	 */
	public void billingCycleFrequency(String frequency, String anniversaryAlign, String dayOfMonthWeek,
			String alignmentDate) throws Exception {

		// ComboBoxHelper.select( "PS_Detail_billingCycle_frequency_comboID", frequency );
		psDataComponentHelper.selectComboBoxVal("PS_Detail_billingCycle_frequency_comboID", frequency);

		if (ValidationHelper.isTrue(anniversaryAlign)) {
			CheckBoxHelper.check("PS_Detail_billingCycle_anniversayalign_chckbx");

		} else {
			CheckBoxHelper.check("PS_Detail_billingCycle_alignUsingthe_chckbx");
			ComboBoxHelper.select("PS_Detail_billingCycle_dayOfMonthWeek_comboId", dayOfMonthWeek);

			String valArr[] = ComboBoxHelper.getAllValues("PS_Detail_billingCycle_alignmentDate_chckbx");
			alignmentDate = valArr[1];
			ComboBoxHelper.select("PS_Detail_billingCycle_alignmentDate_chckbx", alignmentDate);
		}
	}

	/*
	 * This method is for edit billing Cycle Frequency panel
	 */
	public void editbillingCycleFrequency(String frequency, String anniversaryAlign, String dayOfMonthWeek,
			String alignmentDate) throws Exception {

		if (ValidationHelper.isNotEmpty(frequency))
			ComboBoxHelper.select("PS_Detail_billingCycle_frequency_comboID", frequency);
		if (ValidationHelper.isTrue(anniversaryAlign)) {
			CheckBoxHelper.check("PS_Detail_billingCycle_anniversayalign_chckbx");

		} else {
			CheckBoxHelper.check("PS_Detail_billingCycle_alignUsingthe_chckbx");
			ComboBoxHelper.select("PS_Detail_billingCycle_dayOfMonthWeek_comboId", dayOfMonthWeek);

			String valArr[] = ComboBoxHelper.getAllValues("PS_Detail_billingCycle_alignmentDate_chckbx");
			alignmentDate = valArr[1];
			ComboBoxHelper.select("PS_Detail_billingCycle_alignmentDate_chckbx", alignmentDate);
		}
	}

	/*
	 * This method is for Open/Close Period Dates
	 */
	public void periodOpenCloseDates(String openPeriodDays, String closeCurrentPeriodDays) throws Exception {
		TextBoxHelper.type("PS_Detail_billingCycle_openPeriodDays_txtID", openPeriodDays);
		TextBoxHelper.type("PS_Detail_billingCycle_closePeriodDays_txtID", closeCurrentPeriodDays);
	}

	/*
	 * This method is for edit Open/Close Period Dates
	 */
	public void editperiodOpenCloseDates(String openPeriodDays, String closeCurrentPeriodDays) throws Exception {
		if (ValidationHelper.isNotEmpty(openPeriodDays))
			TextBoxHelper.type("PS_Detail_billingCycle_openPeriodDays_txtID", openPeriodDays);
		if (ValidationHelper.isNotEmpty(closeCurrentPeriodDays))
			TextBoxHelper.type("PS_Detail_billingCycle_closePeriodDays_txtID", closeCurrentPeriodDays);
	}

	/*
	 * This method is for production start Date
	 */
	public void productionStartDate(String asSoonAfterPeriod, String specificDayOfWeek, String dayOfWeek)
			throws Exception {

		if (ValidationHelper.isTrue(asSoonAfterPeriod))
			CheckBoxHelper.check("PS_Detail_billingCycle_asSoonaspossible_chkbx");
		if (ValidationHelper.isTrue(specificDayOfWeek))
			CheckBoxHelper.check("PS_Detail_billingCycle_specificDayofWeek_chkbx");
		ComboBoxHelper.select("PS_Detail_billingCycle_dayOfWeek_comboID", dayOfWeek);
	}

	/*
	 * This method is for Bill Issue Date
	 */
	public void billIssueDate(String issueDate) throws Exception {
		TextBoxHelper.type("PS_Detail_billingCycle_issueDate_txtID", issueDate);
	}

	/*
	 * This method is for edit Bill Issue Date
	 */
	public void editbillIssueDate(String issueDate) throws Exception {
		if (ValidationHelper.isTrue(issueDate))
			TextBoxHelper.type("PS_Detail_billingCycle_issueDate_txtID", issueDate);
	}

	/*
	 * This method is for Late Traffic Mapping
	 */
	public void lateTrafficMapping(String lateTrafficOpenBillPeriod, String lateTrafficClosedBillPeriod)
			throws Exception {

		if (ValidationHelper.isTrue(lateTrafficOpenBillPeriod))
			CheckBoxHelper.check("PS_Detail_billingCycle_lateTrafficOpenperiod_chkbx");
		if (ValidationHelper.isTrue(lateTrafficClosedBillPeriod))
			CheckBoxHelper.check("PS_Detail_billingCycle_lateTrafficCloseperiod_chkbx");
	}

}
