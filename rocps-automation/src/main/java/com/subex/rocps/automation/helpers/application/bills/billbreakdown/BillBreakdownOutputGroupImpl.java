package com.subex.rocps.automation.helpers.application.bills.billbreakdown;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownOutputGroupImpl extends PSAcceptanceTest {

	protected String clientPartition;
	protected String billOutputGrpName;
	protected String billOutputName;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected Map<String, String> billOutputGrpImplMap = null;

	/*
	 * This method is to create new BillBreakdown Configuration
	 */
	public void createNewbillBreakdownConfig(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();

	}

	/*
	 * This method is to add Bill Breakdown Output
	 */
	public void addBillbreakdownOutput(String billOutputGrpName, String billOutputName) throws Exception {

		TextBoxHelper.type("PS_Detail_billOutputGrp_name_txtID", billOutputGrpName);
		String spliregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billOutputArr = billOutputName.split(spliregex, -1);
		for (int i = 0; i < billOutputArr.length; i++) {
			int row = GridHelper.getRowNumber( "billBrkdwnOutputGrpDetail", billOutputArr[i] );
			if(row == 0)
			{
			ButtonHelper.click("PS_Detail_billOutputGrp_add_btnID");
			GenericHelper.waitForLoadmask();
			/*assertTrue(NavigationHelper.isTitlePresent("Bill Breakdown Output Search"),
					"Bill Breakdown Output page is not found");*/
			assertTrue( genericHelperObj.waitforPopupHeaderElement( "Name" ), "Bill Breakdown Output Search screen did not open");
			GridHelper.clickRow("Detail_popUpWindowId", "Detail_eventDefn_gridID", billOutputArr[i], "Name");
			ButtonHelper.clickIfEnabled("Detail_popUpWindowId", "OK_Button_ByID");
			GenericHelper.waitForLoadmask();
			// assertTrue(GridHelper.isValuePresent("screensGrid", billOutputArr[i],
			// "Name"), "Grid doesnt have this value");
			}else
				assertEquals( GridHelper.getCellValue( "billBrkdwnOutputGrpDetail", row, 1 ), billOutputArr[i] );
		}
	}
	/*
	 * this method is to save bill breakdown Output Group
	 */

	public void billbreakdownConfigSave(String billOutputGrpName) throws Exception {
		/*ButtonHelper.click("PS_Detail_billOutputGrp_save_btnID");
		GenericHelper.waitForSave(searchScreenWaitSec);*/
		genericHelperObj.detailSave( "PS_Detail_billOutputGrp_save_btnID", billOutputGrpName, "Name" );
	}

	/*
	 * method for clicking delete action in bill breakdown Output Group search
	 * screen
	 */
	public void clickDeleteAction(String billOutputGrpName) throws Exception {

		genericHelperObj.clickDeleteOrUnDeleteAction(billOutputGrpName, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in bill breakdown Output Group search
	 * screen
	 */
	public void clickUnDeleteAction(String billOutputGrpName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(billOutputGrpName, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}

}
