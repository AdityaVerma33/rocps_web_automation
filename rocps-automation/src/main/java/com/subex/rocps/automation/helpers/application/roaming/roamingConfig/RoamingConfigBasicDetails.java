package com.subex.rocps.automation.helpers.application.roaming.roamingConfig;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.DataSelectionHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreemConfigDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.PopupHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class RoamingConfigBasicDetails extends PSAcceptanceTest
{

	protected Map<String, String> roamingConfigBasicDetailsMap = null;
	protected String configType;
	protected String roamingDefn;
	protected String billProfile;
	protected String version;
	protected String currency;
	protected String tapDecimalPlaces;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	DataSelectionHelper dataSelectionHelper = new DataSelectionHelper();

	/**
	 * Default constructor
	 */
	public RoamingConfigBasicDetails()
	{
	}

	/**Constructor
	 * @param roamingConfigBasicDetailsMap
	 */
	public RoamingConfigBasicDetails( Map<String, String> roamingConfigBasicDetailsMap )
	{

		this.roamingConfigBasicDetailsMap = roamingConfigBasicDetailsMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeBasicVariable( Map<String, String> map ) throws Exception
	{
		configType = ExcelHolder.getKey( map, "ConfigurationType" );
		roamingDefn = ExcelHolder.getKey( map, "RoamingDefinition" );
		billProfile = ExcelHolder.getKey( map, "BillProfile" );
		version = ExcelHolder.getKey( map, "Version" );
		currency = ExcelHolder.getKey( map, "Currency" );
		tapDecimalPlaces = ExcelHolder.getKey( map, "TapDecimalPlaces" );

	}

	public void createRoamingConfig() throws Exception
	{
		initializeBasicVariable( roamingConfigBasicDetailsMap );
		configFirstPanelBasicDetail();
		configureBasicDetails( configType );
		configServiceManagement( configType );
		configCamelService( configType );
		psGenericHelper.detailSave( "PS_Detail_roamingConfig_BD_save_btnId", roamingDefn, "Tadig" );
	}

	private void configFirstPanelBasicDetail() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_roamingConfig_BD_conigType_comboId", configType );
		checkConfirmationPopup();
		roamingDfnEnititySearch( roamingDefn );
		billProfileEntitySearch( billProfile );
		ComboBoxHelper.select( "PS_Detail_roamingConfig_BD_version_comboId", version );

		if ( configType.contentEquals( "NRTRDE Out" ) && configType.contentEquals( "HUR Out" ) )
		{
			boolean isCurrencyComboBoxDisabled = ComboBoxHelper.isDisabled( "PS_Detail_roamingConfig_BD_currency_comboId" );
			assertTrue( isCurrencyComboBoxDisabled, "'Currecy' Combo box should be  disabled for -'NRTRDE Out' and 'HUR Out' configuratipn type " );
			boolean isTapDecTextBoxDisabled = ComboBoxHelper.isDisabled( "PS_Detail_roamingConfig_BD_tapDecPlace_comboId" );
			assertTrue( isTapDecTextBoxDisabled, "'Tap  Decimal  Places' Combo box should be  disabled for -'NRTRDE Out' and 'HUR Out' configuratipn type " );
		}
		else
		{
			ComboBoxHelper.select( "PS_Detail_roamingConfig_BD_currency_comboId", currency );
			ComboBoxHelper.select( "PS_Detail_roamingConfig_BD_tapDecPlace_comboId", tapDecimalPlaces );
		}
	}

	/*This method is for roaming definition entity search*/
	private void roamingDfnEnititySearch( String tadigCode ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		RoamingAgreemConfigDetail.roamingDfnEnititySearch( "PS_Detail_roamingConfig_BD_roamDefn_entityId", tadigCode, "PS_Detail_roamingConfig_basicDetailTab_xpath" );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_roamingConfig_basicDetailTab_xpath" ), false );
	}

	/*This method is for bill profile entity search*/
	private void billProfileEntitySearch( String billProfile ) throws Exception
	{
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_roamingConfig_basicDetailTab_xpath" ), false );
		psGenericHelper.waitforEntityElement();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		EntityComboHelper.clickEntityIcon( "PS_Detail_roamingConfig_BD_billProfile_entityId" );
		dataSelectionHelper.billProfileSelection( billProfile );
		ElementHelper.waitForElement( "PS_Detail_roamingConfig_basicDetailTab_xpath", searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is used to confirmationpoup for selecting configuration type
	 */
	private void checkConfirmationPopup() throws Exception
	{
		if ( PopupHelper.isTextPresent( "Changing the Config Type will clear all the existing configured. Are you sure you want to proceed?" ) )
			ButtonHelper.click( "YesButton" );
		ElementHelper.waitForElement( "PS_Detail_roamingConfig_basicDetailTab_xpath", searchScreenWaitSec );
	}
	/*This method is for configure basic Details */
	private void configureBasicDetails( String configType ) throws Exception
	{
		ConfigTypeBasicDetailsContext configTypeBasDetailsContOb;
		switch( configType )
		{
		case "Tap In":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new TapInConfigTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;
		case "Tap Out":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new TapOutConfigTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;

		case "Rap In":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new RapInConfigTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;
		case "Rap Out":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new RapOutConfigTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;

		case "HUR Out":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new HUROutConfigTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;

		case "NRTRDE Out":
			configTypeBasDetailsContOb = new ConfigTypeBasicDetailsContext( new NRTRDEOutConfTypeBasicDetails( roamingConfigBasicDetailsMap ) );
			configTypeBasDetailsContOb.configBasicDetails();
			break;
		default:
			Log4jHelper.logInfo( "No 'Configuration Type' is matched" );
		}

	}

	/*This method is for configure Service Management of TAP IN, TAP OUT*/
	private void configServiceManagement( String configType ) throws Exception
	{
		RoamingConfigServManagement roamingConfSerMan = new RoamingConfigServManagement( roamingConfigBasicDetailsMap );
		if ( configType.contentEquals( "Tap In" ) || configType.contentEquals( "Tap Out" ) )
			roamingConfSerMan.configServManagement( configType );
	}

	/*This method is for configure Camel Service  of TAP IN */
	private void configCamelService( String configType ) throws Exception
	{
		RoamingCamelService roamingCamelService = new RoamingCamelService( roamingConfigBasicDetailsMap );
		if ( configType.contentEquals( "Tap In" ) )
			roamingCamelService.configCamelService();

	}
	/*This method is for configure File Sequence details */
	public void configFileSeqDetails( String cdStartSeq, String tdStartSeq, String endNumber, String restartNumber ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( cdStartSeq ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_cdStartSeq_txtId", cdStartSeq );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_TAPIN_cdStartSeq_txtId" ), "1", " CD Start sequence is not matched" );
		if ( ValidationHelper.isNotEmpty( tdStartSeq ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_tdStartSeq_txtId", tdStartSeq );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_TAPIN_tdStartSeq_txtId" ), "1", " TD Start sequence is not matched" );
		if ( ValidationHelper.isNotEmpty( endNumber ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_endNum_txtId", endNumber );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_TAPIN_endNum_txtId" ), "9999", " End Number is not matched" );
		if ( ValidationHelper.isNotEmpty( restartNumber ) )
			TextBoxHelper.type( "PS_Detail_roamConfig_BD_TAPIN_restartNum_txtId", restartNumber );
		else
			assertEquals( TextBoxHelper.getValue( "PS_Detail_roamConfig_BD_TAPIN_restartNum_txtId" ), "1", " Restart Number is not matched" );
	}
	
}
