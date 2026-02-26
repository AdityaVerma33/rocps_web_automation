package com.subex.rocps.automation.helpers.application.products.productInstance;

import java.util.Map;

import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.DateHelper;
import com.subex.automation.helpers.data.ValidationHelper;

public class ProductInstanceImpl extends PSAcceptanceTest
{
	protected Map<String, String> productInstanceImplMap = null;
	protected String instanceName;
	protected String instanceBillText;
	protected String instanceBillingGrp;
	protected String instaceCountry;
	protected String instanceContractNo;
	protected String instanceCurrency;
	protected String instanceFromDt;
	protected String instanceToDt;
	protected String instanceOrderDt;
	protected String instanceExternalRef;

	/**Constructor
	 * @param productInstanceImplMap
	 */
	public ProductInstanceImpl( Map<String, String> productInstanceImplMap )
	{

		this.productInstanceImplMap = productInstanceImplMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		instanceName = ExcelHolder.getKey( map, "Name" );
		instanceBillText = ExcelHolder.getKey( map, "BillText" );
		instanceBillingGrp = ExcelHolder.getKey( map, "BillingGroup" );
		instaceCountry = ExcelHolder.getKey( map, "Country" );
		instanceContractNo = ExcelHolder.getKey( map, "ContractNo" );
		instanceCurrency = ExcelHolder.getKey( map, "Currency" );
		instanceFromDt = ExcelHolder.getKey( map, "From" );
		instanceToDt = ExcelHolder.getKey( map, "To" );
		instanceOrderDt = ExcelHolder.getKey( map, "OrderDate" );
		instanceExternalRef = ExcelHolder.getKey( map, "ExternalReference" );
	}

	/*Method is to configure product Instance*/
	public void configureProductInstance() throws Exception
	{
		initializeVariableBasicDetails( productInstanceImplMap );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_Instancename_textID", instanceName );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstBillText_textID", instanceBillText );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstBillingGrp_comboID", instanceBillingGrp );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstCountry_comboID", instaceCountry );
		if ( ValidationHelper.isNotEmpty( instanceContractNo ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstContractNo_textID", instanceContractNo );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstCurrency_comboID", instanceCurrency );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_Instfrom_textID", instanceFromDt );
		if ( ValidationHelper.isNotEmpty( instanceToDt ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstTo_textID", instanceToDt );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstOrderDate_textID", instanceOrderDt );
		if ( ValidationHelper.isNotEmpty( instanceExternalRef ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstExternalRef_textID", instanceExternalRef );
	}

	/*Method is to modify product Instance*/
	public void modifyProductInstance() throws Exception
	{
		initializeVariableBasicDetails( productInstanceImplMap );
		assertEquals( TextBoxHelper.getValue("PS_Detail_prodInst_Instancename_textID"), instanceName, "Product Instance name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstBillText_textID", instanceBillText );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstBillingGrp_comboID", instanceBillingGrp );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstCountry_comboID", instaceCountry );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstContractNo_textID", instanceContractNo );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstCurrency_comboID", instanceCurrency );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_prodInst_Instfrom_textXpath", instanceFromDt );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_prodInst_InstTo_textXpath", instanceToDt );
		ProductUtil.modifyTextBox( "PS_Detail_PBundleDrillDown_prodInst_InstOrderDate_textXpath", instanceOrderDt );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstExternalRef_textID", instanceExternalRef );
	}

	private void compareOrderWithFromDt( String fromDt, String orderDt ) throws Exception
	{
		int diffInDate = DateHelper.dateDifference( "MM/dd/yyyy", fromDt, orderDt );
		boolean isOrderDtValid = ( diffInDate == 0 || diffInDate > 0 );
		assertTrue( isOrderDtValid, "Order date is invalid since it is after the start date of the product bundle instance" );
	}
}
