package com.subex.rocps.automation.helpers.application.partnerConfiguration.billprofile;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TabHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class SettlementsTab extends PSAcceptanceTest
{
	protected Map<String, String> bipSettlementsMap = null;
	protected String isAutomaticSettlement;
	protected String linkedProfile;
	protected String linkedProfileArr[];
	protected String billProfileType;
	protected String includeChckbxNm;
	protected String includeChckbxNmArr[];
	protected String autoInclExistBillFlg;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();

	/**Constructor
	 * @param bipSettlementsMap
	 */
	public SettlementsTab( Map<String, String> bipSettlementsMap )
	{

		this.bipSettlementsMap = bipSettlementsMap;
	}
	/*
	 * Method: initialising instance variables
	 */
	/*
	protected void initializeVariable( Map<String, String> map ) throws Exception
	{
		isAutomaticSettlement = ExcelHolder.getKey( map, "AutomaticSettlementFlg" );
		linkedProfile = ExcelHolder.getKey( map, "LinkedProfile" );
		includeChckbxNm = ExcelHolder.getKey( map, "IncludeChckBxName" );
		if ( ValidationHelper.isNotEmpty( linkedProfile ) )
			linkedProfileArr = psStringUtils.stringSplitFirstLevel( linkedProfile );
		if ( ValidationHelper.isNotEmpty( includeChckbxNm ) )
			includeChckbxNmArr = psStringUtils.stringSplitFirstLevel( includeChckbxNm );
	
	}
	*/

	/*
	 * Method: initialising instance variables
	 */
	protected void initializeVariable() throws Exception
	{
		isAutomaticSettlement = bipSettlementsMap.get( "AutomaticSettlementFlg" );
		linkedProfile = bipSettlementsMap.get( "LinkedProfile" );
		includeChckbxNm = bipSettlementsMap.get( "IncludeChckBxName" );

		if ( ValidationHelper.isNotEmpty( linkedProfile ) )
			linkedProfileArr = psStringUtils.stringSplitFirstLevel( linkedProfile );
		if ( ValidationHelper.isNotEmpty( includeChckbxNm ) )
			includeChckbxNmArr = psStringUtils.stringSplitFirstLevel( includeChckbxNm );

	}

	/*
	 * Method: click on settlments tab
	 */
	public void settlementsTabConfig() throws Exception
	{
		TabHelper.gotoTab( GenericHelper.getORProperty( "detail_bip_settlementTabXpath" ) );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "detail_bip_settlementTab_lableXpath" ), searchScreenWaitSec );
		linkedProfileConfig();
	}

	/*
	 * Method: linked profile config
	 */
	private void linkedProfileConfig() throws Exception
	{
		initializeVariable();

		if ( ValidationHelper.isTrue( isAutomaticSettlement ) )
		{
			CheckBoxHelper.check( "detail_bip_automaticSettlement_chckbxId" );
			addBillProfile( true );
		}
		else
			addBillProfile( false );

	}

	/*
	 * Method: add bill profile
	 */
	private void addBillProfile( boolean isAutomatic ) throws Exception
	{

		if ( ValidationHelper.isNotEmpty( linkedProfile ) )
		{
			for ( int i = 0; i < linkedProfileArr.length; i++ )
			{
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				MouseHelper.rightClick( "detail_bip_settlement_addMenu_xpath" );
				ElementHelper.waitForElement( GenericHelper.getORProperty( "detail_bip_settlement_bipAdd_xpath" ), searchScreenWaitSec );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				MouseHelper.click( "detail_bip_settlement_bipAdd_xpath" );
				String popupTitleXpath = GenericHelper.getORProperty( "ps_Detail_newScreen_windowtitleXpath" );
				popupTitleXpath = popupTitleXpath.replace( "screenName", "Add Profile" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				ElementHelper.waitForElement( popupTitleXpath, searchScreenWaitSec );
				EntityComboHelper.clickEntityIcon( "detail_bip_settlement_bip_entityId" );
				dataSelectionHelper.billProfileSelection( linkedProfileArr[i] );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				if ( isAutomatic )
					checkboxChecked( includeChckbxNmArr[i] );
				ButtonHelper.click( "OKButton" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				assertTrue( isLinkedBillprofilePresent( linkedProfileArr[i] ), " This bill profile didn't add in linked bill profile grid- " + linkedProfileArr[i] );

			}
		}

	}

	/*
	 * Method: to check the include checkbox for bills and carrier invoice in add bill profile 
	 */
	private void checkboxChecked( String includeChckBxnm ) throws Exception
	{

		if ( includeChckBxnm.contains( "Bill" ) && CheckBoxHelper.isNotChecked( "detail_bip_settlement_inclBill_ChckbxId" ) )
			CheckBoxHelper.check( "detail_bip_settlement_inclBill_ChckbxId" );
		if ( includeChckBxnm.contains( "Carrier Invoice" ) && CheckBoxHelper.isNotChecked( "detail_bip_settlement_isCi_ChckbxId" ) )
		{
			if ( CheckBoxHelper.isChecked( "detail_bip_settlement_inclBill_ChckbxId" ) )
				CheckBoxHelper.uncheck( "detail_bip_settlement_inclBill_ChckbxId" );
			CheckBoxHelper.check( "detail_bip_settlement_isCi_ChckbxId" );
		}
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	private boolean isLinkedBillprofilePresent( String billprofile ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		int row = GridHelper.getRowNumber( "settlementGrid", billprofile, "Bill Profile" );
		return row > 0;
	}

	public void confirmationAutoInclPopup() throws Exception
	{
		//autoInclExistBillFlg=ExcelHolder.getKey( bipSettlementsMap, "AutoIncludeExistingBills" );
		autoInclExistBillFlg = bipSettlementsMap.get( "AutoIncludeExistingBills" );
		String autoInclBillTest = "Do you wish to automatically include existing bills for this settlement period ?";
		if ( ValidationHelper.isNotEmpty( "autoInclExistBillFlg" ) && PopupHelper.isTextPresent( "window-scroll-panel", autoInclBillTest ) )
		{
			if ( ValidationHelper.isTrue( autoInclExistBillFlg ) )
				ButtonHelper.click( "YesButton" );
			else
				ButtonHelper.click( "NoButton" );
		}
	}
}
