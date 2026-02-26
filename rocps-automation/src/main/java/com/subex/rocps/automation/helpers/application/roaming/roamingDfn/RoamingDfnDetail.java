package com.subex.rocps.automation.helpers.application.roaming.roamingDfn;

import org.openqa.selenium.WebElement;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class RoamingDfnDetail extends PSAcceptanceTest
{
	protected Map<String, String> roamingDefnDetailsMap = null;
	protected String typeOfAgreement;
	protected String tadig;
	protected String secondaryTadig;
	protected String status;
	protected String operator;
	protected String network;
	protected String mcc;
	protected String mnc;
	protected String considerSeconTadigFlg;
	protected String cdStartDate;
	protected String cdEndDate;
	protected String tdStartDate;
	protected String tdEndDate;
	protected String roamingDfnGrp;

	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param roamingDefnDetailsMap
	 */
	public RoamingDfnDetail( Map<String, String> roamingDefnDetailsMap )
	{

		this.roamingDefnDetailsMap = roamingDefnDetailsMap;
	}

	/*This method is for initialize  basic variable*/
	private void initializeBasicVariable( Map<String, String> map ) throws Exception
	{
		typeOfAgreement = ExcelHolder.getKey( map, "TypeOfAgreement" );
		tadig = ExcelHolder.getKey( map, "Tadig" );
		secondaryTadig = ExcelHolder.getKey( map, "SecondaryTadig" );
		status = ExcelHolder.getKey( map, "Status" );
		operator = ExcelHolder.getKey( map, "Operator" );
		network = ExcelHolder.getKey( map, "Network" );
		mcc = ExcelHolder.getKey( map, "MCC" );
		mnc = ExcelHolder.getKey( map, "MNC" );
		considerSeconTadigFlg = ExcelHolder.getKey( map, "ConsiderSecondaryTadigFlg" );
		roamingDfnGrp=ExcelHolder.getKey( map, "RoamingDefnGrp" );
	}

	/*This method is for initialize  date variable*/
	private void initializeDateVariables( Map<String, String> map ) throws Exception
	{
		cdStartDate = ExcelHolder.getKey( map, "CDStartDate" );
		cdEndDate = ExcelHolder.getKey( map, "CDEndDate" );
		tdStartDate = ExcelHolder.getKey( map, "TDStartDate" );
		tdEndDate = ExcelHolder.getKey( map, "TDEndDate" );
	}

	/*This method is for create roaming definition*/
	public void createRoamingDefn() throws Exception
	{
		configBasicDetails();
		psGenericHelper.detailSave( "PS_Detail_roamingDfn_save_btnId", tadig, "Tadig code" );
	}

	/*This method is for configuration of basic details*/
	private void configBasicDetails() throws Exception
	{
		initializeBasicVariable( roamingDefnDetailsMap );
		ComboBoxHelper.select( "PS_Detail_roamingDfn_typeOfAgg_comboId", typeOfAgreement );
		ComboBoxHelper.select( "PS_Detail_roamingDfn_tadig_comboId", tadig );
		if ( typeOfAgreement.contentEquals( "Hub" ) )
		{
			ComboBoxHelper.select( "PS_Detail_roamingDfn_secondaryTadig_comboId", secondaryTadig );
			if ( ValidationHelper.isNotEmpty( considerSeconTadigFlg ) && ValidationHelper.isTrue( considerSeconTadigFlg ) )
				CheckBoxHelper.check( "PS_Detail_roamingDfn_considerseconTadig_chckBxId" );
		}
		else
		{
			assertTrue( ComboBoxHelper.isDisabled( "PS_Detail_roamingDfn_secondaryTadig_comboId" ), "Secondary Tadig should be disabled for Home, Direct 'Type of agreement'" );
			assertTrue( CheckBoxHelper.isDisabled( "PS_Detail_roamingDfn_considerseconTadig_chckBxId" ), "'Consider Secondary Tadig for Tap In/ Rap Out' checkbox should be disabled for Home, Direct 'Type of agreement'" );

		}
		ComboBoxHelper.select( "PS_Detail_roamingDfn_status_comboId", status );
		TextBoxHelper.type( "PS_Detail_roamingDfn_operator_txtId", operator );
		TextBoxHelper.type( "PS_Detail_roamingDfn_network_txtId", network );
		TextBoxHelper.type( "PS_Detail_roamingDfn_MCC_txtId", mcc );
		TextBoxHelper.type( "PS_Detail_roamingDfn_MNC_txtId", mnc );
		ComboBoxHelper.select( "roamingDfnGroup_gwt_uid_", roamingDfnGrp );
		//configDateDetails( typeOfAgreement );
	}

	/*This method is for configuration of date details*/
	private void configDateDetails( String typeOfAgreement ) throws Exception
	{
		initializeDateVariables( roamingDefnDetailsMap );
		//verifyLabelTextOfDate();
		if ( typeOfAgreement.contentEquals( "Home" ) )
		{
			assertTrue( TextBoxHelper.isDisabled( "PS_Detail_roamingDfn_cdStartDate_txtId" ), "'Cd Start Date' should be disabled for Home Operator" );
			assertTrue( TextBoxHelper.isDisabled( "PS_Detail_roamingDfn_cdEndDate_txtId" ), "'Cd End Date' should be disabled for Home Operator" );
			assertTrue( TextBoxHelper.isDisabled( "PS_Detail_roamingDfn_tdStartDate_txtId" ), "'Td Start Date' should be disabled for Home Operator" );
			assertTrue( TextBoxHelper.isDisabled( "PS_Detail_roamingDfn_tdEndDate_txtId" ), "'Td End Date' should be disabled for Home Operator" );
		}
		else
		{
			TextBoxHelper.type( "PS_Detail_roamingDfn_cdStartDate_txtId", cdStartDate );
			TextBoxHelper.type( "PS_Detail_roamingDfn_cdEndDate_txtId", cdEndDate );
			TextBoxHelper.type( "PS_Detail_roamingDfn_tdStartDate_txtId", tdStartDate );
			TextBoxHelper.type( "PS_Detail_roamingDfn_tdEndDate_txtId", tdEndDate );
		}
	}

	/*This method is for verify label text of date*/
	private void verifyLabelTextOfDate() throws Exception
	{
		String cdlabelXpath = GenericHelper.getORProperty( "PS_Detail_roamingDfn_cd_labelXpath" );
		String labelCDText;
		String tdlabelXpath = GenericHelper.getORProperty( "PS_Detail_roamingDfn_td_labelXpath" );
		String labelTDText;
		if ( ElementHelper.isElementPresent( cdlabelXpath ) )
		{
			labelCDText = ElementHelper.getText( cdlabelXpath );
			assertEquals( labelCDText, "CD = Chargeable Data", "CD Label text is not matched" );
		}
		if ( ElementHelper.isElementPresent( tdlabelXpath ) )
		{
			labelTDText = ElementHelper.getText( tdlabelXpath );
			assertEquals( labelTDText, "TD = Test Data", "TD Label text is not matched" );
		}
	}

}
