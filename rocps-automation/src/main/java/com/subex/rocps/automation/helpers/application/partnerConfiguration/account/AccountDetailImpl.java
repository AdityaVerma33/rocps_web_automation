package com.subex.rocps.automation.helpers.application.partnerConfiguration.account;

import java.util.Map;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

public class AccountDetailImpl extends PSAcceptanceTest
{

	protected Map<String, String> accDetailTabMap;
	protected String accountName;
	protected String accountType;
	protected String accountFranchise;
	protected String accountRef;
	protected String accountStatus;
	protected String parentAccount;
	protected String agent;
	protected String classification;
	protected String salesRegion;
	protected String paymentType;
	protected String analyst;
	protected String creditRating;
	protected String bankName;
	protected String bankAccountNumber;
	protected String bankAccountName;
	protected String accountCompanyName;
	protected String accountAddressTypes;
	protected String accountAddressTo;
	protected String accountSalutation;
	protected String accountBuildingOrFloor;
	protected String accountStreet;
	protected String accountState;
	protected String accountTownOrCounty;
	protected String accountCountyName;
	protected String accountPostalCode;
	protected String accountCompanyRegNo;
	protected String accountRegistrationCity;
	protected String accountRegistrationType;
	protected String accountYearsTrading;
	protected String accountCountryName;
	protected String accountAddressName;
	protected String mainAccountAddressFlag;

	protected String contactName;
	protected String contactEmail;
	protected String officeNumbers;
	protected String mobileNumbers;
	protected String homeNumbers;
	protected String fax;
	protected String mainContactFlag;
	protected String customerType;
	protected String accountCategory;
	
	protected String customerNo;
	protected String billtocxaccNo;
	protected String billtocxsiteNo;
	protected String dealerCode;
	protected String siteCode;
	protected String billLob;
	
	

	protected String splitRegex = new PSStringUtils().regexFirstLevelDelimeter();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();

	int paramVal;

	public AccountDetailImpl( Map<String, String> accMap ) throws Exception
	{

		accDetailTabMap = accMap;
		initInstVariables( accMap );
	}

	/*
	 * Method for initialising instance variables
	 */
	protected void initInstVariables( Map<String, String> map ) throws Exception
	{

		accountType = ExcelHolder.getKey( map, "AccountType" );
		accountFranchise = ExcelHolder.getKey( map, "Franchise" );
		accountRef = ExcelHolder.getKey( map, "AccountRef" );
		accountStatus = ExcelHolder.getKey( map, "AccountStatus" );
		parentAccount = ExcelHolder.getKey( map, "ParentAccount" );
		agent = ExcelHolder.getKey( map, "ManagingAgent" );
		classification = ExcelHolder.getKey( map, "Classification" );
		salesRegion = ExcelHolder.getKey( map, "SalesRegion" );
		paymentType = ExcelHolder.getKey( map, "PaymentType" );
		analyst = ExcelHolder.getKey( map, "Analyst" );
		creditRating = ExcelHolder.getKey( map, "CreditRating" );
		bankName = ExcelHolder.getKey( map, "BankName" );
		bankAccountNumber = ExcelHolder.getKey( map, "BankAccountNumber" );
		bankAccountName = ExcelHolder.getKey( map, "BankAccountName" );

		accountCompanyName = ExcelHolder.getKey( map, "CompanyName" );
		accountAddressTypes = ExcelHolder.getKey( map, "AddressTypes" );
		accountAddressTo = ExcelHolder.getKey( map, "AddressTo" );
		accountSalutation = ExcelHolder.getKey( map, "Salutation" );
		accountBuildingOrFloor = ExcelHolder.getKey( map, "BuildingOrFloor" );
		accountStreet = ExcelHolder.getKey( map, "Street" );
		accountState = ExcelHolder.getKey( map, "State" );
		accountCountyName = ExcelHolder.getKey( map, "County" );
		accountTownOrCounty = ExcelHolder.getKey( map, "TownOrCountry" );
		accountPostalCode = ExcelHolder.getKey( map, "PostalCode" );
		accountCountryName = ExcelHolder.getKey( map, "Country" );
		accountCompanyRegNo = ExcelHolder.getKey( map, "CompanyRegNo" );
		accountAddressName = ExcelHolder.getKey( map, "AddressName" );
		accountRegistrationCity = ExcelHolder.getKey( map, "RegistrationCity" );
		accountRegistrationType = ExcelHolder.getKey( map, "RegistrationType" );
		accountYearsTrading = ExcelHolder.getKey( map, "YearsTrading" );
		mainAccountAddressFlag = ExcelHolder.getKey( map, "MainAccountAddressFlag" );

		contactName = ExcelHolder.getKey( map, "ContactName" );
		contactEmail = ExcelHolder.getKey( map, "ContactEmail" );
		officeNumbers = ExcelHolder.getKey( map, "ContactOfficeNumber" );
		mobileNumbers = ExcelHolder.getKey( map, "ContactMobileNumber" );
		homeNumbers = ExcelHolder.getKey( map, "ContactHomeNumber" );
		fax = ExcelHolder.getKey( map, "ContactFax" );
		mainContactFlag = ExcelHolder.getKey( map, "MainContactFlag" );
		customerType = ExcelHolder.getKey( map, "CustomerType" );
		accountName = ExcelHolder.getKey( map, "AccountName" );
		accountCategory = ExcelHolder.getKey( map, "AccountCategory" );
		
		customerNo = ExcelHolder.getKey( map, "CustomerNo" );
		billtocxaccNo = ExcelHolder.getKey( map, "BillToCustAccNo" );
		billtocxsiteNo = ExcelHolder.getKey( map, "BillToCustSiteNo" );
		dealerCode = ExcelHolder.getKey( map, "DealerCode" );
		siteCode = ExcelHolder.getKey( map, "SiteCode" );
		billLob = ExcelHolder.getKey( map, "BillLOB" );
		

	}

