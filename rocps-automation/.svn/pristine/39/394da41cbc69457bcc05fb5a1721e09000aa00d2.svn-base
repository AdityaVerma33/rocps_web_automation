package com.subex.rocps.automation.helpers.application.bills.billbreakdown;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownInputGroupImpl extends PSAcceptanceTest {

	protected String clientPartition;
	protected String billInputGrpName;
	protected String billInputName;

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected Map<String, String> billinputGrpImpl = null;

	/*
	 * This method is to create new BillBreakdown Configuration
	 */
	public void createNewbillBreakdownInputGrp(String clientPartition) throws Exception {
		genericHelperObj.clickNewAction(clientPartition);
		GenericHelper.waitForLoadmask();
	}

	/*
	 * This method is to add Bill Breakdown Input
	 */
	public void addBillbreakdownInput(String billInputGrpName, String billInputName) throws Exception {

		TextBoxHelper.type("PS_Detail_billInputGrp_Name_txtID", billInputGrpName);
		String spliregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billInputArr = billInputName.split(spliregex, -1);
		for (int i = 0; i < billInputArr.length; i++) {
			int row = GridHelper.getRowNumber( "billBrkdwnGrpDetail", billInputArr[i], 1 );
			if(row == 0)
			{
			ButtonHelper.click("PS_Detail_billInput_Add_btnID");
			GenericHelper.waitForLoadmask();
			//assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Input Search");
			assertTrue( genericHelperObj.waitforPopupHeaderElement( "Name" ), "Bill Breakdown Input Search screen did not open");
			genericHelperObj.waitforPopupHeaderElement( "Name" );
			GridHelper.clickRow("Detail_popUpWindowId", "Detail_eventDefn_gridID", billInputArr[i], "Name");
			ButtonHelper.clickIfEnabled("Detail_popUpWindowId", "OK_Button_ByID");
			}else
				assertEquals( GridHelper.getCellValue( "billBrkdwnGrpDetail", row, 1 ), billInputArr[i] );
		}
	}
	/*
	 * This method is to save bill breakdown Configuration
	 */

	public void billbreakdownConfigSave(String billInputGrpName) throws Exception {
		//ButtonHelper.click("PS_Detail_billInputGrp_Save_btnID");
		//GenericHelper.waitForSave(searchScreenWaitSec);
		genericHelperObj.detailSave( "PS_Detail_billInputGrp_Save_btnID", billInputGrpName, "Name" );
	}
	
	/*
	 * This method is to edit Bill Breakdown Input
	 */
	public void editBillbreakdownInput(String billInputGrpName, String billInputName) throws Exception {

		
			TextBoxHelper.type("PS_Detail_billInputGrp_Name_txtID", billInputGrpName);
		String spliregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billInputArr = billInputName.split(spliregex, -1);
		for (int i = 0; i < billInputArr.length; i++) {
			int row = GridHelper.getRowNumber( "billBrkdwnGrpDetail", billInputArr[i], 1 );
			if(row == 0)
			{
			ButtonHelper.click("PS_Detail_billInput_Add_btnID");
			GenericHelper.waitForLoadmask();
			//assertEquals(NavigationHelper.getScreenTitle(), "Bill Breakdown Input Search");
			genericHelperObj.waitforPopupHeaderElement( "Name" );
			GridHelper.clickRow("Detail_popUpWindowId", "Detail_eventDefn_gridID", billInputArr[i], "Name");
			ButtonHelper.clickIfEnabled("Detail_popUpWindowId", "OK_Button_ByID");
			}else
				assertEquals( GridHelper.getCellValue( "billBrkdwnGrpDetail", row, 1 ), billInputArr[i] );
		}
	}

	/*
	 * method for clicking delete action in bill breakdown Input Grp search screen
	 */
	public void clickDeleteAction(String billInputGrpName) throws Exception {

		genericHelperObj.clickDeleteOrUnDeleteAction(billInputGrpName, "Name", "Delete");
		GenericHelper.waitForLoadmask();
	}

	/*
	 * method for clicking un delete action in bill breakdown Input Grp search
	 * screen
	 */
	public void clickUnDeleteAction(String billInputGrpName) throws Exception {
		genericHelperObj.clickDeleteOrUnDeleteAction(billInputGrpName, "Name", "Undelete");
		GenericHelper.waitForLoadmask();
	}

}
