package com.subex.rocps.sprintTestCase.bklg41;

import java.util.ArrayList;
import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class AccountContactInformationTab extends PSAcceptanceTest {

	Map<String, String> accContactInfoMap = null;
	String accountCompanyName;
	String accountAddressTypes;
	String accountAddressTo;
	String accountSalutation;
	String accountBuildingOrFloor;
	String accountStreet;
	String accountTown;
	String accountCounty;
	String accountState;
	String accountPostalCode;
	String accountCompanyRegNo;
	String accountRegistrationCity;
	String accountRegistrationType;
	String accountYearsTrading;
	String accountCountryName;
	String accountAddressName;
	String mainAccountAddressFlag;

	String contactName;
	String contactEmail;
	String officeNumbers;
	String mobileNumbers;
	String homeNumbers;
	String fax;
	String mainContactFlag;
	ArrayList<String> errorList = new ArrayList<String>();
	String errorMessage="";

	/*
	 * Constructor initialising with map from account class paramVal : column
	 * number information
	 */
	public AccountContactInformationTab(Map<String, String> accContactInfoMap) {

		this.accContactInfoMap = accContactInfoMap;

		initAccountContactInfoTabVariables();

	}

	/*
	 * Method for initialising the instance variables
	 */
	protected void initAccountContactInfoTabVariables() {

		accountCompanyName = accContactInfoMap.get("CompanyName");
		accountAddressTypes = accContactInfoMap.get("AddressTypes");
		accountAddressTo = accContactInfoMap.get("AddressTo");
		accountSalutation = accContactInfoMap.get("Salutation");
		accountBuildingOrFloor = accContactInfoMap.get("BuildingOrFloor");
		accountStreet = accContactInfoMap.get("Street");
		accountTown = accContactInfoMap.get("Town");
		accountCounty = accContactInfoMap.get("County");
		accountState= accContactInfoMap.get("State");
		accountPostalCode = accContactInfoMap.get("PostalCode");
		accountCountryName = accContactInfoMap.get("Country");
		accountCompanyRegNo = accContactInfoMap.get("CompanyRegNo");
		accountAddressName = accContactInfoMap.get("AddressName");
		accountRegistrationCity = accContactInfoMap.get("RegistrationCity");
		accountRegistrationType = accContactInfoMap.get("RegistrationType");
		accountYearsTrading = accContactInfoMap.get("YearsTrading");
		mainAccountAddressFlag = accContactInfoMap.get("MainAccountAddressFlag");
		errorMessage = accContactInfoMap.get("ErrorMessage");
		contactName = accContactInfoMap.get("ContactName");
		contactEmail = accContactInfoMap.get("ContactEmail");
		officeNumbers = accContactInfoMap.get("ContactOfficeNumber");
		mobileNumbers = accContactInfoMap.get("ContactMobileNumber");
		homeNumbers = accContactInfoMap.get("ContactHomeNumber");
		fax = accContactInfoMap.get("ContactFax");
		mainContactFlag = accContactInfoMap.get("MainContactFlag");

	}

	/*
	 * configuring account contact information
	 */
	protected void contactInformation() throws Exception {
		try {
			TabHelper.gotoTab(or.getProperty("Detail_contactInfoTabXpath"));
			GenericHelper.waitTime(3, "Loading tab with contact information");

			addressDetails();
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
			String[] expectedMessageArr = errorMessage.split(",", -1);

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

				if (townArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_townTxtId"), townArr[i]);
				else
					errorList.add("Town");

				if (countyArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_countyTxtId"), countyArr[i]);
				else
					errorList.add("County");

				if (stateArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_stateTxtId"), stateArr[i]);
				else
					errorList.add("State");


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
				
				GenericHelper.waitForLoadmask();
				
			}

		} catch (Exception e) {
			// FailureHelper.setErrorMessage(e);
			throw e;
		}
	}

	/*
	 * configuring account contact details
	 */
	protected void contactDetails() throws Exception {

		try {
			String[] contactNameArr = contactName.split(",", -1);
			String[] contactEmailArr = contactEmail.split(",", -1);
			String[] mainContactFlArr = mainContactFlag.split(",", -1);
			String[] mobileNumArr = mobileNumbers.split(",", -1);
			String[] officeNumArr = officeNumbers.split(",",-1);
			String[] homeNumArr = homeNumbers.split(",",-1);
			String[] faxArr = fax.split(",",-1); 

			for (int i = 0; i < contactEmailArr.length; i++) {
				ButtonHelper.click(or.getProperty("Detail_contactAddBtnId"));
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type(or.getProperty("Detail_contactNameTxtId"), contactNameArr[i]);
				TextBoxHelper.type(or.getProperty("Detail_contactEmailTxtId"), contactEmailArr[i]);

				if (!mobileNumbers.isEmpty()) {
					if (mobileNumArr[i] != null) {
						TextBoxHelper.type(or.getProperty("Detail_mobileNumTxtId"), mobileNumArr[i]);
					}
				}

				if (officeNumArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_officeNumTxtId"), officeNumArr[i]);
				
				if (homeNumArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_homeNumTxtId"), homeNumArr[i]);
				
				if (faxArr[i] != null)
					TextBoxHelper.type(or.getProperty("Detail_faxTxtId"), faxArr[i]);
				
				if (mainContactFlArr[i] != null || mainContactFlArr[i].equalsIgnoreCase("true")
						|| mainContactFlArr[i].equalsIgnoreCase("y"))
					CheckBoxHelper.check(or.getProperty("Detail_mainContactFl"));

				ButtonHelper.click(or.getProperty("Detail_accContactSaveBtnId"));
				GenericHelper.waitForLoadmask();
			}

		} catch (Exception e) {
			throw e;
		}

	}

}
