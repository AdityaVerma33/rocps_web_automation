package com.subex.rocps.automation.helpers.application.roaming.testSIMManagement;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.prepayments.prepayments.PrePaymentsDetailImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.application.roaming.roamingAgreemConfig.RoamingAgreemConfigDetail;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class TestSIMManagementDetails extends PSAcceptanceTest
{

	protected Map<String, String> testSIMManagementDetailsMap = null;
	protected String direction;
	protected String homeOp;
	protected String homeMCC;
	protected String homeMNC;
	protected String partnerMCC;
	protected String partnerMNC;
	protected String imsi;
	protected String msisdn;
	protected String validFrom;
	protected String validTo;

	protected String tadigCode;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param testSIMManagementDetailsMap
	 */
	public TestSIMManagementDetails( Map<String, String> testSIMManagementDetailsMap )
	{

		this.testSIMManagementDetailsMap = testSIMManagementDetailsMap;
	}

	/*This method is for initialize   variable*/
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		tadigCode = ExcelHolder.getKey( map, "Tadig" );
		direction = ExcelHolder.getKey( map, "Direction" );
		imsi = ExcelHolder.getKey( map, "IMSI" );
		msisdn = ExcelHolder.getKey( map, "MSISDN" );
		validFrom = ExcelHolder.getKey( map, "ValidFrom" );
		validTo = ExcelHolder.getKey( map, "ValidTo" );

	}

	/*This method is for initialize   variable*/
	private void initializeInboundDyVariable( Map<String, String> map ) throws Exception
	{
		partnerMCC = ExcelHolder.getKey( map, "Partner_MCC" );
		partnerMNC = ExcelHolder.getKey( map, "Partner_MNC" );
	}

	/*This method is for initialize   variable*/
	private void initializeOutboundDyVariable( Map<String, String> map ) throws Exception
	{
		homeOp = ExcelHolder.getKey( map, "Home_Operator" );
		homeMCC = ExcelHolder.getKey( map, "Home_MCC" );
		homeMNC = ExcelHolder.getKey( map, "Home_MNC" );
	}

	/*This method is for create Test SIM Management*/
	public void createTestSimManagement() throws Exception
	{
		initializeVariable( testSIMManagementDetailsMap );
		configureBasicDetails();
		configFinalPanel();
		psGenericHelper.detailSave( "PS_Detail_TestSIMManag_save_btnId", tadigCode, "Tadig" );
	}

	/*This method is for modify Test SIM Management*/
	public void modifyTestSimManagement() throws Exception
	{
		initializeVariable( testSIMManagementDetailsMap );
		modifyBasicDetails();
		modifyFinalPanel();
		psGenericHelper.detailSave( "PS_Detail_TestSIMManag_save_btnId", tadigCode, "Tadig" );
	}

	/*This method is for configure basic details*/
	private void configureBasicDetails() throws Exception
	{

		ComboBoxHelper.select( "PS_Detail_TestSIMManag_Direction_comboId", direction );
		roamingDfnEnititySearch( tadigCode );
		if ( direction.contentEquals( "Inbound" ) )
			validateInBoundVariables();
		if ( direction.contentEquals( "Outbound" ) )
			validateOutBoundVariables();

	}

	/*This method is for modify basic details*/
	private void modifyBasicDetails() throws Exception
	{

		assertEquals( ComboBoxHelper.getValue( "PS_Detail_TestSIMManag_Direction_comboId" ), direction, "Direction is not matched" );
		assertEquals( EntityComboHelper.getValue( "PS_Detail_TestSIMManag_RoamingDfn_entityId" ), tadigCode, "Roaming Definition is not matched" );
		if ( direction.contentEquals( "Inbound" ) )
			validateInBoundVariables();
		if ( direction.contentEquals( "Outbound" ) )
			validateOutBoundVariables();

	}

	/*This method is for roaming definition entity search*/
	private void roamingDfnEnititySearch( String tadigCode ) throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		RoamingAgreemConfigDetail.roamingDfnEnititySearch( "PS_Detail_TestSIMManag_RoamingDfn_entityId", tadigCode, "PS_Detail_TestSIMManag_detail_xpath" );

	}

	/*This method is for validate In bound field values*/
	private void validateInBoundVariables() throws Exception
	{
		initializeInboundDyVariable( testSIMManagementDetailsMap );
		vaildateTextBoxValue( "PS_Detail_TestSIMManag_PartnerMCC_txtId", partnerMCC, "Partner MCC" );
		vaildateTextBoxValue( "PS_Detail_TestSIMManag_PartnerMNC_txtId", partnerMNC, "Partner MNC" );
	}

	/*This method is for validate Out bound field values*/
	private void validateOutBoundVariables() throws Exception
	{
		initializeOutboundDyVariable( testSIMManagementDetailsMap );
		vaildateTextBoxValue( "PS_Detail_TestSIMManag_HomeOp_txtId", homeOp, "Home Operator" );
		vaildateTextBoxValue( "PS_Detail_TestSIMManag_HomeMCC_txtId", homeMCC, "Home MCC" );
		vaildateTextBoxValue( "PS_Detail_TestSIMManag_HomeMNC_txtId", homeMNC, "Home MNC" );
	}

	/*This method is for configure final panel details*/
	private void configFinalPanel() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_TestSIMManag_IMSI_txtId", imsi );
		TextBoxHelper.type( "PS_Detail_TestSIMManag_MSISDN_txtId", msisdn );
		TextBoxHelper.type( "PS_Detail_TestSIMManag_validFrom_txtId", validFrom );
		TextBoxHelper.type( "PS_Detail_TestSIMManag_validTo_txtId", validTo );
	}

	/*This method is for modify final panel details*/
	private void modifyFinalPanel() throws Exception
	{
		assertEquals( TextBoxHelper.getValue( "PS_Detail_TestSIMManag_IMSI_txtId" ), imsi, "'IMSI' is not matched" );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_TestSIMManag_MSISDN_txtId" ), msisdn, "'MSISDN' is not matched" );
		assertTrue( TextBoxHelper.getValue( "PS_Detail_TestSIMManag_validFrom_txtId" ).contains( validFrom), "'Valid From' is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_TestSIMManag_validTo_txtId", validTo );
	}

	/*This method is for validate the textbox value and check for editor is  disable*/
	private void vaildateTextBoxValue( String txtBxId, String txtValue, String txtBxHeaderNm ) throws Exception
	{
		boolean isDisabled = TextBoxHelper.isDisabled( txtBxId );
		assertTrue( isDisabled, " Text box should be disabled for " + txtBxHeaderNm );
		assertEquals( TextBoxHelper.getValue( txtBxId ), txtValue, "Text box value is not matched for this field:- " + txtBxHeaderNm );
		Log4jHelper.logInfo( "Text box value is  matched for this field:- '" + txtBxHeaderNm + "' found " + txtValue );
	}
}
