package com.subex.rocps.sprintTestCase.bklg41;

import java.util.Map;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;

public class AccountDetailsTab extends PSAcceptanceTest {

	Map<String, String> accDetailTabMap;

	String accountType;
	String accountFranchise;
	String accountRef;
	String accountStatus;
	String parentAccount;
	String agent;
	String classification;
	String salesRegion;
	String paymentType;
	String analyst;
	String creditRating;
	String bankName;
	String bankAccountNumber;
	String bankAccountName;

	int paramVal;

	public AccountDetailsTab(Map<String, String> accMap) {

		accDetailTabMap = accMap;

		initInstVariables();

	}

	/*
	 * Method for initialising instance variables
	 */
	protected void initInstVariables() {

		accountType = accDetailTabMap.get("AccountType");
		accountFranchise = accDetailTabMap.get("Franchise");
		accountRef = accDetailTabMap.get("AccountRef");
		accountStatus = accDetailTabMap.get("AccountStatus");
		parentAccount = accDetailTabMap.get("ParentAccount");
		agent = accDetailTabMap.get("ManagingAgent");
		classification = accDetailTabMap.get("Classification");
		salesRegion = accDetailTabMap.get("SalesRegion");
		paymentType = accDetailTabMap.get("PaymentType");
		analyst = accDetailTabMap.get("Analyst");
		creditRating = accDetailTabMap.get("CreditRating");
		bankName = accDetailTabMap.get("BankName");
		bankAccountNumber = accDetailTabMap.get("BankAccountNumber");
		bankAccountName = accDetailTabMap.get("BankAccountName");

	}

	/*
	 * Configuring the account detail info
	 */
	protected void detailTabConfig() throws Exception {

		try {
			TabHelper.gotoTab("Details");
			accountDetailsConfig();
			accountCategory();
			BankDetails();

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * Configuring account details
	 */

	protected void accountDetailsConfig() throws Exception {
		try {

			ComboBoxHelper.select(or.getProperty("Detail_accountType"), accountType);
			ComboBoxHelper.select(or.getProperty("Detail_accountFranchise"), accountFranchise);
			if (!accountRef.isEmpty())
				TextBoxHelper.type(or.getProperty("Detail_accountRef"), accountRef);
			if (!accountStatus.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_accountStatus"), accountStatus);

			if (!parentAccount.isEmpty()) {
				ButtonHelper.click(or.getProperty("Detail_parentAccountEntityId"));
				GenericHelper.waitForLoadmask();
				SearchGridHelper.gridFilterSearchWithTextBox(or.getProperty("Detail_popUpWindowId"),or.getProperty("accountName_Detail"), parentAccount, "Account Name");
				GridHelper.clickRow(or.getProperty("Detail_popUpWindowId"), or.getProperty("SearchGrid"), parentAccount,
						"Account Name");
				ButtonHelper.click(or.getProperty("Detail_popUpWindowId"), or.getProperty("OK_Button_ByID"));
			}

			ButtonHelper.click(or.getProperty("Detail_popUp_ManagingAgent"));
			GenericHelper.waitForLoadmask();
			TextBoxHelper.type(or.getProperty("Detail_popUpWindowId"), or.getProperty("Detail_popUp_companyName"),
					agent);
			ButtonHelper.click(or.getProperty("Detail_popUpWindowId"), or.getProperty("SearchButton"));
			GridHelper.clickRow("Detail_popUpWindowId", "SearchGrid", 1,2);
			ButtonHelper.click(or.getProperty("Detail_popUpWindowId"), or.getProperty("OKButton"));
			

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * configuring account category details
	 */

	protected void accountCategory() throws Exception {
		try {
			if (!classification.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_classification"), classification);

			if (!salesRegion.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_account_salesRegion"), salesRegion);

			if (!paymentType.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_paymentType"), paymentType);

			if (!analyst.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_analyst"), analyst);

			if (!creditRating.isEmpty())
				ComboBoxHelper.select(or.getProperty("Detail_creditRating"), creditRating);

		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * configuring bank details
	 */

	protected void BankDetails() throws Exception {
		try {
			if (!bankName.isEmpty()) {

				String[] bankNamesArr = bankName.split(",", -1);
				for (String str : bankNamesArr) {
					ButtonHelper.click(or.getProperty("Detail_bankDetailsAdd"));
					GenericHelper.waitForLoadmask();
					ComboBoxHelper.select(or.getProperty("Detail_bankNameId"), bankName);
					TextBoxHelper.type(or.getProperty("Detail_bankAccNoPopUpId"), bankAccountNumber);
					TextBoxHelper.type(or.getProperty("Detail_bankAccName"), bankAccountName);
					ButtonHelper.click(or.getProperty("Detail_bankAccPopUpSaveId"));
				}
			}

			GenericHelper.waitForLoadmask();

		} catch (Exception e) {
			throw e;
		}
	}

}