	/*
	 * Method to configure account detail
	 */
	public void openAccountDetail() throws Exception
	{
		ComboBoxHelper.select( "accountType_Detail", customerType );
		TextBoxHelper.type( "accountName_Detail", accountName );
	}

	/*
	 * Configuring the account detail info
	 */
	public void detailTabConfig() throws Exception
	{

		TabHelper.gotoTab( "Details" );
		accountDetailsConfig();
		accountCategory();
		bankDetails();
		//extraArgument();
		extraArgumentDigi();
	}

	/*
	 * Configuring account details
	 */

	public void accountDetailsConfig() throws Exception
	{
		AccountSearchImpl accSearchObj = new AccountSearchImpl();
		ComboBoxHelper.select( "Detail_accountType", accountType );
		ComboBoxHelper.select( "Detail_accountFranchise", accountFranchise );

		TextBoxHelper.type( "Detail_accountRef", accountRef );

		ComboBoxHelper.select( "Detail_accountStatus", accountStatus );

		if ( !parentAccount.isEmpty() )
		{
			accSearchObj.parentAccountEntitySearch( parentAccount );
		}
		accSearchObj.agentEntitySearch( agent );
	}

	/*
	 * configuring account category details
	 */

	public void accountCategory() throws Exception
	{

		ComboBoxHelper.select( "Detail_classification", classification );
		ComboBoxHelper.select( "Detail_account_salesRegion", salesRegion );
		ComboBoxHelper.select( "Detail_paymentType", paymentType );
		ComboBoxHelper.select( "Detail_analyst", analyst );
		ComboBoxHelper.select( "Detail_creditRating", creditRating );

	}

	/*
	 * configuring bank details
	 */

	/*	public void bankDetails() throws Exception {
			if (!bankName.isEmpty()) {
		
				String[] bankNamesArr = bankName.split("\\|", -1);
				for (String str : bankNamesArr) {
					ButtonHelper.click("Detail_bankDetailsAdd");
					GenericHelper.waitForLoadmask();
					// ComboBoxHelper.select("Detail_bankNameId", bankName);
					ComboBoxHelper.select("Detail_bankNamePopUpId", bankName);
					TextBoxHelper.type("Detail_bankAccNoPopUpId", bankAccountNumber);
					TextBoxHelper.type("Detail_bankAccName", bankAccountName);
					ButtonHelper.click("Detail_bankAccPopUpSaveId");
				}
			}
		
			GenericHelper.waitForLoadmask();
		}*/

