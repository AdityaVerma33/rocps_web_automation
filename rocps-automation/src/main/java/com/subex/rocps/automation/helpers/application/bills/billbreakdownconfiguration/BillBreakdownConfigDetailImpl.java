package com.subex.rocps.automation.helpers.application.bills.billbreakdownconfiguration;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.util.FailureHelper;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSDataComponentHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSEntityComboHelper;
import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.PSStringUtils;

public class BillBreakdownConfigDetailImpl extends PSAcceptanceTest
{

	protected PSGenericHelper genericHelperObj = new PSGenericHelper();
	protected PSDataComponentHelper compObj = new PSDataComponentHelper();

	/*
	 * This method is to create new Bill Breakdown Input
	 */
	public void billbreakdownBasicDetails( String name, String displayName, String breakdownPrefix, String breakdownsummaryPrefix, String useDataRange ) throws Exception
	{

		TextBoxHelper.type( "PS_Detail_billConfig_name_txtID", name );
		TextBoxHelper.type( "PS_Detail_billConfig_displayName_txtID", displayName );
		TextBoxHelper.type( "PS_Detail_billConfig_breakdownPrefix_txtID", breakdownPrefix );
		TextBoxHelper.type( "PS_Detail_billConfig_breakdownsummaryPrefix_txtID", breakdownsummaryPrefix );
		if ( ValidationHelper.isTrue( useDataRange ) )
			CheckBoxHelper.check( "PS_Detail_billConfig_usedataRange_chckbx" );
	}

	/*
	 * This method is to edit  Bill Breakdown basic details
	 */
	public void editBillbreakdownBasicDetails( String name, String displayName, String breakdownPrefix, String breakdownsummaryPrefix, String useDataRange ) throws Exception
	{

		assertEquals( TextBoxHelper.getValue( "PS_Detail_billConfig_name_txtID" ), name, "Bill breakdown config. name is not matched" );
		if ( ValidationHelper.isNotEmpty( displayName ) )
			TextBoxHelper.type( "PS_Detail_billConfig_displayName_txtID", displayName );
		if ( ValidationHelper.isNotEmpty( breakdownPrefix ) )
			TextBoxHelper.type( "PS_Detail_billConfig_breakdownPrefix_txtID", breakdownPrefix );
		if ( ValidationHelper.isNotEmpty( breakdownsummaryPrefix ) )
			TextBoxHelper.type( "PS_Detail_billConfig_breakdownsummaryPrefix_txtID", breakdownsummaryPrefix );
		if ( ValidationHelper.isTrue( useDataRange ) )
			CheckBoxHelper.check( "PS_Detail_billConfig_usedataRange_chckbx" );
	}

	public void tableInstanceSelection( String useJoins, String tableInstance, String tableName, String billusageQueryConfig ) throws Exception
	{
		if ( !ValidationHelper.isTrue( useJoins ) )
		{
			String entityXpath = "//*[@id='billBreakdownConfigDetail']//div[contains(@id,'trigger')]";
			if ( !ElementHelper.isElementPresent( entityXpath ) )
				ElementHelper.waitForElement( entityXpath, 120 );
			PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_billConfig_tableInstance_Entity", "Table Instance Search", "PS_Detail_billconfig_tableInstance_gridID", tableName, "Display Name" );
		}
		else
		{
			CheckBoxHelper.check( "Ps_Detail_billConfig_useJoins_chkbx" );
			ComboBoxHelper.select( "PS_Detail_billConfig_billusgaeQueryConfig_comboID", billusageQueryConfig );
		}
	}

	public void editTableInstanceSelection( String useJoins, String tableInstance, String tableName, String billusageQueryConfig ) throws Exception
	{
		if ( !ValidationHelper.isTrue( useJoins ) )
		{
			String entityXpath = "//*[@id='billBreakdownConfigDetail']//div[contains(@id,'trigger')]";
			if ( !ElementHelper.isElementPresent( entityXpath ) )
				ElementHelper.waitForElement( entityXpath, 120 );
			if ( !EntityComboHelper.getValue( "tableInst" ).contentEquals( tableName ) )
				PSEntityComboHelper.selectUsingGridFilterTextBox( "PS_Detail_billConfig_tableInstance_Entity", "Table Instance Search", "PS_Detail_billconfig_tableInstance_gridID", tableName, "Display Name" );
		}
		else
		{
			CheckBoxHelper.check( "Ps_Detail_billConfig_useJoins_chkbx" );
			ComboBoxHelper.select( "PS_Detail_billConfig_billusgaeQueryConfig_comboID", billusageQueryConfig );
		}
	}

	/*
	 * This method is for bill breakdown Keys Config
	 */

