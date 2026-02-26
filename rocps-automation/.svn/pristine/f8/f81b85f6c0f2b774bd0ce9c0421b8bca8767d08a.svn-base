
package com.subex.rocps.sprintTestCase.bklg41;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class AccountTest extends AccountContactInformationTab{
	Boolean exit_flag;
	public AccountTest(Map<String, String> accContactInfoMap) {
		super(accContactInfoMap);
		// TODO Auto-generated constructor stub
	}



	
	protected void contactInformation() throws Exception {
		try {
			TabHelper.gotoTab(or.getProperty("Detail_contactInfoTabXpath"));
			GenericHelper.waitTime(3, "Loading tab with contact information");

			addressDetails();
			if (returnFlagValue() != true)
				contactDetails();

		} catch (Exception e) {

			throw e;
		}
	}



/*
 * configuring address details
 */
protected void addressDetails() throws Exception {

	try {

		String[] accDetailsArr = accountCompanyName.split(",", -1);
		String[] accAddrArrTypeArr = accountAddressTypes.split(",", -1);
		String[] accCtrNameArr = accountCountryName.split(",", -1);
		String[] accountAddressTxtArr = accountAddressName.split(",", -1);
		String[] accAddrMainFlArr = mainAccountAddressFlag.split(",", -1);
		String[] expectedMessageArr = errorMessage.split(",", -1);
		String[] accAddrToArr = accountAddressTo.split(",", -1);
		String[] salutationArr = accountSalutation.split(",", -1);
		String[] buildingOrFloorArr = accountBuildingOrFloor.split(",", -1);
		String[] streetArr = accountStreet.split(",", -1);
		String[] townArr = accountTown.split(",", -1);
		String[] countyArr = accountCounty.split(",", -1);
		String[] stateArr = accountState.split(",", -1);
		String[] postalCodeArr = accountPostalCode.split(",", -1);
		String[] compRegNoArr = accountCompanyRegNo.split(",", -1);
		String[] regCityArr = accountRegistrationCity.split(",", -1);
		String[] regTypeArr = accountRegistrationType.split(",", -1);
		String[] yearsTradingArr = accountYearsTrading.split(",", -1);

		for (int i = 0; i < accDetailsArr.length; i++) {
			
			ButtonHelper.click(or.getProperty("Detail_addressAddToolBarBtnId"));
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type(or.getProperty("Detail_accountCmpNameTxtId"), accDetailsArr[i]);
			ComboBoxHelper.select(or.getProperty("Detail_accountAddressTypeTxtId"), accAddrArrTypeArr[i]);
			ComboBoxHelper.select(or.getProperty("Detail_accountCountryNameId"), accCtrNameArr[i]);
			TextBoxHelper.type(or.getProperty("Detail_accountAddressTxtId"), accountAddressTxtArr[i]);
			if (accAddrMainFlArr[i] != null || accAddrMainFlArr[i].equalsIgnoreCase("true")
					|| accAddrMainFlArr[i].equalsIgnoreCase("Y"))
				CheckBoxHelper.check(or.getProperty("Detail_mainAccAddrFlId"));

			if (accAddrToArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_accountAddressToTxtId"), accAddrToArr[i]);

			if (salutationArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_accountSalutationTxtId"), salutationArr[i]);

			if (buildingOrFloorArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_buildingOrFloorTxtId"), buildingOrFloorArr[i]);

			if (streetArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_streetTxtId"), streetArr[i]);

			if (townArr[i] != "")
				TextBoxHelper.type(or.getProperty("Detail_townTxtId"), townArr[i]);

			if (countyArr[i] != "")
				TextBoxHelper.type(or.getProperty("Detail_countyTxtId"), countyArr[i]);

			if (stateArr[i] != "")
				TextBoxHelper.type(or.getProperty("Detail_stateTxtId"), stateArr[i]);
			
			if (postalCodeArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_postalCodeTxtId"), postalCodeArr[i]);

			if (compRegNoArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_companyRegNoTxtId"), compRegNoArr[i]);

			if (regCityArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_registrationCityTxtId"), regCityArr[i]);

			if (regTypeArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_registrationTypeTxtId"), regTypeArr[i]);

			if (yearsTradingArr[i] != null)
				TextBoxHelper.type(or.getProperty("Detail_yearsTradingTxtId"), yearsTradingArr[i]);
			
			ButtonHelper.click(or.getProperty("Detail_accountAddressSaveId"));
			
			Assert.assertEquals(driver.findElement(By.id("errorText")).getText(), expectedMessageArr[i]);
			exit_flag=true;
			}
			
		}

	 catch (Exception e) {
		// FailureHelper.setErrorMessage(e);
		throw e;
	}
	
	
	
	
	
	
	
}
protected boolean returnFlagValue(){
	return exit_flag;
}



		
	
	
}



