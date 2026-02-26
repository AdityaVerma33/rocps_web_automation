package com.subex.rocps.automation.helpers.application.bills;

import java.util.ArrayList;
import java.util.Map;

import com.subex.rocps.automation.helpers.application.genericHelpers.PSGenericHelper;
import com.subex.rocps.automation.helpers.application.products.productBundles.ProductBundleDetailImpl;
import com.subex.rocps.automation.helpers.application.products.productBundles.product.ProductUtil;
import com.subex.rocps.automation.helpers.selenium.PSAcceptanceTest;
import com.subex.rocps.automation.utils.ExcelHolder;
import com.subex.rocps.automation.utils.PSStringUtils;

import com.subex.automation.helpers.application.NavigationHelper;
import com.subex.automation.helpers.component.ButtonHelper;
import com.subex.automation.helpers.component.ElementHelper;
import com.subex.automation.helpers.component.EntityComboHelper;
import com.subex.automation.helpers.component.GenericHelper;
import com.subex.automation.helpers.component.GridHelper;
import com.subex.automation.helpers.component.SearchGridHelper;
import com.subex.automation.helpers.component.TextBoxHelper;
import com.subex.automation.helpers.data.ValidationHelper;
import com.subex.automation.helpers.file.ExcelReader;
import com.subex.automation.helpers.report.Log4jHelper;
import com.subex.automation.helpers.util.FailureHelper;

public class BillingGroupCode extends PSAcceptanceTest
{
	protected ExcelReader excelData = new ExcelReader();
	protected Map<String, ArrayList<String>> billingGrpCdExcelMap = null;
	protected Map<String, String> billingGrpCdMap = null;
	protected ExcelHolder excelHolderObj = null;
	protected String path;
	protected String workBookName;
	protected String sheetName;
	protected String testCaseName;
	protected int occurence;
	protected String clientPartition;
	protected String colmHdrs;
	protected String billingGrpName;
	protected String billingGrpCd;
	protected String account;
	protected String externalReference;
	protected int colSize;
	protected int index;
	PSGenericHelper psGenericHelper = new PSGenericHelper();
	PSStringUtils psStringUtils = new PSStringUtils();

