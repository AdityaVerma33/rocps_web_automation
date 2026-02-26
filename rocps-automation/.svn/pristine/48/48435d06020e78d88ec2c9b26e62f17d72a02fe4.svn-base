package com.subex.rocps.automation.helpers.application.products.productBundles.product;

import org.dbunit.assertion.FailureHandler;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;

import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.CheckBoxHelper;
import com.subex.automation.helpers.component.ComboBoxHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.MouseHelper;
import com.subex.automation.helpers.component.RadioHelper;
import com.subex.automation.helpers.component.TextAreaHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.componentHelpers.RadioElementHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class ProductUtil extends PSAcceptanceTest
{
	static PSGenericHelper psGenericHelper = new PSGenericHelper();

	/*Method get the key value from search screen of grid*/
	public static Map<String, String> getGridColumnValues( String gridId, String columnHeaderId, List<String> listColumn, int row ) throws Exception
	{
		Map<String, String> hmap = new HashMap<String, String>();
		String key;
		String value;
		List<String> uIColumns = psGenericHelper.getGridColumns( columnHeaderId );

		for ( int col = 0; col < listColumn.size(); col++ )
		{
			key = listColumn.get( col );
			int colNum = uIColumns.indexOf( key );

			String colCellValues = null;
			if ( colNum < 0 )
				FailureHelper.failTest( "This column-" + key + " is not found in the gridId:" + GenericHelper.getORProperty( gridId ) );
			else
				colCellValues = GridHelper.getCellValue( gridId, row, key );
			hmap.put( key, colCellValues );
		}
		return hmap;

	}

	/*Method to wait for parent action element to be clickable*/
	public static void waitForParentActionElementTOBeclickable( String parentActionName ) throws Exception
	{
		String parentActionxpath = "//*[@id='menuBarContainer']//div[text()='ParentActionName']";
		parentActionxpath = parentActionxpath.replace( "ParentActionName", parentActionName );
		ElementHelper.waitForClickableElement( parentActionxpath, searchScreenWaitSec );
	}

	/*Method to wait for child action element to be clickable*/
	public static void waitForChildActionElementTOBeclickable( String childActionName ) throws Exception
	{
		String childActionxpath = "//*[@id='subMenu']//div[text()='ChildActionName']";
		childActionxpath = childActionxpath.replace( "ChildActionName", childActionName );
		ElementHelper.waitForClickableElement( childActionxpath, searchScreenWaitSec );
	}

	//Amounts grid columns keys
	private static List<String> getKeysOfAmountGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "From" );
		listColumn.add( "Currency" );
		listColumn.add( "Amount" );
		listColumn.add( "Min  Amount" );
		listColumn.add( "Max  Amount" );
		return listColumn;

	}

	//BillingGroup grid columns keys
	private static List<String> getKeysOfBillingGrpGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Billing  Group  Code" );
		return listColumn;

	}

	//Sales Tax grid columns keys
	private static List<String> getKeysOfSalesTaxGrid()
	{
		List<String> listColumn = new ArrayList<String>();
		listColumn.add( "Country" );
		listColumn.add( "Sales Tax Group" );
		return listColumn;

	}

	/*Method to get the list of testcase map*/
	public static List<Map<String, String>> getTestCaseMap( String testcaseNm, String workbookname, String sheetName ) throws Exception
	{

		String path = System.getProperty( "user.dir" ) + "\\src\\main\\resources\\";
		ExcelReader excelData = new ExcelReader();
		Map<String, ArrayList<String>> productItemExcelMap = null;
		ExcelHolder excelHolderObj = null;
		List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		productItemExcelMap = excelData.readDataByColumn( path, workbookname, sheetName, testcaseNm );
		excelHolderObj = new ExcelHolder( productItemExcelMap );
		int colSize = excelHolderObj.totalColumns();
		for ( int index = 0; index < colSize; index++ )
		{
			map = excelHolderObj.dataMap( index );
			listmap.add( map );
		}
		return listmap;
	}

	/*Method to click on bundle tree options*/
	public static void clickOnBundleTreeOptions( String parentMenu, String subMenu ) throws Exception
	{
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		ElementHelper.scrollToView( expandXpath, false );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String menuXpath = "//div[starts-with(@id,'productBundleTree') and contains(text(),'ParentMenuName')]";
		menuXpath = menuXpath.replace( "ParentMenuName", parentMenu );
		ElementHelper.waitForClickableElement( menuXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.rightClick( menuXpath );
		String subMenuxpath = "//div[starts-with(@id,'productBundleTreeMenu') ]//div[contains(text(),'SubMenuName')]";
		subMenuxpath = subMenuxpath.replace( "SubMenuName", subMenu );
		ElementHelper.waitForClickableElement( subMenuxpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( subMenuxpath );
	}

	/* method to get the child text of tree*/
	public static String getchildTextOfTree( String parentMenu, String childText ) throws Exception
	{
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		ElementHelper.scrollToView( expandXpath, false );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String childTextxpath = "//div[starts-with(@id,'productBundleTree') and (text()=' ParentMenuName')]//ancestor::div//div[starts-with(@id,'productBundleTree')and (text()=' ChildTextName')]";
		childTextxpath = childTextxpath.replace( "ParentMenuName", parentMenu );
		childTextxpath = childTextxpath.replace( "ChildTextName", childText );
		ElementHelper.waitForClickableElement( childTextxpath, searchScreenWaitSec );
		String actualchildText = ElementHelper.getText( childTextxpath );
		return actualchildText;
	}

	/*method to get all child of tree*/
	public static ArrayList<String> getchildTextOfTree( String parentMenu ) throws Exception
	{

		ArrayList<String> listOfChildText = new ArrayList<String>();
		String childTextxpath = "//div[starts-with(@id,'productBundleTree') and (text()=' ParentMenuName')]/ancestor::div[@role='treeitem'][1]/div[2]/div/div/div[@role='treeitem']/div[1]";
		childTextxpath = childTextxpath.replace( "ParentMenuName", parentMenu );
		List<WebElement> listOfChildelement = ElementHelper.getElements( childTextxpath );
		for ( WebElement childElement : listOfChildelement )
			listOfChildText.add( ElementHelper.getText( childElement ) );
		return listOfChildText;
	}

	/*Click on child of parentTet with the position*/
	public static void clickOnChildOfParentInTree( String parentText, String position ) throws Exception
	{
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		ElementHelper.scrollToView( expandXpath, false );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String childXpath = "//div[starts-with(@id,'productBundleTree') and contains(text(),'ParentText')]/ancestor::div[@role='treeitem'][1]//div[@aria-posinset='Position']";
		childXpath = childXpath.replace( "ParentText", parentText );
		childXpath = childXpath.replace( "Position", position );
		ElementHelper.waitForClickableElement( childXpath, searchScreenWaitSec );
		MouseHelper.click( childXpath );
	}

	/*Select  child of parent with the child Text Value*/
	public static void selectChildOfParent(String treeName, String parentText, String childText ) throws Exception
	{
		treeName=GenericHelper.getORProperty( treeName );
		String expandXpath = "//div[@id='cell-list-panel']//div[text()='Expand All']";
		ElementHelper.scrollToView( expandXpath, false );
		ElementHelper.waitForClickableElement( expandXpath, searchScreenWaitSec );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		MouseHelper.click( expandXpath );
		String childXpath = "//div[starts-with(@id,'TreeName') and (text()=' ParentText')]/ancestor::div[@role='treeitem'][1]//div[text()=' ChildText']";
		childXpath = childXpath.replace( "TreeName", treeName );
		childXpath = childXpath.replace( "ParentText", parentText );
		childXpath = childXpath.replace( "ChildText", childText );
		ElementHelper.scrollToView( childXpath, false );
		ElementHelper.waitForClickableElement( childXpath, searchScreenWaitSec );
		MouseHelper.click( childXpath );

	}

	/*Method to select options on panel*/
	public static void selectOptionsOnPanel( String optionalFlg, String mandatoryFlg, String specifyFlg, String minNo, String maxNo ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( optionalFlg ) && ValidationHelper.isTrue( optionalFlg ) )
		{
			RadioHelper.click( "PS_Detail_bundleProduct_optional_radioBtnId" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMinNo_xpath" ), "0", "Min no. is not matched for optional" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMaxNo_xpath" ), "1", "Max no. is not matched for optional" );
		}
		else if ( ValidationHelper.isNotEmpty( mandatoryFlg ) && ValidationHelper.isTrue( mandatoryFlg ) )
		{
			RadioHelper.click( "PS_Detail_bundleProduct_mandatory_radioBtnId" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMinNo_xpath" ), "1", "Min no. is not matched for Mandatory" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMaxNo_xpath" ), "1", "Max no. is not matched for Mandatory" );
		}
		else if ( ValidationHelper.isNotEmpty( specifyFlg ) && ValidationHelper.isTrue( specifyFlg ) )
		{
			RadioHelper.click( "PS_Detail_bundleProduct_specify_radioBtnId" );
			TextBoxHelper.type( "PS_Detail_bundleProduct_optionsMinNo_xpath", minNo );
			TextBoxHelper.type( "PS_Detail_bundleProduct_optionsMaxNo_xpath", maxNo );
		}

	}

	/*Method to validate options on panel*/
	public static void validateOptionsOnPanel( String optionalFlg, String mandatoryFlg, String specifyFlg, String minNo, String maxNo ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( optionalFlg ) && ValidationHelper.isTrue( optionalFlg ) )
		{
			assertTrue( RadioHelper.isChecked( "PS_Detail_bundleProduct_optional_radioBtnId" ), "Options radio button is not checked as expected" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMinNo_xpath" ), "0", "Min no. is not matched for optional" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMaxNo_xpath" ), "1", "Max no. is not matched for optional" );
		}
		else if ( ValidationHelper.isNotEmpty( mandatoryFlg ) && ValidationHelper.isTrue( mandatoryFlg ) )
		{
			assertTrue( RadioHelper.isChecked( "PS_Detail_bundleProduct_mandatory_radioBtnId" ), "Mandatory radio button is not checked as expected" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMinNo_xpath" ), "1", "Min no. is not matched for Mandatory" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMaxNo_xpath" ), "1", "Max no. is not matched for Mandatory" );
		}
		else if ( ValidationHelper.isNotEmpty( specifyFlg ) && ValidationHelper.isTrue( specifyFlg ) )
		{
			assertTrue( RadioHelper.isChecked( "PS_Detail_bundleProduct_specify_radioBtnId" ), "Specify radio button is not checked as expected" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMinNo_xpath" ), minNo, "Min no is not matched for specify" );
			assertEquals( TextBoxHelper.getValue( "PS_Detail_bundleProduct_optionsMaxNo_xpath" ), maxNo, "Max no is not matched for specify" );
		}

	}

	/*Method to configure allow amount checkbox*/
	public static void configAllowAmountCheckBox( String allowOverrideAmntFlg ) throws Exception
	{
		if ( ValidationHelper.isTrue( allowOverrideAmntFlg ) )
			CheckBoxHelper.check( "PS_Detail_productItem_OneOff_allowOverRideAmnt" );
	}

	/*Method to validate allow amount checkbox*/
	public static void validateAllowAmountCheckBox( String allowOverrideAmntFlg ) throws Exception
	{
		if ( ValidationHelper.isTrue( allowOverrideAmntFlg ) )
			assertTrue( CheckBoxHelper.isChecked( "PS_Detail_productItem_OneOff_allowOverRideAmnt" ), " Allow override amount checkbox is not checked as expected" );
		else
			assertTrue( CheckBoxHelper.isNotChecked( "PS_Detail_productItem_OneOff_allowOverRideAmnt", "Allow override amount checkbox is not unchecked as expected " ) );
	}

	/*Method to configure  amount panel*/
	public static void configureAmountsPanel( String amount_currencyArr[], String amount_fromDtArr[], String amount_amt, String amount_amtArr[], String amount_MinAmnt, String amount_MinAmntArr[], String amount_MaxAmnt, String amount_MaxAmntArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_amountsLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_amountsLabel_xpath" ), false );

		for ( int i = 0; i < amount_currencyArr.length; i++ )
		{
			ButtonHelper.click( "PS_Detail_productItem_amtAdd_btnXpath" );
			GridHelper.updateGridTextBox( "PS_Detail_productItem_amountsGridXpath", "PS_Detail_productItem_amt_From_txtXpath", i + 1, "From", amount_fromDtArr[i] );
			GenericHelper.waitForLoadmask( searchScreenWaitSec );
			GridHelper.updateGridComboBox( "PS_Detail_productItem_amountsGridXpath", "PS_Detail_productItem_amt_Currency_ComboId", i + 1, "Currency", amount_currencyArr[i] );
			if ( ValidationHelper.isNotEmpty( amount_amt ) )
				GridHelper.updateGridTextBox( "PS_Detail_productItem_amountsGridXpath", "PS_Detail_productItem_amt_Amount_txtXpath", i + 1, "Amount", amount_amtArr[i] );
			if ( ValidationHelper.isNotEmpty( amount_MinAmnt ) )
				GridHelper.updateGridTextBox( "PS_Detail_productItem_amountsGridXpath", "PS_Detail_productItem_amt_MinAmount_txtXpath", i + 1, "Min Amount", amount_MinAmntArr[i] );
			if ( ValidationHelper.isNotEmpty( amount_MaxAmnt ) )
				GridHelper.updateGridTextBox( "PS_Detail_productItem_amountsGridXpath", "PS_Detail_productItem_amt_MaxAmount_txtXpath", i + 1, "Max Amount", amount_MaxAmntArr[i] );
		}
	}

	/*Method to validate  amount panel*/
	public static void validateAmountsPanel( String gridId, String amount_currencyArr[], String amount_fromDtArr[], String amount_amt, String amount_amtArr[], String amount_MinAmnt, String amount_MinAmntArr[], String amount_MaxAmnt, String amount_MaxAmntArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_amountsLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_amountsLabel_xpath" ), false );

		List<String> getKeysOfAmounsGrid = getKeysOfAmountGrid();
		for ( int i = 0; i < amount_currencyArr.length; i++ )
		{
			Map<String, String> mapOfAmountsGrid = getGridColumnValues( gridId, "grid_column_header_undefined_", getKeysOfAmounsGrid, ( i + 1 ) );
			isDataPresentInGrid( gridId, mapOfAmountsGrid, "From", amount_fromDtArr[i], i + 1 );
			isDataPresentInGrid( gridId, mapOfAmountsGrid, "Currency", amount_currencyArr[i], i + 1 );
			if ( ValidationHelper.isNotEmpty( amount_amt ) && ValidationHelper.isNotEmpty( amount_amtArr[i] ) )
				isDataPresentInGrid( gridId, mapOfAmountsGrid, "Amount", amount_amtArr[i] + ".0000", i + 1 );
			if ( ValidationHelper.isNotEmpty( amount_MinAmnt ) && ValidationHelper.isNotEmpty( amount_MinAmntArr[i] ) )
				isDataPresentInGrid( gridId, mapOfAmountsGrid, "Min  Amount", amount_MinAmntArr[i] + ".0000", i + 1 );
			if ( ValidationHelper.isNotEmpty( amount_MaxAmnt ) && ValidationHelper.isNotEmpty( amount_MaxAmntArr[i] ) )
				isDataPresentInGrid( gridId, mapOfAmountsGrid, "Max  Amount", amount_MaxAmntArr[i] + ".0000", i + 1 );

		}
	}

	/*Method to configure  Billing group panel*/
	public static void configureBillingGrpPanel( String billingGrpCode, String billingGrpCodeArr[], String billingGrpDefault, String billingGrpDefaultArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_BillGrpLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_BillGrpLabel_xpath" ), false );
		if ( ValidationHelper.isNotEmpty( billingGrpCode ) )
		{
			for ( int i = 0; i < billingGrpCodeArr.length; i++ )
			{
				ButtonHelper.click( "PS_Detail_productItem_BillGrp_Add_btnId" );
				GridHelper.updateGridComboBox( "PS_Detail_productItem_BillGrp_GridId", "PS_Detail_productItem_BillGrp_code_comboId", i + 1, "Billing Group Code", billingGrpCodeArr[i] );
				if ( ValidationHelper.isNotEmpty( billingGrpDefault ) && ValidationHelper.isTrue( billingGrpDefaultArr[i] ) )
					GridHelper.updateGridCheckBox( "PS_Detail_productItem_BillGrp_GridId", "PS_Detail_productItem_BillGrp_default_chckboxId", i + 1, "Default", billingGrpDefaultArr[i] );
			}
		}
	}

	/*Method to validate  Billing group panel*/
	public static void validateBillingGrpPanel( String billingGrpCode, String billingGrpCodeArr[], String billingGrpDefault, String billingGrpDefaultArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_BillGrpLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_BillGrpLabel_xpath" ), false );

		List<String> getKeysOfBillingGrpGrid = getKeysOfBillingGrpGrid();
		if ( ValidationHelper.isNotEmpty( billingGrpCode ) )
		{
			for ( int i = 0; i < billingGrpCodeArr.length; i++ )
			{
				Map<String, String> mapOfBillingGrpGrid = getGridColumnValues( "PS_Detail_productItem_BillGrp_GridId", "grid_column_header_undefined_", getKeysOfBillingGrpGrid, ( i + 1 ) );
				isDataPresentInGrid( "PS_Detail_productItem_BillGrp_GridId", mapOfBillingGrpGrid, "Billing  Group  Code", billingGrpCodeArr[i], i + 1 );
				if ( ValidationHelper.isNotEmpty( billingGrpDefault ) && ValidationHelper.isTrue( billingGrpDefaultArr[i] ) )
					CheckBoxHelper.isChecked( "PS_Detail_productItem_BillGrp_default_chckboxId" );
				else
					CheckBoxHelper.isNotChecked( "PS_Detail_productItem_BillGrp_default_chckboxId" );

			}
		}
	}

	/*Method to configure  sales tax  panel*/
	public static void configureSalesTaxPanel( String salesTax_CountryArr[], String salesTax_Grp, String salesTax_GrpArr[], String salesTax_Default, String salesTax_DefaultArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_SalesTaxLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_SalesTaxLabel_xpath" ), false );
		for ( int i = 0; i < salesTax_CountryArr.length; i++ )
		{
			ButtonHelper.click( "PS_Detail_productItem_SalesTax_Add_BtnId" );
			GridHelper.updateGridComboBox( "PS_Detail_productItem_SalesTax_GridId", "PS_Detail_productItem_SalesTax_Country_ComboId", i + 1, "Country", salesTax_CountryArr[i] );
			if ( ValidationHelper.isNotEmpty( salesTax_Grp ) )
				GridHelper.updateGridComboBox( "PS_Detail_productItem_SalesTax_GridId", "PS_Detail_productItem_SalesTax_Group_ComboId", i + 1, "Sales Tax Group", salesTax_GrpArr[i] );
			if ( ValidationHelper.isNotEmpty( salesTax_Default ) && ValidationHelper.isTrue( salesTax_DefaultArr[i] ) )
				GridHelper.updateGridCheckBox( "PS_Detail_productItem_SalesTax_GridId", "PS_Detail_productItem_SalesTax_Default_ChckBoxId", i + 1, "Default", salesTax_DefaultArr[i] );

		}

	}

	/*Method to validate  sales tax  panel*/
	public static void validateSalesTaxPanel( String salesTax_CountryArr[], String salesTax_Grp, String salesTax_GrpArr[], String salesTax_Default, String salesTax_DefaultArr[] ) throws Exception
	{
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_productItem_SalesTaxLabel_xpath" ), searchScreenWaitSec );
		ElementHelper.scrollToView( GenericHelper.getORProperty( "PS_Detail_productItem_SalesTaxLabel_xpath" ), false );
		List<String> getKeysOfSalesTaxGrid = getKeysOfSalesTaxGrid();
		for ( int i = 0; i < salesTax_CountryArr.length; i++ )
		{
			Map<String, String> mapOfSalesTaxGrid = getGridColumnValues( "PS_Detail_productItem_SalesTax_GridId", "grid_column_header_undefined_", getKeysOfSalesTaxGrid, ( i + 1 ) );

			isDataPresentInGrid( "PS_Detail_productItem_SalesTax_GridId", mapOfSalesTaxGrid, "Country", salesTax_CountryArr[i], i + 1 );
			if ( ValidationHelper.isNotEmpty( salesTax_Grp ) )
				isDataPresentInGrid( "PS_Detail_productItem_SalesTax_GridId", mapOfSalesTaxGrid, "Sales Tax Group", salesTax_GrpArr[i], i + 1 );
			if ( ValidationHelper.isNotEmpty( salesTax_Default ) && ValidationHelper.isTrue( salesTax_DefaultArr[i] ) )
				CheckBoxHelper.isChecked( "PS_Detail_productItem_SalesTax_Default_ChckBoxId" );
			else
				CheckBoxHelper.isNotChecked( "PS_Detail_productItem_SalesTax_Default_ChckBoxId" );

		}

	}

	/*Method to check is data present in grid*/
	private static void isDataPresentInGrid( String gridId, Map<String, String> mapOfGrid, String columnHeader, String columnValue, int row ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = mapOfGrid.entrySet().stream().filter( x -> x.getKey().equals( columnHeader ) ).anyMatch( x -> x.getValue().equals( columnValue ) );
		assertTrue( isDataPresentInGrid, "In " + gridId + "  " + columnHeader + " is not matched for row: " + row );
	}

	/*Method to check is child present on tree*/
	public static boolean isChildPresentOnTree( String parentText, String childText ) throws Exception
	{

		boolean isChildPresent = false;
		ArrayList<String> listOfChilds = getchildTextOfTree( parentText );
		isChildPresent = listOfChilds.stream().anyMatch( child -> child.equals( childText ) );
		return isChildPresent;

	}

	/*Method to modify combo box value*/
	public static void modifyComboBox( String comboId, String comboValue ) throws Exception
	{
		if(ComboBoxHelper.isEnabled( comboId ))
		{
		if ( ValidationHelper.isNotEmpty( comboValue ) && !ComboBoxHelper.getValue( comboId ).equals( comboValue ) )
			ComboBoxHelper.select( comboId, comboValue );
		}
		else
			Log4jHelper.logInfo( "The given combobox is disabled" );
	}

	/*Method to modify text box value*/
	public static void modifyTextBox( String textId, String textValue ) throws Exception
	{
		if(TextBoxHelper.isEnabled( textId ))
		{
		if ( ValidationHelper.isNotEmpty( textValue ) && !TextBoxHelper.getValue( textId ).equals( textValue ) )
			TextBoxHelper.type( textId, textValue );
		}
		else
			Log4jHelper.logInfo( "The given textbox is disabled" );
	}

	/*Method to modify textarea box value*/
	public static void modifyTextAreaBox( String textareaId, String textareaValue ) throws Exception
	{
		if ( ValidationHelper.isNotEmpty( textareaValue ) && !TextAreaHelper.getValue( textareaId ).equals( textareaValue ) )
			TextAreaHelper.type( textareaId, textareaValue );
	}
	
	/*Method to check is data present in grid*/
	public static boolean isDataPresentInGrid( String gridId, Map<String, ArrayList<String>> mapOfGrid, String columnHeader, String columnValue ) throws Exception
	{
		gridId = GenericHelper.getORProperty( gridId );
		boolean isDataPresentInGrid = mapOfGrid.entrySet().stream().filter( x -> x.getKey().equals( columnHeader ) ).anyMatch( x -> x.getValue().contains( columnValue ) );
		return isDataPresentInGrid;
	}

	/*Method get the key values from search screen of grid*/
	public static Map<String, ArrayList<String>> getGridColumnValues( String gridId, String columnHeaderId, List<String> listColumn ) throws Exception
	{
		Map<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
		String key;
		List<String> uIColumns = psGenericHelper.getGridColumns( columnHeaderId );

		for ( String col : listColumn )
		{
			ArrayList<String> colCellValues = new ArrayList<String>();
			key = col;
			int colNum = uIColumns.indexOf( key );
			if ( colNum < 0 )
				FailureHelper.failTest( "This column-" + key + " is not found in the gridId:" + GenericHelper.getORProperty( gridId ) );
			else
				colCellValues = getCellValues( gridId, key );
			hmap.put( key, colCellValues );
		}
		return hmap;

	}

	/*Method get the  values from search screen of grid*/
	public static ArrayList<String> getCellValues( String gridId, String key ) throws Exception
	{
		ArrayList<String> colCellValues = new ArrayList<String>();
		int noOfrow = GridHelper.getRowCount( gridId );
		for ( int i = 0; i < noOfrow; i++ )
		{
			String cellValue = GridHelper.getCellValue( gridId, i + 1, key );
			colCellValues.add( cellValue );
		}

		return colCellValues;
	}

}
