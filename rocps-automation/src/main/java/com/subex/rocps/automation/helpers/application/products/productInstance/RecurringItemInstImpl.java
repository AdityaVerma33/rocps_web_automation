package com.subex.rocps.automation.helpers.application.products.productInstance;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class RecurringItemInstImpl extends PSAcceptanceTest
{
	protected Map<String, String> recurringtemInstImplMap = null;
	protected String instItemMemberName;
	protected String instItemMemberBillText;
	protected String instItemMemberBillingGrp;
	protected String instItemMemberSalesTaxGrp;
	protected String instItemMemberAgent;
	protected String instItemMemberExternalRef;
	protected String instItemRecurringFromDt;
	protected String instItemRecurringToDt;
	protected String instItemRecurringCycle;
	protected String instItemRecurringQuantity;
	protected String instItemMemberUnitOfMeasure;
	protected String instItemRecurringAmt_FromDt;
	protected String instItemRecurringAmt_FromDtArr[];
	protected String instItemRecurringAmt_amnt;
	protected String instItemRecurringAmt_amntArr[];
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor
	 * @param recurringtemInstImplMap
	 */
	public RecurringItemInstImpl( Map<String, String> recurringtemInstImplMap )
	{

		this.recurringtemInstImplMap = recurringtemInstImplMap;
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
		instItemRecurringFromDt = ExcelHolder.getKey( map, "From" );
		instItemRecurringToDt = ExcelHolder.getKey( map, "To" );
		instItemRecurringCycle = ExcelHolder.getKey( map, "CycleInAdvance" );
		instItemRecurringQuantity = ExcelHolder.getKey( map, "Quantity" );
		instItemMemberUnitOfMeasure = ExcelHolder.getKey( map, "UnitOfMeasure" );
	}

	/*Method is to configure recurring instnce item*/
	public void configureRecurringInstItem() throws Exception
	{
		initializeVariableBasicDetails( recurringtemInstImplMap );
		configureBasicDetails();
		configureRecAmntGridPanel();

	}

	/*Method is to configure basic details*/
	private void configureBasicDetails() throws Exception
	{
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberName_textID", instItemMemberName );
		if ( ValidationHelper.isNotEmpty( instItemMemberBillText ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberBillText_textID", instItemMemberBillText );
		if ( ValidationHelper.isNotEmpty( instItemMemberBillingGrp ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberBillingGrp_comboID", instItemMemberBillingGrp );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberSalesTaxGrp_comboID", instItemMemberSalesTaxGrp );
		ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberAgent_comboID", instItemMemberAgent );
		if ( ValidationHelper.isNotEmpty( instItemMemberExternalRef ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberExterRef_textID", instItemMemberExternalRef );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstRecurringfrom_textID", instItemRecurringFromDt );
		if ( ValidationHelper.isNotEmpty( instItemRecurringToDt ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstRecurringTo_textID", instItemRecurringToDt );
		if ( ValidationHelper.isNotEmpty( instItemRecurringCycle ) )
			TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstRecurringCycle_textID", instItemRecurringCycle );
		TextBoxHelper.type( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstRecurringQuant_textID", instItemRecurringQuantity );
		if ( ValidationHelper.isNotEmpty( instItemMemberUnitOfMeasure ) )
			ComboBoxHelper.select( "PS_Detail_prodInst_Instance_gridWrapperID", "PS_Detail_prodInst_InstItemMemberUnitOfM_comboID", instItemMemberUnitOfMeasure );

	}

	/*Method is to modify Recurring instance item details*/
	public void modifyRecurringInstItem() throws Exception
	{
		initializeVariableBasicDetails( recurringtemInstImplMap );
		assertEquals( TextBoxHelper.getValue( "PS_Detail_prodInst_InstItemMemberName_textID" ), instItemMemberName, "Recurring item name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemMemberBillText_textID", instItemMemberBillText );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberBillingGrp_comboID", instItemMemberBillingGrp );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberSalesTaxGrp_comboID", instItemMemberSalesTaxGrp );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberAgent_comboID", instItemMemberAgent );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstItemMemberExterRef_textID", instItemMemberExternalRef );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstRecurringfrom_textID", instItemRecurringFromDt );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstRecurringTo_textID", instItemRecurringToDt );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstRecurringCycle_textID", instItemRecurringCycle );
		ProductUtil.modifyTextBox( "PS_Detail_prodInst_InstRecurringQuant_textID", instItemRecurringQuantity );
		ProductUtil.modifyComboBox( "PS_Detail_prodInst_InstItemMemberUnitOfM_comboID", instItemMemberUnitOfMeasure );
		configureRecAmntGridPanel();
	}

	/*
	 * This method is for configure Recuriing  Amount field panel*/
	private void configureRecAmntGridPanel() throws Exception
	{
		try
		{
			instItemRecurringAmt_amnt = ExcelHolder.getKey( recurringtemInstImplMap, "Amount_amt" );
			instItemRecurringAmt_FromDt = ExcelHolder.getKey( recurringtemInstImplMap, "Amount_From" );
			instItemRecurringAmt_FromDtArr = psStringUtils.stringSplitFirstLevel( instItemRecurringAmt_FromDt );
			instItemRecurringAmt_amntArr = psStringUtils.stringSplitFirstLevel( instItemRecurringAmt_amnt );

			List<String> getKeysOfRecurringAmntGrid = getKeysOfRecAmntGrid();
			if ( ValidationHelper.isNotEmpty( instItemRecurringAmt_FromDt ) )
			{
				for ( int i = 0; i < instItemRecurringAmt_FromDtArr.length; i++ )
				{
					Map<String, ArrayList<String>> mapOfRecAmntGrid = ProductUtil.getGridColumnValues( "PS_Detail_prodInst_InstRecurringAmt_GridId", "grid_column_header_undefined_", getKeysOfRecurringAmntGrid );
					boolean isRecuAmntPresentInGrid = ProductUtil.isDataPresentInGrid( "PS_Detail_prodInst_InstRecurringAmt_GridId", mapOfRecAmntGrid, "From", instItemRecurringAmt_FromDtArr[i] );
					if ( !isRecuAmntPresentInGrid )
					{
						configRecurringAmnt( ( i + 1 ), instItemRecurringAmt_FromDtArr[i], instItemRecurringAmt_amntArr[i] );
						validateTotalAmtInGrid( i + 1 );
					}
					else
					{
						validateTotalAmtInGrid( i + 1 );
						Log4jHelper.logInfo( "This paramvalue: " + instItemRecurringAmt_FromDtArr[i] + " is already present in this grid:" + GenericHelper.getORProperty( "PS_Detail_prodInst_InstRecurringAmt_GridId" ) );

					}
				}
				checkForErroMszInRecAmtGrid();
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}
	}

	/*Method is to configure recurring amount*/
	private void configRecurringAmnt( int row, String fromDt, String amnt ) throws Exception
	{
		ButtonHelper.click(  "PS_Detail_prodInst_InstRecurringAdd_btnID" );
		GridHelper.updateGridTextBox( "PS_Detail_prodInst_InstRecurringAmt_GridId", "PS_Detail_prodInst_InstRecurringAmt_From_txtId", row, "From", fromDt );
		GridHelper.updateGridTextBox( "PS_Detail_prodInst_InstRecurringAmt_GridId", "PS_Detail_prodInst_InstRecurringAmt_amount_txtId", row, "Amount", amnt );

	}

	/*Method is to validate total amount in grid*/
	private void validateTotalAmtInGrid( int row ) throws Exception
	{
		GridHelper.clickRow( "PS_Detail_prodInst_InstRecurringAmt_GridId", row, "Currency" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		GridHelper.clickRow( "PS_Detail_prodInst_InstRecurringAmt_GridId", row, "Currency" );
		String actualTotalAmnt = GridHelper.getCellValue( "PS_Detail_prodInst_InstRecurringAmt_GridId", row, "Total  Amount" );
		String amnt = GridHelper.getCellValue( "PS_Detail_prodInst_InstRecurringAmt_GridId", row, "Amount" );
		Double expectedTotalAmnt = Double.valueOf( instItemRecurringQuantity ) * Double.valueOf( amnt );
		assertEquals( Double.valueOf( actualTotalAmnt ), expectedTotalAmnt, "Actual and expected 'Total Amount' is not matched but found:\n Actual Total Amount: -" + actualTotalAmnt + "\n Expected Total Amount: " + expectedTotalAmnt );

	}

	/*Method is to check for error message in recurring amount grid*/
	private void checkForErroMszInRecAmtGrid() throws Exception
	{
		String errorMszXpath = GenericHelper.getORProperty( "PS_Detail_prodInst_InstRecurringAmt_errorMszXpath" );
		WebElement errorMszElemenet = ElementHelper.getElement( "PS_Detail_prodInst_Instance_gridWrapperID", errorMszXpath );
		if ( ElementHelper.isElementPresent( GenericHelper.getORProperty( "PS_Detail_prodInst_Instance_gridWrapperID" ), errorMszXpath ) )
		{
			ElementHelper.scrollToView( errorMszXpath, false );
			MouseHelper.mouseOver( errorMszElemenet );
			FailureHelper.failTest( "Error message:- " + "One or more mandatory fields has to be filled and cannot be empty or invalid." );
		}
	}

	//Amounts grid columns keys
	private List<String> getKeysOfRecAmntGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "From" );
		return listColumn;
	}

}
