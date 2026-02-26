package com.subex.rocps.automation.helpers.application.products.productInstance;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class OneOffItemInstImpl extends PSAcceptanceTest
{
	protected Map<String, String> oneOfftemInstImplMap = null;

	protected String instItemMemberName;
	protected String instItemMemberBillText;
	protected String instItemMemberBillingGrp;
	protected String instItemMemberSalesTaxGrp;
	protected String instItemMemberAgent;
	protected String instItemMemberExternalRef;
	protected String instItemOneOffChargeDt;
	protected String instItemOneOffReason;
	protected String instItemOneOffAmount;
	protected String instItemOneOffQuantity;
	protected String instItemMemberUnitOfMeasure;
	protected String instItemOneOffTotalAmount;
	protected String instItemOneOffCurrency;

	/**constructor
	 * @param oneOfftemInstImplMap
	 */
	public OneOffItemInstImpl( Map<String, String> oneOfftemInstImplMap )
	{

		this.oneOfftemInstImplMap = oneOfftemInstImplMap;
	}
	/*
	 * This method is for initialize variable for Basic Details
	 */

	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		instItemMemberName = ExcelHolder.getKey( map, "Name" );
		instItemMemberBillText = ExcelHolder.getKey( map, "BillText" );
		instItemMemberBillingGrp = ExcelHolder.getKey( map, "BillingGroup" );
		instItemMemberSalesTaxGrp = ExcelHolder.getKey( map, "SalesTaxGroup" );
		instItemMemberAgent = ExcelHolder.getKey( map, "Agent" );
		instItemMemberExternalRef = ExcelHolder.getKey( map, "ExternalReference" );
		instItemOneOffChargeDt = ExcelHolder.getKey( map, "ChargeDate" );
		instItemOneOffReason = ExcelHolder.getKey( map, "Reason" );
		instItemOneOffAmount = ExcelHolder.getKey( map, "Amount" );
		instItemOneOffQuantity = ExcelHolder.getKey( map, "Quantity" );
		instItemMemberUnitOfMeasure = ExcelHolder.getKey( map, "UnitOfMeasure" );
		instItemOneOffCurrency = ExcelHolder.getKey( map, "Currency" );
	}

	/*Method is to configure one off instance item*/
	public void configureOneOffInstItem() throws Exception
	{
		initializeVariableBasicDetails( oneOfftemInstImplMap );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberName_textID", instItemMemberName );
		if ( ValidationHelper.isNotEmpty( instItemMemberBillText ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberBillText_textID", instItemMemberBillText );
		if ( ValidationHelper.isNotEmpty( instItemMemberBillingGrp ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberBillingGrp_comboID", instItemMemberBillingGrp );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberSalesTaxGrp_comboID", instItemMemberSalesTaxGrp );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberAgent_comboID", instItemMemberAgent );
		if ( ValidationHelper.isNotEmpty( instItemMemberExternalRef ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberExterRef_textID", instItemMemberExternalRef );
		if ( ValidationHelper.isNotEmpty( instItemOneOffChargeDt ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOneOff_ChargeDt_txtId", instItemOneOffChargeDt );
		if ( ValidationHelper.isNotEmpty( instItemOneOffReason ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOneOff_reason_comboId", instItemOneOffReason );
		if ( ValidationHelper.isNotEmpty( instItemOneOffAmount ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOneOff_amount_txtId", instItemOneOffAmount );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOneOff_quant_txtId", instItemOneOffQuantity );
		if ( ValidationHelper.isNotEmpty( instItemMemberUnitOfMeasure ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberUnitOfM_comboID", instItemMemberUnitOfMeasure );
		validateTotalAmt();
		if ( ValidationHelper.isNotEmpty( instItemOneOffCurrency ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOneOff_currency_comboId", instItemOneOffCurrency );

	}

	/*Method is to Modify one off instance item*/
	public void modifyOneOffInstItem() throws Exception
	{
		initializeVariableBasicDetails( oneOfftemInstImplMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_prodInst_InstItemMemberName_textID" ), instItemMemberName, "One Off Item Instance is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemMemberBillText_textID", instItemMemberBillText );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberBillingGrp_comboID", instItemMemberBillingGrp );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberSalesTaxGrp_comboID", instItemMemberSalesTaxGrp );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberAgent_comboID", instItemMemberAgent );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemMemberExterRef_textID", instItemMemberExternalRef );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstOneOff_ChargeDt_txtId", instItemOneOffChargeDt );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstOneOff_reason_comboId", instItemOneOffReason );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstOneOff_amount_txtId", instItemOneOffAmount );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstOneOff_quant_txtId", instItemOneOffQuantity );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberUnitOfM_comboID", instItemMemberUnitOfMeasure );
		validateTotalAmt();
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstOneOff_currency_comboId", instItemOneOffCurrency );

	}

	/*Method is to validate total amount*/
	private void validateTotalAmt() throws Exception
	{
		MouseHelper.click( "PS_Detail_prodInst_InstOneOff_GridWrapperId" );
		String amnt = TextBoxHelper.getValue(  "PS_Detail_prodInst_InstOneOff_amount_txtId" );
		String quantity = TextBoxHelper.getValue(  "PS_Detail_prodInst_InstOneOff_quant_txtId" );
		Double expectedTotalAmnt = Double.valueOf( amnt ) * Double.valueOf( quantity );
		String actualTotalAmnt = TextBoxHelper.getValue(  "PS_Detail_prodInst_InstOneOff_totalAmnt_txtId" );
		assertEquals( Double.valueOf( actualTotalAmnt ), expectedTotalAmnt, "Actual and expected 'Total Amount' is not matched but found:\n Actual Total Amount: -" + actualTotalAmnt + "\n Expected Total Amount: " + expectedTotalAmnt );

	}

}