	/*
	 * configuring bank details
	 */
	public void bankDetails() throws Exception
	{
		if ( !bankName.isEmpty() )
		{

			String[] bankNamesArr = bankName.split( "\\|", -1 );
			String[] bankAccountNumberArr = bankAccountNumber.split( "\\|", -1 );
			String[] bankAccountNamesArr = bankAccountName.split( "\\|", -1 );
			for ( int i = 0; i < bankNamesArr.length; i++ )
			{
				ButtonHelper.click( "Detail_bankDetailsAdd" );
				GenericHelper.waitForLoadmask();
				ComboBoxHelper.select( "Detail_bankNamePopUpId", bankNamesArr[i] );
				TextBoxHelper.type( "Detail_bankAccNoPopUpId", bankAccountNumberArr[i] );
				TextBoxHelper.type( "Detail_bankAccName", bankAccountNamesArr[i] );
				ButtonHelper.click( "Detail_bankAccPopUpSaveId" );
			}
		}

		GenericHelper.waitForLoadmask();
	}

	
		/*
	 * configuring account contact information
	 */
	public void navigateToContactTab() throws Exception
	{

		TabHelper.gotoTab( "Detail_contactInfoTabXpath" );
		// GenericHelper.waitTime(3, "Loading tab with contact information");
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}

	/*
	 * configuring address details
	 */
	public void addressDetails() throws Exception
	{

		try
		{

			String[] accDetailsArr = accountCompanyName.split( splitRegex, -1 );
			String[] accAddrArrTypeArr = accountAddressTypes.split( splitRegex, -1 );
			String[] accCtrNameArr = accountCountryName.split( splitRegex, -1 );
			String[] accountAddressTxtArr = accountAddressName.split( splitRegex, -1 );
			String[] accAddrMainFlArr = mainAccountAddressFlag.split( splitRegex, -1 );

			String[] accAddrToArr = accountAddressTo.split( splitRegex, -1 );
			String[] salutationArr = accountSalutation.split( splitRegex, -1 );
			String[] buildingOrFloorArr = accountBuildingOrFloor.split( splitRegex, -1 );
			String[] streetArr = accountStreet.split( splitRegex, -1 );
			String[] stateArr = accountState.split( splitRegex, -1 );
			String[] countyArr = accountCountyName.split( splitRegex, -1 );
			String[] townOrCountyArr = accountTownOrCounty.split( splitRegex, -1 );
			String[] postalCodeArr = accountPostalCode.split( splitRegex, -1 );
			String[] compRegNoArr = accountCompanyRegNo.split( splitRegex, -1 );
			String[] regCityArr = accountRegistrationCity.split( splitRegex, -1 );
			String[] regTypeArr = accountRegistrationType.split( splitRegex, -1 );
			String[] yearsTradingArr = accountYearsTrading.split( splitRegex, -1 );

			for ( int i = 0; i < accDetailsArr.length; i++ )
			{

				int row = GridHelper.getRowNumber( "accountAddressGrid", accDetailsArr[i], "Company Name" );
				if ( row == 0 )
				{
					ButtonHelper.click( "Detail_addressAddToolBarBtnId" );
					GenericHelper.waitForLoadmask();
					TextBoxHelper.type( "Detail_accountCmpNameTxtId", accDetailsArr[i] );
					ComboBoxHelper.select( "Detail_accountAddressTypeTxtId", accAddrArrTypeArr[i] );
					TextBoxHelper.type( "Detail_accountAddressTxtId", accountAddressTxtArr[i] );
					compObj.typeInTextBoxOptional( "Detail_stateTxtId", accountState, stateArr, i );
					compObj.typeInTextBoxOptional( "Detail_countyTxtId", accountCountyName, countyArr, i );
					compObj.typeInTextBoxOptional( "Detail_townOrCountyTxtId", accountTownOrCounty, townOrCountyArr, i );
					compObj.comboBoxSelectOptional( "Detail_accountCountryNameId", accountCountryName, accCtrNameArr, i );

					if ( ValidationHelper.isTrue( accAddrMainFlArr[i] ) )
						CheckBoxHelper.check( "Detail_mainAccAddrFlId" );

					compObj.typeInTextBoxOptional( "Detail_accountAddressToTxtId", accountAddressTo, accAddrToArr, i );
					compObj.typeInTextBoxOptional( "Detail_accountSalutationTxtId", accountSalutation, salutationArr, i );
					compObj.typeInTextBoxOptional( "Detail_buildingOrFloorTxtId", accountBuildingOrFloor, buildingOrFloorArr, i );
					compObj.typeInTextBoxOptional( "Detail_streetTxtId", accountStreet, streetArr, i );
					compObj.typeInTextBoxOptional( "Detail_postalCodeTxtId", accountPostalCode, postalCodeArr, i );
					compObj.typeInTextBoxOptional( "Detail_companyRegNoTxtId", accountCompanyRegNo, compRegNoArr, i );
					compObj.typeInTextBoxOptional( "Detail_registrationCityTxtId", accountRegistrationCity, regCityArr, i );
					compObj.typeInTextBoxOptional( "Detail_registrationTypeTxtId", accountRegistrationType, regTypeArr, i );
					compObj.typeInTextBoxOptional( "Detail_yearsTradingTxtId", accountYearsTrading, yearsTradingArr, i );

					ButtonHelper.click( "Detail_accountAddressSaveId" );
					GenericHelper.waitForLoadmask();
				}
				else
				{
					assertEquals( GridHelper.getCellValue( "accountAddressGrid", row, "Company Name" ), accDetailsArr[i] );
					assertEquals( GridHelper.getCellValue( "accountAddressGrid", row, "Address Name" ), accountAddressTxtArr[i] );
					assertEquals( GridHelper.getCellValue( "accountAddressGrid", row, "Address Type" ), accAddrArrTypeArr[i] );
				}
			}

		}
		catch ( Exception e )
		{

		}

	}

