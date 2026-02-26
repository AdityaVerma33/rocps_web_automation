package com.subex.rocps.automation.helpers.application.bills.billbreakdown;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.PropertyGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class BillBreakdownInputImpl extends PSAcceptanceTest {

	protected String clientPartition;
	protected String billInputName;
	protected String billDP;
	protected String billTable;
	protected String billComp;
	protected String billQueryComp;
	protected String billTableNonUsage;
	protected Map<String, String> billInputImplmap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();

	/*
	 * This method is to create new Bill Breakdown Input
	 */

	public void createNewbillBreakdownInput(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to create new Bill Breakdown Input
	 */
	public void detailConfig(String billInputName, String billDP, String billTable, String billComp,
			String billQueryComp) throws Exception {
		TextBoxHelper.type("PS_Detail_billInput_name_txtID", billInputName);
		PropertyGridHelper.typeInTextBox("Bill Breakdown DP *", billDP);
		PropertyGridHelper.selectInComboBox("Bill Breakdown Table *", billTable);
		PropertyGridHelper.selectInComboBox("Bill Dialy Item Component *", billComp);
		PropertyGridHelper.selectInComboBox("Bill Query and Processor Component *", billQueryComp);

	}

	/*
	 * This method is for Nonusagae BillInput Config
	 */
	public void nonusageBillInputConfig(String billTableNonUsage) throws Exception {
		if (!billTableNonUsage.isEmpty()) {
			TabHelper.gotoTab("PS_detail_billInput_nonusgae_panelID");
			GenericHelper.waitTime(3, "Loading tab with non usage billInput");
			PropertyGridHelper.selectInComboBox("Bill Breakdown Table", billTableNonUsage);
		}
	}
	/*
	 * This method is to save Bill Breakdown Input
	 */

	public void billBreakdownInputSave(String  billInputName) throws Exception {
		/*ButtonHelper.click("PS_Detail_billInput_savebtn");
		GenericHelper.waitForSave(searchScreenWaitSec);*/
		genericHelperObj.detailSave( "PS_Detail_billInput_savebtn",  billInputName, "Name" );
	}
	
	/*
	 * This method is to edit Bill Breakdown Input
	 */
	public void ediDetailConfig(String billInputName, String billDP, String billTable, String billComp,
			String billQueryComp) throws Exception {
		if(ValidationHelper.isNotEmpty( billInputName ))
			TextBoxHelper.type("PS_Detail_billInput_name_txtID", billInputName);
		PropertyGridHelper.typeInTextBox("Bill Breakdown DP *", billDP);
		PropertyGridHelper.selectInComboBox("Bill Breakdown Table *", billTable);
		PropertyGridHelper.selectInComboBox("Bill Dialy Item Component *", billComp);
		PropertyGridHelper.selectInComboBox("Bill Query and Processor Component *", billQueryComp);

	}


	/*
	 * method for clicking delete action in bill breakdown Input search screen
	 */
	public void clickDeleteAction(String billInputName) throws Exception {

		genericHelperObj.clickDeleteOrUnDeleteAction(billInputName, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in bill breakdown Input search screen
	 */
	public void clickUnDeleteAction(String billInputName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(billInputName, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}

}