	public void billBreakdownKeysConfig( String billKeys, String lineItemKey, String visible, String filter, String disputed, String displayDP ) throws Exception
	{
		String splitregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billKeyArr = billKeys.split( splitregex, -1 );
		String[] lineItemKeyArr = lineItemKey.split( splitregex, -1 );
		String[] visibleArr = visible.split( splitregex, -1 );
		String[] FilterArr = filter.split( splitregex, -1 );
		String[] disputedArr = disputed.split( splitregex, -1 );
		String[] displayDPArr = displayDP.split( splitregex, -1 );

		for ( int j = 0; j < billKeyArr.length; j++ )
		{

			ButtonHelper.click( "PS_Detail_billConfig_billbreakdownKey_addBtn" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.clickRow( "PS_Detail_billConfig_Key_GridID", j + 1, "Key Computation Component" );
			clickbillbreakdownComboBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Keys", "PS_Detail_billConfig_Keys_combobx", billKeyArr[j] );
			GridHelper.clickRow( "PS_Detail_billConfig_Key_GridID", j + 1, "Key Computation Component" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Line Item Key", lineItemKeyArr[j] );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Visible", visibleArr[j] );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Filter", FilterArr[j] );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Disputed", disputedArr[j] );
			if ( !displayDP.isEmpty() )
			{
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Display Dp", "PS_Detail_billConfig_Keys_combobx", displayDPArr[j] );

			}
			deleteExtraRow( billKeyArr, "PS_Detail_billConfig_Key_GridID", "Keys" );
		}
		//deleteExtraRow(billKeyArr, "PS_Detail_billConfig_Key_GridID", "Keys");
	}
	/*
	 * This method is for edit bill breakdown Keys Config
	 */

	public void editBillBreakdownKeysConfig( String billKeys, String lineItemKey, String visible, String filter, String disputed, String displayDP ) throws Exception
	{
		String splitregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billKeyArr = billKeys.split( splitregex, -1 );
		String[] lineItemKeyArr = lineItemKey.split( splitregex, -1 );
		String[] visibleArr = visible.split( splitregex, -1 );
		String[] FilterArr = filter.split( splitregex, -1 );
		String[] disputedArr = disputed.split( splitregex, -1 );
		String[] displayDPArr = displayDP.split( splitregex, -1 );

		for ( int j = 0; j < billKeyArr.length; j++ )
		{

			if ( GridHelper.getRowNumber( "PS_Detail_billConfig_Key_GridID", billKeyArr[j], "Keys" ) == 0 )
			{
				ButtonHelper.click( "PS_Detail_billConfig_billbreakdownKey_addBtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				GridHelper.clickRow( "PS_Detail_billConfig_Key_GridID", j + 1, "Key Computation Component" );
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Keys", "PS_Detail_billConfig_Keys_combobx", billKeyArr[j] );
				GridHelper.clickRow( "PS_Detail_billConfig_Key_GridID", j + 1, "Key Computation Component" );
			}

			int row = GridHelper.getRowNumber( "PS_Detail_billConfig_Key_GridID", billKeyArr[j], "Keys" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", row, billKeyArr[j], "Line Item Key", lineItemKeyArr[j] );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", row, billKeyArr[j], "Visible", visibleArr[j] );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", row, billKeyArr[j], "Filter", FilterArr[j] );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Key_GridID", row, billKeyArr[j], "Disputed", disputedArr[j] );
			if ( !displayDP.isEmpty() )
			{
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Key_GridID", j + 1, "Display Dp", "PS_Detail_billConfig_Keys_combobx", displayDPArr[j] );

			}
			deleteExtraRow( billKeyArr, "PS_Detail_billConfig_Key_GridID", "Keys" );

		}
		//deleteExtraRow(billKeyArr, "PS_Detail_billConfig_Key_GridID", "Keys");
	}

	/*
	 * This method is for bill breakdown Value Config
	 */

	public void billBreakdownValuesConfig( String billValues, String functionValue, String amountValue, String visibleValue, String disputedValue, String displayDPValue ) throws Exception
	{
		String splitregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billValueArr = billValues.split( splitregex, -1 );
		String[] functionValueArr = functionValue.split( splitregex, -1 );
		String[] amountValueArr = amountValue.split( splitregex, -1 );
		String[] visibleValueArr = visibleValue.split( splitregex, -1 );
		String[] disputedValueArr = disputedValue.split( splitregex, -1 );
		String[] displayDPValueArr = displayDPValue.split( splitregex, -1 );
		for ( int j = 0; j < billValueArr.length; j++ )
		{

			ButtonHelper.click( "PS_Detail_billConfig_value_addbtn" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Values", "PS_Detail_billConfig_Value_ComboID", billValueArr[j] );
			clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Function", "PS_Detail_billConfig_Valuefunction_comboID", functionValueArr[j] );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.clickRow( "PS_Detail_billConfig_Value_GridID", j + 1, "Value Computation Component" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Amount", amountValueArr[j] );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Visible", visibleValueArr[j] );
			clickbillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Disputed", disputedValueArr[j] );
			if ( !displayDPValue.isEmpty() )
			{
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Display Dp", "PS_Detail_billConfig_Value_ComboID", displayDPValueArr[j] );
			}
		}
	}
	/*
	 * This method is for edit  bill breakdown Value Config
	 */

