package com.subex.rocps.automation.helpers.application.products.productInstance;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.filters.GridFilterSearchHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;

public class ProductInstItemImpl extends PSAcceptanceTest
{
	protected Map<String, String> productInstItemImplMap = null;
	protected String instItemName;
	protected String productName;
	protected String instItemBillText;
	protected String instItemOrderDt;
	protected String instItemExtenalRef;
	PSGenericHelper psGenericHelper = new PSGenericHelper();

	/**Constructor
	 * @param productInstItemImplMap
	 */
	public ProductInstItemImpl( Map<String, String> productInstItemImplMap )
	{

		this.productInstItemImplMap = productInstItemImplMap;
	}

	/*
	 * This method is for initialize variable for Basic Details
	 */
	private void initializeVariableBasicDetails( Map<String, String> map ) throws Exception
	{
		instItemName = ExcelHolder.getKey( map, "Name" );
		instItemBillText = ExcelHolder.getKey( map, "BillText" );
		instItemOrderDt = ExcelHolder.getKey( map, "OrderDate" );
		instItemExtenalRef = ExcelHolder.getKey( map, "ExternalReference" );
		productName = ExcelHolder.getKey( map, "ProductName" );
	}

	/*Method is to configure product Instance Item*/
	public void configureProductInstItem() throws Exception
	{
		initializeVariableBasicDetails( productInstItemImplMap );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemName_textID", instItemName );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemBillText_textID", instItemBillText );
		if ( ValidationHelper.isNotEmpty( instItemOrderDt ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemOrderDate_textID", instItemOrderDt );
		if ( ValidationHelper.isNotEmpty( instItemExtenalRef ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemExterRef_textID", instItemExtenalRef );
	}

	/*Method is to modify product Instance Item*/
	public void modifyProductInstItem() throws Exception
	{
		initializeVariableBasicDetails( productInstItemImplMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_prodInst_InstItemName_textID" ), instItemName, "Product instance item name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemBillText_textID", instItemBillText );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemOrderDate_textID", instItemOrderDt );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemExterRef_textID", instItemExtenalRef );
	}

	/*Method is to create product Instance Item without mandatory*/
	public void createPrInstItemWithoutMandatory( String bundleNameWithVersionNm ) throws Exception
	{
		initializeVariableBasicDetails( productInstItemImplMap );
		boolean isProductInstItemPresent = isInstItemPresent();
		if ( !isProductInstItemPresent )
		{
			selecttionOfProductInstItem();
			ProductInstanceDetail.waitForInstancePage( "Product Item" );
			configureProductInstItem();
			ButtonHelper.click( "OKButton" );
			isProductInstItemPresent = isInstItemPresent();
			assertTrue( isProductInstItemPresent, "Product instance item name is not saved with name :" + instItemName );
			Log4jHelper.logInfo( "Product instance item name is successfully created 'Product Item' with name :" + instItemName );
		}
		else
			Log4jHelper.logInfo( "Product instance item name is already available  with name :" + instItemName );
	}

	/*Method is to check instance item present */
	private boolean isInstItemPresent() throws Exception
	{
		psGenericHelper.waitforHeaderElement( "Account" );
		ButtonHelper.click( "ClearButton" );
		boolean isInstItemPresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_prodInst_InstItemName_textID", instItemName, "Name" );
		return isInstItemPresent;

	}

	/*Method is to selection of product Instance Item*/
	private void selecttionOfProductInstItem() throws Exception
	{
		ProductInstanceActionImpl productInstanceActionImpl = new ProductInstanceActionImpl();
		productInstanceActionImpl.clickOnAction( "Common Tasks", "New" );
		ProductInstanceDetail.waitForPopupPage( "Product Definition Item" );
		selectProductFromComboBox();
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );

	}

	/*Method is to selection of product from combo box*/
	private void selectProductFromComboBox() throws Exception
	{
		WebElement arrowElement = ElementHelper.getElement( GenericHelper.getORProperty( "PS_Detail_prodInst_InstItem_ProductDfn_arrowXpath" ) );
		MouseHelper.click( arrowElement );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		String productDfnSelectXpath = GenericHelper.getORProperty( "PS_Detail_prodInst_InstItem_ProductDfn_selecterXpath" );
		productDfnSelectXpath = productDfnSelectXpath.replace( "Value", productName );
		WebElement productDfnSelectElement = ElementHelper.getElement( productDfnSelectXpath );
		Actions actions = new Actions( driver );
		actions.moveToElement( productDfnSelectElement );
		actions.click().build().perform();

	}
}
