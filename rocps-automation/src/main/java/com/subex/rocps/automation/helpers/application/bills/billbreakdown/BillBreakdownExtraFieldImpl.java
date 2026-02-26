package com.subex.rocps.automation.helpers.application.bills.billbreakdown;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

public class BillBreakdownExtraFieldImpl extends PSAcceptanceTest {

	protected Map<String, String> billExtraFieldMap = null;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String clientPartition;
	protected String component;
	protected String displayName;
	protected String name;

	/*
	 * This method is to create new BillBreakdown Extra Field
	 */
	public void createNewbillBreaskdownConfig(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to add Bill Breakdown ExtraField
	 */
	public void addBillbreakdownExtraField(String component, String displayName) throws Exception {
		ComboBoxHelper.select("PS_Detail_eventProcessor_comboId", component);
		TextBoxHelper.type("PS_Detail_billbreakdownExtraField_displayname_txtID", displayName);
	}
	
	/*
	 * This method is to edit billbreakdown extra field
	 */
	public void editBillbreakdownExtraField(String component, String displayName,String name) throws Exception {
		if(ValidationHelper.isNotEmpty( component ))
			ComboBoxHelper.select("PS_Detail_eventProcessor_comboId", component);
		if(ValidationHelper.isNotEmpty( displayName ))
			TextBoxHelper.type("PS_Detail_billbreakdownExtraField_displayname_txtID", displayName);
		if(ValidationHelper.isNotEmpty( name ))
			assertEquals( TextBoxHelper.getValue( "pbefFieldName" ), name );
	}

	/*
	 * This method is to save bill breakdown Extra Field
	 */
	public void saveBillBreakdownExtraField(String displayName) throws Exception {
		genericHelperObj.detailSave( "PS_Detail_billBreakdownExtraFiled_savebtn", displayName, "Display Name");		
		GenericHelper.waitForSave(searchScreenWaitSec);
	}

	/*
	 * This method is to perform delete action
	 */
	public void clickDeleteAction(String displayName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(displayName, "Display Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to perform un delete action
	 */
	public void clickUnDeleteAction(String displayName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(displayName, "Display Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}
}