	public void editBillBreakdownValuesConfig( String billValues, String functionValue, String amountValue, String visibleValue, String disputedValue, String displayDPValue ) throws Exception
	{
		String splitregex = new PSStringUtils().regexFirstLevelDelimeter();
		String[] billValueArr = billValues.split( splitregex, -1 );
		String[] functionValueArr = functionValue.split( splitregex, -1 );
		String[] amountValueArr = amountValue.split( splitregex, -1 );
		String[] visibleValueArr = visibleValue.split( splitregex, -1 );
		String[] disputedValueArr = disputedValue.split( splitregex, -1 );
		String[] displayDPValueArr = displayDPValue.split( splitregex, -1 );
		for ( int j = 0; j < billValueArr.length; j++ )
		{

			if ( GridHelper.getRowNumber( "PS_Detail_billConfig_Value_GridID", billValueArr[j], "Values" ) == 0 )
			{
				ButtonHelper.click( "PS_Detail_billConfig_value_addbtn" );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Values", "PS_Detail_billConfig_Value_ComboID", billValueArr[j] );
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Function", "PS_Detail_billConfig_Valuefunction_comboID", functionValueArr[j] );
				GenericHelper.waitForLoadmask( searchScreenWaitSec );
			}
			GridHelper.clickRow( "PS_Detail_billConfig_Value_GridID", j + 1, "Value Computation Component" );
			int row = GridHelper.getRowNumber( "PS_Detail_billConfig_Value_GridID", billValueArr[j], "Values" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );

			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", row, billValueArr[j], "Amount", amountValueArr[j] );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", row, billValueArr[j], "Visible", visibleValueArr[j] );
			modifybillbreakdownCheckBox( "PS_Detail_billConfig_Value_GridID", row, billValueArr[j], "Disputed", disputedValueArr[j] );
			if ( !displayDPValue.isEmpty() )
			{
				clickbillbreakdownComboBox( "PS_Detail_billConfig_Value_GridID", j + 1, "Display Dp", "PS_Detail_billConfig_Value_ComboID", displayDPValueArr[j] );
			}
		}
	}

	/*
	 * This method is for bill breakdown Key/Value combo box
	 */
	private void clickbillbreakdownComboBox( String gridId, int row, String colHeader, String IdOrXpath, String colValue ) throws Exception
	{
		GridHelper.clickRow( gridId, row, colHeader );
		if ( !ComboBoxHelper.isPresent( IdOrXpath ) )
			GridHelper.clickRow( gridId, row, colHeader );
		ComboBoxHelper.select( IdOrXpath, colValue );
	}

	/*
	 * This method is for bill breakdown key/Value checkbox
	 */
	private void clickbillbreakdownCheckBox( String gridId, int row, String colHeader, String colValue ) throws Exception
	{
		if ( ValidationHelper.isTrue( colValue ) )
			GridHelper.clickRow( gridId, row, colHeader );
	}

	/*
	 * This method is for modified bill breakdown key/Value checkbox
	 */
	private void modifybillbreakdownCheckBox( String gridId, int row, String keyfieldNm, String columnHeaderOfCheckbox, String colValueOfChckbox ) throws Exception
	{
		int columnNoOfChckbox = GridHelper.getColumnNumber( gridId, columnHeaderOfCheckbox );
		if ( ValidationHelper.isTrue( colValueOfChckbox ) && getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellunchecked" ) )
		{
			assertTrue( getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellunchecked" ) );
			GridHelper.clickRow( gridId, row, columnNoOfChckbox );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertTrue( getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellchecked" ) );
		}
		if ( ValidationHelper.isFalse( colValueOfChckbox ) && getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellchecked" ) )
		{

			assertTrue( getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellchecked" ) );
			GridHelper.clickRow( gridId, row, columnNoOfChckbox );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			assertTrue( getCheckBoxText( gridId, columnNoOfChckbox, keyfieldNm ).contains( "cellunchecked" ) );
		}
	}

	// Method:Get the value checkbox with src attribute :
	private String getCheckBoxText( String gridId, int columnNoOfChckbox, String keyfieldNm ) throws Exception

	{
		////*[@id='gridId']//div[contains(text(),'Field name')]//parent::td//parent::tr//td["+"columnNo"+"]//div[@id='grid_check_editor']/img

		gridId = GenericHelper.getORProperty( gridId );
		String xpath = GenericHelper.getORProperty( "PS_Detail_billConfig_details_Chckbxpath" );
		xpath = xpath.replace( "gridId", gridId );
		xpath = xpath.replace( "Field name", keyfieldNm );
		xpath = xpath.replace( "columnNo", String.valueOf( columnNoOfChckbox ) );
		String elementTxt = ElementHelper.getAttribute( xpath, "src" );
		return elementTxt;

	}
	/*
	 * This method is to delete Extra Row in Key/Value Grid
	 */

	private void deleteExtraRow( String[] rowArr, String GridID, String colHeader ) throws Exception
	{
		int row = GridHelper.getRowCount( GridID );

		String val = GridHelper.getCellValue( GridID, row, colHeader );
		if ( val.isEmpty() )
		{
			//	if (row > rowArr.length) {
			GridHelper.clickRow( GridID, row, colHeader );
			ButtonHelper.click( "billBreakdownKeyGridToolbar.Delete" );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
		}
	}

	/*
	 * This method is for rating component
	 */
	public void ratingComponentconfig( String ratingComponent, String ratableFlag, String billedUsage, String rate, String tariff, String count, String setupAmt ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_billConfig_ratingcomponent_add_btnID" );
		ComboBoxHelper.select( "PS_Detail_billConfig_ratingcomponent_wrapperID", "PS_Detail_billConfig_ratingcomponent_comboID", ratingComponent );
		clickPropertyValueColumn( "Rateable Flag", ratableFlag );
		clickPropertyValueColumn( "Billed Usage", billedUsage );
		clickPropertyValueColumn( "Rate", rate );
		clickPropertyValueColumn( "Tariff", tariff );
		clickPropertyValueColumn( "Count", count );
		clickPropertyValueColumn( "SetupAmt", setupAmt );
		ButtonHelper.click( "PS_Detail_billConfig_ratingcomponent_Save_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	/*
	 * This method is for edit rating component
	 */
	public void editratingComponentconfig( String ratingComponent, String ratableFlag, String billedUsage, String rate, String tariff, String count, String setupAmt ) throws Exception
	{
		ButtonHelper.click( "PS_Detail_billConfig_ratingcomponent_add_btnID" );
		ComboBoxHelper.select( "PS_Detail_billConfig_ratingcomponent_wrapperID", "PS_Detail_billConfig_ratingcomponent_comboID", ratingComponent );
		editclickPropertyValueColumn( "RateableFlag", ratableFlag );
		editclickPropertyValueColumn( "BilledUsage", billedUsage );
		editclickPropertyValueColumn( "Rate", rate );
		editclickPropertyValueColumn( "Tariff", tariff );
		editclickPropertyValueColumn( "Count", count );
		editclickPropertyValueColumn( "SetupAmt", setupAmt );
		ButtonHelper.click( "PS_Detail_billConfig_ratingcomponent_Save_btnID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

	public void clickPropertyValueColumn( String propertyName, String propertyValue ) throws Exception
	{
		String locator = GenericHelper.getORProperty( "PS_propertyValColXpath" ).replace( "propertyName", propertyName );
		ElementHelper.click( locator );
		ElementHelper.scrollToView( locator, false );
		ElementHelper.click( locator );
		ComboBoxHelper.select( "PS_Detail_billConfig_ratingcomponent_propertyID", propertyValue );
	}

	/*
	 * This method is for edit property value 
	 */
	private void editclickPropertyValueColumn( String propertyName, String propertyValue ) throws Exception
	{
		String locator = GenericHelper.getORProperty( "PS_propertyValColXpath" ).replace( "propertyName", propertyName );
		ElementHelper.click( locator );
		ElementHelper.scrollToView( locator, false );
		ElementHelper.click( locator );
		if ( !ComboBoxHelper.getValue( "PS_Detail_billConfig_ratingcomponent_propertyID" ).contentEquals( propertyValue ) )
			ComboBoxHelper.select( "PS_Detail_billConfig_ratingcomponent_propertyID", propertyValue );
	}

	/*
	 * This method is to save bill breakdown Configuration
	 */
	public void billbreakdownConfigSave() throws Exception
	{
		ButtonHelper.click( "PS_Detail_billConfig_save_btnID" );
		GenericHelper.waitForSave( searchScreenWaitSec );
		GenericHelper.waitForLoadmask( 20000 );
		GenericHelper.waitForElementToDisappear( "PS_Detail_billConfig_save_btnID", detailScreenWaitSec );
		GenericHelper.waitForLoadmask( detailScreenWaitSec );

	}
}