	/*
	 * configuring account contact details
	 */
	public void contactDetails() throws Exception
	{

		String[] contactNameArr = contactName.split( splitRegex, -1 );
		String[] contactEmailArr = contactEmail.split( splitRegex, -1 );
		String[] mainContactFlArr = mainContactFlag.split( splitRegex, -1 );
		String[] mobileNumArr = mobileNumbers.split( splitRegex, -1 );
		String[] officeNumArr = officeNumbers.split( splitRegex, -1 );
		String[] homeNumArr = homeNumbers.split( splitRegex, -1 );
		String[] faxArr = fax.split( splitRegex, -1 );

		for ( int i = 0; i < contactEmailArr.length; i++ )
		{
			int row = GridHelper.getRowNumber( "accountContactsGrid", contactNameArr[i], "Contact Name" );
			if ( row == 0 )
			{
				ButtonHelper.click( "Detail_contactAddBtnId" );
				GenericHelper.waitForLoadmask(detailScreenWaitSec);
				//assertEquals(NavigationHelper.getScreenTitle(), "Contact Information");
				TextBoxHelper.type( "Detail_contactNameTxtId", contactNameArr[i] );
				TextBoxHelper.type( "Detail_contactEmailTxtId", contactEmailArr[i] );

				compObj.typeInTextBoxOptional( "Detail_mobileNumTxtId", mobileNumbers, mobileNumArr, i );
				compObj.typeInTextBoxOptional( "Detail_officeNumTxtId", officeNumbers, officeNumArr, i );
				compObj.typeInTextBoxOptional( "Detail_homeNumTxtId", homeNumbers, homeNumArr, i );
				compObj.typeInTextBoxOptional( "Detail_faxTxtId", fax, faxArr, i );
				if ( ValidationHelper.isTrue( mainContactFlArr[i] ) )
					// if (mainContactFlArr[i].equalsIgnoreCase("true") ||
					// mainContactFlArr[i].equalsIgnoreCase("y"))
					CheckBoxHelper.check( "Detail_mainContactFl" );

				ButtonHelper.click( "Detail_accContactSaveBtnId" );
				GenericHelper.waitForLoadmask();
			}
			else
			{
				assertEquals( GridHelper.getCellValue( "accountContactsGrid", row, "Contact Name" ), contactNameArr[i] );
				assertEquals( GridHelper.getCellValue( "accountContactsGrid", row, "Email" ), contactEmailArr[i] );
			}
		}
	}