	/**Constructor:  Initializing the excel without occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @throws Exception 
	 */
	public BillingGroupCode( String path, String workBookName, String sheetName, String testCaseName ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		billingGrpCdExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName );
		excelHolderObj = new ExcelHolder( billingGrpCdExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/**Constructor: Initializing the excel with occurrence
	 * @param path
	 * @param workBookName
	 * @param sheetName
	 * @param testCaseName
	 * @param occurence
	 * @throws Exception 
	 */
	public BillingGroupCode( String path, String workBookName, String sheetName, String testCaseName, int occurence ) throws Exception
	{

		this.path = path;
		this.workBookName = workBookName;
		this.sheetName = sheetName;
		this.testCaseName = testCaseName;
		this.occurence = occurence;
		billingGrpCdExcelMap = excelData.readDataByColumn( path, workBookName, sheetName, testCaseName, occurence );
		excelHolderObj = new ExcelHolder( billingGrpCdExcelMap );
		colSize = excelHolderObj.totalColumns();
	}

	/*
	 * This method is for initialize variable
	 */
	private void initializeVariable( Map<String, String> map ) throws Exception
	{
		billingGrpName = ExcelHolder.getKey( map, "Name" );
		clientPartition = ExcelHolder.getKey( map, "Partition" );

	}
	/*
	 * This method is for initialize variable
	 */
	private void initializeConfigVariable( Map<String, String> map ) throws Exception
	{
		billingGrpCd = ExcelHolder.getKey( map, "Code" );
		account = ExcelHolder.getKey( map, "Account" );
		externalReference = ExcelHolder.getKey( map, "ExternalReference" );

	}

	/*
	 * This method is for Billing Group Code screen common method
	 */
	private void billingGrpScreen() throws Exception
	{
		NavigationHelper.navigateToScreen( "Billing Group Code" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		billingGrpCdMap = excelHolderObj.dataMap( index );
		ButtonHelper.click( "ClearButton" );
		psGenericHelper.waitforHeaderElement( "Name" );
	}

	/*
	 * This method is for Billing Group Code screen column validation
	 */
	public void billingGrpCdColumsValidation() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billingGrpScreen();
				colmHdrs = ExcelHolder.getKey( billingGrpCdMap, "SearchScreenColumns" );
				ArrayList<String> excelColumnNames = new ArrayList<String>();
				String billingGrpCdGridColumnsArr[] = psStringUtils.stringSplitFirstLevel( colmHdrs );
				for ( String column : billingGrpCdGridColumnsArr )
					excelColumnNames.add( column );
				psGenericHelper.totalColumns( excelColumnNames );
				Log4jHelper.logInfo( "'Billing Group Code' Columns are validated successfully" );
			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}

	/*
	 * This method is for Billing Group Code creation
	 */
	public void createBillingGroupCode() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billingGrpScreen();
				initializeVariable( billingGrpCdMap );
				boolean isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
				if ( !isBillingGrpCodePresent )
				{
					clickNewAction( clientPartition );
					configureBillingGroupCode();
					psGenericHelper.detailSave( "PS_Detail_billingGrpCode_save_btnID", billingGrpName, "Name" );

					Log4jHelper.logInfo( "'Billing Group Code' is successfully created with name " + billingGrpName );
				}
				else
				{
					Log4jHelper.logInfo( "'Billing Group Code is already avilable with name '" + billingGrpName );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}
	
	/*
	 * This method is for Edit Billing Group Code creation
	 */
	public void editBillingGroupCode() throws Exception
	{
		try
		{
			for ( index = 0; index < colSize; index++ )
			{
				billingGrpScreen();
				initializeVariable( billingGrpCdMap );
				boolean isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
				if ( isBillingGrpCodePresent )
				{
					int row=GridHelper.getRowNumber( "SearchGrid", billingGrpName, "Name" );
					GenericHelper.waitForLoadmask( searchScreenWaitSec );
 					GridHelper.clickRow( "SearchGrid", row, "Name" );
					clickOnBillingGrpCodeAction( "Common Tasks", "Edit" );
					modifyBillingGroupCode();
					psGenericHelper.detailSave( "PS_Detail_billingGrpCode_save_btnID", billingGrpName, "Name" );

					Log4jHelper.logInfo( "'Billing Group Code' is successfully upated with name " + billingGrpName );
				}
				else
				{
					Log4jHelper.logInfo( "'Billing Group Code is not avilable with name '" + billingGrpName );
				}

			}
		}
		catch ( Exception e )
		{
			FailureHelper.setErrorMessage( e );
		}

	}

	// Method: for Billing Group Code deletion action
	public void billingGrpCodeDelete() throws Exception
	{
		billingGrpScreen();
		initializeVariable( billingGrpCdMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		boolean isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
		assertTrue( isBillingGrpCodePresent, "Billing Group Code is not available in the screen with  name: -'" + billingGrpName );
		psGenericHelper.clickDeleteOrUnDeleteAction( billingGrpName, "Name", "Delete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
		assertTrue( isBillingGrpCodePresent, billingGrpName + " is not present" );
		Log4jHelper.logInfo( "Billing Group Code is deleted successfully with the value-:'" + billingGrpName );

	}

	// Method: for Billing Group Code Undeletion action
	public void billingGrpCodeUnDelete() throws Exception
	{

		billingGrpScreen();
		initializeVariable( billingGrpCdMap );
		ButtonHelper.click( "ClearButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Deleted Items" );
		boolean isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
		assertTrue( isBillingGrpCodePresent, "Billing Group Code is not available in the screen with  name: -'" + billingGrpName );
		psGenericHelper.clickDeleteOrUnDeleteAction( billingGrpName, "Name", "Undelete" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.selectPartitionFilter( clientPartition, "Non Deleted Items" );
		isBillingGrpCodePresent = psGenericHelper.isGridTextValuePresent( "PS_Detail_billingGrpCode_name_textID", billingGrpName, "Name" );
		assertTrue( isBillingGrpCodePresent, billingGrpName + " is not present" );
		Log4jHelper.logInfo( "Billing Group Code is undeleted successfully with the  value:  '" + billingGrpName );

	}

	/* This method is used to click on 'New' action in Billing Group Code*/
	private void clickNewAction( String clientPartition ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( "Common Tasks" );
		psGenericHelper.clickNewAction( clientPartition );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_billingGrpCode_popUp_code_xpath" ), searchScreenWaitSec );
	}
	
	/* This method is used to click on action in Billing Group Code*/
	private void clickOnBillingGrpCodeAction( String parentActionNm, String childActionNm ) throws Exception
	{
		ProductUtil.waitForParentActionElementTOBeclickable( parentActionNm );
		NavigationHelper.navigateToAction( parentActionNm, childActionNm );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ElementHelper.waitForElement( GenericHelper.getORProperty( "PS_Detail_billingGrpCode_popUp_code_xpath" ), searchScreenWaitSec );
	}

	/*Method to configure Billing Group code*/
	private void configureBillingGroupCode() throws Exception
	{
		initializeConfigVariable( billingGrpCdMap );
	
		if ( ValidationHelper.isNotEmpty( account ) )
			accountEntitySearch();
		TextBoxHelper.type( "PS_Detail_billingGrpCode_name_textID", billingGrpName );
		TextBoxHelper.type( "PS_Detail_billingGrpCode_code_textID", billingGrpCd );
		if ( ValidationHelper.isNotEmpty( externalReference ) )
			TextBoxHelper.type( "PS_Detail_billingGrpCode_externalRefer_textID", externalReference );
	}
	
	/*Method to modify Billing Group code*/
	private void modifyBillingGroupCode() throws Exception
	{
		initializeConfigVariable( billingGrpCdMap );
		if ( ValidationHelper.isNotEmpty( account ) && !EntityComboHelper.getValue( "PS_Detail_billingGrpCode_account_textID" ).equals( account ) )
			accountEntitySearch();
		assertEquals( TextBoxHelper.getValue( "PS_Detail_billingGrpCode_name_textID" ), billingGrpName,"Billing Groupd Code name is not matched" );
		ProductUtil.modifyTextBox( "PS_Detail_billingGrpCode_code_textID", billingGrpCd );
		ProductUtil.modifyTextBox( "PS_Detail_billingGrpCode_externalRefer_textID", externalReference );
	}

	//Method: for account entity search
	private void accountEntitySearch() throws Exception
	{
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforEntityElement();
		EntityComboHelper.clickEntityIcon( "PS_Detail_billingGrpCode_account_textID" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		psGenericHelper.waitforPopupHeaderElement( "Status" );
		int row = SearchGridHelper.gridFilterSearchWithTextBox( "accountName_Detail", account, "Account Name" );
		GridHelper.clickRow( "searchGrid", row, "Name" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
		ButtonHelper.click( "OKButton" );
		GenericHelper.waitForLoadmask( searchScreenWaitSec );
	}

}
