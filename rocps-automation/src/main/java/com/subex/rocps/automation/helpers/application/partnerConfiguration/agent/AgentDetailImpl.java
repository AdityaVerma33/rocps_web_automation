package com.subex.rocps.automation.helpers.application.partnerConfiguration.agent;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.filters.EntityFilterHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AgentDetailImpl extends PSAcceptanceTest {
	protected String clientPartition;
	protected String companyName;
	protected String franchise;
	protected String currency;
	protected String type;
	protected String agentCode;
	protected String title;
	protected String foreName;
	protected String surname;
	protected String companyAddress;
	protected String addressName;
	protected String typeAddress;
	protected String addressedTo;
	protected String salutation;
	protected String company;
	protected String parentCompany;
	protected String agentAddressName;
	protected String agentTypeAddress;
	protected String agentAddressedTo;
	protected String agentSalutation;
	protected String agentCompany;
	protected String agentStreet1;
	protected String agentStreet2;
	protected String agentStreet3;
	protected String agentStreet4;
	protected String agentTown;
	protected String agentCounty;
	protected String agentPostCode;
	protected String agentCountry;
	protected String agentMainAgentAddress;
	protected String contactNameInternet;
	protected String typeInternet;
	protected String addressInternet;
	protected String defaultAddress;
	protected String contactNameDetail;
	protected String typeContact;
	protected String number;
	protected String defaultNumber;
	protected String rootAgent;
	protected String parentAgent;
	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected String regex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();
	protected Map<String, String> agentImplMap = null;

	public AgentDetailImpl(Map<String, String> agentMap) throws Exception {
		this.agentImplMap = agentMap;
		initializeVariables(agentImplMap);
	}

	/*
	 * This method is used for agent basic details
	 */
	public void newAgentBasicDetails() throws Exception {
		ComboBoxHelper.select("Detail_Franchise_comboID", franchise);
		ComboBoxHelper.select("Detail_Currency_comboID", currency);
		ComboBoxHelper.select("Detail_type_comboID", type);
		TextBoxHelper.type("Detail_AgentCode_TextID", agentCode);
		ComboBoxHelper.select("Detail_Title_comboID", title);
		TextBoxHelper.type("Detail_Forename_txtID", foreName);
		TextBoxHelper.type("Detail_Surname_txtID", surname);
		TextBoxHelper.type("Detail_Company_txtID", company);
	}

	/*
	 * This method is used for hierarchy details
	 */
	public void hierarchy() throws Exception {

		if (ValidationHelper.isNotEmpty( rootAgent ) && ValidationHelper.isTrue(rootAgent)) {
			CheckBoxHelper.check("Detail_RootAgent_radioBtn");
		}
		if (ValidationHelper.isNotEmpty( parentAgent ) && ValidationHelper.isTrue(parentAgent)) {
			CheckBoxHelper.check("Detail_ParentAgent_radioBtn");
			EntityFilterHelper entityObj = new EntityFilterHelper();
			entityObj.agentEntitySearch(parentCompany, "Detail_parentEntity");
		}
	}

	/*
	 * This method is used for addressDetails
	 */
	public void addressDetails() throws Exception {

		String[] agentAddressNameArr = agentAddressName.split(regex, -1);
		String[] agentTypeAddressArr = agentTypeAddress.split(regex, -1);
		String[] agentAddressedToArr = agentAddressedTo.split(regex, -1);
		String[] agentSalutationArr = agentSalutation.split(regex, -1);
		String[] agentCompanyArr = agentCompany.split(regex, -1);
		String[] agentStreet1Arr = agentStreet1.split(regex, -1);
		String[] agentStreet2Arr = agentStreet2.split(regex, -1);
		String[] agentStreet3Arr = agentStreet3.split(regex, -1);
		String[] agentStreet4Arr = agentStreet4.split(regex, -1);
		String[] agentTownArr = agentTown.split(regex, -1);
		String[] agentCountyArr = agentCounty.split(regex, -1);
		String[] agentPostCodeArr = agentPostCode.split(regex, -1);
		String[] agentCountryArr = agentCountry.split(regex, -1);
		String[] agentMainAgentAddressArr = agentMainAgentAddress.split(regex, -1);

		for (int i = 0; i < agentAddressNameArr.length; i++) {
			int row = GridHelper.getRowNumber( "agentAddressGrid", agentAddressNameArr[i], "Address Name" );
			if(row == 0 )
			{
			ButtonHelper.click("Detail_addressAddToolBarBtnId");
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type("Detail_agentAddress_wrapperID", "Detail_agentAddress_txtID", agentAddressNameArr[i]);
			ComboBoxHelper.select("Detail_agent_AddressType_comboID", agentTypeAddressArr[i]);
			TextBoxHelper.type("Detail_agentAddress_wrapperID", "Detail_agent_Street1_txtID", agentStreet1Arr[i]);
			ComboBoxHelper.select("Detail_agent_Country_txtID", agentCountryArr[i]);

			compObj.typeInTextBoxOptional("Detail_agent_addressedTo_txt", agentAddressedTo, agentAddressedToArr, i);
			compObj.typeInTextBoxOptional("Detail_agent_salutation_txtID", agentSalutation, agentSalutationArr, i);
			compObj.typeInTextBoxOptional("Detail_agent_company_txtID", agentCompany, agentCompanyArr, i);
			compObj.typeInTextBoxOptional("Detail_agent_Street2_txtID", agentStreet2, agentStreet2Arr, i);
			compObj.typeInTextBoxOptional("Detail_agent_Street3_txtID", agentStreet3, agentStreet3Arr, i);
			compObj.typeInTextBoxOptional("Detail_agent_Street4_txtID", agentStreet4, agentStreet4Arr, i);
			compObj.typeInTextBoxOptional("Detail_agent_Town_txtID", agentTown, agentTownArr, i);
			compObj.typeInTextBoxOptional("Detail_agent_County_txtID", agentCounty, agentCountyArr, i);
			compObj.typeInTextBoxOptional("Detail_agent_Postcode_txtID", agentPostCode, agentPostCodeArr, i);

			if (agentMainAgentAddressArr[i].equalsIgnoreCase("true")
					|| agentMainAgentAddressArr[i].equalsIgnoreCase("Y"))

				CheckBoxHelper.check("Detail_agentAddress_wrapperID", "Detail_mainagentaddressFlag_radiobtn");
			ButtonHelper.click("popup_agent_ok_btn");
			GenericHelper.waitForLoadmask();
			}else
			{
				assertEquals( GridHelper.getCellValue( "agentAddressGrid", row, "Address Name" ), agentAddressNameArr[i]);
				assertEquals(  GridHelper.getCellValue( "agentAddressGrid", row, "Addressed To" ), agentAddressedToArr[i]);
				assertEquals( GridHelper.getCellValue( "agentAddressGrid", row, "Address Type" ), agentTypeAddressArr[i]);
			}
		}
	}

	/*
	 * This method is for contact Tab Info
	 */
	public void contactInfotab() throws Exception {
		TabHelper.gotoTab("Detail_contactInfoTabXpath");
		GenericHelper.waitForLoadmask(detailScreenWaitSec);
		internetAddressDetails();
		contactDetails();

	}

	/*
	 * This method is for Contact Tab, Internet Address detail
	 */
	public void internetAddressDetails() throws Exception {

		if (!contactNameInternet.isEmpty()) {
			String[] contactNameInternetArr = contactNameInternet.split(regex, -1);
			String[] typeInternetArr = typeInternet.split(regex, -1);
			String[] addressInternetArr = addressInternet.split(regex, -1);
			String[] defaultAddressArr = defaultAddress.split(regex, -1);

			for (int i = 0; i < contactNameInternetArr.length; i++) {
				int row = GridHelper.getRowNumber( "agentInternetAddrGrid", contactNameInternetArr[i], "Contact Name" );
				if (row ==0 )
				{
				ButtonHelper.click("popup_contactInfo_Add_btn");
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type("popup_contactTab_Internet_wrapperID", "popup_InternetContactName_TxtID",
						contactNameInternetArr[i]);

				ComboBoxHelper.select("popup_InternetType_comboID", typeInternetArr[i]);

				TextBoxHelper.type("popup_contactTab_Internet_wrapperID", "popup_InternetAddress_TxtID",
						addressInternetArr[i]);
				if (defaultAddressArr[i].equalsIgnoreCase("true") || defaultAddressArr[i].equalsIgnoreCase("Y"))
					CheckBoxHelper.check("popup_DefaultAddress_checkbx");
				ButtonHelper.click("popup_InternetDetails_ok_button");
				GenericHelper.waitForLoadmask();
				}
				else
				{
					assertEquals( GridHelper.getCellValue( "agentInternetAddrGrid", row, "Contact Name" ) ,contactNameInternetArr[i]);
					assertEquals( GridHelper.getCellValue( "agentInternetAddrGrid", row, "Type" ), typeInternetArr[i]);
					assertEquals( GridHelper.getCellValue( "agentInternetAddrGrid", row, "Address" ),addressInternetArr[i]);
				}
			}
		}
	}

	/*
	 * This method is for contact Tab. Phone Details
	 */
	public void contactDetails() throws Exception {

		if (!contactNameDetail.isEmpty()) {
			String[] contactNameDetailArr = contactNameDetail.split(regex, -1);
			String[] typeContactArr = typeContact.split(regex, -1);
			String[] numberArr = number.split(regex, -1);
			String[] defaultNumberArr = defaultNumber.split(regex, -1);

			for (int i = 0; i < contactNameDetailArr.length; i++) {
				int row = GridHelper.getRowNumber( "agentContactsGrid", contactNameDetailArr[i], "Contact Name" );
				if (row ==0 )
				{
				ButtonHelper.click("Detail_contactInfoTab_contactdetails_add_btn");
				GenericHelper.waitForLoadmask();
				TextBoxHelper.type("popup_contactdetails_wrapperID", "popup_contactNameDetail_TxtID",
						contactNameDetailArr[i]);

				ComboBoxHelper.select("popup_contactType_comboID", typeContactArr[i]);
				TextBoxHelper.type("popup_contactdetails_wrapperID", "popup_contactNumber_txtID", numberArr[i]);
				if (defaultNumberArr[i].equalsIgnoreCase("true") || defaultNumberArr[i].equalsIgnoreCase("Y"))
					CheckBoxHelper.check("popup_contact_defaultNumber_chk_bx");
				ButtonHelper.click("popup_contactDetail_ok_btn");
			}else
			{
				assertEquals( GridHelper.getCellValue( "agentContactsGrid", row, "Contact Name" ),contactNameDetailArr[i]);
				assertEquals(GridHelper.getCellValue( "agentContactsGrid", row, "Type" ), typeContactArr[i]);
				assertEquals( GridHelper.getCellValue( "agentContactsGrid", row, "Number" ), numberArr[i]);
			}
			}
		}
	}
	
	/*
	 * This method is used to edit agent basic details
	 */
	public void editAgentBasicDetails() throws Exception {
		assertEquals( ComboBoxHelper.getValue("Detail_Franchise_comboID"), franchise);
		assertEquals( ComboBoxHelper.getValue("Detail_Currency_comboID"), currency);
		if(ValidationHelper.isNotEmpty( type ))
			ComboBoxHelper.select("Detail_type_comboID", type);
		if(ValidationHelper.isNotEmpty( agentCode ))
			TextBoxHelper.type("Detail_AgentCode_TextID", agentCode);
		if(ValidationHelper.isNotEmpty( title ))
			ComboBoxHelper.select("Detail_Title_comboID", title);
		if(ValidationHelper.isNotEmpty( foreName ))
			TextBoxHelper.type("Detail_Forename_txtID", foreName);
		if(ValidationHelper.isNotEmpty( surname ))
			TextBoxHelper.type("Detail_Surname_txtID", surname);
		if(ValidationHelper.isNotEmpty( company ))
			TextBoxHelper.type("Detail_Company_txtID", company);
	}

	/*
	 * This method is used to initialize instance variables
	 */

	protected void initializeVariables(Map<String, String> map) throws Exception {

		clientPartition = ExcelHolder.getKey(map, "Partition");
		companyName = ExcelHolder.getKey(map, "CompanyName");
		franchise = ExcelHolder.getKey(map, "Franchise");
		currency = ExcelHolder.getKey(map, "Currency");
		type = ExcelHolder.getKey(map, "Type");
		agentCode = ExcelHolder.getKey(map, "AgentCode");
		title = ExcelHolder.getKey(map, "Title");
		foreName = ExcelHolder.getKey(map, "ForeName");
		surname = ExcelHolder.getKey(map, "Surname");
		company = ExcelHolder.getKey(map, "Company");
		addressName = ExcelHolder.getKey(map, "AddressName");
		typeAddress = ExcelHolder.getKey(map, "TypeAddress");
		addressedTo = ExcelHolder.getKey(map, "AddressedTo");
		salutation = ExcelHolder.getKey(map, "Salutation");
		company = ExcelHolder.getKey(map, "Company");
		parentCompany = ExcelHolder.getKey(map, "ParentCompany");
		agentAddressName = ExcelHolder.getKey(map, "AddressName");
		agentTypeAddress = ExcelHolder.getKey(map, "TypeAddress");
		agentAddressedTo = ExcelHolder.getKey(map, "AddressedTo");
		agentSalutation = ExcelHolder.getKey(map, "Salutation");
		agentCompany = ExcelHolder.getKey(map, "CompanyAddress");
		agentStreet1 = ExcelHolder.getKey(map, "Street1");
		agentStreet2 = ExcelHolder.getKey(map, "Street2");
		agentStreet3 = ExcelHolder.getKey(map, "Street3");
		agentStreet4 = ExcelHolder.getKey(map, "Street4");
		agentTown = ExcelHolder.getKey(map, "Town");
		agentCounty = ExcelHolder.getKey(map, "County");
		agentPostCode = ExcelHolder.getKey(map, "PostCode");
		agentCountry = ExcelHolder.getKey(map, "CountryAddress");
		agentMainAgentAddress = ExcelHolder.getKey(map, "MainAgentAddress");
		contactNameInternet = ExcelHolder.getKey(map, "ContactNameInternet");
		typeInternet = ExcelHolder.getKey(map, "TypeDetail");
		addressInternet = ExcelHolder.getKey(map, "Address");
		defaultAddress = ExcelHolder.getKey(map, "Default Address");
		contactNameDetail = ExcelHolder.getKey(map, "ContactName");
		typeContact = ExcelHolder.getKey(map, "TypeContact");
		number = ExcelHolder.getKey(map, "Number");
		defaultNumber = ExcelHolder.getKey(map, "DefaultNumber");
		rootAgent = ExcelHolder.getKey(map, "RootAgent");
		parentAgent = ExcelHolder.getKey(map, "ParentAgent");

	}

}