	public void terminateAccount( String terminateFrom, String terminateComment ) throws Exception
	{
		TextBoxHelper.type( "paccTerminatedFrom", terminateFrom );
		TextAreaHelper.type( "paccComment", terminateComment );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );
	}
	
	public void extraArgument() throws Exception
	{
	
		if (ValidationHelper.isNotEmpty( accountCategory ))
			ComboBoxHelper.select( "Account_Category_gwt_uid_", accountCategory );
		
		
	}
	
	
	
	/*
	 * this method is to insert Extra Argument details for DIGI account
	 */
	
	public void extraArgumentDigi() throws Exception
	{
	
		if (ValidationHelper.isNotEmpty( customerNo ))
			TextBoxHelper.type( "Digi_Customer_no", customerNo );
		
		if (ValidationHelper.isNotEmpty( billtocxaccNo ))
			TextBoxHelper.type( "Digi_billToCxName", billtocxaccNo );
		
		if (ValidationHelper.isNotEmpty( billtocxsiteNo ))
			TextBoxHelper.type( "Digi_billToCSitenumber", billtocxsiteNo );
		
		if (ValidationHelper.isNotEmpty( dealerCode ))
			TextBoxHelper.type( "Digi_dealerCode", dealerCode );
		
		if (ValidationHelper.isNotEmpty( siteCode ))
			TextBoxHelper.type( "Digi_siteCode", siteCode );
		
		if ( ValidationHelper.isNotEmpty( billLob ) )
			ComboBoxHelper.select( "digi_LOBdetail", billLob );
	}

	/*
	 * this method is to save account
	 */
	public void saveAccount( String accountName ) throws Exception
	{
		/*ButtonHelper.click("Detail_accountDetailSaveId");
		GenericHelper.waitForLoadmask();
		ElementHelper.isElementPresent("//div[@id='searchPanel']");
		assertTrue(GridHelper.isValuePresent("SearchGrid", accountName, "Account Name"));*/
		PSGenericHelper genericObj = new PSGenericHelper();
		genericObj.detailSave( "Detail_accountDetailSaveId", accountName, "Account Name" );
	}

	public void editAccountDetailsConfig() throws Exception
	{
		AccountSearchImpl accSearchObj = new AccountSearchImpl();
		if ( ValidationHelper.isNotEmpty( accountType ) )
			ComboBoxHelper.select( "Detail_accountType", accountType );
		if ( ValidationHelper.isNotEmpty( accountFranchise ) )
			ComboBoxHelper.select( "Detail_accountFranchise", accountFranchise );
		if ( ValidationHelper.isNotEmpty( accountRef ) )
			TextBoxHelper.type( "Detail_accountRef", accountRef );
		if ( ValidationHelper.isNotEmpty( accountStatus ) )
			ComboBoxHelper.select( "Detail_accountStatus", accountStatus );

		if ( !parentAccount.isEmpty() )
		{
			accSearchObj.parentAccountEntitySearch( parentAccount );
		}
		if ( ValidationHelper.isNotEmpty( agent ) )
			accSearchObj.agentEntitySearch( agent );
	}

	/*
	 * configuring account category details
	 */

	public void editAccountCategory() throws Exception
	{
		if ( ValidationHelper.isNotEmpty( classification ) )
			ComboBoxHelper.select( "Detail_classification", classification );
		if ( ValidationHelper.isNotEmpty( salesRegion ) )
			ComboBoxHelper.select( "Detail_account_salesRegion", salesRegion );
		if ( ValidationHelper.isNotEmpty( paymentType ) )
			ComboBoxHelper.select( "Detail_paymentType", paymentType );
		if ( ValidationHelper.isNotEmpty( analyst ) )
			ComboBoxHelper.select( "Detail_analyst", analyst );
		if ( ValidationHelper.isNotEmpty( creditRating ) )
			ComboBoxHelper.select( "Detail_creditRating", creditRating );

	}

	public void editBankDetails() throws Exception
	{
		if ( !bankName.isEmpty() )
		{

			String[] bankNamesArr = bankName.split( "\\|", -1 );
			String[] bankAccountNumberArr = bankAccountNumber.split( "\\|", -1 );
			String[] bankAccountNamesArr = bankAccountName.split( "\\|", -1 );
			for ( int i = 0; i < bankNamesArr.length; i++ )
			{
				int row = GridHelper.getRowNumber( "bankGridModel", bankNamesArr[i], "Bank" );
				if ( row == 0 )
				{
					ButtonHelper.click( "Detail_bankDetailsAdd" );
					GenericHelper.waitForLoadmask();
					ComboBoxHelper.select( "Detail_bankNamePopUpId", bankNamesArr[i] );
					TextBoxHelper.type( "Detail_bankAccNoPopUpId", bankAccountNumberArr[i] );
					TextBoxHelper.type( "Detail_bankAccName", bankAccountNamesArr[i] );
					ButtonHelper.click( "Detail_bankAccPopUpSaveId" );
				}
				else
				{
					assertEquals( GridHelper.getCellValue( "bankGridModel", row, "Bank" ), bankNamesArr[i] );
					assertEquals( GridHelper.getCellValue( "bankGridModel", row, "Bank  A/ C  No" ), bankAccountNumberArr[i] );
				}
			}
		}

		GenericHelper.waitForLoadmask();
	}

}
